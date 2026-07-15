package net.ent.etnc.seigneuriespring.models.entity.vobjects;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Setter;

public record Prenom(
        @Setter(AccessLevel.PROTECTED) @Column(name = "prenom", unique = true, nullable = false, length = 50) @NotBlank(message = "Le prenom ne doit pas être vide") @Size(max = 50, message = "le prenom ne doit pas dépasser 50 caractères") String prenom) {
}
