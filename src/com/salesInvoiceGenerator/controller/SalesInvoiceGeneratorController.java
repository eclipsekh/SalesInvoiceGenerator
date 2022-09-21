/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salesInvoiceGenerator.controller;

import com.salesInvoiceGenerator.model.InvoiceDetails;
import com.salesInvoiceGenerator.model.InvoiceDetailsTable;
import com.salesInvoiceGenerator.model.InvoiceSummary;
import com.salesInvoiceGenerator.model.InvoiceSummaryTable;
import com.salesInvoiceGenerator.view.InvoiceDetailsDialog;
import com.salesInvoiceGenerator.view.InvoiceSummaryDialog;
import com.salesInvoiceGenerator.view.SalesInvoiceGeneratorFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Mahmoud
 */
public class SalesInvoiceGeneratorController implements ActionListener, ListSelectionListener{
    
    
    private SalesInvoiceGeneratorFrame salesInvoiceGeneratorFrame;
    private InvoiceSummaryDialog invoiceSummaryDialog;
    private InvoiceDetailsDialog invoiceDetailsDialog;

    public SalesInvoiceGeneratorController(SalesInvoiceGeneratorFrame salesInvoiceGeneratorFrame) {
        this.salesInvoiceGeneratorFrame = salesInvoiceGeneratorFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actioncommands = e.getActionCommand();
        switch(actioncommands){
            case "Load":
                loadFile();
                break;
            case "SaveFile":
                saveFile();
                break;
            case "Create":
                createInvoice();
                break;   
            case "Delete":
                deleteInvoice();
                break;
            case "SaveItem":
                saveItem();
                break;
            case "Cancel":
                cancelItem();
                break;
            case "createDialogButton":
                createNewSummaryInvoice();
                break;
            case "discardDialogButton":
                discardCreateNewInvoiceSummary();
                break;
            case "createDetailsDialogButton":
                createNewDetailsInvoice();
                break;
            case "discardDetailsDialogButton":
                discardCreateNewDetailsInvoice();
                break;
        }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int getRowFromTable = salesInvoiceGeneratorFrame.getSummaryTable().getSelectedRow();
        
        if(getRowFromTable >= 0){
            InvoiceSummary selectedSummary = salesInvoiceGeneratorFrame.getInvoiceSummarys().get(getRowFromTable);
            salesInvoiceGeneratorFrame.getInvoiceIdLabel().setText(""+selectedSummary.getId());
            salesInvoiceGeneratorFrame.getInvoiceDateLabel().setText(""+selectedSummary.getDate());
            salesInvoiceGeneratorFrame.getCustomerNameLabel().setText(""+selectedSummary.getCustomerName());
            salesInvoiceGeneratorFrame.getInvoiceTotalLabel().setText(""+selectedSummary.getInvoiceTotal());
            InvoiceDetailsTable invoiceDetailsTable = new InvoiceDetailsTable(selectedSummary.getInvoiceDetailses());
            salesInvoiceGeneratorFrame.getDetailsTable().setModel(invoiceDetailsTable);
            invoiceDetailsTable.fireTableDataChanged();
            
        }
        
        
    }

    private void loadFile() {
        JFileChooser jFileChooser = new JFileChooser();
        
        try {
            int userAction = jFileChooser.showOpenDialog(salesInvoiceGeneratorFrame);
            if(userAction == JFileChooser.APPROVE_OPTION){
                File invoiceSummary = jFileChooser.getSelectedFile();
                List<String> invoiceSummaryRows = Files.lines(Paths.get(invoiceSummary.getAbsolutePath())).collect(Collectors.toList());
                ArrayList<InvoiceSummary> invoiceSummaryList = new ArrayList<>();
                for(String invoiceSummaryRow : invoiceSummaryRows){
                    String[] summaryRow = invoiceSummaryRow.split(",");
                    int id = Integer.parseInt(summaryRow[0]);
                    String date = summaryRow[1];
                    String name = summaryRow[2];
                    InvoiceSummary invoiceSummaryObject = new InvoiceSummary(id, date, name);
                    invoiceSummaryList.add(invoiceSummaryObject);
                }
                userAction = jFileChooser.showOpenDialog(salesInvoiceGeneratorFrame);
                if(userAction == JFileChooser.APPROVE_OPTION){
                    File invoiceDetails = jFileChooser.getSelectedFile();
                    
                    List<String> invoiceDetailsRows = Files.lines(Paths.get(invoiceDetails.getAbsolutePath())).collect(Collectors.toList());
                    for(String invoiceDetailsRow : invoiceDetailsRows){
                        String[] detailsRow = invoiceDetailsRow.split(",");
                        int id = Integer.parseInt(detailsRow[0]);
                        String itemName = detailsRow[1];
                        double itemPrice = Double.parseDouble(detailsRow[2]);
                        int qty = Integer.parseInt(detailsRow[3]);
                        InvoiceSummary invoice = null;
                        for(InvoiceSummary invoiceSummary1 : invoiceSummaryList){
                            if(invoiceSummary1.getId() == id){
                                invoice = invoiceSummary1;
                                break;
                            }
                        }
                        InvoiceDetails invoiceDetailsObject = new InvoiceDetails(itemName, itemPrice, qty, invoice);
                        invoice.getInvoiceDetailses().add(invoiceDetailsObject);
                    }
                }
                salesInvoiceGeneratorFrame.setInvoiceSummarys(invoiceSummaryList);
                InvoiceSummaryTable invoiceSummaryTable = new InvoiceSummaryTable(invoiceSummaryList);
                salesInvoiceGeneratorFrame.setInvoiceSummaryTable(invoiceSummaryTable);
                salesInvoiceGeneratorFrame.getSummaryTable().setModel(invoiceSummaryTable);
                salesInvoiceGeneratorFrame.getInvoiceSummaryTable().fireTableDataChanged();
            }
            
        
        } catch (Exception e) {
        }
        
    }

    private void saveFile() {
        ArrayList<InvoiceSummary> invoiceSummarys = salesInvoiceGeneratorFrame.getInvoiceSummarys();
        String invoiceSummary = "";
        String invoiceDetails = "";
        for(InvoiceSummary invoiceSummary1 : invoiceSummarys){
            invoiceSummary += invoiceSummary1.convertToInvoiceSummary();
            invoiceSummary += "\n";
            
            for(InvoiceDetails invoiceDetails1 : invoiceSummary1.getInvoiceDetailses()){
                invoiceDetails += invoiceDetails1.convertToInvoiceDetails();
                invoiceDetails += "\n";
            }
        }
        
        try {
            JFileChooser jFileChooser = new JFileChooser();
            int userAction = jFileChooser.showSaveDialog(salesInvoiceGeneratorFrame);
            if(userAction == JFileChooser.APPROVE_OPTION){
                File summaryFile = jFileChooser.getSelectedFile();
                FileWriter summaryWriter = new FileWriter(summaryFile);
                summaryWriter.write(invoiceSummary);
                summaryWriter.flush();
                summaryWriter.close();
                userAction = jFileChooser.showSaveDialog(salesInvoiceGeneratorFrame);
                if(userAction == JFileChooser.APPROVE_OPTION){
                    File detailsFile = jFileChooser.getSelectedFile();
                    FileWriter detailsWriter = new FileWriter(detailsFile);
                    detailsWriter.write(invoiceDetails);
                    detailsWriter.flush();
                    detailsWriter.close();
                    
                }
            }
        } catch (Exception e) {
        }
    }

    private void createInvoice() {
        invoiceSummaryDialog = new InvoiceSummaryDialog(salesInvoiceGeneratorFrame);
        invoiceSummaryDialog.setVisible(true);
        
    }

    private void deleteInvoice() {
        int selectedRow = salesInvoiceGeneratorFrame.getSummaryTable().getSelectedRow();
        if(selectedRow >= 0){
            salesInvoiceGeneratorFrame.getInvoiceSummarys().remove(selectedRow);
            salesInvoiceGeneratorFrame.getInvoiceSummaryTable().fireTableDataChanged();
        }
        
    }

    private void saveItem() {
        invoiceDetailsDialog = new InvoiceDetailsDialog(salesInvoiceGeneratorFrame);
        invoiceDetailsDialog.setVisible(true);
    }

    private void cancelItem() {
        int selectedSummaryRow = salesInvoiceGeneratorFrame.getSummaryTable().getSelectedRow();
        int selectedDetailsRow = salesInvoiceGeneratorFrame.getDetailsTable().getSelectedRow();
        
        if(selectedSummaryRow >= 0 && selectedDetailsRow >= 0){
            InvoiceSummary invoiceSummary = salesInvoiceGeneratorFrame.getInvoiceSummarys().get(selectedSummaryRow);
            invoiceSummary.getInvoiceDetailses().remove(selectedDetailsRow);
            InvoiceDetailsTable invoiceDetailsTable = new InvoiceDetailsTable(invoiceSummary.getInvoiceDetailses());
            salesInvoiceGeneratorFrame.getDetailsTable().setModel(invoiceDetailsTable);
            invoiceDetailsTable.fireTableDataChanged();
        }
    }

    private void createNewSummaryInvoice() {
        String name = invoiceSummaryDialog.getCustomerNameField().getText();
        String date = invoiceSummaryDialog.getDatField().getText();
        int newID = salesInvoiceGeneratorFrame.getInvoiceSummarys().size() + 1;
        InvoiceSummary newInvoiceSummary = new InvoiceSummary(newID, date, name);
        salesInvoiceGeneratorFrame.getInvoiceSummarys().add(newInvoiceSummary);
        salesInvoiceGeneratorFrame.getInvoiceSummaryTable().fireTableDataChanged();
        invoiceSummaryDialog.setVisible(false);
    }

    private void discardCreateNewInvoiceSummary() {
        invoiceSummaryDialog.setVisible(false);
        //invoiceSummaryDialog.dispose();
    }

    private void createNewDetailsInvoice() {
        String itemName = invoiceDetailsDialog.getItemNameField().getText();
        double price = Double.parseDouble(invoiceDetailsDialog.getItemPriceField().getText());
        int qty = Integer.parseInt(invoiceDetailsDialog.getQtyField().getText());
        int selectedInvoiceSummary = salesInvoiceGeneratorFrame.getSummaryTable().getSelectedRow();
        if(selectedInvoiceSummary >= 0){
            InvoiceSummary invoiceSummary = salesInvoiceGeneratorFrame.getInvoiceSummarys().get(selectedInvoiceSummary);
            InvoiceDetails newInvoiceDetails = new InvoiceDetails(itemName, price, qty, invoiceSummary);
            invoiceSummary.getInvoiceDetailses().add(newInvoiceDetails);
            InvoiceDetailsTable invoiceDetailsTable = new InvoiceDetailsTable(invoiceSummary.getInvoiceDetailses());
            salesInvoiceGeneratorFrame.getDetailsTable().setModel(invoiceDetailsTable);
            invoiceDetailsTable.fireTableDataChanged();
            invoiceDetailsDialog.setVisible(false);
        }
    }

    private void discardCreateNewDetailsInvoice() {
        invoiceDetailsDialog.setVisible(false);
    }

    
}
