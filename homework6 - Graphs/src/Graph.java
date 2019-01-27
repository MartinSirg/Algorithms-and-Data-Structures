import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Graph {
    String id;
    Vertex first;
    int info = 0;

    public static void main(String[] args) {
        Graph graph = new Graph("G");
        graph.createRandomSimpleGraph(6, 15);
        Graph clone = graph.deepGraphClone();
        System.out.println(clone);
    }

    Graph (String s, Vertex v) {
        id = s;
        first = v;
    }

    Graph (String s) {
        this (s, null);
    }

    @Override
    public String toString() {
         String nl = System.getProperty ("line.separator");
         StringBuffer sb = new StringBuffer (nl);
         sb.append (id);
         sb.append (nl);
         Vertex v = first;
         while (v != null) {
            sb.append (v.toString());
            sb.append (" -->");
            Arc a = v.first;
            while (a != null) {
               sb.append (" ");
               sb.append (a.toString());
               sb.append (" (");
               sb.append (v.toString());
               sb.append ("->");
               sb.append (a.target.toString());
               sb.append (")");
               a = a.next;
            }
            sb.append (nl);
            v = v.next;
         }
         return sb.toString();
      }

    public Vertex createVertex (String vid) {
        Vertex res = new Vertex (vid);
        res.next = first;
        first = res;
        return res;
    }

    public Arc createArc (String aid, Vertex from, Vertex to) {
        Arc res = new Arc (aid);
        res.next = from.first;
        from.first = res;
        res.target = to;
        return res;
    }

    /**
       * Create a connected undirected random tree with n vertices.
       * Each new vertex is connected to some random existing vertex.
       * @param n number of vertices added to this graph
       */
    public void createRandomTree (int n) {
         if (n <= 0)
            return;
         Vertex[] varray = new Vertex [n];
         for (int i = 0; i < n; i++) {
            varray [i] = createVertex ("v" + (n - i));
            if (i > 0) {
               int vnr = (int)(Math.random()*i);
               createArc ("a" + varray [vnr].toString() + "_"
                  + varray [i].toString(), varray [vnr], varray [i]);
               createArc ("a" + varray [i].toString() + "_"
                  + varray [vnr].toString(), varray [i], varray [vnr]);
            } else {}
         }
      }

    /**
       * Create an adjacency matrix of this graph.
       * Side effect: corrupts info fields in the graph
       * @return adjacency matrix
       */
    public int[][] createAdjMatrix() {
         info = 0;
         Vertex v = first;
         while (v != null) {
            v.info = info++;
            v = v.next;
         }
         int[][] res = new int [info][info];
         v = first;
         while (v != null) {
            int i = v.info;
            Arc a = v.first;
            while (a != null) {
               int j = a.target.info;
               res [i][j]++;
               a = a.next;
            }
            v = v.next;
         }
         return res;
      }

    /**
       * Create a connected simple (undirected, no loops, no multiple
       * arcs) random graph with n vertices and m edges.
       * @param n number of vertices
       * @param m number of edges
       */
    public void createRandomSimpleGraph (int n, int m) {
         if (n <= 0)
            return;
         if (n > 2500)
            throw new IllegalArgumentException ("Too many vertices: " + n);
         if (m < n-1 || m > n*(n-1)/2)
            throw new IllegalArgumentException
               ("Impossible number of edges: " + m);
         first = null;
         createRandomTree (n);       // n-1 edges created here
         Vertex[] vert = new Vertex [n];
         Vertex v = first;
         int c = 0;
         while (v != null) {
            vert[c++] = v;
            v = v.next;
         }
         int[][] connected = createAdjMatrix();
         int edgeCount = m - n + 1;  // remaining edges
         while (edgeCount > 0) {
            int i = (int)(Math.random()*n);  // random source
            int j = (int)(Math.random()*n);  // random target
            if (i==j)
               continue;  // no loops
            if (connected [i][j] != 0 || connected [j][i] != 0)
               continue;  // no multiple edges
            Vertex vi = vert [i];
            Vertex vj = vert [j];
            createArc ("a" + vi.toString() + "_" + vj.toString(), vi, vj);
            connected [i][j] = 1;
            createArc ("a" + vj.toString() + "_" + vi.toString(), vj, vi);
            connected [j][i] = 1;
            edgeCount--;  // a new edge happily created
         }
      }

    /**
     * Returns a clone of the graph, where all parts of the graph are independent from the original
     * @return Graph
     */
    public Graph deepGraphClone() {
         if(this.first == null) return new Graph(id);

         List<Vertex> originalVertexes = first.getThisAndSubsecuentVertexes();
         List<Vertex> clonedVertexes = new ArrayList<>(originalVertexes.size());
         HashMap<Vertex, List<Arc>> originalArcs = new HashMap<>();

         //Add all arcs with start vertexes into originalArcs
         for(Vertex vertex: originalVertexes) {
            originalArcs.put(vertex, vertex.getAllOutgoingArcs());
         }

         // Populate clonedVerts with nulls (so list.set(i) doesnt throw an exception)
         for(int i = 0; i < originalVertexes.size(); i++) clonedVertexes.add(null);

         // Cloning vertexes
         for(int i = originalVertexes.size() - 1; i >= 0; i--) {
            Vertex original = originalVertexes.get(i);
            Vertex clone = new Vertex(original.id);
            if(original.next != null) clone.next = clonedVertexes.get(i + 1);
            clonedVertexes.set(i, clone);
         }
         // Cloning Arcs
         for(int vIndex = 0; vIndex < originalVertexes.size(); vIndex++){      // Loop through original Vertexes indexes
            Vertex startVertex = originalVertexes.get(vIndex);
            List<Arc> outgoingArcs = originalArcs.get(startVertex);
            Vertex clonedStartVertex = clonedVertexes.get(vIndex);

            if(outgoingArcs.size() == 0) continue;       // If vertex doesnt have outgoing arcs, continue to next vertex


            List<Arc> clonedOutgoingArcs = new ArrayList<>();       // Create and populate clonedOutgoingArcs with nulls
            for(int i = 0; i < outgoingArcs.size(); i++) clonedOutgoingArcs.add(null);


            for(int aIndex = outgoingArcs.size() - 1; aIndex >= 0; aIndex--) { //Loop through originalOutgoingArcs indexes:
               Arc originalArc = outgoingArcs.get(aIndex);
               Arc clonedArc = new Arc(originalArc.id);

               if(aIndex == 0) clonedStartVertex.first = clonedArc;        // If this is the first arc, set it to vertex first

               if(originalArc.next != null) clonedArc.next = clonedOutgoingArcs.get(aIndex + 1); // Set cloned arc next

               int targetIndex = originalVertexes.indexOf(originalArc.target);  //Find target vertex index with original
               clonedArc.target = clonedVertexes.get(targetIndex);             // Set cloned arc target to cloned vertex

               clonedOutgoingArcs.set(aIndex, clonedArc);                      //Add cloned arc to clonedArcs list
            }
         }
         return new Graph(this.id, clonedVertexes.get(0));
      }
   }