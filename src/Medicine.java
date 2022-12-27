public class Medicine {
    public String medicineID, medicineName, medicineDescription, medicineInstruction;
	public int medicineQuantity, medicinePrice;
	
	Medicine(String medicineID, String medicineName, int medicineQuantity, String medicineDescription, String medicineInstruction, int medicinePrice) {
		this.medicineID = medicineID;
		this.medicineName = medicineName;
		this.medicineQuantity = medicineQuantity;
		this.medicineDescription = medicineDescription;
		this.medicineInstruction = medicineInstruction;
		this.medicinePrice = medicinePrice;
	}
	
	public String getmedicineID() {
		return medicineID;
	}
	
	public String getmedicineName() {
		return medicineName;
	}
	
	public int getmedicineQuantity() {
		return medicineQuantity;
	}
	
	public String getmedicineDescription() {
		return medicineDescription;
	}
	
	public String getmedicineInstruction() {
		return medicineInstruction;
	}
	
	public int getmedicinePrice() {
		return medicinePrice;
	}
}
