import java.util.ArrayList;

public class PatternTable {
	private TreeNode rootNode;
	public PatternTable(){
	}
	public static void main(String args[]){
		
		PatternTable table = new PatternTable();
		table.printTable();
		
		System.out.println("New Test: Pattern contains check (Should contain nothing):");
		ArrayList <String> wordList = table.WordsWithSamePatternAs("is");
		for(String word: wordList){
			System.out.println(word);
		}
		
		System.out.println("New Test: Add News Words:");
		table.AddWord("human");
		table.AddWord("A");
		table.AddWord("AB");
		table.AddWord("US");
		table.AddWord("B");
		table.AddWord("hello");
		table.AddWord("sarro");
		table.printTable();
		
		System.out.println("New Test: Check to see if words are there:");
		System.out.println("Pattern contains check (Should contain AB, US):");
		wordList = table.WordsWithSamePatternAs("is");
		for(String word: wordList){
			System.out.println(word);
		}
		System.out.println("Pattern contains check (Should contain hello and sarro):");
		wordList = table.WordsWithSamePatternAs("hello");
		for(String word: wordList){
			System.out.println(word);
		}
		
		
	}
	public void AddWord(String word){
		String pattern = PatternMaker.MakePattern(word);
		TreeNode newNode = new TreeNode(word, pattern);
		if(rootNode == null){
			rootNode = newNode;
		}else{
			rootNode = addNode(newNode, rootNode);
		}
	}
	/***
	 * Currently adds in reverse. Left contains greater pattern
	 * @param nodeBeingAdded - the node being added to the tree
	 * @param nodeToAddTo - the node the algorithim is adding to. either setting the left or right subtree
	 * @return
	 */
	private TreeNode addNode(TreeNode nodeBeingAdded, TreeNode nodeToAddTo){
		if (nodeToAddTo == null){ // If the node becomes a new "leaf"
			nodeBeingAdded.setHeight(1);
			return nodeBeingAdded;
		}else{
			String nodeToAddToPattern = nodeToAddTo.getPattern();
			String nodePattern = nodeBeingAdded.getPattern();
			String wordBeingAdded = (String)nodeBeingAdded.getList().get(0); //Should only be caled when a single word is in the list
			if(nodeToAddToPattern.compareTo(nodePattern) > 0){
				nodeToAddTo.setRight(addNode(nodeBeingAdded, nodeToAddTo.getRight()));
			}else if(nodeToAddToPattern.compareTo(nodePattern) < 0){
				nodeToAddTo.setLeft(addNode(nodeBeingAdded, nodeToAddTo.getLeft()));
			}else{
				nodeToAddTo.getList().add(wordBeingAdded);
			}
			
			nodeToAddTo.setHeight(nodeToAddTo.getMaxSubTreeHeight() + 1); // After the addition has already been made. The + 1 accounts for the root node NOT the addition
			
			// Perform necessary balance operations
			TreeNode returnNode = nodeToAddTo;
			int balanceConstant = nodeToAddTo.getBalanceInfo();
			if (balanceConstant <= -2) {
				if (nodeToAddTo.getRight().getPattern().compareTo(nodePattern) > 0) {
					returnNode = leftRotate(nodeToAddTo);
				} else if (nodeToAddTo.getRight().getPattern().compareTo(nodePattern) < 0){
		            nodeToAddTo.setRight(rightRotate(nodeToAddTo.getRight()));
		            returnNode= leftRotate(nodeToAddTo);
				}
			}else if (balanceConstant >= 2) {
				if (nodeToAddTo.getLeft().getPattern().compareTo(nodePattern) > 0) {
			        nodeToAddTo.setLeft(leftRotate(nodeToAddTo.getLeft()));
			        returnNode = rightRotate(nodeToAddTo);
				} else if (nodeToAddTo.getLeft().getPattern().compareTo(nodePattern) < 0){
		           returnNode = rightRotate(nodeToAddTo);
				}
			}
			
			return returnNode;
		}
	}

	public ArrayList WordsWithSamePatternAs(String word){
		String pattern = PatternMaker.MakePattern(word);
		return findPatternInTree(pattern, rootNode);
	}
	
	private ArrayList findPatternInTree(String pat, TreeNode node){
		ArrayList returnList;

		if(node == null){
			returnList = new ArrayList();
		}else if(node.getPattern().compareTo(pat) > 0){
			returnList = findPatternInTree(pat, node.getRight());
		}else if(node.getPattern().compareTo(pat) < 0){
			returnList = findPatternInTree(pat, node.getLeft());			
		}else{
			returnList = node.getList();
		}
		
		return returnList;
	}
	
	public TreeNode leftRotate (TreeNode node) {
		// Height is based off the number of nodes below (in the subtree of a node). No changes to the subtrees of node rightLeftTree are made, so it's height does not need to be recaculated.
		TreeNode rightSubTree = node.getRight();
		TreeNode rightLeftTree = rightSubTree.getLeft(); // Get's the left subtree of the right node
		
		rightSubTree.setLeft(node);
		node.setRight(rightLeftTree);
		
		node.setHeight(node.getMaxSubTreeHeight() + 1);
		rightSubTree.setHeight(rightSubTree.getMaxSubTreeHeight() + 1);
		
		return rightSubTree;
	}

	public TreeNode rightRotate (TreeNode node) {
		// Height is based off the number of nodes below (in the subtree of a node). No changes to the subtrees of node leftRightTree are made, so it's height does not need to be recaculated.
		TreeNode nodeLeftTree = node.getLeft();
		TreeNode leftRightTree = nodeLeftTree.getRight(); // Get's the right subtree of the left node
		
		nodeLeftTree.setRight(node);
		node.setLeft(leftRightTree);
		
		 node.setHeight(node.getMaxSubTreeHeight() + 1);
	     nodeLeftTree.setHeight(nodeLeftTree.getMaxSubTreeHeight() + 1);
	     
	     return nodeLeftTree;
	}
	
	public void printTable(){
		System.out.println("TABLE:");
		printTree(rootNode);
		System.out.println("");
	}
	private void printTree(TreeNode node){
		if(node != null){
			printTree(node.getLeft());
			System.out.print(node.getPattern() + "(" + node.getHeight() + "):");
			for (String word: (ArrayList<String>)node.getList()){
				System.out.print(word + ", ");
			}
			System.out.println();
			printTree(node.getRight());
		}
	}
}


