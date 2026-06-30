package net.ent.entc.seingeuriesrping.models.repositories;

import net.ent.entc.seingeuriesrping.models.entity.Batiment;
import net.ent.entc.seingeuriesrping.models.referencies.TypeBat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BatimentRepository extends JpaRepository<Batiment,Long> {

    Optional<Batiment> findByNomBatiment(String nomBatiment);

    List<Batiment> findByNomBatimentStartingWith(String nomBatiment);

    List<Batiment> findByTypeBatiment(TypeBat typeBatiment);

    @Query("SELECT b FROM Batiment b WHERE b.typeBatiment = :type AND b.estActif = true")
    List<Batiment> trouverActifsParType(@Param("type") TypeBat type);
}
