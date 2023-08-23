package de.ait.gr5.bs.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "wait_line")
public class WaitLine {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long lineId;
  @ManyToOne
  @JoinColumn(name = "book_id")
  private Book book;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  private LocalDate dateCreate;

}
