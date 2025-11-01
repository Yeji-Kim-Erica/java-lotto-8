package lotto.controller;

import lotto.domain.BonusNumber;
import lotto.domain.DepositAmount;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;
import lotto.service.DrawService;
import lotto.service.PurchaseService;
import lotto.view.InputView;
import lotto.view.OutputView;

/**
 * 로또 발매기 프로그램의 전체 흐름을 담당하는 클래스
 */
public class LottoController {
    private final PurchaseService purchaseService;
    private final DrawService drawService;

    public LottoController(PurchaseService purchaseService, DrawService drawService) {
        this.purchaseService = purchaseService;
        this.drawService = drawService;
    }

    public void run() {
        DepositAmount depositAmount = makeDeposit();
        Lottos lottos = buyLottos(depositAmount);
        WinningNumbers winningNumbers = drawWinningNumbers();
        BonusNumber bonusNumber = drawBonusNumber(winningNumbers);
    }

    private DepositAmount makeDeposit() {
        while (true) {
            try {
                return attemptDeposit();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

    private DepositAmount attemptDeposit() {
        OutputView.printDepositPrompt();
        String depositAmount = InputView.readDepositAmount();
        return purchaseService.depositMoney(depositAmount);
    }

    private Lottos buyLottos(DepositAmount depositAmount) {
        Lottos lottos = purchaseService.purchaseLottos(depositAmount);
        OutputView.printLottoIssuanceDetails(lottos);
        return lottos;
    }

    private WinningNumbers drawWinningNumbers() {
        while (true) {
            try {
                return attemptSelectingWinningNumbers();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

    private WinningNumbers attemptSelectingWinningNumbers() {
        OutputView.printWinningNumberPrompt();
        String winningNumbers = InputView.readWinningNumbers();
        return drawService.determineWinningNumbers(winningNumbers);
    }

    private BonusNumber drawBonusNumber(WinningNumbers winningNumbers) {
        OutputView.printBlankLine();
        while (true) {
            try {
                return attemptSelectingBonusNumber(winningNumbers);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

    private BonusNumber attemptSelectingBonusNumber(WinningNumbers winningNumbers) {
        OutputView.printBonusNumberPrompt();
        String bonusNumber = InputView.readBonusNumber();
        return drawService.determineBonusNumber(bonusNumber, winningNumbers);
    }
}
