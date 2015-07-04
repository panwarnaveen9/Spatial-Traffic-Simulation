import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
 
public class Testing {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("graph.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //Set the delimiter used in file
        //scanner.useDelimiter(",");
         
        //Get all tokens and store them in some data structure
        //I am just printing them
		String[] edge_info=null;
        while (scanner.hasNext())
        {
        	edge_info = scanner.next().split(",");
//        	for(String data:edge_info)
//        		System.out.print(Float.parseFloat(data) + " # " );
//        	System.out.println();
        	Float data[] = new Float[6];
        	for(int i=0; i<edge_info.length; i++)
        	{
        		data[i] = Float.parseFloat(edge_info[i]);
        		System.out.print(data[i] + "#");
        	}
        	System.out.println();
        }
        System.out.println(edge_info.length);
        // Do not forget to close the scanner 
        scanner.close();
	}

}
