import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Pharmacist extends Person{
    String pharID;

    public Pharmacist(String name, String address, String gender, String phoneNumber, String email, String password, String pharID){
        super(name, address, gender, phoneNumber, email, password);
        this.pharID = pharID;
    }

    public String getPharID() {
        return this.pharID;
    }

    public void setPharID(String pharID) {
        this.pharID = pharID;
    }

    public static Pharmacist getPharmacist(ArrayList<Pharmacist> pharmacistList, String pharmacistID){

        int index = -1;

        for(int i = 0; i < pharmacistList.size(); i++){
            if(pharmacistList.get(i).getPharID().equals(pharmacistID)){
                index = i;
                break;
            }
        }

        return pharmacistList.get(index);
    }

    public static void loadPharmacist(ArrayList<Pharmacist> pharmacistList){
        try{

            BufferedReader br;

            try {
                br = new BufferedReader(new FileReader("./Database/PharmacistRecords.csv"));
            } catch (Exception e) {
                br = new BufferedReader(new FileReader("src/Database/PharmacistRecords.csv"));
            }
            
            String line;

            while((line = br.readLine()) != null){
                String[] detail = line.split(",");

                pharmacistList.add(new Pharmacist(detail[0], detail[1], detail[2], detail[3], detail[4], detail[5], detail[6]));
            }

            br.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("PharmacistRecords.csv not found, closing application...");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occurred, closing application...");
            System.exit(0);
        }
    }
    
    public static void pharmacistMenu(ArrayList<Appointment> appoinmentList, boolean operation) {
		
		//SHOW PATIENT
		System.out.println("Pharmacist Menu");
		System.out.println("===============");
		System.out.println("Patient List");
		
		if(isConsulted == true && givenMedicine == false && isDone == false) {
	        System.out.println("|PatientID|PatientName|DoctorID|AppointmentID");
	        for(int i=0; i<patientList.size(); i++){    
	        	System.out.printf("|%s|", appoinmentList.get(i).getPatient().getpatientID());
	            System.out.printf("%s|", appoinmentList.get(i).getPatient().getName());
	            System.out.printf("%s|", appoinmentList.get(i).getDoctorID());
	            System.out.printf("%s|", appoinmentList.get(i).getAppoinmentID());
	            System.out.printf("%s|", appoinmentList.get(i).getPrescription().getprescriptionID());
	        } 
		}
		
		else {
			System.out.println("No Data");
		}
		
		//SELECT PATIENT
		System.out.print("Enter the PatientID: ");
		
		int selection;
		selection = scanner.nextLine();
		scanner.nextLine();
		
		if (selection.length() < 5) {
			System.out.println("Invalid selection");
		}
		
		else {
			for(int i=0; i<appointmentList.size(); i++) {
				if(appointmentList.get(i).getPatient().getpatientID() == selection) {
					System.out.println("Medicine List");
					System.out.printf("Medicine ID: %s\n", appoinmentList.get(i).getMedicine().getmedicineID());
					System.out.printf("Medicine Name: %s\n", appoinmentList.get(i).getMedicine().getmedicineName());
					System.out.printf("Medicine Quantity: %d\n", appoinmentList.get(i).getMedicine().getmedicineQuantity());
					System.out.printf("Medicine Description: %s\n", appoinmentList.get(i).getMedicine().getmedicineDescription());
					System.out.printf("Medicine Intruction: %s\n", appoinmentList.get(i).getMedicine().getmedicineIntruction());
					System.out.printf("Medicine Price: %d\n", appoinmentList.get(i).getMedicine().getmedicinePrice());
				}

			}
		
		}
	    
		givenMedicine = True;      
	}
   
}
