import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

                String writeString = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", name, address, gender, phoneNumber, email, password, doctorID, specialization, doctorPatientListString);
                
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


}