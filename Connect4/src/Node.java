
public class Node {
	
	//-----Private variables-----
	
	private Board board;
	private Node children[];
	private int n_children;
	private double heuristic_value;
	private int level;
	private int column;
	
	//-----Functions and methods-----
	
	
	//Getters
	public Board getBoard() { return this.board; }
	public void setBoard(Board b) { this.board = b;}
	public int getNChildren() { return this.n_children; }
	public void setNChildren(int n) {this.n_children = n;}
	public double getHeuristicValue() { return this.heuristic_value; }
	public void setHeuristicValue(double value) { this.heuristic_value = value; }
	public int getLevel() {return this.level;}
	public void setLevel(int level) {this.level = level;}
	public int getColumn() {return this.column;}
	public void setColumn(int c) { this.column = c;}
	public Node getChildren(int i) {
		return children[i];
	}
	
	
	//Constructor.  Initializes all the variables
	public Node() {
		
		this.board = new Board(8,8);
		this.n_children = 0;
		this.heuristic_value = 0;
		this.level = 0;
		this.column = 0;
		this.children = new Node[8];
	}
	
	
	//Creates a Node with the same board as his father. If the node is from level 1, the AI it suppose to do the movement. 
	//Level 2 Nodes have no childrens and it suppose the players movement.
	public Node createNode(Node parent, int numChild, int level) {
		
		Node node = new Node();
		
		node.board.copyBoard( parent.board.getMatrixBoard() );
		node.setColumn(numChild);//It saves the column
		
		if (level<2) {
			node.board.introduceToken(numChild, 2); //lvl 1 AI movement to introduce column
			node.n_children = node.board.numOfChildren();
			node.setLevel(level);
		}
		else {
			node.board.introduceToken(numChild, 1); //lvl2 Player movement to introduce column
			node.n_children = 0;
			node.setLevel(level);
		}
		return node;
	}
	
	
	//It generates childrens for a node parent
	public void createChildren(Node parent, int level) {
		int i;
		for (i = 0; i < parent.n_children; i++) {
			parent.children[i] = createNode(parent, i , level);
		}
	}
	
	
	//Creates the structure of tree with all the nodes
	public void createTree(Node root) {
		
		createChildren(root,1);
		
		for (int i = 0; i < root.n_children; i++) {
			createChildren(root.children[i],2);
		}
	}
	
}
