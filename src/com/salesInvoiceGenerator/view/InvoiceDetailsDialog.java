/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salesInvoiceGenerator.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Mahmoud
 */
public class InvoiceDetailsDialog extends JDialog{
    
    private JLabel itemNameLabel;
    private JTextField itemNameField;
    private JLabel itemPriceLable;
    private JTextField itemPriceField;
    private JLabel qtyLabel;
    private JTextField qtyField;
    private JButton createButton;
    private JButton discardButton;
    
    
    public InvoiceDetailsDialog(SalesInvoiceGeneratorFrame salesInvoiceGeneratorFrame){
        itemNameLabel = new JLabel("Item Name");
        itemNameField = new JTextField(30);
        itemPriceLable = new JLabel("Item Price");
        itemPriceField = new JTextField(30);
        itemNameField = new JTextField(30);
        qtyLabel = new JLabel("Quantity");
        qtyField = new JTextField(30);
        createButton = new JButton("Create");
        discardButton = new JButton("Discard");
        createButton.setActionCommand("createDetailsDialogButton");
        discardButton.setActionCommand("discardDetailsDialogButton");
        createButton.addActionListener(salesInvoiceGeneratorFrame.getSalesInvoiceGeneratorController());
        discardButton.addActionListener(salesInvoiceGeneratorFrame.getSalesInvoiceGeneratorController());
        setLayout(new GridLayout(4,2));
        add(itemNameLabel);
        add(itemNameField);
        add(itemPriceLable);
        add(itemPriceField);
        add(qtyLabel);
        add(qtyField);
        add(createButton);
        add(discardButton);
       
        pack();
    }

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }

    public JTextField getQtyField() {
        return qtyField;
    }
    
    
    
}
