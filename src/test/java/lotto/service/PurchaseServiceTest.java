package lotto.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PurchaseServiceTest {
    private PurchaseService purchaseService;

    @BeforeEach
    void setUp() {
        purchaseService = new PurchaseService();
    }

    @Nested
    class ExceptionTest {
        @DisplayName("입금 과정에서 예외가 발생한 경우 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"2500", "1000원"})
        void should_ThrowException_WhenWrongDepositOccurs(String input) {
            // when & then
            assertThatThrownBy(() -> purchaseService.depositMoney(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
