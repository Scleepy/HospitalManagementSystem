class Patient extends Person{
    String patientID;
    String bloodType;

    public Patient(String name, String address, String gender, String phoneNumber, String email, String patientID, String bloodType){
        super(name, address, gender, phoneNumber, email);
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

}