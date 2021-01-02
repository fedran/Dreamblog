package org.zhuch.dreamblog.input.controller;

import org.zhuch.dreamblog.domain.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.zhuch.dreamblog.input.json.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.zhuch.dreamblog.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUser(@PathVariable("id") final Long id) {
        return userService.findById(id)
                .map(user -> ResponseEntity.ok().body(UserDto.fromDomain(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getUsers(
            @RequestParam(name = "pattern", required = false) final String pattern,
            @RequestParam(name = "page", required = false) final Integer page,
            @RequestParam(name = "size", required = false) final Integer size) {
        return userService.find(pattern, page, size).stream()
                .map(UserDto::fromDomain)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody final UserDto user) {
        return UserDto.fromDomain(userService.save(User.fromDto(user)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody final UserDto user) {
        userService.save(User.fromDto(user));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable("id") final Long id) {
        userService.deleteById(id);
    }
}
