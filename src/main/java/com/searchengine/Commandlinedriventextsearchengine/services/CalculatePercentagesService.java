package com.searchengine.Commandlinedriventextsearchengine.services;


import com.searchengine.Commandlinedriventextsearchengine.model.InputFile;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class CalculatePercentagesService {


    private ReadWordsService readWordsService;
    private RankService rankService;

    public CalculatePercentagesService(ReadWordsService readWordsService, RankService rankService) {
        this.readWordsService = readWordsService;
        this.rankService = rankService;
    }

    public boolean calculatePercentages(Map<String, List<String>> filesContent) {
        var searchedWords = readWordsService.readSearchedWords();
        if (searchedWords.size() == 1 && searchedWords.contains("quit")) {
            return true;
        }
        var ranks = rankService.calculateRanks(filesContent, searchedWords);
        ranks.sort(Comparator.comparing(InputFile::getScore).reversed());
        ranks.stream().limit(10).forEach(System.out::println);
        return false;
    }

}
