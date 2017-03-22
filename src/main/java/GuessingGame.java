import java.io.IOException;

public class GuessingGame {
    public static void main(String[] args) throws IOException, InterruptedException {
        Prompter prompter = new Prompter();
        Jar jar = prompter.setup();
        prompter.promptForGuess(jar);

    }
}
