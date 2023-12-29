package trabalhopoo.controllers;

public class User {
    
    private String name;
    private String login;
    private String pswrd;
    private int type = 0;

    public User() {
    }
    
    public User(String name, String login, String pswrd) {
        this.name = name;
        this.login = login;
        this.pswrd = pswrd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPswrd() {
        return pswrd;
    }

    public void setPswrd(String pswrd) {
        this.pswrd = pswrd;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
}
