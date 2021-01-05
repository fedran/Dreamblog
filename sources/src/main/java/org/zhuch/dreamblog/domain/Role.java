package org.zhuch.dreamblog.domain;

import org.jetbrains.annotations.NotNull;

public enum Role {
    ADMIN(1), USER(2);

    private int id;

    Role(final int id) {
        this.id = id;
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
        return role.id;
    }
}
