package Utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory = null;
    // 初始化SqlSessionFactory对象
    static {
        try {
// 使⽤MyBatis提供的Resources类加载MyBatis的配置⽂件
            Reader reader =
                    Resources.getResourceAsReader("mybatis-config.xml");
// 构建SqlSessionFactory⼯⼚
            sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 获取SqlSession对象的静态⽅法
    public static SqlSession getSession() {
        //开启一个事务
        return sqlSessionFactory.openSession(true);
    }
}
