import java.util.ArrayList;

public class TreeNode {   
	private String pattern;   
	private ArrayList list;   
	private TreeNode left;   
	private TreeNode right;   
	private int height;
	public String getPattern() {return pattern;}   
	public int getBalanceInfo () {
		TreeNode leftTree = getLeft();
		int leftTreeHeight;
		if (leftTree == null) {
			leftTreeHeight = 0;
		} else {
			leftTreeHeight = leftTree.getHeight();
		}
		TreeNode rightTree = getRight();
		int rightTreeHeight;
		if (rightTree == null) {
			rightTreeHeight = 0;
		} else {
			rightTreeHeight = rightTree.getHeight();
		}
		return leftTreeHeight - rightTreeHeight;
	}
	public ArrayList getList() {return list;}   
	public TreeNode getLeft() {return left;}   
	public TreeNode getRight() {return right;}   
	public int getHeight() {return height;}
	public int getMaxSubTreeHeight (){
		TreeNode leftTree = getLeft();
		int leftTreeHeight;
		if (leftTree == null) {
			leftTreeHeight = 0;
		} else {
			leftTreeHeight = leftTree.getHeight();
		}
		TreeNode rightTree = getRight();
		int rightTreeHeight;
		if (rightTree == null) {
			rightTreeHeight = 0;
		} else {
			rightTreeHeight = rightTree.getHeight();
		}
		return (leftTreeHeight > rightTreeHeight ? leftTreeHeight: rightTreeHeight); // I wanted to try this cool way of shortening if else
	}
	public void setPattern(String str) {pattern=str;}   
	public void setList(ArrayList wl) {list=wl;}   
	public void setLeft(TreeNode node) {left=node;}   
	public void setRight(TreeNode node) {right=node;}   
	public void setHeight(int newHeight) {height=newHeight;}
	TreeNode(String word, String pat) {      
		pattern=pat;
		list=new ArrayList();      
		list.add(word);      
		left = null;      
		right = null;   
		height = 0;
	}
}