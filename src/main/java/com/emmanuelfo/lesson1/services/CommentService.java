package com.emmanuelfo.lesson1.services;

import com.emmanuelfo.lesson1.Dto.CommentDto;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
}
