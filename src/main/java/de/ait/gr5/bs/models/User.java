package de.ait.gr5.bs.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    public enum Role {
        ADMIN,
        USER
    }

    public enum State {
        NOT_CONFIRMED,
        CONFIRMED,
        DELETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    private Integer postalCode;
    private boolean agreement;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private State state;
    @OneToMany(mappedBy = "owner")
    private List<Book> ownedBooks;
}
