package models;

import org.javers.core.metamodel.annotation.TypeName;

import javax.persistence.*;

@TypeName("Car")
@Entity
@Table(schema = "public",  name="cars")
public class Car {

    @org.javers.core.metamodel.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "color")
    private String color;

    @Column(name = "power")
    private Integer power;

    @Column(name = "brand")
    private String brand;

    @Column(name = "mileage")
    private Integer mileage;


}

