package com.example.gzf;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

/**
 * Created by Doa on 21-11-2020.
 */
@Getter
@AllArgsConstructor
public class GrondzaakFormulier {
    private int nummer;
    private List<Perceel> percelen;

    void showGZF() {
        Set<String> uniekeEigenaren = new HashSet<>();
        System.out.println("**************Grondzaak Formulier " + this.nummer + " *************************");
        System.out.print("Percelen:  ");
        for (Perceel perceel: this.percelen
             ) {
            System.out.print(perceel.getPerceelNummer() + ", ");
            uniekeEigenaren.addAll(Arrays.asList(perceel.getEigenaren()));
        }
        System.out.print("\n" + "Eigenaren: ");
        System.out.println(uniekeEigenaren);
        System.out.println("***************************************************" + "\n \n");

    }
}


