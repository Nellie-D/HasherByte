
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
        BufferedWriter writer = null;
        
        System.out.println("What is your set called?");
        String nameSet = reader.nextLine();
       
        try {
            writer = new BufferedWriter(new FileWriter(nameSet+".txt"));
            for (Map.Entry<String, String> entry : cardSet.entrySet()) {
                            
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
            writer.flush();

            //String fileContent = cardSet.toString();
            // come back here and make sure you iterate through this to print these
            // separately, without the curly braces so that it can be easily read back and
            // iterated through during the read in
            //byte[] strToBytes = fileContent.getBytes();
            //outputStream.write(strToBytes);
         
            
        }
    
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
                try { 
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
        //FileReader takeIn = new FileReader(myPath);
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

   

    // https://www.geeksforgeeks.org/reading-text-file-into-java-hashmap/
                //System.out.println(takeIn.readLine());
                
               /*  for (int i = 1; takeIn.read() != -1; i++){
                    System.out.print((char)i);
                    String mapper = String.valueOf((char)i);
                    System.out.println(mapper);
                    cardSet.put(String.valueOf((char)i), String.valueOf((char)i-1));
                    System.out.println(cardSet);

                } */ // warning: the above code prints asciimath as the hashmap
                //takeIn.close();
                return cardSet;
            }


  
    public static void main(String [] args) throws Exception {
        FlashCardMain firstSet = new FlashCardMain(keyOf, valuesOf, cardSet);
        int i = 0;
        String A = "A";
        String a = "a";

        String B = "B";
        String b = "b";
       
      
        Scanner choiceReader = new Scanner(System.in);
        Scanner loopReader = new Scanner(System.in);
        Scanner mainReader = new Scanner(System.in);
       
        String chooseAction;
        System.out.println("Press 0 to begin: ");
        int j = loopReader.nextInt();
        while (j == 0){
            
            System.out.println("Please choose to build (A) or to review a previous set (B)");
            chooseAction = choiceReader.nextLine();
            if (chooseAction.equals(A) || chooseAction.equals(a)){
                int f;
                System.out.println("How many terms are in this set?");
                f = mainReader.nextInt();
                while (i < f){
                    firstSet.setBuilder();
                    i++; 
                }
                firstSet.writeOut(); 
            }
            else if (chooseAction.equals(B) || chooseAction.equals(b)){
                firstSet.readIn();
                firstSet.reviewTerms();
            }
            else /*(!chooseAction.equals(A) || !chooseAction.equals(a) || !chooseAction.equals(B) || !chooseAction.equals(b)) */{
                System.out.println(" last else Not a valid input: ");       
                System.out.println("Do you want to continue? Press 0 to exit: ");
                j = loopReader.nextInt();        
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

