import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

    public static void loadAdmin(ArrayList<Admin> adminList){
        try{

            BufferedReader br;

            try {
                br = new BufferedReader(new FileReader("./Database/ReceptionistRecords.csv"));
            } catch (Exception e) {
                br = new BufferedReader(new FileReader("src/Database/ReceptionistRecords.csv"));
            }
            
            String line;

            while((line = br.readLine()) != null){
                String[] detail = line.split(",");

                adminList.add(new Admin(detail[0], detail[1], detail[2], detail[3], detail[4], detail[5], detail[6]));
            }

            br.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("ReceptionistRecords.csv not found, closing application...");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occurred, closing application...");
            System.exit(0);
        }
    }

}
