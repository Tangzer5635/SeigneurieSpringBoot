package net.ent.etnc.seigneuriespring.models.repositories;

import net.ent.etnc.seigneuriespring.models.entity.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RessourceRepository extends JpaRepository<Ressource, Long> {
}
