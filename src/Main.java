import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Student> studentDB = new ArrayList<>();
    private static ArrayList<Faculty> facultyDB = new ArrayList<>();
    private static ArrayList<Course> courseDB = new ArrayList<>();
    private static ArrayList<Admin> adminDB = new ArrayList<>();
    private static ArrayList<Link> linkDB = new ArrayList<>();

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean logIn = true;
        boolean toContinue = true;
        Admin newAdmin = new Admin(1, "Adam", "123@gmail.com","password");
        adminDB.add(newAdmin);
        String str;



        do {

            System.out.println("Student, Faculty or Admin(theres only an Admin) or quit?");
            str = input.nextLine().toLowerCase().trim();
            if(str.equalsIgnoreCase("admin")) {
                ArrayList<Admin> admin = getAdminLogin();
                if(admin != null) {
                    for(Admin a: admin) {
                        System.out.println(a.displayText());
                    }
                    do {
                        System.out.println("Options: " +
                                "\nA = Add Student" +
                                "\nB = Add Faculty" +
                                "\nC = Edit Student" +
                                "\nD = Edit Faculty" +
                                "\nE = Add Class" +
                                "\nF = Edit Class" +
                                "\nG = Enroll Student" +
                                "\nH = View All Information" +
                                "\nEnter q to quit");
                        str = input.nextLine().trim().toLowerCase();
                        if (str.equalsIgnoreCase("a")) {
                            createNewStudent();
                            showStudentDB();
                        } else if (str.equalsIgnoreCase("b")) {
                            createNewFaculty();
                            showFacultyDB();
                        } else if (str.equalsIgnoreCase("c")) {
                            editStudent();
                        } else if (str.equalsIgnoreCase("d")) {
                            editFaculty();
                        } else if (str.equalsIgnoreCase("e")) {
                            createNewCourse();
                            showCourseDB();
                        } else if (str.equalsIgnoreCase("f")) {
                            editCourse();
                        } else if (str.equalsIgnoreCase("g")) {
                            createNewLink();
                            showLinkDBByCourse();
                        } else if (str.equalsIgnoreCase("h")) {
                            System.out.println("All Courses " + "\n");
                            showCourseDB();
                            System.out.println("All Students " + "\n");
                            showStudentDB();
                            System.out.println("All Faculty " + "\n");
                            showFacultyDB();
                            //Show linked DB with Each Student in a Course and the Faculty member that is teaching it
                            showLinkDB();
                        } else if (str.equalsIgnoreCase("q")) {
                            System.out.println("You have logged out");
                            toContinue = false;
                        }
                    } while(toContinue);
                }
            } else if(str.equalsIgnoreCase("quit") || str.equalsIgnoreCase("q")) {
                System.out.println("Goodbye!");
                logIn = false;
            }

        } while (logIn);
            input.close();
    }

        //Create Student
        private static void createNewStudent () {
            String name, email, password;

            System.out.println("Enter Students Name: ");
            name = input.nextLine();
            System.out.println("Enter Students Email: ");
            email = input.nextLine();
            System.out.println("Enter Students Password: ");
            password = input.nextLine();
            int id = studentDB.size() + 1;
            studentDB.add(new Student(id, name, email, password));
        }
        //Create  Faculty

        private static void createNewFaculty () {
            String name, email, password;

            System.out.println("Enter Faculty Members Name:");
            name = input.nextLine();
            System.out.println("enter Faculty Members Email: ");
            email = input.nextLine();
            System.out.println("Enter Faculty Members Password: ");
            password = input.nextLine();
            int id = facultyDB.size() + 1;
            facultyDB.add(new Faculty(id, name, email, password));
        }

        //Create Course
        private static void createNewCourse () {
            String courseName, description;
            int instructorId;
            System.out.println("Enter Course Name: ");
            courseName = input.nextLine();
            System.out.println("enter Description: ");
            description = input.nextLine();
            showFacultyDB();
            System.out.println("Enter Instructor by ID to teach this course: ");
            instructorId = input.nextInt();
            input.nextLine();
            int id = courseDB.size() + 1;
            courseDB.add(new Course(id, courseName, description, instructorId));
        }

        //Create Link
        private static void createNewLink () {
            int studentId;
            System.out.println("Here are list of Id of Students....");
            showStudentDB();
            System.out.println("Enter ID you want to link: ");
            studentId = input.nextInt();
            input.nextLine();
            String studentName = searchStudent(studentId);

            int courseId;
            System.out.println("Here are the list of Courses and ID's");
            showCourseDB();
            System.out.println("Enter ID you want to link: ");
            courseId = input.nextInt();
            String courseName = searchCourse(courseId);
            input.nextLine();
            System.out.println("Student Enrolled: " + studentName + "\nCourse: " + courseName);

            System.out.println("Enter Date that you want " + studentName + " to start this course");
            String dateEnrolled = input.nextLine();

            int id = linkDB.size() + 1;
            linkDB.add(new Link(id, studentId, courseId, dateEnrolled));
        }

        //Show information of Students, Faculty and Courses

        private static void showStudentDB () {
            for (Student s : studentDB) {
                System.out.println("ID: "  + s.getID() + "\n" + "Student: " + s.getName());
            }
        }
        private static void showFacultyDB () {
            for (Faculty f : facultyDB) {
                System.out.println("Faculty ID: " + f.getID() + "\n" + "Faculty Name:  " + f.getName());
            }
        }

        private static void showCourseDB () {
            for (Course c : courseDB) {
                System.out.println("Course ID: " + c.getId() + "\n" + "Course: " + c.getCourseName() + "\n" +"Course Description: " + c.getDescription() + "\n" +
                        "Course Instructor: " + searchFaculty(c.getInstructorId()));
            }
        }

        private static void showLinkDBByCourse () {
            for (Course c : courseDB) {
                System.out.println(c.getCourseName() + ":\n");

                for (Link l : linkDB) {
                    if (l.getCourseID() == c.getId()) {
                        for (Student s : studentDB) {
                            if (s.getID() == l.getStudentID()) {
                                System.out.println(s.getName() + " enrolled on " + l.getDateEnrolled());
                            }
                        }
                    }
                }
            }
        }

        private static void showLinkDB() {
        for(Link l: linkDB) {
            System.out.println( "Course: " + searchCourse(l.getCourseID())+ "Instructor: " + searchFaculty(l.getId())  + "\n" + searchStudent(l.getStudentID()) + "\n" +
               "Date Enrolled: "  + l.getDateEnrolled());
            }

        }

        //Search Methods for student and faculty

        private static String searchStudent ( int studentId){
            for (Student s : studentDB) {
                if (s.getID() == studentId) {
                    return s.getName();
                }
            }
            return "";
        }

        private static String searchFaculty ( int facultyId){
            for (Faculty f : facultyDB) {
                if (f.getID() == facultyId) {
                    return f.getName();
                }
            }
            return "";
        }

        private static String searchCourse ( int courseId){
            for (Course c : courseDB) {
                if (c.getId() == courseId) {
                    return c.getCourseName();
                }
            }
            return "";
        }

        //Edit Methods for Student, Faculty and Course

        private static void editStudent () {
            System.out.println("Here is a list of Students..");
            showStudentDB();
            System.out.println("Enter Id of student you want to edit: ");
            int studentId = input.nextInt();
            input.nextLine();
            Student student = null;

            for(Student s: studentDB) {
                if(s.getID() == studentId) {
                    student = s;
                }
            }

            if(student != null) {
                System.out.println("Enter New Name");
                String str = input.nextLine();
                student.setName(str);
                System.out.println("Name is changed!");
                System.out.println("Id: " + student.getID() + "\n" + "Name: " + student.getName() );
            } else {
                System.out.println("Invalid Person!");
            }
        }

        private static void editCourse() {
            System.out.println("Here is list of courses");
            showCourseDB();
            System.out.println("Enter ID of course you want to edit: ");
            int courseId = input.nextInt();
            input.nextLine();
            Course course = null;
            for(Course c: courseDB) {
                if(c.getId() == courseId) {
                    course = c;
                }
            }

            if(course != null) {
                System.out.println("Enter New Name");
                String str = input.nextLine();
                course.setCourseName(str);
                System.out.println("Name is changed!");
                System.out.println("Id: " + course.getId() + "Name " + course.getCourseName() );
            } else {
                System.out.println("Invalid Person!");
            }
        }

        private static void editFaculty() {
            System.out.println("Here is a list of Faculty Members");
            showFacultyDB();
            System.out.println("Enter Id of faculty member you want to edit: ");
            int facultyId = input.nextInt();
            input.nextLine();
            Faculty faculty = null;
            for(Faculty f: facultyDB) {
                if(f.getID() == facultyId) {
                    faculty = f;
                }
            }

            if(faculty != null) {
                System.out.println("Enter New Name");
                String str = input.nextLine();
                faculty.setName(str);
                System.out.println("Name is changed!");
                System.out.println("Id: " + faculty.getID()+ "Name " + faculty.getName() );
            } else {
                System.out.println("Invalid Person!");
            }
        }

        private static ArrayList<Admin> getAdminLogin() {
            ArrayList<Admin> adminUser = new ArrayList<>();
            String email, password;


            System.out.println("Enter Email: ");
            email = input.nextLine();
            System.out.println("Enter Password: ");
            password = input.nextLine();
            boolean validation = toValid(email, password);
            if(validation) {
                adminUser.add(new Admin(1, "Kevin" , email, password)); //fix later
                return adminUser;
            } else {
                System.out.println("Invalid");
                return null;
            }

        }

        public static boolean toValid(String email, String password) {
         for(Admin a: adminDB) {
             if(email.equalsIgnoreCase(a.getEmail()) && password.equalsIgnoreCase(a.getPassword())){
                 return true;
             }
         }
         return false;
        }

    }



