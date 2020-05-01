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
import static com.saburi.common.utils.FXUIUtils.getSelectedItem;
import static com.saburi.common.utils.FXUIUtils.getSelectedValue;
import static com.saburi.common.utils.FXUIUtils.getText;
import static com.saburi.common.utils.FXUIUtils.message;
import static com.saburi.common.utils.FXUIUtils.selectItem;
import static com.saburi.common.utils.FXUIUtils.setTableEditable;
import static com.saburi.common.utils.FXUIUtils.validateNumber;
import static com.saburi.common.utils.FXUIUtils.warningOk;
import com.saburi.common.utils.NumberToWord;
import com.saburi.common.utils.Utilities.FormMode;
import static com.saburi.common.utils.Utilities.formatNumber;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import com.saburi.finance.dbaccess.CreditNoteRequestDA;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import com.saburi.finance.dbaccess.InvoiceDA;
import com.saburi.finance.entities.Invoice;
import com.saburi.finance.utils.FinanceNavigate;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import com.saburi.finance.utils.FinanceEnums.RequestStatus;
import javafx.collections.FXCollections;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;
import java.util.List;
import com.saburi.finance.dbaccess.CreditNoteRequestDetailsDA;
import com.saburi.finance.dbaccess.InvoiceDetailsDA;
import com.saburi.finance.dbaccess.ItemDA;
import com.saburi.finance.dbaccess.MeasureGroupDA;
import com.saburi.finance.dbaccess.MeasureRelationDA;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.util.stream.Collectors;

public class CreditNoteRequestController extends EditController {

    private final CreditNoteRequestDA oCreditNoteRequestDA = new CreditNoteRequestDA();
    @FXML
    private TextField txtInvoiceID;
    @FXML
    private MenuItem cmiSelectInvoice;
    @FXML
    private TextField txtCreditNoteRequestID;
    @FXML
    private DatePicker dtpRequestDate;
    @FXML
    private ComboBox cboRequestStatus;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextArea txaAmountWords;
    @FXML
    private TextArea txaNotes;
    @FXML
    private TextField txtCustomerName, txtInvoiceAmount;
    @FXML
    private TableView<CreditNoteRequestDetailsDA> tblCreditNoteRequestDetails;
    @FXML
    private MenuItem cmiSelectInvoiceDetails;
    private final InvoiceDA oInvoiceDA = new InvoiceDA();
    InvoiceDetailsDA oInvoiceDetailsDA = new InvoiceDetailsDA();
    private Invoice invoice;
    @FXML
    private TableColumn<CreditNoteRequestDetailsDA, Integer> tbcCreditNoteRequestDetailsBaseQuantity;
    @FXML
    private TableColumn<CreditNoteRequestDetailsDA, String> tbcCreditNoteRequestDetailsUnitMeasure;
    @FXML
    private TableColumn<CreditNoteRequestDetailsDA, Integer> tbcCreditNoteRequestDetailsMeasureSize;
    @FXML
    private TableColumn<CreditNoteRequestDetailsDA, Integer> tbcCreditNoteRequestDetailsQuantity;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            cboRequestStatus.setItems(FXCollections.observableArrayList(RequestStatus.values()));
            validateNumber(txtAmount);
            formatValue(txtAmount);
            tblCreditNoteRequestDetails.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            setTableEditable(tblCreditNoteRequestDetails);
            cmiSelectInvoiceDetails.setOnAction(e -> loadCreditNoteRequestDetails());
            this.primaryKeyControl = txtCreditNoteRequestID;
            this.dbAccess = oCreditNoteRequestDA;
            this.restrainColumnConstraint = false;
            //this.minSize = 360;

            txtInvoiceID.focusedProperty().addListener((ov, t, t1) -> {
                if (t) {
                    this.setNextCreditNoteRequestID();
                }
            });
            this.cboRequestStatus.setValue(RequestStatus.Pending);
            this.cboRequestStatus.disableProperty().set(true);
            setCreditNoteRequestDetailsBaseQuantity();
            selectItem(FinanceNavigate.MAIN_CLASS, cmiSelectInvoice, oInvoiceDA, "Invoice", "Invoice", txtInvoiceID, true);
            tblCreditNoteRequestDetails.setOnMouseClicked(e -> {
                addRow(tblCreditNoteRequestDetails, new CreditNoteRequestDetailsDA(), true);
                final TablePosition<CreditNoteRequestDetailsDA, String> focusedCell = tblCreditNoteRequestDetails
                        .focusModelProperty().get().focusedCellProperty().get();
                if (focusedCell.getTableColumn() == tbcCreditNoteRequestDetailsUnitMeasure) {
                    loadUnitMeasure();
                }

            });
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void save() {
        try {
            this.editSuccessful = false;
            String creditNoteRequestID = getText(txtCreditNoteRequestID, "Credit Note RequestID");
            LocalDate requestDate = getDate(dtpRequestDate, "Request Date");
            RequestStatus requestStatus = (RequestStatus) getSelectedValue(cboRequestStatus, "RequestStatus");
            double amount = getDouble(txtAmount, "Amount");
            String amountWords = getText(txaAmountWords, "AmountWords");
            String notes = getText(txaNotes, "Notes");
            List<CreditNoteRequestDetailsDA> creditNoteRequestDetailsDAs = tblCreditNoteRequestDetails.getItems();
            creditNoteRequestDetailsDAs.removeIf((p) -> p.getItemID()== null);

            CreditNoteRequestDA creditNoteRequestDA = new CreditNoteRequestDA(invoice, creditNoteRequestID, requestDate, requestStatus, amount, amountWords, notes);
            creditNoteRequestDetailsDAs.forEach(e -> {
                e.setCreditNoteRequest(creditNoteRequestDA.getCreditNoteRequest());
            });
            creditNoteRequestDA.setCreditNoteRequestDetailsDAs(creditNoteRequestDetailsDAs);
            String buttonText = btnSave.getText();
            if (buttonText.equalsIgnoreCase(FormMode.Save.name())) {
                creditNoteRequestDA.save();
                message("Saved Successfully");
                clear();
                addRow(tblCreditNoteRequestDetails, new CreditNoteRequestDetailsDA());
                this.setNextCreditNoteRequestID();
            } else if (buttonText.equalsIgnoreCase(FormMode.Update.name())) {
                creditNoteRequestDA.update();
                message("Updated Successfully");
            }
            this.dbAccess = creditNoteRequestDA;
            this.editSuccessful = true;
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void delete() {
        try {
            String creditNoteRequestID = getText(txtCreditNoteRequestID, "Credit Note RequestID");
            CreditNoteRequestDA creditNoteRequestDA = oCreditNoteRequestDA.get(creditNoteRequestID);
            if (!warningOk("Confirm Delete", "You are about to delete a record with ID: " + creditNoteRequestID + " Are you sure you want to continue?", "Remember this action cannot be un done")) {
                return;
            }
            if (creditNoteRequestDA.delete()) {
                message("Deleted Successfully");
            }
        } catch (Exception e) {
            errorMessage(e);
        }
    }

    @Override
    public void loadData() {
        try {
            String creditNoteRequestID = getText(txtCreditNoteRequestID, "Credit Note RequestID");
            CreditNoteRequestDA creditNoteRequestDA = oCreditNoteRequestDA.get(creditNoteRequestID);
            RequestStatus rStatus = (RequestStatus) creditNoteRequestDA.getRequestStatus(); 
            
            txtCreditNoteRequestID.setText(creditNoteRequestDA.getCreditNoteRequestID());
            dtpRequestDate.setValue((LocalDate) creditNoteRequestDA.getRequestDate());
            cboRequestStatus.setValue(creditNoteRequestDA.getRequestStatus());
            txtAmount.setText(formatNumber(creditNoteRequestDA.getAmount()));
            txaAmountWords.setText(creditNoteRequestDA.getAmountWords());
            txaNotes.setText(creditNoteRequestDA.getNotes());
            txtInvoiceID.setText(creditNoteRequestDA.getInvoiceID());
            txtCustomerName.setText(creditNoteRequestDA.getBillToName());
            txtInvoiceAmount.setText(formatNumber(creditNoteRequestDA.getInvoice().getAmount()));
            this.invoice = creditNoteRequestDA.getInvoice();
             this.btnDelete.disableProperty().set(!rStatus.equals(RequestStatus.Pending));
            tblCreditNoteRequestDetails.setItems(FXCollections.observableArrayList(creditNoteRequestDA.getCreditNoteRequestDetailsDAs()));
            addRow(tblCreditNoteRequestDetails, new CreditNoteRequestDetailsDA(), true);

        } catch (Exception e) {
            errorMessage(e);
        }

    }

    private void setNextCreditNoteRequestID() {
        try {
            if (btnSave.getText().equalsIgnoreCase(FormMode.Save.name())) {

                String invoiceID = getText(txtInvoiceID);
                if (invoiceID.isBlank()) {
                    return;
                }
                this.invoice = oInvoiceDA.getInvoice(invoiceID);
                if (invoice != null) {
                    txtCreditNoteRequestID.setText(oCreditNoteRequestDA.getNextCreditNoteRequestID(oCreditNoteRequestDA.getNextIdHelper(invoice), invoiceID));
                    txtCustomerName.setText(invoice.getBillTo().getDisplayKey());
                    this.txtInvoiceAmount.setText(formatNumber(invoice.getAmount()));
                    addRow(tblCreditNoteRequestDetails, new CreditNoteRequestDetailsDA());
                } else {
                    txtCustomerName.clear();
                    txtInvoiceAmount.clear();
                    txtCreditNoteRequestID.clear();

                }
            }
        } catch (Exception e) {
            errorMessage(e);
        }
    }

    private void setCreditNoteRequestDetailsBaseQuantity() {
        tbcCreditNoteRequestDetailsBaseQuantity.setCellFactory(EditCell.IntegerTableColumn());
        tbcCreditNoteRequestDetailsBaseQuantity.setOnEditCommit(event -> {
            final int value = event.getNewValue() != null ? event.getNewValue()
                    : event.getOldValue();
            ((CreditNoteRequestDetailsDA) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow()))
                    .setBaseQuantity(value);
            tblCreditNoteRequestDetails.refresh();
            calculateAmount();
        });
    }

    public void calculateAmount() {
        double totalAmount = tblCreditNoteRequestDetails.getItems().stream().mapToDouble(CreditNoteRequestDetailsDA::getAmount).sum();
        txtAmount.setText(formatNumber(totalAmount));
        txaAmountWords.setText(NumberToWord.toWords(totalAmount));

    }

    private void loadCreditNoteRequestDetails() {
        try {
            ObservableList<CreditNoteRequestDetailsDA> selectedItems = tblCreditNoteRequestDetails.getSelectionModel().getSelectedItems();
            if (selectedItems.isEmpty() || selectedItems.size() > 1) {
                return;
            }

            List<InvoiceDetailsDA> invoiceDetailsDAs = oInvoiceDetailsDA.getInvoiceDetailsDAByInvoice(invoice);
            InvoiceDetailsDA invoiceDetailsDA = (InvoiceDetailsDA) getSelectedItem(mainClass, new InvoiceDetailsDA(), invoiceDetailsDAs, "Invoice Details", "Invoice Details", 0, 0, tblCreditNoteRequestDetails, restrainColumnConstraint);

            if (invoiceDetailsDA == null) {
                return;
            }

            List<InvoiceDetailsDA> invoiceDetailsDAsList = tblCreditNoteRequestDetails.getItems()
                    .stream()
                    .map((p) -> p.getInvoiceDetailsDA())
                    .collect(Collectors.toList());
            final TablePosition<CreditNoteRequestDetailsDA, String> focusedCell = tblCreditNoteRequestDetails
                    .focusModelProperty().get().focusedCellProperty().get();
            if (invoiceDetailsDAsList.contains(invoiceDetailsDA)) {
                message("The Item " + invoiceDetailsDA.getItem().getItemName() + " is already selected");
                tblCreditNoteRequestDetails.getItems().remove(focusedCell.getRow());
                return;
            }

            CreditNoteRequestDetailsDA selectedCreditNoteRequestDetailsDA = tblCreditNoteRequestDetails.getItems().get(focusedCell.getRow());
            selectedCreditNoteRequestDetailsDA.setInvoiceDetails(invoiceDetailsDA.getInvoiceDetails());

            tblCreditNoteRequestDetails.getItems().set(focusedCell.getRow(), selectedCreditNoteRequestDetailsDA);
            tblCreditNoteRequestDetails.edit(focusedCell.getRow(), focusedCell.getTableColumn());
            calculateAmount();

        } catch (Exception e) {
            errorMessage(e);
        }
    }

    private void loadUnitMeasure() {
        try {
            ObservableList<CreditNoteRequestDetailsDA> selectedItems = tblCreditNoteRequestDetails.getSelectionModel().getSelectedItems();
            if (selectedItems.isEmpty() || selectedItems.size() > 1) {
                return;
            }

            final TablePosition<CreditNoteRequestDetailsDA, String> focusedCell = tblCreditNoteRequestDetails
                    .focusModelProperty().get().focusedCellProperty().get();

            if (focusedCell == null) {
                return;
            }

            CreditNoteRequestDetailsDA creditNoteRequestDetailsDA = tblCreditNoteRequestDetails.getItems().get(focusedCell.getRow());

            ItemDA itemDA = creditNoteRequestDetailsDA.getInvoiceDetailsDA().getItemDA();
            if (itemDA == null) {
                return;
            }

            List<MeasureRelationDA> measureRelationDAs = new MeasureGroupDA().get(itemDA.getMeasureGroup().getMeasureGroupID()).getAllMeasureRelationsDAs();

            MeasureRelationDA measureRelationDA = (MeasureRelationDA) getSelectedItem(FinanceNavigate.MAIN_CLASS, new MeasureRelationDA(), measureRelationDAs, "View", "Item", 400, 450, tblCreditNoteRequestDetails, false);

            if (measureRelationDA != null) {
                creditNoteRequestDetailsDA.setUnitMeasure(measureRelationDA.getDisplayKey());
                creditNoteRequestDetailsDA.setMeasureSize(measureRelationDA.getBaseSize());

            }
            tblCreditNoteRequestDetails.refresh();
            calculateAmount();

        } catch (IOException e) {
            errorMessage(e);
        } catch (Exception e) {
            errorMessage(e);
        }
    }

}