package org.rbernalop.apichallenge.challenge.domain.port;

import org.rbernalop.apichallenge.challenge.domain.entity.Category;
import org.rbernalop.shared.domain.valueobject.CategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, CategoryId> {
}
