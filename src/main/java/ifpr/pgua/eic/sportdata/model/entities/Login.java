package ifpr.pgua.eic.sportdata.model.entities;

public class Login {

    private String user;
    private String password;
    private boolean isLogado;


    public Login(String user, String password, boolean isLogado) {
        this.user = user;
        this.password = password;
        this.isLogado = isLogado;
    }


    public String getUser() {
        return user;
    }


    public void setUser(String user) {
        this.user = user;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isLogado() {
        return isLogado;
    }


    public void setLogado(boolean isLogado) {
        this.isLogado = isLogado;
    }

    
    

}