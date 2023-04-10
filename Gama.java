import java.util.ArrayList;

public class Game {
	private int playerCount = 0;
	private Board board_;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private Dice dice_;
	private int topBlock=0;
	private int turn=0;
	
	public void start() {
		//開始時に表示する文章
		System.out.println("\n双六を開始します。");
		System.out.println("参加プレイヤーは");
		for (int i=0; i<playerList.size(); i++) {
			String str = playerList.get(i).getName();
			System.out.print(str + "   ");
		}
		System.out.print("だそうです。\n\n");
		
		System.out.println(board_.blockNum.size()-1 + "マスのステージ");
		System.out.println("じゃあ、始めて");
		
		//ここから双六開始
		while(this.getTopBlock()<board_.blockNum.size()) {
			
			for (int i=0; i<this.playerList.size(); i++) {//参加プレイヤーの人数分ループを回す
				Player current = playerList.get(i);
				int loc = current.getCurrentBlock();
				turn++;
				
				System.out.println(turn + "ターン目");
				System.out.println(current.getName() + "の番");
				System.out.println("現在地は" + loc + "マス目!");
				
				if (current.isNextTurn() == true) {//１回休みじゃない人
					current.throwDice(dice_);
					loc = current.getCurrentBlock();
					if (this.getTopBlock()<loc) {
						this.setTopBlock(loc);
					}
					
					if (this.getTopBlock()>=(board_.blockNum.size()-1)) {//出目の結果、マス数を超える/ぴったりになったらゴール
						System.out.println(current.getName()+ "はゴールにたどり着いた");
						break;
					}
					
					int c = Integer.parseInt(board_.blockNum.get(loc));
					int s = Integer.parseInt(board_.event.get(c));
					
					System.out.println("現在地は" + loc + "マス目!");
					System.out.println(board_.eventLog.get(c));	

						if (s>= 10) {//１回休み
							System.out.println("1回休み\n");
							current.setNextTurn(false);
							
						} else {//追加のマス移動
							if(s>0) {
								System.out.println("+" + s + "マス\n");
								current.setCurrentBlock(current.getCurrentBlock()+s);
								if (current.getCurrentBlock()<board_.blockNum.size()-1) {
									System.out.println(current.getName()+ "はゴールにたどり着いた");
									break;
								}
							} else if (s<0) {
								System.out.println(s + "マス");
								current.setCurrentBlock(current.getCurrentBlock()+s);
								if (current.getCurrentBlock() <0) {
									current.setCurrentBlock(0);
								}
								if (current.getCurrentBlock()==0) {
									System.out.println("振出しに戻った\n");
								} else {
									System.out.println("\n");
								}
							} else {
								System.out.println("\n");
							}
						}
				} else {//１回休みの時のアナウンス
					System.out.println(current.getName() + "は１回休み中\n");
					current.setNextTurn(true);
				}
			}
			if (this.getTopBlock()>=board_.blockNum.size()-1) {
				System.out.println("やっと終わった…");
				break;
			}
		}
	}
	
	public void setBoard(Board board) {
		this.setBoard_(board);
	}
	
	public void addPlayer(Player player) {
		playerList.add(player);
		this.setPlayerCount(playerCount+1);
		System.out.println("プレイヤー" + playerCount + " " + player.getName() + " エントリーしました。");
	}
	
	public void setDice(Dice dice) {
		this.setDice_(dice);
	}

	public int getPlayerCount() {
		return playerCount;
	}
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
}


	public Board getBoard_() {
		return board_;
	}

	public void setBoard_(Board board_) {
		this.board_ = board_;
	}

	public Dice getDice_() {
		return dice_;
	}
	public void setDice_(Dice dice_) {
		this.dice_ = dice_;
	}

	public int getTopBlock() {
		return this.topBlock;
	}
	public void setTopBlock(int topBlock) {
		this.topBlock=topBlock;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

}
