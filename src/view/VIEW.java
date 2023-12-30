package view;

import java.util.Scanner;

// 택종 작업 영역

public class VIEW {
	private Scanner sc;

	public VIEW() {
		sc = new Scanner(System.in);
	}
	//로그아웃화면출력 메서드
	public void printLogoutMenu() {
		System.out.println("1. 제품 목록 출력");
		System.out.println("2. 제품 상세 정보 출력");
		System.out.println("3. 로그인");
		System.out.println("4. 회원가입");
		System.out.println("0. 프로그램 종료");
	}
	//사용자에게 번호 입력받는 메서드
	public int inputNum(int min, int max) {
		System.out.print("(" + min + " ~ " + max + ") 번 중에 입력하세요!>> ");
		while (true) {
			//유효성검사
			try {
				int chooseNum = sc.nextInt();
				if (chooseNum >= min && chooseNum <= max) {
					return chooseNum;
				} else {
					System.out.println(min + "과(와)" + max + "사이의 값만 입력 가능합니다.");
				}
			} catch (Exception e) {
				System.out.println(min + "과(와) " + max + " 사이의 '정수'만 입력 가능합니다.");
				sc.nextLine();
			}
		}
	}
	// 로그인 과정
	public MemberDTO login() {
		MemberDTO mDTO = new MemberDTO();
		System.out.println("ID입력>> ");
		mDTO.setMid(sc.next());
		System.out.println("PW입력>> ");
		mDTO.setMpw(sc.next());
		return mDTO;
	}
	//로그인 결과 출력
	public void loginResult(MemberDTO data) {
		if (data != null) {
			System.out.println("로그인 성공 !");
			System.out.println(data.getName() + "님, 안녕하세요!");
		} else {
			System.out.println("로그인에 실패하였습니다.. 다시 시도해주세요.");
		}
	}
	//로그인후메뉴출력
	public void printLoginMenu() {
		System.out.println("1. 제품 목록 출력");
		System.out.println("2. 제품 상세정보 출력");
		System.out.println("3. 제품 추천받기");
		System.out.println("4. 장바구니");
		System.out.println("5. 개인 정보 수정");
		System.out.println("6. 로그아웃");
		System.out.println("7. 회원탈퇴");
	}
	//제품 목록 출력
	public void printPlist(ArrayList<Product> datas) {
		for (ProductDTO data : datas) {
			System.out.println("제품명: " + data.getPname() + ", 가격: " + data.getSellingPrice + "재고: " + data.getCnt);
		}
	}
	//제품 상세정보 출력
	public void printPdetails(Product data) {
		System.out.println(data.getPname + " 의 상세정보입니다.");
		System.out.println(data.genPname + " 의 카테고리: " + data.Pcategory + ", 원료: " + data.Pingredient + " , 복용방법: "
				+ data.Pusage + ", 유통기한: " + data.Pexp);
		System.out.println("1. 구매하기");
		System.out.println("2. 장바구니 추가하기");
		System.out.println("3. 돌아가기");
	}
	//사용자에게 수량 입력받는 메서드
	public int inputCnt(int min, int max) {
		System.out.print(min + "개 부터 " + max + "개 까지 입력하세요!>> ");
		while (true) {
			try {
				int chooseNum = sc.nextInt();
				if (chooseNum >= min && chooseNum <= max) {
					return chooseNum;
				} else {
					System.out.println(min + "과(와)" + max + "사이의 값만 입력 가능합니다.");
				}
			} catch (Exception e) {
				System.out.println(min + "과(와) " + max + " 사이의 '정수'만 입력 가능합니다.");
				sc.nextLine();
			}
		}

	}
	//추가 성공/실패 여부
	public void addResult(boolean result) {
		if (result == true) {
			System.out.println("추가 성공 !");
		} else {
			System.out.println("추가 실패..");
		}
	}
	//장바구니에 담긴 제품 & 최종금액 출력
	public void printClist(ArrayList<Product> datas) {
		for (ProductDTO data : datas) {
			System.out.println("제품명: " + data.getPname() + ", 가격: " + data.getSellingPrice + "재고: " + data.getCnt);
		}
		int total = 0;
		for (int i = 0; i < datas.size(); i++) {
			total += datas.get(i).getSellingPrice();
		}
		System.out.println("최종 금액은 " + total + "입니다 !");
	}
	//장바구니 메뉴 출력
	public void printCartMenu() {
		System.out.println("1. 구매하기");
		System.out.println("2. 돌아가기");
	}
	//사용자에게 쿠폰 적용할것인지 Y/N으로 입력받기
	public String inputYN() {
		String inputYN;
		while (true) {
			System.out.println("Y/N>> ");
			inputYN = sc.next();
			if (inputYN.equals("Y") || inputYN.equals("N")) {
				// 이라면 정상입력 -> 리턴 inputYN
				break;
			} else {
				System.out.println("다시 입력해주세요 !");
			}
		}
		return inputYN;
	}
	//쿠폰 목록 출력
	public void printCpList(ArrayList<Coupon> datas) {
		System.out.println("=====쿠폰 목록=====");
		for (CouponDTO data : datas) {
			System.out.println("쿠폰명: " + data.getCpname() + ", 할인율: " + data.getCpDiscount);
		}
		System.out.println("어떤 쿠폰을 사용하시겠습니까?");

	}
}
