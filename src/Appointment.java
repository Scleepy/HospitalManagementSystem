public class Appointment {
	private String date;
    private String time;
    private Boolean emergency;

	public Appointment(String date, String time, Boolean emergency) {
		this.date = date;
		this.time = time;
		this.emergency = emergency;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Boolean getEmergency() {
		return emergency;
	}

	public void setEmergency(Boolean emergency) {
		this.emergency = emergency;
	}
    
}
