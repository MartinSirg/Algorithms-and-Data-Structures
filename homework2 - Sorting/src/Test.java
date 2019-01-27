public class Test {
    public static void main(String[] args) {
        int[] array = new int[] {8,7,6,5,4,3,2,1};
        binaryInsertionSort(array);
        for(int i: array) {
            System.out.print(i + " ");
        }

    }

    public static void binaryInsertionSort(int[] a) {
        if(a.length < 2) return;

        for(int current = 1; current < a.length; current++) {
//            System.out.println("Current index is " + current + "(" + a[current] + ")");
            int low = 0, high = current - 1;
            int insertValue = a[current];
            if(a[current] >= a[high]) {
//                System.out.println("Current num is bigger/equal last sorted num, continuing");
//                System.out.println();
                continue;
            }
            while(high - low > 1) {
                int mid = (high + low)/ 2;
                if(a[mid] == a[current]) {
                    high = mid;
                    low = mid - 1;
                    break;
                } else if (a[current] > a[mid]) {
                    low = mid;
                } else {
                    high = mid;
                }
            }
//            System.out.println(String.format("Found low %d(%d) and high %d(%d)", low, a[low], high, a[high]));
            if(a[current] > a[low] && a[current] < a[high]) { // between two numbers
//                System.out.println("Inserting between high and low");
                System.arraycopy(a, high, a, high + 1, current - high);
                a[high] = insertValue;
            } else if(a[current] == a[low]) {
//                System.out.println("Inserting after low");
                System.arraycopy(a, low + 1, a, low + 2, current - high);
                a[low + 1] = insertValue;
            } else if(a[current] == a[high]) {
//                System.out.println("Inserting after high");
                System.arraycopy(a, high + 1, a, high + 2, current - high - 1);
                a[high + 1] = insertValue;
            } else if(a[current] < a[low]) {
//                System.out.println("Inserting before low");
                System.arraycopy(a, low, a, low + 1, current - low);
                a[low] = insertValue;
            } else {
//                System.out.println("Biggest number, no need to insert.");
            }
//            System.out.println();
        }
//        System.out.print("[");
//        for(int i: a) System.out.print(i + ",");
//        System.out.println("]");
    }
}
