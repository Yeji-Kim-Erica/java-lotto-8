package lotto.domain;

import lotto.error.ErrorMessage;

import java.util.*;

/**
 * 로또 도메인 클래스
 */
public class Lotto {
    private static final int VALID_SIZE = 6;
    private static final int NUMBER_START_RANGE = 1;
    private static final int NUMBER_END_RANGE = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLottoRule(numbers);
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        this.numbers = sortedNumbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public int countMatchingNumbers(List<Integer> winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private void validateLottoRule(List<Integer> numbers) {
        boolean isInconsistentWithValidSize = (numbers.size() != VALID_SIZE);
        if (isInconsistentWithValidSize) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_SIZE_INVALID.getMessage());
        }

        validateLottoNumbers(numbers);
    }

    private void validateLottoNumbers(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (int number : numbers) {
            boolean isOutOfRange = (number < NUMBER_START_RANGE) || (number > NUMBER_END_RANGE);
            if (isOutOfRange) {
                throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_OUT_OF_RANGE.getMessage());
            }

            boolean hasDuplicateNumber = !uniqueNumbers.add(number);
            if (hasDuplicateNumber) {
                throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_DUPLICATED.getMessage());
            }
        }
    }
}
