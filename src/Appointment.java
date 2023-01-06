import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Appointment {
	private String appointmentID;
	private LocalDateTime dateTime;
    private Boolean emergency;
	private Boolean isConsulted;
	private Boolean givenMedicine;
	private Boolean isDone;
	private Patient patient;
	private Doctor doctor;
	private Prescription prescription;

	public Appointment(String appointmentID, LocalDateTime dateTime, Boolean emergency, Boolean isConsulted, Boolean givenMedicine, Boolean isDone, Patient patient, Doctor doctor, Prescription prescription) {
		this.appointmentID = appointmentID;
		this.dateTime = dateTime;
		this.emergency = emergency;
		this.isConsulted = isConsulted;
		this.givenMedicine = givenMedicine;
		this.isDone = isDone;
		this.patient = patient;
		this.doctor = doctor;
		this.prescription = prescription;
	}

	public String getAppointmentID() {
		return this.appointmentID;
	}

	public void setAppointmentID(String appointmentID) {
		this.appointmentID = appointmentID;
	}

	public LocalDateTime getDateTime(){
		return this.dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Boolean getEmergency() {
		return this.emergency;
	}

	public void setEmergency(Boolean emergency) {
		this.emergency = emergency;
	}

	public Boolean getIsConsulted() {
		return this.isConsulted;
	}

	public void setIsConsulted(Boolean isConsulted) {
		this.isConsulted = isConsulted;
	}

	public Boolean getGivenMedicine() {
		return this.givenMedicine;
	}

	public void setGivenMedicine(Boolean givenMedicine) {
		this.givenMedicine = givenMedicine;
	}

	public Boolean getIsDone(){
		return this.isDone;
	}

	public void setIsDone(Boolean isDone){
		this.isDone = isDone;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Prescription getPrescription() {
        return this.prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public static Appointment getAppointment(ArrayList<Appointment> appointmentList, String appointmentID){

        int index = -1;

        for(int i = 0; i < appointmentList.size(); i++){
            if(appointmentList.get(i).getAppointmentID().equals(appointmentID)){
                index = i;
                break;
            }
        }

        return appointmentList.get(index);
    }

	public static int searchAppointment(ArrayList<Appointment> appointmentList, String inputAppointmentID){

        int index = -1;

        for(int i = 0; i < appointmentList.size(); i++){
            if(appointmentList.get(i).getAppointmentID().equals(inputAppointmentID) && appointmentList.get(i).getIsConsulted() == true && appointmentList.get(i).getGivenMedicine() == true && appointmentList.get(i).getIsDone() == false){
                index = i;
                break;
            }
        }

        return index;
    }

	public static void loadAppointments(ArrayList<Patient> patientList, ArrayList<Doctor> doctorList, ArrayList<Appointment> appointmentList, ArrayList<Prescription> prescriptionList){
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
                
                Patient patient = Patient.getPatient(patientList, detail[6]);
                Doctor doctor = Doctor.getDoctor(doctorList, detail[7]);
                Prescription prescription = Prescription.getPrescription(prescriptionList, detail[8]);

                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(detail[1], format);
                
                appointmentList.add(new Appointment(detail[0], dateTime, Boolean.parseBoolean(detail[2]), Boolean.parseBoolean(detail[3]), Boolean.parseBoolean(detail[4]), Boolean.parseBoolean(detail[5]), patient, doctor, prescription));
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

	public static void updateAppointmentDatabase(ArrayList<Appointment> appointmentList){

        try {

            BufferedWriter bw;
        
            try {
                bw = new BufferedWriter(new FileWriter("./Database/AppointmentRecords.csv",false));
            } catch (Exception e) {
                bw = new BufferedWriter(new FileWriter("src/Database/AppointmentRecords.csv",false));
            }

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
                String prescriptionID = appointmentList.get(i).getPrescription().getPrescriptionID();
                

                String writeString = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", appointmentID, formattedDate, emergency, isConsulted, givenMedicine, isDone, patientID, doctorID, prescriptionID);
                
                try {
                    try {
                        bw = new BufferedWriter(new FileWriter("./Database/AppointmentRecords.csv",true));
                    } catch (Exception e) {
                        bw = new BufferedWriter(new FileWriter("src/Database/AppointmentRecords.csv",true));
                    }

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
