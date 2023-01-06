import java.util.ArrayList;
import java.util.Scanner;

public class HospitalManagement {

    public HospitalManagement(){};

    public static void main(String[] args) throws Exception {
        
        //LOAD PATIENT DATA
        ArrayList<Patient> patientList = new ArrayList<>();
        Patient.loadPatient(patientList);

        //LOAD ADMIN DATA
        ArrayList<Admin> adminList = new ArrayList<>();
        Admin.loadAdmin(adminList);

        //LOAD RECEPTIONIST DATA
        ArrayList<Receptionist> receptionistList = new ArrayList<>();
        Receptionist.loadReceptionist(receptionistList);

        //LOAD DOCTOR DATA
        ArrayList<Doctor> doctorList = new ArrayList<>();
        Doctor.loadDoctors(patientList, doctorList);

        //LOAD PHARMACIST DATA
        ArrayList<Pharmacist> pharmacistList = new ArrayList<>();
        Pharmacist.loadPharmacist(pharmacistList);

        //LOAD MEDICINE DATA
        ArrayList<Medicine> medicineList = new ArrayList<>();
        Medicine.loadMedicine(medicineList);

        //LOAD PRESCRIPTION DATA
        ArrayList<Prescription> prescriptionList = new ArrayList<>();
        Prescription.loadPrescription(prescriptionList, doctorList, medicineList);

        //LOAD APPOINTMENT DATA
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        Appointment.loadAppointments(patientList, doctorList, appointmentList, prescriptionList);

        //LOAD BILLING DATA
        ArrayList<Billing> billingList = new ArrayList<>();
        Billing.loadBilling(billingList, appointmentList);
        
        //LOAD OTHER DATA HERE

        //RECEPTIONIST MENU -> should be controlled in the login menu
        mainMenu(patientList, receptionistList, adminList, doctorList, pharmacistList, medicineList, prescriptionList, appointmentList, billingList);
    }

    public static void mainMenu(ArrayList<Patient> patientList, ArrayList<Receptionist> receptionistList, ArrayList<Admin> adminList, ArrayList<Doctor> doctorList, ArrayList<Pharmacist> pharmacistList, ArrayList<Medicine> medicineList, ArrayList<Prescription> prescriptionList, ArrayList<Appointment> appointmentList, ArrayList<Billing> billingList){

        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        int menu = 0;

        System.out.println("+==============================+");
        System.out.println("|             MENU             |");
        System.out.println("+==============================+");
        System.out.println("| 1. Login                     |");
        System.out.println("| 2. Exit                      |");
        System.out.println("+==============================+");
        System.out.print(">> ");

        try {
            menu = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid Input");
            mainMenu(patientList, receptionistList, adminList, doctorList, pharmacistList, medicineList, prescriptionList, appointmentList, billingList);
        }        

        switch (menu) {
            case 1:
                roleMenu(patientList, receptionistList, adminList, doctorList, pharmacistList, medicineList, prescriptionList, appointmentList, billingList);
                break;
            case 2:
                System.out.println("Thank You!");
                System.exit(0);
                break;
            default:
                mainMenu(patientList, receptionistList, adminList, doctorList, pharmacistList, medicineList, prescriptionList, appointmentList, billingList);
                break;
        }

        
        scanner.close();
    }

    public static void roleMenu(ArrayList<Patient> patientList, ArrayList<Receptionist> receptionistList, ArrayList<Admin> adminList, ArrayList<Doctor> doctorList, ArrayList<Pharmacist> pharmacistList, ArrayList<Medicine> medicineList, ArrayList<Prescription> prescriptionList, ArrayList<Appointment> appointmentList, ArrayList<Billing> billingList){
        
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        int menu = 0;

        System.out.println("+==============================+");
        System.out.println("|             MENU             |");
        System.out.println("+==============================+");
        System.out.println("| 1. Doctor                    |");
        System.out.println("| 2. Receptionist              |");
        System.out.println("| 3. Admin                     |");
        System.out.println("| 4. Pharmacist                |");
        System.out.println("| 5. Exit                      |");
        System.out.println("+==============================+");
        System.out.print(">> ");

        try {
            menu = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid Input");
            mainMenu(patientList, receptionistList, adminList, doctorList, pharmacistList, medicineList, prescriptionList, appointmentList, billingList);
        }        

        switch (menu) {
            case 1:
                menuRedirect(patientList, receptionistList, adminList, doctorList, pharmacistList, medicineList, prescriptionList, appointmentList, billingList, 0);
                break;
            case 2:
                menuRedirect(patientList, receptionistList, adminList, doctorList, pharmacistList, medicineList, prescriptionList, appointmentList, billingList, 1);
                break;
            case 3:
                menuRedirect(patientList, receptionistList, adminList, doctorList, pharmacistList, medicineList, prescriptionList, appointmentList, billingList, 2);
                break;
            case 4:
                menuRedirect(patientList, receptionistList, adminList, doctorList, pharmacistList, medicineList, prescriptionList, appointmentList, billingList, 3);
                break;
            case 5:
                System.out.println("Thank You!");
                System.exit(0);
            default:
                roleMenu(patientList, receptionistList, adminList, doctorList, pharmacistList, medicineList, prescriptionList, appointmentList, billingList);
                break;
        }

        
        scanner.close();

    }

    public static void menuRedirect(ArrayList<Patient> patientList, ArrayList<Receptionist> receptionistList, ArrayList<Admin> adminList, ArrayList<Doctor> doctorList, ArrayList<Pharmacist> pharmacistList, ArrayList<Medicine> medicineList, ArrayList<Prescription> prescriptionList, ArrayList<Appointment> appointmentList, ArrayList<Billing> billingList, int operation){
        
        Scanner scanner = new Scanner(System.in);

        String pass;
    	String user;
    		
    	do {
            System.out.print("userID:  ");
            user = scanner.nextLine();
        }while(user.length() != 5);
    		
    	do {
    		System.out.print("Password: ");
    		pass = scanner.nextLine();
        }while(pass.length() < 5);

        boolean valid = false;

        switch (operation) {
            case 0: //doctor menu
                for(int i = 0; i < doctorList.size(); i++) {
                    if(doctorList.get(i).getPassword().equals(pass) && doctorList.get(i).getDoctorID().equals(user)){
                        System.out.println("Login Successfully");
                        scanner.nextLine();
                        valid = true;
                        //REDIRECT TO MENU
                    } 
                }

                if(valid == false){
                    System.out.println("User/Password Wrong");
                    scanner.nextLine();
                    roleMenu(patientList, receptionistList, adminList, doctorList, pharmacistList, medicineList, prescriptionList, appointmentList, billingList);
                }

                break;
            case 1: //receptionist menu
                for(int i = 0; i < receptionistList.size(); i++) {
                    if(receptionistList.get(i).getPassword().equals(pass) && receptionistList.get(i).getRecID().equals(user)){
                        System.out.println("Login Successfully");
                        scanner.nextLine();
                        valid = true;
                        //REDIRECT TO MENU
                        Receptionist.receptionMenu(patientList, doctorList, appointmentList, billingList, prescriptionList);
                    } 
                }

                if(valid == false){
                    System.out.println("User/Password Wrong");
                    scanner.nextLine();
                    roleMenu(patientList, receptionistList, adminList, doctorList, pharmacistList, medicineList, prescriptionList, appointmentList, billingList);
                }

                break;
            case 2: //admin menu
                for(int i = 0; i < adminList.size(); i++) {
                    if(adminList.get(i).getPassword().equals(pass) && adminList.get(i).getAdminID().equals(user)){
                        System.out.println("Login Successfully");
                        scanner.nextLine();
                        valid = true;
                        //REDIRECT TO MENU
                    }
                }

                if(valid == false){
                    System.out.println("User/Password Wrong");
                    scanner.nextLine();
                    roleMenu(patientList, receptionistList, adminList, doctorList, pharmacistList, medicineList, prescriptionList, appointmentList, billingList);
                }

                break;
            case 3: //pharmacist menu
                for(int i = 0; i < pharmacistList.size(); i++) {
                    if(pharmacistList.get(i).getPassword().equals(pass) && pharmacistList.get(i).getPharID().equals(user)){
                        System.out.println("Login Successfully");
                        scanner.nextLine();
                        valid = true;
                        //REDIRECT TO MENU
                    } 
                }

                if(valid == false){
                    System.out.println("User/Password Wrong");
                    scanner.nextLine();
                    roleMenu(patientList, receptionistList, adminList, doctorList, pharmacistList, medicineList, prescriptionList, appointmentList, billingList);
                }

                break;
            default:
                break;
        }


        scanner.close();
    }

}
