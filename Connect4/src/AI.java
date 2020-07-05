import java.util.Random;

public class AI {
	
	//-----Private variables-----
	
	private int final_value;
	private Node root;
	private Node aux;
	private Random rand;
	private static final int MAX_DEPTH = 2;
	
	//-----Functions and methods-----
	
	
	//Getters
	public Node getRoot() { return this.root; }
	public int getFinalValue() { return this.final_value; }
	
	
	//Constructor. It does all the steps to calculate which movement has to do
	public AI(Board b) {
		
		this.rand = new Random();
		this.final_value = 0;
		
		this.aux = new Node();
		
		
		this.root = new Node();
		this.root.setBoard(b);
		this.root.setLevel(0);
		this.root.setNChildren(root.getBoard().numOfChildren());
		this.root.createTree(this.root);
		
		iniciateLeafValues(this.root);
		
		this.final_value = startMiniMax(this.root, 1); 
		
		this.root.setColumn(this.final_value);
	}
	
	
	//It calculates the heutistic value. If the AI won it returns a high value, in case that Player won it returns a low value
	//It doesn't allow you to win the game but it's not enough smart to win the game 
	public int heuristic(Node leaf, Node child) {
		
		int value = rand.nextInt();
		
		if(leaf.getBoard().win()){
			
			value = -1000000000;
			
		} else if(child.getBoard().win()){
			
			value = 1000000000;
			
		}
		
		return value;
		
	}
	
	
	//Depth 2. Initialize the leaf nodes with the heuristic value
	public void iniciateLeafValues(Node root) {
		
		Node child = new Node();
		Node leaf = new Node();
		
		for (int i = 0; i < root.getNChildren(); i++) {
			
			child = root.getChildren(i);
			
			if (child.getLevel() < MAX_DEPTH) {
				
				for (int j = 0; j < child.getNChildren(); j++ ) {
					
					leaf = child.getChildren(j);
					
					if (leaf.getLevel() == MAX_DEPTH) {
						
						leaf.setHeuristicValue(heuristic(leaf,child));
						
					}
				}
			} 
		}	
	}
	
	
	//We need to return the last movement, with the MiniMax algorithm we calculate the best movement that our AI has to do
	public int startMiniMax(Node root, int depth) {

		miniMax(root, depth); 
		return root.getColumn();
	}
	
	
	
	//Algorithm MiniMax with depth 2. It changes the value of heuristic nodes in function of the level. 
	//Level 1 takes de minimun heuristic and lvl 2 takes the maximum value.
	public void miniMax(Node node, int depth) {
		
		if (node.getLevel() < MAX_DEPTH) { 
			
			if (depth%2 != 0) {
				
				for (int i = 0; i < node.getNChildren(); i++) {
					
					if ( node.getChildren(i).getLevel() < MAX_DEPTH) { //lvl 2 takes the maxim value of all the leafs
						
						miniMax(node.getChildren(i), depth+1);
						
						node.setHeuristicValue(node.getChildren(0).getHeuristicValue());
						node.setColumn(node.getChildren(0).getColumn());
						
						max(node);
					}
				}
			} 
			
			else { //lvl 1 takes the minimum value of all the childrens 
				
				node.setHeuristicValue(node.getChildren(0).getHeuristicValue());
				node.setColumn(node.getChildren(0).getColumn());
				
				min(node);
				
			}
		}
	}
	
	
	//If the value of the children is smaller than the father one, it changes the value of the father to the minimum
	public void min(Node n) {
		
		for (int i=0; i< n.getNChildren(); i++) {
			
			if (n.getChildren(i).getHeuristicValue() < n.getHeuristicValue()) {
				
				n.setHeuristicValue(n.getChildren(i).getHeuristicValue());
				n.setColumn(n.getChildren(i).getColumn());
				
			}
		}
		
	}
	
	
	//If the value of the children is bigger than the father one, it changes the value of the father to the maximum
	public void max(Node n) {
		
		for (int j=0; j< n.getNChildren(); j++) {
			
			if (n.getChildren(j).getHeuristicValue() > n.getHeuristicValue()) {
				
				n.setHeuristicValue(n.getChildren(j).getHeuristicValue());
				n.setColumn(n.getChildren(j).getColumn());
				
			}
		}
		
	}

}
