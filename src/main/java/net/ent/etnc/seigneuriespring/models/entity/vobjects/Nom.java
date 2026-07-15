package net.ent.etnc.seigneuriespring.models.entity.vobjects;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Setter;

public record Nom(
        @Setter(AccessLevel.PROTECTED) @Column(name = "nom", unique = true, nullable = false, length = 50) @NotBlank(message = "Le nom ne doit pas être vide") @Size(max = 50, message = "le nom ne doit pas dépasser 50 caractères") String nom) {
}
