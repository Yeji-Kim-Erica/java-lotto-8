package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * String에 대한 학습 테스트 클래스
 */
public class StringTest {
    @DisplayName("split 테스트")
    @Nested
    class SplitTest {
        private static final String DELIMITER = ",";

        @DisplayName("문자열을 구분자 기준으로 분리한다")
        @Test
        void splitWithDelimiter() {
            // given
            String input = "1,2";

            // when & then
            assertThat(input.split(DELIMITER)).contains("2", "1");
            assertThat(input.split(DELIMITER)).containsExactly("1", "2");
        }

        @DisplayName("문자열에 구분자가 없을 때 해당 문자열만을 포함하는 배열을 반환한다")
        @Test
        void splitWithOutDelimiter() {
            // given
            String input = "1";

            // when & then
            assertThat(input.split(DELIMITER)).containsExactly("1");
        }
    }

    @DisplayName("substring 테스트")
    @Nested
    class SubstringTest {
        @DisplayName("특정 문자열을 제외한 범위의 문자열을 반환한다")
        @Test
        void getStringInCertainRange() {
            // given
            String input = "(1,2)";
            int beginIndex = input.indexOf("(") + 1;
            int endIndex = input.indexOf(")");

            // when & then
            assertThat(input.substring(beginIndex, endIndex)).isEqualTo("1,2");
        }
    }

    @DisplayName("charAt 테스트")
    @Nested
    class CharAtTest {
        private static final String INPUT = "abc";

        @DisplayName("특정 위치의 문자를 가져온다")
        @Test
        void getCharacterAtCertainIndex() {
            // when & then
            assertThat(INPUT.charAt(0)).isEqualTo('a');
        }

        @DisplayName("특정 위치의 문자를 가져올 때 위치 값을 벗어나면 예외가 발생한다")
        @Test
        void throwExceptionWhenFindingCharacterOutOfIndex() {
            // when & then
            assertThatThrownBy(() -> INPUT.charAt(3))
                    .isInstanceOf(StringIndexOutOfBoundsException.class)
                    .hasMessageContaining("Index 3 out of bounds for length 3");

            assertThatExceptionOfType(StringIndexOutOfBoundsException.class)
                    .isThrownBy(() -> INPUT.charAt(5))
                    .withMessageMatching("Index \\d+ out of bounds for length \\d+");
        }
    }
}
