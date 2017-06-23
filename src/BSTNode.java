
public class BSTNode {

	private BSTNode parent;
	private BSTNode left;
	private BSTNode right;
	private String data;
	
	
	public BSTNode(String data) {
		super();
		this.data = data;
		parent = null;
		left = null;
		right = null;
	}
	
	public BSTNode getParent() {
		return parent;
	}
	public void setParent(BSTNode parent) {
		this.parent = parent;
	}
	public BSTNode getLeft() {
		return left;
	}
	public void setLeft(BSTNode left) {
		this.left = left;
	}
	public BSTNode getRight() {
		return right;
	}
	public void setRight(BSTNode right) {
		this.right = right;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
