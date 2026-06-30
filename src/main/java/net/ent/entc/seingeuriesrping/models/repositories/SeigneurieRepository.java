package net.ent.entc.seingeuriesrping.models.repositories;

import net.ent.entc.seingeuriesrping.models.entity.Seigneurie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeigneurieRepository extends JpaRepository<Seigneurie, Long> {


}
