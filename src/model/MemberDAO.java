package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
	
	// DB와의 연결을 담당
	private Connection conn;
	// CRUD 수행을 담당
	private PreparedStatement pstmt;

	// RAll, 멤버 목록 출력
	private static final String selectAll = "SELECT MID, MNAME, MPASSWORD, YOB, GENDER, NATIONALITY, PHONENUMBER, ADDRESS, GRADE, HEALTH FROM MEMBER";

	// ROne, 로그인
	// MID와 MPASSWORD로 확인
	private static final String selectOne = "SELECT MNAME, YOB, GENDER, NATIONALITY, PHONENUMBER, ADDRESS, GRADE, HEALTH FROM MEMBER WHERE MID = ? AND MPASSWORD = ?";
	
	// 로그인
	// - 필요한 멤버변수: MID, mPassword
	// TODO: ID 중복체크
	public MemberDTO selectOne(MemberDTO memberDTO)
	{
		MemberDTO ret = new MemberDTO();
		
		if (memberDTO == null) {
			return null;
		} else if (memberDTO.getMID() == null) {
			return null;
		} else if (memberDTO.getmPassword() == null) {
			return null;
		}
		
		conn = JDBCUtil.connect();
		
		try {
			pstmt = conn.prepareStatement(selectOne);

			pstmt.setString(1, memberDTO.getMID());
			pstmt.setString(2, memberDTO.getmPassword());
			
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				ret.setMID(memberDTO.getMID());
				ret.setmPassword(memberDTO.getmPassword());
				
				ret.setmName(rs.getString("MNAME"));
				ret.setYob(rs.getDate("YOB"));
				ret.setGender(rs.getString("GENDER"));
				ret.setNationality(rs.getString("NATIONALITY"));
				ret.setPhoneNumber(rs.getString("PHONENUMBER"));
				ret.setAddress(rs.getString("ADDRESS"));
				ret.setGrade(rs.getString("GRADE"));
				ret.setHealth(rs.getString("HEALTH"));
			}
			rs.close(); 

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnection(pstmt, conn);
		}

		return ret;
	}
	
	private ArrayList<MemberDTO> selectAll(MemberDTO memberDTO)
	{
		return null;
	}

	// TODO: 회원가입
	private boolean insert(MemberDTO memberDTO)
	{
		return false;
	}
	
	// TODO: 개인 정보 수정
	// TODO: 비밀번호 수정
	private boolean update(MemberDTO memberDTO)
	{
		return false;
	}
	
	// TODO: 회원탈퇴
	private boolean delete(MemberDTO memberDTO)
	{
		return false;
	}
}
