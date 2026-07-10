package net.ent.etnc.seigneuriespring.models.repositories;


import net.ent.etnc.seigneuriespring.models.entity.Habitant;
import net.ent.etnc.seigneuriespring.models.entity.Seigneurie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeigneurieRepository extends JpaRepository<Seigneurie, Long> {

    @Query("""
                SELECT h
                FROM Seigneurie s
                JOIN s.habitants h
                WHERE s = :seigneurie
                ORDER BY h.dateNaissance ASC
                LIMIT :nbPersonne
            """)
    List<Habitant> recupererLesHabitantsLesPlusVieux(Seigneurie seigneurie, int nbPersonne);

    @Query("""
                SELECT s
                FROM Seigneurie s
                LEFT JOIN FETCH s.batiments b
                WHERE s.id = :l
            """)
    Optional<Seigneurie> findByIdFetchBatiment(long l);

    @Query("""
                SELECT s
                FROM Seigneurie s
                LEFT JOIN FETCH s.evenements e
                WHERE s.id = :l
            """)
    Optional<Seigneurie> findByIdFetchEvenement(long l);
}
