package by.intexsoft.vihrova.restfulwebservice.postpackage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Post {
    private Integer id;

    private String title;

    private Date publishingDate;

    private Integer userId;
}
