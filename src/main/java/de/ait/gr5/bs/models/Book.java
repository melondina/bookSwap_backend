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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String title;
    private String author;
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String language;
    private Integer pages;
    private LocalDate publisherDate;
    private String cover;
    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;
    private LocalDate dateCreate;
    private String state;

}