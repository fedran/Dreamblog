package org.zhuch.dreamblog.domain;

import org.zhuch.dreamblog.persistence.row.UserRow;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;

@Value
public class User {
    @With
    Long userId;
    String username;
    String email;
    String passwordHash;
    Role role;
    @With
    LocalDateTime created;
    @With
    LocalDateTime updated;

    public static User fromRow(final UserRow row) {
        return new User(row.getUserId(), row.getUsername(), row.getEmail(),
            row.getPasswordHash(), Role.fromInt(row.getRoleId()),
            row.getCreated(), row.getUpdated());
    }

    public UserRow toRow() {
        return new UserRow(userId, username, email, passwordHash,
            role.ordinal() + 1, created, updated);
    }
}
