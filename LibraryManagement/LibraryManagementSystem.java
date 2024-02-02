package com.LibraryManagement;

import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("\nLibrary Management System Menu");
            System.out.println("1. Add Books");
            System.out.println("2. Display Available Books");
            System.out.println("3. Check Out a Book");
            System.out.println("4. Display all Books & Status");
            System.out.println("5. Check In a Book");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    System.out.println("Enter the title of the book: ");
                    String title = scanner.nextLine();
                    System.out.println("Enter the author of the book: ");
                    String author = scanner.nextLine();
                    Book newBook = new Book(title, author);
                    library.addBook(newBook);
                    System.out.println("---------------------------------------");
                    System.out.println("Book added successfully. Book ID "+ newBook.getBookId());
                    System.out.println("---------------------------------------");
                    break;

                case 2:
                    System.out.println("\nAvailable Books: ");
                    System.out.println("------------------------");
                    library.displayAvailableBooks();
                    break;
                case 3:
                    System.out.println("Enter the Book ID to check out: ");
                    int bookIdToCheckOut = scanner.nextInt();
                    library.checkOutBook(bookIdToCheckOut);
                    break;
                case 4:
                    System.out.println("\nAll Books & Status: ");
                    System.out.println("-------------------------");
                    library.displayAllBooks();
                    break;

                case 5:
                    System.out.println("Enter the Book ID to Check In: ");
                    int bookIdToCheckIn = scanner.nextInt();
                    library.checkInBook(bookIdToCheckIn);
                    break;
                case 6:
                    System.out.println("-------------------------------------");
                    System.out.println("Exiting Library Management System.");
                    System.out.println("-------------------------------------");
                    library.closeDatabaseConnection();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
