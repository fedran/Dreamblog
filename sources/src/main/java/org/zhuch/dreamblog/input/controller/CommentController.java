package org.zhuch.dreamblog.input.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.zhuch.dreamblog.domain.service.CommentService;
import org.springframework.web.bind.annotation.*;
import org.zhuch.dreamblog.input.json.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.zhuch.dreamblog.domain.Comment;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "article/{id}", produces =
        MediaType.APPLICATION_JSON_VALUE)
    public List<CommentDto> getComments(@PathVariable("id") final Long id) {
        return commentService.findByArticleId(id)
            .stream()
            .map(CommentDto::fromDomain)
            .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDto> getComment(@PathVariable("id") final Long id) {
        return commentService.findById(id)
            .map(comment -> ResponseEntity.ok().body(CommentDto.fromDomain(comment)))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommentDto> getComments(
        @RequestParam(name = "pattern", required = false) final String pattern,
        @RequestParam(name = "page", required = false) final Integer page,
        @RequestParam(name = "size", required = false) final Integer size
    ) {
        final List<Comment> comments = commentService.find(pattern, page, size);
        return comments.stream()
            .map(CommentDto::fromDomain)
            .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommentDto createComment(@RequestBody final CommentDto commentDto) {
        final Comment comment = Comment.fromDto(commentDto);
        final Comment saveResult = commentService.save(comment);
        return CommentDto.fromDomain(saveResult);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateComment(@RequestBody final CommentDto commentDto) {
        commentService.save(Comment.fromDto(commentDto));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteComment(@PathVariable("id") final Long id) {
        commentService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping(
        value = "/{id}/like/inc",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void incrementLike(@PathVariable("id") final Long id) {
        commentService.incrementLike(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping(
        value = "/{id}/dislike/inc",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void incrementDislike(@PathVariable("id") final Long id) {
        commentService.incrementDislike(id);
    }
}
