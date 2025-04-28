package com.global.commtech.test.anagramfinder;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class AnagramFinder {

    /**
     * Find all anagrams for given list of words and return map of anagrams, where the key is the sorted word,
     * and the value is a list of all matched words for a given key.
     */
    public Map<String, Set<String>> findAnagrams(List<String> words) {

        Map<String, Set<String>> anagrams = new HashMap<>();

        for (String word : words) {
            var characters = word.toCharArray();
            Arrays.sort(characters);

            var anagramKey = String.valueOf(characters);
            anagrams.computeIfAbsent(anagramKey, k -> new LinkedHashSet<>()).add(word);
        }

        return anagrams;
    }
}
