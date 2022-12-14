package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.domain.BridgeGame;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeGameController {

    private final BridgeMaker bridgeMaker;

    public BridgeGameController() {
        this.bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
    }

    public void startGame() {
        OutputView.printStartMessage();
        int bridgeSize = InputView.readBridgeSize();

        OutputView.printBlankLine();

        BridgeGame bridgeGame = new BridgeGame(bridgeSize, bridgeMaker);
        playGame(bridgeGame);
    }

    private void playGame(BridgeGame bridgeGame) {
        while (true) {
            if (!continueGame(bridgeGame)) {
                OutputView.printResult(bridgeGame);
                return;
            }
        }
    }

    private boolean continueGame(BridgeGame bridgeGame) {
        String movement = InputView.readMoving();

        boolean isPass = bridgeGame.move(movement);
        boolean isSuccess = bridgeGame.checkSuccess();

        OutputView.printMap(bridgeGame);

        return isContinue(bridgeGame, isPass, isSuccess);
    }

    private boolean isContinue(BridgeGame bridgeGame, boolean isPass, boolean isSuccess) {
        if (isPass && isSuccess) {
            return false;
        }
        if (!isPass) {
            String command = InputView.readGameCommand();
            return bridgeGame.retry(command);
        }

        return true;
    }
}
