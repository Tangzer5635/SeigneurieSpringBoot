package net.ent.etnc.seigneuriespring.models.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "seigneurie", uniqueConstraints = @UniqueConstraint(name = "uk___seigneurie___nom", columnNames = {"nom"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = "nom")
@ToString(callSuper = true, of = {"nom", "seigneur", "ressources", "habitants", "batiments"})
public class Seigneurie extends AbstractEntity {


    //<Entite,Non-Entite>
    @ElementCollection
    @CollectionTable(name = "seigneurie_ressource",
            joinColumns = @JoinColumn(name = "seigneurie_id", foreignKey = @ForeignKey(name = "fk___seigneurie_ressource___seigneurie_id")))
    @MapKeyJoinColumn(name = "ressource_id", foreignKey = @ForeignKey(name = "fk___seigneurie_ressource___ressource_id"))
    @Column(name = "quantite", nullable = false)
    private final Map<Ressource, Integer> ressources = new HashMap<>();


    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotBlank(message = "Le nom de la seigneurie ne peut pas être vide")
    @Size(max = 50, message = "le nom ne doit pas dépasser 50 caractères")
    //JPA
    @Column(name = "nom", unique = true, nullable = false, length = 50)
    private String nom;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotNull(message = "La seigneurie doit avoir un seigneur")
    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "seigneurie_habitant"
            , joinColumns = @JoinColumn(name = "seigneurie_id", foreignKey = @ForeignKey(name = "fk___seigneurie_habitant___seigneurie_id"))
            , inverseJoinColumns = @JoinColumn(name = "habitant_id", foreignKey = @ForeignKey(name = "fk___seigneurie_habitant___habitant_id"))
    )
    private Habitant seigneur;

    @Valid
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "seigneurie_evenement"
            , joinColumns = @JoinColumn(name = "seigneurie_id", foreignKey = @ForeignKey(name = "fk___seigneurie_evenement___seigneurie_id"))
            , inverseJoinColumns = @JoinColumn(name = "evenement_id", foreignKey = @ForeignKey(name = "fk___seigneurie_evenement___evenement_id")))
    private List<Evenement> evenements = new ArrayList<>();

    @Valid
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "seigneurie_id", foreignKey = @ForeignKey(name = "fk___habitant___seigneurie_id"))
    private Set<Habitant> habitants = new HashSet<>();

    @Valid
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "seigneurie_id", foreignKey = @ForeignKey(name = "fk___batiment___seigneurie_id"))
    private List<Batiment> batiments = new ArrayList<>();

    public Map<Ressource, Integer> getRessources() {
        return Collections.unmodifiableMap(ressources);
    }

    public void addRessource(@NotNull Ressource ressource, @NotNull Integer quantite) {
        this.ressources.put(ressource, quantite);
    }

    public void removeRessource(@NotNull Ressource ressource, @NotNull Integer quantite) {
        this.ressources.remove(ressource, quantite);
    }

    public @Valid List<Evenement> getEvenements() {
        return Collections.unmodifiableList(evenements);
    }

    public void addEvenement(@NotNull Evenement evenement) {
        this.evenements.add(evenement);
    }

    public @Valid Set<Habitant> getHabitants() {
        return Collections.unmodifiableSet(habitants);
    }

    public void addHabitant(@NotNull Habitant habitant) {
        this.habitants.add(habitant);
    }

    public void removeHabitant(@NotNull Habitant habitant) {
        this.habitants.remove(habitant);
    }

    public List<Batiment> getBatiments() {
        return Collections.unmodifiableList(batiments);
    }

    public void addBatiment(@NotNull Batiment batiment) {
        this.batiments.add(batiment);
    }

}