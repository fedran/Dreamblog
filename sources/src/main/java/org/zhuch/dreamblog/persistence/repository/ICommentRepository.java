package org.zhuch.dreamblog.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.zhuch.dreamblog.persistence.row.CommentRow;

import java.util.List;

import static org.zhuch.dreamblog.persistence.row.CommentRow.COMMENT;

public interface ICommentRepository
    extends PagingAndSortingRepository<CommentRow, Long> {

    List<CommentRow> findByArticleId(Long articleId);

    @Query(
        "UPDATE " + COMMENT +
            " SET likes = " +
                "( SELECT likes FROM " + COMMENT +
                " WHERE comment_id = :commentId ) + 1 " +
            "WHERE comment_id = :commentId "
    )
    @Modifying
    void likeIncrement(Long commentId);

    @Query(
        "UPDATE " + COMMENT +
            " SET dislikes = " +
                "( SELECT dislikes FROM " + COMMENT +
                " WHERE comment_id = :commentId ) + 1 " +
            "WHERE comment_id = :commentId "
    )
    @Modifying
    void dislikeIncrement(Long commentId);
}
