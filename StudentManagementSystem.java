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
	
	public static void extendBatchArrays(){
		int[] tempBatchNos = new int[batchNos.length+1];
		int[] tempBatchStatus = new int[batchStatus.length+1];
		
		for (int i = 0; i < batchNos.length+1; i++){
			tempBatchNos[i] = batchNos[i];
			tempBatchStatus[i] = batchStatus[i];
		}
		
		batchNos = tempBatchNos;
		batchStatus = tempBatchStatus;
		
	}
	
	public static void extendArrays(){
		String[] tempStudentIDs= new String[studentIDs.length+1];
		String[] tempStudentNames= new String[studentNames.length+1];
		String[] tempNICs= new String[NICs.length+1];
		int[] tempPRFMarks= new int[PRFMarks.length+1];
		int[] tempDBMSMarks= new int[DBMSMarks.length+1];
		
		
		
		for (int i = 0; i < studentIDs.length; i++){
			tempStudentIDs[i] = studentIDs[i];
			tempStudentNames[i] = studentNames[i];
			tempNICs[i] = NICs[i];
			tempPRFMarks[i] = PRFMarks[i];
			tempDBMSMarks[i] = DBMSMarks[i];

		}
		
		studentIDs = tempStudentIDs;
		studentNames = tempStudentNames;
		NICs = tempNICs;
		PRFMarks = tempPRFMarks;
		DBMSMarks = tempDBMSMarks; 

	}
}

