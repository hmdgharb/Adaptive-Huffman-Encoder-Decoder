import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Huffman_decoder{
	
	private String inputCoded;		//for input data
	private String outputString;	//for outputResult
	
	private node root;
	private node NYT;
	
	private node finded;
	private node fatherFinded;

	public Huffman_decoder(){
		
		NYT = new node();	//indicate NYT
		NYT.setName('`');
		root = NYT;
		finded = null;
		outputString = "";
	}
	
	public void insert(String input){
		int in;
		
		in = Integer.valueOf(input, 2);
		
		Integer.toString(in);
		char a = (char) in;
		
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
			
			addToResult(a);
		}
		else if(NYT != root){
			
			//if our new element is exist in our huffman tree
			if(in == 1){
				
				finded = null;
				fatherFinded = null;
				
				node current = root;
				
				for(int i = 0; i < input.length(); i++){
					
					if(input.charAt(i) == '1'){
						
						
						finded = null;
						fatherFinded = null;
						
						current.getRight().Increment();
						refind(root, current.getRight().getName());
						sortTree();						
						incre(root);
						
						addToResult(finded.getName());
						
						break;
					}
					else{
						
						if(current.getLeft().getName() == '`'){
							break;
						}
						current = current.getLeft();
						
					}
				}
				
			}
			
			//if our new element is not exist in our tree
			else if(in != 1){
				
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
				
				addToResult(a);
			
				finded = null;
				fatherFinded = null;
				refind(root, a);
				
			}
		}
	}
	
	//for converting string binary to integer value
	public int ConvertToCharacter(String input){
		
		return Integer.valueOf(input, 2);
	}
	
	//for getting string from input and add to our tree each element
	public void handleInput() throws IOException{
		
		inputCoded = getString();
		
		boolean selected = false;
		String temp;
		int startIndex, endIndex;
		startIndex = 0;
		
		if(inputCoded.charAt(0) == '0'){
			for(int i = 0; i < inputCoded.length(); i++){
				
				if(selected == false && inputCoded.charAt(i) == ' '){
					selected = true;
					startIndex = i + 1;
				}
				else if(selected == true && inputCoded.charAt(i) == ' '){
					endIndex = i ;
					selected = false;
					i--;
					
					temp = inputCoded.substring(startIndex, endIndex);
					insert(temp);					
					//showInOrderResult();
				}
				else if(selected == true && i == inputCoded.length() - 1){
					
					endIndex = i + 1;
					selected = false;
					temp = inputCoded.substring(startIndex, endIndex);
					insert(temp);
					//showInOrderResult();
				}
			}
		}
	}
	
	//for calling inorder traverse of huffman tree
	public void showInOrderResult(){
		
		inOrder_Display(root);
		System.out.println();
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

	//for getting data from consul
	public String getString() throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader( System.in));
		return br.readLine();
	}
	
	//for sum of character input to outputString
	public void addToResult(char input){
		
		outputString += input;
	}
	
	//for showing final result
	public void displayResult(){
		
		System.out.println("The Huffman_DeCoded String:");
		System.out.println(outputString);
		System.out.println();
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
	public void setString(String in){
		
		inputCoded = in;
		
		boolean selected = false;
		String temp;
		int startIndex, endIndex;
		startIndex = 0;
		
		if(inputCoded.charAt(0) == '0'){
			for(int i = 0; i < inputCoded.length(); i++){
				
				if(selected == false && inputCoded.charAt(i) == ' '){
					selected = true;
					startIndex = i + 1;
				}
				else if(selected == true && inputCoded.charAt(i) == ' '){
					endIndex = i ;
					selected = false;
					i--;
					
					temp = inputCoded.substring(startIndex, endIndex);
					insert(temp);					
					//showInOrderResult();
				}
				else if(selected == true && i == inputCoded.length() - 1){
					
					endIndex = i + 1;
					selected = false;
					temp = inputCoded.substring(startIndex, endIndex);
					insert(temp);
					//showInOrderResult();
				}
			}
		}
	}
	
	public String Result(){
		
		return outputString;
	}
}