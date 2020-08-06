package io;

public class ArgZip {

    private final String[] args;
    private String dir;
    private String exc;
    private String out;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        for (String arg : args) {
            var keyValue = arg.split("=");
            if (keyValue.length == 2) {
                if ("-d".equals(keyValue[0])) {
                    dir = keyValue[1];
                } else if ("-e".equals(keyValue[0])) {
                    exc = "." + keyValue[1];
                } else if ("-o".equals(keyValue[0])) {
                    out = keyValue[1];
                }
            } else {
                throw new IllegalArgumentException("Неверный параметр: " + arg);
            }
        }
        if (dir == null) {
            throw new IllegalArgumentException("Не хватает параметра: -d=[папка] - которую мы хотим архивировать");
        }
        if (exc == null) {
            throw new IllegalArgumentException("Не хватает параметра: -e=[расширение] - исключить файлы *.[расширение]");
        }
        if (out == null) {
            throw new IllegalArgumentException("Не хватает параметра: -o=[файл] - путь к архиву");
        }
        return true;
    }

    public String directory() {
        return dir;
    }

    public String exclude() {
        return exc;
    }

    public String output() {
        return out;
    }

}
