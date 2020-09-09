package mini.project;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LibraryDao {
	private static ArrayList<Member> memberList = new ArrayList<Member>();
	private static ArrayList<Book> bookList = new ArrayList<Book>();
	
	public static ArrayList<Member> memberList() {
		if (memberList.isEmpty()) {	
			Member testMember = new Member();
			testMember.setName("홍길동");
			testMember.setId("abc1234");
			testMember.setPassword("123456");
			
			memberList.add(testMember);
		}
		
		return memberList;
	}
	
	public static Map<String, String> memberCheck(Member member) {
		
		ArrayList<Member> list = memberList();
		Map<String, String> returnObject = new HashMap<String, String>();
		
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().equals(member.getId()) && list.get(i).getPassword().equals(member.getPassword())) {
				returnObject.put("name", list.get(i).getName());
				returnObject.put("id", list.get(i).getId());
				
				return returnObject;
			}
		}
		
		return null;
	}
	
	public static ArrayList<Book> bookList(){
		
		if (bookList.isEmpty()) {			
			Book book = new Book();
			book.setId("AK5DC578");
			book.setTitle("리버보이");
			book.setAuthor("팀 보울러");
			book.setPlot("다시는 돌아오지 않을 청춘의 한복판에 서 있는 자신이 보이는가? 그 시절 우리는 무슨 생각을 하고 누구와 함께 있고 어떤 일을 겪었나?");
			book.setContent("잠깐 넥타이를 풀고 구두를 벗는다. 긴장을 풀고 눈을 감는다. 가슴이 심하게 두근거린다면 신경안정제를 삼켜도 좋다. 준비가 됐다면 이제 과거로 돌아가는 타임머신에 몸을 실을 차례다. ");
			
			Book book2 = new Book();
			book2.setId("AK5DC579");
			book2.setTitle("테스트1");
			book2.setAuthor("테스트 작가");
			book2.setPlot("테스트 줄거리");
			book2.setContent("테스트 내용");
			
			Book book3 = new Book();
			book3.setId("AK5DC579");
			book3.setTitle("테스트1");
			book3.setAuthor("테스트 작가");
			book3.setPlot("테스트 줄거리");
			book3.setContent("테스트 내용");

			bookList.add(book);
			bookList.add(book2);
			bookList.add(book3);
		}
		
		return bookList;
	}
}
