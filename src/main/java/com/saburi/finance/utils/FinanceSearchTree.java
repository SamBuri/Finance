/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saburi.finance.utils;

import static com.saburi.common.utils.CommonEnums.SearchItemTypes.Revision;
import com.saburi.common.utils.CommonSearchTree;
import com.saburi.common.utils.SearchItem;
import com.saburi.finance.dbaccess.*;
import com.saburi.finance.main.App;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.TreeItem;

/**
 *
 * @author CLINICMASTER13
 */
public class FinanceSearchTree extends CommonSearchTree {

    protected final TreeItem<SearchItem> triChartAccounts = new TreeItem<>(new SearchItem("Chart of Accounts"));
    protected final TreeItem<SearchItem> triChartAccountsRev = new TreeItem<>(new SearchItem("Chart of Accounts"));
    protected final TreeItem<SearchItem> triItem = new TreeItem<>(new SearchItem("Item"));
    protected final TreeItem<SearchItem> triItemRev = new TreeItem<>(new SearchItem("Item"));
    protected final TreeItem<SearchItem> triFinance = new TreeItem<>(new SearchItem("Finance"));
    protected final TreeItem<SearchItem> triFinanceRev = new TreeItem<>(new SearchItem("Finance"));
    protected final TreeItem<SearchItem> triPositingGroup = new TreeItem<>(new SearchItem("Posting Groups"));
    protected final TreeItem<SearchItem> triPositingGroupRev = new TreeItem<>(new SearchItem("Posting Groups"));
    protected final TreeItem<SearchItem> triAccounting = new TreeItem<>(new SearchItem("Accounting"));
    protected final TreeItem<SearchItem> triAccountingRev = new TreeItem<>(new SearchItem("Accounting"));
    protected final TreeItem<SearchItem> triSales = new TreeItem<>(new SearchItem("Sales"));
    protected final TreeItem<SearchItem> triSalesRev = new TreeItem<>(new SearchItem("Sales"));

    private final Class mainClass = App.class;
    private final List<SearchItem> sItems = Arrays.asList(
            //           Setup
            new SearchItem(mainClass, new CurrencyDA(), "Currency", "Currency", true, triFinance),
            new SearchItem(mainClass, new CurrencyDA(), Revision, "Currency", "Currency", true, triFinanceRev),
            new SearchItem(mainClass, new FinancialPeriodDA(), "Financial Period", "Financial Period", true, triFinance),
            new SearchItem(mainClass, new FinancialPeriodDA(), Revision, "Financial Periods", "Financial Periods", true, triFinanceRev),
            new SearchItem(mainClass, new AccountCategoryDA(), "AccountCategory", "Account Categories", false, triChartAccounts),
            new SearchItem(mainClass, new AccountCategoryDA(), Revision, "AccountCategory", "Account Categories", false, triChartAccountsRev),
            new SearchItem(mainClass, new ChartAccountDA(), "ChartAccount", "Chart Accounts", false, triChartAccounts),
            new SearchItem(mainClass, new ChartAccountDA(), Revision, "ChartAccount", "Chart Accounts", false, triChartAccountsRev),
            new SearchItem(mainClass, new BankAccountDA(), "BankAccount", "Bank Account", true, triFinance),
            new SearchItem(mainClass, new BankAccountDA(), Revision, "BankAccount", "Bank Account", true, triFinanceRev),
            new SearchItem(mainClass, new VendorDA(), "Vendor", "Vendor", false, triSetup),
            new SearchItem(mainClass, new VendorDA(), Revision, "Vendor", "Vendor", false, triSetupRev),
            new SearchItem(mainClass, new CustomerPostingGroupDA(), "CustomerPostingGroup", "Customer Posting Group", true, triPositingGroup),
            new SearchItem(mainClass, new CustomerPostingGroupDA(), Revision, "CustomerPostingGroup", "Customer Posting Group", true, triPositingGroupRev),
            new SearchItem(mainClass, new InventoryPostingGroupDA(), "InventoryPostingGroup", "Inventory Posting Group", true, triPositingGroup),
            new SearchItem(mainClass, new InventoryPostingGroupDA(), Revision, "InventoryPostingGroup", "Inventory Posting Group", true, triPositingGroupRev),
            new SearchItem(mainClass, new GeneralPostingGroupDA(), "GeneralPostingGroup", "General Posting Group", false, triPositingGroup),
            new SearchItem(mainClass, new GeneralPostingGroupDA(), Revision, "GeneralPostingGroup", "General Posting Group", false, triPositingGroupRev),
            new SearchItem(mainClass, new VATPostingGroupDA(), "VATPostingGroup", "VAT Posting Group", true, triPositingGroup),
            new SearchItem(mainClass, new VATPostingGroupDA(), Revision, "VATPostingGroup", "VAT Posting Group", true, triPositingGroupRev),
            new SearchItem(mainClass, new VendorPostingGroupDA(), "VendorPostingGroup", "Vendor Posting Group", true, triPositingGroup),
            new SearchItem(mainClass, new VendorPostingGroupDA(), Revision, "VendorPostingGroup", "Vendor Posting Group", true, triPositingGroupRev),
            new SearchItem(mainClass, new MeasureGroupDA(), "MeasureGroup", "Measure Group", true, triItem),
            new SearchItem(mainClass, new MeasureGroupDA(), Revision, "MeasureGroup", "Measure Group", true, triItemRev),
            new SearchItem(mainClass, new MeasureRelationDA(), "MeasureRelation", "Measure Relation", true, triItem),
            new SearchItem(mainClass, new MeasureRelationDA(), Revision, "MeasureRelation", "Measure Relation", true, triItemRev),
            new SearchItem(mainClass, new ItemCategoryDA(), "ItemCategory", "Item Category", true, triItem),
            new SearchItem(mainClass, new ItemCategoryDA(), Revision, "ItemCategory", "Item Category", true, triItemRev),
            new SearchItem(mainClass, new ItemTemplateDA(), "ItemTemplate", "Item Template", true, triItem),
            new SearchItem(mainClass, new ItemTemplateDA(), Revision, "ItemTemplate", "Item Template", true, triItemRev),
            new SearchItem(mainClass, new ItemDA(), "Item", "Item", true, triItem),
            new SearchItem(mainClass, new ItemDA(), Revision, "Item", "Item", true, triItemRev),
            //            Set up

            //             Accounting

            new SearchItem(mainClass, new BankLedgerDA(), "BankLedger", "Bank Ledger", false, triAccounting),
            new SearchItem(mainClass, new BankLedgerDA(), Revision, "BankLedger", "Bank Ledger", false, triAccountingRev),
            new SearchItem(mainClass, new CustomerLedgerDA(), "CustomerLedger", "Customer Ledger", false, triAccounting),
            new SearchItem(mainClass, new CustomerLedgerDA(), Revision, "CustomerLedger", "Customer Ledger", false, triAccountingRev),
            new SearchItem(mainClass, new GeneralLedgerDA(), "GeneralLedger", "General Ledger", false, triAccounting),
            new SearchItem(mainClass, new GeneralLedgerDA(), Revision, "GeneralLedger", "General Ledger", false, triAccountingRev),
            new SearchItem(mainClass, new VendorLedgerDA(), "VendorLedger", "Vendor Ledger", false, triAccounting),
            new SearchItem(mainClass, new VendorLedgerDA(), Revision, "VendorLedger", "Vendor Ledger", false, triAccountingRev),
            new SearchItem(mainClass, new JournalEntryDA(), "JournalEntry", "Journal Entries", false, triAccounting),
            new SearchItem(mainClass, new JournalEntryDetailDA(), "JournalEntry", "Journal Entry Details", false, triAccounting),
            //            Accounting

            //            Invoicing
            new SearchItem(mainClass, new SaleOrderDA(), "SaleOrder", "Sale Orders", false, triSales),
            new SearchItem(mainClass, new SaleOrderDA(), Revision, "SaleOrder", "Sale Orders", false, triSalesRev),
            new SearchItem(mainClass, new SaleOrderDetailDA(), "SaleOrderDetail", "Sale Order Details", false, triSales),
            new SearchItem(mainClass, new SaleOrderDetailDA(), Revision, "SaleOrderDetail", "Sale Order Details", false, triSalesRev),
            new SearchItem(mainClass, new InvoiceDA(), "Invoice", "Invoice", false, triSales, false, true),
            new SearchItem(mainClass, new InvoiceDA(), Revision, "Invoice", "Invoice", false, triSalesRev),
            new SearchItem(mainClass, new InvoiceDetailsDA(), "InvoiceDetails", "Invoice Details", false, triSales, false, false),
            new SearchItem(mainClass, new InvoiceDetailsDA(), Revision, "InvoiceDetails", "Invoice Details", false, triSalesRev),
            new SearchItem(mainClass, new CreditNoteRequestDA(), "CreditNoteRequest", "Credit Note Requests", false, triSales),
            new SearchItem(mainClass, new CreditNoteRequestDA(), Revision, "CreditNoteRequest", "Credit Note Requests", false, triSalesRev),
            new SearchItem(mainClass, new CreditNoteRequestDetailsDA(), "CreditNoteRequestDetails", "Credit Note Request Details", false, triSales, false, false),
            new SearchItem(mainClass, new CreditNoteRequestDetailsDA(), Revision, "CreditNoteRequestDetails", "Credit Note Request Details", false, triSalesRev),
            new SearchItem(mainClass, new CreditNoteApprovalDA(), "CreditNoteApproval", "Credit Note Approvals", false, triSales),
            new SearchItem(mainClass, new CreditNoteApprovalDA(), Revision, "CreditNoteApproval", "Credit Note Approvals", false, triSalesRev),
            new SearchItem(mainClass, new CreditNoteDA(), "CreditNote", "Credit Notes", false, triSales),
            new SearchItem(mainClass, new CreditNoteDA(), Revision, "CreditNote", "Credit Notes", false, triSalesRev),
            new SearchItem(mainClass, new ReceiptDA(), "Receipt", "Receipt", false, triSales, false, true),
            new SearchItem(mainClass, new ReceiptDA(), Revision, "Receipt", "Receipt", false, triSalesRev),
            new SearchItem(mainClass, new ReceiptInvoiceDA(), "ReceiptInvoice", "Receipt Invoice", true, triSales, false, false),
            new SearchItem(mainClass, new ReceiptInvoiceDA(), Revision, "ReceiptInvoice", "Receipt Invoice", false, triSalesRev),
            new SearchItem(mainClass, new RefundDA(), "Refund", "Refunds", false, triSales),
            new SearchItem(mainClass, new RefundDA(), Revision, "Refund", "Refunds", false, triSalesRev),
            new SearchItem(mainClass, new RefundReceiptInvoiceDA(), "RefundReceiptInvoice", "Refund Receipt Invoices", false, triSales),
            new SearchItem(mainClass, new RefundReceiptInvoiceDA(), Revision, "RefundReceiptInvoice", "Refund Receipt Invoices", false, triSalesRev),
            //            Invoicing End
            //            General
            new SearchItem(mainClass, new CustomerDA(), "Customer", "Customer", false),
            new SearchItem(mainClass, new CustomerDA(), Revision, "Customer", "Customer", false)
    //            General End
    );

    @Override
    public List<SearchItem> getSearchObjects() {
        commonSearchObjects = super.getSearchObjects();
        if (!commonSearchObjects.containsAll(sItems)) {
            commonSearchObjects.addAll(sItems);
        }
        return commonSearchObjects;
    }

    @Override
    public List<TreeItem> getTreeItems() {
        treeItems = super.getTreeItems();
        triEntities.setExpanded(true);
        triItem.setExpanded(true);
        triItemRev.setExpanded(true);
        triSales.setExpanded(true);
        triSalesRev.setExpanded(true);
        sItems.forEach(e -> addTreeItem(e));
        triFinance.getChildren().addAll(triChartAccounts, triPositingGroup);
        triFinanceRev.getChildren().addAll(triChartAccountsRev, triPositingGroupRev);
        triSetup.getChildren().addAll(triFinance, triItem);
        triSetupRev.getChildren().addAll(triFinanceRev, triItemRev);
        triEntities.getChildren().addAll(triSales, triAccounting);
        triRevisions.getChildren().addAll(triSalesRev, triAccountingRev);

        this.treeItems = Arrays.asList(triEntities, triRevisions);
        return this.treeItems;
    }

}
