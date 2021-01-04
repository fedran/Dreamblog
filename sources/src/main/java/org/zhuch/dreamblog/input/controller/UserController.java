package org.zhuch.dreamblog.input.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.zhuch.dreamblog.domain.assembler.UserAssembler;
import org.zhuch.dreamblog.domain.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.zhuch.dreamblog.input.json.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserAssembler userAssembler;

    @Autowired
    public UserController(
        final UserService userService,
        final UserAssembler userAssembler
    ) {
        this.userService = userService;
        this.userAssembler = userAssembler;
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
        @RequestParam(name = "size", required = false) final Integer size
    ) {
        return userService.find(pattern, page, size).stream()
            .map(UserDto::fromDomain)
            .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody final UserDto userDto) {
        return UserDto.fromDomain(userService.save(userAssembler.fromDto(userDto)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody final UserDto userDto) {
        userService.save(userAssembler.fromDto(userDto));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable("id") final Long id) {
        userService.deleteById(id);
    }
}
