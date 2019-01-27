public class MasterFarmerProbability {

    private static final int LAST_LEVEL = 99, START_LEVEL = 54;
    public static void main(String[] args) {

        for(int current = START_LEVEL; current < LAST_LEVEL + 1; current++){
            System.out.println(String.format("Success probability at level %d is %.2f(%.2f)", current,
                    calculateChanceAtLevel(current, false), calculateChanceAtLevel(current, true)));
        }
    }

    private static double calculateChanceAtLevel(int level, boolean ardyHardDiary){
        double chance = ((5./833) * level) + 17./49;
        return ardyHardDiary? chance + 0.1 : chance;
    }
}
