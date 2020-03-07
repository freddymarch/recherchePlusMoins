package com.ocr.game.enums;

public enum ModeJeu {

    CHALLENGER(1,"Challenger"),
    DEFENSEUR(2,"Défenseur"),
    DUEL(3,"Duel");

    private int choix;
    private String message;

    ModeJeu(int choix , String message){
        this.choix = choix;
        this.message = message;
    }

    /**
     * choix de l'enum en fonction du numero
     *
     * @param code numero de l'enum.
     * @return L'enum choisie
     */
    public static ModeJeu getMode(int code) {
        for (ModeJeu modeJeu : ModeJeu.values()) {
            if (code == modeJeu.choix) {
                return modeJeu;
            }
        }
        return null;
    }

    public String getMessage() {
        return message;
    }

    public int getChoix() {
        return choix;
    }
}
