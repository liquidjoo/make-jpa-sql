package persistence.study;

import javax.persistence.*;

@Entity
@Table(name = "wheel_info")
public class Wheel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer size;

    private Integer price;

    @Transient
    private String data;
}
