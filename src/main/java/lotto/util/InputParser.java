package lotto.util;

import lotto.error.InputNotNumericException;
import lotto.error.InputNullOrBlankException;
import lotto.error.InputNumberOverflowException;

/**
 * 사용자 입력을 변환, 검증하는 유틸리티 클래스
 */
public final class InputParser {
    private InputParser() {}

    public static String refineInput(String input) {
        boolean isNullOrBlank = (input == null) || input.isBlank();
        if (isNullOrBlank) {
            throw new InputNullOrBlankException();
        }
        return input.trim();
    }

    public static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            distinguishNumberFormatError(input);
            throw new InputNumberOverflowException();
        }
    }

    private static void distinguishNumberFormatError(String input) {
        try {
            Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new InputNotNumericException();
        }
    }
}
