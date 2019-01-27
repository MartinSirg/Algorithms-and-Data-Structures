/** Arc represents one arrow in the graph. Two-directional edges are
 //    * represented by two Arc objects (for both directions).
 //    */
   class Arc {

      String id;
      Vertex target;
      Arc next;
      int info = 0;

      Arc (String s, Vertex v, Arc a) {
         id = s;
         target = v;
         next = a;
      }

      Arc (String s) {
         this (s, null, null);
      }

      @Override
      public String toString() {
         return id;
      }
   }