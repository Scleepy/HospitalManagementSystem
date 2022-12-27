import java.util.ArrayList;

public class Disease {
    String diseaseID;
    String diseaseName;
    ArrayList<Medicine> medicineList;
    String category;
    String dangerLevel;

    public Disease(String diseaseID, String diseaseName, ArrayList<Medicine> medicineList, String category, String dangerLevel){
        this.diseaseID = diseaseID;
        this.diseaseName = diseaseName;
        this.medicineList = new ArrayList<Medicine>();
        this.category = category;
        this.medicineList = medicineList;
    }

    public String getDiseaseID() {
        return diseaseID;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public ArrayList<Medicine> getMedicineList() {
        return medicineList;
    }

    public String getCategory() {
        return category;
    }

    public String getDangerLevel() {
        return dangerLevel;
    }

    public void setDiseaseID(String diseaseID) {
        this.diseaseID = diseaseID;
    }
    
    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }
    
    public void setMedicineList(ArrayList<Medicine> medicineList) {
        this.medicineList = medicineList;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setDangerLevel(String dangerLevel) {
        this.dangerLevel = dangerLevel;
    }


}



