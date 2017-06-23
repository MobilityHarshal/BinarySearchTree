import java.util.Random;
import java.util.Scanner;

public class BSTGenerator {
	
	int maxStrSize = 5;
	int sizeOfList = 1000;
	
	private String generateRandomString(){
		Random r = new Random();
		int k = r.nextInt(maxStrSize - 2) + 2;
		String returnable = "";
		for(int i = 0 ; i < k; i++){
			returnable += (char)(96 + (r.nextInt(25)+1));
		}
		
		return returnable;
	}
	
	private String[] createList(){
		String list[] = new String[sizeOfList];
		
		for(int i = 0; i < list.length; i++){
			list[i] = generateRandomString();
		}
		
		return list;
	}
	
	private String[] shuffleList(String[] list){
		Random r = new Random();
		for(int i = 0; i < list.length; i++){
			list = swap(list, i,r.nextInt(list.length - i)+i);
		}
		return list;
	}
	
	private String[] swap(String[] list,int index1, int index2){
		String temp = list[index1];
		list[index1] = list[index2];
		list[index2] = temp;
		return list;
	}
	
	private double calculateAvgHeight(){
		int height = 0;
		double avgHeight = 0;
		String[] list = createList();
		int iterations = 1000;
		if(list.length > 1000)
			iterations = list.length;
		for(int i = 0; i < iterations; i++){
			//shuffle list
			list = shuffleList(list);
			//instanciate BT
			BSTree tree = new BSTree();
			//loop to populate BT
			for(int j = 0; j < list.length; j++){
				tree.InsertNew(list[j]);
			}
			//get height of BT and add to total height
			height += tree.Height(tree.root);
		}
		avgHeight = (double)height / iterations;
		
		return avgHeight;
	}
	
	private double calculateAvgHeightForBalancedTree(){
		int height = 0;
		double avgHeight = 0;
		String[] list = createList();
		int iterations = 1000;
		if(list.length > 1000)
			iterations = list.length;
		for(int i = 0; i < iterations; i++){
			//shuffle list
			list = shuffleList(list);
			//instanciate BBT
			BSBalancedTree tree = new BSBalancedTree();
			//loop to populate BBT
			for(int j = 0; j < list.length; j++){
				tree.InsertNew(list[j]);
			}
			//get height of BBT and add to total height
			height += tree.Height(tree.root);
		}
		avgHeight = (double)height / iterations;
		
		return avgHeight;
	}
	
	private void createRandomTreeAndCheckFor(String s){
		String[] list = createList();
		BSBalancedTree tree = new BSBalancedTree();
		for(int j = 0; j < list.length; j++){
			tree.InsertNew(list[j]);
		}
		int matchesAtLevel[] = new int[tree.Height(tree.root)];
		checkMatchAt(tree.root, 1, tree.Height(tree.root), matchesAtLevel, s);
		tree.inOrderTraversal(tree.root);
		for(int i = 0; i < matchesAtLevel.length; i++)
			System.out.println("there were "+matchesAtLevel[i]+" matches at level "+(i+1));
	}
	
	private void checkMatchAt(BSTNode node, int level, int height, int matchesAtLevel[], String match){
		if(node == null)
			return;
		if(node.getData().contains(match.toLowerCase()))
			matchesAtLevel[level - 1] = matchesAtLevel[level - 1] + 1;
		if(level < height){
			checkMatchAt(node.getLeft(), level + 1, height, matchesAtLevel, match);
			checkMatchAt(node.getRight(), level + 1, height, matchesAtLevel, match);
		}
	}
	
	private void run(){
		System.out.println("=================================================\n   Calculating Avg Height........................  \n=================================================");
		double avg = calculateAvgHeight();
		System.out.println("\n   Avg Height = "+avg+"\n");
		System.out.println("=================================================\n   Calculating Avg Height For Balanced Tree...... \n=================================================");
		avg = calculateAvgHeightForBalancedTree();
		System.out.println("\n   Avg Height (Balanced Tree)= "+avg+"\n");
		Scanner S = new Scanner(System.in);
		while(true){
			System.out.println("please Enter a String to match (spaces not allowed, all lower case, tpe \"quit\" to exit)");
			String match = S.next();
			if(match.equalsIgnoreCase("quit"))
				break;
			createRandomTreeAndCheckFor(match);
		}
		S.close();
	}
	
	public static void main(String args[]){
		BSTGenerator obj = new BSTGenerator();
		obj.run();
	}
	
}
