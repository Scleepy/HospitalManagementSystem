import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Prescription {
	private String prescriptionID;
	private Doctor doctor;
	ArrayList <Medicine> medicineList = new ArrayList <>();
	
	public Prescription(String prescriptionID, Doctor doctor, ArrayList<Medicine> medicineList) {
		this.prescriptionID = prescriptionID;
		this.doctor = doctor;
		this.medicineList = medicineList;
	}

	public String getPrescriptionID() {
		return prescriptionID;
	}

	public void setPrescriptionID(String prescriptionID) {
		this.prescriptionID = prescriptionID;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public ArrayList<Medicine> getMedicineList() {
		return medicineList;
	}

	public void setMedicineList(ArrayList<Medicine> medicineList) {
		this.medicineList = medicineList;
	}

	public static Prescription getPrescription(ArrayList<Prescription> prescriptionList, String prescriptionID){

        int index = -1;

        for(int i = 0; i < prescriptionList.size(); i++){
            if(prescriptionList.get(i).getPrescriptionID().equals(prescriptionID)){
                index = i;
                break;
            }
        }

        return prescriptionList.get(index);
    }

	public static void loadPrescription(ArrayList<Prescription> prescriptionList, ArrayList<Doctor> doctorList, ArrayList<Medicine> medicineList){
        try{

            BufferedReader br;

            try {
                br = new BufferedReader(new FileReader("./Database/PrescriptionRecords.csv"));
            } catch (Exception e) {
                br = new BufferedReader(new FileReader("src/Database/PrescriptionRecords.csv"));
            }
            
            String line;

            while((line = br.readLine()) != null){
                String[] detail = line.split(",");

                ArrayList<Medicine> prescriptionMedicineList = new ArrayList<Medicine>();
                String[] medicineArr = detail[2].split("#");

                for(String medicineID : medicineArr){   
                    prescriptionMedicineList.add(Medicine.getMedicine(medicineList, medicineID));
                }

                prescriptionList.add(new Prescription(detail[0], Doctor.getDoctor(doctorList, detail[1]), prescriptionMedicineList));
            }

            br.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("PrescriptionRecords.csv not found, closing application...");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occurred, closing application...");
            System.exit(0);
        }
    }

	
}
