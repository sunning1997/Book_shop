import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class c3p0_test {

		public static void main(String[] args) throws SQLException {
			// 方式一: 使用默认配置（default‐config）
			// new ComboPooledDataSource();
			// ComboPooledDataSource ds = new ComboPooledDataSource();
			// 方式二： 使用命名配置（named‐config：配置名）
			// new ComboPooledDataSource("配置名");

			//方式二使用
			ComboPooledDataSource ds = new ComboPooledDataSource("itcast");
			Connection connection = ds.getConnection();
			System.out.println(connection);
			connection.close();
		}

}
