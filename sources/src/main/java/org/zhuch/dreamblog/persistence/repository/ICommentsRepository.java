package org.zhuch.dreamblog.persistence.repository;


import org.springframework.data.repository.CrudRepository;
import org.zhuch.dreamblog.persistence.row.CommentRow;

public interface ICommentsRepository extends CrudRepository<CommentRow, Long> {
}
