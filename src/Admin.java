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
                br = new BufferedReader(new FileReader("./Database/AdminRecords.csv"));
            } catch (Exception e) {
                br = new BufferedReader(new FileReader("src/Database/AdminRecords.csv"));
            }
            
            String line;

            while((line = br.readLine()) != null){
                String[] detail = line.split(",");

                adminList.add(new Admin(detail[0], detail[1], detail[2], detail[3], detail[4], detail[5], detail[6]));
            }

            br.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("AdminRecords.csv not found, closing application...");
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
                manageDoctor(patientList, doctorList, appointmentList, billingList, prescriptionList);
                break;
        }
    }
    
    public static void manageDoctor(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList, 
    ArrayList<Appointment> appointmentList, ArrayList<Billing> billingList, ArrayList<Prescription> prescriptionList){
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
                addDoctor(patientList, doctorList, appointmentList, billingList, prescriptionList);
                break;

            }
    }
    
    public static void addDoctor(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList, 
    ArrayList<Appointment> appointmentList, ArrayList<Billing> billingList, ArrayList<Prescription> prescriptionList){
        String name ="", address = "", gender = "", phoneNumber = "", email = "", specialization = "";
        int DocFee = 0;
        // name, address, gender, phonenumber, email, specialization, doctorfee
        Boolean valid = true;

        Scanner s = new Scanner(System.in);
        
        
        // doctor name
        do{
            valid = true;

            System.out.print("Enter doctor name: ");
            name = s.nextLine();
            

            if((name.length() < 5) || hasNumber(name) || validSpace(name)){
                System.out.println("Invalid name! Try again!");
                valid = false;
            } 

        } while(!valid);

        
        // doctor specialization
        do{
            valid = true;
            
            System.out.print("Input specialization: ");
            specialization = s.nextLine();
            
            if(hasNumber(name)){
                System.out.println("Invalid name! Try again!");
                valid = false;
            }
            
        } while(!valid);
        
        
        
        // doctor address
        do{
            valid = true;

            System.out.print("Enter doctor address: ");
            address = s.nextLine();
            
            if((address.length() < 10)){
                System.out.println("Invalid address! Try again!");
                valid = false;
            } 

        } while(!valid);

        
        // doctor gender
        do{

            valid = true;

            System.out.print("Input gender [Male || Female]: ");
            gender = s.nextLine();

            if(!gender.equals("Male") && !gender.equals("Female")){
                System.out.println("Invalid gender! Try again!");
                valid = false;
            } 

        }while(!valid);

        
        // doctor phonenumber
        do{

            valid = true;

            System.out.print("Input Phone Number: ");
            phoneNumber = s.nextLine();

            for(int i = 0; i < doctorList.size(); i++){
                if(doctorList.get(i).getPhoneNumber().equals(phoneNumber)){
                    System.out.println("Duplicate phone number prohibited!");
                    valid = false;
                    break;
                }
            }

            if(phoneNumber.length() < 10 || phoneNumber.length() > 13 || hasLetter(phoneNumber)){
                System.out.println("Invalid number! Try again!");
                valid = false;
            } 

        }while(!valid);

        
        // doctor email
        do{ 

            valid = true;

            System.out.print("Input Email: ");
            email = s.nextLine();

            String regex = "^([\\w\\.-]+)@([\\w-]+)\\.([\\w-]{2,6})(\\.[\\w]{2,6})?$";

            if(!Pattern.matches(regex, email)){
                System.out.println("Invalid email! Try again!");
                valid = false;
            }else {
                for(int i = 0; i < doctorList.size(); i++){
                    if(doctorList.get(i).getEmail().equals(email)){
                        System.out.println("Duplicate email prohibited!");
                        valid = false;
                        break;
                    }
                }
            }
        }while(!valid);
        
        
        
        // doctor fee
        do{
            valid = true;
            
            System.out.print("Input Doctor Fee: ");
            try{
                DocFee = s.nextInt();
                valid = true;
            } catch (Exception e){
                System.out.println("Enter only integer value..." + e);
                valid = false;
            }
            
            
        } while (!valid);
        
        System.out.println("Name: "  + name);
        System.out.println("Specialization " + Specialization);
        System.out.println("Address: " + address);
        System.out.println("Gender: " + gender);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Fee per Month: " + DocFee);

        System.out.print("Reenter details? [Y/N]: ");

        String input = "";

        do{
            valid = true;

            input = s.nextLine();

            if(!input.toLowerCase().equals("y") && !input.toLowerCase().equals("n")){
                System.out.println("Invalid input! Try again!");
                valid = false;
            }

        } while(!valid);

        String doctorID = "";

        String currentID = doctorList.get(doctorList.size() - 1).getDoctorID().substring(2, 5);
        int currentIDNumber = Integer.parseInt(currentID);

        if(doctorList.size() < 10){
            doctorID = String.format("DC00%d", currentIDNumber + 1);
        } else if (doctorList.size() < 100){
            doctorID = String.format("DC0%d", currentIDNumber + 1);
        } else {
            doctorID = String.format("DC%d", currentIDNumber + 1);
        }

        if(input.toLowerCase().equals("y")){
            addDoctor(patientList, doctorList, appointmentList, billingList, prescriptionList);
        } else {

            doctorList.add(new Doctor(name, address, gender, phoneNumber, email, patientID, specialization));

            String writeString = String.format("%s,%s,%s,%s,%s,%s,%s,%d", doctorID, specialization, name, address, gender, phoneNumber, email, DocFee);

            try {
                BufferedWriter bw;

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
                s.nextLine();
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IOException occurred, closing application...");
                s.nextLine();
                System.exit(0);
            }

            System.out.println("Added doctor to database!");
            System.out.println("Press any key to continue...");
            s.nextLine();
            manageDoctor(patientList, doctorList, appointmentList, billingList, prescriptionList);
        }

        s.close();
    }
    
        public static Boolean hasNumber(String str){
        
        Boolean numberExist = false;

        for(int i = 0; i < str.length(); i++){
            if(Character.isDigit(str.charAt(i))){
                numberExist = true;
                break;
            }
        }

        return numberExist;
    }

    public static Boolean validSpace(String str){
        
        int count = 0;

        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ' ') count++;
        }

        if(count < 1){
            return true;
        } else {
            return false;
        }
    }

    public static Boolean hasLetter(String str){
        
        Boolean letterExist = false;

        for(int i = 0; i < str.length(); i++){
            if(Character.isLetter(str.charAt(i))){
                letterExist = true;
                break;
            }
        }

        return letterExist;
    }
}
