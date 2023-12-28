package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil {

	static final String driverName = "oracle.jdbc.driver.OracleDriver";
	static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static final String user = "YUMI";
	static final String passwd = "1234";

	public static Connection connect() {
		// 연결기능의 객체 정의
		Connection conn = null;

		try {
			// 메모리에 DB적재
			Class.forName(driverName);

			// DB정보 계정 정보 입력 하여 연결
			// SQLException e 예외처리를 꼭 추가하자
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return conn;

	}

	public static void disconnection(PreparedStatement pstmt, Connection conn) {

		try {
			// 연결 해제
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}