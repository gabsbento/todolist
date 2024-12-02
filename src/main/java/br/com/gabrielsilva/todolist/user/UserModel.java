package br.com.gabrielsilva.todolist.user;

public class UserModel {
    private String username;
    private String name;
    private String password;

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getUsername(){return this.username;}
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword(){return this.password;}
    public void setPassword(String password){
        this.password = password;
    }
}
