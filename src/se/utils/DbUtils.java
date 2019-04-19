/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/13
 */

package se.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DbUtils<T> {
    public SqlSession session;
    public T mapper;

    public DbUtils(Class<T> tClass) {
        InputStream inputStream;
        {
            try {
                String resource = "se/utils/mybatis-config.xml";
                inputStream = Resources.getResourceAsStream(resource);
                SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                session = sqlSessionFactory.openSession();
                mapper = session.getMapper(tClass);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
