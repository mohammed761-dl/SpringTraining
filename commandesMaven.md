# Commandes Maven du projet SpringTraining (Linux)

Ouvre un terminal à la racine du projet, dans le dossier qui contient `pom.xml` et `mvnw`.

## Vérifier Java et Maven Wrapper

Le projet demande Java 25 (`java.version` dans `pom.xml`). Maven est lancé via le wrapper du projet, donc il n'est pas nécessaire d'installer Maven séparément.

```bash
java -version
chmod +x mvnw   # à faire une seule fois si le wrapper n'est pas exécutable
./mvnw -v
```

## Démarrer l'application

```bash
./mvnw spring-boot:run
```

L'application démarre normalement sur `http://localhost:8080`. Arrête-la avec `Ctrl+C`.

## Commandes Maven courantes

| Commande | Utilité |
| --- | --- |
| `./mvnw -v` | Afficher les versions de Maven et Java utilisées par le wrapper. |
| `./mvnw clean` | Supprimer les fichiers générés dans `target`. |
| `./mvnw compile` | Compiler le code principal. |
| `./mvnw test` | Compiler et exécuter les tests. |
| `./mvnw package` | Construire le JAR dans `target` après les tests. |
| `./mvnw clean package` | Nettoyer puis reconstruire le JAR. |
| `./mvnw install` | Construire le projet et installer son artefact dans le dépôt Maven local. |
| `./mvnw spring-boot:run` | Démarrer l'application Spring Boot. |
| `./mvnw test -Dtest=NomDuTest` | Exécuter une classe de test précise, par exemple `./mvnw test -Dtest=SpringTrainingApplicationTests`. |

Pour démarrer le JAR après `package` :

```bash
java -jar target/SpringTraining-0.0.1-SNAPSHOT.jar
```

## Créer un contrôleur REST

Maven ne possède pas de commande intégrée pour générer un contrôleur dans ce projet. Crée le dossier et le fichier depuis la racine :

```bash
mkdir -p src/main/java/com/spring/springtraining/controller
touch src/main/java/com/spring/springtraining/controller/HelloController.java
```

Ajoute le code suivant à `HelloController.java` :

```java
package com.spring.springtraining.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }
}
```

Démarre l'application puis, depuis un second terminal, appelle le endpoint :

```bash
curl http://localhost:8080/hello
```

La réponse attendue est `Hello, Spring Boot!`. Le contrôleur est détecté automatiquement car son package est sous `com.spring.springtraining`.

## Nettoyer les fichiers générés

```bash
./mvnw clean
```
