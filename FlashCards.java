import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

// HasherBytes Flashcards//

public class FlashCards { 
    
    
    static String keyOf;
    static String valuesOf;
    static HashMap<String, String> cardSet = new HashMap<String, String>();
   
    


    public FlashCards(String keyA, String valueA, HashMap<String, String> cardA){
        keyOf = keyA;
        valuesOf = valueA;
        cardSet = cardA;
        
        
    }

    private Scanner reader = new Scanner(System.in);
    
    /*public void action(String chooseAction){
        System.out.println("Choose an action: ");
        
        chooseAction = reader.nextLine();
        

    }*/

    public HashMap<String, String> setBuilder(){
        System.out.println("What is the term?");
        String newKey = reader.nextLine();
        keyOf = newKey;
        System.out.println("What is the definition?");
        String newValue = reader.nextLine();
        valuesOf = newValue;
        cardSet.put(newKey, newValue);
        
        return cardSet;

    }

    public void reviewTerms(){
        
        for (String i : cardSet.keySet()){
            
            
            System.out.println("What is " + i);
            String askResult = reader.nextLine();
            if (cardSet.get(i).equals(askResult)){
                System.out.println("Correct!");
                
            }
            else {
                System.out.println("Sorry - that's incorrect.");
                
            }
        
        }
    }

    /**
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public void writeOut() throws FileNotFoundException, UnsupportedEncodingException{
        FileOutputStream outputStream = null;
        System.out.println("What is your set called?");
        String nameSet = reader.nextLine();
        try {
            outputStream = new FileOutputStream(nameSet+".txt");
            String fileContent = cardSet.toString();
            byte[] strToBytes = fileContent.getBytes();
            outputStream.write(strToBytes);
    }
        catch (IOException e) {
            System.out.print(e.getMessage());
        }
        finally {
            if (outputStream != null) {
                try { 
                    outputStream.close();
                }
                catch (IOException e){
                    System.out.print(e.getMessage());
                }
            }
        }
        
    }

    public void readIn() throws Exception{
        System.out.println("What is your file's name?");
        String nameMe = reader.nextLine() + ".txt";
        

        Path pathing = Paths.get(nameMe);
        String myPath = pathing.toString();
        FileReader takeIn = new FileReader(myPath);
        int i;
        while ((i = takeIn.read()) != -1){
            System.out.print((char)i);
        }
        takeIn.close();
    }



    public static void main(String [] args) throws Exception {
        FlashCards firstSet = new FlashCards(keyOf, valuesOf, cardSet);
        int i = 0;
        String A = "A";
        String B = "B";
        System.out.println("Please choose to build (A) or to review a previous set (B)");
        Scanner mainRead = new Scanner(System.in);
        String chooseAction = mainRead.nextLine();
        if (chooseAction.equals(A)){
            while (i < 3){
                firstSet.setBuilder();
                i++;
            }
            firstSet.writeOut();
        }
        else if (chooseAction.equals(B)){
            firstSet.readIn();
            firstSet.reviewTerms();
        }
        else {
            System.out.println("Not a valid choice.");
        }
        mainRead.close();
        }
        
        //firstSet.reviewTerms();
        //firstSet.writeOut();
        //System.out.println(cardSet);
       // firstSet.readIn();
       

    }

