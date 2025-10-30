package lotto.view;

/**
 * 프로그램의 모든 출력을 담당하는 클래스
 */
public class OutputView {
    private static final String DEPOSIT_INPUT_PROMPT = "구입금액을 입력해 주세요.";

    public void printDepositPrompt() {
        System.out.println(DEPOSIT_INPUT_PROMPT);
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println();
    }
}
