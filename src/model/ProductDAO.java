package model;

import java.util.ArrayList;

public class ProductDAO {
	
	// 제품 상세 정보 출력 & 재고 확인
	public ProductDTO selectOne(ProductDTO productDTO)
	{
//	    - productDTO null 체크
//		- 멤버변수: PID
		return null;
	}
	
	// 제품 목록 출력
	// TODO: 제품 추천 받기
	public ArrayList<ProductDTO> selectAll(ProductDTO productDTO)
	{
//	    - 모든 제품 출력
//		- select * 쓰지 않기
		return null;
	}
	
	// TODO: 제품 정보 추가
	private boolean insert(ProductDTO productDTO)
	{
		return false;
	}
	
	// 제품 구매
	// TODO: 제품 정보 변경
	// TODO: 환불 & 취소
	public boolean update(ProductDTO productDTO)
	{
//	    - productDTO null 체크
//		- 멤버변수: PID, CNT
		return false;
	}
	
	// TODO: 제품 정보 삭제
	private boolean delete(ProductDTO productDTO)
	{
		return false;
	}

}
