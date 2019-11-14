public class Link {
    private int id;
    private int studentID;
    private int courseID;
    private String dateEnrolled;

    public Link(){};

    public Link(int id, int studentID, int courseID ,String dateEnrolled) {
        this.id = id;
        this.studentID = studentID;
        this.courseID = courseID;
        this.dateEnrolled = dateEnrolled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getDateEnrolled() {
        return dateEnrolled;
    }

    public void setDateEnrolled(String dateEnrolled) {
        this.dateEnrolled = dateEnrolled;
    }


}
