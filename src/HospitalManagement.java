import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HospitalManagement {

    //FIX THIS

    //ArrayList<patient> list = new ArrayList<patient>();
	
	// public void showPatient() {
	// 	for(int i=0; i<list.size(); i++) {
	// 	    System.out.println((i + 1) + ". " + list.get(i).patientName);
	// 		System.out.print(list.get(i).patientID);
	// 		System.out.print(list.get(i).doctorID);
	// 		System.out.print(list.get(i).medicineList);
    //    }
	// }
	
	// public void selectPatient() {
	// 	Scanner scanner = new Scanner(System.in);
	// 	System.out.print("Enter the number of the patient to select: ");
		
    //     int selection = scanner.nextInt();
    //     if (selection > 0 && selection <= list.size()) {    	
    //     	System.out.print(list.get(selection - 1));
    //     } 
        
    //     else {
    //         System.out.println("Invalid selection");
    //     }
        
    //     isConsulted = True;

    public static void main(String[] args) throws Exception {
        
        //LOADING PATIENT DATA
        ArrayList<Patient> patientList = new ArrayList<>();
        loadPatient(patientList);

        ArrayList<Appointment> appointmentList = new ArrayList<>();

        //LOAD OTHER DATA HERE

        //RECEPTIONIST MENU -> should be controlled in the login menu
        Receptionist receptionist = new Receptionist("Daniel", "Puri Mansion", "Male", "085280076262", "danielyohanes03@gmail.com", "RC001");
        receptionist.receptionMenu(patientList, appointmentList);

    }

    public static void loadPatient(ArrayList<Patient> patientList){
        try{

            BufferedReader br = new BufferedReader(new FileReader("./Database/PatientRecords.csv"));
            
            String line;

            while((line = br.readLine()) != null){
                String[] detail = line.split(",");
                patientList.add(new Patient(detail[2], detail[3], detail[4], detail[5], detail[6], detail[0], detail[1]));
            }

            br.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("PatientRecords.csv not found, closing application...");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occurred, closing application...");
            System.exit(0);
        }
    }
}
