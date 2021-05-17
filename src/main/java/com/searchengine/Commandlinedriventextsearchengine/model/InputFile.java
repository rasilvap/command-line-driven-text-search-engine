package com.searchengine.Commandlinedriventextsearchengine.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Rank of a file. It's a representation of a file.
 */
@Getter
@Setter
public class InputFile {

    private final String fileName;
    private final BigDecimal score;

    /**
     * Parameterized constructor.
     * @param fileName the file name
     * @param score The file Score
     */
    public InputFile(String fileName, BigDecimal score) {
        this.fileName = fileName;
        this.score = score;
    }

    @Override
    public String toString() {
        return fileName + " : " +
                score
                        .multiply(BigDecimal.valueOf(100))
                        .setScale(2, RoundingMode.HALF_UP)
                        .toString() + "%";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InputFile rank = (InputFile) o;
        return Objects.equals(fileName, rank.fileName) &&
                Objects.equals(score.setScale(2, RoundingMode.HALF_UP), rank.score.setScale(2, RoundingMode.HALF_UP));
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, score);
    }

}
