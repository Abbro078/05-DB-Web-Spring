import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {

    private int id;
    private String email;
    private String name;
    private String major;
    private List<Course>courses;

    public Student(int id, String email, String name, String major, List<Course> courses) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.major = major;
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", courses=" + courses +
                '}';
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
