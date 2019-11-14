public class Admin extends Person {

    public Admin() {
    }

    public Admin(int Id, String name, String email, String password) {
        super(Id, name, email, password);
    }
    @Override
    public String displayText(){
        return "Hello Admin " + getEmail();
    }
}
