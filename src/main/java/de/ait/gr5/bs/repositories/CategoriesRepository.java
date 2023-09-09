package de.ait.gr5.bs.repositories;

import de.ait.gr5.bs.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
  @Query("SELECT DISTINCT c FROM Book b JOIN b.category c order by c.categoryId")
  List<Category> findCategoryForFilter();

}
