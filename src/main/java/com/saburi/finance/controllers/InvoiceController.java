/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.controllers;

import com.itextpdf.text.PageSize;
import com.saburi.common.controllers.EditController;
import com.saburi.common.dbaccess.LookupDataDA;
import com.saburi.common.utils.CommonEnums.EntryModes;
import com.saburi.common.utils.CommonEnums.NumericDataTypes;
import com.saburi.common.utils.CommonEnums.Rights;
import com.saburi.common.utils.CurrentUser;
import com.saburi.common.utils.EditCell;
import static com.saburi.common.utils.FXUIUtils.addRow;
import static com.saburi.common.utils.FXUIUtils.addRowOnce;
import static com.saburi.common.utils.FXUIUtils.errorMessage;
import static com.saburi.common.utils.FXUIUtils.formatDatePicker;
import static com.saburi.common.utils.FXUIUtils.formatValue;
import static com.saburi.common.utils.FXUIUtils.getDate;
import static com.saburi.common.utils.FXUIUtils.getDouble;
import static com.saburi.common.utils.FXUIUtils.getEntity;
import static com.saburi.common.utils.FXUIUtils.getSelectedItem;
import static com.saburi.common.utils.FXUIUtils.getSelectedValue;
import static com.saburi.common.utils.FXUIUtils.getText;
import static com.saburi.common.utils.FXUIUtils.loadDBEntities;
import static com.saburi.common.utils.FXUIUtils.message;
import static com.saburi.common.utils.FXUIUtils.selectItem;
import static com.saburi.common.utils.FXUIUtils.selectPrevious;
import static com.saburi.common.utils.FXUIUtils.validateNumber;
import static com.saburi.common.utils.FXUIUtils.warningOK;
import static com.saburi.common.utils.FXUIUtils.warningOk;
import com.saburi.common.utils.JavaFXPDF;
import com.saburi.common.utils.NumberToWord;
import com.saburi.common.utils.PrintableColumn;
import com.saburi.common.utils.Utilities.FormMode;
import static com.saburi.common.utils.Utilities.defortNumberOptional;
import static com.saburi.common.utils.Utilities.formatDate;
import static com.saburi.common.utils.Utilities.formatNumber;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import com.saburi.finance.dbaccess.InvoiceDA;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import javafx.scene.control.ComboBox;
import com.saburi.finance.utils.FinanceEnums.InvoiceTypes;
import javafx.collections.FXCollections;
import javafx.scene.control.MenuItem;
import com.saburi.finance.dbaccess.CustomerDA;
import com.saburi.finance.entities.Customer;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;
import java.util.List;
import com.saburi.finance.dbaccess.InvoiceDetailsDA;
import com.saburi.finance.dbaccess.ItemDA;
import com.saburi.finance.dbaccess.MeasureGroupDA;
import com.saburi.finance.dbaccess.MeasureRelationDA;
import com.saburi.finance.entities.Invoice;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.collections.ObservableList;
import com.saburi.finance.entities.Item;
import com.saburi.finance.utils.FinanceNavigate;
import java.io.IOException;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import com.saburi.finance.utils.FinanceObjectNames;
import com.saburi.finance.utils.InvoiceSourceNames;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import static com.saburi.common.utils.Utilities.getInteger;

public class InvoiceController extends EditController {

    private final InvoiceDA oInvoiceDA = new InvoiceDA();
    @FXML
    private TextField txtInvoiceID;
    @FXML
    private DatePicker dtpInvoiceDate;
    @FXML
    private ComboBox cboInvoiceType;
    @FXML
    private ComboBox cboSellTo;
    @FXML
    private MenuItem cmiSelectSellTo;
    @FXML
    private ComboBox cboBillTo;

    @FXML
    private TextField txtAmount;
    @FXML
    private TextArea txaAmountWords;
    @FXML
    private TableView<InvoiceDetailsDA> tblInvoiceDetails;
    @FXML
    private MenuItem cmiSelectItem;
    private final CustomerDA oCustomerDA = new CustomerDA();
    ItemDA oItemDA = new ItemDA();
    @FXML
    private TableColumn<InvoiceDetailsDA, String> tbcInvoiceDetailsItemID, tbcInvoiceDetailsItemDisplay;
    @FXML
    private TableColumn<InvoiceDetailsDA, String> tbcInvoiceDetailsBaseQuantity;
    @FXML
    private TableColumn<InvoiceDetailsDA, String> tbcInvoiceDetailsUnitMeasure;

    @FXML
    private TableColumn<InvoiceDetailsDA, String> tbcInvoiceDetailsQuantity;
    @FXML
    private TableColumn<InvoiceDetailsDA, String> tbcInvoiceDetailsUnitPrice;
    @FXML
    private TableColumn<InvoiceDetailsDA, String> tbcInvoiceDetailsDiscount, tbcInvoiceDetailsAmount;
    @FXML
    private TableColumn<InvoiceDetailsDA, String> tbcInvoiceDetailsLocation;
    private Customer selectedBillTo;
    private final LookupDataDA oLookupDataDA = new LookupDataDA();
    private String invoiceSource;
    private EntryModes entryMode;
    private boolean popedUp;
    private InvoiceDA popedUpInvoiceDA;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cboInvoiceType.setItems(FXCollections.observableArrayList(InvoiceTypes.values()));
            loadDBEntities(oCustomerDA.getSellableCustomers(), cboSellTo);
            loadDBEntities(cboBillTo);
            validateNumber(txtAmount);
            formatValue(txtAmount);
            formatDatePicker(dtpInvoiceDate);
            tblInvoiceDetails.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            setTableEditable();
            addRow(tblInvoiceDetails, new InvoiceDetailsDA());
            cmiSelectItem.setOnAction(e -> loadItem());
            this.primaryKeyControl = txtInvoiceID;
            this.dbAccess = oInvoiceDA;
            this.restrainColumnConstraint = false;
            this.prefSize = 850;
            this.setNextInvoiceID();
            setInvoiceDetailsItem();
            setInvoiceDetailsBaseQuantity();
            setInvoiceDetailsUnitMeasure();
            setInvoiceDetailsDiscount();
            selectItem(FinanceNavigate.MAIN_CLASS, cmiSelectSellTo, oCustomerDA, oCustomerDA.getSellableCustomerDAs(), "Customer", "Sell To", cboSellTo, true);
            cboBillTo.setOnAction(e -> {
                this.selectedBillTo = ((Customer) getEntity(cboBillTo));
                InvoiceTypes invoiceType = (InvoiceTypes) cboInvoiceType.getValue();
                if (invoiceType == null) {
                    return;
                }
                if (invoiceType.equals(InvoiceTypes.Direct)) {
                    tblInvoiceDetails.getItems().forEach(bi -> {
                        ItemDA itemDA = bi.getItemDA();
                        if (itemDA != null) {
                            if (itemDA.getItemID() != null) {
                                setPrices(bi);
                            }
                        }

                    });
                } else {
                    tblInvoiceDetails.setItems(FXCollections.observableList(new CustomerDA(selectedBillTo).getToCreatePendingInvoiceDetailsDA()));

                }

                calculateAmount();
                tblInvoiceDetails.refresh();

            });
            tblInvoiceDetails.setOnMouseClicked(e -> {
                final TablePosition<InvoiceDetailsDA, String> focusedCell = tblInvoiceDetails
                        .focusModelProperty().get().focusedCellProperty().get();
                if (focusedCell.getTableColumn() == tbcInvoiceDetailsUnitMeasure) {
                    loadUnitMeasure();
                } else if (focusedCell.getTableColumn() == tbcInvoiceDetailsLocation) {
                    loadLocation();
                }

            });

            cboInvoiceType.setOnAction(e -> setTableColumns());
            dtpInvoiceDate.setValue(LocalDate.now());

            cboSellTo.setOnAction(e -> {
                try {
                    cboBillTo.getItems().clear();
                    Customer customer = (Customer) getEntity(cboSellTo);
                    if (customer == null) {
                        return;
                    }
                    CustomerDA customerDA = oCustomerDA.get(customer.getCustomerID());
                    loadDBEntities(customerDA.getAllSponseringCustomers(), cboBillTo);
                    cboBillTo.setValue(customerDA.getTranDefaulSponser());
                    cmiSelectItem.disableProperty().set(btnSave.getText().equalsIgnoreCase(FormMode.Print.name()));
                } catch (Exception ex) {
                    errorMessage(ex);
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
            String invoiceID = getText(txtInvoiceID, "Invoice ID");
            LocalDate invoiceDate = getDate(dtpInvoiceDate, "Invoice Date");
            InvoiceTypes invoiceType = (InvoiceTypes) getSelectedValue(cboInvoiceType, "Invoice Type",
                    btnSave.getText().equalsIgnoreCase(FormMode.Save.name()));
            Customer sellTo = (Customer) getEntity(cboSellTo, "Sell To");
            Customer billTo = (Customer) getEntity(cboBillTo, "Bill To");
            double amount = getDouble(txtAmount, "Amount");
            String amountWords = getText(txaAmountWords, "Amount Words");
            if (this.invoiceSource == null) {
                this.invoiceSource = invoiceType.equals(InvoiceTypes.Direct) ? InvoiceSourceNames.DIRECT : InvoiceSourceNames.SALES_ORDER;
            }
            EntryModes entryModes = btnSave.getText().equalsIgnoreCase(FormMode.Save.name()) ? EntryModes.Manual : this.entryMode;
            List<InvoiceDetailsDA> invoiceDetailsDAs = tblInvoiceDetails.getItems();
            invoiceDetailsDAs.removeIf((p) -> p.getItem() == null);
            if (invoiceDetailsDAs.size() < 0) {
                throw new Exception("You must select atleast one item");
            }
            if (popedUp) {
                this.popedUpInvoiceDA.setInvoiceDate(invoiceDate);
                this.popedUpInvoiceDA.setAmount(amount);
                this.popedUpInvoiceDA.setAmountWords(amountWords);
                this.popedUpInvoiceDA.setEntryMode(EntryModes.Manual);
                popedUpInvoiceDA.setInvoiceDetailsDAs(invoiceDetailsDAs);
                this.btnClose.fire();
                return;
            }
            InvoiceDA invoiceDA = new InvoiceDA(invoiceID, invoiceDate, sellTo, billTo, amount, amountWords, invoiceSource, entryModes);
            invoiceDetailsDAs.forEach(e -> {
                e.setInvoice((Invoice) invoiceDA.getInvoice());

            });
            invoiceDA.setInvoiceDetailsDAs(invoiceDetailsDAs);

//            Printing data
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("Invoice ID", invoiceID);
            map.put("Sale To Customer", sellTo.getDisplayKey());
            map.put("Bill To Customer", billTo.getDisplayKey());
            map.put("Invoice Date", formatDate(invoiceDate));
            map.put("Amount", formatNumber(amount));

            JavaFXPDF javaFXPDF = new JavaFXPDF("Invoice", PageSize.A4, map, 1, 40, 200, 100, 20, 3, 70,
                    tblInvoiceDetails, Arrays.asList(new PrintableColumn(tbcInvoiceDetailsItemID),
                            new PrintableColumn(tbcInvoiceDetailsItemDisplay, "Item Name"),
                            new PrintableColumn(tbcInvoiceDetailsQuantity, NumericDataTypes.INTEGER),
                            new PrintableColumn(tbcInvoiceDetailsUnitPrice, NumericDataTypes.DOUBLE),
                            new PrintableColumn(tbcInvoiceDetailsDiscount, NumericDataTypes.DOUBLE, true),
                            new PrintableColumn(tbcInvoiceDetailsAmount, NumericDataTypes.DOUBLE, true)),
                    "Invoice for Customer: " + billTo.getDisplayKey(), new String[]{"Customer", "Checked By"},
                    "Net Amount In Words: " + amountWords);

            String buttonText = btnSave.getText();
            if (buttonText.equalsIgnoreCase(FormMode.Save.name())) {
                invoiceDA.save();
                if (warningOK("Operation Successful", "Would like to print")) {

                    javaFXPDF.makePrintablePDFDocument();
                }
                clear();
                this.setNextInvoiceID();
                this.invoiceSource = null;
            } else if (buttonText.equalsIgnoreCase(FormMode.Update.name())) {
                invoiceDA.update();
                message("Updated Successfully");
            } else if (buttonText.equalsIgnoreCase(FormMode.Print.name())) {
                javaFXPDF.modifyTitle();
                javaFXPDF.makePrintablePDFDocument();
            }
            this.dbAccess = invoiceDA;
            this.editSuccessful = true;

        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void delete() {
        try {
            String invoiceID = getText(txtInvoiceID, "Invoice ID");
            InvoiceDA invoiceDA = oInvoiceDA.get(invoiceID);
            if (!warningOk("Confirm Delete", "You are about to delete a record with ID: " + invoiceID + " Are you sure you want to continue?", "Remember this action cannot be un done")) {
                return;
            }
            if (invoiceDA.delete()) {
                message("Deleted Successfully");
                this.clear();
            }
        } catch (Exception e) {
            errorMessage(e);
        }
    }

    @Override
    public void loadData() {
        try {
            String invoiceID = getText(txtInvoiceID, "Invoice ID");
            InvoiceDA invoiceDA = oInvoiceDA.get(invoiceID);
            txtInvoiceID.setText(invoiceDA.getInvoiceID());
            dtpInvoiceDate.setValue((LocalDate) invoiceDA.getInvoiceDate());
            this.entryMode = (EntryModes) invoiceDA.getEntryMode();
            this.invoiceSource = invoiceDA.getInvoiceSource();
            cboSellTo.setValue(invoiceDA.getSellTo());
            Customer billTo = invoiceDA.getBillTo();
            List<Customer> customers = cboBillTo.getItems();
            if (!customers.contains(billTo)) {
                cboBillTo.getItems().add(billTo);
            }
            cboBillTo.setValue(billTo);
            txtAmount.setText(formatNumber(invoiceDA.getAmount()));
            txaAmountWords.setText(invoiceDA.getAmountWords());
            tblInvoiceDetails.setItems(FXCollections.observableArrayList(invoiceDA.getInvoiceDetailsDAs()));
            addRow(tblInvoiceDetails, new InvoiceDetailsDA());

        } catch (Exception e) {
            errorMessage(e);
        }

    }

    private void setNextInvoiceID() {
        try {
            if (btnSave.getText().equalsIgnoreCase(FormMode.Save.name())) {
                txtInvoiceID.setText(oInvoiceDA.getNextInvoiceID(oInvoiceDA.getNextIdHelper()));
            }
        } catch (Exception e) {
            errorMessage(e);
        }
    }

    private void setInvoiceDetailsItem() {
        tbcInvoiceDetailsItemID.setCellFactory(EditCell.StringTableColumn());
        tbcInvoiceDetailsItemID.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null ? event.getNewValue()
                    : event.getOldValue();
            Item item = oItemDA.getItem(value);
            if (item == null) {
                Platform.runLater(() -> {
                    message("No Item with Id " + value + " found");
                    tblInvoiceDetails.getItems().set(event.getTablePosition().getRow(), new InvoiceDetailsDA());
                    tblInvoiceDetails.refresh();

                });
                return;
            }
            if (tblInvoiceDetails.getItems()
                    .parallelStream()
                    .filter((p) -> p.getItemID() != null)
                    .filter(p -> p.getItemID().equals(value))
                    .collect(Collectors.toList())
                    .size() > 1) {
                Platform.runLater(() -> {
                    message("Item : " + item.getItemName() + " is already selected");
                    tblInvoiceDetails.getItems().set(event.getTablePosition().getRow(), new InvoiceDetailsDA());
                    tblInvoiceDetails.refresh();
                });
                return;

            }
            InvoiceDetailsDA invoiceDetailsDA = event.getTableView().getItems().get(event.getTablePosition().getRow());
            invoiceDetailsDA.setItem(item);
            setPrices(invoiceDetailsDA);
            calculateAmount();
            tblInvoiceDetails.refresh();
            addRowOnce(tblInvoiceDetails, new InvoiceDetailsDA());
        });
    }

    private void setInvoiceDetailsBaseQuantity() {
        tbcInvoiceDetailsBaseQuantity.setCellFactory(EditCell.StringTableColumn());
        tbcInvoiceDetailsBaseQuantity.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null ? event.getNewValue()
                    : event.getOldValue();
            ((InvoiceDetailsDA) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow()))
                    .setBaseQuantity(getInteger(value));
            tblInvoiceDetails.refresh();
            addRowOnce(tblInvoiceDetails, new InvoiceDetailsDA());
            calculateAmount();
        });
    }

    private void setInvoiceDetailsUnitMeasure() {
        tbcInvoiceDetailsUnitMeasure.setCellFactory(EditCell.StringTableColumn());
        tbcInvoiceDetailsUnitMeasure.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null ? event.getNewValue()
                    : event.getOldValue();
            ((InvoiceDetailsDA) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow()))
                    .setUnitMeasure(value);
            tblInvoiceDetails.refresh();
            addRowOnce(tblInvoiceDetails, new InvoiceDetailsDA());
        });
    }

    private void setInvoiceDetailsDiscount() {
        tbcInvoiceDetailsDiscount.setCellFactory(EditCell.StringTableColumn());
        tbcInvoiceDetailsDiscount.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null ? event.getNewValue()
                    : event.getOldValue();
            ((InvoiceDetailsDA) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow()))
                    .setDiscount(defortNumberOptional(value));
            tblInvoiceDetails.refresh();
            addRowOnce(tblInvoiceDetails, new InvoiceDetailsDA());
            calculateAmount();
        });
    }

    private void loadItem() {
        try {
            ObservableList<InvoiceDetailsDA> selectedItems = tblInvoiceDetails.getSelectionModel().getSelectedItems();
            if (selectedItems.isEmpty() || selectedItems.size() > 1) {
                return;
            }
            ItemDA itemDA = (ItemDA) getSelectedItem(FinanceNavigate.MAIN_CLASS, new ItemDA(), "Item", "Items", 400, 450, tblInvoiceDetails, false);

            if (itemDA == null) {
                return;
            }

            if (tblInvoiceDetails.getItems().stream().map(InvoiceDetailsDA::getItemDA).collect(Collectors.toList()).contains(itemDA)) {
                throw new Exception("The record with id: " + itemDA.getId() + " is already selected");
            }

            final TablePosition<InvoiceDetailsDA, String> focusedCell = tblInvoiceDetails
                    .focusModelProperty().get().focusedCellProperty().get();
            InvoiceDetailsDA invoiceDetailsDA = tblInvoiceDetails.getItems().get(focusedCell.getRow());
            invoiceDetailsDA.setItem((Item) itemDA.getDBEntity());
            setPrices(invoiceDetailsDA);
            calculateAmount();
            addRow(tblInvoiceDetails, new InvoiceDetailsDA());
            tblInvoiceDetails.refresh();

        } catch (IOException e) {
            errorMessage(e);
        } catch (Exception e) {
            errorMessage(e);
        }
    }

    public void calculateAmount() {
        double totalAmount = tblInvoiceDetails.getItems().stream().mapToDouble(InvoiceDetailsDA::getAmount).sum();
        txtAmount.setText(formatNumber(totalAmount));
        txaAmountWords.setText(NumberToWord.toWords(totalAmount));

    }

    private void setPrices(InvoiceDetailsDA invoiceDetailsDA) {
        invoiceDetailsDA.setUnitPrice(invoiceDetailsDA.getItemDA().getUnitPrice(selectedBillTo));
        invoiceDetailsDA.setDiscount(invoiceDetailsDA.getItemDA().getDiscount(selectedBillTo));
    }

    private void setTableColumns() {
        InvoiceTypes invoiceType = (InvoiceTypes) cboInvoiceType.getValue();
        tblInvoiceDetails.getItems().clear();
        if (invoiceType.equals(InvoiceTypes.Direct)) {
            tbcInvoiceDetailsBaseQuantity.editableProperty().set(false);
            tbcInvoiceDetailsUnitMeasure.editableProperty().set(false);
            tbcInvoiceDetailsItemID.editableProperty().set(true);
            tbcInvoiceDetailsQuantity.editableProperty().set(true);
            addRow(tblInvoiceDetails, new InvoiceDetailsDA());
        } else {
            tbcInvoiceDetailsItemID.editableProperty().set(false);
            tbcInvoiceDetailsBaseQuantity.editableProperty().set(false);
            tbcInvoiceDetailsUnitMeasure.editableProperty().set(false);
            tbcInvoiceDetailsQuantity.editableProperty().set(false);
            tbcInvoiceDetailsUnitPrice.editableProperty().set(false);
            cmiSelectItem.setVisible(false);
        }

        txtAmount.clear();
        txaAmountWords.clear();

    }

    public void setTableEditable() {
        tblInvoiceDetails.setEditable(true);
        // allows the individual cells to be selected
        tblInvoiceDetails.getSelectionModel().cellSelectionEnabledProperty().set(true);
        // when character or numbers pressed it will start edit in editable
        // fields
        tblInvoiceDetails.setOnKeyPressed(event -> {
            if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
                final TablePosition<InvoiceDetailsDA, ?> focusedCell = tblInvoiceDetails
                        .focusModelProperty().get().focusedCellProperty().get();
                tblInvoiceDetails.edit(focusedCell.getRow(), focusedCell.getTableColumn());

            } else if (event.getCode() == KeyCode.RIGHT
                    || event.getCode() == KeyCode.TAB) {
                tblInvoiceDetails.getSelectionModel().selectNext();
                event.consume();
            } else if (event.getCode() == KeyCode.LEFT) {
                // work around due to
                // TableView.getSelectionModel().selectPrevious() due to a bug
                // stopping it from working on
                // the first column in the last row of the table
                selectPrevious(tblInvoiceDetails);
                event.consume();
            } else if (event.getCode() == KeyCode.DELETE) {
                tblInvoiceDetails.getItems().removeAll(tblInvoiceDetails.getSelectionModel().getSelectedItems());
                calculateAmount();
            }
        });
    }

    private void loadUnitMeasure() {
        try {
            if (!this.btnSave.getText().equalsIgnoreCase(FormMode.Save.name())) {
                return;
            }
            ObservableList<InvoiceDetailsDA> selectedItems = tblInvoiceDetails.getSelectionModel().getSelectedItems();
            if (selectedItems.isEmpty() || selectedItems.size() > 1) {
                return;
            }

            final TablePosition<InvoiceDetailsDA, String> focusedCell = tblInvoiceDetails
                    .focusModelProperty().get().focusedCellProperty().get();

            if (focusedCell == null) {
                return;
            }

            InvoiceDetailsDA invoiceDetailsDa = tblInvoiceDetails.getItems().get(focusedCell.getRow());

            ItemDA itemDA = invoiceDetailsDa.getItemDA();
            if (itemDA == null) {
                return;
            }
            List<MeasureRelationDA> measureRelationDAs = new MeasureGroupDA().get(itemDA.getMeasureGroup().getMeasureGroupID()).getAllMeasureRelationsDAs();

            MeasureRelationDA measureRelationDA = (MeasureRelationDA) getSelectedItem(FinanceNavigate.MAIN_CLASS, new MeasureRelationDA(), measureRelationDAs, "View", "Item", 400, 450, tblInvoiceDetails, false);

            if (measureRelationDA != null) {
                invoiceDetailsDa.setUnitMeasure(measureRelationDA.getDisplayKey());
                invoiceDetailsDa.setMeasureSize(measureRelationDA.getBaseSize());

            }

            tblInvoiceDetails.refresh();
            calculateAmount();

        } catch (IOException e) {
            errorMessage(e);
        } catch (Exception e) {
            errorMessage(e);
        }
    }

    private void loadLocation() {
        try {
            ObservableList<InvoiceDetailsDA> selectedItems = tblInvoiceDetails.getSelectionModel().getSelectedItems();
            if (selectedItems.isEmpty() || selectedItems.size() > 1) {
                return;
            }

            final TablePosition<InvoiceDetailsDA, String> focusedCell = tblInvoiceDetails
                    .focusModelProperty().get().focusedCellProperty().get();

            if (focusedCell == null) {
                return;
            }

            InvoiceDetailsDA invoiceDetailsDa = tblInvoiceDetails.getItems().get(focusedCell.getRow());

            ItemDA itemDA = invoiceDetailsDa.getItemDA();
            if (itemDA == null) {
                return;
            }
            List<LookupDataDA> lookupDataDAs = LookupDataDA.getLookupDataDAs(oLookupDataDA.getLookupDataByObjectID(FinanceObjectNames.LOCATION));

            LookupDataDA lookupDataDA = (LookupDataDA) getSelectedItem(FinanceNavigate.MAIN_CLASS, new LookupDataDA(), lookupDataDAs, "View", "Item", 400, 450, tblInvoiceDetails, false);

            if (lookupDataDA != null) {
                invoiceDetailsDa.setLocation(lookupDataDA.getLookupData());

            }

            tblInvoiceDetails.refresh();

        } catch (IOException e) {
            errorMessage(e);
        } catch (Exception e) {
            errorMessage(e);
        }
    }

    public void loadInvoiceData(InvoiceDA invoiceDA) {
        try {
            this.init("Invoice", FormMode.Save);
            txtInvoiceID.setText(invoiceDA.getInvoiceID());
            dtpInvoiceDate.setValue((LocalDate) invoiceDA.getInvoiceDate());
            this.entryMode = (EntryModes) invoiceDA.getEntryMode();
            this.invoiceSource = invoiceDA.getInvoiceSource();
            cboSellTo.setValue(invoiceDA.getSellTo());
            cboInvoiceType.setValue(InvoiceTypes.Popup);
            Customer billTo = invoiceDA.getBillTo();
            List<Customer> customers = cboBillTo.getItems();
            if (!customers.contains(billTo)) {
                cboBillTo.getItems().add(billTo);
            }
            cboBillTo.setValue(billTo);
            txtAmount.setText(formatNumber(invoiceDA.getAmount()));
            txaAmountWords.setText(invoiceDA.getAmountWords());
            tblInvoiceDetails.setItems(FXCollections.observableArrayList(invoiceDA.getInvoiceDetailsDAs()));
            tbcInvoiceDetailsBaseQuantity.editableProperty().set(false);
            tbcInvoiceDetailsUnitMeasure.editableProperty().set(false);
            tbcInvoiceDetailsItemID.editableProperty().set(false);
            tbcInvoiceDetailsQuantity.editableProperty().set(false);
            tbcInvoiceDetailsDiscount.editableProperty().set(false);
            tbcInvoiceDetailsUnitPrice.editableProperty().set(false);
            tbcInvoiceDetailsUnitMeasure.editableProperty().set(false);
            cboInvoiceType.setDisable(true);
            cboSellTo.setDisable(true);
            cboBillTo.setDisable(true);
            cmiSelectItem.setVisible(false);
            this.popedUp = true;
            this.popedUpInvoiceDA = invoiceDA;
            this.btnSave.setText("OK");
            CurrentUser.applyRights(btnSave, Rights.Read);

        } catch (Exception e) {
            errorMessage(e);
        }

    }

    public InvoiceDA getPopedUpInvoiceDA() {
        return popedUpInvoiceDA;
    }

}
