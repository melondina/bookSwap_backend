package de.ait.gr5.bs.repositories;

import de.ait.gr5.bs.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Long> {}
