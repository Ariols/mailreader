# Description [POC]

Lecture de mails depuis l'api Microsoft Graph -> enregistrement dans une base Mongo

# Prérequis

- Java 17
- Mongo >= 5.0.6 (Disponible via docker-compose)
- NodeJs >= 16.14.2

# Lancement

- La partie back

Démarrer le serveur Mongo

```shell
docker-compose up
```

```shell
./gradlew bootRun
```

Le serveur est accessible localhost:8080

- La partie front

```shell
cd client
```

Récupérer les dépendenses

```shell
yarn
```

Lancer l'appli

```shell
yarn start
```

Le serveur est accessible localhost:3000
