package org.zhuch.dreamblog.domain;

import org.zhuch.dreamblog.persistence.row.UserRow;
import org.zhuch.dreamblog.input.json.UserDto;
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
    int roleId;
    @With
    LocalDateTime created;
    @With
    LocalDateTime updated;

    public static User fromDto(final UserDto dto) {
        return new User(dto.getUserId(), dto.getUsername(), dto.getEmail(),
                dto.getPassword(), dto.getRoleId(),
                dto.getCreated(), dto.getUpdated());
    }

    public static User fromRow(final UserRow row) {
        return new User(row.getUserId(), row.getUsername(), row.getEmail(),
                row.getPasswordHash(), row.getRoleId(),
                row.getCreated(), row.getUpdated());
    }

    public UserRow toRow() {
        return new UserRow(userId, username, email, passwordHash,
                roleId, created, updated);
    }
}
