package net.ent.entc.seingeuriesrping.models.entity;

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
@ToString(callSuper = true, of = {"nom", "seigneur"})
public class Seigneurie extends AbstractEntity {

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotBlank(message = "Le nom de la seigneurie ne peut pas être vide")
    @Size(max = 50, message = "Le nom ne doit pas dépasser 50 caractères")
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Getter
    @Setter
    @NotNull(message = "La seigneurie doit avoir un seigneur")
    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "seigneurie_habitant"
        ,joinColumns = @JoinColumn(name = "idSeigneurie", foreignKey = @ForeignKey(name = "fk___seigneurie_habitant___id_seigneurie"))
        , inverseJoinColumns = @JoinColumn(name = "idHabitant", foreignKey = @ForeignKey(name = "fk___seigneurie_habitant___id_habitant"))
    )
    private Habitant seigneur;

    @Valid
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSeigneurie", foreignKey = @ForeignKey(name = "fk___seigneurie___habitant"))
    private List<Habitant> habitants = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSeigneurie", foreignKey = @ForeignKey(name = "fk____seigneurie___batiments"))
    private List<Batiment> batiments = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "seigneurie_evenement",
            joinColumns = @JoinColumn(name = "idSeigneurie", foreignKey = @ForeignKey(name = "fk___seigneurie_evenement___seigneurie")),
            inverseJoinColumns = @JoinColumn(name = "idEvenement", foreignKey = @ForeignKey(name = "fk___seigneurie_evenement___evenement")))
    private Set<Evenement> evenements = new HashSet<>();

    @Getter
    @ElementCollection
    @CollectionTable(name = "seigneurie_ressource", joinColumns = @JoinColumn(name = "seigneurie_id"), foreignKey = @ForeignKey(name = "fk___seigneurie_ressource___seigneurie_id"))
    @MapKeyJoinColumn(name = "idRessource", foreignKey = @ForeignKey(name = "fk___seigneurie_ressource___ressource"))
    @Column(name = "quantite", nullable = false)
    private Map<Ressource, Integer> ressources = new HashMap<>();

    public List<Habitant> getHabitants() {
        return Collections.unmodifiableList(habitants);
    }

    public void addHabitant(@NotNull Habitant habitant) {
        habitants.add(habitant);
    }

    public void removeHabitant(@NotNull Habitant habitant) {
        habitants.remove(habitant);
    }

    public List<Batiment> getBatiments() {
        return Collections.unmodifiableList(batiments);
    }

    public void addBatiment(@NotNull Batiment batiment) {
        batiments.add(batiment);
    }

    public void removeBatiment(@NotNull Batiment batiment) {
        batiments.remove(batiment);
    }

    public Set<Evenement> getEvenements() {
        return Collections.unmodifiableSet(evenements);
    }

    public void addEvenement(@NotNull Evenement evenement) {
        evenements.add(evenement);
    }

    public void removeEvenement(@NotNull Evenement evenement) {
        evenements.remove(evenement);
    }

    public void addRessource(@NotNull Ressource ressource, @NotNull Integer quantite) {
        ressources.merge(ressource, quantite, Integer::sum);
    }

    public void removeRessource(@NotNull Ressource ressource, @NotNull Integer quantite) {
        Integer actuelle = ressources.get(ressource);
        int nouvelleQuantite = actuelle - quantite;

        if (nouvelleQuantite <= 0) {
            ressources.remove(ressource);
        } else {
            ressources.put(ressource, nouvelleQuantite);
        }
    }

}