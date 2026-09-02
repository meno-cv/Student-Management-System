import java.util.*;

class StudentManagementSystem {

	public static String[] studentIDs = new String[0];
	public static String[] studentNames = new String[0];
	public static String[] NICs = new String[0];
	public static int[] PRFMarks = new int[0];
	public static int[] DBMSMarks = new int[0];

	public static int[] batchNos = { 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110 };
	public static int[] batchStatus = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1 };

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
			// Handle any exceptions.
		}
	}

	public static void extendBatchArrays() {
		int[] tempBatchNos = new int[batchNos.length + 1];
		int[] tempBatchStatus = new int[batchStatus.length + 1];

		for (int i = 0; i < batchNos.length + 1; i++) {
			tempBatchNos[i] = batchNos[i];
			tempBatchStatus[i] = batchStatus[i];
		}

		batchNos = tempBatchNos;
		batchStatus = tempBatchStatus;

	}

	public static void extendArrays() {
		String[] tempStudentIDs = new String[studentIDs.length + 1];
		String[] tempStudentNames = new String[studentNames.length + 1];
		String[] tempNICs = new String[NICs.length + 1];
		int[] tempPRFMarks = new int[PRFMarks.length + 1];
		int[] tempDBMSMarks = new int[DBMSMarks.length + 1];

		for (int i = 0; i < studentIDs.length; i++) {
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

	public static boolean checkBatchStatus(int batchNO) {

		for (int i = 0; i < batchNos.length; i++) {
			if (batchNO == batchNos[i]) {
				if (batchStatus[i] == 1) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean checkNICIsExists(String NIC) {
		for (int i = 0; i < NICs.length; i++) {
			if (NIC.equals(NICs[i])) {
				return true;
			}
		}
		return false;
	}

	public static String generateStudentId(int userInputBatchNo, int mode) {

		// student count
		int studentCount = 0;

		for (int i = 0; i < studentIDs.length; i++) {
			String studentId = studentIDs[i];
			String batchNO = studentId.substring(4, 7); // OR24110520 -> 110

			int batchNo = Integer.parseInt(batchNO);

			if (batchNo == userInputBatchNo) {
				studentCount++;
			}
		}

		String studentId = "";

		if (mode == 1) {
			studentId = studentId + "PR" + 26 + userInputBatchNo + String.format("%03d", (studentCount + 1)); // PR 26
																												// 110
																												// 120
		} else {
			studentId = studentId + "OR" + 26 + userInputBatchNo + String.format("%03d", (studentCount + 1));
		}

		return studentId;
	}

	public static void addStudentDetailsToArrays(String studentId, String name, String NIC) {
		studentIDs[studentIDs.length - 1] = studentId;
		studentNames[studentNames.length - 1] = name;
		NICs[NICs.length - 1] = NIC;
		PRFMarks[PRFMarks.length - 1] = -2;
		DBMSMarks[DBMSMarks.length - 1] = -2;
	}

	public static void addStudent() {

		Scanner input = new Scanner(System.in);

		do {
			System.out.println("-----------------------------------------------------------------");
			System.out.println("|\t\t\t\tAdd Student\t\t\t|");
			System.out.println("-----------------------------------------------------------------\n");

			System.out.println();

			System.out.print("Enter batch Number(Student should be added): ");
			int batchNO = input.nextInt();

			System.out.println();

			if (checkBatchStatus(batchNO)) {

				System.out.print("Enter Student NIC : ");
				String NIC = input.next();

				System.out.println();

				if (checkNICIsExists(NIC)) {
					System.out.println("\tThis student is already added to the system...");
					continue;
				} else {
					System.out.print("Enter Student name : ");
					String studentName = input.next();

					System.out.println();

					System.out.print("Enter Lecturer Mode(1 - PHYSICAL / 0 - ONLINE) : ");
					int lecturerMode = input.nextInt();

					System.out.println();

					String studentId = generateStudentId(batchNO, lecturerMode);

					System.out.println("\tStudent Registration No - " + studentId);

					extendArrays();

					// NIC,studentName,studentId
					addStudentDetailsToArrays(studentId, studentName, NIC);

					System.out.println();

					System.out.print("Do you want to add another student (Y/N): ");
					char retryOption = input.next().charAt(0);

					if (retryOption == 'Y' || retryOption == 'y') {
						clearConsole();
						continue;
					} else {
						clearConsole();
						studentManagement();
					}

				}

			} else {
				System.out.println();
				System.out.println("\t Student cannot be added to this course because enrollment is closed....");

				System.out.println();
				System.out.print("Do you want to add student to another  batch (Y/N): ");
				char retryOption = input.next().charAt(0);

				if (retryOption == 'Y' || retryOption == 'y') {
					clearConsole();
					continue;
				} else {
					clearConsole();
					return;
				}
			}
		} while (true);

	}

	// Add BATCH 

	public static void addBatch() {
		Scanner input = new Scanner(System.in);

		do {
			System.out.println("------------------------------------------------");
			System.out.println("|\t\t\tAdd Batch\t\t\t|");
			System.out.println("------------------------------------------------\n");

			int newBatchNo = batchNos[batchNos.length - 1] + 1;

			System.out.println("New Batch Number : " + newBatchNo);

			System.out.print("\nDo you want to add this batch (Y/N): ");
			char option = input.next().charAt(0);

			if (option == 'Y' || option == 'y') {

				extendBatchArrays();

				batchNos[batchNos.length - 1] = newBatchNo;
				batchStatus[batchStatus.length - 1] = 1;

				System.out.println();
				System.out.println("\tBatch added successfully...");

			} else {
				System.out.println();
				System.out.println("\tBatch adding cancelled...");
			}

			System.out.println();
			System.out.print("Do you want to add another batch? : ");
			char retryOption = input.next().charAt(0);

			if (retryOption == 'Y' || retryOption == 'y') {
				clearConsole();
				continue;

			} else {
				clearConsole();
				return;
			}

		} while (true);
	}

	// Update Batch

	public static void updateBatch() {
		Scanner input = new Scanner(System.in);

		do {

			System.out.println("------------------------------------------------");
			System.out.println("|\t\t\tUpdate Batch\t\t\t|");
			System.out.println("------------------------------------------------\n");

			System.out.print("Enter Batch Number : ");
			int batchNO = input.nextInt();

			int index = -1;

			for (int i = 0; i < batchNos.length; i++) {

				if (batchNos[i] == batchNO) {
					index = i;
					break;
				}
			}

			if (index >= 0) {

				System.out.println();
				System.out.println("\tBatch Number : " + batchNos[index]);

				if (batchStatus[index] == 1) {
					System.out.println("\tBatch Status : ENROLLMENT--OPEN");

				} else {
					System.out.println("\tBatch Status : ENROLLMENT---CLOSED");
				}

				System.out.println();

				System.out.println("[1] ENROLLMENT--OPEN");
				System.out.println("[0] ENROLLMENT--CLOSED");

				System.out.print("\nEnter new status : ");
				int status = input.nextInt();

				if (status == 1 || status == 0) {

					batchStatus[index] = status;

					System.out.println();
					System.out.println("\tBatch status updated successfully...");

				} else {
					System.out.println();
					System.out.println("\tInvalid status...");
				}

			} else {
				System.out.println();
				System.out.println("\tThis batch does not exist in the system!");
			}

			System.out.println();
			System.out.print("Do you want to update another batch? : ");
			char retryOption = input.next().charAt(0);

			if (retryOption == 'Y' || retryOption == 'y') {
				clearConsole();
				continue;

			} else {
				clearConsole();
				return;
			}

		} while (true);
	}

	// View Batch

	public static void viewBatches() {
		Scanner input = new Scanner(System.in);

		do {
			System.out.println("-----------------------------------------------------------------");
			System.out.println("|\t\t\tView Batches\t\t\t\t|");
			System.out.println("-----------------------------------------------------------------\n");

			System.out.println("Batch No\tStudent Count\tStatus");
			System.out.println("------------------------------------------------");

			for (int i = 0; i < batchNos.length; i++) {
				
				int studentCount = 0;
				

				for (int j = 0; j < studentIDs.length; j++) {

					String studentID = studentIDs[j];
					int batchNO = Integer.parseInt(studentID.substring(4, 7));

					if (batchNO == batchNos[i]) {
						studentCount++;
					}
				}

				String status;

				if (batchStatus[i] == 1) {
					status = "ENROLLMENT--OPEN!";

				} else {
					status = "ENROLLMENT--CLOSED!";
				}

				System.out.println(batchNos[i] + "\t\t" + studentCount + "\t\t" + status);
			}

			System.out.println();
			System.out.print("Do you want to stay in View Batches? : ");
			char option = input.next().charAt(0);

			if (option == 'Y' || option == 'y') {
				clearConsole();
			} else {
				clearConsole();
				return;
			}

		} while (true);
	}

	public static int isRegistrationNoExists(String registrationNO) {
		for (int i = 0; i < studentIDs.length; i++) {
			if (registrationNO.equals(studentIDs[i])) {
				return i;
			}
		}
		return -1;

	}

	public static void printStudentFullDeatils(int index) {
		System.out.println("\tRegistration No     : " + studentIDs[index]);
		System.out.println("\tStudent Name        : " + studentNames[index]);
		System.out.println("\tStudent NIC         : " + NICs[index]);

		if (PRFMarks[index] == -2) {
			System.out.println("\tStudent PRF Marks   : Not Conducted");
		} else if (PRFMarks[index] == -1) {
			System.out.println("\tStudent PRF Marks   : Absent");
		} else {
			System.out.println("\tStudent PRF Marks   : " + PRFMarks[index]);
		}

		if (DBMSMarks[index] == -2) {
			System.out.println("\tStudent DBMS Marks   : Not Conducted");
		} else if (DBMSMarks[index] == -1) {
			System.out.println("\tStudent DBMS Marks   : Absent");
		} else {
			System.out.println("\tStudent DBMS Marks   : " + DBMSMarks[index]);
		}

		System.out.println("\tStudent GPA         : " + calculateGPA(index));
	}

	public static double getGPA(int marks) {
		if (marks >= 90) {
			return 4.25;
		} else if (marks >= 80) {
			return 4.00;
		} else if (marks >= 75) {
			return 3.70;
		} else if (marks >= 70) {
			return 3.30;
		} else if (marks >= 65) {
			return 3.00;
		} else if (marks >= 60) {
			return 2.70;
		} else if (marks >= 55) {
			return 2.30;
		} else if (marks >= 50) {
			return 2.00;
		} else if (marks >= 45) {
			return 1.70;
		} else if (marks >= 40) {
			return 1.30;
		} else if (marks >= 30) {
			return 1.00;
		} else {
			return 0.70;
		}
	}

	public static double calculateGPA(int index) {

		int prfMarks = PRFMarks[index];
		int dbmsMarks = DBMSMarks[index];

		double prfGPA = 0.0;
		double dbmsGPA = 0.0;

		if (prfMarks >= 0) {
			prfGPA = getGPA(prfMarks);
		}

		if (dbmsMarks >= 0) {
			dbmsGPA = getGPA(dbmsMarks);
		}

		double GPA = (prfGPA + dbmsGPA) / 2;

		return GPA;
	}

	public static void viewStudentProfile() {
		Scanner input = new Scanner(System.in);

		do {
			System.out.println("------------------------------------------------");
			System.out.println("|\t\tView Student's Profile \t\t|");
			System.out.println("------------------------------------------------\n");

			System.out.println();

			System.out.print("Enter Student Registration No : ");
			String registrationNo = input.next().toUpperCase();

			System.out.println();

			int index = isRegistrationNoExists(registrationNo);

			if (index >= 0) {

				printStudentFullDeatils(index);

				System.out.println();

				System.out.print("Do you want to search another student details(Y/N): ");
				char retryOption = input.next().charAt(0);

				if (retryOption == 'Y' || retryOption == 'y') {
					clearConsole();
					continue;
				} else {
					clearConsole();
					studentManagement();
					;
				}

			} else {
				System.out.println();
				System.out.println("\t This student does not exists in the system...");

				System.out.println();
				System.out.print("Do you want to search another student details (Y/N): ");
				char retryOption = input.next().charAt(0);

				if (retryOption == 'Y' || retryOption == 'y') {
					clearConsole();
					continue;
				} else {
					studentManagement();
					return;
				}
			}
		} while (true);

	}

	public static void studentManagement() {
		Scanner input = new Scanner(System.in);

		System.out.println("-----------------------------------------------------------------");
		System.out.println("|\t\t\tStudent Management\t\t\t|");
		System.out.println("-----------------------------------------------------------------\n");

		System.out.println("[1] Add Student");
		System.out.println("\n[2] Update Student ");
		System.out.println("\n[3] View Student Profile");
		System.out.println("\n[4] Delete Student Profile");
		System.out.println("\n[5] Exit");

		System.out.println();

		System.out.print("Enter an option to continue : ");
		int studentOption = input.nextInt();

		switch (studentOption) {
			case 1: {
				clearConsole();
				addStudent();
			}
				break;
			case 2: {
				clearConsole();
				updateStudent();
			}
				break;
			case 3: {
				clearConsole();
				viewStudentProfile();
			}
				break;
			case 4: {
				clearConsole();
				deleteStudent();
			}
				break;
			case 5: {
				clearConsole();
				main(null);
			}
				break;

		}
	}

	public static void updateStudent() {
		Scanner input = new Scanner(System.in);

		do {

			System.out.println("------------------------------------------------");
			System.out.println("|\t\tUpdate Student Details\t\t|");
			System.out.println("------------------------------------------------\n");

			System.out.print("Enter Student Registration No. : ");
			String registrationNo = input.next().toUpperCase();

			int index = isRegistrationNoExists(registrationNo);

			if (index >= 0) {

				System.out.println();
				System.out.println("\tRegistration No : " + studentIDs[index]);
				System.out.println("\tStudent Name    : " + studentNames[index]);
				System.out.println("\tStudent NIC     : " + NICs[index]);

				System.out.println();
				System.out.println("[1] Update Student Name");
				System.out.println("[2] Update Student NIC");

				System.out.println();
				System.out.print("Enter an option to continue : ");
				int option = input.nextInt();

				if (option == 1) {

					System.out.print("\nEnter new Student Name : ");
					String studentName = input.next();

					studentNames[index] = studentName;

					System.out.println();
					System.out.println("\tStudent Name updated successfully.....");

				} else if (option == 2) {

					System.out.print("\nEnter new Student NIC : ");
					String NIC = input.next();

					if (checkNICIsExists(NIC)) {

						System.out.println();
						System.out.println("\tThis NIC already exists in the system..");

					} else {

						NICs[index] = NIC;

						System.out.println();
						System.out.println("\tStudent NIC updated successfully....");
					}

				} else {
					System.out.println("\n\tInvalid option...");
				}

			} else {
				System.out.println();
				System.out.println("\tThis student not exist in the system...");
			}

			System.out.println();
			System.out.print("Do you want to update another student? : ");
			char retryOption = input.next().charAt(0);

			if (retryOption == 'Y' || retryOption == 'y') {

				clearConsole();
				continue;

			} else {

				clearConsole();
				return;
			}

		} while (true);
	}

	public static void deleteStudent() {
		Scanner input = new Scanner(System.in);

		do {

			System.out.println("------------------------------------------------");
			System.out.println("|\t\tDelete Student Profile\t\t|");
			System.out.println("------------------------------------------------\n");

			System.out.print("Enter Student Registration No. : ");
			String registrationNo = input.next().toUpperCase();

			int index = isRegistrationNoExists(registrationNo);

			if (index >= 0) {

				System.out.println();
				printStudentFullDeatils(index);

				System.out.println();
				System.out.print("Are you sure you want to delete this student (Y/N): ");
				char deleteOption = input.next().charAt(0);

				if (deleteOption == 'Y' || deleteOption == 'y') {

					String[] tempStudentIDs = new String[studentIDs.length - 1];
					String[] tempStudentNames = new String[studentNames.length - 1];
					String[] tempNICs = new String[NICs.length - 1];

					int[] tempPRFMarks = new int[PRFMarks.length - 1];
					int[] tempDBMSMarks = new int[DBMSMarks.length - 1];

					for (int i = 0; i < index; i++) {

						tempStudentIDs[i] = studentIDs[i];
						tempStudentNames[i] = studentNames[i];
						tempNICs[i] = NICs[i];
						tempPRFMarks[i] = PRFMarks[i];
						tempDBMSMarks[i] = DBMSMarks[i];
					}

					for (int i = index; i < tempStudentIDs.length; i++) {

						tempStudentIDs[i] = studentIDs[i + 1];
						tempStudentNames[i] = studentNames[i + 1];
						tempNICs[i] = NICs[i + 1];
						tempPRFMarks[i] = PRFMarks[i + 1];
						tempDBMSMarks[i] = DBMSMarks[i + 1];
					}

					studentIDs = tempStudentIDs;
					studentNames = tempStudentNames;
					NICs = tempNICs;
					PRFMarks = tempPRFMarks;
					DBMSMarks = tempDBMSMarks;

					System.out.println();
					System.out.println("\tStudent deleted successfully...");

				} else {

					System.out.println();
					System.out.println("\tStudent deletion cancelled...");
				}

			} else {

				System.out.println();
				System.out.println("\tThis student not exist in the system...");
			}

			System.out.println();
			System.out.print("Do you want to delete another student (Y/N): ");
			char retryOption = input.next().charAt(0);

			if (retryOption == 'Y' || retryOption == 'y') {
				clearConsole();
				continue;

			} else {

				clearConsole();
				return;
			}

		} while (true);
	}

	public static void main(String args[]) {

		Scanner input = new Scanner(System.in);

		System.out.println("-----------------------------------------------------------------");
		System.out.println("|\t\t\tiCET Student Management System\t\t|");
		System.out.println("-----------------------------------------------------------------\n");

		System.out.println("[1] Student Management");
		System.out.println("\n[2] Batch Management ");
		System.out.println("\n[3] Grade Management");
		System.out.println("\n[4] Report Generator");
		System.out.println("\n[5] Exit");

		System.out.println();

		System.out.print("Enter an option to continue : ");
		int mainOption = input.nextInt();

		switch (mainOption) {
			case 1: {
				clearConsole();
				studentManagement();
			}
				break;
			case 2: {

			}
				break;
			case 3: {

			}
				break;
			case 4: {

			}
				break;
			case 5: {
				System.exit(0);
			}
				break;
			default: {
				System.out.println("Invalid Option.....");
				System.exit(0);
			}
		}

	}
}
