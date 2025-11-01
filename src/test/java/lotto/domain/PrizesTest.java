package lotto.domain;

import lotto.util.LottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PrizesTest {
    @Nested
    class SuccessTest {
        @DisplayName("로또의 당첨 결과를 확인해 Prizes 객체를 반환한다")
        @ParameterizedTest(name = "2등 당첨 장수는 {1}장이다.")
        @MethodSource
        void should_ReturnsPrizes(Lottos lottos, int expected) {
            // given
            WinningNumbers winningNumbers = WinningNumbers.from("1,2,3,4,5,7");
            BonusNumber bonusNumber = BonusNumber.of("6", winningNumbers);

            // when
            Prizes result = Prizes.of(lottos, winningNumbers, bonusNumber);

            // then
            assertThat(result.countPrizes(Prize.SECOND_PRIZE)).isEqualTo(expected);
        }

        private static Stream<Arguments> should_ReturnsPrizes() {
            LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator() {
                @Override
                public List<Integer> generateUniqueNumbersInRange() {
                    return List.of(1,2,3,4,5,6);
                }
            };
            return Stream.of(
                    Arguments.of(Lottos.issue(1, lottoNumberGenerator), 1),
                    Arguments.of(Lottos.issue(2, lottoNumberGenerator), 2),
                    Arguments.of(Lottos.issue(3, lottoNumberGenerator), 3)
            );
        }
    }
}
