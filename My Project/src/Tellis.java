public class Tellis {

    public static void main (String[] param) {
        System.out.println(mahub(3,3,1, 3,1));
    }

    public static boolean mahub (double a, double b, double c,
                                 double x, double y) {
        return (a <= x && b <= y) || (b <= x && a <= y) ||
                (a <= x && c <= y) || (c <= x && a <= y) ||
                (b <= x && c <= y) || (c <= x && b <= y);
    }

}