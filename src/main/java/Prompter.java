import com.sun.xml.internal.fastinfoset.util.CharArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Prompter {
    private BufferedReader mReader;

    public Prompter(){
        mReader = new BufferedReader(new InputStreamReader(System.in));
    }


    public void promptForGuess (Jar jar) throws IOException {
        boolean rightAnswer = false;
        int numberOfGuesses = 0;
        String guess;
        System.out.println("\nPLAYER");
        System.out.println("==================");
        System.out.println("How many " + jar.getItemName() + " are in the jar? Pick a number between 1 and " + jar.getMaxItems());
        try {
            do {
                do {
                System.out.print("Guess: ");
                guess = mReader.readLine();
                } while (!isInteger(guess));

                if(guess == null || guess.equals("")) {
                    System.out.println("Please enter a response.");
                    continue;
                }
                if(Integer.parseInt(guess) == 0){
                    System.out.println("Guess cannot be 0");
                    continue;
                }
                if(Integer.parseInt(guess) < 0){
                    System.out.println("Guess cannot be negative");
                    continue;
                }
                if(Integer.parseInt(guess) > jar.getMaxItems()){
                    System.out.println("Your guess must be less than " + jar.getMaxItems());
                    continue;
                }
                numberOfGuesses++;
                if (Integer.parseInt(guess) > jar.getAmount()){
                    System.out.println("Your guess is too high.");
                }
                else if (Integer.parseInt(guess) < jar.getAmount()){
                    System.out.println("Your guess is too low.");
                }
                else {
                    rightAnswer = true;
                    String attempt;
                    if(jar.getAmount() == 1){
                        attempt = "attempt";
                    }
                    else{
                        attempt = "attempts";
                    }
                    System.out.println("You got it in " + numberOfGuesses + " " + attempt + "!");
                }

            } while (!rightAnswer);
        }catch (IOException ioe){
            System.out.println("There was a problem prompting for guess");
            ioe.printStackTrace();
            System.exit(0);
        }

    }

    public Jar setup() throws IOException {
        System.out.println("\nADMINISTRATOR SETUP");
        System.out.println("======================");
        String item;
        do {
            System.out.print("What type of item should fill the jar? ");
            item = mReader.readLine();
            if (item == null || item.equals("")) {
                System.out.println("Please enter an item");
            }
        } while (item == null || item.equals(""));
        boolean valid;
        int maxItems;
        do {
            String input;
            do {
                System.out.print("What is the maximum amount of " + item + "? ");
                input = mReader.readLine();
                if ((input == null) || input.equals("")) {
                    System.out.println("Please enter a maximum amount of " + item + ".");
                }
            } while (input == null || input.equals("") || !isInteger(input));

            maxItems = Integer.parseInt(input);
            if (maxItems <= 0) {
                System.out.println(maxItems + " is not a valid maximum amount.");
                valid = false;
            } else {
                valid = true;
            }
        } while (!valid);

        return new Jar(item, maxItems);

    }

    public static boolean isInteger(String str){
        try {
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException nfe){
            System.out.println(str.substring(0,1).toUpperCase() + str.substring(1) + " is not an integer. Please enter an integer.");
            return false;
        }
    }

}
