package mini.project;

import java.util.ArrayList;
import java.util.Map;

public class Screen {
	private static String command;

	public static String main() {

		System.out.println("=============================");
		System.out.println("비트캠프 전자도서관에 오신것을 환영합니다.");
		System.out.println("1. 로그인");
		System.out.println("2. 종료");
		System.out.println("=============================");
		String command = Prompt.inputString("명령 : ");

		return command;
	}

	public static String menu(Object name) {
		System.out.println("=============================");
		System.out.printf("전자 도서관에 오신것을 환영합니다  %s님!\n", name);
		System.out.println("1. 도서목록");
		System.out.println("2. 대여목록");
		System.out.println("3. 로그아웃");
		System.out.println("=============================");
		String command = Prompt.inputString("명령 : ");

		return command;
	}

	public static String bookList(ArrayList<Book> bookList) {
		System.out.println("=========도서목록=========");
		for (int i = 0; i < bookList.size(); i++) {
			System.out.printf("%s. %s\n", i, bookList.get(i).getTitle());
		}
		System.out.println("========================");
		System.out.println("뒤로 가려면 exit를 입력해 주세요");
		String command = Prompt.inputString("명령 : ");

		return command;
	}


	public static String bookInfoAdmin(ArrayList<Book> bookList, String index) {
		//		Book bookInfo = bookList.get(Integer.valueOf(index));
		Map<String, String> bookInfo = LibraryDao.bookInfo(index);
		System.out.println("========================");
		System.out.printf("제목    : %s\n", bookInfo.get("title"));
		System.out.printf("저자    : %s\n", bookInfo.get("author"));
		System.out.printf("줄거리 : %s\n", bookInfo.get("plot"));
		System.out.println("========================");
		System.out.println("1. 도서수정");
		System.out.println("2. 도서삭제");
		System.out.println("뒤로 가려면 exit를 입력해 주세요");
		String command = Prompt.inputString("명령 : ");

		return command;
	}
	
	public static String bookInfoMember(ArrayList<Book> bookList, String index, Map<String, String> sessionInfo) {
		//		Book bookInfo = bookList.get(Integer.valueOf(index));
		Map<String, String> bookInfo = LibraryDao.bookInfo(index);
		System.out.println("========================");
		System.out.printf("제목    : %s\n", bookInfo.get("title"));
		System.out.printf("저자    : %s\n", bookInfo.get("author"));
		System.out.printf("줄거리 : %s\n", bookInfo.get("plot"));
		System.out.println("========================");
		if(LibraryDao.rentList(sessionInfo) == null || LibraryDao.rentList(sessionInfo).contains(bookInfo.get("id"))) {
			System.out.println("1. 대출");
		} else {
			System.out.println("[대출중]");
		}
		System.out.println("뒤로 가려면 exit를 입력해 주세요");
		String command = Prompt.inputString("명령 : ");

		return command;
	}


	//for (int i = 0; i < LibraryDao.memberList().size(); i++) {

	public static String bookRentalList(ArrayList<Book> bookList, Object name) {
		Member member = new Member();
		LibraryDao rent = new LibraryDao();


		System.out.printf("======%s님의 대여목록=======\n", name);

		if (member.getRentBookList() == null) {
			System.out.printf("%s님은 현재 책을 대여하고있지 않습니다.\n", name);
		} else {
			System.out.printf("%s\n", member.getRentBookList());
		}
		System.out.println("========================");
		while (true) {
			String command = Prompt.inputString("추가로 대여하시겠습니까?(y/N) : ");
			if (!command.equalsIgnoreCase("y")) {		
				System.out.println("대여를 종료합니다.");
				break;
			}
			System.out.println("========대여 가능목록========");
			for (int i = 0; i < bookList.size(); i++) {
				System.out.printf("%s, %s\n", i, bookList.get(i).getTitle());
			}
			command = Prompt.inputString("명령 : ");
			rent.rentBook(command, );
		}
		return command;
	}

	public static String adminMenu() {

		System.out.println("=============================");
		System.out.println("전자 도서관 관리 페이지");
		System.out.println("1. 사용자 관리");
		System.out.println("2. 도서 관리");
		System.out.println("3. 로그아웃");
		System.out.println("=============================");
		String command = Prompt.inputString("명령 : ");

		return command;
	}

	public static String adminMemberList() {

		ArrayList<Member> memberList = LibraryDao.memberList();

		for (int i = 0; i < memberList.size(); i++) {
			System.out.printf("%s. 이름 : %s | 아이디 : %s\n", i, memberList.get(i).getName(), memberList.get(i).getId());
		}
		System.out.println("add : 사용자 추가");

		String command = Prompt.inputString("명령 : ");

		return command;
	}

	public static String memberInfo(String memberIndex) {
		Member memberInfo = LibraryDao.memberInfo(memberIndex);
		System.out.println("========================");
		System.out.printf("이름       : %s\n", memberInfo.getName());
		System.out.printf("아이디    : %s\n", memberInfo.getId());
		System.out.printf("비밀번호 : %s\n", memberInfo.getPassword());
		System.out.println("========================");
		System.out.println("1. 정보수정");
		System.out.println("2. 사용자 삭제");
		System.out.println("뒤로 가려면 exit를 입력해 주세요");
		String command = Prompt.inputString("명령 : ");

		return command;
	}

	public static String editMember(String memberIndex) {
		String resultMessage;

		System.out.println("=================================");
		System.out.println("아무것도 입력하지 않으면 기존값을 그대로 사용합니다.");
		System.out.println("=================================");
		Member editMember = new Member();
		editMember.setName(Prompt.inputString("이름 : "));
		if (editMember.getName().isEmpty()) {
			editMember.setName(LibraryDao.memberInfo(memberIndex).getName());
		}
		
		editMember.setId(Prompt.inputString("아이디 : "));
		if (editMember.getId().isEmpty()) {
			editMember.setId(LibraryDao.memberInfo(memberIndex).getId());
		}
		editMember.setPassword(Prompt.inputString("비밀번호  : "));
		if (editMember.getPassword().isEmpty()) {
			editMember.setPassword(LibraryDao.memberInfo(memberIndex).getPassword());
		}
		try {
			LibraryDao.editMember(editMember, Integer.valueOf(memberIndex));
			resultMessage = "수정 되었습니다.";
		} catch (Exception e) {
			resultMessage = "수정에 실패 하였습니다.";
		}

		return resultMessage;
	}

	public static boolean addMember() {
		System.out.println("==========================");
		System.out.println("*표지가 있는 항목은 필수 입력 항목입니다.");
		System.out.println("==========================");
		Member addMember = new Member();
		addMember.setName(Prompt.inputString("이름 : "));

		while (addMember.getId() == null || addMember.getId().isEmpty()) {
			addMember.setId(Prompt.inputString("*아이디 : "));
		}

		while (addMember.getPassword() == null || addMember.getPassword().isEmpty()) {
			addMember.setId(Prompt.inputString("*비밀번호 : "));
		}

		try {
			LibraryDao.addMember(addMember);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean removeMember(String memberIndex) {
		while (true) {
			String answer = Prompt.inputString("" + "정말 삭제 하시겠습니까?\n" + "(1.예 / 2.아니요)\n" + "명령 : " + "");
			if (answer.equals("1")) {
				try {
					LibraryDao.removeMember(Integer.valueOf(memberIndex));
					return true;
				} catch (Exception e) {
					return false;
				}
			} else if (answer.equals("2")) {
				return false;
			} else {
				continue;
			}
		}
	}

	public static String adminBookList() {
		ArrayList<Book> bookList = LibraryDao.bookList();

		for (int i = 0; i < bookList.size(); i++) {
			System.out.printf("%s. 제목 : %s | 저자 : %s\n", i, bookList.get(i).getTitle(), bookList.get(i).getAuthor());
		}
		System.out.println("add : 도서 추가");

		String command = Prompt.inputString("명령 : ");

		return command;
	}

	public static String editBook(String bookIndex) {
		String resultMessage;

		System.out.println("=================================");
		System.out.println("아무것도 입력하지 않으면 기존값을 그대로 사용합니다.");
		System.out.println("=================================");
		Book editBook = new Book();
		editBook.setTitle(Prompt.inputString("제목 : "));
		if (editBook.getTitle().isEmpty()) {
			editBook.setTitle(LibraryDao.bookInfo(bookIndex).get("title"));
		}
		editBook.setAuthor(Prompt.inputString("저자 : "));
		if (editBook.getAuthor().isEmpty()) {
			editBook.setAuthor(LibraryDao.bookInfo(bookIndex).get("author"));
		}

		editBook.setPlot(Prompt.inputString("줄거리 : "));
		if (editBook.getPlot().isEmpty()) {
			editBook.setPlot(LibraryDao.bookInfo(bookIndex).get("plot"));
		}

		editBook.setContent(Prompt.inputString("내용 : "));
		if (editBook.getContent().isEmpty()) {
			editBook.setContent(LibraryDao.bookInfo(bookIndex).get("content"));
		}

		try {
			LibraryDao.editBook(editBook, Integer.valueOf(bookIndex));
			resultMessage = "수정 되었습니다.";
		} catch (Exception e) {
			resultMessage = "수정에 실패 하였습니다.";
		}

		return resultMessage;
	}

	public static boolean removeBook(String bookIndex) {
		while (true) {
			String answer = Prompt.inputString("" + "정말 삭제 하시겠습니까?\n" + "(1.예 / 2.아니요)\n" + "명령 : " + "");
			if (answer.equals("1")) {
				try {
					LibraryDao.removeBook(Integer.valueOf(bookIndex));
					return true;
				} catch (Exception e) {
					return false;
				}
			} else if (answer.equals("2")) {
				return false;
			} else {
				continue;
			}
		}
	}
	
	public static boolean addBook() {
		System.out.println("==========================");
		System.out.println("*표지가 있는 항목은 필수 입력 항목입니다.");
		System.out.println("==========================");
		Book addBook = new Book();
		
		while (addBook.getTitle() == null || addBook.getTitle().isEmpty()) {			
			addBook.setTitle(Prompt.inputString("*제목 : "));
		}
		while (addBook.getAuthor() == null || addBook.getAuthor().isEmpty()) {
			addBook.setAuthor(Prompt.inputString("*저자 : "));
		}
		addBook.setPlot(Prompt.inputString("줄거리 : "));
		
		addBook.setContent(Prompt.inputString("내용 : "));
		
		long beforeTime = System.currentTimeMillis();
		
		addBook.setId(String.valueOf(beforeTime * 100));

		try {
			LibraryDao.addBook(addBook);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String rentList(ArrayList<String> rentList) {
		System.out.println("=========대출목록=========");
		if (rentList != null) {			
			for (int i = 0; i < rentList.size(); i++) {
				Book bookInfo = LibraryDao.searchBook(rentList.get(i));
				System.out.printf("%s. 제목 : %s | 저자 : %s\n", i, bookInfo.getTitle(), bookInfo.getAuthor());
			}
		} else {
			System.out.println("대출중인 도서가 없습니다.");
		}
		System.out.println("========================");
		System.out.println("뒤로 가려면 exit를 입력해 주세요");
		String command = Prompt.inputString("명령 : ");

		return command;
		
	}

}
