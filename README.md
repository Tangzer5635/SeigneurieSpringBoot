# SeigneurieSpringBoot

Un projet Spring Boot d'application de gestion de seigneuries en environnement féodal. Ce système permet de gérer une ou plusieurs seigneuries avec leurs habitants, bâtiments, ressources et événements.

## 📋 Vue d'ensemble

**SeigneurieSpring** est une application Back-End développée avec Spring Boot et PostgreSQL. Elle suit une architecture en couches avec des services CRUD génériques, des façades métier, une initialisation de données via **Spring Batch**, et une interface en ligne de commande via **Spring Shell**.

### Contexte métier

L'application simule la gestion d'une seigneurie médiévale avec :
- **Seigneuries** : Les domaines principaux, chacun rattaché à un habitant "seigneur"
- **Habitants** : La population (statut : Seigneur, Clerc, Bourgeois, Paysan, etc.)
- **Bâtiments** : Les infrastructures (type : Château, Ferme, Maison, Caserne, etc.)
- **Ressources** : Les biens économiques (type : Bois, Pierre, Or, Nourriture, etc.)
- **Événements** : Les actions qui surviennent dans la seigneurie (fêtes, guerres, famines...)

## 🛠 Stack technique

| Composant | Version |
|-----------|---------|
| **Java** | 21 |
| **Spring Boot** | 4.0.7 |
| **Spring Batch** | ✓ (starter Spring Boot 4) |
| **Spring Shell** | 3.4.1 |
| **PostgreSQL** | Latest |
| **Spring Data JPA** | ✓ |
| **Lombok** | Latest |
| **Maven** | 3.x |

### Dépendances principales

```xml
- spring-boot-starter-validation
- spring-boot-starter-data-jpa
- spring-boot-devtools
- spring-shell-starter
- lombok
- postgresql
```

## 📁 Architecture du projet

```
src/
├── main/
│   ├── java/net/ent/etnc/seigneuriespring/
│   │   ├── batchs/                      # Import des données via Spring Batch
│   │   │   ├── InitialisationBatch.java # Job maître (enchaîne tous les steps)
│   │   │   ├── habitant/                # Batch, LineCSV, Processor, ReaderConfig, FieldSetMapper
│   │   │   ├── seigneurie/
│   │   │   ├── batiment/
│   │   │   ├── evenement/
│   │   │   └── ressource/
│   │   ├── shell/                       # Commandes Spring Shell (CLI interactive)
│   │   │   ├── ChargementCommands.java  # commande `init`
│   │   │   ├── HabitantCommands.java    # commande `h-list`
│   │   │   ├── SeigneurieCommands.java  # commandes `s-list`, `s-renew`
│   │   │   └── exceptions/
│   │   ├── models/
│   │   │   ├── entity/                  # Entités JPA (@Entity, @Table)
│   │   │   │   ├── AbstractEntity.java
│   │   │   │   ├── Seigneurie.java
│   │   │   │   ├── Habitant.java
│   │   │   │   ├── Batiment.java
│   │   │   │   ├── Ressource.java
│   │   │   │   ├── Evenement.java
│   │   │   │   ├── EntitiesFactory.java # Factory de création avec validation
│   │   │   │   ├── vobjects/            # Value Objects (Nom, Prenom)
│   │   │   │   └── communs/             # ValidUtils, exceptions de validation
│   │   │   ├── repositories/            # Spring Data JPA repositories
│   │   │   ├── services/                # Couche métier
│   │   │   │   ├── commun/              # CRUDService / CRUDServiceImpl génériques
│   │   │   │   └── crud/                # Services spécifiques par entité
│   │   │   ├── facade/                  # Façades (niveau application)
│   │   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   │   ├── exception/
│   │   │   │   └── impl/                # Implémentations des façades
│   │   │   └── referencies/             # Énumérations et constantes métier
│   │   └── SeigneurieSpringApplication.java
│   │
│   └── resources/
│       ├── application.yaml             # Configuration Spring
│       └── csv/                         # Données d'initialisation (lues par les Readers Batch)
│           ├── seigneurie.csv
│           ├── habitant.csv
│           ├── batiment.csv
│           ├── ressource.csv
│           ├── evenement.csv
│           ├── seigneurie_habitant.csv
│           ├── seigneurie_ressource.csv
│           └── seigneurie_evenement.csv
└── test/
    └── java/net/ent/etnc/seigneuriespring/
        └── SeigneurieSpringApplicationTests.java
```

## 🏗️ Architecture en couches

```
┌──────────────────────────────┐
│  Shell (CLI interactive)      │
│  (ChargementCommands, etc.)   │
└──────────────┬────────────────┘
               │
┌──────────────▼────────────────┐
│  Façade (Métier)               │
│  (SeigneurieFacade, etc.)      │
└──────────────┬────────────────┘
               │
┌──────────────▼────────────────┐
│  Services CRUD                 │
│  (CRUDService générique)       │
└──────────────┬────────────────┘
               │
┌──────────────▼────────────────┐
│  Repositories                  │
│  (Spring Data JPA)             │
└──────────────┬────────────────┘
               │
┌──────────────▼────────────────┐
│  Entités & Base de données     │
│  (JPA/PostgreSQL)              │
└────────────────────────────────┘

┌────────────────────────────────┐
│  Spring Batch (import CSV)      │
│  Reader → Processor → Writer    │
│  → alimente directement JPA     │
└────────────────────────────────┘
```

## 🚀 Installation et démarrage

### Prérequis

1. **Java 21+** installé
   ```bash
   java -version
   ```

2. **PostgreSQL** en cours d'exécution
   ```bash
   # Sur Ubuntu/Debian
   sudo systemctl start postgresql
   ```

3. **Maven 3.x+** (optionnel, mvnw inclus)

### Étapes d'installation

1. **Cloner le repository**
   ```bash
   git clone https://github.com/Tangzer5635/SeigneurieSpringBootVersionFinaleMVP.git
   cd SeigneurieSpringBootVersionFinaleMVP
   ```

2. **Configurer la base de données**

   Mettre à jour `src/main/resources/application.yaml` :
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/db_seigneurie
       username: postgres
       password: votre_mot_de_passe
   ```

   Créer la base de données PostgreSQL :
   ```bash
   createdb db_seigneurie
   ```

3. **Compiler et démarrer**

   Avec Maven Wrapper (Linux/Mac) :
   ```bash
   ./mvnw spring-boot:run
   ```

   Ou Windows :
   ```bash
   mvnw.cmd spring-boot:run
   ```

   Ou avec Maven standard :
   ```bash
   mvn spring-boot:run
   ```

4. **Utiliser le shell interactif**

   Au démarrage, l'application ouvre un **shell Spring Shell**. La base démarre vide : c'est la commande `init` qui déclenche l'import des données CSV via Spring Batch.

## 💾 Initialisation des données (Spring Batch)

Contrairement aux versions précédentes du projet, **les données ne sont plus importées automatiquement au démarrage**. L'import est piloté par un job Spring Batch, déclenché manuellement depuis le shell :

```
shell:> init
```

Ce job (`initialisationJob`, défini dans `InitialisationBatch`) enchaîne les steps dans un ordre précis :

```
importHabitantStep → importSeigneurieStep → importBatimentStep → importEvenementStep → importRessourceStep
```

⚠️ **L'ordre est important** : `importSeigneurieStep` a besoin que les habitants soient déjà en base, car chaque seigneurie référence un habitant "seigneur" existant (lookup par `HabitantService.findById`).

Chaque entité dispose de son propre module d'import dans `batchs/<entite>/`, structuré de façon identique :

| Fichier | Rôle |
|---|---|
| `<Entite>LineCSV.java` | Record représentant une ligne CSV brute |
| `FieldSetMapper<Entite>.java` | Parse chaque ligne du CSV vers le `LineCSV` |
| `<Entite>Processor.java` | Transforme le `LineCSV` en entité JPA via `EntitiesFactory` |
| `<Entite>ReaderConfig.java` | Configure le `FlatFileItemReader` (fichier, délimiteur, colonnes) |
| `<Entite>Batch.java` | Déclare le `Step` et le `Job` (writer JPA, chunk size 10) |

### Configuration requise

L'auto-lancement de job au démarrage de Spring Batch est désactivé, puisque le lancement est piloté manuellement via le shell :

```yaml
spring:
  batch:
    job:
      enabled: false
```

### Réinitialiser les données

Si vous avez besoin de remettre à zéro :
1. Supprimez la base de données (ou relancez avec `ddl-auto: create`)
2. Redémarrez l'application
3. Relancez `init` dans le shell

## 💻 Commandes Shell disponibles

| Commande | Description |
|---|---|
| `init` | Lance l'import complet des données CSV (job Spring Batch) |
| `h-list` | Affiche tous les habitants |
| `s-list` | Affiche toutes les seigneuries |
| `s-renew -i <idSeigneurie>` | Déclenche le renouvellement de population d'une seigneurie |

## 📦 Construction et packaging

### Créer un JAR exécutable

```bash
./mvnw clean package
```

Le JAR généré se trouvera dans `target/seigneurieSpring-0.0.1-SNAPSHOT.jar`

### Exécuter le JAR

```bash
java -jar target/seigneurieSpring-0.0.1-SNAPSHOT.jar
```

## 🔑 Points clés de l'architecture

### Services génériques

La classe abstraite **`CRUDServiceImpl<T, R>`** fournit des opérations standard, implémentées via `CRUDService<T>` :
- `save(T entity)` — Créer / mettre à jour une entité
- `findById(Long id)` — Lire une entité
- `deleteById(Long id)` — Supprimer
- `existsById(Long id)` — Vérifier l'existence
- `isValid(T entity)` — Valider via Bean Validation
- `getAll()` — Récupérer toutes les entités
- `count()` — Compter les entités

Chaque service spécifique (`HabitantService`, `BatimentService`, etc.) hérite de `CRUDServiceImpl` et peut ajouter des méthodes métier spécifiques (ex. `existsByNom`, `findByIdFetchBatiment`).

### Value Objects

`Nom` et `Prenom` sont des Value Objects `@Embeddable`, validés à la construction, utilisés dans `Habitant`, `Batiment` et `Evenement`.

### Factory centralisée

`EntitiesFactory` centralise la création des entités avec validation systématique (`ValidUtils.validate(...)`), utilisée à la fois par les `Processor` Spring Batch et par les façades métier.

### Façades métier

Les façades (`SeigneurieFacade`, `HabitantFacade`, etc.) orchestrent les appels aux services pour encapsuler la logique métier complexe (ex. renouvellement de population, échange de ressources entre seigneuries, migration d'habitants).

### DTO (Data Transfer Objects)

Les DTO (ex. `SeigneurieDTO`, `BatimentDTO`) découplent la représentation interne (entités JPA) des données manipulées par la couche façade/présentation.

## ⚙️ Configuration

### application.yaml

```yaml
spring:
  application:
    name: seigneurieSpring
  datasource:
    url: jdbc:postgresql://localhost:5432/db_seigneurie
    username: postgres
    password: azerty+35
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: create          # create | update | validate | none
    show-sql: true              # Affiche les requêtes SQL
  batch:
    job:
      enabled: false            # Empêche le lancement auto d'un job au démarrage
```

### Configuration adaptée à votre environnement

#### Windows avec proxy corporate

Si vous êtes derrière un proxy (comme à BackBase) :

```bash
./mvnw -D"http.proxyHost=172.16.0.1" -D"http.proxyPort=8080" spring-boot:run
```

Ou ajouter au `settings.xml` Maven.

## 🧪 Tests

Exécuter les tests unitaires :

```bash
./mvnw test
```

Voir la couverture de tests :

```bash
./mvnw test jacoco:report
```

## 📚 Conventions de code

- **Package naming** : `net.ent.etnc.seigneuriespring.*`
- **Entities** : Classes annotées `@Entity`, noms au singulier
- **Services** : Interface + classe `Impl`, noms en `-Service`
- **Repositories** : Interfaces étendant `JpaRepository`
- **DTOs** : Suffix `-DTO` pour les objets de transfert
- **Batch** : Un sous-package par entité dans `batchs/`, avec 5 classes nommées de façon identique (`LineCSV`, `FieldSetMapper`, `Processor`, `ReaderConfig`, `Batch`)
- **Enums** : Package `referencies` pour les énumérations métier

## 🔍 Troubleshooting

### PostgreSQL ne se connecte pas

**Erreur** : `Connection refused`

**Solution** :
```bash
# Vérifier que PostgreSQL est en cours d'exécution
sudo systemctl status postgresql

# Démarrer PostgreSQL
sudo systemctl start postgresql

# Vérifier les credentials dans application.yaml
```

### Port 5432 déjà utilisé

**Solution** :
```bash
# Modifier le port dans application.yaml
url: jdbc:postgresql://localhost:5433/db_seigneurie
```

### `Job name must be specified in case of multiple jobs`

**Cause** : plusieurs `@Bean Job` sont définis (un par entité + le job maître), et Spring Boot essaie d'en lancer un automatiquement au démarrage.

**Solution** : désactiver l'auto-lancement dans `application.yaml` (voir section Configuration) et piloter les jobs manuellement via la commande shell `init`.

### Build échoue avec des erreurs Lombok

**Solution** :
```bash
# Nettoyer le cache Maven
./mvnw clean

# Récompiler avec annotation processors
./mvnw compile
```

## 📝 Commits conventionnels

Pour contribuer, utilisez les types suivants :

```
feat:     Nouvelle fonctionnalité
fix:      Correction de bug
docs:     Documentation
refactor: Restructuration sans changer le comportement
test:     Ajout ou modification de tests
chore:    Tâches de maintenance
```

## 📄 Licence

À définir dans pom.xml

## 👨‍💻 Auteur

**Tangzer**

---

## 🔗 Ressources utiles

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Batch Documentation](https://spring.io/projects/spring-batch)
- [Spring Shell Documentation](https://spring.io/projects/spring-shell)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Lombok Documentation](https://projectlombok.org/)

## 📌 Version actuelle

**Version** : 0.0.1-SNAPSHOT
**Statut** : En développement
**Date** : Juillet 2026
