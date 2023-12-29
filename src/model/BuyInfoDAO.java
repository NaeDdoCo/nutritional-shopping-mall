package model;

import java.util.ArrayList;

public class BuyInfoDAO {
	
	// TODO: 배송 상태 확인
	private BuyInfoDTO sellectOne(BuyInfoDTO buyInfoDTO)
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
//	    - buyInfoDTO null 체크
//	    - CPID는 nullable
//	    - buyDate도 외부 입력
//	    - 그 외에는 모두 매개변수에 포함
		return false;
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
