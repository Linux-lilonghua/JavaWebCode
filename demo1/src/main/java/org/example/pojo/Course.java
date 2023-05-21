package org.example.pojo;

public class Course {
      private int id; //课程id
      private String name; //课程名
      private int hours; //课时
      private int sid;  //开课学院
      private String schoolname; //学院名称

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

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
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

    public String allinfo(){
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", sid=" + sid +
                ", schoolname='" + schoolname + '\'' +
                '}';
    }
}
