package Start;

public class User {
    private String id;
    private String pwd;

    public User(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    public boolean check(String pwd){
        if(pwd.equals(this.pwd)){
            return true;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
