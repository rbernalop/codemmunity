package org.rbernalop.apiexecution.language.domain.port;

import org.rbernalop.apiexecution.language.domain.aggregate.Language;
import org.rbernalop.shared.domain.valueobject.LanguageId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, LanguageId> {
}
