package com.emmanuelfo.lesson1.repositroy;

import com.emmanuelfo.lesson1.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
