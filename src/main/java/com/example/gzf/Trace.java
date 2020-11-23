package com.example.gzf;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Doa on 21-11-2020.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Trace {
    private List<Perceel> percelen = new ArrayList<>();

    public void listAllPercelen () {
        for (Perceel perceel: this.percelen
             ) {
            System.out.println(perceel);
        }
    }

    public void addPerceel(Perceel perceel) {
        this.percelen.add(perceel);
    }

    public void removePercelen(List<Perceel> percelen) {
        this.percelen.removeAll(percelen);
    }

    public List<String> geEigenaren () {
        List<String> eigenaren = new ArrayList<>();
        for (Perceel perceel: this.percelen
             ) {
            for (String eigenaar: perceel.getEigenaren()
                 ) {
                eigenaren.add(eigenaar);
            }
        }
        return eigenaren.stream().sorted().collect(Collectors.toList());
    }

    public List<Perceel> getAllPercelenForEigenaar (String eigenaar) {
        return this.percelen.stream()
                .filter(perceel -> perceel.heeftDitPerceelDezeEigenaar(eigenaar))
               .collect(Collectors.toList());
    }
}
