package de.ait.gr5.bs.repositories;

import de.ait.gr5.bs.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {
  @Query("SELECT DISTINCT l FROM Book b JOIN b.language l order by l.languageId")
  List<Language> findLanguageForFilter();
}
