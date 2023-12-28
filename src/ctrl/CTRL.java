package ctrl;

import model.MemberDTO;
import view.VIEW;

public class CTRL {
	
	private VIEW view;
	private MemberDTO loginINFO;

	public CTRL() {
		
		view = new VIEW();
		loginINFO = null;
		
	}

	public void start() {
		
		while (true) {
			
			if (loginINFO == null) {
				
				// 비로그인 상태
				view.printLogoutMenu();
				int action = view.inputAction();
				
				if (action == 0) {
					
					break;
					
				} else if (action == 1) {
				} else if (action == 2) {
				} else if (action == 3) {
				} else if (action == 4) {	
				}
				
//			} else if (일반유저라면) {
				
				// 일반유저 메뉴 출력
				
					// 장바구니만?
				
				// 메뉴 중 선택(입력값을 받음)
				
				// if(입력값이 1이면){ // 구매하기
				
					// 장바구니 내용 출력(이름 개수 금액)
				
					// 메뉴 출력
			
					// 메뉴 중 선택(입력값을 받음)
			
					// 입력값이 1이면 구매 기능으로 진입
			
						// 쿠폰을 적용할지 질문(입력값을 받음)
			
							// 입력값이 Y면
			
								// 쿠폰 리스트 출력
			
									// 사용하지 않은 쿠폰만 출력
			
								// 쿠폰 선택(입력값을 받음)
			
									// 쿠폰DB의 쿠폰 상태 변화
				
								// 쿠폰을 더 적용할건지 질문(입력값을 받음)
			
									// 입력값이 Y면 구매기능 진입으로 다시 이동
			
									// 입력값이 N이면 구매기능에서 탈출
			
									// 쿠폰이 적용된 금액으로 결제
			
										// 해당 유저의 장바구니DB 비우기
			
										// 해당 유저의 구매내역 추가
			
							// 입력값이 N이면
			
								// 구매 기능에서 탈출
			
								// 쿠폰이 적용안된 금액으로 결제
			
									// 해당 유저의 장바구니DB 비우기
			
									// 해당 유저의 구매내역 추가
				
				
				// } else if(입력값이 2이면){ // 구매내역
				
					// 해당 유저의 구매내역DB을 R하여 표시
				
				// }
				
				
//			} else if (관리자라면) {
				
			}
			
		}
		
	}
	
}