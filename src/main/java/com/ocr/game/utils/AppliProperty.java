package com.ocr.game.utils;

import com.ocr.game.jeu.RecherchePlusMoins;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppliProperty {

    private static final Logger logger = Logger.getLogger(AppliProperty.class);

    public static String getProperty(String propertyName) {
        Properties appProps = new Properties();
        String value = null;
        try {
            InputStream inputStream = RecherchePlusMoins.class.getClassLoader().getResourceAsStream("config.properties");
            appProps.load(inputStream);
            value = appProps.getProperty(propertyName);
        } catch (IOException ioexception) {
            logger.error(ioexception.getStackTrace());
        }
        return value;
    }


    /**
     * Getter Nbr d'essai
     *
     * @return nombreDessais
     */
    public static int getNombreDessais() {
        String value = getProperty("nbEssai");
        return value != null ? Integer.parseInt(value) : 10;
    }

    /**
     * Nombre de chiffre servant à donner la longueur du résultat à trouver.
     *
     * @return nombre de chiffres
     */
    public static int getNombreDeChiffre() {
        String value = getProperty("nbChiffre");
        return value != null ? Integer.parseInt(value) : 4;
    }

    /**
     * mode développeur servent à afficher le code secret
     *
     * @return mdDeveloppeur
     */
    public static boolean getModeDeveloppeur() {
        String value = getProperty("mdDevelopeur");
        return value != null ? Boolean.parseBoolean(value) : false;
    }

}
