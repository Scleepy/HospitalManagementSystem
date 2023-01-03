import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Receptionist extends Person{
    String recID;

    public Receptionist(String name, String address, String gender, String phoneNumber, String email, String recID){
        super(name, address, gender, phoneNumber, email);
        this.recID = recID;
    }

    public String getRecID() {
        return this.recID;
    }

    public void setRecID(String recID) {
        this.recID = recID;
    }

    public static void receptionMenu(ArrayList<Patient> patientList, ArrayList<Appointment> appointmentList){

        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss");

        String currentDateTime = dateTime.format(dateTimeFormat);

        System.out.println("Reception Menu");
        System.out.println(currentDateTime);

        System.out.println("1. Manage Patient");
        System.out.println("2. Manage Appointment");
        System.out.println("3. Create Payment");
        System.out.println("4. Logout");
        System.out.print(">> ");

        int choice = 0;

        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            receptionMenu(patientList, appointmentList);
        }

        switch(choice){
            case 1:
                managePatients(patientList, appointmentList);
                break;
            case 2:
                manageAppointment(appointmentList);
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                receptionMenu(patientList, appointmentList);
                break;
        }

        scanner.close();
    }

    public static void managePatients(ArrayList<Patient> patientList, ArrayList<Appointment> appointmentList){

        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Show All Patients");
        System.out.println("2. Register Patient");
        System.out.println("3. Update Patient");
        System.out.println("4. Delete Patient");
        System.out.println("5. Back");
        System.out.print(">> ");

        int choice = 0;

        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            receptionMenu(patientList, appointmentList);
        }

        switch(choice){
            case 1:
                if(patientList.size() == 0){
                    System.out.println("NO PATIENT DATA");
                    scanner.nextLine();
                    managePatients(patientList, appointmentList);
                } else {
                    showPatients(patientList);
                }

                System.out.println("Press any key to continue...");
                scanner.nextLine();
                managePatients(patientList, appointmentList);

                break;
            case 2:
                registerPatient(patientList, appointmentList);
                break;
            case 3:
                if(patientList.size() == 0){
                    System.out.println("NO PATIENT DATA");
                    scanner.nextLine();
                    managePatients(patientList, appointmentList);
                } else {
                    deleteUpdatePatientMenu(patientList, appointmentList, 0);
                }

                break;
            case 4:
                if(patientList.size() == 0){
                    System.out.println("NO PATIENT DATA");
                    scanner.nextLine();
                    managePatients(patientList, appointmentList);
                } else {
                    deleteUpdatePatientMenu(patientList, appointmentList, 1);
                }

                break;
            case 5:
                receptionMenu(patientList, appointmentList);
                break;
            default:
                managePatients(patientList, appointmentList);
                break;
        }

        scanner.close();
    }

    public static void showPatients(ArrayList<Patient> patientList){
        
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("|============================================PATIENTS====================================================================|");
            System.out.println("|PatientID|BloodType|Name                  |Address                  |Gender|Phone Number |Email                         |");
            for(int i = 0; i < patientList.size(); i++){
                
                System.out.printf("|%-9s|", patientList.get(i).getPatientID());
                System.out.printf("%-9s|", patientList.get(i).getBloodType());
                System.out.printf("%-22s|", patientList.get(i).getName());
                System.out.printf("%-25s|", patientList.get(i).getAddress());
                System.out.printf("%-6s|", patientList.get(i).getGender());
                System.out.printf("%-13s|", patientList.get(i).getPhoneNumber());
                System.out.printf("%-30s|", patientList.get(i).getEmail());
                System.out.println();
            }
        }


    public static void registerPatient(ArrayList<Patient> patientList, ArrayList<Appointment> appointmentList){
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        String name = "", bloodType = "", address = "", gender = "", phoneNumber = "", email = "";
        Boolean valid = true;

        do{
            valid = true;

            System.out.print("Enter patient name: ");
            name = scanner.nextLine();
            

            if((name.length() < 5) || hasNumber(name) || validSpace(name)){
                System.out.println("Invalid name! Try again!");
                valid = false;
            } 

        } while(!valid);

        do{
            valid = true;

            System.out.print("Enter patient blood type: ");
            bloodType = scanner.nextLine();

            if(!bloodType.equals("A") && !bloodType.equals("B") && !bloodType.equals("AB") && !bloodType.equals("O")){
                System.out.println("Invalid blood type! Try again!");
                valid = false;
            } 

        } while(!valid);



        do{
            valid = true;

            System.out.print("Enter patient address: ");
            address = scanner.nextLine();
            
            if((address.length() < 10)){
                System.out.println("Invalid address! Try again!");
                valid = false;
            } 

        } while(!valid);

        do{

            valid = true;

            System.out.print("Input gender [Male || Female]: ");
            gender = scanner.nextLine();

            if(!gender.equals("Male") && !gender.equals("Female")){
                System.out.println("Invalid gender! Try again!");
                valid = false;
            } 

        }while(!valid);

        do{

            valid = true;

            System.out.print("Input Phone Number: ");
            phoneNumber = scanner.nextLine();

            for(int i = 0; i < patientList.size(); i++){
                if(patientList.get(i).getPhoneNumber().equals(phoneNumber)){
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

        do{ 

            valid = true;

            System.out.print("Input Email: ");
            email = scanner.nextLine();

            String regex = "^([\\w\\.-]+)@([\\w-]+)\\.([\\w-]{2,6})(\\.[\\w]{2,6})?$";

            if(!Pattern.matches(regex, email)){
                System.out.println("Invalid email! Try again!");
                valid = false;
            }else {
                for(int i = 0; i < patientList.size(); i++){
                    if(patientList.get(i).getEmail().equals(email)){
                        System.out.println("Duplicate email prohibited!");
                        valid = false;
                        break;
                    }
                }
            }
        }while(!valid);
        
        
        System.out.println("Name: "  + name);
        System.out.println("Address: " + address);
        System.out.println("Gender: " + gender);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);

        System.out.print("Reenter details? [Y/N]: ");

        String input = "";

        do{
            valid = true;

            input = scanner.nextLine();

            if(!input.toLowerCase().equals("y") && !input.toLowerCase().equals("n")){
                System.out.println("Invalid input! Try again!");
                valid = false;
            }

        } while(!valid);

        String patientID = "";

        String currentID = patientList.get(patientList.size() - 1).getPatientID().substring(2, 5);
        int currentIDNumber = Integer.parseInt(currentID);

        System.out.println(currentID);

        if(patientList.size() < 10){
            patientID = String.format("PA00%d", currentIDNumber + 1);
        } else if (patientList.size() < 100){
            patientID = String.format("PA0%d", currentIDNumber + 1);
        } else {
            patientID = String.format("PA%d", currentIDNumber + 1);
        }

        if(input.toLowerCase().equals("y")){
            registerPatient(patientList, appointmentList);
        } else {

            patientList.add(new Patient(name, address, gender, phoneNumber, email, patientID, bloodType));

            String writeString = String.format("%s,%s,%s,%s,%s,%s,%s", patientID, bloodType, name, address, gender, phoneNumber, email);

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("./Database/PatientRecords.csv",true));
                bw.write(writeString);
                bw.newLine();
                bw.close();

            } catch (FileNotFoundException e){
                e.printStackTrace();
                System.out.println("PatientRecords.csv not found, closing application...");
                scanner.nextLine();
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IOException occurred, closing application...");
                scanner.nextLine();
                System.exit(0);
            }

            System.out.println("Added patient to database!");
            System.out.println("Press any key to continue...");
            scanner.nextLine();
            managePatients(patientList, appointmentList);
        }

        scanner.close();
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

    public static void deleteUpdatePatientMenu(ArrayList<Patient> patientList, ArrayList<Appointment> appointmentList, int operation){

        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);
        showPatients(patientList);

        if(operation == 1){
            System.out.println("1. Delete Patient");
        } else {
            System.out.println("1. Update Patient");
        }

        System.out.println("2. Back");
        System.out.print(">> ");

        int choice = 0;

        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input!");
            scanner.nextLine(); scanner.nextLine();
            if(operation == 1){
                deleteUpdatePatientMenu(patientList, appointmentList, 1);
            } else {
                deleteUpdatePatientMenu(patientList, appointmentList, 0);
            }
        }
        
        switch(choice){
            case 1:
                if(operation == 1){
                    deletePatient(patientList, appointmentList);
                } else {
                    updatePatient(patientList, appointmentList);
                }

                break;
            case 2:
                managePatients(patientList, appointmentList);
                break;
            default:
                if(operation == 1){
                    deleteUpdatePatientMenu(patientList, appointmentList, 1);
                } else {
                    deleteUpdatePatientMenu(patientList, appointmentList, 0);
                }

                break;
            }

        scanner.close();
    }

    public static int searchPatient(ArrayList<Patient> patientList, String inputPatientID){

        int index = -1;

        for(int i = 0; i < patientList.size(); i++){
            if(patientList.get(i).getPatientID().equals(inputPatientID)){
                index = i;
                break;
            }
        }

        return index;
    }

    public static void deletePatient(ArrayList<Patient> patientList, ArrayList<Appointment> appointmentList){

        Scanner scanner = new Scanner(System.in);

        Boolean valid = false;
        String inputPatientID = "";

        System.out.print("Enter patientID: ");
        inputPatientID = scanner.nextLine();

        int index = searchPatient(patientList, inputPatientID);

        if(index != -1){

            System.out.print("Delete patient? [Y/N]: ");

            String input = "";

            do{
                valid = true;

                input = scanner.nextLine();

                if(!input.toLowerCase().equals("y") && !input.toLowerCase().equals("n")){
                    System.out.println("Invalid input! Try again!");
                    valid = false;
                }

            } while(!valid);

            if(input.toLowerCase().equals("y")){

                patientList.remove(index);

                updatePatientDatabase(patientList);

                System.out.println("Patient data deleted successfully!");

                System.out.println("Press any key to continue...");
                scanner.nextLine();
                deleteUpdatePatientMenu(patientList, appointmentList, 1);            
            } else {
                deleteUpdatePatientMenu(patientList, appointmentList, 1);
            }

        } else {
            System.out.println("Patient not found!");
            scanner.nextLine();
            deleteUpdatePatientMenu(patientList, appointmentList, 1);
        }

        scanner.close();

    }

    public static void updatePatientDatabase(ArrayList<Patient> patientList){

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./Database/PatientRecords.csv", false));
            bw.write("");

            for(int i = 0; i < patientList.size(); i++){

                String patientID = patientList.get(i).getPatientID();
                String bloodType = patientList.get(i).getBloodType();
                String name = patientList.get(i).getName();
                String address = patientList.get(i).getAddress();
                String gender = patientList.get(i).getGender();
                String phoneNumber = patientList.get(i).getPhoneNumber();
                String email = patientList.get(i).getEmail();

                String writeString = String.format("%s,%s,%s,%s,%s,%s,%s", patientID, bloodType, name, address, gender, phoneNumber, email);
                
                try {
                    bw = new BufferedWriter(new FileWriter("./Database/PatientRecords.csv",true));
                    bw.write(writeString);
                    bw.newLine();
                    bw.close();
    
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                    System.out.println("PatientRecords.csv not found, closing application...");
                    System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("IOException occurred, closing application...");
                    System.exit(0);
                }            
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("PatientRecords.csv not found, closing application...");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occurred, closing application...");
            System.exit(0);
        }

        
    }

    public static void updatePatient(ArrayList<Patient> patientList, ArrayList<Appointment> appointmentList){

        Scanner scanner = new Scanner(System.in);

        Boolean valid = false;
        String inputPatientID = "";

        System.out.print("Enter patientID: ");
        inputPatientID = scanner.nextLine();

        int index = searchPatient(patientList, inputPatientID);

        if(index != -1){

            System.out.print("Update patient? [Y/N]: ");

            String input = "";

            do{
                valid = true;

                input = scanner.nextLine();

                if(!input.toLowerCase().equals("y") && !input.toLowerCase().equals("n")){
                    System.out.println("Invalid input! Try again!");
                    valid = false;
                }

            } while(!valid);

            if(input.toLowerCase().equals("y")){
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println("|============================================PATIENT DETAIL==============================================================|");
                System.out.println("|PatientID|BloodType|Name                  |Address                  |Gender|Phone Number |Email                         |");                    
                System.out.printf("|%-9s|", patientList.get(index).getPatientID());
                System.out.printf("%-9s|", patientList.get(index).getBloodType());
                System.out.printf("%-22s|", patientList.get(index).getName());
                System.out.printf("%-25s|", patientList.get(index).getAddress());
                System.out.printf("%-6s|", patientList.get(index).getGender());
                System.out.printf("%-13s|", patientList.get(index).getPhoneNumber());
                System.out.printf("%-30s|", patientList.get(index).getEmail());
                System.out.println();

                System.out.println("Choose field to update");
                System.out.println("1. BloodType");
                System.out.println("2. Name");
                System.out.println("3. Address");
                System.out.println("4. Gender");
                System.out.println("5. Phone Number");
                System.out.println("6. Email");
                System.out.print(">> ");

                int choice = 0;

                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                } catch (Exception e) {
                    updatePatient(patientList, appointmentList);
                }

                String name = "", bloodType = "", address = "", gender = "", phoneNumber = "", email = "";
                valid = true;

                switch(choice){
                    case 1:
                        do{
                            valid = true;
                
                            System.out.print("Enter patient blood type: ");
                            bloodType = scanner.nextLine();
                
                            if(!bloodType.equals("A") && !bloodType.equals("B") && !bloodType.equals("AB") && !bloodType.equals("O")){
                                System.out.println("Invalid blood type! Try again!");
                                valid = false;
                            } 
                        } while(!valid);

                        patientList.get(index).setBloodType(bloodType);
                        updatePatientDatabase(patientList);
                        deleteUpdatePatientMenu(patientList, appointmentList, 0);

                        break;
                    case 2:
                        do{
                            valid = true;
                
                            System.out.print("Enter patient name: ");
                            name = scanner.nextLine();
                            
                            if((name.length() < 5) || hasNumber(name) || validSpace(name)){
                                System.out.println("Invalid name! Try again!");
                                valid = false;
                            } 
                
                        } while(!valid);

                        patientList.get(index).setName(name);
                        updatePatientDatabase(patientList);
                        deleteUpdatePatientMenu(patientList, appointmentList, 0);

                        break;
                    case 3:

                        do{
                            valid = true;
                
                            System.out.print("Enter patient address: ");
                            address = scanner.nextLine();
                            
                            if((address.length() < 10)){
                                System.out.println("Invalid address! Try again!");
                                valid = false;
                            } 
                
                        } while(!valid);

                        patientList.get(index).setAddress(address);
                        updatePatientDatabase(patientList);
                        deleteUpdatePatientMenu(patientList, appointmentList, 0);

                        break;
                    case 4:
                        do{
                            valid = true;
                
                            System.out.print("Input gender [Male || Female]: ");
                            gender = scanner.nextLine();
                
                            if(!gender.equals("Male") && !gender.equals("Female")){
                                System.out.println("Invalid gender! Try again!");
                                valid = false;
                            } 
                
                        }while(!valid);

                        patientList.get(index).setGender(gender);
                        updatePatientDatabase(patientList);
                        deleteUpdatePatientMenu(patientList, appointmentList, 0);

                        break;

                    case 5:

                        do{
                            valid = true;
                
                            System.out.print("Input Phone Number: ");
                            phoneNumber = scanner.nextLine();
                
                            for(int i = 0; i < patientList.size(); i++){
                                if(patientList.get(i).getPhoneNumber().equals(phoneNumber)){
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

                        patientList.get(index).setPhoneNumber(phoneNumber);
                        updatePatientDatabase(patientList);
                        deleteUpdatePatientMenu(patientList, appointmentList, 0);

                        break;
                    case 6:

                        do{ 

                            valid = true;
                
                            System.out.print("Input Email: ");
                            email = scanner.nextLine();
                
                            String regex = "^([\\w\\.-]+)@([\\w-]+)\\.([\\w-]{2,6})(\\.[\\w]{2,6})?$";
                
                            if(!Pattern.matches(regex, email)){
                                System.out.println("Invalid email! Try again!");
                                valid = false;
                            }else {
                                for(int i = 0; i < patientList.size(); i++){
                                    if(patientList.get(i).getEmail().equals(email)){
                                        System.out.println("Duplicate email prohibited!");
                                        valid = false;
                                        break;
                                    }
                                }
                            }
                        }while(!valid);

                        patientList.get(index).setEmail(email);
                        updatePatientDatabase(patientList);
                        deleteUpdatePatientMenu(patientList, appointmentList, 0);

                        break;
                    default:
                        deleteUpdatePatientMenu(patientList, appointmentList, 0);

                        break;
                }

            } else {
                deleteUpdatePatientMenu(patientList, appointmentList, 0);
            }

        } else {
            System.out.println("Patient not found!");
            deleteUpdatePatientMenu(patientList, appointmentList, 0);
        }

        scanner.close();
    }

    public static void manageAppointment(ArrayList<Appointment> appointmentList){

        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        //CREATE APPOINTMENT
        //SHOW APPOINTMENTS -> ACTIVE/PAST

        System.out.println("1. Show Appointments");
        System.out.println("2. Create Appointment");
        System.out.print(">> ");

        int choice = 0;

        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            manageAppointment(appointmentList);
        }

        switch(choice){
            case 1:
                break;
            case 2:
                break;
            default:
            manageAppointment(appointmentList);
                break;
        }

        scanner.close();

    }

    public static void showAppointmentsMenu(ArrayList<Appointment> appointmentList){
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Show Active Appointments");
        System.out.println("2. Show Past Appointments");

        int choice = 0;

        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            showAppointmentsMenu(appointmentList);
        }

        switch(choice){
            case 1:
                if(appointmentList.size() == 0){
                    System.out.println("NO ACTIVE APPOINTMENTS");
                    scanner.nextLine();
                } else {
                    showAppointments(appointmentList, false);
                }

                break;
            case 2:
                if(appointmentList.size() == 0){
                    System.out.println("NO PAST APPOINTMENTS");
                    scanner.nextLine();
                } else {
                    showAppointments(appointmentList, true);
                }

                break;
            default:
                showAppointmentsMenu(appointmentList);
                break;
        }

        scanner.close();
    }

    public static void showAppointments(ArrayList<Appointment> appointmentList, boolean operation){

        System.out.print("\033[H\033[2J");
        System.out.flush();

        for(int i = 0; i < appointmentList.size(); i++){
            if(appointmentList.get(i).getIsDone() == operation){
                System.out.println("|AppointmentID|Patient Name             | Date & Time | Doctor Name | Emergency | Consulted | Given Medicine | Done |");                    
                System.out.printf("|%-9s|", appointmentList.get(i).getAppointmentID());
                System.out.printf("%-9s|", appointmentList.get(i).getPatient().getName());
                System.out.printf("%-9s", appointmentList.get(i).getDate() + appointmentList.get(i).getTime());
                System.out.printf("%-9s", appointmentList.get(i).getDoctor().getName());
                System.out.printf("%-9s", appointmentList.get(i).getEmergency());
                System.out.printf("%-9s", appointmentList.get(i).getIsConsulted());
                System.out.printf("%-9s", appointmentList.get(i).getGivenMedicine());
                System.out.printf("%-9s", appointmentList.get(i).getIsDone());
            } 
            
        }
    }
}
