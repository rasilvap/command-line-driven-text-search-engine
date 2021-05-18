package com.searchengine.Commandlinedriventextsearchengine;

import com.searchengine.Commandlinedriventextsearchengine.exceptions.ScoresEngineInputException;
import com.searchengine.Commandlinedriventextsearchengine.services.CalculatePercentagesService;
import com.searchengine.Commandlinedriventextsearchengine.services.InputFilesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.System.exit;

@SpringBootApplication
public class CommandLineDrivenTextSearchEngineApplication implements CommandLineRunner {

    private final CalculatePercentagesService calculatePercentagesService;
    private final InputFilesService inputFilesService;

    public CommandLineDrivenTextSearchEngineApplication(CalculatePercentagesService calculatePercentagesService, InputFilesService inputFilesService) {
        this.calculatePercentagesService = calculatePercentagesService;
        this.inputFilesService = inputFilesService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CommandLineDrivenTextSearchEngineApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (args.length == 0) {
            throw new ScoresEngineInputException("No directory given to index. Please type directory containing files to read.");
        }
        try {
            var directory = args[0];
            inputFilesService.displayFilesCount(directory, "txt");
            readFilesAndShowScores(directory);
        } catch (IOException e) {
            System.err.println("The program can't find the specified folder. Please type an existing one.");
        }

        exit(0);
    }

    /**
     * General algorithm calculating the scores.
     * <p>Note : 'exit' is a reserved word but alone. If typed alone, the program will exit.</p>
     *
     * @param directory The directory containing files
     * @throws IOException If the directory is not read.
     */
    private void readFilesAndShowScores(String directory) throws IOException {
        try (var filesPaths = Files.list(Paths.get(directory))) {
            var filesContent = inputFilesService.readFilesContent(filesPaths);
            while (true) {
                if (calculatePercentagesService.calculatePercentages(filesContent)) break;
            }
        }
    }

}
