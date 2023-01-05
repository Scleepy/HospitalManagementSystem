public class Admin extends Person{
    String adminID;

    public Admin(String name, String address, String gender, String phoneNumber, String email, String password, String adminID){
        super(name, address, gender, phoneNumber, email, password);
        this.adminID = adminID;
    }

    public String getAdminID() {
        return this.adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

}
