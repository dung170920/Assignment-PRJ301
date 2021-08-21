/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;
/**
 *
 * @author ASUS
 */
public class BookDTO {
      private String bookId = null;
      private String description = null;
      private float price = 0;
      private String bookName = null;
      private String author = null;
      private int year = 0;
      private int quantity = 0;
      private String img = null;
      private CategoryDTO categoryID = null;

    public BookDTO() {
    }

    public BookDTO(String bookId, String description, float price, String bookName, 
    String author, int year, int quantity, String img, CategoryDTO categoryID) {
        this.bookId = bookId;
        this.description = description;
        this.price = price;
        this.bookName = bookName;
        this.author = author;
        this.year = year;
        this.quantity = quantity;
        this.img = img;
        this.categoryID = categoryID;       
    }
    
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public CategoryDTO getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(CategoryDTO categoryID) {
        this.categoryID = categoryID;
    }
  
}
