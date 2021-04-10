package cn.edu.nbut.jerry.Utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class MyBatisUtils {
    private static SqlSessionFactory sqlSessionFactory = null;
    /*
      读取配置文件是一个很耗时的操作，希望只执行一次就行，所以放入静态代码块（只执行一次）。
     */
    static {
        String resource = "Configuration.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 创建Session对象
     * @return
     */
    public static SqlSession createSqlSession() {
        return sqlSessionFactory.openSession();
    }

    /***
     * 关闭连接
     * @param sqlSession
     */
    public static void closeAll (SqlSession sqlSession) {
        if (sqlSession != null){
            sqlSession.close();
        }
    }
}
