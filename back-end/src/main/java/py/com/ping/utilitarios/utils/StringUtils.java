/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utilitarios.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import py.com.ping.utils.dto.PasswordDto;

/**
 *
 * @author rudy
 */
public class StringUtils {

    public static boolean isEmpty(Object parametro) {
        if (parametro == null) {
            return true;
        }

        if (parametro instanceof String) {
            return isEmpty((String) parametro);
        }
        return false;
    }

    public static boolean isEmpty(String parametro) {
        return parametro == null || parametro.trim().isEmpty();
    }

    public static String addSingleQuote(String parametro) {
        StringBuilder builder = new StringBuilder();
        builder.append("'").append(parametro).append("'");
        return builder.toString();
    }

    public static String getAsIntegerString(BigDecimal monto) {
        BigInteger valorConvertido;
        try {
            valorConvertido = monto.toBigInteger();
        } catch (Exception e) {
            valorConvertido = BigInteger.ZERO;
        }
        return valorConvertido.toString();
    }

    public static String getAsIntegerString(Integer monto) {
        Integer valorConvertido;
        try {
            valorConvertido = monto;
        } catch (Exception e) {
            valorConvertido = 0;
        }
        return valorConvertido.toString();
    }

    public static BigInteger getBigDecimalAsInteger(BigDecimal monto) {
        BigInteger valorConvertido;
        try {
            valorConvertido = monto.toBigInteger();
        } catch (Exception e) {
            valorConvertido = BigInteger.ZERO;
        }
        return valorConvertido;
    }

    public static String getSortedString(List<? extends Ordenable> auxList) {
        StringBuilder builder = new StringBuilder();

        Ordenable[] vector = new Ordenable[auxList.size()];
        vector = auxList.toArray(vector);

        Arrays.sort(vector, new Comparator<Ordenable>() {
            @Override
            public int compare(Ordenable o1, Ordenable o2) {
                String[] v1 = o1.getNro().trim().split("\\.");
                String[] v2 = o2.getNro().trim().split("\\.");
                int i = 0;
                // set index to first non-equal ordinal or length of shortest version string
                while (i < v1.length && i < v2.length && v1[i].equals(v2[i])) {
                    i++;
                }
                // compare first non-equal ordinal number
                if (i < v1.length && i < v2.length) {
                    int diff = Integer.valueOf(v1[i]).compareTo(Integer.valueOf(v2[i]));
                    return Integer.signum(diff);
                } // the strings are equal or one string is a substring of the other
                // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
                else {
                    return Integer.signum(v1.length - v2.length);
                }

            }

        });

        for (int i = 0; i < vector.length; i++) {
            builder.append(vector[i].getNro()).append(".\t")
                    .append(vector[i].getDescripcion()).append("\n");
        }
        return builder.toString();

    }

    public static Ordenable[] getSortedList(List<? extends Ordenable> auxList) {
        Ordenable[] vector = new Ordenable[auxList.size()];
        vector = auxList.toArray(vector);

        Arrays.sort(vector, new Comparator<Ordenable>() {
            @Override
            public int compare(Ordenable o1, Ordenable o2) {
                String[] v1 = o1.getNro().trim().split("\\.");
                String[] v2 = o2.getNro().trim().split("\\.");
                int i = 0;
                // set index to first non-equal ordinal or length of shortest version string
                while (i < v1.length && i < v2.length && v1[i].equals(v2[i])) {
                    i++;
                }
                // compare first non-equal ordinal number
                if (i < v1.length && i < v2.length) {
                    int diff = Integer.valueOf(v1[i]).compareTo(Integer.valueOf(v2[i]));
                    return Integer.signum(diff);
                } // the strings are equal or one string is a substring of the other
                // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
                else {
                    return Integer.signum(v1.length - v2.length);
                }

            }

        });

        return vector;

    }

    public static List<? extends Ordenable> getSortedListTwo(List<? extends Ordenable> auxList) {
        if (auxList != null && !auxList.isEmpty()) {
            Ordenable[] vector = new Ordenable[auxList.size()];
            vector = auxList.toArray(vector);

            Arrays.sort(vector, new Comparator<Ordenable>() {
                @Override
                public int compare(Ordenable o1, Ordenable o2) {
                    String[] v1 = o1.getNro().trim().split("\\.");
                    String[] v2 = o2.getNro().trim().split("\\.");
                    int i = 0;
                    // set index to first non-equal ordinal or length of shortest version string
                    while (i < v1.length && i < v2.length && v1[i].equals(v2[i])) {
                        i++;
                    }
                    // compare first non-equal ordinal number
                    if (i < v1.length && i < v2.length) {
                        int diff = Integer.valueOf(v1[i]).compareTo(Integer.valueOf(v2[i]));
                        return Integer.signum(diff);
                    } // the strings are equal or one string is a substring of the other
                    // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
                    else {
                        return Integer.signum(v1.length - v2.length);
                    }

                }

            });

            return new ArrayList(Arrays.asList(vector));
        } else {
            return null;
        }

    }

    public static String getTextInsideTag(String tag, String text) {
        text = text.replace("\n", "").replace("\r", "");
        final Pattern pattern = Pattern.compile("<" + tag + ">(.+?)</" + tag + ">");
        final Matcher matcher = pattern.matcher(text);
        matcher.find();
        return matcher.group(1);
    }

    public static String getTextInsideTagTwo(String tag, String text) {
        text = text.replace("\n", "").replace("\r", "");
        final Pattern pattern = Pattern.compile("\\{" + tag + "\\}(.+?)\\{/" + tag + "\\}");
        final Matcher matcher = pattern.matcher(text);
        matcher.find();
        return matcher.group(1);
    }

    public static String replaceTextInsideTag(String text, String tag, String replacement) {
        return text.replaceAll("(?<=<" + tag + ">).*?(?=</" + tag + ">)", replacement);
    }

    public static String replaceTextInsideTagTwo(String text, String tag, String replacement) {
        try {
            return text.replaceAll("(?<=\\{" + tag + "\\}).*?(?=\\{/" + tag + "\\})", replacement);
        } catch (Exception e) {
            return text;
        }

    }

    public static String removeTag(String text) {
        return text.replaceAll("\\{.*?\\}", "");
    }

    public static List<String> getTagValues(String texto, String tag) {
        Pattern tagRegex = Pattern.compile("<" + tag + ">(.+?)</" + tag + ">");
        final List<String> tagValues = new ArrayList<String>();
        final Matcher matcher = tagRegex.matcher(texto);
        while (matcher.find()) {
            tagValues.add(matcher.group(1));
        }
        return tagValues;
    }

    public static void main(String[] args) {
        String text = "MÍNIMO <dias>30</dias> DÍAS CALENDARIO a partir de la fecha límite de presentación.";
        String tag = "dias";
        System.out.println(getTextInsideTag(tag, text));

        System.out.println(replaceTextInsideTag(text, tag, "55"));
        System.out.println(removeTag(text));
    }

    public static PasswordDto passwordValidation(String password) {
        PasswordDto passwordDto = new PasswordDto();
        boolean valid = true;
        if (password.length() > 15 || password.length() < 8) {
            System.out.println("Password should be less than 15 and more than 8 characters in length.");
            valid = false;
            passwordDto.setValido(false);
            passwordDto.setMsg("La contraseña debe ser de al menos 8 y menor a 15 caracteres");
        }
//        if (password.indexOf(userName) > -1) {
//            System.out.println("Password Should not be same as user name");
//            valid = false;
//        }
//        String upperCaseChars = "(.*[A-Z].*)";
//        if (!password.matches(upperCaseChars)) {
//            System.out.println("Password should contain atleast one upper case alphabet");
//            valid = false;
//        }
//        String lowerCaseChars = "(.*[a-z].*)";
//        if (!password.matches(lowerCaseChars)) {
//            System.out.println("Password should contain atleast one lower case alphabet");
//            valid = false;
//             passwordDto.setValido(false);
//            passwordDto.setMsg("La contraseña debe tener al menos un caractere");
//        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers)) {
            System.out.println("Password should contain atleast one number.");
            valid = false;
            passwordDto.setValido(false);
            passwordDto.setMsg("La contraseña debe tener al menos un número");
        }
//        String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
//        if (!password.matches(specialChars)) {
//            System.out.println("Password should contain atleast one special character");
//            valid = false;
//        }
        if (valid) {
            passwordDto.setValido(true);
            passwordDto.setMsg("La contraseña es valida");
        }

        return passwordDto;
    }

    public static String nullTo(String unString, String valorSiNull) {
       if(unString == null) 
           return valorSiNull;
       else
           return  unString;
    }

    public static String trimIfNotNull(String str) {
        if(str != null)
            return str.trim();
        else
            return str;
    }

    public static String getIntegerToRoman(int num) {

        System.out.println("Integer: " + num);
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder roman = new StringBuilder();

        for(int i=0;i<values.length;i++) {
            while(num >= values[i]) {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        System.out.println("Roman: " + roman.toString());
        System.out.println("---------------------------------------------------");
        return roman.toString();
    }
}
