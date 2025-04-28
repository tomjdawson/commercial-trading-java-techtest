package com.global.commtech.test.anagramfinder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnagramService {

    private final AnagramFinder anagramFinder;

    /**
     * Process file iteratively by words of same length.
     */
    public void process(Path filePath) {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            List<String> wordsOfSamelength = new ArrayList<>();
            var wordLength = 0;
            String currentWord;

            while ((currentWord = reader.readLine()) != null) {

                if (currentWord.length() != wordLength) {
                    processBatch(wordsOfSamelength);
                    wordsOfSamelength = new ArrayList<>();
                    wordLength = currentWord.length();
                }
                wordsOfSamelength.add(currentWord);
            }

            //Process any remaining words of same length
            processBatch(wordsOfSamelength);

        } catch (IOException e) {
            log.error("error while processing file {}", filePath, e);
           throw new RuntimeException(e);
        }
    }

    private void processBatch(List<String> words) {
        if (!words.isEmpty()) {
            var anagrams = anagramFinder.findAnagrams(words);
            printAnagrams(anagrams);
        }
    }

    private void printAnagrams(Map<String, Set<String>> anagrams) {
        anagrams.forEach((k, v) -> System.out.println(String.join(",", v)));
    }
}
