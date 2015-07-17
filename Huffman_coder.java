import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Huffman_coder{	
	
	private node root;
	private node NYT;
	
	private node finded;
	private node fatherFinded;

	private String inputed;		//for input data
	private String outputCoded;	//for out put result
	
	public Huffman_coder(){
	
		NYT = new node();	//indicate NYT
		NYT.setName('`');
		root = NYT;
		finded = null;
		outputCoded = "0";
	}
	
	//for displaying Inorder traverse of tree
	private void inOrder_Display(node rt){
		
		if(rt == null){
			return;
		}
		else{
			
			
			inOrder_Display(rt.getLeft());
			
			char name = rt.getName();
			
			if(rt == root){
				System.out.print(" root(" + rt.getIteration() + ")");
			}
			else if(name == '.'){
				System.out.print(" temp(" + rt.getIteration() + ")");
			}
			else if(name == '`'){
				System.out.print(" NYT(" + rt.getIteration() + ")");
			}
			else{
				System.out.print(" " + rt.getName() + "(" + rt.getIteration() + ")");
			}
			
			inOrder_Display(rt.getRight());
		}
	}
	
	//for finding finded and fatherFinded of character name in our huffman tree
	private void refind(node rt, char name){
		
		if(rt == null){
			
			return ;
		}
		
		else if(finded != null){
			
			return ;
		}
		
		else{
			
			
			
			refind(rt.getLeft(), name);
			
			if(rt.getRight() != null){
				if(rt.getRight().getName() == name){
				
					fatherFinded = rt;
					finded = rt.getRight();
					return ;
				}
			}
			refind(rt.getRight(), name);
		}
	}
	
	public void insert(char a){

		//if first time come to our insert method
		if(root == NYT){
			
			node left, right;
			
			left = new node();
			right = new node();
			
			right.setName(a);
			right.Increment();
			
			left.setName(NYT.getName());

			NYT.setName('.');
			
			NYT.setLeft(left);
			NYT.setRight(right);
			
			NYT = NYT.getLeft();
			
			incre(root);
			
			addToResult(ConvertToBinaryString((int)a));
		}
		
		else{
			
			finded = null;
			fatherFinded = null;
			
			refind(root, a);
			
			//if our new element is not exist in our tree
			if(finded == null){	//the character a is not in our tree
				
				node left, right;
				
				left = new node();
				right = new node();
				
				right.setName(a);
				right.Increment();
				
				left.setName(NYT.getName());
				
				NYT.setName('.');		// . character indicate temporary node
				
				NYT.setLeft(left);
				NYT.setRight(right);
				
				NYT = NYT.getLeft();
				
				incre(root);
				
				addToResult(ConvertToBinaryString((int)a));
			}
			
			//if our new element is exist in our huffman tree
			else{
				
				addToResult(findTreePath());
				
				finded.Increment();
				
				sortTree();
				
				incre(root);
				
			}
		}
	}
	
	//for finding the path of each new element that exsit in our tree and added new element to our dynamic huffman tree from the tree 
	public String findTreePath(){
		
		node current = root;
		String path = "";
		boolean quit = false;
		
		while(!quit){
			
			if(current == NYT){
				quit = false;
			}
			else if(current.getRight().getName() == finded.getName()){
				path += "1";
				quit = true;
			}
			else{
				path += "0";
			}
			
			current = current.getLeft();
		}
		return path;
		
	}
	public String ConvertToBinaryString(int in){
		
		return Integer.toBinaryString(in);
		
	}
	
	//for summing String in to our outputCoded String
	public void addToResult(String in){
		outputCoded += " " + in;
		
	}
	
	//for sorting huffman tree according to finded and fatherFinded node
	public void sortTree(){
		
		node smaller, fatherSmaller;
		
		boolean quit;
		quit = false;
		
		fatherSmaller = smaller = root;
		while(!quit){
			
			smaller = fatherSmaller.getRight();
			if(smaller == finded){
				quit = true;
			}
			else if(smaller.getIteration() < finded.getIteration()){
				
				fatherFinded.setRight(smaller);
				fatherSmaller.setRight(finded);
				quit = true;
				
			}
			else{
				fatherSmaller = fatherSmaller.getLeft();
			}
		}
	}
	
	//for update our middle node according to his child iteration
	private void incre(node rt){
		
		if(rt == NYT){
			
			return;
		}
		else {
			
			incre(rt.getLeft());
			rt.setIteration(rt.getLeft().getIteration() + rt.getRight().getIteration());
		}
	}
	
	//for getting string from input and add to our tree each element
	public void handleInput()throws IOException{
		
		
		inputed = getString();
		
		for(int i = 0; i < inputed.length(); i++){
			
			insert(inputed.charAt(i));
			//showInOrderResult();
		}
	}
	
	//for getting data from consul
	public String getString() throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}
	
	//for calling inorder traverse of huffman tree
	public void showInOrderResult(){
		
		inOrder_Display(root);
		System.out.println();
		
	}
	
	//for showing final result
	public void displayResult(){
		
		System.out.println("The Huffman_Coded String:");
		System.out.println(outputCoded);
		System.out.println();
	}
	
	public void setString(String in){
		
		inputed = in;
		
		for(int i = 0; i < inputed.length(); i++){
			
			insert(inputed.charAt(i));
			//showInOrderResult();
		}
	}
	
	public String Result(){
		
		return outputCoded;
	}
}