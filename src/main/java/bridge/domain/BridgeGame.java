package bridge.domain;

import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.BridgeRandomNumberGenerator;
import bridge.validator.InputValidator;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final Bridge bridge;
    private final int bridgeSize;
    private UserPath userPath;
    private int userPosition;

    public BridgeGame(int size) {
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);

        this.bridge = new Bridge(bridgeMaker.makeBridge(size));
        this.bridgeSize = size;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean move(String userInput) {
        boolean isPass = bridge.compareWithPosition(userPosition, userInput);

        userPath.move(userInput);
        userPosition++;

        return isPass;
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean retry(String command) {
        if (command.equals(InputValidator.RETRY)) {
            userPosition = 0;
            userPath.initialize();
            return true;
        }
        return false;
    }

    public boolean checkSuccess() {
        if (userPosition == bridgeSize)
            return true;
        return false;
    }

}
