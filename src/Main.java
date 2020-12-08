import java.util.function.BooleanSupplier;

public class Main {

    private static final Client client = new Client();
    private static final BooleanSupplier runAgain = client::isRunAgain;

    public static void main(String[] args) {
        while (runAgain.getAsBoolean()) {
            String input = client.gatherInput();
            Sentence sentence = new Sentence(input);
            client.outputSentence(sentence);
            client.makeTable(sentence);
            if (!client.runAgain()) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
        }
    }
}

