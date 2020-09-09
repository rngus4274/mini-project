package mini.project;

import java.util.Arrays;

public class BookRentalList {
	static final int DEFAULT_CAPACITY = 3;
	Book[] list;
	int size = 0;

	public BookRentalList() {
		list = new Book[DEFAULT_CAPACITY];
	}

	public BookRentalList(int initialCapacity) {
		if (initialCapacity <= DEFAULT_CAPACITY) {
			list = new Book[DEFAULT_CAPACITY];
		} else {
			list = new Book[initialCapacity];
		}
	}

	public void add(Book book) {
		if (size == list.length) {
			int oldCapacity = list.length;
			int newCapacity = oldCapacity + (oldCapacity >> 1);

			list = Arrays.copyOf(list, newCapacity);
			System.out.printf("==> 새 배열을 %d 개 생성하였음!\n", newCapacity);
		}
		list[size++] = book;
	}

	public Book[] toArray() {

		return Arrays.copyOf(list, size);
	}
}