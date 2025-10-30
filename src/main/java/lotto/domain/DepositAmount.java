package lotto.domain;

import lotto.error.ErrorMessage;

/**
 * 구입금액 도메인 클래스
 */
public class DepositAmount {
    private static final int LOTTO_PRICE = 1000;
    private static final int MAXIMUM_AMOUNT = Math.floorDiv(Integer.MAX_VALUE, LOTTO_PRICE) * LOTTO_PRICE;

    private final int amount;

    private DepositAmount(int amount) {
        validateDepositRule(amount);
        this.amount = amount;

    }

    public static DepositAmount parse(String depositAmount) {
        String refinedAmount = refineInput(depositAmount);
        int parsedAmount = parseToInt(refinedAmount);
        return new DepositAmount(parsedAmount);
    }

    /**
     * 현재 입금액으로 구매 가능한 로또의 개수를 반환합니다.
     * @return 구매 가능한 로또 개수
     */
    public int getNumberOfPurchasableLotto() {
        return amount / LOTTO_PRICE;
    }

    private void validateDepositRule(int amount) {
        boolean isLessThanMinimum = amount < LOTTO_PRICE;
        if (isLessThanMinimum) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_LESS_THAN_MINIMUM.getMessage());
        }

        boolean isOverMaximum = amount > MAXIMUM_AMOUNT;
        if (isOverMaximum) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_OVER_MAXIMUM.getMessage());
        }

        boolean isNotDivisibleByLottoPrice = (amount % LOTTO_PRICE != 0);
        if (isNotDivisibleByLottoPrice) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_NOT_DIVISIBLE_BY_LOTTO_PRICE.getMessage());
        }
    }

    private static String refineInput(String input) {
        boolean isNullOrBlank = (input == null) || input.isBlank();
        if (isNullOrBlank) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_NULL_OR_BLANK.getMessage());
        }
        return input.trim();
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            distinguishNumberFormatError(input);
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_OVER_MAXIMUM.getMessage());
        }
    }

    private static void distinguishNumberFormatError(String input) {
        try {
            Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_NOT_CONVERTIBLE_TO_NUMERIC.getMessage());
        }
    }
}
