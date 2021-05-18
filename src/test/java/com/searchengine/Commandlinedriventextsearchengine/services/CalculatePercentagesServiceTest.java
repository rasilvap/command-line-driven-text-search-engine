package com.searchengine.Commandlinedriventextsearchengine.services;

import com.searchengine.Commandlinedriventextsearchengine.model.InputFile;
import com.searchengine.Commandlinedriventextsearchengine.services.imp.CalculatePercentagesServiceImp;
import com.searchengine.Commandlinedriventextsearchengine.util.ReadWordsService;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculatePercentagesServiceTest {

    @Rule
    public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    private CalculatePercentagesService calculatePercentagesServiceSUT;
    private ReadWordsService readWordsService;
    private RankService rankService;

    @BeforeEach
    void setUp() {
        readWordsService = mock(ReadWordsService.class);
        rankService = mock(RankService.class);
        calculatePercentagesServiceSUT = new CalculatePercentagesServiceImp(readWordsService, rankService);
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
        when(readWordsService.readSearchedWords()).thenReturn(searchedWords);
        when(rankService.calculateRanks(filesContent, searchedWords))
                .thenReturn(Arrays.asList(
                        new InputFile("file2.txt", BigDecimal.valueOf(1)),
                        new InputFile("file1.txt", BigDecimal.ZERO)
                ));
        systemInMock.provideLines(searchedWord);
        boolean result = calculatePercentagesServiceSUT.calculatePercentages(filesContent);
        assertFalse(result);
        assertEquals("file2.txt : 100.00%\nfile1.txt : 0.00%\n", systemOutRule.getLogWithNormalizedLineSeparator());

    }

    @Test
    public void should_return_true_exit() {
        when(readWordsService.readSearchedWords()).thenReturn(Collections.singletonList("quit"));
        systemInMock.provideLines("quit");
        boolean result = calculatePercentagesServiceSUT.calculatePercentages(Map.of(
                "file1.txt", List.of("quit")
        ));
        assertTrue(result);
    }
}