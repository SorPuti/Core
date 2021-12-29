package br.com.puti.core.component.cryptography.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static String capitalize(String str, String arg0) {
        String[] args = str.split(arg0);
        if (args == null)
            return null;

        String result = "";
        for (String a : args)
            result = result.concat(a.substring(0, 1).toUpperCase() + a.substring(1, a.length()).toLowerCase() + " ");

        return result;
    }

    public static List<String> toList(String value) {
        List<String> lista = new ArrayList<String>();
        for (String a : value.split("@line", 10))
            lista.add(a.replace("&", "§"));

        return lista;
    }

    public static String toString(List<String> values) {
        if (values == null)
            return "";
        if (values.size() == 0)
            return "";

        StringBuilder str = new StringBuilder();
        for (String line : values)
            str.append((line.isEmpty() ? "&f" : line.replace("§", "&") + "@line"));
        return str.toString();
    }
}
