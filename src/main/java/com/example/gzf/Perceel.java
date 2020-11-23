package com.example.gzf;

import lombok.Getter;

import java.util.Arrays;

/**
 * Created by Doa on 21-11-2020.
 */
@Getter
public class Perceel {
    private String perceelNummer;
    private String[] eigenaren;

    Perceel (String perceelNummer, String ...eigenaren) {
        this.perceelNummer = perceelNummer;
        this.eigenaren = eigenaren;
    }

    @Override
    public String toString() {
        return "Perceel " + perceelNummer +
               ", eigenaren=" + Arrays.toString(eigenaren);
    }

    public boolean heeftDitPerceelDezeEigenaar (String eigenaar) {
        return Arrays.stream(this.eigenaren).anyMatch(eigenaar::equals);
    }

}
