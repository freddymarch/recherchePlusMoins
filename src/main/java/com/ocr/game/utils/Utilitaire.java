package com.ocr.game.utils;

import com.ocr.game.jeu.Resultat;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.Scanner;

import static com.ocr.game.utils.AppliProperty.getNombreDeChiffre;

public class Utilitaire {

    private static final Logger logger = Logger.getLogger(Utilitaire.class);

    /**
     * Ecrire une methode qui va générer un nombre aléatoire contenant 4 chiffres
     */
    public static int[] genererNombreAleatoire(int nbChiffre) {
        Random random = new Random();
        int[] combinaisonSecret = new int[nbChiffre];
        for (int i = 0; i < combinaisonSecret.length; i++) {
            combinaisonSecret[i] = random.nextInt(10);
        }
        return combinaisonSecret;
    }


    /**
     * Ecrire une methode qui va permettre une saisie clavier de 4 chiffres
     */
    public static int[] saisieClavier(int nbChiffre) {
        Scanner sc = new Scanner(System.in);
        boolean saisieOk = false;
        int[] saisieJoueur = new int[nbChiffre];
        do {
            String nombreSaisi = sc.nextLine();
            try {
                int test = Integer.valueOf(nombreSaisi);
                if (nombreSaisi.length() != nbChiffre) {
                    logger.info("La longueur du code saisie ne correspond pas a la valeur attendue.");
                    logger.info("Reessayez une saisie sur " + nbChiffre + " chiffres.");
                    logger.error("saisi supérieur au code");
                    saisieOk = false;
                } else {
                    for (int i = 0; i < nbChiffre; i++) {
                        saisieJoueur[i] = Integer.parseInt(String.valueOf(nombreSaisi.charAt(i)));
                    }
                    saisieOk = true;
                }
            } catch (NumberFormatException s) {
                logger.info("Utilisez uniquement des caractères numériques.");
                logger.error("saisi lettre");
                saisieOk = false;
            }

        } while (!saisieOk);
        return saisieJoueur;
    }


    public static char[] comparaisonOrdi(int nbChiffre) {
        Scanner sc = new Scanner(System.in);
        boolean saisieOk = false;
        char[] saisie = new char[nbChiffre];
        do {
            char[] comparJoueur = sc.nextLine().toCharArray();
            if (comparJoueur.length != nbChiffre) {
                logger.info("La saisie doit contenir uniquement " + nbChiffre + " caractères.");
                saisieOk = false;
            } else {
                int i = 0;
                while (i<comparJoueur.length) {
                    if (comparJoueur[i] != '+' && comparJoueur[i] != '-' && comparJoueur[i] != '=') {
                        logger.info("La saisie doit contenir uniquement les caractères -, +, =.");
                        saisieOk = false;
                        break;
                    }else {
                        i++;
                    }
                }
                if (i == comparJoueur.length) {
                    saisie = comparJoueur;
                    saisieOk = true;
                }
            }
        } while (!saisieOk);
        return saisie;
    }
}
