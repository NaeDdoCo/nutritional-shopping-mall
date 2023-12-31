package ctrl;

import java.util.ArrayList;

import model.BuyInfoDAO;
import model.BuyInfoDTO;
import model.CartDAO;
import model.CartDTO;
import model.CouponDAO;
import model.CouponDTO;
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

				int action = view.inputNum(0, 4); // 메뉴 중 선택(입력값을 받음)

				if (action == 0) { // 프로그램 종료

					break; // While문 멈춤

				} else if (action == 1) { // 제품 목록 출력

					// 구현 X

				} else if (action == 2) { // 제품 상세 정보 출력

					// 구현 X

				} else if (action == 3) { // 로그인

					MemberDTO memberDTO = view.login(); // 유저 정보가 담긴 멤버DTO를 반환 받아 변수에 저장

					loginINFO = memberDAO.selectOne(memberDTO); // 로그인 정보 저장 // 멤버 정보를 SELECTONE => loginINFO

					view.loginResult(loginINFO);

				} else if (action == 4) { // 회원가입

					// 구현 X

				}

			} else if (loginINFO.getGrade().equals("USER")) { // 일반유저

				view.printLoginMenu(); // 일반유저 메뉴출력

				int action = view.inputNum(0, 8); // 메뉴 중 선택(입력값을 받음)

				if (action == 0) { // 프로그램 종료

					break; // While문 멈춤

				} else if (action == 1) { // 제품 전체 출력

					// 구현 X

				} else if (action == 2) { // 제품 상세 출력

					ProductDTO productDTO = new ProductDTO(); // 제품DTO 객체 생성

					ArrayList<ProductDTO> productArr = productDAO.selectAll(productDTO); // 제품 리스트를 DB에서 가져와 배열리스트에 저장

					view.printPlist(productArr); // 상품 리스트 출력

					while (true) {

						int productNum = view.inputNum(1, productArr.size()); // 제품을 선택

						productDTO = productArr.get(productNum - 1);// 선택한 제품의 정보를 제품DTO에 저장

						view.printPdetails(productDTO); // 제품 상세 출력 // 메뉴출력

						action = view.inputNum(1, 3); // 메뉴 중 선택(입력값을 받음)

						if (action == 1) { // 구매하기

							// 구현 X

						} else if (action == 2) { // 장바구니 추가

							boolean flag = false; // 장바구니에 선택 제품이 존재하는지 확인하기 위한 플래그

							int cnt = view.inputCnt(1, productDTO.getCnt()); // 수량 입력 받기

							CartDTO cartDTO = new CartDTO(); // 장바구니DTO 객체 생성
							cartDTO.setMid(loginINFO.getMID());

							ArrayList<CartDTO> cartArr = cartDAO.selectAll(cartDTO); // 장바구니 리스트를 DB에서 가져와 배열리스트에 저장

							for (CartDTO c : cartArr) { // 장바구니 리스트를 하나씩 확인 // 같은 제품이 이미 존재하는지 확인 위해

								if (c.getPid() == productDTO.getPID()) { // 장바구니의 제품 아이디가, 선택한 제품의 아이디와 같다면 // 제품이 이미
																			// 장바구니에 존재한다면

									cartDTO = c; // 해당 제품을 장바구니 DTO에 저장

									flag = true; // 확인 플래그를 true로 변환

									break; // for문 탈출

								}

							}

							if (!flag) { // flag가 false면

								cartDTO.setMid(loginINFO.getMID()); // 장바구니DTO에 유저 아이디 저장
								cartDTO.setPid(productDTO.getPID()); // 장바구니DTO에 제품 아이디 저장
								cartDTO.setCnt(cnt); // 장바구니DTO 객체에 수량 저장

								view.addResult(cartDAO.insert(cartDTO)); // 장바구니에 제품 정보 저장

							} else { // flag가 true면

								if (cartDTO.getCnt() + cnt > productDTO.getCnt()) { // 기존 수량 + 추가 수량이 제품 수량보다 많으면

									System.out.println("장바구니에 추가 가능한 수량을 넘었습니다."); // 임시 출력

									continue; // while문 처음으로 되돌아감 // 다시 선택

								}

								cartDTO.setPid(productDTO.getPID()); // 제품 아이디 저장
								cartDTO.setCnt(cartDTO.getCnt() + cnt); // 기존 수량에 입력 수량을 추가

								view.addResult(cartDAO.update(cartDTO)); // 장바구니 제품 정보 갱신

							}

							break;

						} else if (action == 3) {// 3. 제품 추천받기

							break; // while문 탈출

						}

					}

				} else if (action == 3) { // 장바구니 출력

					CartDTO cartDTO = new CartDTO(); // 장바구니DTO 객체 생성
					cartDTO.setMid(loginINFO.getMID());

					ArrayList<CartDTO> cartArr = cartDAO.selectAll(cartDTO); // / CartDTO 정보를 배열리스트에 저장

					ProductDTO productDTO; // 제품DTO 객체 생성

					ArrayList<ProductDTO> productArr = new ArrayList<ProductDTO>(); // ProductDTO 객체를 담을 배열리스트 선언

					for (CartDTO c : cartArr) { // 로그인 유저의 장바구니의 제품 정보만 저장
						
						productDTO = new ProductDTO();

						productDTO.setPID(c.getPid()); // 장바구니의 제품아이디를 제품DTO에 저장

						productDTO = productDAO.selectOne(productDTO);

						if (productDTO != null) { // 해당 제품이 존재한다면

							productDTO.setCnt(c.getCnt());

							productArr.add(productDTO); // 배열리스트에 객체를 저장

						}

					}

					view.printClist(productArr); // 장바구니 리스트 출력

					view.printCartMenu();// 장바구니 메뉴 출력

					action = view.inputNum(1, 2);// 메뉴 중 선택(입력값을 받음)

					if (action == 1) { // 구매하기

						String YN = view.inputYN(); // 쿠폰 적용 여부 입력 값 받음

						if (YN.equals("Y")) { // 입력값이 Y면

							while (true) {

								CouponDTO couponDTO = new CouponDTO(); // 쿠폰DTO의 객체를 생성
								couponDTO.setMID(loginINFO.getMID());

								ArrayList<CouponDTO> couponArr = couponDAO.selectAll(couponDTO); // 쿠폰 정보를 배열리스트에 저장

								for (int i = 0; i < couponArr.size(); i++) {

									if (couponArr.get(i).getUsed().equals("사용")) { // 쿠폰 상태가 사용이라면

										couponArr.remove(couponArr.get(i)); // 해당 정보를 삭제

									}

								}

								if (couponArr.size() == 0) {

									System.out.println("사용 가능한 쿠폰이 존재하지 않습니다.");

									break;

								}

								view.printCpList(couponArr); // 쿠폰 리스트 출력 // 사용하지 않은 쿠폰만 출력

								int couponNum = view.inputNum(1, couponArr.size()); // 쿠폰 선택(입력값을 받음)

								couponDTO = couponArr.get(couponNum - 1); // 선택된 쿠폰 정보를 객체에 저장

								for (int i = 0; i < productArr.size(); i++) { // 모든 제품을 한번씩 확인

									if (productArr.get(i).getCategory().equals(couponDTO.getCategory())) { // 제품의 카테고리와
																											// 쿠폰의 카테고리가
																											// 일치한다면

										double newSellingPrice = productArr.get(i).getSellingPrice()
												* (1 - 1.0 * couponDTO.getDiscount() / 100);

										productArr.get(i).setSellingPrice((int) newSellingPrice); // 기존 값을 할인 값으로 대체

									}

								}

								view.printClist(productArr); // 쿠폰을 적용한 장바구니 리스트 출력

								couponDAO.update(couponDTO); // 사용한 쿠폰 상태를 변경

								System.out.println("쿠폰을 더 적용하시겠습니까"); // 쿠폰 관련 문구 출력

								YN = view.inputYN(); // 쿠폰 적용 여부 입력 값 받음

								if (YN.equals("Y")) { // 입력값이 Y면

									continue;

								} else if (YN.equals("N")) { // 입력값이 N이면

									break;

								}

							}

						} else if (YN.equals("N")) {

			
						}

						for (int i = 0; i < cartArr.size(); i++) { // 제품의 남은 수량 조정

							for (int j = 0; j < productArr.size(); j++) {

								if (cartArr.get(i).getPid() == productArr.get(j).getPID()) {

									productDTO = new ProductDTO();
									productDTO.setCnt(cartArr.get(i).getCnt());
									productDTO.setPID((productArr.get(j).getPID()));

									productDAO.update(productDTO); // 제품의 남은 수량 조정
									
									break;

								}

							}

						}
						
						view.printClist(productArr);

						cartDTO.setMid(loginINFO.getMID()); // 해당 유저의 장바구니DB 비우기
						cartDAO.delete(cartDTO);

					} else if (action == 2) { // 돌아가기

						continue;

					}

				} else if (action == 4) { // 구매 내역 확인

					// 구현 X?

				} else if (action == 5) { // 제품 추천 받기

					// 구현 X

				} else if (action == 6) { // 개인 정보 수정

					// 구현 X

				} else if (action == 7) { // 로그아웃

					// 구현 X

				} else if (action == 8) { // 회원탈퇴

					// 구현 X

				}

			} else if (loginINFO.getGrade().equals("admin")) { // 관리자

				// 구현 X

			} else { // 재입력

				continue; // While문 처음으로 되돌아감

			}

		}

	}

}