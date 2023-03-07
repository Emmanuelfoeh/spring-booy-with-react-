package com.emmanuelfo.lesson1.Dto;

import com.emmanuelfo.lesson1.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private  long id;
    private String name;
    private String email;
    private String body;

    public Comment mapToEntity() {
        Comment comment = new Comment();
        comment.setId(this.getId());
        comment.setName(this.getName());
        comment.setEmail(this.getEmail());
        comment.setBody(this.getBody());
        return comment;
    }
}
