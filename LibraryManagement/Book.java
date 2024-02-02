package com.LibraryManagement;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private  boolean available;

    public Book(String title, String author){
        this.title = title;
        this.author = author;
        this.available = true;
    }
    public Book(String title, String author, boolean available){
        this.title = title;
        this.author = author;
        this.available = available;
    }


    public int getBookId(){
        return bookId;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public boolean isAvailable(){
        return available;
    }
    public void setBookId(int bookId){
        this.bookId = bookId;
    }

    public void markAsCheckedOut(){
        available = false;
    }


    @Override
    public String toString(){
        String status ;
        if(available){
            status = "Available";
        }
        else {
            status = "Checked Out";
        }
        return "Book ID: "+bookId+
                "\nTitle: "+title+
                "\nAuthor: "+author+
                "\nStatus: "+status;
    }
}
