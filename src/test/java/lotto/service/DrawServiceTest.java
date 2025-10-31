package lotto.service;

import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DrawServiceTest {
    private DrawService drawService;

    @BeforeEach
    void setUp() {
        drawService = new DrawService();
    }

    @Nested
    class SuccessTest {
        @Test
        @DisplayName("유효한 당첨 번호 입력 시 WinningNumbers 객체를 생성한다.")
        void determineWinningNumbers_ShouldCreateWinningNumbers() {
            // given
            String input = "1,2,3,4,5,6";

            // when
            WinningNumbers result = drawService.determineWinningNumbers(input);

            // then
            assertThat(result.getNumbers()).hasSize(6);
        }
    }

    @Nested
    class ExceptionTest {
        @DisplayName("당첨 번호 선정 중 유효성 검증 실패 시 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"1,2,3,4,5,5", "당첨번호"})
        void should_ThrowException_WhenWrongWinningNumbersEntered(String input) {
            // when & then
            assertThatThrownBy(() -> drawService.determineWinningNumbers(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
