package net.ent.etnc.seigneuriespring.models.repositories;

import net.ent.etnc.seigneuriespring.models.entity.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
}
