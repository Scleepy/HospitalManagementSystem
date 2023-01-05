import java.util.ArrayList;

class Doctor extends Person{
    String doctorID;
    String specialization;
    ArrayList<Patient> patientList = new ArrayList<>();
    Integer hargaJasa;

    public Doctor(){};

    public Doctor(String name, String address, String gender, String phoneNumber, String email, String password, String doctorID, String specialization, ArrayList<Patient> patient, Integer hargaJasa){
        super(name, address, gender, phoneNumber, email, password);
        this.doctorID = doctorID;
        this.specialization = specialization;
        this.patientList = patient;
        this.hargaJasa = hargaJasa;
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

    public Integer getHargaJasa() {
        return hargaJasa;
    }

    public void setHargaJasa(Integer hargaJasa) {
        this.hargaJasa = hargaJasa;
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


}