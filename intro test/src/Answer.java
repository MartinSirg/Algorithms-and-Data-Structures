import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Answer {

   public static void main (String[] param) {
       Random random = new Random();
      // conversion double -> String
       double exampleDouble = random.nextDouble();
       String convertedDouble = String.valueOf(exampleDouble);
       System.out.println(convertedDouble);

      // conversion String -> int
       String exampleIntString = "17";
       int parsedInt = Integer.parseInt(exampleIntString);
       System.out.println(parsedInt);

      // "hh:mm:ss"

       LocalDateTime currentTime = LocalDateTime.now();
       System.out.println("Current time is " + currentTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

      // cos 45 deg
       System.out.println("Cos 45 deg is " + Math.cos(Math.toRadians(45)));
      // table of square roots
       for (int i = 0; i <= 100; i+= 5) {
           System.out.println("Square root of " + i + " is " + Math.sqrt(i));
       }

      String firstString = "ABcd12";
      String result = reverseCase(firstString);
      System.out.println("\"" + firstString + "\" -> \"" + result + "\"");

      // reverse string

      String s = "How  many	 words   here";
      int nw = countWords(s);
      System.out.println(s + "\t" + String.valueOf(nw));

      // pause. COMMENT IT OUT BEFORE JUNIT-TESTING!

       long t1 = System.nanoTime();
       try {
           Thread.sleep(3000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       long t2 = System.nanoTime();

       System.out.println("Ajavahe on " + (t2 - t1));

       final int LSIZE = 100;
      ArrayList<Integer> randList = new ArrayList<> (LSIZE);
      Random generaator = new Random();
      for (int i=0; i<LSIZE; i++) {
         randList.add(generaator.nextInt(1000));
      }

      // minimal element

       System.out.println(Collections.min(randList));

      // HashMap tasks:
      //    create
       Map<String, String> ained = new HashMap<>();
       ained.put("icd0001" , "Algoritmid");
       ained.put("iti0101", "Proge algkursus");
       ained.put("iti0102", "Proge põhikursus");
      //    print all keys
       System.out.println(ained.keySet());
       //    remove a key
       ained.remove("iti0101");
       //    print all pairs
       for (Map.Entry entry: ained.entrySet()) {
           System.out.println(entry.getKey() + " - " + entry.getValue() );
       }

      System.out.println ("Before reverse:  " + randList);
      reverseList(randList);
      System.out.println("After reverse: " + randList);

      System.out.println("Maximum: " + maximum(randList));
   }

   /** Finding the maximal element.
    * @param a Collection of Comparable elements
    * @return maximal element.
    * @throws NoSuchElementException if <code> a </code> is empty.
    */
   static public <T extends Object & Comparable<? super T>>
         T maximum (Collection<? extends T> a) 
            throws NoSuchElementException{
      return Collections.max(a);
   }

   /** Counting the number of words. Any number of any kind of
    * whitespace symbols between words is allowed.
    * @param text text
    * @return number of words in the text
    */
   public static int countWords(String text){
       //annab teha ka string tokenizeriga
       return new StringTokenizer(text).countTokens();
       //return text.trim().split("\\s+").length;
   }

   /** Case-reverse. Upper -> lower AND lower -> upper.
    * @param s string
    * @return processed string
    */
   public static String reverseCase (String s) {
      StringBuilder sb = new StringBuilder();
      for(Character c : s.toCharArray()) {
          if (Character.isUpperCase(c)) {
              sb.append(Character.toLowerCase(c));
          } else {
              sb.append(Character.toUpperCase(c));
          }
      }
      return sb.toString();
   }

   /** List reverse. Do not create a new list.
    * @param list list to reverse
    */
   public static <T extends Object> void reverseList (List<T> list)
      throws UnsupportedOperationException {
         Collections.reverse(list);
   }
}
