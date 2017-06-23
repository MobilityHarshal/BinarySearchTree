
public class BSBalancedTree {

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
			balance(parent);
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
	private int slopeAt(BSTNode root){
		int leftHeight = 0;
		int rightHight = 0;
		if(root == null)
			return 0;
		if(root.getLeft() != null)
			leftHeight = Height(root.getLeft());
		if(root.getRight() != null)
			rightHight = Height(root.getRight());
		return leftHeight - rightHight;
	}
	private void balance(BSTNode node){
		
		if(slopeAt(node) > 1){
			if(slopeAt(node.getLeft()) > 0){
				// LL rotation
				LLRotation(node);
			}else if(slopeAt(node.getLeft()) <= 0){
				// LR rotation
				LRRotation(node);
			}
		}else if(slopeAt(node) < -1){
			if(slopeAt(node.getRight()) < 0){
				// RR rotation
				RRRotation(node);
			}else if(slopeAt(node.getRight()) >= 0){
				// RL rotation
				RLRotation(node);
			}
		}
		
	}
	
	private void LLRotation(BSTNode node){
		BSTNode temp1 = node;
		BSTNode temp2 = node.getLeft();
		BSTNode temp3 = node.getLeft().getRight();
		
		temp1.setLeft(temp3);
		temp2.setRight(temp1);
		
		temp2.setParent(temp1.getParent());
		if(temp1.getParent() == null){
			
		}else if(temp1.getParent().getLeft() == temp1){
			temp1.getParent().setLeft(temp2);
		}else{
			temp1.getParent().setRight(temp2);
		}
		temp1.setParent(temp2);
		if(temp3 != null)
			temp3.setParent(temp1);
		
	}
	
	private void RRRotation(BSTNode node){
		BSTNode temp1 = node;
		BSTNode temp2 = node.getRight();
		BSTNode temp3 = node.getRight().getLeft();
		
		temp1.setRight(temp3);
		temp2.setLeft(temp1);
		
		temp2.setParent(temp1.getParent());
		if(temp1.getParent() == null){
			
		}else if(temp1.getParent().getLeft() == temp1){
			temp1.getParent().setLeft(temp2);
		}else{
			temp1.getParent().setRight(temp2);
		}
		temp1.setParent(temp2);
		if(temp3 != null)
			temp3.setParent(temp1);
		
	}
	
	private void LRRotation(BSTNode node){
		BSTNode A = node;
		BSTNode B = node.getLeft();
		BSTNode C = node.getLeft().getRight();
		
		A.setLeft(C.getRight());
		if(C.getRight() != null)
			C.getRight().setParent(A);
		B.setRight(C.getLeft());
		if(C.getLeft() != null)
			C.getLeft().setParent(B);
		
		C.setLeft(B);
		C.setRight(A);
		
		B.setParent(C);
		C.setParent(A.getParent());
		A.setParent(C);
		
		if(C.getParent() == null){
			root = C;
		}else if(C.getParent().getLeft() == A){
			C.getParent().setLeft(C);
		}else{
			C.getParent().setRight(C);
		}
	}
	
	private void RLRotation(BSTNode node){
		BSTNode A = node;
		BSTNode B = node.getRight();
		BSTNode C = node.getRight().getLeft();
		
		B.setLeft(C.getRight());
		if(C.getRight() != null)
			C.getRight().setParent(B);
		A.setRight(C.getLeft());
		if(C.getLeft() != null)
			C.getLeft().setParent(A);
		
		C.setLeft(A);
		C.setRight(B);
		
		B.setParent(C);
		C.setParent(A.getParent());
		A.setParent(C);
		
		if(C.getParent() == null){
			root = C;
		}else if(C.getParent().getLeft() == A){
			C.getParent().setLeft(C);
		}else{
			C.getParent().setRight(C);
		}
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
