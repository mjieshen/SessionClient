package entity;

public enum ActionType {
    Start("start"),
    Stop("stop");

    private String name;

    ActionType(String name) {
        this.name = name;
    }
}
