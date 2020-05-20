/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.controllers;

import com.saburi.common.controllers.EditController;
import com.saburi.common.dbaccess.StaffDA;
import static com.saburi.common.utils.FXUIUtils.addRow;
import static com.saburi.common.utils.FXUIUtils.errorMessage;
import static com.saburi.common.utils.FXUIUtils.formatDatePicker;
import static com.saburi.common.utils.FXUIUtils.formatValue;
import static com.saburi.common.utils.FXUIUtils.getDate;
import static com.saburi.common.utils.FXUIUtils.getDouble;
import static com.saburi.common.utils.FXUIUtils.getSelectedItem;
import static com.saburi.common.utils.FXUIUtils.getSelectedValue;
import static com.saburi.common.utils.FXUIUtils.getText;
import static com.saburi.common.utils.FXUIUtils.message;
import static com.saburi.common.utils.FXUIUtils.setTableEditable;
import static com.saburi.common.utils.FXUIUtils.validateNumber;
import static com.saburi.common.utils.FXUIUtils.warningOk;
import com.saburi.common.utils.Utilities.FormMode;
import static com.saburi.common.utils.Utilities.formatNumber;
import com.saburi.finance.dbaccess.CreditNoteApprovalDA;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import com.saburi.finance.dbaccess.CreditNoteDA;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import com.saburi.finance.dbaccess.InvoiceDA;
import com.saburi.finance.utils.FinanceEnums.RequestStatus;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import java.util.List;
import com.saburi.finance.dbaccess.CreditNoteRequestDetailsDA;
import com.saburi.finance.entities.CreditNoteApproval;
import com.saburi.finance.utils.FinanceEnums;
import javafx.scene.control.TableColumn;
import java.io.IOException;

public class CreditNoteController extends EditController {

    private final CreditNoteDA oCreditNoteDA = new CreditNoteDA();
    @FXML
    private TextField txtCreditNoteApprovalID;
    @FXML
    private DatePicker dtpCreditNoteDate;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextArea txaAmountWords;
    @FXML
    private TextField txtCustomerName;
    @FXML
    private DatePicker dtpApprovalDate;
    @FXML
    private TextArea txaApprovalNotes;
    @FXML
    private TextField txtApprovedBy;
    @FXML
    private TextField txtInvoice;
    @FXML
    private DatePicker dtpRequestDate;
    @FXML
    private TextArea txaRequestNotes;
    @FXML
    private TextField txtInvoiceAmount;
    @FXML
    private ComboBox cboRequestStatus;
    @FXML
    private TableView<CreditNoteRequestDetailsDA> tblCreditNoteRequestDetails;
    private final StaffDA oStaffDA = new StaffDA();
    private final InvoiceDA oInvoiceDA = new InvoiceDA();
    @FXML
    private TableColumn<CreditNoteRequestDetailsDA, String> tbcCreditNoteRequestDetailsItemID;
    @FXML
    private TableColumn<CreditNoteRequestDetailsDA, String> tbcCreditNoteRequestDetailsItemName;
    @FXML
    private TableColumn<CreditNoteRequestDetailsDA, Integer> tbcCreditNoteRequestDetailsBaseQuantity;
    @FXML
    private TableColumn<CreditNoteRequestDetailsDA, String> tbcCreditNoteRequestDetailsUnitMeasure;
    @FXML
    private TableColumn<CreditNoteRequestDetailsDA, Integer> tbcCreditNoteRequestDetailsMeasureSize;
    @FXML
    private TableColumn<CreditNoteRequestDetailsDA, Integer> tbcCreditNoteRequestDetailsQuantity;
    @FXML
    private TableColumn<CreditNoteRequestDetailsDA, String> tbcCreditNoteRequestDetailsUnitPrice;
    @FXML
    private TableColumn<CreditNoteRequestDetailsDA, String> tbcCreditNoteRequestDetailsAmount;
    @FXML
    private MenuItem cmiApprovedLoad;
    private final CreditNoteApprovalDA oCreditNoteApprovalDA = new CreditNoteApprovalDA();
    private CreditNoteApproval creditNoteApproval;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cboRequestStatus.setItems(FXCollections.observableArrayList(RequestStatus.values()));
            validateNumber(txtAmount);
            validateNumber(txtInvoiceAmount);
            formatValue(txtAmount);
            formatValue(txtInvoiceAmount);
            formatDatePicker(dtpCreditNoteDate);
            formatDatePicker(dtpRequestDate);
            formatDatePicker(dtpApprovalDate);
            tblCreditNoteRequestDetails.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            setTableEditable(tblCreditNoteRequestDetails);
            this.primaryKeyControl = txtCreditNoteApprovalID;
            this.dbAccess = oCreditNoteDA;
            this.restrainColumnConstraint = false;
            //this.prefSize = 360;

            txtCreditNoteApprovalID.focusedProperty().addListener((ov, t, t1) -> {
                if (t) {
                    this.loadCreditNoteApproval();
                }
            });

            dtpRequestDate.disableProperty().set(true);
            dtpApprovalDate.disableProperty().set(true);
            cboRequestStatus.disableProperty().set(true);

            cmiApprovedLoad.visibleProperty().set(btnSave.getText().equalsIgnoreCase(FormMode.Save.name()));
            cmiApprovedLoad.setOnAction(e -> selectCreditNoteApproval());

        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void save() {
        try {
            this.editSuccessful = false;
            String creditNoteApprovalID = getText(txtCreditNoteApprovalID, "Credit Note Approval ID");
            LocalDate creditNoteDate = getDate(dtpCreditNoteDate, "Credit Note Date");
            double amount = getDouble(txtAmount, "Amount");
            String amountWords = getText(txaAmountWords, "AmountWords");
            String customerName = getText(txtCustomerName, "Customer Name");
            LocalDate approvalDate = getDate(dtpApprovalDate, "Approval Date");
            String approvalNotes = getText(txaApprovalNotes, "Approval Notes");

            LocalDate requestDate = getDate(dtpRequestDate, "Request Date");
            String requestNotes = getText(txaRequestNotes, "Request Notes");
            double invoiceAmount = getDouble(txtInvoiceAmount, "Invoice Amount");
            RequestStatus requestStatus = (RequestStatus) getSelectedValue(cboRequestStatus, "Request Status");
            List<CreditNoteRequestDetailsDA> creditNoteRequestDetailsDAs = tblCreditNoteRequestDetails.getItems();
            creditNoteRequestDetailsDAs.removeIf((p) -> p.getItemID() == null);

            CreditNoteDA creditNoteDA = new CreditNoteDA(creditNoteApproval, creditNoteDate, FinanceEnums.ApprovalModes.Manual, amount, amountWords);
            String buttonText = btnSave.getText();
            if (buttonText.equalsIgnoreCase(FormMode.Save.name())) {
                creditNoteDA.save();
                message("Saved Successfully");
                clear();
                addRow(tblCreditNoteRequestDetails, new CreditNoteRequestDetailsDA());
            } else if (buttonText.equalsIgnoreCase(FormMode.Update.name())) {
                creditNoteDA.update();
                message("Updated Successfully");
            }
            this.dbAccess = creditNoteDA;
            this.editSuccessful = true;
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void delete() {
        try {
            String creditNoteApprovalID = getText(txtCreditNoteApprovalID, "Credit Note Approval ID");
            CreditNoteDA creditNoteDA = oCreditNoteDA.get(creditNoteApprovalID);
            if (!warningOk("Confirm Delete", "You are about to delete a record with ID: " + creditNoteApprovalID + " Are you sure you want to continue?", "Remember this action cannot be un done")) {
                return;
            }
            if (creditNoteDA.delete()) {
                message("Deleted Successfully");
            }
        } catch (Exception e) {
            errorMessage(e);
        }
    }

    @Override
    public void loadData() {
        try {
            String creditNoteApprovalID = getText(txtCreditNoteApprovalID, "Credit Note Approval ID");

            CreditNoteDA creditNoteDA = oCreditNoteDA.get(creditNoteApprovalID);
            txtCreditNoteApprovalID.setText(creditNoteDA.getCreditNoteApprovalID());
            dtpCreditNoteDate.setValue((LocalDate) creditNoteDA.getCreditNoteDate());
            txtAmount.setText(formatNumber(creditNoteDA.getAmount()));
            txaAmountWords.setText(creditNoteDA.getAmountWords());
            txtCustomerName.setText(creditNoteDA.getCustomerName());
            dtpApprovalDate.setValue((LocalDate) creditNoteDA.getApprovalDate());
            txaApprovalNotes.setText(creditNoteDA.getApprovalNotes());
            txtApprovedBy.setText(creditNoteDA.getApprovedBy().getDisplayKey());
            txtInvoice.setText(creditNoteDA.getInvoice().getDisplayKey());
            dtpRequestDate.setValue((LocalDate) creditNoteDA.getRequestDate());
            txaRequestNotes.setText(creditNoteDA.getRequestNotes());
            txtInvoiceAmount.setText(formatNumber(creditNoteDA.getInvoiceAmount()));
            cboRequestStatus.setValue(creditNoteDA.getRequestStatus());
            tblCreditNoteRequestDetails.setItems(FXCollections.observableArrayList(creditNoteDA.getCreditNoteRequestDetailsDAs()));

        } catch (Exception e) {
            errorMessage(e);
        }

    }

    public void loadCreditNoteApproval() {
        try {
            String creditNoteApprovalID = getText(txtCreditNoteApprovalID);
            if (creditNoteApprovalID.isBlank()) {
                return;
            }

            CreditNoteApprovalDA creditNoteApprovalDA = oCreditNoteApprovalDA.get(creditNoteApprovalID);
            setCreditNoteApprovalData(creditNoteApprovalDA);

        } catch (Exception e) {
            errorMessage(e);
        }

    }

    private void setCreditNoteApprovalData(CreditNoteApprovalDA creditNoteApprovalDA) {
        try {
            txtInvoice.setText(creditNoteApprovalDA.getInvoiceDisplay());
            dtpRequestDate.setValue((LocalDate) creditNoteApprovalDA.getRequestDate());
            txaRequestNotes.setText(creditNoteApprovalDA.getRequestNotes());
            txtAmount.setText(formatNumber(creditNoteApprovalDA.getAmount()));
            txaAmountWords.setText(creditNoteApprovalDA.getAmountWords());
            txtCustomerName.setText(creditNoteApprovalDA.getInvoice().getBillTo().getCustomerName());
            txtInvoiceAmount.setText(formatNumber(creditNoteApprovalDA.getInvoice().getAmount()));
            cboRequestStatus.setValue(creditNoteApprovalDA.getRequestStatus());
            txaApprovalNotes.setText(creditNoteApprovalDA.getNotes());
            dtpApprovalDate.setValue((LocalDate) creditNoteApprovalDA.getApprovalDate());
            txtApprovedBy.setText(creditNoteApprovalDA.getApprovedBy().getDisplayKey());
            tblCreditNoteRequestDetails.setItems(FXCollections.observableArrayList(creditNoteApprovalDA.getCreditNoteRequestDetailsDAs()));
            this.creditNoteApproval = creditNoteApprovalDA.getCreditNoteApproval();
        } catch (Exception ex) {
            errorMessage(ex);
        }
    }

    private void selectCreditNoteApproval() {

        try {
            CreditNoteApprovalDA creditNoteApprovalDA = (CreditNoteApprovalDA) getSelectedItem(mainClass, oCreditNoteApprovalDA, CreditNoteApprovalDA.getCreditNoteApprovalDAs(oCreditNoteApprovalDA.getApprovedCreditNoteApprovals()),
                     "CreditNoteApproval", "Credit Note Approvals", 0, 0, txtCreditNoteApprovalID, true);
            if (creditNoteApproval == null) {
                return;
            }
            setCreditNoteApprovalData(creditNoteApprovalDA);
        } catch (IOException ex) {
            errorMessage(ex);
        }
    }

}
