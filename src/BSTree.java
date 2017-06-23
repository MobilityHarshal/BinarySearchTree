
public class BSTree {

	BSTNode root;
	
	public void InsertNew(String data){
		BSTNode node = new BSTNode(data);
		InsertNode(root, node);
	}
	
	private void InsertNode(BSTNode parent,BSTNode node){
		if(parent == null){
			root = node;
		}else if(parent.getData().compareTo(node.getData()) > 0){
			if(parent.getLeft() == null){
				parent.setLeft(node);
				node.setParent(parent);
			}else{
				InsertNode(parent.getLeft(), node);
			}
		}else if(parent.getData().compareTo(node.getData()) < 0){
			if(parent.getRight() == null){
				parent.setRight(node);
				node.setParent(parent);
			}else{
				InsertNode(parent.getRight(), node);
			}
		}
	}
	
	public int Height(BSTNode root){
		int h = 0;
		if(root != null){
			h = 1 + Math.max(Height(root.getLeft()), Height(root.getRight()));
		}
		return h;
	}
	
	public void preOrderTraversal(BSTNode root){
		if(root == null)
			return;
		System.out.print(root.getData()+" ");
		preOrderTraversal(root.getLeft());
		preOrderTraversal(root.getRight());
	}
	
	public void inOrderTraversal(BSTNode root){
		if(root == null)
			return;
		inOrderTraversal(root.getLeft());
		System.out.print(root.getData()+" ");
		inOrderTraversal(root.getRight());
	}
	
	public void postOrderTraversal(BSTNode root){
		if(root == null)
			return;
		postOrderTraversal(root.getLeft());
		postOrderTraversal(root.getRight());
		System.out.print(root.getData()+" ");
	}
	
	//Delete is not tested yet
	
	public void delete(String value){
		BSTNode node = findValue(root, value);
		remove(node);
	}
	
	private BSTNode findValue(BSTNode root1, String value){
		BSTNode node = null;
		if(root1 == null){
			return null;
		}else if(root1.getData() == value){
			return root1;
		}else{
			node = findValue(root1.getLeft(), value);
			if(node == null)
				node = findValue(root1.getRight(), value);
		}
		return node;
	}
	
	private void remove(BSTNode node){
		if(node.getLeft() != null){
			
			if(node.getLeft().getRight() != null){
				BSTNode temp = node.getLeft().getRight();
				remove(temp);
				temp.setLeft(node.getLeft());
				temp.setRight(node.getRight());
				node.getRight().setParent(temp);
				node.getLeft().setParent(temp);
				temp.setParent(node.getParent());
				if(node.getParent() != null){
					if(node.getParent().getRight() == node)
						node.getParent().setRight(temp);
					else
						node.getParent().setLeft(temp);
				}else{
					root = temp;
				}

			}else{
				node.getLeft().setRight(node.getRight());
				node.getLeft().setParent(node.getParent());
				if(node.getParent() != null){
					if(node.getParent().getRight() == node)
						node.getParent().setRight(node.getLeft());
					else
						node.getParent().setLeft(node.getLeft());
				}else{
					root = node.getLeft();
				}
			}
			
		}else if(node.getRight() != null){
			
			if(node.getRight().getLeft() != null){
				BSTNode temp = node.getRight().getLeft();
				remove(temp);
				temp.setLeft(node.getLeft());
				temp.setRight(node.getRight());
				node.getRight().setParent(temp);
				node.getLeft().setParent(temp);
				temp.setParent(node.getParent());
				if(node.getParent() != null){
					if(node.getParent().getRight() == node)
						node.getParent().setRight(temp);
					else
						node.getParent().setLeft(temp);
				}else{
					root = temp;
				}

			}else{
				node.getRight().setLeft(node.getRight());
				node.getRight().setParent(node.getParent());
				if(node.getParent() != null){
					if(node.getParent().getRight() == node)
						node.getParent().setRight(node.getRight());
					else
						node.getParent().setLeft(node.getRight());
				}else{
					root = node.getRight();
				}
			}
			
		}else if(node.getParent() != null){
			
			if(node.getParent().getLeft() == node){
				node.getParent().setLeft(null);
			}else{
				node.getParent().setRight(null);
			}
			node.setParent(null);
			
		}else{
			
			root = null;
		
		}
	}
	
}
