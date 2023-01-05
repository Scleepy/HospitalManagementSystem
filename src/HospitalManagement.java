import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
public class HospitalManagement {

    public HospitalManagement(){};

    public static void main(String[] args) throws Exception {
        
        //LOAD PATIENT DATA
        ArrayList<Patient> patientList = new ArrayList<>();
        loadPatient(patientList);

        //LOAD DOCTOR DATA
        ArrayList<Doctor> doctorList = new ArrayList<>();
        loadDoctors(patientList, doctorList);

        //LOAD APPOINTMENT DATA
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        loadAppointments(patientList, doctorList, appointmentList);
        
        //LOAD OTHER DATA HERE

        //RECEPTIONIST MENU -> should be controlled in the login menu

        Receptionist.receptionMenu(patientList, doctorList, appointmentList);
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

    public static void loadDoctors(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList){
        try{

            BufferedReader br = new BufferedReader(new FileReader("./Database/DoctorRecords.csv"));
            
            String line;

            while((line = br.readLine()) != null){
                String[] detail = line.split(",");

                ArrayList<Patient> doctorPatientList = new ArrayList<Patient>();

                String[] patientArr = detail[8].split("#");

                for(String patientID : patientArr){   
                    doctorPatientList.add(Patient.getPatient(patientList, patientID));
                }

                doctorList.add(new Doctor(detail[0], detail[1], detail[2], detail[3], detail[4], detail[5], detail[6], detail[7], doctorPatientList));
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

    public static void loadAppointments(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList, ArrayList<Appointment> appointmentList){
        try{

            BufferedReader br = new BufferedReader(new FileReader("./Database/AppointmentRecords.csv"));
            
            String line;

            while((line = br.readLine()) != null){
                String[] detail = line.split(",");

                Doctor doctor = Doctor.getDoctor(doctorList, detail[7]);
    
                Patient patient = Patient.getPatient(patientList, detail[6]);

                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(detail[1], format);
                
                appointmentList.add(new Appointment(detail[0], dateTime, Boolean.parseBoolean(detail[2]), Boolean.parseBoolean(detail[3]), Boolean.parseBoolean(detail[4]), Boolean.parseBoolean(detail[5]), patient, doctor));
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

    //LOAD BILLING
    //LOAD PHARMACIST
    //LOAD PRECRIPTION



}
