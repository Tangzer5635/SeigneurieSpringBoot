/*package net.ent.etnc.seigneuriespring.init;

public class JeuDeDonnees {

   public static Seigneurie creerSeigneurie() throws ValidException {

        // 1. Seigneur
        Habitant seigneur = EntitiesFactory.creerHabitant(
                "Stark",
                "Ned",
                LocalDate.of(1970, 1, 1),
                StatutHabitant.SEIGNEUR
        );

        // 2. Seigneurie
        Seigneurie seigneurie = EntitiesFactory.creerSeigneurie(
                "Winterfell",
                seigneur
        );

        // 3. Habitants
        Habitant h1 = EntitiesFactory.creerHabitant(
                "Snow",
                "Jon",
                LocalDate.of(1995, 5, 5),
                StatutHabitant.PAYSAN
        );

        Habitant h2 = EntitiesFactory.creerHabitant(
                "Bran",
                "Bran",
                LocalDate.of(2000, 3, 3),
                StatutHabitant.PAYSAN
        );

        seigneurie.addHabitant(h1);
        seigneurie.addHabitant(h2);

        // 4. Ressources
        Ressource bois = EntitiesFactory.creerRessource(
                "Bois",
                TypeRessource.MATIERE_PREMIERE
        );

        seigneurie.addRessource(bois, 100);

        // 5. Batiment
        Batiment maison = EntitiesFactory.creerBatiment(
                "Maison principale",
                true,
                TypeBat.HABITATION
        );

        seigneurie.addBatiment(maison);

        return seigneurie;
    }
}
*/