package io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Search {

    public static void main(String[] args) throws IOException {
        Path start = Paths.get("d:\\Java");
        System.out.println("Идет поиск, ждите...");
        search(start, "java").forEach(System.out::println);
    }

    public static List<Path> search(Path root, String pattern) throws IOException {
        SearchFiles searcher = new SearchFiles(pattern);
        Files.walkFileTree(root, searcher);
        return searcher.getSearchPaths();
    }

    private static class SearchFiles implements FileVisitor<Path> {

        private final List<Path> searchPaths = new ArrayList<>();
        private final String pattern;

        public List<Path> getSearchPaths() {
            return searchPaths;
        }

        public SearchFiles(String pattern) {
            this.pattern = pattern;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (file.toFile().getName().contains(pattern)) {
                searchPaths.add(file.toAbsolutePath());
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
