package net.ent.entc.seingeuriesrping.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity extends AbstractPersistable<Long> {

    @Column(name = "dateCreation", nullable = false)
    private LocalDateTime date_creation = LocalDateTime.now();
}
