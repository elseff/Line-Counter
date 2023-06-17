package com.elseff.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Counter {
    private final Map<File, Integer> files;
    private final String project;

    public Counter(String project) {
        this.project = project;
        files = new HashMap<>();
    }

    public void start() {
        findFiles(new File(project));
    }

    private void findFiles(File file) {
        if (file.isFile()) {
            if (file.getName().endsWith(".java"))
                files.put(file, countLinesInFile(file));
        } else if (file.isDirectory())
            Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(this::findFiles);
    }

    public void printFilesInfo() {
        files.forEach((key, value) -> System.out.printf("%s has %d lines\n", key.getName(), value));
    }

    private int countLinesInFile(File file) {
        int count = 0;
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                count++;
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        return count;
    }

    public Optional<Map.Entry<File, Integer>> getMaxLinesFile() {
        int max = files
                .values()
                .stream()
                .max(Comparator.naturalOrder())
                .orElse(0);

        return files
                .entrySet()
                .stream()
                .filter(entry ->
                        entry.getValue().equals(max))
                .findFirst();
    }

    public Optional<Map.Entry<File, Integer>> getMinLinesFile() {
        int min = files
                .values()
                .stream()
                .min(Comparator.naturalOrder())
                .orElse(0);
        return files
                .entrySet()
                .stream()
                .filter(entry->
                        entry.getValue().equals(min))
                .findFirst();
    }

    public Integer getTotal() {
        return files.values().stream().mapToInt(value -> value).sum();
    }

    public String getProject() {
        return project;
    }

    public Integer getCountClasses() {
        return files.values().size();
    }
}
