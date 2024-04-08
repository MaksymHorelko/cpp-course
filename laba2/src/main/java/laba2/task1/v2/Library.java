package laba2.task1.v2;

public class Library {
	public static void main(String[] args) {

		// Створення авторів
		Author author1 = new Author("William", "Shakespeare", 1L);
		Author author2 = new Author("Jane", " Austen", 2L);

		// Створення книг
		Book book1 = new Book("Hamlet", author1);
		Book book2 = new Book("Pride and Prejudice", author2);

		// Створення читачів
		Reader reader1 = new Reader("Alex", "Borman", 1L);
		Reader reader2 = new Reader("Stewen", "Dobrovich", 2L);

		// Створення системи бібліотеки
		LibraryService libraryService = new LibraryService();

		// Додавання книг до книжкової шафи
		libraryService.addBook(book1);
		libraryService.addBook(book2);

		// Додавання читачів до системи
		libraryService.addReader(reader1);
		libraryService.addReader(reader2);

		libraryService.addRentals(new Rental(reader1, book1, 50));

		// Збереження стану системи у файл
		libraryService.saveState("library_system2.ser");

		// Відновлення системи з файлу
		LibraryService restoredSystem = LibraryService.restoreState("library_system2.ser");
		System.out.println("Restored Library System: " + restoredSystem);
	}
}
