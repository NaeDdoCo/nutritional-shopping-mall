package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CouponDAO {

	// DB와의 연결을 담당
	private Connection conn;
	// CRUD 수행을 담당
	private PreparedStatement pstmt;

	// RAll, 쿠폰 목록 출력
	// 출력대상 : 쿠폰이름 쿠폰번호 쿠폰할인율 쿠폰유효기간 쿠폰사용 가능한 카테고리
	public static final String selectAll = "SELECT CPID, CPNAME, PERIOD, DISCOUNT, CATEGORY FROM COUPON";

	// ROne, 쿠폰번호 확인
	// 선택한 쿠폰번호와 같은 행의 쿠폰번호와 할인율을 가져온다
	// MID(쿠폰보유회원)도 같이 가져와서 유효성 검사에 사용
	public static final String selectOne = "SELECT CPID, DISCOUNT, MID FROM COUPON WHERE CPID = ?";

	// C, 쿠폰 추가 및 부여
	// 생성해서 부여할 때 생성해야 하는 컬럼
	// 쿠폰번호, 받을 회원, 쿠폰이름, 쿠폰유효기간, 쿠폰할인율, 카테고리
	// 미부여 : 적용상품 null, 사용여부는 미사용으로 기본값 지정
	// 조건 : 받을 회원()
	private static final String insert = "INSERT INTO COUPON " + "(CPID, MID, CPNAME, PERIOD, DISCOUNT, CATEGORY) "
			+ "VALUES (?, ?, ?, ?, ?, ?)";

	// U, 쿠폰정보변경
	// 사용하면 쿠폰에 사용여부 갱신
	// 조건은 입력한 쿠폰번호
	public static final String update = "UPDATE COUPON SET USED = '사용' WHERE CPID = ?";

	// D1, 제품 삭제(회수?)
	// 문제있는 쿠폰을 삭제
	private static final String deleteCPNAME = "DELETE FROM COUPON WHERE CPNAME = ?";

	// D2, 제품 삭제(회수?)
	// 쿠폰을 잘못주면 회수
	private static final String deleteMID = "DELETE FROM COUPON WHERE MID = ?";

	public ArrayList<CouponDTO> selectAll() {
		// DB에 연결해서 COUPON테이블에 있는 데이터를 불러온다
		// 불러온 데이터를 DTO객체에 넣어준다
		// 데이터가 들어간 객체를 리스트에 넣어준다

		ArrayList<CouponDTO> cDTO = new ArrayList<CouponDTO>();

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(selectAll);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				// 출력대상 : 쿠폰이름 쿠폰번호 쿠폰할인율 쿠폰유효기간 사용가능한 카테고리
				String CPID = rs.getString("CPID");
				String couponName = rs.getString("CPNAME");
				int discount = rs.getInt("DISCOUNT");
				LocalDateTime period = rs.getTimestamp("PERIOD").toLocalDateTime();
				String category = rs.getString("CATEGORY");

				CouponDTO couponDTO = new CouponDTO();

				couponDTO.setCPID(CPID);
				couponDTO.setCouponName(couponName);
				couponDTO.setDiscount(discount);
				couponDTO.setPeriod(period);
				couponDTO.setCategory(category);

				cDTO.add(couponDTO);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnection(pstmt, conn);
		}

		return cDTO;
	}

	public CouponDTO selectOne(CouponDTO couponDTO) {
		// 쿠폰번호를 입력해 있는 쿠폰이라면 쿠폰정보 반환

		// 입력한 쿠폰번호
		String CPID = couponDTO.getCPID();

		conn = JDBCUtil.connect();

		try {

			pstmt = conn.prepareStatement(selectOne);

			pstmt.setString(1, CPID);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				couponDTO.setCPID(rs.getString("CPID"));
				couponDTO.setDiscount(rs.getInt("DISCOUNT"));
				couponDTO.setMID(rs.getString("MID"));
			}

			rs.close(); 

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnection(pstmt, conn);
		}

		return couponDTO;
	}

	private boolean insert(CouponDTO couponDTO) {
		return true;
	}

	public boolean update(CouponDTO couponDTO) {
		// selectOne에서 값이 있다면
		// 입력한 쿠폰번호를 가진 행의 사용여부를 변경

		// SQL의 결과를 반환할 변수
		int result = 0;

		// 입력한 제품의 쿠폰번호
		String CPID = couponDTO.getCPID();

		// DB에 연결
		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, CPID);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB연결 종료
			JDBCUtil.disconnection(pstmt, conn);
		}

		if (result > 0) {	// result가 1이상이면 성공
			return true;
		}
		return false;	// 아니면 실패
	}

	private boolean delete(CouponDTO couponDTO) {
		return true;
	}
}