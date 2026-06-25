package net.ent.entc.seingeuriesrping.models.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "seigneurie", uniqueConstraints = @UniqueConstraint(name = "uk___seigneurie___nom", columnNames = {"nom"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = "nom")
@ToString(callSuper = true, of = {"nom", "seigneur"})

public class Seigneurie extends AbstractEntity implements Serializable {

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotBlank(message = "Le nom de la seigneurie ne peut pas être vide")
    @Size(max = 50, message = "Le nom ne doit pas dépasser 50 caractères")
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @NotNull(message = "La seigneurie doit avoir un seigneur")
    @Valid
    @OneToOne(optional = false)
    @JoinColumn(name = "seigneur_id", nullable = false, foreignKey = @ForeignKey(name = "fk___seigneurie___seigneur"))
    private Habitant seigneur;

    @Valid
    @OneToMany
    private Set<Habitant> habitants = new HashSet<>();

    public Set<Habitant> getHabitants() {
        return Collections.unmodifiableSet(habitants);
    }

    public void addHabitant(@NotNull Habitant habitant) {
        habitants.add(habitant);
    }

    public void removeHabitant(@NotNull Habitant habitant) {
        habitants.remove(habitant);
    }

    @OneToMany()
    private List<Batiment> batiments = new ArrayList<>();

    public List<Batiment> getBatiments() {
        return Collections.unmodifiableList(batiments);
    }

    public void addBatiment(@NotNull Batiment batiment) {
        batiments.add(batiment);
    }

    public void removeBatiment(@NotNull Batiment batiment) {
        batiments.remove(batiment);
    }

    @OneToMany
    private Set<Evenement> evenements = new HashSet<>();

    public Set<Evenement> getEvenements() {
        return Collections.unmodifiableSet(evenements);
    }

    public void addEvenement(@NotNull Evenement evenement) {
        evenements.add(evenement);
    }

    public void removeEvenement(@NotNull Evenement evenement) {
        evenements.remove(evenement);
    }

    @Getter
    @ElementCollection
    @CollectionTable(name = "seigneurie_ressource", joinColumns = @JoinColumn(name = "seigneurie_id"), foreignKey = @ForeignKey(name = "fk___seigneurie_ressource___seigneurie"))
    @MapKeyJoinColumn(name = "idRessource", foreignKey = @ForeignKey(name = "fk___seigneurie_ressource___ressource"))
    @Column(name = "quantite", nullable = false)
    private Map<Ressource, Integer> ressources = new HashMap<>();

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

    protected Seigneurie(String nom, Habitant seigneur) {
        this.nom = nom;
        this.seigneur = seigneur;
    }

    @Override
    public String displayable() {
        return "Seigneurie : " + nom;
    }
}