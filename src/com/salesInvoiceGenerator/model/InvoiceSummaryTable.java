/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salesInvoiceGenerator.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mahmoud
 */
public class InvoiceSummaryTable extends AbstractTableModel{
    
    private ArrayList<InvoiceSummary> invoiceSummary;
    private String[] columnTitles = {"No.", "Date", "Customer Name", "Total"};

    public InvoiceSummaryTable(ArrayList<InvoiceSummary> invoiceSummary) {
        this.invoiceSummary = invoiceSummary;
    }

    @Override
    public int getRowCount() {
        return invoiceSummary.size();
    }

    @Override
    public int getColumnCount() {
        return columnTitles.length;
    }
    
    @Override
    public String getColumnName(int column){
        return columnTitles[column];
    } 

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceSummary invoice = invoiceSummary.get(rowIndex);
        switch (columnIndex) {
            case 0:
                
                return invoice.getId();
            case 1:
                
                return invoice.getDate();
            case 2:
                
                return invoice.getCustomerName();
            case 3:
                
                return invoice.getInvoiceTotal();
            default:
                return "";
        }
    }
    
}
