package io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;

public class SearchDuplicates {

    public static void main(String[] args) throws IOException {
        Path start = Paths.get("d:\\Java");
        System.out.println("Идет поиск, ждите...");
        if (search(start)) {
            System.out.println("Дубликаты найдены");
        } else {
            System.out.println("Дубликаты не найдены");
        }
    }

    public static boolean search(Path root) throws IOException {
        SearchDup searcher = new SearchDup();
        Files.walkFileTree(root, searcher);
        return searcher.isDuplicates();
    }

    private static class SearchDup implements FileVisitor<Path> {

        private Set<String> fileNameSet = new HashSet<>();
        private boolean duplicates = false;

        public boolean isDuplicates() {
            return duplicates;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            var key = file.getFileName().toString() + " " + file.toFile().length();
            if (fileNameSet.contains(key)) {
                duplicates = true;
                return TERMINATE;
            } else {
                fileNameSet.add(key);
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
