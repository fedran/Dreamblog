package org.zhuch.dreamblog.domain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    Role role;
    @With
    LocalDateTime created;
    @With
    LocalDateTime updated;

    public static User fromDto(final UserDto userDto) {
        return new User(userDto.getUserId(), userDto.getUsername(),
            userDto.getEmail(),
            new BCryptPasswordEncoder().encode(userDto.getPassword()),
            Role.valueOf(userDto.getRole()), userDto.getCreated(),
            userDto.getUpdated());
    }

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
