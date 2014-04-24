package org.aterfact.objects.enums;

public enum AdminLevel {
    PLAYER(0),
    ANIMATOR(1),
    MODERATOR(2),
    ADMINISTRATOR(3);

    private int adminLevel;
    private AdminLevel(int adminLevel) {
        this.adminLevel = adminLevel;
    }

    public int getValue() {
        return this.adminLevel;
    }
}
