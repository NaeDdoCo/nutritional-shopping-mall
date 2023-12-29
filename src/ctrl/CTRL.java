package ctrl;

import java.util.ArrayList;
import java.util.Scanner;

import model.BuyInfoDAO;
import model.CartDAO;
import model.CartDTO;
import model.CouponDAO;
import model.MemberDAO;
import model.MemberDTO;
import model.ProductDAO;
import model.ProductDTO;
import view.VIEW;

// 깃테스트

public class CTRL {

	private MemberDAO memberDAO;
	private ProductDAO productDAO;
	private CartDAO cartDAO;
	private BuyInfoDAO buyInfoDAO;
	private CouponDAO couponDAO;

	private MemberDTO loginINFO;

	private VIEW view;

	public CTRL() {

		memberDAO = new MemberDAO();
		productDAO = new ProductDAO();
		cartDAO = new CartDAO();
		buyInfoDAO = new BuyInfoDAO();
		couponDAO = new CouponDAO();

		loginINFO = null;

		view = new VIEW();

	}

	public void start() {

		while (true) {

			if (loginINFO == null) { // 비로그인

				view.printLogoutMenu(); // 비로그인 메뉴 출력
				
				int action = view.inputAction(); // 메뉴 중 선택(입력값을 받음)

				if (action == 0) { // 프로그램 종료

					break; // While문 멈춤

				} else if (action == 1) { // 제품 목록 출력
					
					// 구현 X
					
				} else if (action == 2) { // 제품 상세 정보 출력
					
					// 구현 X
					
				} else if (action == 3) { // 로그인
					
					MemberDTO memberDTO = new MemberDTO(); // 멤버DTO 객체 생성
					
					// 아이디 받기
					
					// 비밀번호 받기
					
					// 멤버DTO 객체에 아이디 저장
					
					// 멤버DTO 객체에 비밀번호 저장				
					
					loginINFO = memberDAO.selectOne(memberDTO); // 로그인 정보 저장 // 멤버 정보를 SELECTONE => loginINFO
					
				} else if (action == 4) { // 회원가입
					
					// 구현 X
					
				} else { // 재입력
					
					continue; // while문 처음으로 되돌아감 // 다시 선택
					
				}

			} else if (loginINFO.getGrade().equals("user")) { // 일반유저

				// 일반유저 메뉴 출력
				
				int action = view.inputAction();// 메뉴 중 선택(입력값을 받음)
				
				if(action == 1) { // 제품 전체 출력
					
					// 구현 X
					
				} else if(action == 2) { // 제품 상세 출력
					
					ProductDTO productDTO = new ProductDTO(); // 제품DTO 객체 생성
					
					ArrayList<ProductDTO> productArr = productDAO.selectAll(productDTO); // 제품 리스트를 DB에서 가져와 배열리스트에 저장
					
					while(true) {
						
						action = view.inputAction(); // 제품을 선택
						
						if(action > productArr.size()) { // 입력 값이 배열리스트 크기보다 크다면
							
							continue; // while문 처음으로 되돌아감 // 다시 선택
							
						}
						
						productDTO = productArr.get(action);// 선택한 제품의 정보를 제품DTO에 저장
						
						System.out.println(productDTO); // 임시로 출력문 사용 // 제품 상세 출력
						
						while(true) {
							
							// 메뉴 출력 1. 구매하기 2. 장바구니 추가 3. 돌아가기
							
							action = view.inputAction(); // 메뉴 중 선택(입력값을 받음)
							
							if(action == 1) { // 구매하기
								
								// 구현 X
								
							} else if(action == 2) { // 장바구니 추가
								
								CartDTO cartDTO = new CartDTO(); // 장바구니DTO 객체 생성
								
								boolean flag = false; // 장바구니에 선택 제품이 존재하는지 확인하기 위한 플래그
								
								action = view.inputAction(); // 수량 입력 받기
								
								if(action > productDTO.getCnt()) { // 입력 수량이 제품 수량 보다 크다면
									
									// 수량 상한 관련 문구 출력
									
									continue; // while문 처음으로 되돌아감 // 다시 선택
									
								}
								
								ArrayList<CartDTO> cartArr = cartDAO.selectAll(); // 장바구니 리스트를 DB에서 가져와 배열리스트에 저장
								
								for(CartDTO c : cartArr) { // 장바구니 리스트를 하나씩 확인 // 같은 제품이 이미 존재하는지 확인 위해
									
									if(c.getPid() == productDTO.getPID()) { // 장바구니의 제품 아이디가, 선택한 제품의 아이디와 같다면 // 같은 제품이 이미 장바구니에 존재한다면
										
										cartDTO = c; // 해당 제품을 장바구니 DTO에 저장
										
										flag = true; // 확인 플래그를 true로 변환
										
										break; // for문 탈출
										
									}
						
								}
								
								if(!flag) { // flag가 false면
									
									cartDTO.setCnt(action); // 장바구니DTO 객체에 수량 저장
									cartDTO.setMid(loginINFO.getMID()); // 장바구니DTO에 유저 아이디 저장
									cartDTO.setPid(productDTO.getPID()); // 장바구니DTO에 제품 아이디 저장
									
									cartDAO.insert(cartDTO); // 장바구니에 제품 정보 저장
									
								} else { // flag가 true면
									
									if(cartDTO.getCnt() + action > productDTO.getCnt()) { // 기존 수량 + 추가 수량이 제품 수량보다 많으면
										
										continue; // while문 처음으로 되돌아감 // 다시 선택
										
									}
									
									cartDTO.setCnt(cartDTO.getCnt() + action); // 기존 수량에 입력 수량을 추가
									
									cartDAO.update(cartDTO); // 장바구니 제품 정보 갱신
									
								}
								
							} else if(action == 3) { // 돌아가기
								
								break; // while문 탈출
								
							}
							
						}
						
					}
					
				} else if(action == 3) { // 장바구니 출력
					
					// 장바구니 내용 출력(이름 개수 금액)

					// 메뉴 출력

					// 메뉴 중 선택(입력값을 받음)

					// 입력값이 1이면 구매 기능으로 진입

						// 쿠폰을 적용할지 질문(입력값을 받음)

						// 입력값이 Y면

							// 쿠폰 리스트 출력 // 사용하지 않은 쿠폰만 출력

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
					
				} else if(action == 4) { // 구매 내역 확인
					
					// 해당 유저의 구매내역DB을 R하여 표시
					
				}

			} else if (loginINFO.getGrade().equals("admin")) { // 관리자

				// 구현 X
				
			} else { // 재입력
				
				continue; // While문 처음으로 되돌아감
				
			}

		}

	}

}