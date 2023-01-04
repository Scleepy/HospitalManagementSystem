import java.time.LocalDateTime;

public class Appointment {
	private String appointmentID;
	private LocalDateTime dateTime;
    private Boolean emergency;
	private Boolean isConsulted;
	private Boolean givenMedicine;
	private Boolean isDone;
	private Patient patient;
	private Doctor doctor;

	public Appointment(String appointmentID, LocalDateTime dateTime, Boolean emergency, Boolean isConsulted, Boolean givenMedicine, Boolean isDone, Patient patient, Doctor doctor) {
		this.appointmentID = appointmentID;
		this.dateTime = dateTime;
		this.emergency = emergency;
		this.isConsulted = isConsulted;
		this.givenMedicine = givenMedicine;
		this.isDone = isDone;
		this.patient = patient;
		this.doctor = doctor;
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

    
}
