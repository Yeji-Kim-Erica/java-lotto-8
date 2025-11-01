package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;

/**
 * 프로그램의 모든 출력을 담당하는 클래스
 */
public class OutputView {
    private static final String DEPOSIT_INPUT_PROMPT = "구입금액을 입력해 주세요.";
    private static final String LOTTO_QUANTITY_ISSUED = "%d개를 구매했습니다.";
    private static final String WINNING_NUMBER_INPUT_PROMPT = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_INPUT_PROMPT = "보너스 번호를 입력해 주세요.";

    private OutputView() {}

    public static void printBlankLine() {
        System.out.println();
    }

    public static void printDepositPrompt() {
        System.out.println(DEPOSIT_INPUT_PROMPT);
    }

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println();
    }

    public static void printLottoIssuanceDetails(Lottos lottos) {
        System.out.println();
        System.out.printf(LOTTO_QUANTITY_ISSUED, lottos.size());
        System.out.println();
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
        System.out.println();
    }

    public static void printWinningNumberPrompt() {
        System.out.println(WINNING_NUMBER_INPUT_PROMPT);
    }

    public static void printBonusNumberPrompt() {
        System.out.println(BONUS_NUMBER_INPUT_PROMPT);
    }
}
