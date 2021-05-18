package com.searchengine.Commandlinedriventextsearchengine.services;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface InputFilesService {

    /**
     * This method reads all files content (filesPaths parameter).
     * The content is spliited into a list of words.
     * <p>Exemple :</p>
     * <p>If a file contains these words : one two three four, The methods will return a ({@link List}) of these four words.</p>
     *
     * @param filesPaths Files list to read.
     * @return A Map containing as key, the files name. And a list of words in values.
     */
    Map<String, List<String>> readFilesContent(Stream<Path> filesPaths);

    /**
     * Displays on the console the number of files from the directory parameter.
     *
     * @param directory      Directory containing the files to count.
     * @param filesExtension Predicat that indicate files extension to consider.
     * @throws IOException If the directory doesn't exist.
     */
    String displayFilesCount(String directory, String filesExtension) throws IOException;
}
