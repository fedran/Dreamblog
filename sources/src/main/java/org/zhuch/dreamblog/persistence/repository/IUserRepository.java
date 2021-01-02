package org.zhuch.dreamblog.persistence.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.zhuch.dreamblog.persistence.row.UserRow;

import static org.zhuch.dreamblog.persistence.row.UserRow.USER;

@RepositoryRestResource(collectionResourceRel = USER, path = USER)
public interface IUserRepository
        extends PagingAndSortingRepository<UserRow, Long> {
//    @Query("SELECT * FROM " + USER + " WHERE user_id = " + ":user_id")
//    Optional<UserRow> findById(@Param("user_id") Long id);
}
