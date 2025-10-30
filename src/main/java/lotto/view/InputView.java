package lotto.view;

import camp.nextstep.edu.missionutils.Console;

/**
 * 프로그램의 모든 입력을 담당하는 클래스
 */
public class InputView {
    public String readDepositAmount() {
        return Console.readLine();
    }
}
