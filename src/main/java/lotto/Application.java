package lotto;

import lotto.controller.LottoController;
import lotto.service.PurchaseService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        PurchaseService purchaseService = new PurchaseService();

        LottoController controller = new LottoController(inputView, outputView, purchaseService);
        controller.run();
    }
}
