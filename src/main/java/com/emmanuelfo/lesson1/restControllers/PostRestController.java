package com.emmanuelfo.lesson1.restControllers;

import com.emmanuelfo.lesson1.Dto.PostDto;
import com.emmanuelfo.lesson1.Dto.PostRespond;
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
    public PostRespond getPosts(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo, @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize, @RequestParam(value = "sortBy" ,defaultValue = "id") String sortBy, @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir ){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
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
