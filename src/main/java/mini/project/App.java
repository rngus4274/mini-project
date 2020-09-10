package mini.project;

import java.util.ArrayList;
import java.util.Map;

public class App {
	public static void main(String[] args) {
		Member member = new Member();

		String command;

		Map sessionInfo = null;
		main: while (true) {

			command = Screen.main();

			if (command.equals("1")) {
				member.setId(Prompt.inputString("아이디 : "));
				member.setPassword(Prompt.inputString("비밀번호 : "));

				Map<String, String> checkResult = LibraryDao.memberCheck(member);

				if (checkResult != null) {
					System.out.println("로그인 성공!");
					sessionInfo = checkResult;

					if (sessionInfo.get("rating") == "1") {
						// 로그인한 계정이 사용자 계정일 경우
						login_first: while (true) {
							if (sessionInfo == null) {
								continue main;
							}

							command = Screen.menu(sessionInfo.get("name"));

							if (command.equals("1")) {
								// 도서 목록
								book_list: while (true) {
									ArrayList<Book> bookList = LibraryDao.bookList();
									command = Screen.bookList(bookList);
									if (command.equals("exit")) {
										continue login_first;
									} else {
										try {
											command = Screen.bookInfo(bookList, command);
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
								book_rental: while (true) {
									ArrayList<Book> bookRental = LibraryDao.bookList();
									command = Screen.bookRentalList(bookRental, sessionInfo.get("name"));
									if (command.equals("exit")) {
										continue login_first;
									} 
								}


							} else if (command.equals("3")) {
								// 로그아웃
								sessionInfo = null;
								continue login_first;
							}
						}
					} else if (sessionInfo.get("rating") == "2") {
						// 로그인한 계정이 관리자 계정일 경우
						admin_menu: while (true) {
							command = Screen.adminMenu();
							if (command.equals("1")) {
								// 사용자 관리
								user_list: while (true) {
									String memberIndex = Screen.adminMemberList();
									if (memberIndex.equals("add")) {
										// 사용자 추가
										boolean result = Screen.addMember();
										if (result) {
											System.out.println("사용자를 추가 하였습니다.");
										} else {
											System.out.println("사용자 추가에 실패하였습니다.");
										}
									} else if (memberIndex.equals("exit")) {
										continue admin_menu;
									} else {
										try {
											command = Screen.memberInfo(memberIndex);
											if (command.equals("exit")) {
												continue admin_menu;
											} else if (command.equals("1")) {
												// 사용자 정보 수정
												String resultMessage = Screen.editMember(memberIndex);
												System.out.println(resultMessage);
												continue user_list;
											} else if (command.equals("2")) {
												// 사용자 삭제
												boolean result = Screen.removeMember(memberIndex);
												if (result == true) {
													System.out.println("사용자가 삭제 되었습니다.");
												} else {
													System.out.println("사용자 삭제가 취소 되었습니다.");
												}
											} else {
												System.out.println("존재하지 않는 명령입니다.");
												continue user_list;
											}

										} catch (Exception e) {
											System.out.println("해당하는 유저 정보가 존재하지 않습니다.");
											continue user_list;
										}
									}
								}
							} else if (command.equals("2")) {
								// 도서 관리(도서 추가, 수정, 삭제)
								book_list: while (true) {
									String bookIndex = Screen.adminBookList();
									ArrayList<Book> bookList = LibraryDao.bookList();
									if (bookIndex.equals("add")) {
										// 도서 추가
									} else if (bookIndex.equals("exit")) {
										continue admin_menu;
									} else {
										try {
											command = Screen.bookInfo(bookList, bookIndex);
											if (command.equals("exit")) {
												continue book_list;
											} else if (command.equals("1")) {
												// 도서 정보 수정
												String resultMessage = Screen.editBook(bookIndex);
												System.out.println(resultMessage);
												continue book_list;
											} else if (command.equals("2")) {
												// 도서 삭제
												boolean result = Screen.removeBook(bookIndex);
												if (result == true) {
													System.out.println("도서가 삭제 되었습니다.");
												} else {
													System.out.println("도서 삭제가 취소 되었습니다.");
												}
											} else {
												System.out.println("존재하지 않는 명령입니다.");
												continue book_list;
											}

										} catch (Exception e) {
											System.out.println("해당하는 도서 정보가 존재하지 않습니다.");
											continue book_list;
										}
									}
								}
							} else if (command.equals("3")) {
								sessionInfo = null;
								continue main;
							}
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