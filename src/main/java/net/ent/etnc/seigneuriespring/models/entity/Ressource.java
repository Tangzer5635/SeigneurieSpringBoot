package net.ent.etnc.seigneuriespring.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import net.ent.etnc.seigneuriespring.models.referencies.TypeRessource;

@Entity
@Table(name = "ressource", uniqueConstraints = @UniqueConstraint(name = "uk___ressource___nom", columnNames = {"nom"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = "nom")
@ToString(callSuper = true, of = {"nom", "type"})
public class Ressource extends AbstractEntity {
    //LBK
    @Getter
    @Setter(AccessLevel.PROTECTED)
    //BV
    @NotBlank(message = "Le nom ne doit pas être vide")
    @Size(max = 50, message = "le nom ne doit pas dépasser 50 caractères")
    //JPA
    @Column(name = "nom", unique = true, nullable = false, length = 50)
    private String nom;

    //LBK
    @Getter
    @Setter(AccessLevel.PROTECTED)
    //BV
    @NotNull(message = "Le type de ressource ne peut pas etre vide")
    //JPA
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type_ressource", nullable = false, length = 20)
    private TypeRessource type;

}
