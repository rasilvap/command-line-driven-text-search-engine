package com.searchengine.Commandlinedriventextsearchengine.services;

import java.util.List;

public interface ReadUserInputs {
    /**
     * Read all the words typed by user.
     * @return List of words typed by user.
     */
    List<String> readSearchedWords();
}
