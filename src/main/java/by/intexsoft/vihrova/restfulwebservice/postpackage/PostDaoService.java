package by.intexsoft.vihrova.restfulwebservice.postpackage;

import by.intexsoft.vihrova.restfulwebservice.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostDaoService {
    private static List<Post> posts= new ArrayList<>();

    private static int postCount = 3;

    private User user;

    static {
        posts.add(new Post(1, "My happy year", new Date(), 1));
        posts.add(new Post(2, "Lucky strike", new Date(), 2));
        posts.add(new Post(3, "That's your gift", new Date(), 2));
    }

    public List<Post> getAll(int userId){
        return posts.stream().filter(post -> post.getUserId().equals(userId)).collect(Collectors.toList());
    }

    public Post create(Post post, int userId){
        if(post.getId()==null){
            post.setId(++postCount);
        }
        posts.add(post);
        return post;
    }

    public Post getById(int id){
        for(Post post: posts){
            if(post.getId()==id){
                return post;
            }
        }
        return null;
    }
}
