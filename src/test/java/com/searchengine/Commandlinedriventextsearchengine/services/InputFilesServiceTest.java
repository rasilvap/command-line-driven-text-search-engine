package com.searchengine.Commandlinedriventextsearchengine.services;

import com.searchengine.Commandlinedriventextsearchengine.services.imp.InputFilesServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;


class InputFilesServiceTest {


    @Autowired
    private InputFilesService inputFilesService;

    @BeforeEach
    public void setUp() {
        inputFilesService = new InputFilesServiceImp();
    }

    @Test
    public void should_return_each_word_in_list_by_file() throws IOException {
        try (var filesPaths = Files.list(Paths.get("src/test/resources"))) {
            Map<String, List<String>> result = inputFilesService.readFilesContent(filesPaths);
            String file1 = "file1.txt";
            String file2 = "file2.txt";
            String file3 = "file3.txt";
            var expectedFile1Content = Arrays.asList("the", "other", "word");
            var expectedFile2Content = Arrays.asList("the", "amazing", "word");
            var expectedFile3Content = Arrays.asList("The", "search", "engine", "is",  "very", "efficient");
            Assertions.assertEquals(Set.of(file1, file2, file3), result.keySet());
            Assertions.assertEquals(expectedFile1Content, result.get(file1));
            Assertions.assertEquals(expectedFile2Content, result.get(file2));
            Assertions.assertEquals(expectedFile3Content, result.get(file3));
        }
    }

    @Test
    public void should_display_number_of_files_in_direcory() throws IOException {
        String message = inputFilesService.displayFilesCount("src/test/resources", "txt");
        Assertions.assertEquals("3 files read in directory src/test/resources", message);
    }
}