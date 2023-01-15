import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Doctor extends Person{
    String doctorID;
    String specialization;
    ArrayList<Patient> patientList = new ArrayList<>();
    Integer doctorFee;

    public Doctor(){};

    public Doctor(String name, String address, String gender, String phoneNumber, String email, String password, String doctorID, String specialization, ArrayList<Patient> patient, Integer doctorFee){
        super(name, address, gender, phoneNumber, email, password);
        this.doctorID = doctorID;
        this.specialization = specialization;
        this.patientList = patient;
        this.doctorFee = doctorFee;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(ArrayList<Patient> patientList) {
        this.patientList = patientList;
    }

    public Integer getDoctorFee() {
        return doctorFee;
    }

    public void setDoctorFee(Integer doctorFee) {
        this.doctorFee = doctorFee;
    }
    
    public static Doctor getDoctor(ArrayList<Doctor> doctorList, String doctorID){

        int index = -1;

        for(int i = 0; i < doctorList.size(); i++){
            if(doctorList.get(i).getDoctorID().equals(doctorID)){
                index = i;
                break;
            }
        }

        return doctorList.get(index);
    }

    public static int searchDoctor(ArrayList<Doctor> doctorList, String inputDoctorID){

        int index = -1;

        for(int i = 0; i < doctorList.size(); i++){
            if(doctorList.get(i).getDoctorID().equals(inputDoctorID)){
                index = i;
                break;
            }
        }

        return index;
    }

    public static void loadDoctors(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList){
        try{

            BufferedReader br;

            try {
                br = new BufferedReader(new FileReader("./Database/DoctorRecords.csv"));
            } catch (Exception e) {
                br = new BufferedReader(new FileReader("src/Database/DoctorRecords.csv"));
            }
            
            String line;

            while((line = br.readLine()) != null){
                String[] detail = line.split(",");

                ArrayList<Patient> doctorPatientList = new ArrayList<Patient>();

                String[] patientArr = detail[8].split("#");

                for(String patientID : patientArr){   
                    doctorPatientList.add(Patient.getPatient(patientList, patientID));
                }

                doctorList.add(new Doctor(detail[0], detail[1], detail[2], detail[3], detail[4], detail[5], detail[6], detail[7], doctorPatientList, Integer.parseInt(detail[9])));
            }

            br.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("DoctorRecords.csv not found, closing application...");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occurred, closing application...");
            System.exit(0);
        }
    }

    public static void updateDoctorDatabase(ArrayList<Doctor> doctorList){

        try {
            BufferedWriter bw;
        
            try {
                bw = new BufferedWriter(new FileWriter("./Database/DoctorRecords.csv",false));
            } catch (Exception e) {
                bw = new BufferedWriter(new FileWriter("src/Database/DoctorRecords.csv",false));
            }

            bw.write("");

            for(int i = 0; i < doctorList.size(); i++){

                String name = doctorList.get(i).getName();
                String address = doctorList.get(i).getAddress();
                String gender = doctorList.get(i).getGender();
                String phoneNumber = doctorList.get(i).getPhoneNumber();
                String email = doctorList.get(i).getEmail();
                String password = doctorList.get(i).getPassword();

                String doctorID = doctorList.get(i).getDoctorID();
                String specialization = doctorList.get(i).getSpecialization();

                ArrayList<Patient> doctorPatientList = doctorList.get(i).getPatientList();
                String doctorPatientListString = "";
                
                for(int j = 0; j < doctorPatientList.size(); j++){
                    doctorPatientListString = doctorPatientListString + doctorPatientList.get(j).getPatientID();

                    if(j < doctorPatientList.size() - 1){
                        doctorPatientListString = doctorPatientListString + "#";
                    }
                }                

                String doctorFee = String.valueOf(doctorList.get(i).getDoctorFee());

                String writeString = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", name, address, gender, phoneNumber, email, password, doctorID, specialization, doctorPatientListString, doctorFee);
                
                try {
                    try {
                        bw = new BufferedWriter(new FileWriter("./Database/DoctorRecords.csv",true));
                    } catch (Exception e) {
                        bw = new BufferedWriter(new FileWriter("src/Database/DoctorRecords.csv",true));
                    }

                    bw.write(writeString);
                    bw.newLine();
                    bw.close();
    
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                    System.out.println("DoctorRecords.csv not found, closing application...");
                    System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("IOException occurred, closing application...");
                    System.exit(0);
                }            
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("DoctorRecords.csv not found, closing application...");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occurred, closing application...");
            System.exit(0);
        }
    }




    public static void doctorMenu(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList, ArrayList<Prescription> prescriptionList, ArrayList<Appointment> appointmentList) throws InterruptedException {
    	Scanner input = new Scanner(System.in);
    	int choice;
    
	    do {
	    	System.out.println("1. Show Patient's data");
	    	System.out.println("2. Log out");
	    	System.out.print(">> ");
	    	
	    	choice = input.nextInt();
	    	input.nextLine();
	    	
	    	switch(choice) 
	    	{    		
	    		case 1:
	    			if (patientList.size() == 0) {
	    				System.out.println("There is no Patient");
	    			}
	    			else {
	    				System.out.println("|PatientID|BloodType|Name                  |Gender|");
		    			for (int i = 0 ; i < patientList.size() ; i++) {
		    				System.out.printf("|%-9s|", patientList.get(i).getPatientID());
		                    System.out.printf("%-9s|", patientList.get(i).getBloodType());
		                    System.out.printf("%-22s|", patientList.get(i).getName());
		                    System.out.printf("%-6s|", patientList.get(i).getGender());
		                    System.out.println();
		    			}
		    		
		    			System.out.println("Enter Patient ID");
		    			System.out.print(">> ");
		    			String choice2 = input.nextLine();
		    			
		    			int index = Patient.searchPatient(patientList, choice2);
		    			
		    			if(index != -1) {
		    				System.out.print("Patient ID found");
		    				
		    				for(int i = 0 ; i < appointmentList.size() ; i++) {
		    					if(appointmentList.get(i).getIsConsulted() != true) {
									appointmentList.get(i).setIsConsulted(true);
									
									System.out.println("Checking the disease.....");
				    				TimeUnit.SECONDS.sleep(3);
				    				System.out.println("Disease Found!");
				    				TimeUnit.SECONDS.sleep(1);
				    				
				    				Random rand = new Random();
				    		    	String[] arrayDisease = {"Flu", "Influenza", "Sakit Tenggorokan", "Demam", "Cold"};
				    		    	String disease = arrayDisease[rand.nextInt(arrayDisease.length)];
				    		    	
				    		    	System.out.println("Patient ID " + patientList.get(index).getPatientID() + "has " + disease);
				    		    	
				    		    	// Finding the doctor
				    		    	
				    		    	for(int j = 0 ; j < doctorList.size() ; j++) {
				    		    		System.out.println("Finding the doctor...");
					    				TimeUnit.SECONDS.sleep(3);
					    				
				    		    		if(doctorList.get(j).getPatientList().size() != 5) {
				    		    			System.out.println("FOUND!");
				    		    			
				    		    			if(doctorList.get(j).getSpecialization().equals("Umum") && !doctorList.get(j).getPatientList().equals(choice2)) {
				    		    				System.out.println("Patient " + choice2 + "'s doctor is " + doctorList.get(j).getDoctorID() + "(Umum)");
					    		    			doctorList.get(j).getPatientList().add(Patient.getPatient(patientList, choice2));
					    		    			updateDoctorDatabase(doctorList);
					    		    		}
				    		    			else if(!doctorList.get(j).getSpecialization().equals("Umum") && !doctorList.get(j).getPatientList().equals(choice2)) {
				    		    				System.out.println("Patient " + choice2 + "'s doctor is " + doctorList.get(j).getDoctorID() + "(" + doctorList.get(j).getSpecialization() + ")");
				    		    				doctorList.get(j).getPatientList().add(Patient.getPatient(patientList, choice2));
					    		    			updateDoctorDatabase(doctorList);
				    		    			}
					    		    		else {
					    		    			System.out.println("Doctor " + doctorList.get(j).getDoctorID() + "already have patient " + choice2);
					    		    		}
				    		    			
				    		    			// Give medicine and prescription
				    		    			for (int k = 0 ; k < appointmentList.size() ; k++) {
				    		    				if(appointmentList.get(k).getPatient().equals(choice2)) {
				    		    					if((disease.equals("Flu") || disease.equals("Sakit Tenggorokan")) && appointmentList.get(k).getPrescription().equals(null)) {
				    		    						appointmentList.get(k).getPrescription().setPrescriptionID("PR002");
				    		    						appointmentList.get(k).setGivenMedicine(true);
				    		    						appointmentList.get(k).setIsDone(true);
				    		    						System.out.println("Treatment is complete");
						    		    			}
						    		    			else if(disease.equals("Demam") && appointmentList.get(k).getPrescription().equals(null)) {
						    		    				appointmentList.get(k).getPrescription().setPrescriptionID("PR003");
						    		    				appointmentList.get(k).setGivenMedicine(true);
						    		    				appointmentList.get(k).setIsDone(true);
						    		    				System.out.println("Treatment is complete");
						    		    			}
						    		    			else if(disease.equals("Influenza") && appointmentList.get(k).getPrescription().equals(null)) {
						    		    				appointmentList.get(k).getPrescription().setPrescriptionID("PR001");
						    		    				appointmentList.get(k).setGivenMedicine(true);
						    		    				appointmentList.get(k).setIsDone(true);
						    		    				System.out.println("Treatment is complete");
						    		    			}
						    		    			else {
						    		    				System.out.println("No medicine will be given");
						    		    				appointmentList.get(k).setIsDone(true);
						    		    				TimeUnit.SECONDS.sleep(3);
						    		    				System.out.println("Treatment is complete");
						    		    			}
				    		    				}
				    		    			}
				    		    		}
				    		    		else {
				    		    			System.out.println("Doctor " + doctorList.get(j).getDoctorID() + "already have 5 patients");
				    		    		}
				    		    	}
											
				    			}
		    					else {
		    						System.out.println("Already Consulted");
		    						TimeUnit.SECONDS.sleep(3);
		    						
		    						appointmentList.get(i).setIsDone(true);
		    						System.out.println("Treatment is done");
		    					}
		    				}
		    				
		    				

		    			}
		    			else {
		    				System.out.println("Patient ID not found!");
		    			}
	    			}
	    			
	    			break;
	    	}
	    	
	    } while (choice != 2);
	    System.out.println("Logged out");
    }
    
}
