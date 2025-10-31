package lotto.service;

import lotto.domain.WinningNumbers;

/**
 * 당첨 처리 관련 로직을 담당하는 클래스
 */
public class DrawService {
    public WinningNumbers determineWinningNumbers(String winningNumbers) {
        return WinningNumbers.from(winningNumbers);
    }
}
