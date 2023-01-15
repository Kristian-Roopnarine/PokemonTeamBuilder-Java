package com.pokemonteambuilder.app.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;


public class FileWriterService {

    private Path getAbsoluteSaveFilePath(String outputFile){
        return Paths.get(System.getProperty("user.dir") + "/" + outputFile);
    }

    public void saveToFile(String outputFile, String outputData) throws IOException{
        final Path savePath = getAbsoluteSaveFilePath(outputFile);
        System.out.println(savePath.toAbsolutePath());
        Files.writeString(savePath, outputData, StandardCharsets.UTF_8);
    }
}
