package net.ent.entc.seingeuriesrping.models.repositories;

import net.ent.entc.seingeuriesrping.models.entity.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
}
