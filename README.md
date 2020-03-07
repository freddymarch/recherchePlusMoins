Plusieurs modifications : 
 - séparation des sources (src/main/java) et des resources properties (src/main/resources)
 - création d'un répertoire lib contenant log4j-1.2.17.jar
 - Récupération des valeurs properties par la classe AppliProperty. L'idée est d'extraire cette 
 partie de la classe RecherchePlusMoins qui s'occupera uniquement des traitement métier
 - Création des random et des saisies clavier dans la classe Utilitaire
 - remplacement de tous les System.out.println par logger.info (lire https://automationtalks.com/2017/06/04/why-log4j-is-better-than/)
 - 


Création du jar du projet : 

https://www.jetbrains.com/help/idea/packaging-a-module-into-a-jar-file.html
https://www.youtube.com/watch?v=3Xo6zSBgdgk


Exemple de fichier README.md à compléter : README_EXEMPLE.md
Documentation Markdown : https://www.markdownguide.org/basic-syntax/