

import java.io.Reader;
import java.sql.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.playcrab.storm.demo.model.BasicData;

public class TestMysql {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		try {
			reader = Resources.getResourceAsReader("mybitis-map-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	
	 @Test
	public void test1() {
		SqlSession session = sqlSessionFactory.openSession();
		BasicData basicData=new BasicData();
		basicData.setDau(100L);
		basicData.setDt(new Date(new java.util.Date().getTime()));
		basicData.setGame("kof");
		basicData.setGamesvrid("s11");
		basicData.setPlatform("appstore");
		basicData.setViplevel(1);
		try {
			int rs = session.insert("com.playcrab.storm.demo.dao.IBasicData.insert", basicData);
			session.commit();
			System.out.println(rs);
		} finally {
			session.close();
		}
	}
}
