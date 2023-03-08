package com.emmanuelfo.lesson1.restControllers;

import com.emmanuelfo.lesson1.Dto.CommentDto;
import com.emmanuelfo.lesson1.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class CommentRestController {
    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId, @RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public List<CommentDto> getAllComments(@PathVariable(value = "postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId){
        return ResponseEntity.ok(commentService.getCommentById(postId,commentId));
    }


    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId, @RequestBody CommentDto commentDto){
         CommentDto commentToUpdate = commentService.updateComment(postId,commentId,commentDto);
         return new ResponseEntity<>(commentToUpdate,HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment( @PathVariable(value = "postId") Long postId, @PathVariable Long commentId){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Comment has been deleted successfully",HttpStatus.OK);
    }
}
