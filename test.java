import com.esbuy.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserTest {
    public static void selectUserbyname() throws Exception{
        String resource = "mybatis-config.xml";

        try {
            //读取配置文件
            InputStream inputStream = Resources.getResourceAsStream(resource);
            //创建sqlsessionfactory会话工厂
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session=sqlSessionFactory.openSession();
            //执行并打印
            List<User> list=session.selectList("selectUser","zhangsan");
            for (User u:list){
                System.out.println(u.getUname()+" "+u.getUphone());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void addUser() throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
        User u=new User();
        u.setUname("zhangsan");
        u.setUpwd("123456");
        u.setUsex("男");
        u.setUphone("15311111111");
        int i=session.update("addUser",u);
        session.commit();
        System.out.println(i);
        session.close();

    }
    public static void main(String[] args) throws Exception{
        //读取核心配置文件
        addUser();
        selectUserbyname();
    }
}
