
//class node for use each node in our dynamic Huffman tree
public class node{
	
	private int iteration;
	private char name;
	private node left;
	private node right;
	
	public node(){
	
		name = '.';					//	indicate empty node
		iteration = 0;
	}
	
	public void setLeft(node left){
	
		this.left = left;
	}
	
	public void setRight(node right){
		this.right = right;
	}
	
	public void setName(char name){
		
		this.name = name;
	}
	
	public void setIteration(int iteration){
		
		this.iteration = iteration;
	}
	
	public void Increment(){
		
		iteration++;
	}
	
	public boolean isMatch(char name){
		
		return this.name == name;
	}
	
	public int getIteration(){
		
		return iteration;
	}
	
	public char getName(){
		
		return name;
	}
	
	public node getLeft(){
		
		return left;
	}
	
	public node getRight(){
		
		return right;
	}

}

