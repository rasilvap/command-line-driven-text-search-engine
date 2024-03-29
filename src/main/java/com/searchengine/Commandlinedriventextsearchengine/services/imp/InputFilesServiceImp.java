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

import static com.searchengine.Commandlinedriventextsearchengine.util.Constants.FILE;
import static com.searchengine.Commandlinedriventextsearchengine.util.Constants.FILES;

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
                System.err.println("Error while loading lines from file : " + e.getMessage());
            }
        });
        return filesContent;
    }

    public String displayFilesCount(String directory, String filesExtension) throws IOException {
        long filesCount;
        try (var filesPaths = Files.list(Paths.get(directory))) {
            filesCount = filesPaths
                    .filter(p -> p.toString().endsWith(filesExtension))
                    .count();
        }
        var filesText = FILES;
        if (filesCount == 1) {
            filesText = FILE;
        }
        String message = filesCount + " " + filesText + " read in directory " + directory;
        System.out.println(message);
        return message;
    }
}
