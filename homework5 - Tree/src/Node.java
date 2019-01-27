
import java.rmi.UnexpectedException;
import java.util.*;

public class Node {

   private String name;
   private Node firstChild;
   private Node nextSibling;

   Node (String n, Node d, Node r) {
      // TODO!!! Your constructor here
      if(n.contains(" ")) throw new RuntimeException("Found a \" \" in a node's name.");
      if(n.contains(",")) throw new RuntimeException("Found a \",\" in a node's name.");
      if(n.contains("(") || n.contains(")")) throw new RuntimeException("Found a bracket in a node's name.");
      name = n;
      firstChild = d;
      nextSibling = r;
   }
   
   public static Node parsePostfix (String s) { // Kontrollib et juurtipul poleks naabrit
       Node root = parsePostfix2(s);
       if(root.nextSibling != null) throw new RuntimeException("Too many nodes at root level. Input: " + s);
       return root;
   }

   private static Node parsePostfix2 (String s) //Kõik töötab peale selle, et meetod laseb luua juurtipule naabreid
   {
       System.out.println("Parsing:" + s);
       if(s == null || s.trim().length() == 0 ) throw new RuntimeException("Null or empty input");
       if(s.contains(" ")) throw new RuntimeException("No whitespaces allowed in input: " + s);
       if(s.charAt(0) == ',') throw new RuntimeException("Too many commas in input: " + s);
       Node root = null;

       if(s.charAt(0) == '(') { // on ka child node'e. Teha kindlaks kas on ka neighbour
           StringBuilder nameSb = new StringBuilder();
           int currentIndex = 1;
           int bracketCounter = 1;
           for(char c: s.substring(1).toCharArray())
           {
               currentIndex++;

               if(c == '(') {
                   bracketCounter++;
                   continue;
               }
               if(c == ')') {
                   bracketCounter--;
                   continue;
               }
               if(c == ',' && bracketCounter == 0) { // Since there is a comma, there is a sibling
                   // make root from name, define child, define neighbour, return root
                   root = new Node(nameSb.toString(),null,null);
//                   System.out.print(nameSb + "[47]firstChild: ");
                   root.firstChild = parsePostfix2(s.substring(1, currentIndex - nameSb.length() - 2));
//                   System.out.print(nameSb + "[49]nextSibling: ");
                   root.nextSibling = parsePostfix2(s.substring(currentIndex));
                   return root;
               }
               if(bracketCounter == 0) {
                   nameSb.append(c);
               }
           }
           if(nameSb.length() > 0) { // make root, define child, no neigbour, return root. Example: (.....)NAME - >
               root = new Node(nameSb.toString(), null, null);
//               System.out.print(nameSb + "[59]firstChild: ");
               root.firstChild = parsePostfix2(s.substring(1, s.length() - nameSb.length()  - 1));
               return root;
           }else {
               throw new RuntimeException("Invalid input at: " + s);
           }

       }
       else { // Ei ole child node'i, ehk teha root ja koma puhul neighbour
           StringBuilder nameSb = new StringBuilder();
           int currentIndex = 0;
           for(char c: s.toCharArray()) {
               currentIndex++;
               if(c == ',') { // end of name
                   root = new Node(nameSb.toString(), null, null);
//                   System.out.print(nameSb + "[72]nextSibling: ");
                   root.nextSibling = parsePostfix2(s.substring(currentIndex));
                   return root;
               } else {
                   nameSb.append(c);
               }
           }
           //Ei leidnud koma, seega pole child ega siblingut
//           System.out.println("[80]returning leaf " + nameSb);
           return new Node(nameSb.toString(), null, null);
       }
   }

   public String leftParentheticRepresentation() {
       StringBuilder sb = new StringBuilder();
       sb.append(name);
       if(firstChild != null)   sb.append("(").append(firstChild.leftParentheticRepresentation()).append(")");
       if(nextSibling != null)  sb.append(",").append(nextSibling.leftParentheticRepresentation());
       return sb.toString();
   }

   public static void main (String[] param) {
       String s = "(((5,1)-,7)*,(6,3)/)+";
       String s3 = "(5,1)-,7";
       String s2 = "1";
       String s4 = "(B1,C)A";
       String s5 = "(B,(D,E)C)A";
       String s6 = "(B)A,(D)C";
       Node t = Node.parsePostfix (s6);
       System.out.println(t.leftParentheticRepresentation());
//       System.out.println("-------------------");
//       System.out.println("Input: " + s);
//       System.out.println(t);
//       System.out.println(t.firstChild);
//       System.out.println(t.firstChild.firstChild);
//       System.out.println(t.firstChild.nextSibling);
//       System.out.println(t.firstChild.nextSibling.firstChild);
//       System.out.println(t.firstChild.nextSibling.firstChild.nextSibling);
      //String v = t.leftParentheticRepresentation();
      //System.out.println (s + " ==> " + v); // (B1,C)A ==> A(B1,C)
   }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Node: ").append(name).append(" ");
        if(firstChild != null) sb.append("\t").append("FirstChild: ").append(firstChild.name);
        if(nextSibling!= null) sb.append("\t").append("NextSibling: ").append(nextSibling.name);
        return sb.toString();
    }
}

