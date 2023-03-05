package com.emmanuelfo.lesson1.repositroy;

import com.emmanuelfo.lesson1.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
