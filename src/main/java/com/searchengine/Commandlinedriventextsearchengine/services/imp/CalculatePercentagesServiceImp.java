package com.searchengine.Commandlinedriventextsearchengine.services.imp;


import com.searchengine.Commandlinedriventextsearchengine.model.InputFile;
import com.searchengine.Commandlinedriventextsearchengine.services.CalculatePercentagesService;
import com.searchengine.Commandlinedriventextsearchengine.services.RankService;
import com.searchengine.Commandlinedriventextsearchengine.util.ReadUserInputs;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class CalculatePercentagesServiceImp implements CalculatePercentagesService {


    private ReadUserInputs readUserInputs;
    private RankService rankService;

    public CalculatePercentagesServiceImp(ReadUserInputs readUserInputs, RankService rankService) {
        this.readUserInputs = readUserInputs;
        this.rankService = rankService;
    }

    public boolean calculatePercentages(Map<String, List<String>> filesContent) {
        var searchedWords = readUserInputs.readSearchedWords();
        if (searchedWords.size() == 1 && searchedWords.contains("quit")) {
            return true;
        }
        var ranks = rankService.calculateRanks(filesContent, searchedWords);
        ranks.sort(Comparator.comparing(InputFile::getScore).reversed());
        ranks.stream().limit(10).forEach(System.out::println);
        return false;
    }

}