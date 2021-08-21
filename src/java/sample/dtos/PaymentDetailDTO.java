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
public class PaymentDetailDTO {
    private int paymentId;
    private BookDTO book;
    private int quantity;
    private float subTotal;

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

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

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public PaymentDetailDTO() {
    }

    public PaymentDetailDTO(int paymentId, BookDTO book, int quantity, float subTotal) {
        this.paymentId = paymentId;
        this.book = book;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }
    
}
