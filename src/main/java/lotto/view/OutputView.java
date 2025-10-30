package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;

import java.util.Arrays;
import java.util.List;

/**
 * 프로그램의 모든 출력을 담당하는 클래스
 */
public class OutputView {
    private static final String DEPOSIT_INPUT_PROMPT = "구입금액을 입력해 주세요.";
    private static final String LOTTO_QUANTITY_ISSUED = "%d개를 구매했습니다.\n";

    public void printDepositPrompt() {
        System.out.println(DEPOSIT_INPUT_PROMPT);
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println();
    }

    public void printLottoIssuanceDetails(Lottos lottos) {
        System.out.println();
        System.out.printf(LOTTO_QUANTITY_ISSUED, lottos.size());
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
    }
}
