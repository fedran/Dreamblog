package org.zhuch.dreamblog.domain.service;

import org.zhuch.dreamblog.persistence.repository.IUserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.zhuch.dreamblog.persistence.row.UserRow;
import org.springframework.stereotype.Service;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import org.zhuch.dreamblog.domain.User;

import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Transactional
@Service
public class UserService {
    private final IUserRepository userRepository;

    @Autowired
    public UserService(final IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @NotNull
    public Optional<User> findById(@NotNull final Long id) {
        return userRepository
            .findById(id)
            .map(User::fromRow);
    }

    @NotNull
    public User save(@NotNull final User user) {
        user.withCreated(LocalDateTime.now());
        final UserRow userRow = user.toRow();
        final UserRow saveResultUserRow = userRepository.save(userRow);
        return User.fromRow(saveResultUserRow);
    }

    public void delete(@NotNull final User user) {
        userRepository.delete(user.toRow());
    }

    public void deleteById(@NotNull final Long id) {
        userRepository.deleteById(id);
    }

    @NotNull
    public List<User> find(
        @Nullable final String pattern,
        @Nullable Integer page,
        @Nullable Integer size
    ) {
        page = page == null ? 0 : page;
        size = size == null ? 25 : size;
        return userRepository.findAll(PageRequest.of(page, size)).stream()
            .map(User::fromRow)
            .collect(Collectors.toList());
    }
}
