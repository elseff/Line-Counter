package com.elseff.project;

import java.io.File;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter("/home/elseff/Documents/dev/projects/socialmedia");
        counter.start();
        Integer total = counter.getTotal();
        String project = counter.getProject();
        Integer countClasses = counter.getCountClasses();

        counter.printFilesInfo();

        Optional<Map.Entry<File, Integer>> maxLinesFile = counter.getMaxLinesFile();
        maxLinesFile.ifPresent(entry ->
                System.out.printf("\nmost bigger file %s has %d lines\n",
                        entry.getKey().getName(),
                        entry.getValue())
        );

        Optional<Map.Entry<File, Integer>> minLinesFile = counter.getMinLinesFile();
        minLinesFile.ifPresent(entry ->
                System.out.printf("\nsmallest file %s has %d lines\n",
                        entry.getKey().getName(),
                        entry.getValue())
        );
        System.out.printf("\nproject %s has total %d lines and %d classes\n", project, total, countClasses);
    }
}
