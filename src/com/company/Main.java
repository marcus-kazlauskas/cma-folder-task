package com.company;

import java.io.IOException;
import java.nio.file.*;

public class Main {
    /**
     * Класс для обхода дерева файлов и
     * склейки содержащихся в нём файлов в один
     * в алфавитном порядке следования их имён
     */
    public static void main(String[] args) {
        // директория, в которой надо искать файлы
        Path sourceFolder = Paths.get("./folders/source");
        // накопитель путей к директориям
        FileVisitor fileVisitor = new FileVisitor();
        try {
            Files.walkFileTree(sourceFolder, fileVisitor);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        // cортировка по имени самих файлов
        fileVisitor.sortPaths();

        // директория, в которой будет находится склеенный файл
        Path destinationFile = Paths.get("./folders/destination/summary.txt");
        //destinationFile.getName().toString()
        // содержимое копируемого файла
        byte[] byteLines;
        // путь к копируемому файлу
        Path currentPath = fileVisitor.getPath();
        // текст из первого файла всё перезаписывает, текст из остальных добавляется в конец
        try {
            byteLines = Files.readAllBytes(currentPath);
            Files.write(destinationFile, byteLines);
            currentPath = fileVisitor.getPath();
            while (currentPath != null) {
                byteLines = Files.readAllBytes(currentPath);
                Files.write(destinationFile, byteLines, StandardOpenOption.APPEND);
                currentPath = fileVisitor.getPath();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
