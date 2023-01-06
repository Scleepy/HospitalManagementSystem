import java.util.ArrayList;

public class HospitalManagement {

    public HospitalManagement(){};

    public static void main(String[] args) throws Exception {
        
        //LOAD PATIENT DATA
        ArrayList<Patient> patientList = new ArrayList<>();
        Patient.loadPatient(patientList);

        //LOAD DOCTOR DATA
        ArrayList<Doctor> doctorList = new ArrayList<>();
        Doctor.loadDoctors(patientList, doctorList);

        //LOAD PHARMACIST DATA
        ArrayList<Pharmacist> pharmacistList = new ArrayList<>();
        Pharmacist.loadPharmacist(pharmacistList);

        //LOAD MEDICINE DATA
        ArrayList<Medicine> medicineList = new ArrayList<>();
        Medicine.loadMedicine(medicineList);

        //LOAD PRESCRIPTION DATA
        ArrayList<Prescription> prescriptionList = new ArrayList<>();
        Prescription.loadPrescription(prescriptionList, doctorList, medicineList);

        //LOAD APPOINTMENT DATA
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        Appointment.loadAppointments(patientList, doctorList, appointmentList, prescriptionList);

        //LOAD BILLING DATA
        ArrayList<Billing> billingList = new ArrayList<>();
        Billing.loadBilling(billingList, appointmentList);
        
        //LOAD OTHER DATA HERE

        //RECEPTIONIST MENU -> should be controlled in the login menu
        Receptionist.receptionMenu(patientList, doctorList, appointmentList, billingList, prescriptionList);
    }

}
