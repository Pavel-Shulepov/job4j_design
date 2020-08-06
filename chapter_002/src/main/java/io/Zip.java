package io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, File target) {
        try (FileOutputStream fos = new FileOutputStream(target)) {
            byte[] buffer = new byte[1024];
            try (ZipOutputStream zos = new ZipOutputStream(fos)) {
                for (Path source : sources) {
                    System.out.println("Архивируем файл: " + source.toFile().getAbsolutePath());
                    FileInputStream fis = new FileInputStream(source.toFile());
                    // "Костыль" для Windows split(":\\\\"), чтобы из пути исключить диск:\
                    String path;
                    if (source.toFile().getAbsolutePath().split(":\\\\").length > 0) {
                        path = source.toFile().getAbsolutePath().split(":\\\\")[1];
                    } else {
                        path = source.toFile().getAbsolutePath();
                    }
                    zos.putNextEntry(new ZipEntry(path));
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка архивирования: " + e);
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        ArgZip argZip = new ArgZip(args);

        if (argZip.valid()) {
            Path start = Paths.get(argZip.directory());
            System.out.println("Идет архивация, ждите...");
            packFiles(Search.exSearch(start, argZip.exclude()), new File(argZip.output()));
            System.out.println("Готово!");
        }

    }

}
