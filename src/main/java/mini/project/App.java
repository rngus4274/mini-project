
package mini.project;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Member member = new Member();

		String command;
		Scanner sc = new Scanner(System.in);
		Map sessionInfo = null;
		main: while (true) {
			System.out.println("=============================");
			System.out.println("비트캠프 전자도서관에 오신것을 환영합니다.");
			System.out.println("1. 로그인");
			System.out.println("2. 종료");
			System.out.println("=============================");
			System.out.print("명령 : ");
			command = sc.next();

			if (command.equals("1")) {
				System.out.print("아이디 : ");
				member.setId(sc.next());
				System.out.print("비밀번호 : ");
				member.setPassword(sc.next());

				Map<String, String> checkResult = LibraryDao.memberCheck(member);

				if (checkResult != null) {
					System.out.println("로그인 성공!");
					sessionInfo = checkResult;
					
					login_first: while (true) {
						if (sessionInfo == null) {
							continue main;
						}

						System.out.println("=============================");
						System.out.printf("전자 도서관에 오신것을 환영합니다  %s님!\n", sessionInfo.get("name"));
						System.out.println("1. 도서목록");
						System.out.println("2. 대출목록");
						System.out.println("3. 로그아웃");
						System.out.println("=============================");
						System.out.print("명령 : ");
						command = sc.next();
						if (command.equals("1")) {
							book_list: while (true) {
								System.out.println("=========도서목록=========");
								ArrayList<Book> bookList = LibraryDao.bookList();
								for (int i = 0; i < bookList.size(); i++) {
									System.out.printf("%s. %s\n", i, bookList.get(i).getTitle());
								}
								System.out.println("========================");
								System.out.println("뒤로 가려면 exit를 입력해 주세요");
								System.out.print("명령 : ");
								command = sc.next();
								if (command.equals("exit")) {
									continue login_first;
								} else {
									try {
										Book bookInfo = bookList.get(Integer.valueOf(command));
										System.out.println("========================");
										System.out.printf("제목    : %s\n", bookInfo.getTitle());
										System.out.printf("저자    : %s\n", bookInfo.getAuthor());
										System.out.printf("줄거리 : %s\n", bookInfo.getPlot());
										System.out.println("========================");
										System.out.println("뒤로 가려면 exit를 입력해 주세요");
										System.out.print("명령 : ");
										command = sc.next();
										if (command.equals("exit")) {
											continue book_list;
										} else {
											System.out.println("존재하지 않는 명령입니다.");
										}

									} catch (Exception e) {
										System.out.println("해당하는 책 정보가 존재하지 않습니다.");
										continue book_list;
									}
								}
							}

						} else if (command.equals("2")) {
							System.out.println("기능 구현중");

						} else if (command.equals("3")) {
							sessionInfo = null;
							continue login_first;
						}
					}
				} else {
					System.out.println("해당 계정 정보가 존재하지 않습니다.");
				}

			} else if (command.equals("2")) {
				System.out.println("종료 되었습니다..");
				break;
			} else {
				continue;
			}
		}
	}
}
