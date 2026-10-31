public class StudentMarksGrid {
    public static void main(String[] args) {
        String[] subjects = {"Math", "Sci", "Eng"};
        String[] names = {"Anu", "Ravi", "Meena"};
        int[][] marks = {
            {80, 90, 70},
            {60, 85, -1},
            {95, 75, 88}
        };
        StringBuilder totals = new StringBuilder("Totals ");
        for (int i = 0; i < names.length; i++) {
            int sum = 0;
            for (int j = 0; j < subjects.length; j++) {
                if (marks[i][j] != -1) {
                    sum += marks[i][j];
                }
            }
            totals.append(names[i]).append(" ").append(sum);
            if (i < names.length - 1) {
                totals.append(", ");
            }
        }
        System.out.println(totals);
        StringBuilder toppers = new StringBuilder("toppers ");
        for (int j = 0; j < subjects.length; j++) {
            int best = -1;
            int bestIdx = -1;
            for (int i = 0; i < names.length; i++) {
                if (marks[i][j] > best) {
                    best = marks[i][j];
                    bestIdx = i;
                }
            }
            toppers.append(subjects[j]).append(" ").append(names[bestIdx]).append(" ").append(best);
            if (j < subjects.length - 1) {
                toppers.append(", ");
            }
        }
        System.out.println(toppers);
    }
}
