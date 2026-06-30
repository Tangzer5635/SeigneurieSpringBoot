package net.ent.entc.seingeuriesrping.models.repositories;

import net.ent.entc.seingeuriesrping.models.entity.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RessourceRepository extends JpaRepository<Ressource, Long> {
}
