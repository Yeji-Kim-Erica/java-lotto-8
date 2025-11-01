package lotto.domain;

import java.util.*;

/**
 * 로또 도메인 클래스
 */
public class Lotto extends LotteryNumbers {
    public Lotto(List<Integer> numbers) {
        super(copyAndSort(numbers));
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public int countMatchingWinningNumbers(WinningNumbers winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean containsBonusNumber(BonusNumber bonusNumber) {
        for (int number : numbers) {
            if (bonusNumber.isEqualTo(number)) {
                return true;
            }
        }
        return false;
    }

    private static List<Integer> copyAndSort(List<Integer> numbers) {
        List<Integer> copiedNumbers = new ArrayList<>(numbers);
        Collections.sort(copiedNumbers);
        return copiedNumbers;
    }
}
