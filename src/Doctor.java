import java.util.ArrayList;

class Doctor extends Person{
    String doctorID;
    String specialization;
    ArrayList<Patient> patientList = new ArrayList<>();

    public Doctor(String name, String address, String gender, String phoneNumber, String email, String doctorID, String specialization, ArrayList<Patient> patient){
        super(name, address, gender, phoneNumber, email);
        this.doctorID = doctorID;
        this.specialization = specialization;
        this.patientList = patient;
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

}