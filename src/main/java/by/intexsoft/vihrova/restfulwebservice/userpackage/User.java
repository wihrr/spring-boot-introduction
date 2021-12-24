package by.intexsoft.vihrova.restfulwebservice.userpackage;

import lombok.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
    private Integer id;

    @Size(min = 2, message = "Name should have more than 1 characters")
    private String name;

    @Past
    private Date birthDay;
}
