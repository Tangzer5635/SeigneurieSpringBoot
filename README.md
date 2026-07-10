# SeigneurieSpringBoot

Un projet Spring Boot d'application de gestion de seigneuries en environnement féodal. Ce système permet de gérer une ou plusieurs seigneuries avec leurs habitants, bâtiments, ressources et événements.

## 📋 Vue d'ensemble

**SeigneurieSpring** est une application Back-End développée avec Spring Boot et PostgreSQL. Elle suit une architecture en couches avec des services CRUD génériques, des facades métier, et une initialisation de données via CSV.

### Contexte métier

L'application simule la gestion d'une seigneurie médiévale avec :
- **Seigneuries** : Les domaines principaux
- **Habitants** : La population (avec statut : Noble, Paysan, Commerçant, etc.)
- **Bâtiments** : Les infrastructures (type : Château, Ferme, Taverne, etc.)
- **Ressources** : Les biens économiques (type : Blé, Or, Bois, Fer, etc.)
- **Événements** : Les actions qui surviennent dans la seigneurie

## 🛠 Stack technique

| Composant | Version |
|-----------|---------|
| **Java** | 21 |
| **Spring Boot** | 4.1.0 |
| **PostgreSQL** | Latest |
| **Spring Data JPA** | ✓ |
| **Lombok** | Latest |
| **Maven** | 3.x |

### Dépendances principales

```xml
- spring-boot-starter-validation
- spring-boot-starter-data-jpa
- spring-boot-devtools
- lombok
- postgresql
- spring-boot-starter-validation-test
```

## 📁 Architecture du projet

```
src/
├── main/
│   ├── java/net/ent/etnc/seigneuriespring/
│   │   ├── models/
│   │   │   ├── entity/          # Entités JPA (@Entity, @Table)
│   │   │   │   ├── AbstractEntity.java
│   │   │   │   ├── Seigneurie.java
│   │   │   │   ├── Habitant.java
│   │   │   │   ├── Batiment.java
│   │   │   │   ├── Ressource.java
│   │   │   │   └── Evenement.java
│   │   │   ├── repositories/    # Spring Data JPA repositories
│   │   │   ├── services/        # Couche métier
│   │   │   │   ├── commun/      # Services CRUD génériques
│   │   │   │   └── crud/        # Services spécifiques par entité
│   │   │   ├── facade/          # Façades (niveau application)
│   │   │   │   ├── dto/         # Data Transfer Objects
│   │   │   │   └── impl/        # Implémentations des façades
│   │   │   └── referencies/     # Énumérations et constantes
│   │   ├── init/
│   │   │   └── jeuxDonnee.java  # Initialisation des données
│   │   └── SeigneurieSpringApplication.java
│   │
│   └── resources/
│       ├── application.yaml      # Configuration Spring
│       └── csv/                  # Données d'initialisation
│           ├── seigneurie.csv
│           ├── habitant.csv
│           ├── batiment.csv
│           ├── ressource.csv
│           ├── evenement.csv
│           └── relations
└── test/
    └── java/net/ent/etnc/seigneuriespring/
        └── SeigneurieSpringApplicationTests.java
```

## 🏗️ Architecture en couches

```
┌─────────────────────────────┐
│  Façade (Métier)            │
│ (InitFacade, Etc...)        │
└──────────────┬──────────────┘
               │
┌──────────────▼──────────────┐
│  Services CRUD              │
│ (CRUDService générique)     │
└──────────────┬──────────────┘
               │
┌──────────────▼──────────────┐
│  Repositories               │
│ (Spring Data JPA)           │
└──────────────┬──────────────┘
               │
┌──────────────▼──────────────┐
│  Entités & Base de données  │
│ (JPA/PostgreSQL)            │
└─────────────────────────────┘
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

4. **Accéder à l'application**

   Une fois démarrée, les données CSV seront automatiquement importées et la base peuplée avec les données initiales.

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

## 💾 Initialisation des données

L'application initialise automatiquement les données via CSV au démarrage. Les fichiers CSV sont situés dans `src/main/resources/csv/` :

- **seigneurie.csv** : Les seigneuries
- **habitant.csv** : Les habitants
- **batiment.csv** : Les bâtiments
- **ressource.csv** : Les ressources
- **evenement.csv** : Les événements
- **seigneurie_habitant.csv** : Relations seigneurie-habitants
- **seigneurie_ressource.csv** : Relations seigneurie-ressources
- **seigneurie_evenement.csv** : Relations seigneurie-événements

### Réinitialiser les données

Si vous avez besoin de remettre à zéro :
1. Supprimez la base de données
2. Redémarrez l'application

## 🔑 Points clés de l'architecture

### Services génériques

La classe **CRUDService<T, ID>** fournit des opérations standard :
- `create(T entity)` - Créer une entité
- `read(ID id)` - Lire une entité
- `update(T entity)` - Mettre à jour
- `delete(ID id)` - Supprimer
- `findAll()` - Récupérer toutes les entités

Chaque service spécifique (HabitantService, BatimentService, etc.) hérite de CRUDService et peut ajouter des méthodes métier spécifiques.

### Façades métier

Les façades (SeigneurieFacade, HabitantFacade, etc.) orchestrent les appels aux services pour encapsuler la logique métier complexe.

### DTO (Data Transfer Objects)

Les DTO (ex: SeigneurieDTO, BatimentDTO) sont utilisés pour transformer les entités JPA avant transmission au client, permettant une séparation entre la représentation interne et externe.

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
- **Services** : Interface + classe Impl, noms en `-Service`
- **Repositories** : Interfaces étendant JpaRepository
- **DTOs** : Suffix `-DTO` pour les objets transfert
- **Enums** : Package `referencies` pour énumérations métier

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
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Lombok Documentation](https://projectlombok.org/)

## 📌 Version actuelle

**Version** : 0.0.1-SNAPSHOT  
**Statut** : En développement  
**Date** : Juillet 2026
