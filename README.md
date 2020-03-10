## Pr�sentation du projet RecherchePlusMoins

scapeGameOnline et un jeu qui consiste a trouver une combinaison � 4 chiffre (par default) en un certain nombre d'essai.la comparaisson ce fera avec = + ou - en fontion de la valeur comparer. il y a trois mode de jeu

-challenger:vous devez deviner le code d�tenu par la machine.
-D�fenseur : L'ordinateur tente de deviner votre code.
-Duel : Le premier qui trouve le code de l'autre a gagn�.

## R�alisation

1. Environnement technique

  - IDE :  [IntelliJ 2018.3](https://www.jetbrains.com/idea/).
  - Langage de programmation : [Java 8/JDK 1.8.0_181](https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java)


2. Programmation - Concepts techniques

  - algorithmie-enumeration-h�ritage-polymorphisme-switch-condition-propoerties-log4j

## Livrable

1. `recherchePlusMoins.jar`

    � la fin de la programmation, j'ai g�n�r� le fichier `recherchePlusMoins.jar` qui repr�sente l'ex�cutable de mon programme.
C'est un archive qui contient :
  - les fichiers `*.class` issus de la compilation des fichiers sources `*.java`,
  - les biblioth�ques `*.jar` que j'ai utilis�es pour construire le programme (biblioth�ques `log4j`).

2. Ex�cution du fichier `recherchePlusMoins.jar`  

    Pour ex�cuter le programme :

    1. Ex�cution sans argument

    ```
    java -jar mastermind.jar
    ```

    2. Ex�cution avec arguments

        - Pour ex�cuter le programme en mode "DEV", taper la commande :

        ```
        java -jar mastermind.jar DEV
        ```

        - Pour ex�cuter le programme en mode "PROD", taper la commande :

        ```
        java -jar mastermind.jar PROD