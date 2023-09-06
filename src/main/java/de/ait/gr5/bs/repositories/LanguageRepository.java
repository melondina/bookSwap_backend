package de.ait.gr5.bs.repositories;

import de.ait.gr5.bs.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
