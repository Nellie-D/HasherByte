
import java.io.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

// HasherBytes Flashcards//

public class FlashCardMain { 
    
    
    static String keyOf;
    static String valuesOf;
    static HashMap<String, String> cardSet = new HashMap<String, String>();
   
    


    public FlashCardMain(String keyA, String valueA, HashMap<String, String> cardA){
        keyOf = keyA;
        valuesOf = valueA;
        cardSet = cardA;
  
        
    }

    private Scanner reader = new Scanner(System.in);

    public HashMap<String, String> setBuilder(){
        String DONE = "DONE";
        boolean go = false;
        System.out.println("Type DONE at any point when finished");
    
        while (!go){
            System.out.println("What is the term?");
            String newKey = reader.nextLine();
            if (newKey.equals(DONE)){
                go = true;
            }
            else {
                keyOf = newKey;
                System.out.println("What is the definition?");
                String newValue = reader.nextLine();
                if (newValue.equals(DONE)){
                    go = true;
                }
                else {
                    valuesOf = newValue;
                    
                }
                cardSet.put(newKey, newValue);
            }
          
            
            
        }
        
        return cardSet;

    }

    public void reviewTerms(){
        double correctIter = 0;
        double incorrectIter = 0;
        for (String i : cardSet.keySet()){
            
            
            System.out.println("What is " + i);
            String askResult = reader.nextLine();
            if (cardSet.get(i).equals(askResult)){
                System.out.println("Correct!");
                correctIter++;
            }
            else {
                System.out.println("Sorry - that's incorrect.");
                incorrectIter++;
            }
            
        }
        double gradePercent = correctIter / cardSet.size();
        System.out.print("You got " + correctIter + " terms correct, ");
        System.out.println("and " + incorrectIter + " terms incorrect.");

        System.out.println("Grade = " + gradePercent*100 + "%");
        cardSet.clear();
    }

    /**
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public void writeOut() throws FileNotFoundException, UnsupportedEncodingException{
        BufferedWriter writer = null;
        
        System.out.println("What is your set called?");
        String nameSet = reader.nextLine();
       
        try {
            writer = new BufferedWriter(new FileWriter(nameSet+".txt"));
            for (Map.Entry<String, String> entry : cardSet.entrySet()) {
                            
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
            cardSet.clear();
            //clearing the cardSet is meant to ensure that values created before a review session
            // do not unintentionally contaminate a review file by adding to the hashmap
            writer.flush();
            writer.close();


        }
    
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
                try { 
                    writer.flush();
                    writer.close();
                }
                catch (Exception e){
                    System.out.print(e.getMessage());
                }
            }
        }
        
    
    public HashMap<String, String> readIn() throws Exception{
        System.out.println("What is your file's name?");
        String nameMe = reader.nextLine() + ".txt";
     
        Path pathing = Paths.get(nameMe);
        String myPath = pathing.toString();
        BufferedReader takeIn = new BufferedReader(new FileReader(myPath));
  
        // create BufferedReader object from the File
    
        try {

        // read file line by line
        
        String line = null;
        while ((line = takeIn.readLine()) != null) {

            // split the line by :
            String[] pair = line.split(":");

            // first part is name, second is number
            String term = pair[0].trim();
            String definition = pair[1].trim();

            // put name, number in HashMap if they are
            // not empty
            if (!term.equals("") && !definition.equals(""))
                cardSet.put(term, definition);
        }
        }
    catch (Exception e) {
        e.printStackTrace();
    }
    finally {

        // Always close the BufferedReader
        if (takeIn != null) {
            try {
                takeIn.close();
            }
            catch (Exception e) {
            };
        }
    }
                takeIn.close();
                return cardSet;
            }


  
    public static void main(String [] args) throws Exception {
        FlashCardMain firstSet = new FlashCardMain(keyOf, valuesOf, cardSet);
        
        String A = "A";
        String a = "a";

        String B = "B";
        String b = "b";
        String E = "END";
      
        Scanner choiceReader = new Scanner(System.in);
        Scanner loopReader = new Scanner(System.in);
        Scanner mainReader = new Scanner(System.in);
       
        String chooseAction;
        System.out.println("Type END to exit after/before reviewing/building a set. ");
        int j = 0;
        
     
        while (j == 0) {
            System.out.println("Please choose to build (A) or to review a previous set (B)");
        
            
            chooseAction = choiceReader.nextLine();

            if (chooseAction.equals(A) || chooseAction.equals(a)){
                
               
                firstSet.setBuilder();
                
                firstSet.writeOut();
                 
            }

            else if (chooseAction.equals(B) || chooseAction.equals(b)){
                
                firstSet.readIn();
                firstSet.reviewTerms();

            }
            else {
                                
                if (chooseAction.equals(E)){
                    j++;
                }
    
            }
        
            
        }
            mainReader.close();
            choiceReader.close();
            loopReader.close();
    }
    
        //firstSet.reviewTerms();
        //firstSet.writeOut();
        //System.out.println(cardSet);
       // firstSet.readIn();
       

    }

