package by.intexsoft.vihrova.restfulwebservice.postpackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class PostResources {
    @Autowired
    private PostDaoService postDaoService;

    @GetMapping("/users/{userId}/posts")
    public List<Post> findAll(@PathVariable int userId){
        return postDaoService.getAll(userId);
    }

    @GetMapping("/users/{userId}/posts/{id}")
    public Post findById(@PathVariable int userId, @PathVariable int id) {
        Post post = postDaoService.getById(id);
        if(post==null){
            throw new PostNotFoundException("id-" + id);
        }
        return post;
    }

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<Object> create(@RequestBody Post post, @PathVariable int userId){
        Post newPost = postDaoService.create(post, userId);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
