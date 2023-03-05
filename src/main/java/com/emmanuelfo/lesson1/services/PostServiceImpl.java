package com.emmanuelfo.lesson1.services;

import com.emmanuelfo.lesson1.Dto.PostDto;
import com.emmanuelfo.lesson1.exception.ResourceNotFoundException;
import com.emmanuelfo.lesson1.model.Post;
import com.emmanuelfo.lesson1.repositroy.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
//        convert Dto to Entity
        Post post = postDto.mapToEntity();


//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);


//        convert Entity back to Dto
        PostDto postResponse = newPost.mapToDto();

//        postResponse.setId(newPost.getId());
//        postResponse.setTitle(newPost.getTitle());
//        postResponse.setDescription(newPost.getDescription());
//        postResponse.setContent(newPost.getContent());
        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> post.mapToDto()).collect(Collectors.toList());
    }

    @Override
    public PostDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        return post.mapToDto();
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return updatedPost.mapToDto();
    }

    @Override
    public void deletePost(Long id) {
//        Optional<Post> post = postRepository.findById(id);
//        if(post.isPresent()){
//            postRepository.deleteById(id);
//        }
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
    }

}
