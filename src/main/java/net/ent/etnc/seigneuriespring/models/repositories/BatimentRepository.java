package net.ent.etnc.seigneuriespring.models.repositories;

import net.ent.etnc.seigneuriespring.models.entity.Batiment;
import net.ent.etnc.seigneuriespring.models.referencies.TypeBat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BatimentRepository extends JpaRepository<Batiment, Long> {

    Optional<Batiment> findByNom(String nom);

    List<Batiment> findByNomStartsWith(String nom);

    List<Batiment> findByType(TypeBat type);

    @Query("SELECT b.id FROM Batiment b WHERE b.nom = :nom")
    Optional<Long> trouverBatimentparNom(String nom);

    boolean existsByNom(String nom);
}
