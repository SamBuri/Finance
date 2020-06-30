/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saburi.finance.controllers;

import com.saburi.common.controllers.CommonSceneController;
import com.saburi.common.utils.CommonEnums;
import com.saburi.common.utils.CommonEnums.ViewMenuTypes;
import com.saburi.common.utils.FXUIUtils;
import static com.saburi.common.utils.Navigation.loadSearchEngine;
import com.saburi.common.utils.Utilities.FormMode;
import com.saburi.finance.dbaccess.*;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import static com.saburi.finance.utils.FinanceNavigate.*;
import com.saburi.finance.utils.FinanceSearchTree;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Hp
 */
public class FinanceSceneController extends CommonSceneController {

    @FXML
    private MenuItem mnuVendorAdd, mnuVendorUpdate, mnuVendorView;
    @FXML
    private MenuItem mnuCurrencyAdd, mnuCurrencyUpdate, mnuCurrencyView;
    @FXML
    private MenuItem mnuAccountCategoryAdd, mnuAccountCategoryUpdate, mnuAccountCategoryView;

    @FXML
    private MenuItem mnuChartAccountAdd, mnuChartAccountUpdate, mnuChartAccountView;
    @FXML
    private MenuItem mnuBankAccountAdd, mnuBankAccountUpdate, mnuBankAccountView;

    @FXML
    private MenuItem mnuGeneralPostingGroupAdd, mnuGeneralPostingGroupUpdate, mnuGeneralPostingGroupView;
    @FXML
    private MenuItem mnuCustomerPostingGroupAdd, mnuCustomerPostingGroupUpdate, mnuCustomerPostingGroupView;
    @FXML
    private MenuItem mnuVendorPostingGroupAdd, mnuVendorPostingGroupUpdate, mnuVendorPostingGroupView;

    @FXML
    private MenuItem mnuVATPostingGroupAdd, mnuVATPostingGroupUpdate, mnuVATPostingGroupView;
    @FXML
    private MenuItem mnuInventoryPostingGroupAdd, mnuInventoryPostingGroupUpdate, mnuInventoryPostingGroupView;
    @FXML
    private MenuItem spmCustomerAdd, spmCustomerUpdate, spmCustomerView;
    @FXML
    private MenuItem mnuFinancialPeriodAdd, mnuFinancialPeriodUpdate, mnuFinancialPeriodView;
    @FXML
    private MenuItem mnuJournalEntryAdd, mnuJournalEntryUpdate, mnuJournalEntryView;
    @FXML
    private MenuItem mnuGeneralLedger;

    @FXML
    private MenuItem mnuMeasureGroupAdd, mnuMeasureGroupUpdate, mnuMeasureGroupView;

    @FXML
    private MenuItem mnuItemCategoryAdd, mnuItemCategoryUpdate, mnuItemCategoryView;
    @FXML
    private MenuItem mnuItemTemplateAdd, mnuItemTemplateUpdate, mnuItemTemplateView;
    @FXML
    private MenuItem mnuItemAdd, mnuItemUpdate, mnuItemView;

    @FXML
    private MenuItem spmSaleOrderAdd, spmSaleOrderUpdate, spmSaleOrderView;

    @FXML
    private MenuItem spmInvoiceAdd, spmInvoicePrint, spmInvoiceView;
    @FXML
    private MenuItem spmReceiptAdd, spmReceiptPrint, spmReceiptView;
    @FXML
    private MenuItem mnuCreditNoteRequestNew, mnuCreditNoteRequestEdit, mnuCreditNoteRequestView;
    @FXML
    private MenuItem mnuCreditNoteApprovalNew, mnuCreditNoteApprovalEdit, mnuCreditNoteApprovalView;
    @FXML
    private MenuItem mnuCreditNoteNew, mnuCreditNoteEdit, mnuCreditNoteView;

    @FXML
    private MenuItem mnuRefundNew, mnuRefundEdit, mnuRefundView;
    @FXML
    SplitMenuButton spmCustomer, spmFinance;

    @Override
    protected void init() throws IOException {
        super.init();
        loadSearchEngine(mnuSearchEngine, new FinanceSearchTree().getTreeItems(), true);
        setIcons();

//Begin SettingFinance ************************************************************************************
        editMenuItemClick(mnuCurrencyAdd, "Currency", "Currency", FormMode.Save);
        editMenuItemClick(mnuCurrencyUpdate, "Currency", "Currency", FormMode.Update);
        viewMenuItemClick(mnuCurrencyView, new CurrencyDA(), "Currency", "Currency", true, true);

        editMenuItemClick(mnuAccountCategoryAdd, "AccountCategory", "Account Category", FormMode.Save);
        editMenuItemClick(mnuAccountCategoryUpdate, "AccountCategory", "Account Category", FormMode.Update);
        viewMenuItemClick(mnuAccountCategoryView, new AccountCategoryDA(), "AccountCategory", "Account Category", true, true);

        editMenuItemClick(mnuChartAccountAdd, "ChartAccount", "Chart of Account", FormMode.Save);
        editMenuItemClick(mnuChartAccountUpdate, "ChartAccount", "Chart of Account", FormMode.Update);
        viewMenuItemClick(mnuChartAccountView, new ChartAccountDA(), "ChartAccount", "Chart of Account", false, true);

        editMenuItemClick(mnuBankAccountAdd, "BankAccount", "Bank Account", FormMode.Save);
        editMenuItemClick(mnuBankAccountUpdate, "BankAccount", "Bank Account", FormMode.Update);
        viewMenuItemClick(mnuBankAccountView, new BankAccountDA(), "BankAccount", "Bank Account", false, true);

        editMenuItemClick(mnuVendorAdd, "Vendor", "Vendor", FormMode.Save);
        editMenuItemClick(mnuVendorUpdate, "Vendor", "Vendor", FormMode.Update);
        viewMenuItemClick(mnuVendorView, new VendorDA(), "Vendor", "Vendor", false, true);

        editMenuItemClick(mnuGeneralPostingGroupAdd, "GeneralPostingGroup", "General Posting Group", FormMode.Save);
        editMenuItemClick(mnuGeneralPostingGroupUpdate, "GeneralPostingGroup", "General Posting Group", FormMode.Update);
        viewMenuItemClick(mnuGeneralPostingGroupView, new GeneralPostingGroupDA(), "GeneralPostingGroup", "General Posting Group", false, true);

        editMenuItemClick(mnuCustomerPostingGroupAdd, "CustomerPostingGroup", "Customer Posting Group", FormMode.Save);
        editMenuItemClick(mnuCustomerPostingGroupUpdate, "CustomerPostingGroup", "Customer Posting Group", FormMode.Update);
        viewMenuItemClick(mnuCustomerPostingGroupView, new CustomerPostingGroupDA(), "CustomerPostingGroup", "Customer Posting Group", false, true);

        editMenuItemClick(mnuVendorPostingGroupAdd, "VendorPostingGroup", "Vendor Posting Group", FormMode.Save);
        editMenuItemClick(mnuVendorPostingGroupUpdate, "VendorPostingGroup", "Vendor Posting Group", FormMode.Update);
        viewMenuItemClick(mnuVendorPostingGroupView, new VendorPostingGroupDA(), "VendorPostingGroup", "Vendor Posting Group", false, true);

        editMenuItemClick(mnuVATPostingGroupAdd, "VATPostingGroup", "VAT Posting Group", FormMode.Save);
        editMenuItemClick(mnuVATPostingGroupUpdate, "VATPostingGroup", "VAT Posting Group", FormMode.Update);
        viewMenuItemClick(mnuVATPostingGroupView, new VATPostingGroupDA(), "VATPostingGroup", "VAT Posting Group", false, true);

        editMenuItemClick(mnuInventoryPostingGroupAdd, "InventoryPostingGroup", "Inventory Posting Group", FormMode.Save);
        editMenuItemClick(mnuInventoryPostingGroupUpdate, "InventoryPostingGroup", "Inventory Posting Group", FormMode.Update);
        viewMenuItemClick(mnuInventoryPostingGroupView, new InventoryPostingGroupDA(), "InventoryPostingGroup", "Inventory Posting Group", false, true);

        editMenuItemClick(spmCustomerAdd, "Customer", "Customer", FormMode.Save);
        editMenuItemClick(spmCustomerUpdate, "Customer", "Customer", FormMode.Update);
        viewMenuItemClick(spmCustomerView, new CustomerDA(), "Customer", "Customer", false, true);
        editMenuItemClick(mnuFinancialPeriodAdd, "FinancialPeriod", "Financial Period", FormMode.Save);
        editMenuItemClick(mnuFinancialPeriodUpdate, "FinancialPeriod", "Financial Period", FormMode.Update);
        viewMenuItemClick(mnuFinancialPeriodView, new FinancialPeriodDA(), "FinancialPeriod", "Financial Period", true, true);

// End SettingFinance ***********************************************************************
//        Begin Accountant
        editMenuItemClick(mnuJournalEntryAdd, "JournalEntry", "Journal Entry", FormMode.Save);
        editMenuItemClick(mnuJournalEntryUpdate, "JournalEntry", "Journal Entry", FormMode.Update);
        viewMenuItemClick(mnuJournalEntryView, new JournalEntryDA(), "JournalEntry", "Journal Entry", false, true);
        viewMenuItemClickIgnoreParent(mnuGeneralLedger, new GeneralLedgerDA(), "GeneralLedger", "General Ledger", true, true);

//End Account
        editMenuItemClick(mnuMeasureGroupAdd, "MeasureGroup", "Measure Group", FormMode.Save);
        editMenuItemClick(mnuMeasureGroupUpdate, "MeasureGroup", "Measure Group", FormMode.Update);
        viewMenuItemClick(mnuMeasureGroupView, new MeasureGroupDA(), "MeasureGroup", "Measure Group", false, true);

        editMenuItemClick(mnuItemCategoryAdd, "ItemCategory", "Item Category", FormMode.Save);
        editMenuItemClick(mnuItemCategoryUpdate, "ItemCategory", "Item Category", FormMode.Update);
        viewMenuItemClick(mnuItemCategoryView, new ItemCategoryDA(), "ItemCategory", "Item Category", false, true);

        editMenuItemClick(mnuItemTemplateAdd, "ItemTemplate", "Item Template", FormMode.Save);
        editMenuItemClick(mnuItemTemplateUpdate, "ItemTemplate", "Item Template", FormMode.Update);
        viewMenuItemClick(mnuItemTemplateView, new ItemTemplateDA(), "ItemTemplate", "ItemTemplateView", "Item Template", false, true);

        editMenuItemClick(mnuItemAdd, "Item", "Item", FormMode.Save);
        editMenuItemClick(mnuItemUpdate, "Item", "Item", FormMode.Update);
        viewMenuItemClick(mnuItemView, new ItemDA(), "Item", "Item", false, true);

        editMenuItemClick(spmSaleOrderAdd, "SaleOrder", "Sale Orders", FormMode.Save);
        editMenuItemClick(spmSaleOrderUpdate, "SaleOrder", "Sale Orders", FormMode.Update);
        viewMenuItemClick(spmSaleOrderView, new SaleOrderDA(), "SaleOrder", "Sale Orders", false, true);

        editMenuItemClick(spmInvoiceAdd, "Invoice", "Invoice", FormMode.Save);
        editMenuItemClick(spmInvoicePrint, "Invoice", "Invoice", FormMode.Print);
        viewMenuItemClick(spmInvoiceView, new InvoiceDA(), "Invoice", "Invoice", false, true, ViewMenuTypes.PrintNew);

        editMenuItemClick(spmReceiptAdd, FontAwesomeIcon.MONEY, "Receipt", "Receipt", FormMode.Save);
        editMenuItemClick(spmReceiptPrint, "Receipt", "Receipt", FormMode.Print);
        viewMenuItemClick(spmReceiptView, new ReceiptDA(), "Receipt", "Receipt", false, true, ViewMenuTypes.PrintNew);

        editMenuItemClick(mnuCreditNoteRequestNew, "CreditNoteRequest", "Credit Note Request", FormMode.Save);
        editMenuItemClick(mnuCreditNoteRequestEdit, "CreditNoteRequest", "Credit Note Request", FormMode.Update);
        viewMenuItemClick(mnuCreditNoteRequestView, new CreditNoteRequestDA(), "CreditNoteRequest", "Credit Note Requests", false, true);

        editMenuItemClick(mnuCreditNoteApprovalNew, "CreditNoteApproval", "Credit Note Approval", FormMode.Save);
        editMenuItemClick(mnuCreditNoteApprovalEdit, "CreditNoteApproval", "Credit Note Approval", FormMode.Update);
        viewMenuItemClick(mnuCreditNoteApprovalView, new CreditNoteApprovalDA(), "CreditNoteApproval", "Credit Note Approvals", false, true);

        editMenuItemClick(mnuCreditNoteNew, "CreditNote", "Credit Note", FormMode.Save);
        editMenuItemClick(mnuCreditNoteEdit, "CreditNote", "Credit Note", FormMode.Update);
        viewMenuItemClick(mnuCreditNoteView, new CreditNoteDA(), "CreditNote", "Credit Notes", false, true);
        editMenuItemClick(mnuRefundNew, "Refund", "Refund", FormMode.Save);
        editMenuItemClick(mnuRefundEdit, "Refund", "Refund", FormMode.Update);
        viewMenuItemClick(mnuRefundView, new RefundDA(), "Refund", "Refunds", false, true);
    }

    private void setIcons() {
        spmCustomer.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.USER));
        spmFinance.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.MONEY));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.init();
        } catch (Exception e) {
            FXUIUtils.errorMessage(e);
        }
    }

}
