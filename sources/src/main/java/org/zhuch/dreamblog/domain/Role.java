package org.zhuch.dreamblog.domain;

import org.jetbrains.annotations.NotNull;

public enum Role {
    ADMIN(1), USER(2);

    int ordinalNumber;

    Role(final int ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    @NotNull
    public static Role fromInt(final int i) {
        switch (i) {
            case 1:
                return ADMIN;
            case 2:
                return USER;
            default:
                throw new IllegalArgumentException("wrong role ordinal number");
        }
    }

    public static int toInt(@NotNull final Role role) {
        return role.ordinalNumber;
    }
}
