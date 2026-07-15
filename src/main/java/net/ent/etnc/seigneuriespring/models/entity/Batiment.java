package net.ent.etnc.seigneuriespring.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import net.ent.etnc.seigneuriespring.models.entity.vobjects.Nom;
import net.ent.etnc.seigneuriespring.models.referencies.TypeBat;

@Entity
@Table(name = "batiment", uniqueConstraints = @UniqueConstraint(name = "uk___batiment___nom", columnNames = {"nom"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = "nom")
@ToString(callSuper = true, of = {"nom", "estActif", "type"})
public class Batiment extends AbstractEntity {

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @Embedded
    private Nom nom;
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "Le batiment doit avoir un choix actif ou non")
    //JPA
    @Column(name = "estActif", nullable = false)
    private Boolean estActif;
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "Le batiment doit avoir un type")
    //JPA
    @Column(name = "type", nullable = false, length = 10)
    @Enumerated(value = EnumType.STRING)
    private TypeBat type;


}
