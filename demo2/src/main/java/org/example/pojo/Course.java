package org.example.pojo;

public class Course {
      private int id;       //课程id
      private String name;  //课程名称
      private int hours;    //课时数
      private int sid;      //所属学院id

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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public Course(int id, String name, int hours, int sid) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.sid = sid;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", sid=" + sid +
                '}';
    }
}
