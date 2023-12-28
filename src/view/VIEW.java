package view;

import java.util.Scanner;

public class VIEW {
	private Scanner sc;
	public VIEW() {
		sc = new Scanner(System.in);
	}
	
	public void printLogoutMenu() {
		System.out.println("1. 제품 목록 출력");
		System.out.println("2. 제품 상세 정보 출력");
		System.out.println("3. 로그인");
		System.out.println("4. 회원가입");
		System.out.println("0. 프로그램 종료");
	}
	
	public int inputAction() {
		System.out.print("번호 입력 >> ");
		return sc.nextInt();
	}
}
