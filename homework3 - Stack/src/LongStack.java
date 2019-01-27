import java.util.LinkedList;
import java.util.StringTokenizer;

public class LongStack implements Cloneable {
    private LinkedList<Long> stack;

   public static void main (String[] argum) {
//       LongStack longStack = new LongStack();
//       System.out.println(longStack.toString());
//       longStack.push(1);
//       longStack.push(2);
//       longStack.push(3);
//       longStack.pop();
//       System.out.println(longStack.toString());
       interpret("4 -");
   }

   LongStack() {
       stack = new LinkedList<>();
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
       super.clone();
       LongStack copy = new LongStack();
       if(stack.isEmpty()) return copy;
       for(int i = stack.size() - 1; i >= 0; i--) {
           copy.push(stack.get(i));
       }
       return copy;
   }

   public boolean stEmpty() {
       return stack.isEmpty();
   }

   public void push (long a) {
       stack.push(a);
   }

   public long pop() {
       if(stEmpty()) throw new RuntimeException("Not enough operands in statement.");
       return stack.pop();
   }

   public void op (String s) {
       if(stack.size() < 2) throw new RuntimeException("Not enough operators left for the operation.");
       switch(s) {
           case "+":
               push(pop() + pop());
               break;
           case "-":
               push(-pop() + pop());
               break;
           case "*":
               push(pop() * pop());
               break;
           case "/":
               long b = pop(), a = pop();
               push(a / b);
               break;
           default:
               throw new RuntimeException("Invalid operator: \"" + s + "\".");
       }
   }
  
   public long tos() {
      if(stEmpty()) throw new RuntimeException("No elements left.");
      return stack.element();
   }

   @Override
   public boolean equals (Object o) {
       if(o.getClass().equals(this.getClass())) return stack.equals(((LongStack) o).getStack());
       return false;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      for(int i = stack.size() - 1; i >= 0; i--) {
          sb.append(stack.get(i));
          if(i != 0) sb.append(" ");
      }
      return sb.toString();
   }

   public static long interpret (String pol) {

       LongStack stack = new LongStack();
       StringTokenizer tokenizer = new StringTokenizer(pol);
       StringBuilder operationSoFar = new StringBuilder();

       while(tokenizer.hasMoreTokens()) {
           String token = tokenizer.nextToken();
           operationSoFar.append(token);

           try {
               stack.push(Long.valueOf(token));
           } catch(NumberFormatException e) {
               try{
                   stack.op(token);
               } catch(RuntimeException ex) {
                   throw new RuntimeException(ex.getMessage() + " At: " + operationSoFar);
               }
           }
           operationSoFar.append(" ");
       }
       long result = stack.pop();
       if(!stack.stEmpty()) throw new RuntimeException("After all calculations, there is more than one operand left. At: " + operationSoFar);
       return result;
   }

    public LinkedList<Long> getStack() {
        return stack;
    }
}