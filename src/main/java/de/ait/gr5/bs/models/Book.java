package de.ait.gr5.bs.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {

    public enum State{
        AVAILABLE,
        DELETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = false)
    private String publisherDate;

    @Column(nullable = false)
    private String cover;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(nullable = false)
    private LocalDate dateCreate;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private State state;

}
