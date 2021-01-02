package org.zhuch.dreamblog.input.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.jetbrains.annotations.NotNull;
import org.zhuch.dreamblog.domain.User;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    @With
    Long userId;
    String username;
    String email;
    String password;
    int roleId;
    @With
    LocalDateTime created;
    @With
    LocalDateTime updated;

    @NotNull
    public static UserDto fromDomain(@NotNull final User domain) {
        return new UserDto(domain.getUserId(), domain.getUsername(),
                domain.getEmail(), domain.getPasswordHash(), domain.getRoleId(),
                domain.getCreated(), domain.getUpdated());
    }
}
