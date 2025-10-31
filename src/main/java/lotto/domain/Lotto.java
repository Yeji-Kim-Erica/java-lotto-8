package lotto.domain;

import lotto.error.ErrorMessage;

import java.util.*;

/**
 * 로또 도메인 클래스
 */
public class Lotto extends LotteryNumbers {
    public Lotto(List<Integer> numbers) {
        super(numbers);
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
}
