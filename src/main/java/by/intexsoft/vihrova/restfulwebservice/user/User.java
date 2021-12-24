package by.intexsoft.vihrova.restfulwebservice.user;

import by.intexsoft.vihrova.restfulwebservice.post.Post;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "Name should have more than 1 characters")
    private String name;

    @Past
    private Date birthDay;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}
