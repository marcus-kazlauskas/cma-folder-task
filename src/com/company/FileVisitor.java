package com.company;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;

public class FileVisitor extends SimpleFileVisitor<Path> {
    /**
     * Накопитель путей при обходе дерева файлов
     */
    private final LinkedList<Path> pathToFile = new LinkedList<>();

    public Path getPath() {
        return pathToFile.pollFirst();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes fileAttributes) {
        String fileName = file.getFileName().toString();
        char[] fileNameArray = fileName.toCharArray();
        if (fileNameArray[0] != '.') {
            pathToFile.addLast(file);
            // каждый записанный путь печатается для наглядности
            System.out.println(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
