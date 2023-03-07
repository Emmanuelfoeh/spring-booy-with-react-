package com.emmanuelfo.lesson1.services;

import com.emmanuelfo.lesson1.Dto.CommentDto;
import com.emmanuelfo.lesson1.exception.ResourceNotFoundException;
import com.emmanuelfo.lesson1.model.Comment;
import com.emmanuelfo.lesson1.model.Post;
import com.emmanuelfo.lesson1.repositroy.CommentRepository;
import com.emmanuelfo.lesson1.repositroy.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = commentDto.mapToEntity();
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return newComment.mapToDto();
    }
}