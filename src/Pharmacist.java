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

}
