package mini.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.common.util.concurrent.ExecutionError;

public class LibraryDao {
	private static ArrayList<Member> memberList = new ArrayList<Member>();
	private static ArrayList<Book> bookList = new ArrayList<Book>();

	public static ArrayList<Member> memberList() {
		if (memberList.isEmpty()) {
			Member testMember = new Member();
			testMember.setName("홍길동");
			testMember.setId("abc1234");
			testMember.setPassword("123456");
			testMember.setRating("1");
			
			Member testMember2 = new Member();
			testMember2.setName("김철수");
			testMember2.setId("qwer1234");
			testMember2.setPassword("123456");
			testMember2.setRating("1");

			Member adminMember = new Member();
			adminMember.setName("홍길동");
			adminMember.setId("admin");
			adminMember.setPassword("admin");
			adminMember.setRating("2");

			memberList.add(testMember);
			memberList.add(testMember2);
			memberList.add(adminMember);
		}

		return memberList;
	}
	
	public static Member memberInfo(String memberIndex) {
		
		Member memberInfo = memberList.get(Integer.valueOf(memberIndex));

		return memberInfo;
	}
	
	public static boolean addMember(Member member) {
		try {			
			memberList.add(member);
			return true;
		} catch (ExecutionError e) {
			return false;
		}
	}
	
	public static boolean removeMember(int memberIndex) {
		try {
			memberList.remove(memberIndex);
			return true;
		} catch(ExecutionError e) {
			return false;
		}
	}

	public static boolean editMember(Member changeMemberInfo, int memberIndex) {
		Member memberInfoObject = new Member();
		
		memberInfoObject.setName(changeMemberInfo.getName());
		memberInfoObject.setId(changeMemberInfo.getId());
		memberInfoObject.setPassword(changeMemberInfo.getPassword());
		memberInfoObject.setRating(changeMemberInfo.getRating());
		
		memberList.set(memberIndex, memberInfoObject);
		
		return true;
	}

	public static Map<String, String> memberCheck(Member member) {

		ArrayList<Member> list = memberList();
		Map<String, String> returnObject = new HashMap<String, String>();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(member.getId()) && list.get(i).getPassword().equals(member.getPassword())) {
				returnObject.put("name", list.get(i).getName());
				returnObject.put("id", list.get(i).getId());
				returnObject.put("rating", list.get(i).getRating());

				return returnObject;
			}
		}

		return null;
	}

	public static ArrayList<Book> bookList() {

		if (bookList.isEmpty()) {
			Book book = new Book();
			book.setId("AK5DC578");
			book.setTitle("리버보이");
			book.setAuthor("팀 보울러");
			book.setPlot("다시는 돌아오지 않을 청춘의 한복판에 서 있는 자신이 보이는가? 그 시절 우리는 무슨 생각을 하고 누구와 함께 있고 어떤 일을 겪었나?");
			book.setContent(
					"잠깐 넥타이를 풀고 구두를 벗는다. 긴장을 풀고 눈을 감는다. 가슴이 심하게 두근거린다면 신경안정제를 삼켜도 좋다. 준비가 됐다면 이제 과거로 돌아가는 타임머신에 몸을 실을 차례다. ");

			Book book2 = new Book();
			book2.setId("AK5DC579");
			book2.setTitle("테스트1");
			book2.setAuthor("테스트 작가");
			book2.setPlot("테스트 줄거리");
			book2.setContent("테스트 내용");

			Book book3 = new Book();
			book3.setId("AK5DC579");
			book3.setTitle("테스트2");
			book3.setAuthor("테스트 작가2");
			book3.setPlot("테스트 줄거리2");
			book3.setContent("테스트 내용2");

			bookList.add(book);
			bookList.add(book2);
			bookList.add(book3);
		}

		return bookList;
	}

	public static Map<String, String> bookInfo(String bookIndex) {
		Book bookInfo = bookList.get(Integer.valueOf(bookIndex));
		Map<String, String> bookInfoObject = new HashMap<String, String>();
		bookInfoObject.put("id", bookInfo.getId());
		bookInfoObject.put("title", bookInfo.getTitle());
		bookInfoObject.put("author", bookInfo.getAuthor());
		bookInfoObject.put("plot", bookInfo.getPlot());
		bookInfoObject.put("content", bookInfo.getContent());

		return bookInfoObject;
	}

	public static boolean addBook(Book book) {
		try {			
			bookList.add(book);
			return true;
		} catch (ExecutionError e) {
			return false;
		}
	}

	public static boolean removeBook(int bookIndex) {
		try {
			bookList.remove(bookIndex);
			return true;
		} catch(ExecutionError e) {
			return false;
		}
	}

	public static boolean editBook(Book changeBookInfo, int bookIndex) {
		Book bookInfoObject = new Book();
		
		bookInfoObject.setTitle(changeBookInfo.getTitle());
		bookInfoObject.setAuthor(changeBookInfo.getAuthor());
		bookInfoObject.setPlot(changeBookInfo.getPlot());
		bookInfoObject.setContent(changeBookInfo.getContent());
		
		bookList.set(bookIndex, bookInfoObject);
		
		return true;
	}
	
	public static ArrayList<String> rentList(Map<String, String> sessionInfo) {
		for(int i = 0; i < LibraryDao.memberList.size(); i++) {
			if(sessionInfo.get("id") == LibraryDao.memberList().get(i).getId()) {
				
				ArrayList<String> rentList = LibraryDao.memberList().get(i).getRentBookList();
				return rentList;
			}
		}
		return null;
	}
	
	public static boolean rentBook(Map<String, String> sessionInfo, String bookId) {
		for(int i = 0; i < LibraryDao.memberList.size(); i++) {
			if(sessionInfo.get("id") == LibraryDao.memberList.get(i).getId()) {
				Member member = LibraryDao.memberList.get(i);
				ArrayList<String> tempRentBookList = member.getRentBookList();
				if (tempRentBookList == null) {
					tempRentBookList = new ArrayList<String>();
				}
				tempRentBookList.add(bookId);
				member.setRentBookList(tempRentBookList);
				
				LibraryDao.memberList.set(i, member);
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean returnBook(Map<String, String> sessionInfo, String bookId) {
		for(int i = 0; i < LibraryDao.memberList.size(); i++) {
			if(sessionInfo.get("id") == LibraryDao.memberList.get(i).getId()) {
				Member member = LibraryDao.memberList.get(i);
				ArrayList<String> tempRentBookList = member.getRentBookList();
				int rentBookIndex = tempRentBookList.indexOf(bookId);
				tempRentBookList.remove(rentBookIndex);
				member.setRentBookList(tempRentBookList);
				
				LibraryDao.memberList.set(i, member);
				return true;
			}
		}
		
		return false;
	}
	
	public static Book searchBook(String bookId) {
		for(int i = 0; i < bookList.size(); i++) {
			if(bookId == bookList.get(i).getId()) {
				return bookList.get(i);
			}
		}
		return null;
	}
}
