package week1.practice_problems;
public class BmiCalculatorForTeam{
    static String getBmiStatus(double bmi){
        if(bmi<18.5) return "Underweight";
        if(bmi<25) return "Normal";
        if(bmi<30) return "Overweight";
        return "Obese";
    }
    static void printWellnessReport(double[] heights,double[] weights){
        if(heights.length!=weights.length){
            System.out.println("Heights and weights count must match");
            return;
        }
        System.out.println("Person\tHeight(m)\tWeight(kg)\tBMI\tStatus");
        for(int i=0;i<heights.length;i++){
            double bmi=weights[i]/(heights[i]*heights[i]);
            System.out.printf("%d\t%.2f\t\t%.1f\t\t%.2f\t%s%n",i+1,heights[i],weights[i],bmi,getBmiStatus(bmi));
        }
    }
    public static void main(String[] args){
        double[] heights={1.75,1.60,1.68,1.80,1.72};
        double[] weights={70,90,58,82,76};
        printWellnessReport(heights,weights);
    }
}
