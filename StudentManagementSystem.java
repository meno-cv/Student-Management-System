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

	public static boolean checkBatchStatus(int batchNO){
		
		for (int i = 0; i < batchNos.length; i++){
			if(batchNO == batchNos[i]){
				if(batchStatus[i] == 1){
					return true;
				}				
			}
		}
		return false;
	}
	
	public static boolean checkNICIsExists(String NIC){
		for (int i = 0; i < NICs.length; i++){
			if(NIC.equals(NICs[i])){
				return true;
			}
		}
		return false;
	}
	
	public static String generateStudentId(int userInputBatchNo, int mode){
		
		//student count
		
		int studentCount = 0;
		
		for (int i = 0; i < studentIDs.length; i++){
			String studentId = studentIDs[i];
			String batchNO = studentId.substring(4,7); // OR24110520 -> 110
			
			int batchNo = Integer.parseInt(batchNO);
			
			if(batchNo == userInputBatchNo){
				studentCount++;
			}
		}
		
		String studentId = "";
		
		if(mode == 1){
			studentId = studentId + "PR" + 26 + userInputBatchNo + String.format("%03d" , (studentCount+1)); //PR 26 110 120
		}else{
			studentId = studentId + "OR" + 26 + userInputBatchNo + String.format("%03d" , (studentCount+1));
		}
		
		return studentId;
	}
}

