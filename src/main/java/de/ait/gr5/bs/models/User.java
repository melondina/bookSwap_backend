package de.ait.gr5.bs.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class User {

    public enum Role {
        ADMIN,
        USER
    }

    public enum State {

        CONFIRMED,
        BANNED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private State state;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String hashPassword;

    private String firstName;

    private String lastName;

    private String country;

    private String city;

    private String street;

    private String houseNumber;

    private String apartmentNumber;

    private Integer postalCode;

    private String userPhotoPath;


}
