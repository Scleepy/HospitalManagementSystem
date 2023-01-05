import java.util.ArrayList;

public class Pharmacist extends Person{
    String pharID;

    public Pharmacist(String name, String address, String gender, String phoneNumber, String email, String password, String pharID){
        super(name, address, gender, phoneNumber, email, password);
        this.pharID = pharID;
    }

    public String getPharID() {
        return this.pharID;
    }

    public void setPharID(String pharID) {
        this.pharID = pharID;
    }

    public static Pharmacist getPharmacist(ArrayList<Pharmacist> pharmacistList, String pharmacistID){

        int index = -1;

        for(int i = 0; i < pharmacistList.size(); i++){
            if(pharmacistList.get(i).getPharID().equals(pharmacistID)){
                index = i;
                break;
            }
        }

        return pharmacistList.get(index);
    }

}
