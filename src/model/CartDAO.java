package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartDAO {
	private Connection conn; //DB와의 연결을 담당
	private PreparedStatement pstmt; // CRUD 수행을 담당

	private static final String SELECTALL="SELECT CID,PID,MID,CNT FROM CART";
	private static final String SELECTONE="SELECT (CID,MID,CNT) FROM CART WHERE PID=?";
																			//'문자열'
	private static final String INSERT="INSERT INTO CART VALUES((SELECT NVL(MAX(CID),0) + 1 FROM CART),'?',?,?)";
	private static final String UPDATE="UPDATE CART SET CNT = ? WHERE PID = ?";
	private static final String DELETE="DELETE FROM CART WHERE MID = ?"; //카트 전체 비우기
	private static final String DELETE_PID="DELETE FROM CART WHERE PID= ?"; //부분 삭제


	/*
	ctrl
	장바구니 추가할때
	SELECTONE -> 중복검사 ->
    	NOT NULL(같은 pid 있으면) -> UPDATE
    	NULL(같은 pid 없으면) -> INSERT

	장바구니에 같은 상품이 담겨있지 않다면 insert
	같은 상품이 담겨있다면 update
	--------------------------------------------------------------------
	1. 장바구니 추가
	public boolean insert (CartDTO cDTO){ //성공 여부 반환 
		매개변수 : CartDTO 모두
	(cnt가 음수 또는 0)라면
		-> false 반환
	conn 연결
    pstmt연결
    INSERT쿼리문 실행
		-> cart DB에 추가 CartDTO 모두 저장
		RS에 결과값 담기

		conn,pstmt 해제
			RS값이 있다면
			true 반환;

			아니면 false반환;

			선택한 상품의 cnt가 실재고보다 작다면, 담지 못함
	    return = null; //insert 실패

	2. 
	public CartDTO selectOne(CartDTO cDTO){ //1개 조회
			매개변수 : PID
		반환해줄 CartDTO data 초기화 
		//CartDTO data=null;

		conn연결
		pstmt연결
		SELECTONE 쿼리 실행
		PID 중복검사 -> 일치(장바구니에 있는 상품이면)
		data에 저장

		conn,pstmt 해제

		data 반환; //저장된 값이 없다면 초기값 null이 반환됨
	 }   

	 3.
	 public boolean update(CartDTO cDTO){ //성공 여부 반환
	 	매개변수 : PID,CNT
	 	conn연결
	 	pstmt연결
	 	UPDATE 쿼리문 실행
	 	RS생성
	 	장바구니의 해당 pid의 cnt 추가해주기
	 	RS해제?
	 	결과값이 없다면 {
	 	false 반환;
	 	}
	 	true 반환;
	 }

	 -------------------------------------------------
	장바구니 목록보기R(2개이상 조회) selectall
	담긴 제품 정보 보여주기
	-> 반환 cartList(CID,MID,PID,CNT)


	4.
	public ArrayList<CartDTO> selectAll(CartDTO cDTO){ 
			매개변수 : MID
		반환해 줄 ArrayList<CartDTO> cartList 생성 -> !null -> 아래 쿼리에서 rs를 담던 안담던 계속 null이 아닌게 된다!

		conn 연결
		pstmt연결

		SELECTAll쿼리문 실행

		결과값 resultSet에 담기

			rs결과값이 있다면{
			cartDTO data 생성
			data에 CART테이블에서 가져온 정보 저장(CID,MID,PID,CNT)

			catch 
			return null;

			cartList에 data에 담긴 정보 추가
		}
		사용이 끝난 rs 해제
		conn,pstmt해제


		카트리스트가 비었다면 null, 비어있지 않다면 cartList 반환 //에러면 null, 빈배열
		//return cartList.isEmpty() ? null : cartList; ->제거
}		

	5.장바구니 비우기
	public boolean delete(CartDTO cDTO) { //성공 여부 반환
							매개변수 mid->일치하는것 삭제
	conn연결
	pstmt연결

		delete 쿼리문실행
		장바구니db 데이터 삭제실행 
		그결과값을 result에 담기
		result가 0이면(삭제가 이루어지지 않았다면)
		false반환;

	conn해제
	pstmt해제

	return true;
	}

	 */
	

	public  boolean delete(CartDTO cDTO) {
//		매개변수 : MID로 일치하는 데이터 삭제
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(DELETE);
			pstmt.setString(1, cDTO.getMid());
			int result = pstmt.executeUpdate();//영향을 받은 숫자 저장
			if(result<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disconnection(pstmt, conn);
		}return true;
	}

	
	public boolean insert (CartDTO cDTO){
		if(cDTO.getCnt()<=0) {
			return false;
		}
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(INSERT); //MID,PID,CNT
			pstmt.setString(1, cDTO.getMid());
			pstmt.setInt(2, cDTO.getPid());
			pstmt.setInt(3, cDTO.getCnt());
			int result = pstmt.executeUpdate();//영향을 받은 숫자 저장
			if(result<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disconnection(pstmt, conn);
		}
		return true;
	}

//	public ArrayList<CartDTO> selectAll(){ 
//		반환해 줄 ArrayList<CartDTO> cartList 생성 -> !null -> 아래 쿼리에서 rs를 담던 안담던 계속 null이 아닌게 된다!
//
//		conn 연결
//		pstmt연결
//
//		SELECTAll쿼리문 실행
//
//		결과값 resultSet에 담기
//
//			rs결과값이 있다면{
//			cartDTO data 생성
//			data에 CART테이블에서 가져온 정보 저장(CID,MID,PID,CNT)
//
//			catch 
//			return null;
//
//			cartList에 data에 담긴 정보 추가
//		}
//		사용이 끝난 rs 해제
//		conn,pstmt해제
//	return cartlist;
	
	public ArrayList<CartDTO> selectAll(){
		ArrayList<CartDTO> cdatas = new ArrayList<CartDTO>(); //!null
		
//		매개변수 : xxxx
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTALL);
//			private static final String SELECTALL="SELECT CID,PID,MID,CNT FROM CART";
			ResultSet rs=pstmt.executeQuery();

			while(rs.next()) {
				CartDTO data=new CartDTO();
				data.setCid(rs.getInt("CID"));
				data.setMid(rs.getString("MID"));
				data.setPid(rs.getInt("PID"));
				data.setCnt(rs.getInt("CNT"));
				cdatas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			JDBCUtil.disconnection(pstmt, conn);
		}
		return cdatas;
	}

//	 public boolean update(CartDTO cDTO){ //성공 여부 반환
//	 	매개변수 : PID,CNT
//	 	conn연결
//	 	pstmt연결
//	 	UPDATE 쿼리문 실행
//	 	RS생성
//	 	장바구니의 해당 pid의 cnt 추가해주기
//	 	RS해제?
//	 	결과값이 없다면 {
//	 	false 반환;
//	 	}
//	 	true 반환;
//	 }
	public boolean update(CartDTO cDTO){
//	 	매개변수 : PID,CNT
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(UPDATE);
//			private static final String UPDATE="UPDATE CART SET CNT = ? WHERE PID = ?";
			pstmt.setInt(1, cDTO.getCnt());
			pstmt.setInt(2, cDTO.getPid());
			int result = pstmt.executeUpdate();//영향을 받은 숫자 저장
			if(result<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disconnection(pstmt, conn);
		}
		return true;
	}

//	public CartDTO selectOne(CartDTO cDTO){ //1개 조회
//			매개변수 : PID
//		반환해줄 CartDTO data 초기화 
//		//CartDTO data=null;
//
//		conn연결
//		pstmt연결
//		SELECTONE 쿼리 실행
//		PID 중복검사 -> 일치(장바구니에 있는 상품이면)
//		data에 저장
//
//		conn,pstmt 해제
//
//		data 반환; //저장된 값이 없다면 초기값 null이 반환됨
//	 }   
	public CartDTO selectOne(CartDTO cDTO){ 
//		매개변수 : PID
		CartDTO data = null;
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTONE);
//			private static final String SELECTONE="SELECT (CID,MID,CNT) FROM CART WHERE PID=?";
			pstmt.setInt(1, cDTO.getPid());
			ResultSet rs=pstmt.executeQuery();

			while(rs.next()) {
				data=new CartDTO();
				data.setCid(rs.getInt("CID"));
				data.setMid(rs.getString("MID"));
				data.setCnt(rs.getInt("CNT"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			JDBCUtil.disconnection(pstmt, conn);
		}
		
		
		return data;
	}
	

	
	
}

