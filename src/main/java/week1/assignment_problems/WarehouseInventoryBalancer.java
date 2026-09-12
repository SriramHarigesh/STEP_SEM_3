package week1.assignment_problems;
public class WarehouseInventoryBalancer{
    static void analyzeInventory(int[] sectionA,int[] sectionB){
        if(sectionA.length!=sectionB.length){
            System.out.println("Both sections must contain equal number of items");
            return;
        }
        int totalA=0,totalB=0,highest=Integer.MIN_VALUE,highestIndex=-1;
        String highestSection="";
        for(int i=0;i<sectionA.length;i++){
            totalA+=sectionA[i];
            totalB+=sectionB[i];
            if(sectionA[i]>highest){
                highest=sectionA[i];
                highestSection="Section A";
                highestIndex=i;
            }
            if(sectionB[i]>highest){
                highest=sectionB[i];
                highestSection="Section B";
                highestIndex=i;
            }
        }
        String status=totalA==totalB?"Balanced":"Not Balanced";
        System.out.println("Section A Total: "+totalA+" | Section B Total: "+totalB+" | Status: "+status+" | Highest Quantity: "+highest+" ("+highestSection+", Item "+(highestIndex+1)+")");
    }
    public static void main(String[] args){
        int[] sectionA={20,15,30};
        int[] sectionB={25,10,30};
        analyzeInventory(sectionA,sectionB);
    }
}
