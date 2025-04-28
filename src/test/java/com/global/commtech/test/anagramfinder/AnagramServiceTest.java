package com.global.commtech.test.anagramfinder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AnagramServiceTest {

    @InjectMocks
    private AnagramService anagramService;

    @Mock
    private AnagramFinder anagramFinder;

    @Test
    void shouldProcessFileBatchedByWordLength() {
        Path path = Path.of("src/test/resources/example1.txt");
        anagramService.process(path);

        ArgumentCaptor<List<String>> captor = ArgumentCaptor.forClass(List.class);

        verify(anagramFinder, times(2)).findAnagrams(captor.capture());

        assertThat(captor.getAllValues())
                .hasSize(2)
                .satisfiesExactlyInAnyOrder(
                        batch1 -> assertThat(batch1).containsExactly("abc", "fun", "bac", "fun", "cba", "unf"),
                        batch2 -> assertThat(batch2).containsExactly("hello"));
    }

    @Test
    void shouldHandleEmptyFile() {
        Path path = Path.of("src/test/resources/empty.txt");
        anagramService.process(path);

        verify(anagramFinder, never()).findAnagrams(any());
    }

    @Test
    void shouldThrowExceptionWhenErrorProcessingFile() {
        Path path = Path.of("src/test/resources/invalidFile.txt");
        assertThrows(RuntimeException.class, () -> anagramService.process(path));
    }

}