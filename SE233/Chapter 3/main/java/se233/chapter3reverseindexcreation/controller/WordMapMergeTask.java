package se233.chapter3reverseindexcreation.controller;

import se233.chapter3reverseindexcreation.model.FileFreq;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class WordMapMergeTask implements Callable<LinkedHashMap<String, ArrayList<FileFreq>>> {
    private Map<String, FileFreq>[] wordMap;

    public WordMapMergeTask(Map<String, FileFreq>[] wordMap) {
        this.wordMap = wordMap;
    }

    @Override
    public LinkedHashMap<String, ArrayList<FileFreq>> call() {
        LinkedHashMap<String, ArrayList<FileFreq>> uniqueSets;
        List<Map<String, FileFreq>> wordMapList = new ArrayList<>(Arrays.asList(wordMap));

        uniqueSets = wordMapList.stream()
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collector.of(
                                () -> new ArrayList<FileFreq>(),
                                (list, item) -> list.add(item.getValue()),
                                (current_list, new_items) -> {
                                    current_list.addAll(new_items);
                                    return current_list;
                                })
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (v1, v2) -> v1, LinkedHashMap::new));
        return uniqueSets;
    }
}
