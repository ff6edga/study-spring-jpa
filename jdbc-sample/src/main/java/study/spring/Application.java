package study.spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Application {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/springdata";
		String username = "younsoo";
		String password = "pass";

		try(Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Connection created :" + connection);
			// 1, Create Account table
			//String sql = "CREATE TABLE ACCOUNT (id int, username varchar(255), passoword varchar(255));";

			//2, Insert a account value
			String sql = "INSERT INTO ACCOUNT VALUES(1, 'younsoo', 'pass');";
			try(PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.execute();
			}
		}
	}
}
