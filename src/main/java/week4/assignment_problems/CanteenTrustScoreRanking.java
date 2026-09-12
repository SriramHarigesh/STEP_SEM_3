package week4.assignment_problems;
public class CanteenTrustScoreRanking{
public static void main(String[] args){
Canteen[] canteens={new Canteen("HB3-C","Spice Junction",3),new Canteen("hb1-c","Grand Mess",5),new Canteen("HB2-C","Southern Treats")};
for(Canteen canteen:Canteen.rankCanteens(canteens))System.out.println(canteen.canteenCode);
}
}
class Canteen{
String canteenCode;
String canteenName;
int trustScore;
public Canteen(String canteenCode,String canteenName,int trustScore){
this.canteenCode=canteenCode;
this.canteenName=canteenName;
this.trustScore=trustScore;
}
public Canteen(String canteenCode,String canteenName){
this(canteenCode,canteenName,3);
}
int compareTo(Canteen other){
if(trustScore!=other.trustScore)return other.trustScore-trustScore;
int codeComparison=canteenCode.compareToIgnoreCase(other.canteenCode);
if(codeComparison!=0)return codeComparison;
return canteenName.length()-other.canteenName.length();
}
static Canteen[] rankCanteens(Canteen[] canteens){
Canteen[] ranked=canteens.clone();
for(int i=0;i<ranked.length-1;i++)for(int j=0;j<ranked.length-1-i;j++)if(ranked[j].compareTo(ranked[j+1])>0){Canteen temp=ranked[j];ranked[j]=ranked[j+1];ranked[j+1]=temp;}
return ranked;
}
}
