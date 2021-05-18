package com.searchengine.Commandlinedriventextsearchengine.services;

import com.searchengine.Commandlinedriventextsearchengine.model.InputFile;
import com.searchengine.Commandlinedriventextsearchengine.services.imp.RankServiceImp;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class RankServiceTest {

    private List<String> fileWords;
    private String fileName;
    private RankService rankServiceSUT;

    @BeforeEach
    void setUp() {
        rankServiceSUT = new RankServiceImp();
        fileWords = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");
        fileName = "file.txt";
    }

    @Test
    public void should_return_0_percent_score() {
        var rank = rankServiceSUT.calculateRank(fileName, fileWords, Arrays.asList("fifteen", "eleven", "twelve", "twenty"));
        Assert.assertEquals(new InputFile(fileName, new BigDecimal("0.00")), rank);
    }

    @Test
    public void should_return_25_percent_score() {
        var rank = rankServiceSUT.calculateRank(fileName, fileWords, Arrays.asList("one", "eleven", "twelve", "twenty"));
        Assert.assertEquals(new InputFile(fileName, new BigDecimal("0.25")), rank);
    }

    @Test
    public void should_return_50_percent_score() {
        var rank = rankServiceSUT.calculateRank(fileName, fileWords, Arrays.asList("one", "eleven"));
        Assert.assertEquals(new InputFile(fileName, new BigDecimal("0.50")), rank);
    }

    @Test
    public void should_return_75_percent_score() {
        var rank = rankServiceSUT.calculateRank(fileName, fileWords, Arrays.asList("one", "two", "three", "twenty"));
        Assert.assertEquals(new InputFile(fileName, new BigDecimal("0.75")), rank);
    }

    @Test
    public void should_return_100_percent_score() {
        var rank = rankServiceSUT.calculateRank(fileName, fileWords, Arrays.asList("one", "two", "three", "four"));
        Assert.assertEquals(new InputFile(fileName, new BigDecimal("1.00")), rank);
    }

    @Test
    public void should_return_100_percent_for_two_files() {
        var ranks = rankServiceSUT.calculateRanks(Map.of(fileName, fileWords, "file2.txt", Arrays.asList("one", "two", "three", "four")),
                Arrays.asList("one", "two", "three", "four"));
        assertEquals(2, ranks.size());
        ranks.forEach(rank ->
                assertTrue("file.txt : 100.00%".equals(rank.toString())
                        || "file2.txt : 100.00%".equals(rank.toString())));

    }
}