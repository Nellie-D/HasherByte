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
            System.out.println("Type " + cardSet.get(i));
            String askResult = reader.nextLine();
            //System.out.println(askResult);
            //String result = cardSet.get(i);
            
        
            if (cardSet.get(i).equals(askResult)){
                System.out.println("Correct!");
                
            }
            else {
                System.out.println("Sorry - that's incorrect.");
                
            }
        
        }
    }

    public static void main(String [] args) {
        FlashCards firstSet = new FlashCards(keyOf, valuesOf, cardSet);
        int i = 0;
        while (i < 2){
            firstSet.setBuilder();
            i++;
        }
        firstSet.reviewTerms();
        System.out.println(cardSet);
        System.out.println("Hello");
    }
}
