package org.zhuch.dreamblog.input.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.jetbrains.annotations.NotNull;
import org.zhuch.dreamblog.domain.Role;
import org.zhuch.dreamblog.domain.User;
import lombok.Value;
import lombok.With;
import org.zhuch.dreamblog.validation.ValueOfEnum;

import java.time.LocalDateTime;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    @With
    Long userId;
    @javax.validation.constraints.NotNull
    String username;
    @javax.validation.constraints.NotNull
    String email;
    String password;
    @ValueOfEnum(enumClass = Role.class)
    String role;
    @With
    LocalDateTime created;
    @With
    LocalDateTime updated;

    @NotNull
    public static UserDto fromDomain(@NotNull final User domain) {
        return new UserDto(domain.getUserId(), domain.getUsername(),
            domain.getEmail(), null, domain.getRole().name(),
            domain.getCreated(), domain.getUpdated());
    }
}
