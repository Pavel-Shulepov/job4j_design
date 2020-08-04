package io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchDuplicates {

    public static void main(String[] args) throws IOException {
        Path start = Paths.get("d:\\Java");
        System.out.println("Идет поиск, ждите...");
        List<List<String>> dub = search(start);
        if (dub.size() > 0) {
            System.out.println("Дубликаты найдены:");
            dub.forEach(d -> {
                d.forEach(System.out::println);
                System.out.println();
            });
        } else {
            System.out.println("Дубликаты не найдены");
        }
    }

    public static List<List<String>> search(Path root) throws IOException {
        SearchDup searcher = new SearchDup();
        Files.walkFileTree(root, searcher);
        return searcher.getDuplicates();
    }

    private static class SearchDup implements FileVisitor<Path> {

        private final Map<String, List<String>> dubMap = new HashMap<>();

        public List<List<String>> getDuplicates() {
            return dubMap.values().stream().filter(v -> v.size() > 1).collect(Collectors.toList());
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            var keyFile = file.getFileName().toString() + " " + file.toFile().length();
            if (dubMap.containsKey(keyFile)) {
                dubMap.merge(keyFile, dubMap.get(keyFile), (key, list) -> {
                    List<String> fileList = new ArrayList<>(list);
                    fileList.add(file.toAbsolutePath().toString());
                    return fileList;
                });
            } else {
                List<String> list = new ArrayList<>();
                list.add(file.toAbsolutePath().toString());
                dubMap.put(keyFile, list);
            }
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
            return CONTINUE;
        }
    }

}
