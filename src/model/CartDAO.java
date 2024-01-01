package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartDAO {
	private Connection conn; //DB와의 연결을 담당
	private PreparedStatement pstmt; // CRUD 수행을 담당

	private static final String SELECTALL="SELECT CID,PID,MID,CNT FROM CART WHERE MID=?";
	private static final String SELECTONE="SELECT (CID,MID,CNT) FROM CART WHERE PID=?";
	private static final String INSERT="INSERT INTO CART VALUES((SELECT NVL(MAX(CID),0) + 1 FROM CART),?,?,?)";
	private static final String UPDATE="UPDATE CART SET CNT = ? WHERE PID = ?";
	private static final String DELETE="DELETE FROM CART WHERE MID = ?"; //카트 전체 비우기
	private static final String DELETE_PID="DELETE FROM CART WHERE MID =? AND PID= ?"; //부분 삭제


	/*
	ctrl
	장바구니 추가할때
	SELECTONE -> 중복검사 ->
    	NOT NULL(같은 pid 있으면) -> UPDATE
    	NULL(같은 pid 없으면) -> INSERT

	장바구니에 같은 상품이 담겨있지 않다면 insert
	같은 상품이 담겨있다면 update
	 */


	//장바구니 목록 조회: MID를 입력받아 일치하는 Cart내역 반환
	public ArrayList<CartDTO> selectAll(CartDTO cartDTO){
		//반환해 줄 cdatas 생성
		ArrayList<CartDTO> cdatas = new ArrayList<CartDTO>(); //!null

		conn=JDBCUtil.connect(); //DB연결
		try {
			pstmt=conn.prepareStatement(SELECTALL);
//			static final String SELECTALL="SELECT CID,PID,MID,CNT FROM CART WHERE MID=?";
			pstmt.setString(1, cartDTO.getMid());
			ResultSet rs=pstmt.executeQuery(); //쿼리 실행 후 결과로 나온 데이터를 rs에 담기
			
			while(rs.next()) { //결과 데이터가 있을때 까지 반복->rs의 결과값을 data에 담아 cdatas에 저장
				CartDTO data=new CartDTO();
				data.setCid(rs.getInt("CID"));
				data.setMid(rs.getString("MID"));
				data.setPid(rs.getInt("PID"));
				data.setCnt(rs.getInt("CNT"));
				cdatas.add(data);
			}
			rs.close(); //ResultSet 해제
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			JDBCUtil.disconnection(pstmt, conn); //필수조건 : DB연결해제
		}
		return cdatas;
	}

	public CartDTO selectOne(CartDTO cartDTO){ 
		//      PID를 입력받아 존재여부 확인?? -> 현재 미사용
		CartDTO data = null;
		conn=JDBCUtil.connect();//DB연결
		try {
			pstmt=conn.prepareStatement(SELECTONE);
//			static final String SELECTONE="SELECT (CID,MID,CNT) FROM CART WHERE PID=?";
			pstmt.setInt(1, cartDTO.getPid());
			ResultSet rs=pstmt.executeQuery(); //입력된 PID에 해당하는 결과 데이터를 rs 저장

			while(rs.next()) { 
				data=new CartDTO();
				data.setCid(rs.getInt("CID"));
				data.setMid(rs.getString("MID"));
				data.setCnt(rs.getInt("CNT"));
			}
			rs.close();//ResultSet 해제
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			JDBCUtil.disconnection(pstmt, conn); //필수조건 : DB연결해제
		}
		return data;
	}

	//장바구니 추가
	public boolean insert (CartDTO cartDTO){
		if(cartDTO.getCnt()==0) { 		//입력할 cnt가 0이라면
			return false;				//insert 실패
		}
		conn=JDBCUtil.connect(); //DB연결
		try {
			pstmt=conn.prepareStatement(INSERT);
//			static final String INSERT="INSERT INTO CART VALUES((SELECT NVL(MAX(CID),0) + 1 FROM CART),?,?,?)";
			pstmt.setString(1, cartDTO.getMid());
			pstmt.setInt(2, cartDTO.getPid());
			System.out.println(cartDTO.getCnt());
			pstmt.setInt(3, cartDTO.getCnt()); 
			int result = pstmt.executeUpdate(); //추가된 행의 수 저장
			if(result<=0) { 					//추가된 행의 수가 없다면
				return false;					//INSERT 실패 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disconnection(pstmt, conn); //필수조건 : DB연결해제
		}
		return true; //INSERT 성공 반환
	}

	//장바구니에 같은 제품이 있다면 수량만 변경
	public boolean update(CartDTO cartDTO){
		//	    해당하는 PID의 CNT를 추가
		conn=JDBCUtil.connect(); //DB연결
		try {
			pstmt=conn.prepareStatement(UPDATE);
//			static final String UPDATE="UPDATE CART SET CNT = ? WHERE PID = ?";
			pstmt.setInt(1, cartDTO.getCnt());
			pstmt.setInt(2, cartDTO.getPid());
			int result = pstmt.executeUpdate(); //변경된 행의 수 저장
			if(result<=0) { 					//변경된 행의 수가 없다면 
				return false; 					//UPDATE 실패 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disconnection(pstmt, conn); //필수조건 : DB연결해제
		}
		return true; //UPDATE 성공 반환
	}

	//장바구니 비우기
	public  boolean delete(CartDTO cartDTO) {
		//		MID로 일치하는 데이터 삭제
		conn=JDBCUtil.connect(); //DB연결
		try {
			pstmt=conn.prepareStatement(DELETE);
//			static final String DELETE="DELETE FROM CART WHERE MID = ?";
			pstmt.setString(1, cartDTO.getMid());
			int result = pstmt.executeUpdate(); //삭제된 행의 수 저장
			if(result<=0) {  					//삭제가 이루어지지 않았다면
				return false;  					//DELETE 실패 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disconnection(pstmt, conn); //필수조건 : DB해제
		}
		return true; //DELETE 성공 반환
	}


}


