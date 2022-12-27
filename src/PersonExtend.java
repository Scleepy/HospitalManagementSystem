import java.util.ArrayList;

class Doctor extends Person{
    String doctorID;
    String specialization;
    ArrayList<Patient> patientList = new ArrayList<>();
}

class Patient extends Person{
    String patientID;
    String bloodType;
}

class Nurse extends Person{
    String nurseID;
    ArrayList<Patient> patientList = new ArrayList<>();
}

class Admin extends Person{
    String adminID;
}

class Receptionist extends Person{
    String recID;
}

class Pharmacist extends Person{
    String pharID;
}