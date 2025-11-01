package lotto.service;

import lotto.domain.*;
import lotto.util.LottoNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

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
        @DisplayName("유효한 당첨 번호 입력 시 WinningNumbers 객체를 생성한다.")
        @Test
        void should_CreateWinningNumbers_ForValidInput() {
            // given
            String input = "1,2,3,4,5,6";

            // when
            WinningNumbers result = drawService.determineWinningNumbers(input);

            // then
            assertThat(result.contains(1)).isTrue();
            assertThat(result.contains(2)).isTrue();
            assertThat(result.contains(3)).isTrue();
            assertThat(result.contains(4)).isTrue();
            assertThat(result.contains(5)).isTrue();
            assertThat(result.contains(6)).isTrue();
            assertThat(result.contains(7)).isFalse();
        }

        @DisplayName("유효한 보너스 번호 입력 시 BonusNumber 객체를 생성한다.")
        @Test
        void should_CreateBonusNumber_ForValidInput() {
            // given
            String input = "7";
            WinningNumbers winningNumbers = WinningNumbers.from("1,2,3,4,5,6");

            // when
            BonusNumber result = drawService.determineBonusNumber(input, winningNumbers);

            // then
            assertThat(result.hasMatchingNumber(List.of(7))).isTrue();
        }

        @DisplayName("로또 추첨 시 로또 당첨 결과를 담고 있는 Prizes 객체를 생성한다.")
        @Test
        void should_CreatePrizes() {
            // given
            LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator() {
                @Override
                public List<Integer> generateUniqueNumbersInRange() {
                    return List.of(1,2,3,4,5,6);
                }
            };
            Lottos lottos = Lottos.issue(3, lottoNumberGenerator);
            WinningNumbers winningNumbers = WinningNumbers.from("1,2,3,4,5,6");
            BonusNumber bonusNumber = BonusNumber.of("7", winningNumbers);

            // when
            Prizes result = Prizes.of(lottos, winningNumbers, bonusNumber);

            // then
            assertThat(result.countPrizes(Prize.FIRST_PRIZE)).isEqualTo(3);
        }

    }

    @Nested
    class ExceptionTest {
        @DisplayName("당첨 번호 선정 중 유효성 검증 실패 시 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"1,2,3,4,5,5", "당첨번호"})
        void should_ThrowException_WhenInvalidWinningNumbersEntered(String input) {
            // when & then
            assertThatThrownBy(() -> drawService.determineWinningNumbers(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("보너스 번호 선정 중 유효성 검증 실패 시 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"", "보너스번호", " ", "1"})
        void should_ThrowException_WhenInvalidBonusNumberEntered(String input) {
            // given
            WinningNumbers winningNumbers = WinningNumbers.from("1,2,3,4,5,6");

            // when & then
            assertThatThrownBy(() -> drawService.determineBonusNumber(input, winningNumbers))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
