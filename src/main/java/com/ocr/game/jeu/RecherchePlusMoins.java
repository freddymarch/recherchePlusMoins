package com.ocr.game.jeu;

import com.ocr.game.enums.ModeJeu;
import com.ocr.game.utils.AppliProperty;
import com.ocr.game.utils.Utilitaire;
import org.apache.log4j.Logger;
import java.util.Arrays;

import static com.ocr.game.utils.AppliProperty.*;

public class RecherchePlusMoins {

    private static final Logger logger = Logger.getLogger(RecherchePlusMoins.class);
    private static final String DEV = "DEV";
    private static final String PROD = "PROD";

    private int choixUtilisateur;
    private String paramModLancement;

    public void jouer() {
        logger.info("Vous avez  : " + getNombreDessais() + " essais et " + getNombreDeChiffre() + "  cases à trouver");
        ModeJeu modeJeu = getModeJeu();
        logger.info("le joueur veut jouer a recherche plus ou moins, mode de jeu = " + modeJeu + ", nombre d'éssai = " + getNombreDessais() + ", nombre de case a trouver = " + getNombreDeChiffre());
        switch (modeJeu) {
            case CHALLENGER:
                jouerRecherchePlusMoinsChallenger();
                break;

            case DEFENSEUR:
                jouerRecherchePlusMoinsDefenseur();
                break;

            case DUEL:
                jouerRecherchePlusMoinsDuel();
                break;

            default:
                logger.info("Vous avez rentré un numéro qui ne correspond à aucun choix");
                System.exit(-1);
                break;

        }
    }

    public int getChoixUtilisateur() {
        return choixUtilisateur;
    }

    public void setChoixUtilisateur(int choixUtilisateur) {
        this.choixUtilisateur = choixUtilisateur;
    }

    public String getParamModLancement() {
        return paramModLancement;
    }

    public void setParamModLancement(String paramModLancement) {
        this.paramModLancement = paramModLancement;
    }
    /**
     * Getter de l'enum des types de jeu disponibles
     *
     * @return le mode de jeu
     */
    private ModeJeu getModeJeu() {
        return ModeJeu.getMode(choixUtilisateur);
    }

    private void jouerRecherchePlusMoinsChallenger() {
        logger.info("Vous jouez à RecherchePlusMoins - challenger vous devez tentez de deviner un code que l'ordinateur va génerer !");
        int[] codeSecret = Utilitaire.genererNombreAleatoire(getNombreDeChiffre());
        String codeSecretFormate = formatterTableau(codeSecret);
        if (isModeDeveloppeur() == true) {
            logger.info("Le code secret généré est :" + codeSecretFormate);
        }
        int compteur = 0;
        Resultat resultat = new Resultat();
        int nbEssais = getNombreDessais();
        do {
            logger.info("Essai n° " + (compteur + 1) + " /  " + nbEssais);
            int[] saisieClavier = Utilitaire.saisieClavier(getNombreDeChiffre());
            resultat = comparaison(saisieClavier, codeSecret);
            logger.info("Proposition : " + formatterTableau(saisieClavier) + " -> Réponse : " + formatterTableau(resultat.getTabPlusMoins()));
            compteur++;
        } while (!resultat.isTrouve() && compteur != nbEssais);
        if (resultat.isTrouve()) {
            logger.info("Bravo !!! Tu gagnes en " + compteur + " essais.");
            logSucces("challenger", compteur);
        }
        if (!resultat.isTrouve() && compteur == nbEssais) {
            logger.info("Tu as perdu ! tu as atteint tes " + compteur + " essais");
            logger.info("Le code géneré à deviner était  " + codeSecretFormate);
            logEchec("challenger", compteur);
        }
        ;
    }

    private void jouerRecherchePlusMoinsDefenseur() {
        //l'ordinateur vas généré un code aleatoire et verifier si le code aleatoire est egal a mon code secret
        logger.info("Vous jouez à RecherchePlusMoins - Defenseur l'ordinateur va tentez de deviner le code que vous allez saisir !");
        System.out.print("veuillez saisir le code secret :");
        int[] codeSecret = Utilitaire.saisieClavier(getNombreDeChiffre());
        if (isModeDeveloppeur() == true) {
            logger.info("Le code secret généré est :" + formatterTableau(codeSecret));
        }
        int compteur = 0;
        Resultat resultat = new Resultat();
        int nbEssais = getNombreDessais();
        do {
            logger.info("Essai n° " + (compteur + 1) + " /  " + nbEssais);
            int[] saisieOrdinateur = Utilitaire.genererNombreAleatoire(getNombreDeChiffre());
            resultat = comparaison(saisieOrdinateur, codeSecret);
            logger.info("Proposition : " + formatterTableau(saisieOrdinateur) + " -> Réponse : " + formatterTableau(resultat.getTabPlusMoins()));
            compteur++;

        } while (!resultat.isTrouve() && compteur != nbEssais);
        if (resultat.isTrouve()) {
            logger.info("Tu as perdu l'ordinateur a trouver le code en " + compteur + " essais.");
            logSucces("defenseur", compteur);
        }
        if (!resultat.isTrouve() && compteur == nbEssais) {
            logger.info("Tu as gagner ! l'ordinateur n'a pas trouvé le code secret avant les " + compteur + " essais");
            logger.info("Le code géneré à deviner était  " + formatterTableau(codeSecret));
            logEchec("defenseur", compteur);
        }
        ;
    }

    private void jouerRecherchePlusMoinsDuel() {
        logger.info("Vous jouez à RecherchePlusMoins - Duel vous allez affrontez l'ordinateur tour par tour pour trouver le code secret !");
        int[] codeSecret = Utilitaire.genererNombreAleatoire(getNombreDeChiffre());
        String codeSecretFormate = formatterTableau(codeSecret);
        int compteur = 0;
        Resultat resultatJoueur = new Resultat();
        Resultat resultatOrdinateur = new Resultat();
        int nbEssais = getNombreDessais();
        do {
            logger.info("-------------------------------");
            formatterTableau(codeSecret);
            if (isModeDeveloppeur() == true) {
                logger.info("Le code secret généré est " + codeSecretFormate);
            }
            logger.info("Essai du joueur n° " + (compteur + 1) + " / " + nbEssais);
            int[] saisieClavier = Utilitaire.saisieClavier(getNombreDeChiffre());
            resultatJoueur = comparaison(saisieClavier, codeSecret);
            logger.info("Proposition : " + formatterTableau(saisieClavier) + " -> Réponse : " + formatterTableau(resultatJoueur.getTabPlusMoins()));
            if (resultatJoueur.isTrouve()) {
                compteur++;
                break;
            }
            logger.info("-------------------------------");
            logger.info("veuillez saisir le code secret:");
            int[] codeSecretJoueur = Utilitaire.saisieClavier(getNombreDeChiffre());
            if (isModeDeveloppeur() == true) {
                logger.info("Le code secret saisie par le joueur est " + formatterTableau(codeSecretJoueur));
            }
            logger.info("Essai de l'ordinateur n° " + (compteur + 1) + " / " + nbEssais);
            int[] saisieOrdinateur = Utilitaire.genererNombreAleatoire(getNombreDeChiffre());
            logger.info(formatterTableau(saisieOrdinateur));
            resultatOrdinateur = comparaison(saisieOrdinateur, codeSecretJoueur);
            logger.info("Proposition : " + formatterTableau(saisieOrdinateur) + " -> Réponse : " + formatterTableau(resultatOrdinateur.getTabPlusMoins()));
            compteur++;
        } while (!resultatJoueur.isTrouve() && !resultatOrdinateur.isTrouve() && compteur != nbEssais);
        if (resultatJoueur.isTrouve()) {
            logger.info("Bravo !!! Tu as battu l'ordinateur en " + compteur + " essais.");
            logSucces("duel", compteur);
        }
        if (resultatOrdinateur.isTrouve()) {
            logger.info("Perdu !!! L'ordinateur a trouvé le code en " + compteur + " essais.");
            logEchec("duel", compteur);
        }
        if (!resultatJoueur.isTrouve() && !resultatOrdinateur.isTrouve() && compteur == nbEssais) {
            logger.info("Perdu !!! Le nombre d'essais a été atteint :" + compteur + " essais");
            logger.info("Le code du joueur géneré à deviner était  " + formatterTableau(codeSecret));
            logger.info("Le code de l'ordinateur saisie à deviner était  ");
            logEchec("duel", compteur);
        }
        ;
    }

    private void logSucces(String modeJeu, int nbEssai) {
        logger.info("le joueur a gagné au jeu en mode " + modeJeu + " au bout de " + nbEssai + " essai(s)");
    }

    private void logEchec(String modeJeu, int nbEssai) {
        logger.info("le joueur a perdu au jeu en mode " + modeJeu + " au bout de " + nbEssai + " essai(s)");
    }

    private boolean isModeDeveloppeur(){
        if (paramModLancement == null) {
            return getModeDeveloppeur();
        } else {
            if (DEV.equals(paramModLancement)) {
                return true;
            } else {
                return false;
            }
        }
    }
    /**
     * Créer une methode qui compare la combinaison avec la saisie clavier de l'utilisateur
     */
    private Resultat comparaison(int[] codeSaisie, int[] codeSecret) {
        int compteur = 0;
        String[] tabPlusOuMoins = new String[getNombreDeChiffre()];
        Resultat resultat = new Resultat();
        for (int i = 0; i < codeSaisie.length; i++) {
            if (codeSaisie[i] < codeSecret[i]) {
                tabPlusOuMoins[i] = "+";
            } else if (codeSaisie[i] > codeSecret[i]) {
                tabPlusOuMoins[i] = "-";
            } else {
                tabPlusOuMoins[i] = "=";
                compteur++;
            }
        }
        resultat.setTrouve(compteur == codeSaisie.length);
        resultat.setTabPlusMoins(tabPlusOuMoins);
        return resultat;
    }

    /**
     * Methode qui permet de modifier le texte enlever les espaces et les virgules.
     *
     * @param tableau tableau à modifier
     * @return valeur modifiée.
     */
    private String formatterTableau(int[] tableau) {
        String format = Arrays.toString(tableau);
        return format.replace(",", "").replace(" ", "").
                replace("[", "").replace("]", "");
    }

    private String formatterTableau(String[] tableau) {
        String format = Arrays.toString(tableau);
        return format.replace(",", "").replace(" ", "").
                replace("[", "").replace("]", "");
    }
}
