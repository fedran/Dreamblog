package org.zhuch.dreamblog.domain.service;

import org.zhuch.dreamblog.persistence.repository.IUserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import org.zhuch.dreamblog.domain.User;
import org.zhuch.dreamblog.persistence.row.UserRow;

import java.util.stream.Collectors;
import java.util.Optional;
import java.util.List;

@Service
@Transactional
public class UserService {
    private final IUserRepository userRepository;

    public UserService(final IUserRepository userRepository){
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
        final UserRow row = user.toRow();
        final UserRow userRow = userRepository.save(row);
        return User.fromRow(userRow);
    }

    public void delete(@NotNull final User user) {
        userRepository.delete(user.toRow());
    }

    public void deleteById(@NotNull final Long id) {
        userRepository.deleteById(id);
    }

    @NotNull
    public List<User> find(@Nullable final String pattern,
                           @Nullable Integer page,
                           @Nullable Integer size) {
        page = page == null ? 0 : page;
        size = size == null ? 25 : size;
        return userRepository.findAll(PageRequest.of(page, size)).stream()
                .map(User::fromRow)
                .collect(Collectors.toList());
    }
}
