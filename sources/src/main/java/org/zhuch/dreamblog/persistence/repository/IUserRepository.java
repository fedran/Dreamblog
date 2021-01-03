package org.zhuch.dreamblog.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.zhuch.dreamblog.persistence.row.UserRow;

public interface IUserRepository
    extends PagingAndSortingRepository<UserRow, Long> {
}
