/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salesInvoiceGenerator.model;

import java.util.ArrayList;

/**
 *
 * @author Mahmoud
 */
public class InvoiceSummary {
    
    private int id;
    private String date;
    private String customerName;
    private ArrayList<InvoiceDetails> invoiceDetailses;

    public InvoiceSummary() {
    }
    
    public InvoiceSummary(int id, String date, String customerName) {
        this.id = id;
        this.date = date;
        this.customerName = customerName;
    }

    public InvoiceSummary(int id, String date, String customerName, ArrayList<InvoiceDetails> invoiceDetailses) {
        this.id = id;
        this.date = date;
        this.customerName = customerName;
        this.invoiceDetailses = invoiceDetailses;
    }
    
    
    public double getInvoiceTotal(){
        double invoiceTotal = 0.0;
        if(invoiceDetailses != null){
            for(InvoiceDetails invoiceDetails : invoiceDetailses){
            invoiceTotal += invoiceDetails.getItemTotal();
        }
        }
        return invoiceTotal;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<InvoiceDetails> getInvoiceDetailses() {
        if(invoiceDetailses == null){
            invoiceDetailses = new ArrayList<>();
        }
        return invoiceDetailses;
    }

    public void setInvoiceDetailses(ArrayList<InvoiceDetails> invoiceDetailses) {
        this.invoiceDetailses = invoiceDetailses;
    }
    
    public String convertToInvoiceSummary(){
        return id+","+date+","+customerName+","+getInvoiceTotal();
    }
    
}
