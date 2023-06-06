package persistence;

import javax.persistence.*;

@Table(name = "user")
@Entity
public class Person {

    @Id
    private Long id;

    private String name;

    private Integer age;

}
