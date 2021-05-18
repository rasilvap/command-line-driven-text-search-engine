package com.searchengine.Commandlinedriventextsearchengine.services;

import com.searchengine.Commandlinedriventextsearchengine.model.InputFile;
import com.searchengine.Commandlinedriventextsearchengine.services.imp.CalculatePercentagesServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculatePercentagesServiceTest {

    private CalculatePercentagesService calculatePercentagesServiceSUT;
    private ReadUserInputs readUserInputs;
    private RankService rankService;

    @BeforeEach
    void setUp() {
        readUserInputs = mock(ReadUserInputs.class);
        rankService = mock(RankService.class);
        calculatePercentagesServiceSUT = new CalculatePercentagesServiceImp(readUserInputs, rankService);
    }

    @Test
    void calculatePercentages() {
    }

    @Test
    public void calculateScores() {
        String searchedWord = "second";
        List<String> searchedWords = Collections.singletonList(searchedWord);
        Map<String, List<String>> filesContent = Map.of(
                "file1.txt", List.of("the", "word"),
                "file2.txt", List.of("the", searchedWord, "word")
        );
        when(readUserInputs.readSearchedWords()).thenReturn(searchedWords);
        when(rankService.calculateRanks(filesContent, searchedWords))
                .thenReturn(Arrays.asList(
                        new InputFile("file2.txt", BigDecimal.valueOf(1)),
                        new InputFile("file1.txt", BigDecimal.ZERO)
                ));

        boolean result = calculatePercentagesServiceSUT.calculatePercentages(filesContent);
        assertFalse(result);

    }

    @Test
    public void should_return_true_exit() {
        when(readUserInputs.readSearchedWords()).thenReturn(Collections.singletonList("quit"));
        boolean result = calculatePercentagesServiceSUT.calculatePercentages(Map.of(
                "file1.txt", List.of("quit")
        ));
        assertTrue(result);
    }
}