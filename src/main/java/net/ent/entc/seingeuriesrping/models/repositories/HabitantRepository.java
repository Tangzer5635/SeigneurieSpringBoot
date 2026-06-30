package net.ent.entc.seingeuriesrping.models.repositories;

import net.ent.entc.seingeuriesrping.models.entity.Habitant;
import net.ent.entc.seingeuriesrping.models.entity.Seigneurie;
import net.ent.entc.seingeuriesrping.models.referencies.StatutHabitant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitantRepository extends JpaRepository<Habitant, Long> {

    //Les habitants qui sont des seigneurs
    @Query("""
            SELECT DISTINCT h
                FROM Seigneurie s
                JOIN s.seigneur h
            """)
    List<Seigneurie> recupererLesSeigneurs();
}
