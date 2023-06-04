public class Utility {

    public Utility() {};

    public Boolean hasNumber(String str){
        
        Boolean numberExist = false;

        for(int i = 0; i < str.length(); i++){
            if(Character.isDigit(str.charAt(i))){
                numberExist = true;
                break;
            }
        }

        return numberExist;
    }

    public Boolean validSpace(String str){
        
        int count = 0;

        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ' ') count++;
        }

        if(count < 1){
            return true;
        } else {
            return false;
        }
    }

    public Boolean hasLetter(String str){
        
        Boolean letterExist = false;

        for(int i = 0; i < str.length(); i++){
            if(Character.isLetter(str.charAt(i))){
                letterExist = true;
                break;
            }
        }

        return letterExist;
    }
}