package org.zhuch.dreamblog.domain;

import org.jetbrains.annotations.NotNull;

public enum Role {
    ADMIN, USER;

    @NotNull
    public static Role fromInt(final int i) {
        if (i == 1) {
            return ADMIN;
        }
        return USER;
    }

    public static int toInt(@NotNull final Role role) {
        if (role == ADMIN) {
            return 1;
        }
        return 2;
    }
}
