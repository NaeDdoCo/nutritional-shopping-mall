package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ProductDAO {
	
	// DB와의 연결을 담당
	private Connection conn;
	// CRUD 수행을 담당
	private PreparedStatement pstmt;
	
	// RAll, 제품 목록 출력
	private static final String selectAll = "SELECT PID, PNAME, COSTPRICE, REGULARPRICE, SELLINGPRICE, CNT, INGREDIENT, USAGE, EXP, CATEGORY FROM PRODUCT";

	// ROne, 제품 상세 정보 출력 & 재고 확인
	// PID로 확인
	private static final String selectOne = "SELECT PNAME, COSTPRICE, REGULARPRICE, SELLINGPRICE, CNT, INGREDIENT, USAGE, EXP, CATEGORY FROM PRODUCT WHERE PID = ?";
	
	// U, 제품 구매 시 재고 차감
	// PID로 확인
	private static final String insert = "INSERT INTO PRODUCT (PID, PNAME, COSTPRICE, REGULARPRICE, SELLINGPRICE, CNT, INGREDIENT, USAGE, EXP, CATEGORY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	// U, 제품 구매 시 재고 차감
	// PID로 확인
	private static final String update = "UPDATE PRODUCT SET CNT = CNT - ? WHERE PID = ?";

	// 제품 목록 출력
	// TODO: 제품 추천 받기
	public ArrayList<ProductDTO> selectAll(ProductDTO productDTO)
	{
		ArrayList<ProductDTO> ret = new ArrayList<ProductDTO>();
		conn = JDBCUtil.connect();
		
		try {
			pstmt = conn.prepareStatement(selectAll);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ProductDTO pDTO = new ProductDTO();

				pDTO.setPID(rs.getInt("PID"));
				pDTO.setpName(rs.getString("PNAME"));
				pDTO.setCostPrice(rs.getInt("COSTPRICE"));
				pDTO.setRegularPrice(rs.getInt("REGULARPRICE"));
				pDTO.setSellingPrice(rs.getInt("SELLINGPRICE"));
				pDTO.setCnt(rs.getInt("CNT"));
				pDTO.setIngredient(rs.getString("INGREDIENT"));
				pDTO.setUsage(rs.getString("USAGE"));
				pDTO.setExp(rs.getString("EXP"));
				pDTO.setCategory(rs.getString("CATEGORY"));
				
				ret.add(pDTO);
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnection(pstmt, conn);
		}
		
		return ret;
	}
	
	// 제품 상세 정보 출력 & 재고 확인
	// - 필요한 멤버변수: PID
	public ProductDTO selectOne(ProductDTO productDTO)
	{
		if (productDTO == null) {
			return null;
		}

		ProductDTO ret = new ProductDTO();
		
		conn = JDBCUtil.connect();
		
		try {
			pstmt = conn.prepareStatement(selectOne);

			pstmt.setInt(1, productDTO.getPID());
			
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				ret.setPID(productDTO.getPID());
				
				ret.setpName(rs.getString("PNAME"));
				ret.setCostPrice(rs.getInt("COSTPRICE"));
				ret.setRegularPrice(rs.getInt("REGULARPRICE"));
				ret.setSellingPrice(rs.getInt("SELLINGPRICE"));
				ret.setCnt(rs.getInt("CNT"));
				ret.setIngredient(rs.getString("INGREDIENT"));
				ret.setUsage(rs.getString("USAGE"));
				ret.setExp(rs.getString("EXP"));
				ret.setCategory(rs.getString("CATEGORY"));
			}
			rs.close(); 

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnection(pstmt, conn);
		}

		return ret;
	}
	
	// 웹 크롤링 시 추가
	// - 필요한 멤버변수: all
	// TODO: 제품 정보 추가
	public boolean insert(ProductDTO productDTO)
	{
		if (productDTO == null) {
			return false;
		}
		
		int res = 0;
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(insert);

			pstmt.setInt(1, productDTO.getPID());
			pstmt.setString(2, productDTO.getpName());
			pstmt.setInt(3, productDTO.getCostPrice());
			pstmt.setInt(4, productDTO.getRegularPrice());
			pstmt.setInt(5, productDTO.getSellingPrice());
			pstmt.setInt(6, productDTO.getCnt());
			pstmt.setString(7, productDTO.getIngredient());
			pstmt.setString(8, productDTO.getUsage());
			pstmt.setString(9, productDTO.getExp());
			pstmt.setString(10, productDTO.getCategory());
			
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
	
	// 제품 구매
	// - 필요한 멤버변수: PID, CNT
	// TODO: 제품 정보 변경
	// TODO: 환불 & 취소
	public boolean update(ProductDTO productDTO)
	{
		if (productDTO == null) {
			return false;
		} else if (productDTO.getCnt() <= 0) {
			return false;
		}
		
		int res = 0;
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(update);

			pstmt.setInt(1, productDTO.getCnt());
			pstmt.setInt(2, productDTO.getPID());
			
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
	
	// TODO: 제품 정보 삭제
	private boolean delete(ProductDTO productDTO)
	{
		return false;
	}

}
