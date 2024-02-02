package com.LibraryManagement;

import java.sql.*;

public class Library {
    private Connection connection;

    public Library()  {
        initializeDatabase();
    }

    private void initializeDatabase() {



        try{
            String url = "jdbc:mysql://localhost:3306/library_db";
            String username ="root";
            String password = "sky1234";

            // loading my jdbc
            Class.forName("com.mysql.cj.jdbc.Driver");
            //connecting it
            connection = DriverManager.getConnection(url,username,password);

//
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
//
    }

    public void addBook(Book book){
        try{

            // insert the book into the books table
            String insertQuery = "INSERT INTO books(title, author, available) VALUES(?, ?, ?)";

            try(PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.setBoolean(3, book.isAvailable());
                preparedStatement.executeUpdate();

                // retrieve the auto-generated book id
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if(generatedKeys.next()){
                    int bookId = generatedKeys.getInt(1);
                    book.setBookId(bookId);
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    // display available books

    public void displayAvailableBooks(){
        try{
            // get data from table
            String selectQuery = "SELECT * FROM books WHERE available = true";

            try(Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery)
            ){
                displayBooksFromResultSet(resultSet);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    // display all books along with status
    public void displayAllBooks(){
        try{
            // get data from table
//            String selectQuery = "SELECT * FROM books WHERE available = true";
            String selectQuery = "SELECT * FROM books";
            try(Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery)
            ){
                displayBooksFromResultSet(resultSet);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    public void checkOutBook(int bookId){
        try{
            // check out the book: available = false
            String updateQuery = "UPDATE books SET available = false WHERE id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)){
                preparedStatement.setInt(1,bookId);
                int rowsAffected = preparedStatement.executeUpdate();
                if(rowsAffected>0) {

                    System.out.println("BookID:"+bookId+" Checked out successfully.");
                    System.out.println("---------------------------------------------------");
                } else {
                    System.out.println("Book not found or already checked out.");
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void checkInBook(int bookId){
        try{
            // check in the book: available = true
            String updateQuery = "UPDATE books SET available = true WHERE id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)){
                preparedStatement.setInt(1,bookId);
                int rowsAffected = preparedStatement.executeUpdate();
                if(rowsAffected>0) {

                    System.out.println("BookID:"+bookId+" Checked In successfully.");
                    System.out.println("---------------------------------------------------");
                } else {
                    System.out.println("Book not found or already checked out.");
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void displayBooksFromResultSet(ResultSet resultSet) throws SQLException{
        while(resultSet.next()) {
            int bookId = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            boolean available = resultSet.getBoolean("available");
            Book book = new Book(title, author,available);
            book.setBookId(bookId);
//            if(available){
//                System.out.println(book.toString()+"\n");
//            }
            System.out.println(book.toString()+"\n");
            System.out.println("------------------------------------");
        }
    }


    public void closeDatabaseConnection(){
        try {
            if(connection != null){
                connection.close();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
