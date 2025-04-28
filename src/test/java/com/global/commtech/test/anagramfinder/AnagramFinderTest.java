package com.global.commtech.test.anagramfinder;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AnagramFinderTest {

    private final AnagramFinder anagramFinder = new AnagramFinder();

    @Test
    void findAnagramsShouldReturnAnagramsMap() {
        Map<String, Set<String>> result = anagramFinder.findAnagrams(List.of("abc", "bac", "fun"));
        assertThat(result)
                .isNotNull()
                .containsEntry("abc", Set.of("abc", "bac"))
                .containsEntry("fnu", Set.of("fun"));
    }

    @Test
    void findAnagramsShouldIgnoreDuplicates() {
        Map<String, Set<String>> result = anagramFinder.findAnagrams(List.of("abc", "bca", "abc"));
        assertThat(result)
                .isNotNull()
                .containsEntry("abc", Set.of("abc", "bca"));
    }

    @Test
    void findAnagramsShouldHandleNoMatchingAnagrams() {
        Map<String, Set<String>> result = anagramFinder.findAnagrams(List.of("abc", "fun"));
        assertThat(result)
                .isNotNull()
                .containsEntry("abc", Set.of("abc"))
                .containsEntry("fnu", Set.of("fun"));
    }

    @Test
    void findAnagramsShouldHandleEmptyInput() {
        Map<String, Set<String>> result = anagramFinder.findAnagrams(List.of());
        assertThat(result).isEmpty();
    }
}