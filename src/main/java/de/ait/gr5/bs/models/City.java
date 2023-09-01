package de.ait.gr5.bs.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "city")
public class City {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String postalCode;

  @Column(nullable = false)
  private String title;

  @OneToMany(mappedBy = "city")
  @Column(nullable = false)
  private List<User> users;
}
