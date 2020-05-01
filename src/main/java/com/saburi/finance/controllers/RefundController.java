/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.controllers;

import com.saburi.common.controllers.EditController;
import com.saburi.common.utils.EditCell;
import static com.saburi.common.utils.FXUIUtils.addRow;
import static com.saburi.common.utils.FXUIUtils.errorMessage;
import static com.saburi.common.utils.FXUIUtils.formatValue;
import static com.saburi.common.utils.FXUIUtils.getDate;
import static com.saburi.common.utils.FXUIUtils.getDouble;
import static com.saburi.common.utils.FXUIUtils.getEntity;
import static com.saburi.common.utils.FXUIUtils.getSelectedValue;
import static com.saburi.common.utils.FXUIUtils.getText;
import static com.saburi.common.utils.FXUIUtils.loadDBEntities;
import static com.saburi.common.utils.FXUIUtils.message;
import static com.saburi.common.utils.FXUIUtils.selectItem;
import static com.saburi.common.utils.FXUIUtils.setTableEditable;
import static com.saburi.common.utils.FXUIUtils.validateNumber;
import static com.saburi.common.utils.FXUIUtils.warningOk;
import com.saburi.common.utils.Utilities.FormMode;
import static com.saburi.common.utils.Utilities.defortNumberOptional;
import static com.saburi.common.utils.Utilities.formatDate;
import static com.saburi.common.utils.Utilities.formatNumber;
import static com.saburi.common.utils.Utilities.toWords;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import com.saburi.finance.dbaccess.RefundDA;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import com.saburi.finance.dbaccess.CreditNoteDA;
import com.saburi.finance.entities.CreditNote;
import com.saburi.finance.utils.FinanceNavigate;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import com.saburi.finance.utils.FinanceEnums.PayModes;
import javafx.collections.FXCollections;
import com.saburi.finance.dbaccess.BankAccountDA;
import com.saburi.finance.dbaccess.ReceiptDA;
import com.saburi.finance.entities.BankAccount;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import java.util.List;
import com.saburi.finance.dbaccess.RefundReceiptInvoiceDA;
import com.saburi.finance.entities.Invoice;
import javafx.scene.control.TableColumn;
import javafx.application.Platform;
import com.saburi.finance.entities.Receipt;

public class RefundController extends EditController {

    private final RefundDA oRefundDA = new RefundDA();
    @FXML
    private ComboBox cboCreditNote;
    @FXML
    private MenuItem cmiSelectCreditNote;
    @FXML
    private DatePicker dtpRefundDate;
    @FXML
    private ComboBox cboPayMode;
    @FXML
    private ComboBox cboBankAccount;
    @FXML
    private MenuItem cmiSelectBankAccount;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextField txtAmountWords;
    @FXML
    private TextField txtCustomerID;
    @FXML
    private TextField txtCustomerName;
    @FXML
    private TextField txtInvoice;
    @FXML
    private TextField txtInvoiceDate;
    @FXML
    private TextField txtCreditNoteDate;
    @FXML
    private TableView<RefundReceiptInvoiceDA> tblRefundReceiptInvoices;
   
    private final CreditNoteDA oCreditNoteDA = new CreditNoteDA();
    private final BankAccountDA oBankAccountDA = new BankAccountDA();
    ReceiptDA oReceiptDA = new ReceiptDA();
    @FXML
    private TableColumn<RefundReceiptInvoiceDA, String> tbcRefundReceiptInvoiceReceiptID;
    @FXML
    private TableColumn<RefundReceiptInvoiceDA, String> tbcRefundReceiptInvoiceAmount;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadDBEntities(oCreditNoteDA.getToRefundCreditNotes(), cboCreditNote);
            cboPayMode.setItems(FXCollections.observableArrayList(PayModes.values()));
            loadDBEntities(oBankAccountDA.getBankAccounts(), cboBankAccount);

            validateNumber(txtAmount);
            formatValue(txtAmount);
            tblRefundReceiptInvoices.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            setTableEditable(tblRefundReceiptInvoices, false);
            this.primaryKeyControl = cboCreditNote;
            this.dbAccess = oRefundDA;
            this.restrainColumnConstraint = false;
            //this.minSize = 360;

            setRefundReceiptAmount();
            selectItem(FinanceNavigate.MAIN_CLASS, cmiSelectCreditNote, oCreditNoteDA, oCreditNoteDA.getToRefundCreditNoteDAs(), "CreditNote", "Credit Note", cboCreditNote, true);
            selectItem(FinanceNavigate.MAIN_CLASS, cmiSelectBankAccount, oBankAccountDA, "BankAccount", "Bank Account", cboBankAccount, true);
            cboCreditNote.setOnAction(e -> creditNoteSelected());
            dtpRefundDate.setValue(LocalDate.now());
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void save() {
        try {
            this.editSuccessful = false;
            CreditNote creditNote = (CreditNote) getEntity(cboCreditNote, "Credit Note");
            LocalDate refundDate = getDate(dtpRefundDate, "Refund Date");
            PayModes payMode = (PayModes) getSelectedValue(cboPayMode, "Pay Mode");
            BankAccount bankAccount = (BankAccount) getEntity(cboBankAccount, "Bank Account");
            double amount = getDouble(txtAmount, "Amount");
            String amountWords = getText(txtAmountWords, "Amount Words");
            String customerID = getText(txtCustomerID, "Customer ID");
            String customerName = getText(txtCustomerName, "Customer Name");
            String invoice = getText(txtInvoice, "Invoice");
            String invoiceDate = getText(txtInvoiceDate, "Invoice Date");
            String creditNoteDate = getText(txtCreditNoteDate, "Credit Note Date");
            List<RefundReceiptInvoiceDA> refundReceiptInvoiceDAs = tblRefundReceiptInvoices.getItems();
            refundReceiptInvoiceDAs.removeIf((p) -> p.getRefundReceiptInvoice() == null || p.getAmount() == 0);

            RefundDA refundDA = new RefundDA(creditNote, refundDate, payMode, bankAccount, amount, amountWords);
            refundReceiptInvoiceDAs.forEach(e -> {
                e.setRefund(refundDA.getRefund());
            });

            refundDA.setRefundReceiptInvoiceDAs(refundReceiptInvoiceDAs);
            String buttonText = btnSave.getText();
            if (buttonText.equalsIgnoreCase(FormMode.Save.name())) {
                refundDA.save();
                message("Saved Successfully");
                clear();
                addRow(tblRefundReceiptInvoices, new RefundReceiptInvoiceDA());
            } else if (buttonText.equalsIgnoreCase(FormMode.Update.name())) {
                refundDA.update();
                message("Updated Successfully");
            }
            this.dbAccess = refundDA;
            this.editSuccessful = true;
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void delete() {
        try {
            CreditNote creditNote = (CreditNote) getEntity(cboCreditNote, "Credit Note");
            RefundDA refundDA = oRefundDA.get(creditNote.getCreditNoteID());
            if (!warningOk("Confirm Delete", "You are about to delete a record with ID: " + creditNote + " Are you sure you want to continue?", "Remember this action cannot be un done")) {
                return;
            }
            if (refundDA.delete()) {
                message("Deleted Successfully");
            }
        } catch (Exception e) {
            errorMessage(e);
        }
    }

    @Override
    public void loadData() {
        try {
            CreditNote creditNote = (CreditNote) getEntity(cboCreditNote, "Credit Note");

            RefundDA refundDA = oRefundDA.get(creditNote.getCreditNoteID());
            cboCreditNote.setValue(refundDA.getCreditNote());
            dtpRefundDate.setValue((LocalDate) refundDA.getRefundDate());
            cboPayMode.setValue(refundDA.getPayMode());
            cboBankAccount.setValue(refundDA.getBankAccount());
            txtAmount.setText(formatNumber(refundDA.getAmount()));
            txtAmountWords.setText(refundDA.getAmountWords());
            txtCustomerID.setText(refundDA.getCustomerID());
            txtCustomerName.setText(refundDA.getCustomerName());
            txtInvoice.setText(refundDA.getInvoiceID());
            txtInvoiceDate.setText(refundDA.getInvoiceDate());
            txtCreditNoteDate.setText(refundDA.getCreditNoteDate());
            tblRefundReceiptInvoices.setItems(FXCollections.observableArrayList(refundDA.getRefundReceiptInvoiceDAs()));

        } catch (Exception e) {
            errorMessage(e);
        }

    }

    private void setRefundReceiptAmount() {
        tbcRefundReceiptInvoiceAmount.setCellFactory(EditCell.StringTableColumn());
        tbcRefundReceiptInvoiceAmount.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null ? event.getNewValue()
                    : event.getOldValue();
            ((RefundReceiptInvoiceDA) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow()))
                    .setAmount(defortNumberOptional(value));
            tblRefundReceiptInvoices.refresh();
        });
    }

    private void creditNoteSelected() {
        try {
            CreditNote creditNote = (CreditNote) getEntity(cboCreditNote);
            if (creditNote == null) {
                return;
            }
            Invoice invoice = creditNote.getCreditNoteApproval().getCreditNoteRequest().getInvoice();
            double toRefundAmount = creditNote.getToRefundAmount();
            txtAmount.setText(formatNumber(toRefundAmount));
            txtAmountWords.setText(toWords(toRefundAmount));
            txtCreditNoteDate.setText(formatDate(creditNote.getCreditNoteDate()));
            txtInvoice.setText(invoice.getInvoiceID());
            txtInvoiceDate.setText(formatDate(invoice.getInvoiceDate()));
            txtCustomerID.setText(invoice.getBillTo().getCustomerID());
            txtCustomerName.setText(invoice.getBillTo().getCustomerName());
            tblRefundReceiptInvoices.setItems(FXCollections.observableList(RefundReceiptInvoiceDA.getToRefundReceiptDAs(creditNote)));
            if (tblRefundReceiptInvoices.getItems().size() == 1) {
                Receipt receipt = tblRefundReceiptInvoices.getItems().get(0).getReceiptInvoice().getReceipt();
                cboPayMode.setValue(receipt.getPayMode());
                cboBankAccount.setValue(receipt.getBankAcccount());
                tblRefundReceiptInvoices.editableProperty().set(false);
            }
        } catch (Exception e) {
            Platform.runLater(() -> errorMessage(e));
        }

    }

   
}
