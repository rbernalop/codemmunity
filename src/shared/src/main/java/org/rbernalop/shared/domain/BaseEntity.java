package org.rbernalop.shared.domain;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.rbernalop.shared.domain.valueobject.Identifier;
import org.springframework.data.domain.Persistable;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity implements Persistable<Identifier> {
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Transient
    protected boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew;
    }


    @PrePersist
    @PostLoad
    private void setPersisted() {
        isNew = false;
    }
}