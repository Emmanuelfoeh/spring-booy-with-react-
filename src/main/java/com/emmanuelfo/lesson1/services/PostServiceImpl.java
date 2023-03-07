package com.emmanuelfo.lesson1.services;

import com.emmanuelfo.lesson1.Dto.PostDto;
import com.emmanuelfo.lesson1.Dto.PostRespond;
import com.emmanuelfo.lesson1.exception.ResourceNotFoundException;
import com.emmanuelfo.lesson1.model.Post;
import com.emmanuelfo.lesson1.repositroy.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


        return postResponse;
    }

    @Override
    public PostRespond getAllPosts(int pageNo,int pageSize ,String sortBy,String sortDir) {


//        Ternary for sorting in asc or desc order
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> pageablePost = posts.getContent();

        List<PostDto> content =  pageablePost.stream().map(post -> post.mapToDto()).collect(Collectors.toList());

        PostRespond postRespond = new PostRespond();
        postRespond.setContent(content);
        postRespond.setPageNo(posts.getNumber());
        postRespond.setPageSize(posts.getSize());
        postRespond.setTotalElements(posts.getNumberOfElements());
        postRespond.setTotalPages(posts.getTotalPages());
        postRespond.setLast(posts.isLast());

        return postRespond;
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
