package model;

import java.util.ArrayList;

public class MemberDAO {
	// 로그인
	// 장바구니 담기 & 구매하기 (??)
	// TODO: ID 중복체크
	public MemberDTO selectOne(MemberDTO memberDTO)
	{
//	    - memberDTO null 체크
//	    - 멤버변수: MID, mPassword
		return null;
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
