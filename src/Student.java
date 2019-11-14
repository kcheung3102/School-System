public class Student extends Person {
    public Student() {
    }

    public Student(int id, String name, String email, String password) {
        super(id, name, email, password);
    }

    @Override
    public String displayText(){
        return "You have logged into as " + getEmail();
    }
}
