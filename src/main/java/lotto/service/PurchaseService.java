package lotto.service;

import lotto.domain.DepositAmount;

/**
 * 로또 구매 관련 로직을 담당하는 클래스
 */
public class PurchaseService {
    public DepositAmount depositMoney(String amount) {
        return DepositAmount.parse(amount);
    }
}
