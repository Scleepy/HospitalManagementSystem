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

        //LOAD PHARMACIST DATA
        ArrayList<Pharmacist> pharmacistList = new ArrayList<>();
        loadPharmacist(pharmacistList);

        //LOAD MEDICINE DATA
        ArrayList<Medicine> medicineList = new ArrayList<>();
        loadMedicine(medicineList);

        //LOAD PRESCRIPTION DATA
        ArrayList<Prescription> prescriptionList = new ArrayList<>();
        loadPrescription(prescriptionList, doctorList, medicineList);

        //LOAD BILLING DATA
        ArrayList<Billing> billingList = new ArrayList<>();
        loadBilling(billingList, appointmentList, pharmacistList, prescriptionList);
        
        //LOAD OTHER DATA HERE

        //RECEPTIONIST MENU -> should be controlled in the login menu
        Receptionist.receptionMenu(patientList, doctorList, appointmentList);
    }

    public static void loadPatient(ArrayList<Patient> patientList){
        try{

            BufferedReader br;

            try {
                br = new BufferedReader(new FileReader("./Database/PatientRecords.csv"));
            } catch (Exception e) {
                br = new BufferedReader(new FileReader("src/Database/PatientRecords.csv"));
            }
            
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

    public static void loadAppointments(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList, ArrayList<Appointment> appointmentList){
        try{

            BufferedReader br;

            try {
                br = new BufferedReader(new FileReader("./Database/AppointmentRecords.csv"));
            } catch (Exception e) {
                br = new BufferedReader(new FileReader("src/Database/AppointmentRecords.csv"));
            }
            
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
            System.out.println("AppointmentRecords.csv not found, closing application...");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occurred, closing application...");
            System.exit(0);
        }
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

    public static void loadMedicine(ArrayList<Medicine> medicineList){
        try{

            BufferedReader br;

            try {
                br = new BufferedReader(new FileReader("./Database/MedicineRecords.csv"));
            } catch (Exception e) {
                br = new BufferedReader(new FileReader("src/Database/MedicineRecords.csv"));
            }
            
            String line;

            while((line = br.readLine()) != null){
                String[] detail = line.split(",");

                medicineList.add(new Medicine(detail[0], detail[1], Integer.parseInt(detail[2]), detail[3], detail[4], Integer.parseInt(detail[5])));

            }

            br.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("MedicineRecords.csv not found, closing application...");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occurred, closing application...");
            System.exit(0);
        }
    }

    public static void loadPrescription(ArrayList<Prescription> prescriptionList, ArrayList<Doctor> doctorList, ArrayList<Medicine> medicineList){
        try{

            BufferedReader br;

            try {
                br = new BufferedReader(new FileReader("./Database/PrescriptionRecords.csv"));
            } catch (Exception e) {
                br = new BufferedReader(new FileReader("src/Database/PrescriptionRecords.csv"));
            }
            
            String line;

            while((line = br.readLine()) != null){
                String[] detail = line.split(",");

                ArrayList<Medicine> prescriptionMedicineList = new ArrayList<Medicine>();
                String[] medicineArr = detail[2].split("#");

                for(String medicineID : medicineArr){   
                    prescriptionMedicineList.add(Medicine.getMedicine(medicineList, medicineID));
                }

                prescriptionList.add(new Prescription(detail[0], Doctor.getDoctor(doctorList, detail[1]), prescriptionMedicineList));
            }

            br.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("PrescriptionRecords.csv not found, closing application...");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occurred, closing application...");
            System.exit(0);
        }
    }

    public static void loadBilling(ArrayList<Billing> billingList, ArrayList<Appointment> appointmentList, ArrayList<Pharmacist> pharmacistList, ArrayList<Prescription> prescriptionList){
        try{

            BufferedReader br;

            try {
                br = new BufferedReader(new FileReader("./Database/BillingRecords.csv"));
            } catch (Exception e) {
                br = new BufferedReader(new FileReader("src/Database/BillingRecords.csv"));
            }
            
            String line;

            while((line = br.readLine()) != null){
                String[] detail = line.split(",");

                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(detail[1], format);
                
                billingList.add(new Billing(detail[0], dateTime, Appointment.getAppointment(appointmentList, detail[2]), Pharmacist.getPharmacist(pharmacistList, detail[3])  , Prescription.getPrescription(prescriptionList, detail[4]), Integer.parseInt(detail[5])));
            }

            br.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("BillingRecords.csv not found, closing application...");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occurred, closing application...");
            System.exit(0);
        }
    }
}
