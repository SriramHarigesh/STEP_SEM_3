import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
public class FireDrillExits{
static int[][] exitDistance(char[][] plan){
int rows=plan.length;
int cols=plan[0].length;
int[][] distance=new int[rows][cols];
Queue<int[]> queue=new LinkedList<>();
for(int i=0;i<rows;i++){
for(int j=0;j<cols;j++){
distance[i][j]=-1;
if(plan[i][j]=='E'){
distance[i][j]=0;
queue.add(new int[]{i,j});
}
}
}
int[] rowMove={-1,1,0,0};
int[] colMove={0,0,-1,1};
while(!queue.isEmpty()){
int[] cell=queue.remove();
int row=cell[0];
int col=cell[1];
for(int k=0;k<4;k++){
int newRow=row+rowMove[k];
int newCol=col+colMove[k];
if(newRow>=0&&newRow<rows&&newCol>=0&&newCol<cols&&plan[newRow][newCol]!='#'&&distance[newRow][newCol]==-1){
distance[newRow][newCol]=distance[row][col]+1;
queue.add(new int[]{newRow,newCol});
}
}
}
return distance;
}
public static void main(String[] args){
char[][] plan={
{'.','#','.','E'},
{'.','.','.','#'},
{'E','#','.','.'}
};
int[][] result=exitDistance(plan);
for(int i=0;i<result.length;i++) System.out.println(Arrays.toString(result[i]));
}
}
