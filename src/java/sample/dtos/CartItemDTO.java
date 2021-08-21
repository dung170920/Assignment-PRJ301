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
public class CartItemDTO {
    private BookDTO book;
    private int quantity;

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartItemDTO() {
    }

    public CartItemDTO(BookDTO book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }
    
}
