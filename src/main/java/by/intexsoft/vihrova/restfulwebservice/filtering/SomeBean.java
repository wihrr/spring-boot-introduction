package by.intexsoft.vihrova.restfulwebservice.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private String field1;
    private String field2;

//    @JsonIgnore
    private String field3;
}
