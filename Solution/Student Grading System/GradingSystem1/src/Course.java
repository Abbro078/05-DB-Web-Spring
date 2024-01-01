import java.io.Serializable;

public class Course implements Serializable {
    private int id;
    private String name;
    private Integer mark;


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mark=" + mark +
                '}';
    }

    public Course(int id, String name, Integer mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}