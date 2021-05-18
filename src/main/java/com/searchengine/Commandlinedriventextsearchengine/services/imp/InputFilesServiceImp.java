package com.searchengine.Commandlinedriventextsearchengine.services.imp;

import com.searchengine.Commandlinedriventextsearchengine.services.InputFilesService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Tool class manipulating files.
 */
@Service
public class InputFilesServiceImp implements InputFilesService {

    public Map<String, List<String>> readFilesContent(Stream<Path> filesPaths) {
        Map<String, List<String>> filesContent = new HashMap<>();
        filesPaths.forEach(filePath -> {
            try {
                filesContent.put(filePath.getFileName().toString(),
                        Files.readAllLines(filePath)
                                .stream()
                                .flatMap(list -> Stream.of(list.split(" ")))
                                .filter(word -> !word.isEmpty())
                                .map(String::trim)
                                .collect(Collectors.toList()));
            } catch (IOException e) {
                //TODO: throw an exception
                System.err.println("Error while loading lines from file : " + e.getMessage());
            }
        });
        return filesContent;
    }

    public void displayFilesCount(String directory, String filesExtension) throws IOException {
        long filesCount;
        try (var filesPaths = Files.list(Paths.get(directory))) {
            filesCount = filesPaths
                    .filter(p -> p.toString().endsWith(filesExtension))
                    .count();
        }
        var filesText = "files";
        if (filesCount == 1) {
            filesText = "file";
        }
        System.out.println(filesCount + " " + filesText + " read in directory " + directory);
    }
}
