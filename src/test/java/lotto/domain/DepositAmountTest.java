package lotto.domain;

import lotto.error.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DepositAmountTest {
    @Nested
    class SuccessTest {
        @DisplayName("구입 금액으로 구매 가능한 로또의 개수를 반환한다")
        @ParameterizedTest(name = "{0}원 입금 시 {1}개의 로또를 구매한다")
        @CsvSource(value = {"1000,1", "5000,5", "10000,10"})
        void should_ReturnNumberOfAffordableLotto(String input, int expected) {
            // when
            DepositAmount depositAmount = DepositAmount.parse(input);
            int numberOfPurchasableLotto = depositAmount.getNumberOfPurchasableLotto();

            // then
            assertThat(numberOfPurchasableLotto).isEqualTo(expected);
        }
    }

    @Nested
    class ExceptionTest {
        @DisplayName("입력 값이 존재하지 않는 경우")
        @ParameterizedTest
        @EmptySource
        @NullSource
        @ValueSource(strings = {"  ", ""})
        void should_ThrowException_ForNullOrBlank(String input) {
            // when & then
            assertThatThrownBy(() -> DepositAmount.parse(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.INPUT_NULL_OR_BLANK.getMessage());
        }

        @DisplayName("입력값을 숫자로 변환 불가한 경우")
        @ParameterizedTest(name = "{0}은 숫자로 변환할 수 없다")
        @ValueSource(strings = {"3천원", "3k", "$1000", "3,000", "3000.00", "3 000"})
        void should_ThrowException_When_NotConvertibleToNumeric(String input) {
            // when & then
            assertThatThrownBy(() -> DepositAmount.parse(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.INPUT_NOT_CONVERTIBLE_TO_NUMERIC.getMessage());
        }

        @DisplayName("최소 입금 금액(로또 1장의 가격)보다 적은 경우")
        @ParameterizedTest(name = "{0}원은 입금 불가하다")
        @ValueSource(strings = {"-1000", "0", "500"})
        void should_ThrowException_When_LessThanMinimum(String input) {
            // when & then
            assertThatThrownBy(() -> DepositAmount.parse(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.AMOUNT_LESS_THAN_MINIMUM.getMessage());
        }

        @DisplayName("로또 가격으로 나누어 떨어지지 않는 경우")
        @Test
        void should_ThrowException_When_NotDivisibleByLottoPrice() {
            // given
            String input = "5500";

            // when & then
            assertThatThrownBy(() -> DepositAmount.parse(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.AMOUNT_NOT_DIVISIBLE_BY_LOTTO_PRICE.getMessage());
        }

        @DisplayName("최대 입금 가능 금액을 초과한 경우")
        @ParameterizedTest
        @ValueSource(strings = {Integer.MAX_VALUE + "", "999999999999999999"})
        void should_ThrowException_When_TooLarge(String input) {
            // when & then
            assertThatThrownBy(() -> DepositAmount.parse(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.AMOUNT_OVER_MAXIMUM.getMessage());
        }
    }
}
