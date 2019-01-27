import java.util.ArrayList;
import java.util.List;

/**
 * Vertex is a node in a graph.
 * String id = Name of the node
 * Vertex next = next node in the graph(doesnt have to be connected to it with an arc)
 * Arc first = first arc originating from this node
 */
class Vertex {

      String id;
      Vertex next;
      Arc first;
      int info = 0;

      Vertex (String s, Vertex v, Arc e) {
         id = s;
         next = v;
         first = e;
      }

      Vertex (String s) {
         this (s, null, null);
      }

      @Override
      public String toString() {
         return id;
      }

      /**
       * Returns a list of Vertexes, that are connected to eachother.
       * Vertexes are connected with the next parameter in the vertex class.
       * Last element's next will be null.
       * @return List<Vertex>
       */
      public List<Vertex> getThisAndSubsecuentVertexes() {
         List<Vertex> vertexes = new ArrayList<>();
         Vertex currentVert = this;
         while(true) {
            vertexes.add(currentVert);
            if(currentVert.next == null) break;
            currentVert = currentVert.next;
         }
         return vertexes;
      }

      /**
       * Returns a list of arcs that originate from this vertex
       * If vertex doesnt have any outgoing arcs, then an empty list is returned
       * Arcs will be in the order they were referenced from the originating vertex
       * @return List<Arc>
       */
      public List<Arc> getAllOutgoingArcs(){
         List<Arc> vertexArcs = new ArrayList<>();

         if(this.first == null) {
            return vertexArcs;
         }

         Arc currentArc = this.first;
         while(true){
            vertexArcs.add(currentArc);
            if(currentArc.next == null) break ;
            currentArc = currentArc.next;
         }

         return vertexArcs;
      }
   }