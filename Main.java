public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		game.setBoard(new Board("data/board.csv"));
		game.addPlayer(new Player("Taro"));
		game.addPlayer(new Player("Jiro"));
		game.setDice(new Dice());
		
		game.start();
		
	}

}
