package com.ocr.game.jeu;

import com.ocr.game.enums.ModeJeu;
import com.ocr.game.utils.AppliProperty;
import com.ocr.game.utils.Utilitaire;
import org.apache.log4j.Logger;
import java.util.Arrays;
import java.util.Scanner;

import static com.ocr.game.utils.AppliProperty.*;
import static com.ocr.game.utils.Utilitaire.*;

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
            logSucces(compteur);
        }
        if (!resultat.isTrouve() && compteur == nbEssais) {
            logger.info("Tu as perdu ! tu as atteint tes " + compteur + " essais");
            logger.info("Le code géneré à deviner était  " + codeSecretFormate);
            logEchec(compteur);
        }
        ;
    }

    private void jouerRecherchePlusMoinsDefenseur() {
        Boolean cpuWin = false;
        Scanner sc = new Scanner(System.in);
        logger.info("veuillez saisir le code secret:");
        String codeSecret = sc.nextLine();
        if (isModeDeveloppeur()) {
            logger.info("Le code secret généré est :" + codeSecret);
        }
        String compare = "";
        int[] proposition = null;
        for(int compteur=0; compteur< getNombreDessais(); compteur++){
            logger.info("Essai : "+(compteur+1)+"/"+getNombreDessais());
            proposition = recherche(proposition,compare,getNombreDeChiffre());
            logger.info("Proposition : " +formatterTableau(proposition) + " -> reponse : ");
            compare = String.valueOf(comparaisonOrdi(getNombreDeChiffre()));
            if (! compare.contains("+") && ! compare.contains("-")) {
                cpuWin = true;
                logger.info("L'ordinateur remporte la partie!");
                break;
            }
        }
        if (!cpuWin){
            logger.info("Vous avez gagné !");
        }
    }

    private void jouerRecherchePlusMoinsDuel() {
        logger.info("Vous jouez à RecherchePlusMoins - Duel vous allez affrontez l'ordinateur tour par tour pour trouver le code secret !");
        logger.info("veuillez saisir le code secret:");
        String compare = "";
        int[] codeSecretJoueur = Utilitaire.saisieClavier(getNombreDeChiffre());
        if (isModeDeveloppeur()) {
            logger.info("Le code secret saisie par le joueur est : " + formatterTableau(codeSecretJoueur));
        }
        int[] codeSecret = Utilitaire.genererNombreAleatoire(getNombreDeChiffre());
        String codeSecretFormate = formatterTableau(codeSecret);
        if (isModeDeveloppeur()) {
            logger.info("Le code secret généré par l'intelligence artificielle est : " + codeSecretFormate);
        }
        int compteur = 0;
        int nbEssais = getNombreDessais() / 2;
        int[] proposition = null;
        for(int i = 0; i < nbEssais ; i++){
            logger.info("-------------------------------");
            formatterTableau(codeSecret);
            logger.info("Essai du joueur n° " + (compteur + 1) + " / " + nbEssais);
            int[] saisieClavier = Utilitaire.saisieClavier(getNombreDeChiffre());
            Resultat resultatJoueur = comparaison(saisieClavier, codeSecret);
            logger.info("Proposition : " + formatterTableau(saisieClavier) + " -> Réponse : " + formatterTableau(resultatJoueur.getTabPlusMoins()));
            if (resultatJoueur.isTrouve() ){
                logger.info("Bravo !!! Tu as battu l'ordinateur en " + compteur + " essais.");
                logSucces(compteur);
                break;
            }
            logger.info("-------------------------------");
            logger.info("Essai de l'ordinateur n° " + (compteur + 1) + " / " + nbEssais);
            Scanner sc = new Scanner(System.in);
            proposition = recherche(proposition,compare,getNombreDeChiffre());
            System.out.println("Proposition : " + formatterTableau(proposition) + " -> Réponse :");
            compare = String.valueOf(comparaisonOrdi(getNombreDeChiffre()));
            compteur++;
            if (! compare.contains("+") && ! compare.contains("-")){
                logger.info("Perdu !!! L'ordinateur a trouvé le code en " + compteur + " essais.");
                logEchec(compteur);
                break;
            }
            if (i == nbEssais){
                logger.info("Perdu !!! Le nombre d'essais a été atteint :" + compteur + " essais");
                logEchec(compteur);
            }
        }
    }

    private void logSucces(int nbEssai) {
        logger.info("le joueur a gagné au jeu en mode " + getModeJeu().getMessage() + " au bout de " + nbEssai + " essai(s)");
    }

    private void logEchec(int nbEssai) {
        logger.info("le joueur a perdu au jeu en mode " + getModeJeu().getMessage() + " au bout de " + nbEssai + " essai(s)");
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

    private static int[] recherche(int[] combinaison, String compare,int nbcominaisons) {
        if("".equals(compare)){
            int[] firstRes = new int[nbcominaisons];
            for(int i=0; i < nbcominaisons; i++){
                firstRes[i] = 5;
            }
            return(firstRes);
        }
        int [] res = new int[combinaison.length];
        int borneMin = 1;
        int borneMax = 9;
        for(int cpt = 0; cpt<combinaison.length; cpt++) {
            if(compare.charAt(cpt) == '+') {
                if (combinaison[cpt] >= 5) {
                    borneMin = combinaison[cpt] + 1;
                    borneMax = 9;
                    res[cpt] = (borneMax + borneMin) / 2;
                }
                else {
                    borneMin = combinaison[cpt] + 1 ;
                    borneMax = 4;
                    res[cpt] = (borneMax + borneMin) / 2;
                }
            } else {
                if (compare.charAt(cpt) == '-'){
                    if (combinaison[cpt] <= 5) {
                        borneMin = 1;
                        borneMax = combinaison[cpt] -1;
                        res[cpt] = (borneMax + borneMin) / 2;
                    } else {
                        borneMin = 6;
                        borneMax = combinaison[cpt] -1  ;
                        res[cpt] = (borneMax + borneMin) / 2;
                    }
                }
                else {
                    res[cpt] = combinaison[cpt];
                }
            }
        }
        return res;
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
