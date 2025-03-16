import java.util.ArrayList;
import java.util.Scanner;

// Book Class
class Book {
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        if (!isBorrowed) {
            isBorrowed = true;
            System.out.println("[SUCCESS] " + title + " has been borrowed.");
        } else {
            System.out.println("[ERROR] " + title + " is already borrowed.");
        }
    }

    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
            System.out.println("[SUCCESS] " + title + " has been returned.");
        } else {
            System.out.println("[ERROR] " + title + " was not borrowed.");
        }
    }

    public void displayBook() {
        String status = isBorrowed ? "Borrowed" : "Available";
        System.out.println("Book: " + title + " | Author: " + author + " | Status: " + status);
    }
}

// Library Class
class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
        System.out.println("[SUCCESS] Book added: " + title);
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("[INFO] No books in the library.");
        } else {
            System.out.println("\n--- Library Books ---");
            for (Book book : books) {
                book.displayBook();
            }
        }
    }

    public void borrowBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.borrowBook();
                return;
            }
        }
        System.out.println("[ERROR] Book not found!");
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.returnBook();
                return;
            }
        }
        System.out.println("[ERROR] Book not found!");
    }
}

// Main Class
public class LM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n--- LIBRARY MANAGEMENT SYSTEM ---");
            System.out.println("1. Add a Book");
            System.out.println("2. Display Books");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("[ERROR] Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input
                continue;
            }

            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    break;
                case 2:
                    library.displayBooks();
                    break;
                case 3:
                    System.out.print("Enter book title to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    library.borrowBook(borrowTitle);
                    break;
                case 4:
                    System.out.print("Enter book title to return: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnTitle);
                    break;
                case 5:
                    System.out.println("[INFO] Exiting... Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("[ERROR] Invalid choice! Please try again.");
            }
        }
    }
}
