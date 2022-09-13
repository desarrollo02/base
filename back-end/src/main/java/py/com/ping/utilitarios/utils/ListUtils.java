/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utilitarios.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author rudy
 */
public class ListUtils {

    /**
     * order : un numero negativo indica order inverso
     */
    public static List<? extends Ordenable> getSortedList(List<? extends Ordenable> auxList, int order) {
        if (auxList != null ) {
            Ordenable[] vector = new Ordenable[auxList.size()];
            vector = auxList.toArray(vector);

            Arrays.sort(vector, new Comparator<Ordenable>() {
                @Override
                public int compare(Ordenable o1, Ordenable o2) {
                    String[] v1;
                    String[] v2;
                    if (order < 0) {
                        v1 = o2.getNro().trim().split("\\.");
                        v2 = o1.getNro().trim().split("\\.");
                    } else {
                        v1 = o1.getNro().trim().split("\\.");
                        v2 = o2.getNro().trim().split("\\.");
                    }

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

}
