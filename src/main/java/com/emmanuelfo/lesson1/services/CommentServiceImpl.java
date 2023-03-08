package com.emmanuelfo.lesson1.services;

import com.emmanuelfo.lesson1.Dto.CommentDto;
import com.emmanuelfo.lesson1.exception.BlogAPIException;
import com.emmanuelfo.lesson1.exception.ResourceNotFoundException;
import com.emmanuelfo.lesson1.model.Comment;
import com.emmanuelfo.lesson1.model.Post;
import com.emmanuelfo.lesson1.repositroy.CommentRepository;
import com.emmanuelfo.lesson1.repositroy.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

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

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {

        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map(comment -> comment.mapToDto()).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {

//        get Post
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
//        get comment
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }

        return comment.mapToDto();
    }
}