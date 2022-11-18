package bridge.domain;

import java.util.List;

public class Bridge {

    static final String up = "U";
    static final String down = "D";

    private final List<String> bridge;

    public Bridge(List<String> bridge) {
        validate(bridge);
        this.bridge = bridge;
    }

    private void validate(List<String> bridge) {
        if (bridge.stream()
                .anyMatch(element -> !element.equals(up) && !element.equals(down))) {
            throw new IllegalArgumentException("다리는 위, 아래 두 칸만으로 이루어져 있습니다.");
        }
    }
}
