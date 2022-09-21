/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salesInvoiceGenerator.model;

/**
 *
 * @author Mahmoud
 */
public class InvoiceDetails {
    
    private String itemName;
    private double price;
    private int quantity;
    private InvoiceSummary invoiceSummary; 
    

    public InvoiceDetails() {
    }

    public InvoiceDetails(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public InvoiceDetails(String itemName, double price, int quantity, InvoiceSummary invoiceSummary) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.invoiceSummary = invoiceSummary;
    }
    
    
    public double getItemTotal(){
        return price * quantity;
    }
    
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public InvoiceSummary getInvoiceSummary() {
        return invoiceSummary;
    }

    public void setInvoiceSummary(InvoiceSummary invoiceSummary) {
        this.invoiceSummary = invoiceSummary;
    }
    
    public String convertToInvoiceDetails(){
        return invoiceSummary.getId()+","+itemName+","+price+","+quantity+","+getItemTotal();
    }
    
}
