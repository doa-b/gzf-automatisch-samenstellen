package com.example.gzf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class GzfApplication {

    public static void main(String[] args) throws CloneNotSupportedException {
        SpringApplication.run(GzfApplication.class, args);

        Trace trace = new Trace();
        String[] eigenaren = {"doa", "ronie", "niels", "anne", "rashid", "tom", "shervin", "rogier"};
        int aantalPercelen = 26;
        for (char i = 'A'; i < 'A' + aantalPercelen; i++) {
            String eigenaar1 = eigenaren[new Random().nextInt(eigenaren.length)];
            String eigenaar2 = eigenaren[new Random().nextInt(eigenaren.length)];
            if (eigenaar1.equals(eigenaar2)) {
                trace.addPerceel(new Perceel(String.valueOf(i), eigenaar1));
            } else trace.addPerceel(new Perceel(String.valueOf(i), eigenaar1, eigenaar2));
        }

        List<GrondzaakFormulier> grondzaakformulieren = new ArrayList<>();
        System.out.println();
        System.out.println("========= " + trace.getPercelen().size() + " Percelen" + " =========");
        trace.listAllPercelen();
        System.out.println();
        algoritme2(trace);
    }

    static Map<String, Long> mapEigenarenByCount(List<String> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    static List<String> meestVoorkomend(List<String> list) {
        Map<String, Long> elementCountMap = mapEigenarenByCount(list);
        return elementCountMap.values().stream()
                .max(Long::compareTo).map(maxValue -> elementCountMap.entrySet().stream()
                        .filter(entry -> maxValue.equals(entry.getValue())).map(Map.Entry::getKey).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    static List<String> minstVoorkomend(List<String> list) {
        Map<String, Long> elementCountMap = mapEigenarenByCount(list);
        return elementCountMap.values().stream()
                .min(Long::compareTo).map(maxValue -> elementCountMap.entrySet().stream()
                        .filter(entry -> maxValue.equals(entry.getValue())).map(Map.Entry::getKey).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    static void algoritme1(Trace trace) {
        // Algoritme 1. Verwijder kleinste bundel eerst
        List<String> alleEigenaren = trace.geEigenaren();
        System.out.println(mapEigenarenByCount(alleEigenaren));

        GrondzaakFormulier GZF;

        for (int n = 1; trace.getPercelen().size() > 0; n++) {
            // vind kleinste bundel
            alleEigenaren = trace.geEigenaren();
            String eigenaarKleinsteBundel = minstVoorkomend(alleEigenaren).get(0);
            System.out.println("we bundelen " + eigenaarKleinsteBundel);
            GZF = new GrondzaakFormulier(n, trace.getAllPercelenForEigenaar(eigenaarKleinsteBundel));
            GZF.showGZF();
            trace.removePercelen(GZF.getPercelen());
            //trace.listAllPercelen();
        }
    }

    static void algoritme2(Trace trace) {
        // Algoritme 1. Verwijder grootste bundel eerst
        List<String> alleEigenaren = trace.geEigenaren();
        System.out.println(mapEigenarenByCount(alleEigenaren));

        GrondzaakFormulier GZF;

        for (int n = 1; trace.getPercelen().size() > 0; n++) {
            // vind grootste bundel
            alleEigenaren = trace.geEigenaren();
            String eigenaarGrootsteBundel = meestVoorkomend(alleEigenaren).get(0);
            System.out.println("we bundelen " + eigenaarGrootsteBundel);
            GZF = new GrondzaakFormulier(n, trace.getAllPercelenForEigenaar(eigenaarGrootsteBundel));
            GZF.showGZF();
            trace.removePercelen(GZF.getPercelen());
            //trace.listAllPercelen();
        }
    }
}
