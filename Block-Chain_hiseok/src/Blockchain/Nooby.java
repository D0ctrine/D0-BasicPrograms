package Blockchain;

import java.util.ArrayList;

import DAO.dao;
import DAO.dto;

public class Nooby {
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;
	dao d = dao.getInstance();
	ArrayList<dto> dtobox =null;
		public ArrayList<String> blockmain(ArrayList<String> mybox) {
		//add our blocks to the blockchain ArrayList:
			int c=0;
			dtobox = d.checksend();
			String imsi = "";
			ArrayList<String> linebox = new ArrayList<String>();
			if(dtobox.size()!=0) {
			for(int i=0;i<dtobox.size();i++) {
				imsi="송신자 : "+dtobox.get(i).getSender()+" / "+"수신자 : "+dtobox.get(i).getReceiver()+"("+d.getID(dtobox.get(i).getReceiver())+")"+
						"/"+"보낸 금액 : "+dtobox.get(i).getMoney()+"/"+"날짜 : "+dtobox.get(i).getDate().subSequence(0, dtobox.get(i).getDate().lastIndexOf("."));
			System.out.println(dtobox.get(i).getSender()+"|"+dtobox.get(i).getReceiver());
				linebox.add(imsi);
			}
				//c=dtobox.size()-1;
			}

			
			for(int i=0;i<linebox.size();i++) {
				System.out.println(linebox.get(i)+"|"+i);
				
				blockchain.add(new Block(linebox.get(i), "0"));
				System.out.println("Trying to Mine block "+(i+1)+"... ");
				mybox.add(blockchain.get(i).mineBlock(difficulty));
				  
			}
		
//		blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
//		System.out.println("Trying to Mine block 2... ");
//		blockchain.get(1).mineBlock(difficulty);
//		
//		blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));
//		System.out.println("Trying to Mine block 3... ");
//		blockchain.get(2).mineBlock(difficulty);	
//		
//		blockchain.add(new Block("Hey im the gear Forth block",blockchain.get(blockchain.size()-1).hash));
//		System.out.println("Trying to Mine block 4... ");
//		blockchain.get(3).mineBlock(difficulty);	
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		return mybox;
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
}
