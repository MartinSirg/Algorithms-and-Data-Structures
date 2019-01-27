
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

/** Tests.
 * @author jaanus
 */
public class AnswerTest {

   String s = null;
   ArrayList<Integer> l = null;
   ArrayList<String> sl = null;
   ArrayList<Double> dl = null;

   @Test (timeout=1000)
   public void testReverseCase1() { 
      s = "AAAbbb";
      assertEquals (Aout.toString(s), "aaaBBB", Answer.reverseCase(s));
      s = "AaB,xX123";
      assertEquals (Aout.toString(s), "aAb,Xx123", Answer.reverseCase(s));
      s = "JÜriöÖülestõus1Ää";
      assertEquals (Aout.toString(s), "jüRIÖöÜLESTÕUS1äÄ", 
         Answer.reverseCase(s));
   } // test1

   @Test (timeout=1000)
   public void testCountWords2() {
      s = "four words are here";
      assertEquals (Aout.toString(s), 4, Answer.countWords(s));
      s = "three" + "\t" + "words here";
      assertEquals (Aout.toString(s), 3, Answer.countWords(s));
      s = "\t\ttwo\t   \t\there\t ";
      assertEquals (Aout.toString(s), 2, Answer.countWords(s));
   } // test2

   @Test (timeout=1000)
   public void testMaximum3() {
      l = new ArrayList<Integer> (Arrays.asList (new Integer[]{1}));
      assertEquals (l.toString(), Integer.valueOf(1), Answer.maximum (l));
      l = new ArrayList<Integer> (Arrays.asList (
         new Integer[]{1, -1, 0, -1, 2}));
      assertEquals (l.toString(), Integer.valueOf(2), Answer.maximum (l));
      l = new ArrayList<Integer> (Arrays.asList (
         new Integer[]{-1, -1, -10, -1, -2}));
      assertEquals (l.toString(), Integer.valueOf(-1), Answer.maximum (l));
   } // test3

   @Test (expected=RuntimeException.class)
   public void testMaximum4() {
      l = new ArrayList<Integer>();
      Answer.maximum (l);
   } // test4

   @Test (expected=RuntimeException.class)
   public void testMaximum5() {
      l = null;
      Answer.maximum (l);
   } // test5

   @Test (timeout=1000)
   public void testMaximum6() {
      sl = new ArrayList<String> (Arrays.asList (new String[]{"A"}));
      assertEquals (sl.toString(), "A", Answer.maximum (sl));
      dl = new ArrayList<Double> (Arrays.asList (
         new Double[]{1., -1., 0., -1., 2.}));
      assertEquals (dl.toString(), Double.valueOf(2.), Answer.maximum (dl));
      sl = new ArrayList<String> (Arrays.asList (
         new String[]{"A", "C", "B"}));
      assertEquals (sl.toString(), "C", Answer.maximum (sl));
   } // test6

   @Test (timeout=1000)
   public void testReverseList7() {
      dl = new ArrayList<Double> (Arrays.asList (
         new Double[]{1., -1., 0., -1., 2.}));
      ArrayList<Double> cdl = new ArrayList<Double>(dl); // copy
      Answer.reverseList (dl); // dl = 2 -1 0 -1 1
      assertEquals (cdl.toString(), new ArrayList<Double> (Arrays.asList (
         new Double[]{2., -1., 0., -1., 1.})), dl);
      sl = new ArrayList<String> (Arrays.asList (
         new String[]{"A", "C", "B"}));
      ArrayList<String> cl = new ArrayList<String>(sl); // copy
      Answer.reverseList (sl);   // sl = B C A
      assertEquals (cl.toString(), new ArrayList<String> (Arrays.asList (
         new String[]{"B", "C", "A"})), sl);
      sl = new ArrayList<String> (Arrays.asList (
         new String[]{"A", "B"}));
      cl = new ArrayList<String>(sl); // copy
      Answer.reverseList (sl);   // sl = B A
      assertEquals (cl.toString(), new ArrayList<String> (Arrays.asList (
         new String[]{"B", "A"})), sl);
      sl = new ArrayList<String> (Arrays.asList (
         new String[]{"A"}));
      cl = new ArrayList<String>(sl); // copy
      Answer.reverseList (sl);   // sl = A
      assertEquals (cl.toString(), new ArrayList<String> (Arrays.asList (
         new String[]{"A"})), sl);
   } // test7

   @Test (expected=RuntimeException.class)
   public void testReverseList8() {
      l = null;
      Answer.reverseList (l);
   } // test8

} // AnswerTest

