package io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Параметр не найден: " + key);
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            var keyValue = arg.split("=");
            if (keyValue.length == 2) {
                values.put(keyValue[0].replaceFirst("-", ""), keyValue[1]);
            } else {
                throw new IllegalArgumentException("Неверный параметр: " + arg);
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }

}
