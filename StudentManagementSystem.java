import java.util.*;
class StudentManagementSystem{
	
	public static String[] studentIDs= new String[0];
	public static String[] studentNames= new String[0];
	public static String[] NICs= new String[0];
	public static int[] PRFMarks= new int[0];
	public static int[] DBMSMarks= new int[0];
	
	public static int[] batchNos = {100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110};
	public static int[] batchStatus = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}; 
	
	public final static void clearConsole() {   
		try {  
			final String os = System.getProperty("os.name");   
			if (os.contains("Windows")) {  
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();  
			} else {  
				System.out.print("\033[H\033[2J");   
				System.out.flush();  
			}  
		} catch (final Exception e) {  
			e.printStackTrace();  
		}  
	}
	
	
}

