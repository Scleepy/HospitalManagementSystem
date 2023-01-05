import java.util.ArrayList;
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
	
	public String getMedicineID() {
		return medicineID;
	}
	
	public String getMedicineName() {
		return medicineName;
	}
	
	public int getMedicineQuantity() {
		return medicineQuantity;
	}
	
	public String getMedicineDescription() {
		return medicineDescription;
	}
	
	public String getMedicineInstruction() {
		return medicineInstruction;
	}
	
	public int getMedicinePrice() {
		return medicinePrice;
	}

	public static Medicine getMedicine(ArrayList<Medicine> medicineList, String medicineID){

        int index = -1;

        for(int i = 0; i < medicineList.size(); i++){
            if(medicineList.get(i).getMedicineID().equals(medicineID)){
                index = i;
                break;
            }
        }

        return medicineList.get(index);
    }
}
