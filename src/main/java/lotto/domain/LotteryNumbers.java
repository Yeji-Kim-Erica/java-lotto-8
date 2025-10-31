package lotto.domain;

import lotto.error.DuplicateLottoNumberException;
import lotto.error.ErrorMessage;
import lotto.error.InvalidLottoSizeException;
import lotto.error.LottoNumberOutOfRangeException;

import java.util.*;

/**
 * 로또 번호 조합의 공통 규칙을 정의하는 추상화 클래스
 */
public abstract class LotteryNumbers {
    private static final int VALID_SIZE = 6;
    private static final int NUMBER_START_RANGE = 1;
    private static final int NUMBER_END_RANGE = 45;

    protected final List<Integer> numbers;

    protected LotteryNumbers(List<Integer> numbers) {
        validateLotteryNumberRule(numbers);
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        this.numbers = sortedNumbers;
    }

    private void validateLotteryNumberRule(List<Integer> numbers) {
        boolean isInconsistentWithValidSize = (numbers.size() != VALID_SIZE);
        if (isInconsistentWithValidSize) {
            throw new InvalidLottoSizeException(ErrorMessage.LOTTO_SIZE_INVALID.getMessage());
        }

        validateLotteryNumbers(numbers);
    }

    private void validateLotteryNumbers(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (int number : numbers) {
            boolean isOutOfRange = (number < NUMBER_START_RANGE) || (number > NUMBER_END_RANGE);
            if (isOutOfRange) {
                throw new LottoNumberOutOfRangeException(ErrorMessage.LOTTO_NUMBER_OUT_OF_RANGE.getMessage());
            }

            boolean hasDuplicateNumber = !uniqueNumbers.add(number);
            if (hasDuplicateNumber) {
                throw new DuplicateLottoNumberException(ErrorMessage.LOTTO_NUMBER_DUPLICATED.getMessage());
            }
        }
    }
}
