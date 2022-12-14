package bridge.view;

import bridge.validator.InputBridgeSizeValidator;
import bridge.validator.InputMovingValidator;
import bridge.validator.InputRetryOrExitValidator;
import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String BRIDGE_SIZE_INPUT_MESSAGE = "다리의 길이를 입력해주세요.";
    private static final String MOVING_INPUT_MESSAGE = "이동할 칸을 선택해주세요. (위: U, 아래: D)";
    private static final String COMMAND_INPUT_MESSAGE = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";

    private static final String NUMBER_FORMAT_ERROR_MESSAGE = "[ERROR] 숫자를 입력해야 합니다.";

    public static int readBridgeSize() {
        System.out.println(BRIDGE_SIZE_INPUT_MESSAGE);
        int size = 0;
        try {
            size = convertStringToInt(Console.readLine());
            InputBridgeSizeValidator.validateBridgeSize(size);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readBridgeSize();
        }
        return size;
    }

    public static String readMoving() {
        System.out.println(MOVING_INPUT_MESSAGE);

        String movingInput = Console.readLine();
        try {
            InputMovingValidator.validateMovingInput(movingInput);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readMoving();
        }
        return movingInput;
    }

    public static String readGameCommand() {
        System.out.println(COMMAND_INPUT_MESSAGE);

        String command = Console.readLine();
        try {
            InputRetryOrExitValidator.validateRetryOrExitCommand(command);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readGameCommand();
        }
        return command;
    }

    private static int convertStringToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }
}
