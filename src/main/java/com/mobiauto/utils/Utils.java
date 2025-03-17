package com.mobiauto.utils;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.Objects;

public class Utils {

    private static String MASCARA_CNPJ = "##.###.###/####-##";

    public static String formataCnpj (String cnpj) {
        try {
            cnpj = cnpj.replaceAll("[^0-9]", "");
            MaskFormatter mascara = new MaskFormatter(MASCARA_CNPJ);
            return mascara.valueToString(cnpj);
        } catch (Exception e) {
            return null;
        }
    }

    public static Boolean validaCnpj(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", "");

        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        //Espaço para uma validação mais elaborada

        return true;
    }
}
