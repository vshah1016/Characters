import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Client {
    Scanner scanner = new Scanner(System.in);
    private boolean runAgain = true;

    public String gatherInput() {
        System.out.print("Enter your sentence here: ");
        String input = scanner.nextLine();
        System.out.println();
        return input;
    }

    public boolean isRunAgain() {
        return runAgain;
    }

    public boolean runAgain() {
        System.out.print("\nWould you like to run the program again? (y/n): ");
        runAgain = scanner.next().strip().equalsIgnoreCase("y");
        System.out.println();
        scanner.nextLine();
        return runAgain;
    }

    public void makeTable(Sentence sentence){
        HashMap<String, Integer> hashMap = sentence.getWordsAndOccurrences();
        String[] columnNames = {"Word", "Occurrences"};
        ArrayList<ArrayList<String>> listData = new ArrayList<ArrayList<String>>();
        hashMap.forEach((key, pair) -> {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(key);
            temp.add(String.valueOf(pair));
            listData.add(temp);
        });
        String[][] data = listData.stream().map(e -> e.toArray(new String[0])).toArray(String[][]::new);
        JFrame f = new JFrame();
        JTable  jt = new JTable(data, columnNames);
        JScrollPane sp = new JScrollPane(jt);
        f.add(sp);
        f.setSize(300, 400);
        f.setVisible(true);
    }
    public void outputSentence(Sentence sentence) {
        System.out.println("The sentence you entered is as follows: " + sentence.sentence);
        System.out.println("There " + (sentence.characterCount == 1 ? "is " : "are ") + sentence.characterCount + " character" + (sentence.characterCount == 1 ? "" : "s") + " in the sentence.");
        table(sentence.getWordsAndOccurrences());
    }

    private void table(HashMap<String, Integer> wordsAndOccurrences) {
        int maxWordLength = Arrays.stream(wordsAndOccurrences.keySet().toArray(new String[0])).map(String::length).max(Integer::compareTo).get();
        int wordColumnLength = Math.max(maxWordLength, 5);
        int occurrencesLength = Arrays.stream(wordsAndOccurrences.keySet().toArray(new String[0])).map(String::length).max(Integer::compareTo).get();
        int occurrencesColumnLength = Math.max(11, occurrencesLength);

        System.out.println("Words" + " ".repeat(wordColumnLength - 5) + "   " + "Occurrences");
        System.out.println("-".repeat(wordColumnLength) + "   " + "-".repeat(occurrencesColumnLength));

        wordsAndOccurrences.forEach((key, pair) -> {
            if (key.equals("")) return;
            System.out.print(key + " ".repeat(wordColumnLength - key.length()));
            System.out.print("   ");
            System.out.print(pair);
            System.out.println();
        });
    }
}
