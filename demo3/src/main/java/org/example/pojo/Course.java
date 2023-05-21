package org.example.pojo;

public class Course {
      private int id;       //课程id
      private String name;  //课程名称
      private int hours;    //课时数
      private int sid;      //所属学院id
      private String image;     //图片地址

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public Course(int id, String name, int hours, int sid, String image) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.sid = sid;
        this.image = image;
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
                ", image='" + image + '\'' +
                '}';
    }
}
