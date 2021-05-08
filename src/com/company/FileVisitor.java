package com.company;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.LinkedList;

public class FileVisitor extends SimpleFileVisitor<Path> {
    /**
     * Класс накопителя путей при обходе дерева файлов
     */
    private final LinkedList<PathToFile> pathsToFiles = new LinkedList<>();

    public Path getPath() {
        PathToFile pathToFile = pathsToFiles.pollFirst();
        if (pathToFile != null) {
            return pathToFile.getFilePath();
        }
        else {
            return null;
        }
    }

    public void sortPaths() {
        Collections.sort(pathsToFiles);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes fileAttributes) {
        String fileName = file.getFileName().toString();
        char[] fileNameArray = fileName.toCharArray();
        if (fileNameArray[0] != '.') {
            pathsToFiles.addLast(new PathToFile(file));
            // каждый записанный путь печатается для наглядности
            System.out.println(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
