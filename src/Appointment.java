public class Appointment {
	private String appointmentID;
	private String date;
    private String time;
    private Boolean emergency;
	private Boolean isConsulted;
	private Boolean givenMedicine;
	private Boolean isDone;
	private Patient patient;
	private Doctor doctor;

	public Appointment(String appointmentID, String date, String time, Boolean emergency, Boolean isConsulted, Boolean givenMedicine, Boolean isDone, Patient patient, Doctor doctor) {
		this.appointmentID = appointmentID;
		this.date = date;
		this.time = time;
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

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
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
