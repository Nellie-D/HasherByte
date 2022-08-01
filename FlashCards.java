import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
        String name = reader.nextLine();
        try {
            outputStream = new FileOutputStream(name+".text");
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

    public static void main(String [] args) throws FileNotFoundException, UnsupportedEncodingException {
        FlashCards firstSet = new FlashCards(keyOf, valuesOf, cardSet);
        int i = 0;
        while (i < 2){
            firstSet.setBuilder();
            i++;
        }
        firstSet.reviewTerms();
        firstSet.writeOut();
        System.out.println(cardSet);
        System.out.println("Hello");
    }
}
