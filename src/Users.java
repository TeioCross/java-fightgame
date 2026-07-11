import java.util.ArrayList;
import java.util.Random;

public class Users {
    private String id;
    private String name;
    private String pw;
    private boolean state=true;
    private String phone;
    public Users() {
    }

    public Users(String name, String pw,String phone) {
        String temp="heima";
        for (int i = 0; i < 5; i++) {
            temp += Service.getrdint(0,9);
        }
        this.id=temp;
        this.name = name;
        this.pw = pw;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
