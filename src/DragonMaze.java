import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**********************************************************
 * * DragonMaze This Class is called DragonMaze and its purpose is to be the
 * foundation for playing the game with any maze file you may have and want to
 * use. it does this by housing several methods that manipulate manipulate the
 * game pieces and allow for the game to be played
 **********************************************************/
public class DragonMaze {

	Random rand;
	ArrayList<String> board;
	GamePiece hero;
	GamePiece dragon;
	GamePiece princess;

	/**********************************************************
	 * DragonMaze method It houses the constructor for the three game pieces, a
	 * random number generator, and the board array list
	 * 
	 * @param none
	 * @return none
	 **********************************************************/
	public DragonMaze() {
		rand = new Random();
		board = new ArrayList<>();
		hero = new GamePiece();
		dragon = new GamePiece();
		princess = new GamePiece();

	}

	/**********************************************************
	 * loadMazeFile method. Description: it allows the math file to be implemented
	 * into the game It does this by using a buffered reader to read each line of a
	 * file and put that line into the board and It finds the game Pieces in the
	 * board
	 * 
	 * @param String filename of the maze you would like to use
	 * @return always false
	 **********************************************************/
	public boolean loadMazeFile(String fileName) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		int row = 0;

		while (in.ready()) {
			// get line from file
			String line = in.readLine();
			// add it to the board
			board.add(line);
			if (line.contains("h"))
				hero = new GamePiece(row, line.indexOf("h"), 'h');
			if (line.contains("P"))
				princess = new GamePiece(row, line.indexOf("P"), 'P');
			if (line.contains("D"))
				dragon = new GamePiece(row, line.indexOf("D"), 'D');

			row++;
		}
		in.close();
		return false;

	}

	/**********************************************************
	 * printMaze method Description: It allows the printing of the board using a for
	 * loop to print each row and it also prints the coordinates of the game pieces
	 * 
	 * @param none
	 * @return void
	 **********************************************************/
	public void printMaze() {
		for (String row : board) {
			System.out.println(row);
		}
		System.out.println(hero);
		System.out.println(princess);
		System.out.println(dragon);
	}

	/**********************************************************
	 * getCharAt method. Description: it is a getter who's purpose is the get the
	 * specific character that is in the location of a game piece it does this by
	 * getting the row and column of a piece then using charAt to find the character
	 * 
	 * @param GamePiece p
	 * @return char, which is the character in that coordinate
	 **********************************************************/
	private char getCharAt(GamePiece p) {
		String row = board.get(p.getRow());
		return row.charAt(p.getCol());
	}

	/**********************************************************
	 * setCharAt. Description It is a getter who's purpose is to set a coordinate of
	 * a gamePiece to a character of your choice by setting the character into the
	 * board at the location of the game piece you put in it
	 * 
	 * @param Gamepiece you choose and the new symbol
	 * @return void
	 **********************************************************/
	private void setCharAt(GamePiece p, char newSymbol) {
		String row = board.get(p.getRow());
		// System.out.println("Old Row: \t" + row);
		String beginning = row.substring(0, p.getCol());
		// System.out.println("Beginning:\t" + beginning);
		String end = row.substring(p.getCol() + 1);
		String newRow = beginning + newSymbol + end;
		// System.out.println("New Row:\t" + newRow);
		board.set(p.getRow(), newRow);
	}

	/**********************************************************
	 * moveHero method. This method moves the hero based on user input. It takes the
	 * direction string which is inputed via scanner in the main method of the
	 * project2 class, then it creates copies and uses switch case to decide which
	 * direction to move and then if else statements to see if the move is valid and
	 * what happens as a result of that move. It also decides if the user has won
	 * 
	 * @param String direction which comes from the user
	 * @return Returns true when hero reaches 'F' and false otherwise
	 **********************************************************/
	public boolean moveHero(String direction) {
		GamePiece hCopy = new GamePiece(hero);
		char princesscheck = getCharAt(hCopy);
		switch (direction) {
		case "w":
			GamePiece copy = new GamePiece(hCopy);
			copy.moveUp();
			char w = getCharAt(copy);
			// checks if the character is a space
			if (w == ' ') {
				hCopy.moveUp();
				setCharAt(hCopy, hero.getSymbol());
				setCharAt(hero, ' ');
				// checks to see if the space contains the princess
			} else if (w == 'P') {
				hCopy.moveUp();
				setCharAt(copy, 'H');
				hCopy.setSymbol('H');
				setCharAt(hero, ' ');
				// checks if the hero has the princess and returns true if he does
			} else if (w == 'F' && princesscheck == 'H') {
				hCopy.moveUp();
				setCharAt(hCopy, hero.getSymbol());
				setCharAt(hero, ' ');
				return true;
			} else if (w == 'F' && princesscheck == 'h') {
				System.out.println("umm, I think you forgot something!!");
				setCharAt(hCopy, hero.getSymbol());
				// if the hero hits a wall
			} else {
				System.out.println("OUUUCH!!!");
				setCharAt(hCopy, hero.getSymbol());

			}
			break;
		case "s":
			GamePiece copy2 = new GamePiece(hCopy);
			copy2.moveDown();
			char s = getCharAt(copy2);
			if (s == ' ') {
				hCopy.moveDown();
				setCharAt(hCopy, hero.getSymbol());
				setCharAt(hero, ' ');
			} else if (s == 'P') {
				hCopy.moveDown();
				setCharAt(copy2, 'H');
				hCopy.setSymbol('H');
				setCharAt(hero, ' ');
			} else if (s == 'F' && princesscheck == 'H') {
				hCopy.moveDown();
				setCharAt(hCopy, hero.getSymbol());
				setCharAt(hero, ' ');
				return true;
			} else if (s == 'F' && princesscheck == 'h') {
				System.out.println("umm, I think you forgot something!!");
				setCharAt(hCopy, hero.getSymbol());
			} else {
				System.out.println("OUUUCH!!!");
				hCopy = hero;
				setCharAt(hCopy, hero.getSymbol());
			}
			break;
		case "d":
			GamePiece copy3 = new GamePiece(hCopy);
			copy3.moveRight();
			char d = getCharAt(copy3);
			if (d == ' ') {
				hCopy.moveRight();
				setCharAt(hCopy, hero.getSymbol());
				setCharAt(hero, ' ');
			} else if (d == 'P') {
				hCopy.moveRight();
				setCharAt(copy3, 'H');
				hCopy.setSymbol('H');
				setCharAt(hero, ' ');
				// hero Has to have princess to leave
			} else if (d == 'F' && princesscheck == 'H') {
				hCopy.moveRight();
				setCharAt(hCopy, hero.getSymbol());
				setCharAt(hero, ' ');

				return true;
				// Hero cannot leave without princess
			} else if (d == 'F' && princesscheck == 'h') {
				System.out.println("umm, I think you forgot something!!");
				setCharAt(hCopy, hero.getSymbol());
			} else {
				System.out.println("OUUUCH!!!");
				setCharAt(hCopy, hero.getSymbol());
			}
			break;
		case "a":
			GamePiece copy4 = new GamePiece(hCopy);
			copy4.moveLeft();
			char a = getCharAt(copy4);
			if (a == ' ') {
				hCopy.moveLeft();
				setCharAt(hCopy, hero.getSymbol());
				setCharAt(hero, ' ');
			} else if (a == 'P') {
				hCopy.moveLeft();
				setCharAt(copy4, 'H');
				hCopy.setSymbol('H');
				setCharAt(hero, ' ');

			} else if (a == 'F' && princesscheck == 'H') {
				hCopy.moveLeft();
				setCharAt(hCopy, hero.getSymbol());
				setCharAt(hero, ' ');
				return true;
			} else if (a == 'F' && princesscheck == 'h') {
				System.out.println("umm, I think you forgot something!!");
				setCharAt(hCopy, hero.getSymbol());
			} else {
				System.out.println("OUUUCH!!!");
				setCharAt(hCopy, hero.getSymbol());
			}
			break;
		// the case for waiting so the hero does not move
		case "f":

			setCharAt(hCopy, hero.getSymbol());

			break;
		// if the input is invalid
		default:
			System.out.println("invalid input, try again");

		}
		// sets the copy of the hero equal to the hero
		hero = hCopy;

		return false;

	}

	/**********************************************************
	 * moveDragon method. Description: very similar to moveHero but rather than
	 * using a string inputed by user it instead uses a random number between 0 and
	 * 3 then creates copies and uses switch case for direction and then if else
	 * statements to see if move is valid. It also decides if user loses by using
	 * the combat method when hero is adjacent to dragon
	 * 
	 * @param none
	 * @return returns true if combat(); returns false, returns false otherwise
	 **********************************************************/
	public boolean moveDragon() {
		// creates copy of dragon
		GamePiece copy = new GamePiece(dragon);
		// obtains random number
		Random rand = new Random();
		int number = rand.nextInt(4);
		switch (number) {
		// if number is 0 move down
		case 0:
			GamePiece copyS = new GamePiece(copy);
			copyS.moveDown();
			char s = getCharAt(copyS);
			// to see if movements is allowed is it a space
			if (s == ' ') {
				copy.moveDown();
				setCharAt(copy, dragon.getSymbol());
				setCharAt(dragon, ' ');
			} else {

				setCharAt(copy, dragon.getSymbol());
			}
			break;
		// in case 1 move up if valid
		case 1:
			GamePiece copyW = new GamePiece(copy);
			copyW.moveUp();
			char w = getCharAt(copyW);
			if (w == ' ') {
				copy.moveUp();
				setCharAt(copy, dragon.getSymbol());
				setCharAt(dragon, ' ');
			} else {

				setCharAt(copy, dragon.getSymbol());
			}
			break;
		// in case 2 move right if valid
		case 2:
			GamePiece copyD = new GamePiece(copy);
			copyD.moveRight();
			char d = getCharAt(copyD);
			if (d == ' ') {
				copy.moveRight();
				setCharAt(copy, dragon.getSymbol());
				setCharAt(dragon, ' ');
			} else {

				setCharAt(copy, dragon.getSymbol());
			}
			break;
		// in case 3 move left if valid
		case 3:
			GamePiece copyA = new GamePiece(copy);
			copyA.moveLeft();
			char a = getCharAt(copyA);
			if (a == ' ') {
				copy.moveLeft();
				setCharAt(copy, dragon.getSymbol());
				setCharAt(dragon, ' ');
			} else {

				setCharAt(copy, dragon.getSymbol());
			}
			break;
		}
		// System.out.println(copy);

		// checks the see if hero is adjacent and
		// if so it starts the combat method

		dragon = copy;
		if (hero.adjacentTo(dragon) == true) {
			boolean combatResult = combat();
			// returns false if combat is won
			if (combatResult == true) {
				return false;
			} else if (combatResult == false) {
				return true;
			}

		}
		return false;

	}

	/**********************************************************
	 * test method. Description: just made this one following along in class, it
	 * uses the equals method to determine if dragon is equal to hero and then
	 * prints that result
	 * 
	 * @param none
	 * @return void
	 **********************************************************/
	public void test() {
		if (dragon.equals(hero))
			System.out.println("D == h");
		else
			System.out.println("D != h");
		if (dragon == hero)
			System.out.println("D == h");
		else
			System.out.println("D != h");
		if (hero.adjacentTo(dragon))
			System.out.println("Hero is next to dragon");
		else
			System.out.println("Hero Not next to dragon");

	}

	/**********************************************************
	 * combat method. Description: This method allows for a second chance for the
	 * hero. when it is used in moveDragon it asks the user to chose a number
	 * between 0 and 2, it then generates a random number between 0 and 2 if the
	 * numbers match the hero can get away, if not he dies
	 * 
	 * @param none
	 * @return true if random number equals input and false otherwise
	 **********************************************************/
	public boolean combat() {
		System.out.print("The dragon has caught you!\nChoose between 0 and 2 to tell him a joke and get away!");
		Random rand = new Random();
		Scanner sc = new Scanner(System.in);
		int combatChoice = sc.nextInt();
		int number = rand.nextInt(1);
		sc.close();
		if (combatChoice == number) {
			System.out.println("you're a real Comedian\n QUICK RUN WHILE HE LAUGHS!!");
			return true;
		} else if (combatChoice != number) {
			return false;
		}
		return false;
	}

}