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
public class InvoiceSummaryDialog extends JDialog{
    
    private JLabel customerNameLabel;
    private JTextField customerNameField;
    private JLabel dateLabel;
    private JTextField datField;
    private JButton createButton;
    private JButton discardButton;
    
    public InvoiceSummaryDialog(SalesInvoiceGeneratorFrame salesInvoiceGeneratorFrame){
        customerNameLabel = new JLabel("Customer Name");
        customerNameField = new JTextField(30);
        dateLabel = new JLabel("Date");
        datField = new JTextField(30);
        createButton = new JButton("Create");
        discardButton = new JButton("Discard");
        createButton.addActionListener(salesInvoiceGeneratorFrame.getSalesInvoiceGeneratorController());
        discardButton.addActionListener(salesInvoiceGeneratorFrame.getSalesInvoiceGeneratorController());
        
        createButton.setActionCommand("createDialogButton");
        discardButton.setActionCommand("discardDialogButton");
        
        setLayout(new GridLayout(3,2));
        add(customerNameLabel);
        add(customerNameField);
        add(dateLabel);
        add(datField);
        add(createButton);
        add(discardButton);
        
        pack();
        
    }

    public JTextField getCustomerNameField() {
        return customerNameField;
    }

    public JTextField getDatField() {
        return datField;
    }
    
    
    
}
