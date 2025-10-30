package lotto.util;

import java.util.List;

/**
 * 숫자 목록을 생성하는 클래스
 */
public interface LottoNumberGenerator {
    public List<Integer> generateUniqueNumbersInRange();
}
