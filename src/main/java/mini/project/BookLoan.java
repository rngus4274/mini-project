package mini.project;

import java.util.Scanner;

public class BookLoan {
	public static void main(String[] args) {

		Scanner keyboardscan = new Scanner(System.in);

		System.out.println("[대출 시스템]");

		System.out.print("대출하실 책의 고유번호를 입력해주세요 : ");
		String bookNo = keyboardscan.nextLine();

		System.out.println("입력하신 책은 대출이 가능합니다. ");

		System.out.println("대출 하시겠습니까?(y/N) ");
		String str = keyboardscan.nextLine();

		if(!str.equalsIgnoreCase("y")) {
			System.out.println("대출을 종료합니다.");
		} else {
			System.out.println("사용자 정보를 입력하여 주십시오.");
			System.out.print("성함 :");
			String name = keyboardscan.nextLine();

			System.out.print("주소 :");
			String address = keyboardscan.nextLine();

			System.out.print("전화번호 :");
			String tel = keyboardscan.nextLine();
			System.out.println("대출을 완료하였습니다.");
		}
		keyboardscan.close();
	}
}
