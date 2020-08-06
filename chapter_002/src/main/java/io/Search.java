package io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Search {

    public static void main(String[] args) throws IOException {

        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder or search pattern is null. Usage java -jar dir.jar ROOT_FOLDER PATTERN.");
        }

        Path start = Paths.get(args[0]);
        System.out.println("Идет поиск, ждите...");
        search(start, args[1]).forEach(System.out::println);
    }

    public static List<Path> search(Path root, String pattern) throws IOException {
        return doSearch(root, new SearchFiles(p -> p.toFile().getName().contains(pattern)));
    }

    public static List<Path> exSearch(Path root, String pattern) throws IOException {
        return doSearch(root, new SearchFiles(p -> !p.toFile().getName().contains(pattern)));
    }

    private static List<Path> doSearch(Path root, SearchFiles searcher) throws IOException {
        Files.walkFileTree(root, searcher);
        return searcher.getSearchPaths();
    }

    private static class SearchFiles implements FileVisitor<Path> {

        private final List<Path> searchPaths = new ArrayList<>();
        private final Predicate<Path> predicate;

        public List<Path> getSearchPaths() {
            return searchPaths;
        }

        public SearchFiles(Predicate<Path> predicate) {
            this.predicate = predicate;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (predicate.test(file)) {
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
