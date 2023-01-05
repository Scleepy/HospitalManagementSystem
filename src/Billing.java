import java.time.LocalDateTime;
public class Billing {
    private String billingID;
    private LocalDateTime billingDate;
    private Appointment appointment;
    private Pharmacist pharmacist;
    private Prescription prescription;
    private int totalBill;

    public Billing(){}

    public Billing(String billingID, LocalDateTime billingDate, Appointment appointment, Pharmacist pharmacist, Prescription prescription, int totalBill) {
        this.billingID = billingID;
        this.billingDate = billingDate;
        this.appointment = appointment;
        this.pharmacist = pharmacist;
        this.prescription = prescription;
        this.totalBill = totalBill;
    }

    public String getBillingID() {
        return this.billingID;
    }

    public void setBillingID(String billingID) {
        this.billingID = billingID;
    }

    public LocalDateTime getBillingDate() {
        return this.billingDate;
    }

    public void setBillingDate(LocalDateTime billingDate) {
        this.billingDate = billingDate;
    }

    public Appointment getAppointment(){
        return this.appointment;
    }

    public void setAppointment(Appointment appointment){
        this.appointment = appointment;
    }

    public Pharmacist getPharmacist() {
        return this.pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    public Prescription getPrescription() {
        return this.prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public int getTotalBill() {
        return this.totalBill;
    }

    public void setTotalBill(int totalBill) {
        this.totalBill = totalBill;
    }
}
