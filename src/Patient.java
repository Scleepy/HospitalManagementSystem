import java.util.ArrayList;

class Patient extends Person{
    String patientID;
    String bloodType;

    public Patient(){};

    public Patient(String name, String address, String gender, String phoneNumber, String email, String patientID, String bloodType){
        super(name, address, gender, phoneNumber, email, patientID);
        this.patientID = patientID;
        this.bloodType = bloodType;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public static Patient getPatient(ArrayList<Patient> patientList, String patientID){

        int index = -1;

        for(int i = 0; i < patientList.size(); i++){
            if(patientList.get(i).getPatientID().equals(patientID)){
                index = i;
                break;
            }
        }

        return patientList.get(index);
    }
    
}