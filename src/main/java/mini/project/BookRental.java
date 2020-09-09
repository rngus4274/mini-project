package mini.project;

import java.util.ArrayList;
import java.util.Scanner;

public class BookRental {


	public static void rental () {
		Scanner keyboardscan = new Scanner(System.in);
		ArrayList<Book> bookList = new ArrayList<>();
		Book book = new Book();
		int LENGTH = 100;

		System.out.print("대여하실 책의 이름을 적어주십시오. ");
		String bookName = keyboardscan.nextLine();

		int size = 1;

		for (int i = 0; i < LENGTH; i++) {
			if (bookName != null) {
				System.out.println("입력하신 책은 대여가 가능합니다. ");
				System.out.println("대여 하시겠습니까?(y/N) ");
				String str = keyboardscan.nextLine();
				if (!str.equalsIgnoreCase("y")) {		
					System.out.println("대여를 종료합니다.");
					break;
				}
				System.out.println("사용자 정보를 입력하여 주십시오.");
				System.out.print("성함 :");
				String name = keyboardscan.nextLine();

				System.out.print("주소 :");
				String address = keyboardscan.nextLine();

				System.out.print("전화번호 :");
				String tel = keyboardscan.nextLine();
				System.out.println("대여를 완료하였습니다.");
				System.out.printf("대여 기한은 %d까지입니다.\n", );
				break;
			}
		}
	}

}

