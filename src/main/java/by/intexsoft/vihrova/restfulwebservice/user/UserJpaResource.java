package by.intexsoft.vihrova.restfulwebservice.user;

import by.intexsoft.vihrova.restfulwebservice.post.Post;
import by.intexsoft.vihrova.restfulwebservice.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> findById(@PathVariable int id) {
        Optional<User> user= userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id-" + id);
        }
        EntityModel<User> model = EntityModel.of(user.get());

        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findAll());

        model.add(linkToUsers.withRel("all-users"));


        return model;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> create(@Valid @RequestBody User user){
        User newUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/jpa/users/{id}")
    public User update(User user) {
      return null;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteById(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getAllWithPosts (@PathVariable int id){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("id-" + id);
        }
        return userOptional.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> savedUserOptional = userRepository.findById(id);
        if(savedUserOptional.isEmpty()){
            throw new UserNotFoundException("id-" + id);
        }
        User user = savedUserOptional.get();

        post.setUser(user);

        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
