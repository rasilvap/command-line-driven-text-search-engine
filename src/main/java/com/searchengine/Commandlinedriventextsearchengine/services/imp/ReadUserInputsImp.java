package com.searchengine.Commandlinedriventextsearchengine.services.imp;

import com.searchengine.Commandlinedriventextsearchengine.services.ReadUserInputs;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class ReadUserInputsImp implements ReadUserInputs {
    public List<String> readSearchedWords() {
        List<String> searchedWord = new ArrayList<>();
        System.out.print("search>");
        var scanner = new Scanner(System.in);
        var line = scanner.nextLine();
        if (line == null || line.trim().isEmpty()) {
            System.err.println("No words in the input. Please type something.");
        } else {
            searchedWord = Arrays.stream(line.split(" "))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
        return searchedWord;
    }
}
