import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;


public class GraphTaskTest {

   @Test (timeout=20000)
   public void testGraphNotSameObjectAsClone() {
      Graph graph = new Graph("G");
      graph.createRandomSimpleGraph(6,10);
      Graph clone = graph.deepGraphClone();
      assertNotSame(clone, graph);
   }

   @Test (timeout=20000)
   public void testGraphElementsIdNameIndependence() {
      Graph graph = new Graph("G");
      graph.createRandomSimpleGraph(6,10);
      Graph clone = graph.deepGraphClone();
      assertEquals(clone.id, graph.id);
      assertEquals(clone.first.id, graph.first.id);
      assertEquals(clone.first.first.id, graph.first.first.id);
      graph.id = "new name";
      graph.first.id = "new vertex name";
      graph.first.first.id = "new arc name";
      assertNotEquals(clone.id, graph.id);
      assertNotEquals(clone.first.id, graph.first.id);
      assertNotEquals(clone.first.first.id, graph.first.first.id);
   }


   @Test (timeout=20000)
   public void testGraphVertexNextIndependence() {
      Graph graph = new Graph("G");
      graph.createRandomSimpleGraph(6,10);
      Graph clone = graph.deepGraphClone();

      graph.first.next = null;
      assertNotNull(clone.first.next);
   }

   @Test (timeout=20000)
   public void testGraphVertexFirstIndependence() {
      Graph graph = new Graph("G");
      graph.createRandomSimpleGraph(6,10);
      Graph clone = graph.deepGraphClone();

      graph.first.first = null;
      assertNotNull(clone.first.first);
   }

   @Test (timeout=20000)
   public void testGraphArcTargetIndependence() {
      Graph graph = new Graph("G");
      graph.createRandomSimpleGraph(6,10);
      Graph clone = graph.deepGraphClone();

      clone.first.first.target = clone.first.next.next;
      graph.first.first.target = graph.first.next;
      assertNotSame(clone.first.first.target, clone.first.next);
   }

   @Test (timeout=20000)
   public void testGraphArcNextIndependence() {
      Graph graph = new Graph("G");
      graph.createRandomSimpleGraph(6,15);
      Graph clone = graph.deepGraphClone();
      graph.first.first.next = null;
      assertNotNull(clone.first.first.next);
   }
}

