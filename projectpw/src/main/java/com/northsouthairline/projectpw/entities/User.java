package com.northsouthairline.projectpw.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String fullname;

    @Column (nullable = false, unique=true)
    private  String username;

    @Column (nullable = false)
    private String email;

    @Column (nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy="user")
    private Set<Booking> bookings;



}
