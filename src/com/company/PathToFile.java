package com.company;

import java.nio.file.Path;

public class PathToFile implements Comparable<PathToFile> {
    /**
     * Класс для хранения строки имени файла помимо пути к нему,
     * чтобы иметь возможность сортировать пути по именам файлов
     */
    private final Path filePath;
    private final String fileName;

    public PathToFile(Path file) {
        filePath = file;
        fileName = file.getFileName().toString();
    }

    public Path getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public int compareTo(PathToFile o) {
        return fileName.compareTo(o.getFileName());
    }
}
