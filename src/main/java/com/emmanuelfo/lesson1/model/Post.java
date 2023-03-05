package com.emmanuelfo.lesson1.model;

import com.emmanuelfo.lesson1.Dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content",nullable = false)
    private String content;



//    Convert Entity to Dto
    public PostDto mapToDto(){
        PostDto postDto = new PostDto();
        postDto.setId(this.getId());
        postDto.setTitle(this.getTitle());
        postDto.setDescription(this.getDescription());
        postDto.setContent(this.getContent());

        return postDto;
    }


}
