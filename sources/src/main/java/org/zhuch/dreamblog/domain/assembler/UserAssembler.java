package org.zhuch.dreamblog.domain.assembler;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhuch.dreamblog.input.json.UserDto;
import org.zhuch.dreamblog.domain.Role;
import org.zhuch.dreamblog.domain.User;

@Service
public class UserAssembler {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAssembler(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User fromDto(final UserDto userDto) {
        return new User(userDto.getUserId(), userDto.getUsername(),
            userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
            Role.valueOf(userDto.getRole()), userDto.getCreated(),
            userDto.getUpdated());
    }
}
