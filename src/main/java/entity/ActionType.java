package entity;

/**
 * Created by shenjie on 2020/08/21.
 */
public enum ActionType {
    Start("start"),
    Stop("stop");

    private String name;

    ActionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
