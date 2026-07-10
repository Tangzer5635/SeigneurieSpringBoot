package net.ent.etnc.seigneuriespring.models.repositories;

import net.ent.etnc.seigneuriespring.models.entity.Habitant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HabitantRepository extends JpaRepository<Habitant, Long> {

    //les Habitants qui sont des seigneurs
    @Query("""
            SELECT DISTINCT h
                FROM Seigneurie s
                LEFT JOIN s.seigneur h
            """)
    List<Habitant> recupererLesSeigneurs();

}
