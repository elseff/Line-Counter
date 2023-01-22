package com.elseff.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Counter {
    private static int total = 0;
    private static int countClasses = 0;

    public static void main(String[] args){
        String pathToProject = "D:\\dev\\LineCounter"; // HERE PATH TO YOUR JAVA PROJECT
        File project = new File(pathToProject);

        count(project);

        System.out.printf("\nProject %s has total %d lines and %d classes", project, total, countClasses);
    }

    public static void count(File file) {
        if (file.isFile())
            total+=countLines(file);
        else if (file.isDirectory())
            Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(Counter::count);
    }

    public static int countLines(File file) {
        int count = 0;
        Scanner scanner;
        try {
            if(file.getName().endsWith(".java")) {
                scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    scanner.nextLine();
                    count++;
                }
                System.out.printf("%s has %d lines\n", file.getName(), count);
                countClasses++;
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        return count;
    }
}
