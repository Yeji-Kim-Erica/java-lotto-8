package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

/**
 * Set Collection에 대한 학습 테스트 클래스
 */
public class SetTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @DisplayName("Set의 크기를 반환한다")
    @Test
    void size_ShouldReturnSizeOfSet() {
        // when & then
        assertThat(numbers.size()).isEqualTo(3);
    }

    @DisplayName("Set에 특정 값이 존재하면 true를 반환한다")
    @ParameterizedTest(name = "[{index}] Set에 {0}이 값으로 존재한다")
    @ValueSource(ints = {1, 2, 3})
    void contains_ShouldReturnTrueForMemberValue(int memberValue) {
        // when & then
        assertThat(numbers.contains(memberValue)).isTrue();
    }

    @DisplayName("Set에 특정 값이 존재하는지 여부를 boolean값으로 반환한다")
    @ParameterizedTest
    @CsvSource(value = {
            "1:true",
            "2:true",
            "3:true",
            "4:false",
            "5:false"
    }, delimiter = ':')
    void contains_ShouldReturnTrueOrFalseIfSetContainsCertainValue(int testValue, boolean expected) {
        // when & then
        assertThat(numbers.contains(testValue)).isEqualTo(expected);
    }
}
