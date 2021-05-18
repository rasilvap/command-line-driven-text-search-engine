package com.searchengine.Commandlinedriventextsearchengine.services.imp;

import com.searchengine.Commandlinedriventextsearchengine.model.InputFile;
import com.searchengine.Commandlinedriventextsearchengine.services.RankService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RankServiceImp implements RankService {

    public InputFile calculateRank(String fileName, List<String> fileWords, List<String> searchedWords) {
        var existingWordsInFile = searchedWords.stream().filter(fileWords::contains).collect(Collectors.toList());
        return new InputFile(fileName,
                BigDecimal.valueOf(existingWordsInFile.size())
                        .divide(BigDecimal.valueOf(searchedWords.size()), 4, RoundingMode.HALF_UP));
    }

    public List<InputFile> calculateRanks(Map<String, List<String>> filesContent, List<String> searchedWords) {
        List<InputFile> ranks = new ArrayList<>();
        filesContent.forEach((inputFileName, fileWords) ->
                ranks.add(calculateRank(inputFileName, fileWords, searchedWords))
        );
        return ranks;
    }
}
