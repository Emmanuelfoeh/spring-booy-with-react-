package com.emmanuelfo.lesson1.restControllers;

import com.emmanuelfo.lesson1.Dto.PostDto;
import com.emmanuelfo.lesson1.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostRestController {
    private final PostService postService;


    public PostRestController(PostService postService) {
        this.postService = postService;
    }
//create new post
    @PostMapping()
    public ResponseEntity<PostDto> createNewPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }
//    get all posts
    @GetMapping()
    public List<PostDto> getPosts(){
        return postService.getAllPosts();
    }

//    get Post by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPost(id));
    }

//    update post by id
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name = "id") long id, @RequestBody PostDto postDto){
        PostDto postToUpdate = postService.updatePost(postDto,id);
        return new ResponseEntity<>(postToUpdate,HttpStatus.OK);
    }

//    Delete post
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted Successfully",HttpStatus.OK);

    }
}
