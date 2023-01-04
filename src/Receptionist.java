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

    public static void receptionMenu(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList, ArrayList<Appointment> appointmentList){

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
            receptionMenu(patientList, doctorList, appointmentList);
        }

        switch(choice){
            case 1:
                managePatients(patientList, doctorList, appointmentList);
                break;
            case 2:
                manageAppointment(patientList, doctorList, appointmentList);
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                receptionMenu(patientList, doctorList, appointmentList);
                break;
        }

        scanner.close();
    }

    public static void managePatients(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList, ArrayList<Appointment> appointmentList){

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
            receptionMenu(patientList, doctorList, appointmentList);
        }

        switch(choice){
            case 1:
                if(patientList.size() == 0){
                    System.out.println("NO PATIENT DATA");
                    scanner.nextLine();
                    managePatients(patientList, doctorList, appointmentList);
                } else {
                    showPatients(patientList);
                }

                System.out.println("Press any key to continue...");
                scanner.nextLine();
                managePatients(patientList, doctorList, appointmentList);

                break;
            case 2:
                registerPatient(patientList, doctorList, appointmentList);
                break;
            case 3:
                if(patientList.size() == 0){
                    System.out.println("NO PATIENT DATA");
                    scanner.nextLine();
                    managePatients(patientList, doctorList, appointmentList);
                } else {
                    deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 0);
                }

                break;
            case 4:
                if(patientList.size() == 0){
                    System.out.println("NO PATIENT DATA");
                    scanner.nextLine();
                    managePatients(patientList, doctorList, appointmentList);
                } else {
                    deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 1);
                }

                break;
            case 5:
                receptionMenu(patientList, doctorList, appointmentList);
                break;
            default:
                managePatients(patientList, doctorList, appointmentList);
                break;
        }

        scanner.close();
    }

    public static void showPatients(ArrayList<Patient> patientList){
        
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("=============================================PATIENTS=====================================================================");
        System.out.println("|PatientID|BloodType|Name                  |Address                  |Gender|Phone Number |Email                         |");
        System.out.println("==========================================================================================================================");

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


    public static void registerPatient(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList, ArrayList<Appointment> appointmentList){
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
            registerPatient(patientList, doctorList, appointmentList);
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
            managePatients(patientList, doctorList, appointmentList);
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

    public static void deleteUpdatePatientMenu(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList, ArrayList<Appointment> appointmentList, int operation){

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
                deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 1);
            } else {
                deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 0);
            }
        }
        
        switch(choice){
            case 1:
                if(operation == 1){
                    deletePatient(patientList, doctorList, appointmentList);
                } else {
                    updatePatient(patientList, doctorList, appointmentList);
                }

                break;
            case 2:
                managePatients(patientList, doctorList, appointmentList);
                break;
            default:
                if(operation == 1){
                    deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 1);
                } else {
                    deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 0);
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

    public static void deletePatient(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList, ArrayList<Appointment> appointmentList){

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
                deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 1);            
            } else {
                deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 1);
            }

        } else {
            System.out.println("Patient not found!");
            scanner.nextLine();
            deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 1);
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

    public static void updatePatient(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList, ArrayList<Appointment> appointmentList){

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
                    updatePatient(patientList, doctorList, appointmentList);
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
                        deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 0);

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
                        deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 0);

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
                        deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 0);

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
                        deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 0);

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
                        deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 0);

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
                        deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 0);

                        break;
                    default:
                        deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 0);

                        break;
                }

            } else {
                deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 0);
            }

        } else {
            System.out.println("Patient not found!");
            deleteUpdatePatientMenu(patientList, doctorList, appointmentList, 0);
        }

        scanner.close();
    }

    public static void manageAppointment(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList, ArrayList<Appointment> appointmentList){

        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        //CREATE APPOINTMENT
        //SHOW APPOINTMENTS -> ACTIVE/PAST

        System.out.println("1. Show Appointments");
        System.out.println("2. Create Appointment");
        System.out.println("3. Back");
        System.out.print(">> ");

        int choice = 0;

        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            manageAppointment(patientList, doctorList, appointmentList);
        }

        switch(choice){
            case 1:
                showAppointmentsMenu(patientList, doctorList, appointmentList);
                break;
            case 2:
                createAppointment(patientList, doctorList, appointmentList);
                break;
            case 3:
                receptionMenu(patientList, doctorList, appointmentList);
                break;
            default:
                manageAppointment(patientList, doctorList, appointmentList);
                break;
        }

        scanner.close();

    }

    public static void showAppointmentsMenu(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList, ArrayList<Appointment> appointmentList){
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Show Active Appointments");
        System.out.println("2. Show Past Appointments");
        System.out.println("3. Back");
        System.out.print(">> ");

        int choice = 0;

        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            showAppointmentsMenu(patientList, doctorList, appointmentList);
        }

        switch(choice){
            case 1:
                if(appointmentList.size() == 0){
                    System.out.println("NO ACTIVE APPOINTMENTS");
                    scanner.nextLine();
                } else {
                    showAppointments(appointmentList, false);

                    System.out.println("Press any key to continue...");
                    scanner.nextLine();
                    showAppointmentsMenu(patientList, doctorList, appointmentList);
                }

                break;
            case 2:
                if(appointmentList.size() == 0){
                    System.out.println("NO PAST APPOINTMENTS");
                    scanner.nextLine();
                } else {
                    showAppointments(appointmentList, true);

                    System.out.println("Press any key to continue...");
                    scanner.nextLine();
                    showAppointmentsMenu(patientList, doctorList, appointmentList);
                }

                break;
            case 3:
                manageAppointment(patientList, doctorList, appointmentList);
                break;
            default:
                showAppointmentsMenu(patientList, doctorList, appointmentList);
                break;
        }

        scanner.close();
    }

    public static void showAppointments(ArrayList<Appointment> appointmentList, boolean operation){

        System.out.print("\033[H\033[2J");
        System.out.flush();


        DateTimeFormatter stringFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");

        System.out.println("============================================================APPOINTMENT LIST============================================================");
        System.out.println("|AppointmentID|Patient Name             |Date & Time              |Doctor Name              |Emergency|Consulted|Given Medicine|Done   |");
        System.out.println("========================================================================================================================================");
            
        for(int i = 0; i < appointmentList.size(); i++){
    
            if(appointmentList.get(i).getIsDone() == operation){                    
                System.out.printf("|%-13s|", appointmentList.get(i).getAppointmentID());
                System.out.printf("%-25s|", appointmentList.get(i).getPatient().getName());

                String dateTime = appointmentList.get(i).getDateTime().format(stringFormat);

                System.out.printf("%-25s|", dateTime);
                System.out.printf("%-25s|", appointmentList.get(i).getDoctor().getName());
                System.out.printf("%-9s|", appointmentList.get(i).getEmergency());
                System.out.printf("%-9s|", appointmentList.get(i).getIsConsulted());
                System.out.printf("%-14s|", appointmentList.get(i).getGivenMedicine());
                System.out.printf("%-7s|", appointmentList.get(i).getIsDone());
                System.out.println();
            } 
            
        }
    }

    public static void showDoctors(ArrayList<Doctor> doctorList){

        System.out.println("===============================DOCTOR LIST===============================");
        System.out.println("|DoctorID|Name                  |Specialization          |Patients Slots|");
        System.out.println("=========================================================================");

        for(int i = 0; i < doctorList.size(); i++){
                
            System.out.printf("|%-9s|", doctorList.get(i).getDoctorID());
            System.out.printf("%-21s|", doctorList.get(i).getName());
            System.out.printf("%-24s|", doctorList.get(i).getSpecialization());
            System.out.printf("%d/%-12d|", doctorList.get(i).getPatientList().size(), 5);
            System.out.println();
        }
    }

    public static void createAppointment(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList, ArrayList<Appointment> appointmentList){

        Scanner scanner = new Scanner(System.in);
        
        System.out.print("\033[H\033[2J");
        System.out.flush();

        showPatients(patientList);
        String inputPatientID = "";
        
        System.out.print("Enter patientID: ");
        inputPatientID = scanner.nextLine();

        int indexPatient = searchPatient(patientList, inputPatientID);

        if(indexPatient != -1){

            String dateTimeInput = "";
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
            Boolean valid = false;
            
            do {

                valid = true;

                System.out.print("Enter appointment date & time [dd MMMM yyyy HH:mm | 12 December 2020 14:25 ]: ");
                dateTimeInput = scanner.nextLine();

                try {
                    LocalDateTime dateTime = LocalDateTime.parse(dateTimeInput, format);

                    LocalDateTime currentDateTime = LocalDateTime.now();

                    if(dateTime.compareTo(currentDateTime) < 0){
                        System.out.println("Invalid Date!");
                        valid = false;
                    } 

                } catch (Exception e) {
                    System.err.println("Invalid Format!");
                    valid = false;
                }

            } while(!valid);

            String input = "";
            Boolean emergency = false;

            do{
                valid = true;

                System.out.print("Is it an emergency? [Y/N]: ");

                input = scanner.nextLine();

                if(!input.toLowerCase().equals("y") && !input.toLowerCase().equals("n")){
                    System.out.println("Invalid input! Try again!");
                    valid = false;
                }

                if(input.toLowerCase().equals("y")){
                    emergency = true;
                } 

            } while(!valid);


            showDoctors(doctorList);
            String inputDoctorID = "";

            do{

                valid = true;

                System.out.print("Enter DoctorID: ");
                inputDoctorID = scanner.nextLine();

                int indexDoctor = searchDoctor(doctorList, inputDoctorID);

                if(indexDoctor == -1 || doctorList.get(indexDoctor).patientList.size() == 5){
                    valid = false;
                } else {
                    doctorList.get(indexDoctor).patientList.add(Patient.getPatient(patientList, inputPatientID));
                }

                //UPDATE DOCTOR DATABASE
                updateDoctorDatabase(doctorList);

                //UPDATE APPOINTMENT ARRAYLIST

                String appointmentID = "";

                String currentID = appointmentList.get(appointmentList.size() - 1).getAppointmentID().substring(2, 5);
                int currentIDNumber = Integer.parseInt(currentID);

                System.out.println(currentID);

                if(appointmentList.size() < 10){
                    appointmentID = String.format("AP00%d", currentIDNumber + 1);
                } else if (patientList.size() < 100){
                    appointmentID = String.format("AP0%d", currentIDNumber + 1);
                } else {
                    appointmentID = String.format("AP%d", currentIDNumber + 1);
                }

                appointmentList.add(new Appointment(appointmentID, LocalDateTime.parse(dateTimeInput, format), emergency, false, false, false, patientList.get(indexPatient), doctorList.get(indexDoctor)));

                //UPDATE APPOINTMENT DATABASE
                updateAppointmentDatabase(appointmentList);
                

            }while(!valid);

        } else {
            System.out.print("PATIENT NOT FOUND!");
        }

        System.out.println("Press any key to continue...");
        scanner.nextLine();
        manageAppointment(patientList, doctorList, appointmentList);

        scanner.close();
    }

    public static void updateDoctorDatabase(ArrayList<Doctor> doctorList){

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./Database/DoctorRecords.csv", false));
            bw.write("");

            for(int i = 0; i < doctorList.size(); i++){

                String name = doctorList.get(i).getName();
                String address = doctorList.get(i).getAddress();
                String gender = doctorList.get(i).getGender();
                String phoneNumber = doctorList.get(i).getPhoneNumber();
                String email = doctorList.get(i).getEmail();

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

                String writeString = String.format("%s,%s,%s,%s,%s,%s,%s,%s", name, address, gender, phoneNumber, email, doctorID, specialization, doctorPatientListString);
                
                try {
                    bw = new BufferedWriter(new FileWriter("./Database/DoctorRecords.csv",true));
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

    public static void updateAppointmentDatabase(ArrayList<Appointment> appointmentList){

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./Database/AppointmentRecords.csv", false));
            bw.write("");

            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");

            for(int i = 0; i < appointmentList.size(); i++){

                String appointmentID = appointmentList.get(i).getAppointmentID();
                String formattedDate = appointmentList.get(i).getDateTime().format(format);
                String emergency = String.valueOf(appointmentList.get(i).getEmergency());
                String isConsulted = String.valueOf(appointmentList.get(i).getIsConsulted());
                String givenMedicine = String.valueOf(appointmentList.get(i).getGivenMedicine());
                String isDone = String.valueOf(appointmentList.get(i).getIsDone());
                String patientID = appointmentList.get(i).getPatient().getPatientID();
                String doctorID = appointmentList.get(i).getDoctor().getDoctorID();
                

                String writeString = String.format("%s,%s,%s,%s,%s,%s,%s,%s", appointmentID, formattedDate, emergency, isConsulted, givenMedicine, isDone, patientID, doctorID);
                
                try {
                    bw = new BufferedWriter(new FileWriter("./Database/AppointmentRecords.csv",true));
                    bw.write(writeString);
                    bw.newLine();
                    bw.close();
    
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                    System.out.println("AppointmentRecords.csv not found, closing application...");
                    System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("IOException occurred, closing application...");
                    System.exit(0);
                }            
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("AppointmentRecords.csv not found, closing application...");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occurred, closing application...");
            System.exit(0);
        }
    }

    

}
