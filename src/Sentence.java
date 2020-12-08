import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Sentence {
    public final int characterCount;
    public final String sentence;
    private HashMap<String, Integer> wordsAndOccurrences;

    public Sentence(String sentence) {
        this.sentence = sentence.trim();
        characterCount = sentence.replace("+\\s", "").length();
    }

    private static boolean isPunctuation(char punctuation) {
        return punctuation == '.' || punctuation == ';' || punctuation == ',' || punctuation == '!' || punctuation == '?' || punctuation == ':';
    }

    private static String[] split(String stringToBeChanged) {
        int index = 0;
        List<String> matchList = new LinkedList<>();
        Matcher m = Pattern.compile("\\s+").matcher(stringToBeChanged);
        while(m.find()) {
                if (index == 0 && index == m.start() && 0 == m.end()) {
                    continue;
                }
                String match = stringToBeChanged.subSequence(index, m.start()).toString();
                matchList.add(match);
                index = m.end();

        }
        if (index == 0)
            return new String[] {stringToBeChanged};
        matchList.add(stringToBeChanged.subSequence(index, stringToBeChanged.length()).toString());
        int resultSize = matchList.size();
        while (resultSize > 0 && matchList.get(resultSize-1).isEmpty())
            resultSize--;
        String[] result = new String[resultSize];
        return matchList.subList(0, resultSize).toArray(result);
    }

    HashMap<String, Integer> getWordsAndOccurrences() {
        if (wordsAndOccurrences != null) return wordsAndOccurrences;
        String[] words = split(sentence);
        words = Arrays.stream(words).map(word -> {
            if (isPunctuation(word.charAt(word.length() - 1))) return word.substring(0, word.length() - 1);
            return word;
        }).toArray(String[]::new);
        String[] finalWords = words;
        wordsAndOccurrences = (HashMap<String, Integer>) Arrays.stream(words).collect(Collectors.toMap(String::toLowerCase,
                e -> (int) Arrays.stream(finalWords).filter(word -> word.equalsIgnoreCase(e)).count(),
                (e1, e2) -> e1));
        return wordsAndOccurrences;
    }
}
