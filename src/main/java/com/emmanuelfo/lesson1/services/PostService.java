package com.emmanuelfo.lesson1.services;

import com.emmanuelfo.lesson1.Dto.PostDto;
import com.emmanuelfo.lesson1.Dto.PostRespond;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
//     create new post
     PostDto createPost(PostDto postDto);

//     get all post
PostRespond getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);

//     get post by id
     PostDto getPost(Long id);

//     update post
     PostDto updatePost(PostDto postDto,Long id);

//     Delete post
     void deletePost(Long id);
}
