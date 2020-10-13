import java.util.InputMismatchException;
import java.util.Scanner;

public class AutoComplete {

    public static void main(String [] args){

        AutocompleteProvider provider = new AutocompleteProvider();

        boolean quit = false; 

        System.out.println("Welcome to the AutoComplete Application.");

        //Central loop of the app that ends when the user quit == false, meaning user has decided to quit
        do{

            System.out.println("What would you like to do?");

            Scanner scan = new Scanner(System.in);
            int select = 0;

            boolean isInteger = false;

            //If the user doesn't enter an int then catch the error and tell them do so
            while(!isInteger){
    
                try{

                    System.out.println("[1] - Train");
                    System.out.println("[2] - See Autocomplete Candidates");
                    System.out.println("[3] - Exit");

                    select = scan.nextInt();
                    scan.nextLine();
                    isInteger = true;

                }
                catch(InputMismatchException e){

                    System.out.println("Enter a number.");
                    scan.nextLine();

                }

                
            }

            //Do whatever menu option the user chose, if not 1-3, just quit
            switch(select){

                case 1:                 
                    System.out.println("Type a message to use to train...");
                    String trainInput = scan.nextLine();
                    provider.train(trainInput.toLowerCase());
                    break;

                case 2:
                    System.out.println("Type an input to see autocomplete suggestions...");
                    String candidateInput = scan.nextLine();
                    provider.printCandidates(provider.getWords(candidateInput.toLowerCase()));
                    break;

                case 3:
                    quit = true;
                    scan.close();
                    break;

                default:
                    quit = true;
                    scan.close();
                    break;

            }
        }

        while(quit == false);

    }

}

