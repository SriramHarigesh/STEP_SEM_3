package week4.practice_problems;
public class BusRouteRankingEngine{
public static void main(String[] args){
BusRoute[] routes={new BusRoute("RT205L","Airport Express",3),new BusRoute("rt201j","City Central",4),new BusRoute("RT299T","Night Service")};
for(BusRoute route:BusRoute.rankRoutes(routes))System.out.println(route.routeCode);
}
}
class BusRoute{
String routeCode;
String routeName;
int priority;
public BusRoute(String routeCode,String routeName,int priority){this.routeCode=routeCode;this.routeName=routeName;this.priority=priority;}
public BusRoute(String routeCode,String routeName){this(routeCode,routeName,3);}
int compareTo(BusRoute other){
if(priority!=other.priority)return other.priority-priority;
int codeComparison=routeCode.compareToIgnoreCase(other.routeCode);
if(codeComparison!=0)return codeComparison;
return routeName.length()-other.routeName.length();
}
static BusRoute[] rankRoutes(BusRoute[] routes){
BusRoute[] ranked=routes.clone();
for(int i=0;i<ranked.length-1;i++)for(int j=0;j<ranked.length-1-i;j++)if(ranked[j].compareTo(ranked[j+1])>0){BusRoute temp=ranked[j];ranked[j]=ranked[j+1];ranked[j+1]=temp;}
return ranked;
}
}
