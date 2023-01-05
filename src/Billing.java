import java.time.LocalDateTime;

public class Billing {
    private String billingID;
    private LocalDateTime billingDate;
    private Appointment appointment;
    private Prescription prescription;
    private int totalBill;

    public Billing(){} 

    public Billing(String billingID, LocalDateTime billingDate, Appointment appointment, int totalBill) {
        this.billingID = billingID;
        this.billingDate = billingDate;
        this.appointment = appointment;
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

    public int getTotalBill() {
        return this.totalBill;
    }

    public void setTotalBill(int totalBill) {
        this.totalBill = totalBill;
    }
}
