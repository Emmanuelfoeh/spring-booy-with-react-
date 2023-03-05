package com.emmanuelfo.lesson1.Dto;

import com.emmanuelfo.lesson1.model.Post;
import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;



//    convert Dto to Entity
    public Post mapToEntity(){
        Post post = new Post();
//        post.setId(this.getId());
        post.setTitle(this.getTitle());
        post.setDescription(this.getDescription());
        post.setContent(this.getContent());
        return post;
    }

}
