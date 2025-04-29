# Anagram Finder
A simple command line utility for finding anagrams in a specified file

## Software required to run this
* Java 17

## Building and Running the tests
```
./gradlew clean build
```

## Running the program
```
./gradlew bootRun --args="example2.txt" 
```
where example2.txt is the text file that we want to search for anagrams

### Assumptions

- Anagrams are case-sensitive.
- Duplicate words should be excluded.
- The file contain one word per line, and all characters are assumed to be part of the word.
- The words in the input file are ordered by size.

### Big O analysis

The complexity of the anagram finder is O(N * n log(n)). Within the main loop for each N words we sort the word's characters. Sorting the word takes O(n log(n)), since this is performed once for each word the overall complexity is  O(N * n log(n)).

### Implementation Details

- Process file iteratively by batching all words of the same length for processing.
- Process current batch before starting on the next batch of words.
- The anagram finder first sorts the character of each word alphabetically then stores all matching anagrams in a map. 
- A ```Map<String, Set<String>>``` is used to store the anagrams:
  - They key is the sorted word of a batch.
  - The value is the Set containing all original words that match the sorted characters.
- After finding all anagrams of a batch the result are seperated by commas and printed.

The solution contains a AnagramService responsible for creating the batches to be processed, and printing out any matches returned from the AnagramFinder. The AnagramFinder handles the core logic for finding anagrams for a given list of words.


### Future Improvements

- Improve extensibility:
    - Split out reading and writing responsibilities to dedicated services (currently all within AnagramService).
    - Use of interfaces for core components (e.g. AnagramFinder, Reader, Writer) to allow easier replacement or extension.
- Error handling improvements:
  - Improve logging for better debugging (batch size, processing times). 
  - Handle empty or invalid lines instead of failing the entire batch/file.
  - Provide more user friendly errors.
- Investigate performance improvements: 
  - Processing batches in parallel.
  - Allowing configuration max batch size (currently reads all words of same length without limit).
  - Explore more efficient anagram finder strategies.
- Increase test coverage. 


