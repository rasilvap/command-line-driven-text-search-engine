package com.searchengine.Commandlinedriventextsearchengine.services;

import com.searchengine.Commandlinedriventextsearchengine.model.InputFile;

import java.util.List;
import java.util.Map;


public interface RankService {

    /**
     * Calculate the file rank.
     * @param fileName The file name.
     * @param fileWords the words contained in the file.
     * @param searchedWords The searched words.
     * @return The rank of the file.
     */
    InputFile calculateRank(String fileName, List<String> fileWords, List<String> searchedWords);

    /**
     * Calculate multiple files ranks. For each file, a rank is calculated, but the list is not sorted.
     * <p>If you want to sort the returned list, you must do that after calling this method.</p>
     * @param filesContent Map of files (files name) and their content (list of words).
     * @param searchedWords The searched words.
     * @return A rank for each file. This list is not sorted.
     */
    List<InputFile> calculateRanks(Map<String, List<String>> filesContent, List<String> searchedWords);
}