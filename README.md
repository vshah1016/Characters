#Character Program Algorithm
In this program I knew I had to do a few things. First I had to gather the input for the program, make an object for the sentence, then find the Words and the Occurences that it would take place in, and then loop the program. My extra credit was it popping up a GUI with the words and occurences, I was not sure how much else I could do with it.

#Main Class
Pretty standard class, there is a Client object that has client side methods, and there is a sentence object made with the input sentence.

Then the main class asks the user if they want to run the program again utilizing the client class to do that.

#Sentence Class
Sentence class has three properties, the charatercount, the sentence itself, and a HashMap of the words and the occurrences they took place.

Theres is the isPunctuation method that will take in a character and return true if it is one of the six punctiation.

Next I made a split method based off of the navive split method it utilizes some regex magic and a clever way to split strings using Pattern classes. Full implementation shown below.
```java
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
```
The last method I have in the sentence class getWordAndOccurrences will just split the sentence by spaces so all the words are seen, then remove the last character on each word if it is a puntuation. Finalls I change all the words to lowercase so that the word "word" and "Word" match up. Then I map the occurences to the second field on the hashmap.

#Client Class
There is a method to gatherInput using the Scanner. There are two methods regarding the loop to run the program again that are used in the main class. Then we have the makeTable method which makes the GUI table that pops up, all it does is take the list of words and occurences already made and turn it into a list. THen it is simply displayed on a JScrollPane with a JTable inside of it. This is added to a JFrame.
 

The next method is outputSentence for 