package by.intexsoft.vihrova.restfulwebservice.post;

import by.intexsoft.vihrova.restfulwebservice.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
