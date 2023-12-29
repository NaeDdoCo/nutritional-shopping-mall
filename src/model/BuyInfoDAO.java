package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BuyInfoDAO {
	
	// DB와의 연결을 담당
	private Connection conn;
	// CRUD 수행을 담당
	private PreparedStatement pstmt;
	
	// I, 제품 구매 내역 생성
	private static final String insert = "INSERT INTO BUYINFO (BID, MID, PID, CPID, ORDERNUM, DELISTAT, CNT, PAYMENTPRICE, BUYDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	// TODO: 배송 상태 확인
	private BuyInfoDTO selectOne(BuyInfoDTO buyInfoDTO)
	{
//	    - buyInfoDTO null 체크
//	    - 매개변수: orderNum
		return null;
	}

	// TODO: 주문 목록 확인
	private ArrayList<BuyInfoDTO> selectAll(BuyInfoDTO buyInfoDTO)
	{
//	    - buyInfoDTO null 체크
//	    - 매개변수: orderNum
		return null;
	}

	// 구매 하기
	public boolean insert(BuyInfoDTO buyInfoDTO)
	{
		if (buyInfoDTO == null) {
			return false;
		}
		
		int res = 0;
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(insert);

			pstmt.setInt(1, buyInfoDTO.getBID());
			pstmt.setString(2, buyInfoDTO.getMID());
			pstmt.setInt(3, buyInfoDTO.getPID());
			pstmt.setString(4, buyInfoDTO.getCPID());
			pstmt.setInt(5, buyInfoDTO.getOrderNum());
			pstmt.setString(6, buyInfoDTO.getDeliStat());
			pstmt.setInt(7, buyInfoDTO.getCnt());
			pstmt.setInt(8, buyInfoDTO.getPaymentPrice());
			pstmt.setDate(9, buyInfoDTO.getBuyDate());

			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnection(pstmt, conn);
		}

		if (res > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	// TODO: 배송 상태 변경
	// TODO: 환불 & 취소
	private boolean update(BuyInfoDTO buyInfoDTO)
	{
		return false;
	}
	
	private boolean delete(BuyInfoDTO buyInfoDTO)
	{
		return false;
	}
}
