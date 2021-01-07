package org.zhuch.dreamblog.domain.service;

import org.zhuch.dreamblog.persistence.repository.ICommentRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.zhuch.dreamblog.persistence.row.CommentRow;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.zhuch.dreamblog.domain.Comment;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Service
@Transactional
public class CommentService {
    private final ICommentRepository commentRepository;

    @Autowired
    public CommentService(final ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @NotNull
    public List<Comment> findByArticleId(@NotNull final Long articleId) {
        return commentRepository.findByArticleId(articleId)
            .stream()
            .map(Comment::fromRow)
            .collect(Collectors.toList());
    }

    @NotNull
    public Optional<Comment> findById(@NotNull final Long commentId) {
        return commentRepository
            .findById(commentId)
            .map(Comment::fromRow);
    }

    @NotNull
    public Comment save(@NotNull Comment comment) {
        comment = comment.withCreated(LocalDateTime.now());
        return Comment.fromRow(commentRepository.save(comment.toRow()));
    }

    public void delete(@NotNull final Comment comment) {
        commentRepository.delete(comment.toRow());
    }

    public void deleteById(@NotNull final Long id) {
        commentRepository.deleteById(id);
    }

    @NotNull
    public List<Comment> find(
        @Nullable final String pattern,
        @Nullable Integer page,
        @Nullable Integer size
    ) {
        page = page == null ? 0 : page;
        size = size == null ? 25 : size;
        final Page<CommentRow> comments =
            commentRepository.findAll(PageRequest.of(page, size));
        return comments.stream()
            .map(Comment::fromRow)
            .collect(Collectors.toList());
    }

    public void incrementLike(@NotNull final Long id) {
        commentRepository.likeIncrement(id);
    }

    public void incrementDislike(@NotNull final Long id) {
        commentRepository.dislikeIncrement(id);
    }
}
