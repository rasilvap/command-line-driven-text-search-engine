package com.searchengine.Commandlinedriventextsearchengine.services;

import com.searchengine.Commandlinedriventextsearchengine.model.InputFile;
import com.searchengine.Commandlinedriventextsearchengine.services.imp.InputFilesServiceImp;
import com.searchengine.Commandlinedriventextsearchengine.services.imp.RankServiceImp;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class InputFilesServiceTest {

    @Rule
    public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Autowired
    private InputFilesService inputFilesServiceSUT;

    @BeforeEach
    public void setUp() {
        inputFilesServiceSUT = new InputFilesServiceImp();
    }

    @org.junit.jupiter.api.Test
    public void should_return_each_word_in_list_by_file() throws IOException {
        try (var filesPaths = Files.list(Paths.get("src/test/resources"))) {
            Map<String, List<String>> result = inputFilesServiceSUT.readFilesContent(filesPaths);
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

    @org.junit.jupiter.api.Test
    public void folder_not_exist_should_return_error() {
        assertThrows(RuntimeException.class, () -> {
            inputFilesServiceSUT.readFilesContent(Stream.of(Paths.get("src/test/resources/non-existent-folder")));
        });
    }

    @Test
    public void should_display_number_of_files_in_direcory() throws IOException {
        inputFilesServiceSUT.displayFilesCount("src/test/resources", "txt");
        Assertions.assertEquals("3 files read in directory src/test/resources", systemOutRule.getLogWithNormalizedLineSeparator().trim());
    }
}