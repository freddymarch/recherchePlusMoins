package com.ocr.game;

import com.ocr.game.enums.ModeJeu;
import com.ocr.game.jeu.RecherchePlusMoins;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class EscapeGameOnline {
    private static final Logger logger = Logger.getLogger(EscapeGameOnline.class);
    private static final int CHOIX_REJOUER = 0;
    private static final int CHOIX_QUITTER = 4;

    public static void main(String[] args) {
        String paramModLancement = null;
        if (args.length > 0) {
            paramModLancement = args[0];
        }
        Scanner sc = new Scanner(System.in);
        boolean optionRejouer = false;
        int modePrecedent = 0;
        boolean quitterJeu = false;
        do {
            RecherchePlusMoins recherchePlusMoins = new RecherchePlusMoins();
            recherchePlusMoins.setParamModLancement(paramModLancement);
            menuPrincipal(optionRejouer);
            int selection = sc.nextInt();
            ModeJeu modeJeu = ModeJeu.getMode(selection);
            if (selection != CHOIX_QUITTER && selection != CHOIX_REJOUER && modeJeu == null) {
                logger.info("Choix inconnu. Vous allez quitter le jeu.");
                quitterJeu = true;
                break;
            }
            if (selection == CHOIX_QUITTER) {
                quitterJeu = true;
                break;
            } else if (selection == CHOIX_REJOUER && modePrecedent > 0) {
                recherchePlusMoins.setChoixUtilisateur(modePrecedent);
                recherchePlusMoins.jouer();
                optionRejouer = true;
            } else {
                recherchePlusMoins.setChoixUtilisateur(selection);
                recherchePlusMoins.jouer();
                modePrecedent = selection;
                optionRejouer = true;
            }
        } while (!quitterJeu);

    }

    public static void menuPrincipal(boolean optionRejouer) {
        logger.info("Bienvenue dans Escape Game Online");
        if (optionRejouer) {
            logger.info("Pour rejouer le jeu précédent, taper : " + CHOIX_REJOUER);
        }
        for (ModeJeu modeJeu : ModeJeu.values()) {
            logger.info("Pour jouer en " + modeJeu.getMessage() + ", taper : " + modeJeu.getChoix());
        }
        logger.info("Pour quitter, taper : " + CHOIX_QUITTER);
    }
}




