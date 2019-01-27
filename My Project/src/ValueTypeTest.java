public class ValueTypeTest {

    public static void main(String[] args) {
        // String is a value type!
        String s1 = "test";
        String s2 = s1;
        s1 = "testTest";
        System.out.println(s2);
    }
}
