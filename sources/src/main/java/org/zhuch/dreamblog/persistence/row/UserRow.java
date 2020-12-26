package org.zhuch.dreamblog.persistence.row;

import liquibase.pro.packaged.L;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.zhuch.dreamblog.persistence.Updatable;

import java.time.LocalDateTime;

@Value
@Table(UserRow.USER)
public class UserRow implements Updatable<Long> {
    public static final String USER = "users";

    @Id
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

    public static UserRow of(String username, String email, String password_hash,
            int role_id, LocalDateTime created) {
        return new UserRow(null, username, email, password_hash, role_id, created, created);
    }

    public static UserRow of(String username, String email, String password_hash, LocalDateTime created) {
        return new UserRow(null, username, email, password_hash, 2, created, created);
    }

    public static UserRow of(String username, String email, String password_hash) {
        var now = LocalDateTime.now();
        return new UserRow(null, username, email, password_hash, 2, now, now);
    }

    @Override
    public Long getId() {
        return userId;
    }
}
