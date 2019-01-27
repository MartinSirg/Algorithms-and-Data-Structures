import java.util.*;
import java.util.stream.Collectors;

/**
 * Prefix codes and Huffman tree.
 * Tree depends on source data.
 */
public class Huffman{


   // TODO!!! Your instance variables here!
   Node root;
   HashMap<Byte, String> codingTable;
   String encoded;
   String original;
   /** Constructor to build the Huffman code for a given bytearray.
    * @param original source data
    */
   Huffman (byte[] original) {
      // TODO!!! Your constructor here!
      List<Node> frequencies = new ArrayList<>();

      for(byte b: original){

         boolean isPresent = frequencies.stream().anyMatch(node -> node.getByteValue() == b);
         if(!isPresent){
            frequencies.add(new Node(b,1));
         } else {
            Node node = frequencies
                    .stream()
                    .filter(node1 -> node1.getByteValue() == b)
                    .collect(Collectors.toList())
                    .get(0);
            node.increaseFrequency();
         }
      }
      List<Node> allBytes = new ArrayList<>(frequencies);

//      frequencies.forEach((node) -> System.out.println(new String(new byte[]{node.getByteValue()}) + ":" + node.getFrequency()));
      Optional<Node> minimum = frequencies.stream().min(Huffman::compare);

      if(frequencies.size() == 1) {
         Node onlyNode = frequencies.get(0);

         root = new Node(onlyNode.getFrequency());
         root.setLeft(onlyNode);
         onlyNode.setParent(root);
      } else {
         while(minimum.isPresent()){

            Node node1 = minimum.get();
            frequencies.remove(node1);
//            System.out.println( "Chose: " + new String(new byte[]{node1.getByteValue()})+ " : " + node1.getFrequency());
            minimum = frequencies.stream().min(Huffman::compare);
            if(!minimum.isPresent()){
               root = node1;
               break;
            }

            Node node2 = minimum.get();
            frequencies.remove(node2);

//            System.out.println("Chose: " + new String(new byte[]{node2.getByteValue()})+ " : " + node2.getFrequency());
//            System.out.println("-------------------");

            Node rootOfTwo = new Node(node1.getFrequency() + node2.getFrequency());
            boolean bool = node1.getFrequency() > node2.getFrequency();
            rootOfTwo.setLeft(bool ? node1 : node2);
            rootOfTwo.setRight(bool ? node2 : node1);
            node1.setParent(rootOfTwo);
            node2.setParent(rootOfTwo);

            frequencies.add(rootOfTwo);
            minimum = frequencies.stream().min(Huffman::compare);
//            frequencies.forEach((node) -> System.out.println(new String(new byte[]{node.getByteValue()}) + ":" + node.getFrequency()));
         }
      }

      HashMap<Byte, String> codes = new HashMap<>();

      allBytes.forEach(node -> codes.put(node.getByteValue(), node.goToParent()));
//      codes.forEach((aByte, s) -> System.out.println(new String(new byte[]{aByte}) + "(" + aByte + ") : " + s));
      codingTable = codes;

      this.original = new String(original);
      StringBuilder sb = new StringBuilder();
      for(byte b: original) sb.append(codingTable.get(b));
//      System.out.println(sb);
      encoded = sb.toString();
//      System.out.println("-----End of Constructor-------");
   }


   private static int compare(Node o1, Node o2) {
      if(o1.getFrequency() == o2.getFrequency()) return 0;
      if(o1.getFrequency() > o2.getFrequency()) return 1;
      return -1;
   }

   /** Length of encoded data in bits. 
    * @return number of bits
    */
   public int bitLength() {
      return encoded.length(); // TODO!!!
   }


   /** Encoding the byte array using this prefixcode.
    * @param origData original data
    * @return encoded data
    */
   public byte[] encode (byte [] origData) {
      StringBuilder sb = new StringBuilder();
      int bytesAmount = bitLength() % 8 == 0 ? bitLength() / 8 : (bitLength() / 8) + 1;
      byte[] result = new byte[bytesAmount];

      for(byte b: origData) sb.append(codingTable.get(b));
      String bits = sb.toString();
//      System.out.println(bits);

      for(int i = 0; i < bytesAmount; i++) {
//         System.out.println("Current " + i);
         if((i*8) + 8 > bits.length()) {
            String paddedByte = bits.substring(i*8);
            for(int j = 0; j < 8 - bits.length() % 8; j++) paddedByte += "0";
//            System.out.println("Added padded " + paddedByte);
            result[i] = (byte) Integer.parseInt(paddedByte, 2);
         } else {
//            System.out.println("Adding: " + bits.substring(i * 8, (i * 8) + 8));
            result[i] = (byte) Integer.parseInt(bits.substring(i * 8, (i * 8) + 8), 2);
         }
      }

//      for(byte b: result) System.out.print(b + " ");
//      System.out.println();
//      for(byte b: result) System.out.print(String.format("%8s",Integer.toBinaryString(b & 0xFF)).replace(" ", "0") + " ");
//      System.out.println();

      return result;
   }

   /** Decoding the byte array using this prefixcode.
    * @param encodedData encoded data
    * @return decoded data (hopefully identical to original)
    */
   public byte[] decode (byte[] encodedData) {
      StringBuilder sb = new StringBuilder();
//      System.out.println("Decoding");
      for(byte b: encodedData) {
         sb.append(String.format("%8s",Integer.toBinaryString(b & 0xFF)).replace(" ", "0"));
         //https://stackoverflow.com/a/12310078
      }


      String bits = sb.toString().substring(0, bitLength());
//      System.out.println(bits);

      if(encodedData.length == 0) return encodedData;


      List<Byte> result = new ArrayList<>();
      Node lastNode = root;
      StringBuilder currentCode = new StringBuilder();

      for(char bit: bits.toCharArray()){
         if(bit == '1') lastNode = lastNode.getRight();
         else if(bit == '0') lastNode = lastNode.getLeft();
         else {
            throw new IllegalArgumentException("Unknown byte in encoded data: " + bit + ". Input:" + new String(encodedData));
         }

         if(lastNode.hasByteValue()){
            result.add(lastNode.getByteValue());
            lastNode = root;
         }
      }
      byte[] finalResult = new byte[result.size()];
      for(int i = 0; i < result.size(); i++) {
         finalResult[i] = result.get(i);
      }
      return finalResult;
   }

   /** Main method. */
   public static void main (String[] params) {
      String tekst = "AAAAAAAAAAAAABBBBBBCCCDDEEF";
      byte[] orig = tekst.getBytes();
      Huffman huf = new Huffman (orig);
      int lngth = huf.bitLength();
      System.out.println ("Length of encoded data in bits: " + lngth);
      byte[] kood = huf.encode (orig);
      byte[] orig2 = huf.decode (kood);
//       must be equal: orig, orig2
      for(byte b: orig) System.out.print(b);
      System.out.println();


      for(byte b: kood) System.out.print(b);
      System.out.println();

      for(byte b: orig2) System.out.print(b);
      System.out.println();

      System.out.println (Arrays.equals (orig, orig2));
//      // TODO!!! Your tests here!

//      String s = "Osasõne on saadud antud sõnest (võib-olla) migite sümbolite väljajätmise teel. Iga sõne osasõnedeks on tühisõne ja see sõne ise, kokku on variante halvimal juhul 2n, kus n on sõne pikkus (halvim juht realiseerub näiteks siis, kui kõik sümbolid on erinevad).";
//      String s2 = "babcddabbbaa";
//      String s3 = "bascrftcwgdyaqvhujb";
//      Huffman huff = new Huffman(s2.getBytes());
//      var encoded = huff.encode(s2.getBytes());
//      var decoded = huff.decode(encoded);
//      for(byte b: s2.getBytes()) System.out.print(b + " ");
//      System.out.println();
//
//      for(byte b: decoded) System.out.print(b + " ");
//      System.out.println();
   }
}

