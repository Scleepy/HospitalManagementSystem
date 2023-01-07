import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Admin extends Person{
    String adminID;

    public Admin(String name, String address, String gender, String phoneNumber, String email, String password, String adminID){
        super(name, address, gender, phoneNumber, email, password);
        this.adminID = adminID;
    }

    public String getAdminID() {
        return this.adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public static void loadAdmin(ArrayList<Admin> adminList){
        try{

            BufferedReader br;

            try {
                br = new BufferedReader(new FileReader("./Database/ReceptionistRecords.csv"));
            } catch (Exception e) {
                br = new BufferedReader(new FileReader("src/Database/ReceptionistRecords.csv"));
            }
            
            String line;

            while((line = br.readLine()) != null){
                String[] detail = line.split(",");

                adminList.add(new Admin(detail[0], detail[1], detail[2], detail[3], detail[4], detail[5], detail[6]));
            }

            br.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("ReceptionistRecords.csv not found, closing application...");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occurred, closing application...");
            System.exit(0);
        }
    }
    
    public static void printManage(){
        System.out.println("1. Manage Doctor");
        System.out.println("2. Manage Nurse");
        System.out.println("3. Manage Reception");
        System.out.print(">> ");
    }

    public static void printManageDoctor(){
        System.out.println("1. Add Doctor");
        System.out.println("2. Remove Doctor");
        System.out.println("3. Show Doctors");
        System.out.print(">> ");
    }

    public static void printManageNurse(){
        System.out.println("1. Add Nurse");
        System.out.println("2. Remove Nurse");
        System.out.println("3. Show Nurses");
        System.out.print(">> ");
    }

    public static void printManageRec(){
        System.out.println("1. Add Receptionist");
        System.out.println("2. Remove Receptionist");
        System.out.println("3. Show Receptionists");
        System.out.print(">> ");
    }
    
    public static void AdminMenu(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList, 
    ArrayList<Appointment> appointmentList, ArrayList<Billing> billingList, ArrayList<Prescription> prescriptionList)
    {
        printManage();
        Scanner s = new Scanner(System.in);
        int option = 0;
        option = s.nextInt();

        while (option < 1 || option > 3){
            printManage();
            option = s.nextInt();
        }

        switch(option){
            // Option 1 Manage Doctor
            case 1:
                printManageDoctor();
                int optionDoc = 0;
                optionDoc = s.nextInt();

                while (optionDoc < 1 || optionDoc > 3){
                    printManageDoctor();
                    optionDoc = s.nextInt();
                }

                switch(optionDoc){
                    // Option 1 Add Doctor
                    case 1:

                }

        }
    }
}
