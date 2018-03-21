import java.util.*;
import java.io.*;

public class WordCount3{

   public static void main(String[] args) throws IOException{
      //loading original bible file
      File bibleFile = new File("bible.txt");
      //scanner for original bible file
      Scanner scanner = new Scanner(bibleFile);
      //File to save entire bible file words to individual lines
      File outputFile = new File("outputFile.txt");
      PrintWriter printWriter = new PrintWriter(outputFile);
      String lineSeparator = System.getProperty("line.separator");
      while(scanner.hasNext()){
         String word = scanner.next();
         word.toLowerCase();
         StringBuilder wordAdd = new StringBuilder();

         //writing only words with characters, ignoring punctuation and spaces
         for(int i = 0; i<word.length(); i++){
            if(Character.isLetter(word.charAt(i))){
               wordAdd.append(word.charAt(i));
            }
         }
         printWriter.write(wordAdd.toString().toLowerCase() + " ");
         printWriter.println();

      }

      printWriter.close();
      /* 
       * We have now obtained every word of the original file in its lowercase
       * form on separate lines.
       */

      /* 
       * We will now create a TreeMap to efficiently store the strins and 
       * occurence values of these words
       */
      Scanner outputFileScanner = new Scanner(outputFile);
      ArrayList<String> words= new ArrayList<>();
      //storing the entire input file into string array
      while (outputFileScanner.hasNext()) {
         String a = outputFileScanner.next();
         words.add(a);
      }

      int totalWords = words.size();
      System.out.println(words.size());

      //TreeMap to store our Words and their count values
      //Map<Integer, String> map = new TreeMap<Integer, String>(Collections.reverseOrder());
      Map<String, Integer> map = new TreeMap<>();

      //if map does not contain word, add it. If it does, increment its word count
      for(int i = 0; i<words.size(); i++){
         String key = words.get(i).toLowerCase();
         if(key.length() > 0){
            if(!map.containsKey(key)){
               map.put(key,1);
            }
            else{
               int value = map.get(key);
               value++;
               map.put(key,value);
            }
         }
      }

      File finalFile = new File("FinalFile.txt");
      PrintWriter finalPrintWriter = new PrintWriter(finalFile);

      Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
      //     for(Map.Entry<String, Integer> entry: entrySet){
      //      System.out.println(entry.getKey() + "\t" + entry.getValue());
      // }


      ///////////////////////////////////////////////////////////////
      /*                                                            */
      /*               I DID NOT WRITE THIS LOWER PART              */
      /*                                                            */  
      /*                                                            */  
      /*                                                            */  
      /*                                                            */
      /*////////////////////////////////////////////////////////////*/
      ArrayList<WordOccurrence> list = new ArrayList<>();
      // Display key and value for each entry
      map.forEach((k, v) -> list.add(new WordOccurrence(k, v)));
      Collections.sort(list);
      list.forEach((word) -> finalPrintWriter.println(word.word + "\t" + word.count + 
      "\t" + (((double)word.count)/totalWords)));
      finalPrintWriter.close();

   }

   //http://soultionmanual.blogspot.com/2017/01/chapter-21-exercise-7-introduction-to.html
   //copied this part, don't claim credit for it

   private static class WordOccurrence implements Comparable<WordOccurrence> {

      int count;
      String word;

      public WordOccurrence(String word, int count) {
         this.word = word;
         this.count = count;
      }

      @Override
         public int compareTo(WordOccurrence o) {
            if (o.count > count)
               return 1;
            else if (o.count < count)
               return -1;
            else
               return 0;
         }
   }
}





