## Présentation du projet RecherchePlusMoins

EscapeGameOnline et un jeu qui consiste à trouver une combinaison à 4 chiffres (par défaut) en un certain nombre d'essais.La comparaison se fera avec = + ou - en fonction de la valeur comparée. Il y a trois modes de jeu

-challenger : vous devez deviner le code détenu par la machine.
-Défenseur : L'ordinateur tente de deviner votre code.
-Duel : Le premier qui trouve le code de l'autre a gagné.

## Réalisation

1. Environnement technique

  - IDE :  [IntelliJ 2018.3](https://www.jetbrains.com/idea/).
  - Langage de programmation : [Java 8/JDK 1.8.0_181](https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java)


2. Programmation - Concepts techniques

  - algorithmie-enumeration-héritage-polymorphisme-switch-condition-propoerties-log4j

## Livrable

1. `recherchePlusMoins.jar`

    A la fin de la programmation, j'ai généré le fichier `recherchePlusMoins.jar` qui représente l'exécutable de mon programme.
C'est un archive qui contient :
  - les fichiers `*.class` issus de la compilation des fichiers sources `*.java`,
  - les bibliothèques `*.jar` que j'ai utilisées pour construire le programme (bibliothèques `log4j`).

2. Exécution du fichier `recherchePlusMoins.jar`  

    Pour exécuter le programme :

    1. Exécution sans argument

    ```
    java -jar mastermind.jar
    ```

    2. Exécution avec arguments

        - Pour exécuter le programme en mode "DEV", taper la commande :

        ```
        java -jar mastermind.jar DEV
        ```

        - Pour exécuter le programme en mode "PROD", taper la commande :

        ```
        java -jar mastermind.jar PROD