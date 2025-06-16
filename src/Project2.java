import java.util.Scanner;

public class Project2 {

	public static void main(String[] args) throws Exception {
		// creates new dragon maze
		DragonMaze game = new DragonMaze();
		// loads in the maze file
		game.loadMazeFile("maze.txt");
		game.printMaze();

// Initialize scanner
		Scanner scan = new Scanner(System.in);
		// create boolean flags
		boolean winFlag = false;
		boolean loseFlag = false;
// create main while loop with condition of boolean flags being false
		while (winFlag == false && loseFlag == false)

		{

			// Instructions for user
			System.out.println("Make your move!\nValid moves are w, a, s, d, f");
			// creates string based on user input
			String direction = scan.nextLine();
			// gives value to boolean flags based on moveHero and moveDragon
			winFlag = game.moveHero(direction);
			loseFlag = game.moveDragon();
			game.printMaze();

		}
		// close scanner
		scan.close();
		// displays losing message and exits system if lose flag is true
		if (loseFlag == true) {
			System.out.println("You're not much of hero or a comedian\nstay home next time\nGAME OVER");
			System.exit(0);
		}
		// displays winning message and exits system if win flag is true
		if (winFlag == true) {
			System.out.println("GG m8, you won!");
			System.exit(0);
		}
	}

}
