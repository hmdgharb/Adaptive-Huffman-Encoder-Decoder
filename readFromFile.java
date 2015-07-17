import java.io.*;
import java.util.*;

//class readFromFile for reading file for decode or incode process
public class readFromFile {

	private String fileName;
	private File myReadFile, myWriteFile;
	private Scanner scan;
	private FileWriter write;
	
	public void handleInput() throws IOException{
		
		char type;
		do{
			System.out.println("Please insert one of numbers {1, 2, 3}:\n 1) coding your File by adaptive huffman algorithm.\n 2) decoding your File by adaptive huffman algorithm.\n 3) exit for this program.");
			type = getString().charAt(0);
		}while(type != '1' && type != '2' && type != '3');
		if(type == '1'){
			
			
			do{
				System.out.println("Please insert name of file for coding to Huffman_coded file");
				fileName = getString();
			}while(fileName.equals(""));
			
			readFileForHuffmanCoding(fileName);
			
		}
		else if(type == '2') {
			
			do{
				System.out.println("please insert name of file for decoding to Huffman_decoded file");
				fileName = getString();
			}while(fileName.equals(""));
			readFileForHuffmanDecoding(fileName);
		}
		else if(type == '3') {
			
			System.out.println("thank you for your effort :)\n");
		}
	}
	
	//read file for converting to huffman code
	public void readFileForHuffmanCoding(String name) throws IOException{
		try{
			myReadFile = new File(name);
			scan = new Scanner(myReadFile);
		}catch(Exception e){
			
			System.out.println("Error: the " + name + " file is not exist.");
		}
		try{
			myWriteFile = new File("Huffman_coded");
			
			write = new FileWriter(myWriteFile);

		}catch(Exception e){
			
			System.out.println("Error: the Huffman_coded file cannot to be created.");
		}
		
			
		String	input = "";
		Huffman_coder temp = new Huffman_coder();
		
		while(scan.hasNext()){
			input += scan.nextLine();
			
		}
		temp.setString(input);
		write.write(temp.Result());
		
		scan.close();
		write.close();
		
		System.out.println("The result of coding is exist in file Huffman_coded.");
	}
	
	//read file for convert to huffman decode
	public void readFileForHuffmanDecoding(String name) throws IOException{
		
		try{
			myReadFile = new File(name);
			scan = new Scanner(myReadFile);
		}catch(Exception e){
			
			System.out.println("Error: the " + name + " file is not exist.");
		}
		
		try{
			myWriteFile = new File("Huffman_decoded");
			write = new FileWriter(myWriteFile);
		}catch(Exception e){
			
			System.out.println("Error: the Huffman_decoded file cannot to be created.");
		}
		
		String	input = "";
		Huffman_decoder temp = new Huffman_decoder();
		
		while(scan.hasNext()){
			input += scan.nextLine();
			
		}
		temp.setString(input);
		write.write(temp.Result());
		
		scan.close();
		write.close();
		System.out.println("The result of decoding is exist in file Huffman_decoded.");
	}
	
	//for getting input name file from consul
	public String getString() throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}
}
