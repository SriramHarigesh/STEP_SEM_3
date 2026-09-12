package week1.practice_problems;
import java.util.Random;
public class RockPaperScissorsGame{
    static String playRound(String playerMove,String computerMove){
        if(playerMove.equalsIgnoreCase(computerMove)) return "Draw";
        if((playerMove.equalsIgnoreCase("Rock")&&computerMove.equalsIgnoreCase("Scissors"))||(playerMove.equalsIgnoreCase("Paper")&&computerMove.equalsIgnoreCase("Rock"))||(playerMove.equalsIgnoreCase("Scissors")&&computerMove.equalsIgnoreCase("Paper"))) return "Player Wins";
        return "Computer Wins";
    }
    public static void main(String[] args){
        String[] moves={"Rock","Paper","Scissors"};
        String[] playerMoves={"Rock","Paper","Scissors","Rock","Paper"};
        Random random=new Random();
        int wins=0,losses=0,draws=0;
        System.out.println("Round\tPlayer\t\tComputer\tResult");
        for(int i=0;i<playerMoves.length;i++){
            String computerMove=moves[random.nextInt(moves.length)];
            String result=playRound(playerMoves[i],computerMove);
            if(result.equals("Player Wins")) wins++;
            else if(result.equals("Computer Wins")) losses++;
            else draws++;
            System.out.println((i+1)+"\t"+playerMoves[i]+"\t\t"+computerMove+"\t"+result);
        }
        double percentage=(double)wins/playerMoves.length*100;
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%\n",wins,losses,draws,percentage);
    }
}
