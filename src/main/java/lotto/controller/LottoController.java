package lotto.controller;

import lotto.domain.DepositAmount;
import lotto.domain.Lottos;
import lotto.service.PurchaseService;
import lotto.view.InputView;
import lotto.view.OutputView;

/**
 * 로또 발매기 프로그램의 전체 흐름을 담당하는 클래스
 */
public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final PurchaseService purchaseService;

    public LottoController(InputView inputView, OutputView outputView, PurchaseService purchaseService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.purchaseService = purchaseService;
    }

    public void run() {
        DepositAmount depositAmount = makeDeposit();
        Lottos lottos = buyLottos(depositAmount);
    }

    private DepositAmount makeDeposit() {
        while (true) {
            try {
                return attemptDeposit();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private DepositAmount attemptDeposit() {
        outputView.printDepositPrompt();
        String depositAmount = inputView.readDepositAmount();
        return purchaseService.depositMoney(depositAmount);
    }

    private Lottos buyLottos(DepositAmount depositAmount) {
        Lottos lottos = purchaseService.purchaseLottos(depositAmount);
        outputView.printLottoIssuanceDetails(lottos);
        return lottos;
    }
}
