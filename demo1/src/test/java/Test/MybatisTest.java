package Test;
import Utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.example.pojo.Course;
import org.junit.Test;
import java.util.List;
import java.util.Scanner;
public class MybatisTest {
    Scanner sc = new Scanner(System.in);
    MybatisUtil mybatisUtil = new MybatisUtil();
    SqlSession sqlSession = mybatisUtil.getSession();
    @Test
    public void test() {
        System.out.println("1.通过id查询的课程信息");
        System.out.println("2.通过学院名称查出这个学院开的所有课程信息");
        System.out.println("3.通过课程id修改这⻔课程的课时数");
        System.out.println("4.插⼊⼀条新的课程记录");
        System.out.println("5.输出所有的学院开设的课程信息");
        System.out.println("6.退出");
        while (true) {
            System.out.print("请输入您要执行的功能：");
            int choose=sc.nextInt();
            switch (choose){
                case 1:findById();
                break;
                case 2:findBySchoolName();
                break;
                case 3: updateByHours();
                    break;
                case 4:insertByCourse();
                    break;
                case 5:findByAll();
                    break;
                default:
                    System.exit (0);
            }
        }
    }
    @Test
    //通过id查询的课程信息
    public void findById(){
        System.out.print("请输入您要查询的课程id：");
        int it = sc.nextInt();
        Course course = sqlSession.selectOne("findById", it);
        System.out.println("查询id=" + it + "的课程信息为：");
        System.out.println(course.toString());
    }
    @Test
    //通过学院名称查出这个学院开的所有课程信息
    public void findBySchoolName() {
        System.out.print("请输入您要查询的学院名称：");
        String str = sc.next();
        List<Course> list = sqlSession.selectList("findBySchoolName", str);
        System.out.println("查询" + str + "的所有课程信息结果为：");
        for(Course course:list) {
            System.out.println(course);
        }
    }
    @Test
    //通过课程id修改这⻔课程的课时数
    public void updateByHours() {
        Course course = new Course();
        System.out.print("请输入要修改课程课时数的id：");
        int it=sc.nextInt();
        System.out.print("请输入要增加的课程课时数数量：");
        int num=sc.nextInt();
        course.setId(it);
        course.setHours(num);
        int result = sqlSession.update("updateByHours", course);
        if(result==1)
        System.out.println("修改成功!");
        else System.out.println("修改失败！");
    }
    @Test
    //插⼊⼀条新的课程记录
    public void insertByCourse() {
        Course course = new Course();
        System.out.print("请输入要插入的课程id：");
        int id=sc.nextInt();
        System.out.print("请输入要插入的课程名：");
        String str=sc.next();
        System.out.print("请输入要插入的课时：");
        int hour=sc.nextInt();
        System.out.print("请输入要插入的开课学院id：");
        int sid=sc.nextInt();
        course.setSid(id);
        course.setName(str);
        course.setHours(hour);
        course.setSid(sid);
        int result = sqlSession.insert("insertByCourse", course);
        if(result==1)
        System.out.println("插入成功！");
        else System.out.println("插入失败！");
    }
    @Test
        //输出所有的学院开设的课程信息
    public void findByAll() {
        List<Course> list = sqlSession.selectList("findByAll");
        System.out.println("所有的学院开设的课程信息为");
        for (Course course : list) {
            System.out.println(course);
        }
    }
}
