package com.emmanuelfo.lesson1.model;

import com.emmanuelfo.lesson1.Dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String body;



//    convert Entity to Dto
    public CommentDto mapToDto(){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(this.getId());
        commentDto.setName(this.getName());
        commentDto.setEmail(this.getEmail());
        commentDto.setBody(this.getBody());
        return commentDto;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;


}
