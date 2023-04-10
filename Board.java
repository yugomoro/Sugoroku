import java.io.*;
import java.util.ArrayList;

public class Board {
	FileInputStream fi=null;
	InputStreamReader is=null;
	BufferedReader br=null;
	private String fileName;
	private int blocks;
	String line; //読み込んだ行
	int index=0; //読み込んだ行数
	String[] columnName = null;
	String[] dataArray = null;//列の内容を入れる
	public  ArrayList<String> blockNum = new ArrayList<String>();
	public  ArrayList<String> event = new ArrayList<String>();
	public  ArrayList<String> eventLog = new ArrayList<String>();
	
	public Board(String fileName_) {
		this.setFileName(fileName_);
		
		//csvファイルの読み込み開始
		try {
			fi=new FileInputStream(getFileName());
			is=new InputStreamReader(fi);
			br=new BufferedReader(is);
			
			//1行ずつファイル読み込み
			while ((line=br.readLine()) != null) {
				if (index==0) {
					columnName = line.split(",");//1行目だけは列のタイトル
				} else {
					dataArray = line.split(",");//2行目以降は列の内容
					blockNum.add(index-1, dataArray[0]);
					event.add(index-1, dataArray[1]);
					eventLog.add(index-1, dataArray[2]);
				}
				index++;
			}
			
			this.setBlocks(blockNum.size()-1);
			System.out.println("すごろくのボードを用意しました。");
			System.out.println("全部で" + this.getBlocks() + "マス。");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getBlocks() {
		return this.blocks;
	}
	public void setBlocks(int blocks) {
		this.blocks = blocks;
	}
	
	
}
