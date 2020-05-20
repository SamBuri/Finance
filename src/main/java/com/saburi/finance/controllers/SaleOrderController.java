/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.controllers;

import com.saburi.common.controllers.EditController;
import com.saburi.common.utils.CommonEnums.EntryModes;
import com.saburi.common.utils.CommonEnums.OpenStatus;
import com.saburi.common.utils.EditCell;
import static com.saburi.common.utils.FXUIUtils.addRow;
import static com.saburi.common.utils.FXUIUtils.errorMessage;
import static com.saburi.common.utils.FXUIUtils.formatDatePicker;
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
import static com.saburi.common.utils.FXUIUtils.warningOk;
import com.saburi.common.utils.NumberToWord;
import com.saburi.common.utils.Utilities.FormMode;
import static com.saburi.common.utils.Utilities.defortNumberOptional;
import static com.saburi.common.utils.Utilities.formatNumber;
import static com.saburi.common.utils.Utilities.getInt;
import com.saburi.finance.dbaccess.CustomerDA;
import com.saburi.finance.dbaccess.ItemDA;
import com.saburi.finance.dbaccess.MeasureGroupDA;
import com.saburi.finance.dbaccess.MeasureRelationDA;
import com.saburi.finance.dbaccess.SaleOrderDA;
import com.saburi.finance.dbaccess.SaleOrderDetailDA;
import com.saburi.finance.entities.Customer;
import com.saburi.finance.entities.Item;
import com.saburi.finance.entities.SaleOrder;
import com.saburi.finance.utils.FinanceEnums.InvoiceStatus;
import com.saburi.finance.utils.FinanceNavigate;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class SaleOrderController extends EditController {

    private final SaleOrderDA oSaleOrderDA = new SaleOrderDA();
    @FXML
    private TextField txtSaleOrderID;
    @FXML
    private DatePicker dtpOrderDate;
    @FXML
    private TextArea txaShipAddress;
    @FXML
    private ComboBox cboSellTo;
    @FXML
    private MenuItem cmiSelectSellTo;
    @FXML
    private ComboBox cboBillTo;

    @FXML
    private ComboBox cboStatus;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextArea txaAmountWords;
    @FXML
    private TableView<SaleOrderDetailDA> tblSaleOrderDetails;
    @FXML
    private MenuItem cmiSelectItem;
    private final CustomerDA oCustomerDA = new CustomerDA();
    ;
    ItemDA oItemDA = new ItemDA();
    @FXML
    private TableColumn<SaleOrderDetailDA, String> tbcSaleOrderDetailItemID;
    @FXML
    private TableColumn<SaleOrderDetailDA, String> tbcSaleOrderDetailBaseQuantity;
    @FXML
    private TableColumn<SaleOrderDetailDA, String> tbcSaleOrderDetailUnitMeasure;

    @FXML
    private TableColumn<SaleOrderDetailDA, String> tbcSaleOrderDetailUnitPrice;
    @FXML
    private TableColumn<SaleOrderDetailDA, String> tbcSaleOrderDetailDiscount;
    @FXML
    private Customer selectedBillToDA;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadDBEntities(oCustomerDA.getSellableCustomers(), cboSellTo);
            loadDBEntities(cboBillTo);
            cboStatus.setItems(FXCollections.observableArrayList(OpenStatus.values()));
            formatDatePicker(dtpOrderDate);
            tblSaleOrderDetails.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            setTableEditable();
            addRow(tblSaleOrderDetails, new SaleOrderDetailDA());
            cmiSelectItem.setOnAction(e -> loadItem());
            this.primaryKeyControl = txtSaleOrderID;
            this.dbAccess = oSaleOrderDA;
            this.restrainColumnConstraint = false;
            this.prefSize = 850;
            this.setNextSaleOrderID();
            setSaleOrderDetailItem();
            setSaleOrderDetailBaseQuantity();
            setSaleOrderDetailUnitPrice();
            setSaleOrderDetailDiscount();
            selectItem(FinanceNavigate.MAIN_CLASS, cmiSelectSellTo, oCustomerDA, oCustomerDA.getSellableCustomerDAs(), "Customer", "Customer", 700, 400, cboSellTo, true);
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
                } catch (Exception ex) {
                    errorMessage(ex);
                }

            });

            dtpOrderDate.setValue(LocalDate.now());
            cboBillTo.setOnAction(e -> {
                this.selectedBillToDA = ((Customer) getEntity(cboBillTo));
                tblSaleOrderDetails.getItems().forEach(bi -> {
                    if (bi.getItemDA().getItemID() != null) {
                        setPrices(bi);
                        calculateAmount();
                        tblSaleOrderDetails.refresh();
                    }
                });

            });

            tblSaleOrderDetails.setOnMouseClicked(e -> {
                final TablePosition<SaleOrderDetailDA, String> focusedCell = tblSaleOrderDetails
                        .focusModelProperty().get().focusedCellProperty().get();
                if (focusedCell.getTableColumn() == tbcSaleOrderDetailUnitMeasure) {
                    loadUnitMeasure();
                }

            });

            cboStatus.setValue(OpenStatus.Open);
            cboStatus.disableProperty().set(true);
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void save() {
        try {
            this.editSuccessful = false;
            String saleOrderID = getText(txtSaleOrderID, "Sale Order ID");
            LocalDate orderDate = getDate(dtpOrderDate, "Order Date");
            String shipAddress = getText(txaShipAddress, "Ship Address");
            Customer sellTo = (Customer) getEntity(cboSellTo, "Sell To");
            Customer billTo = (Customer) getEntity(cboBillTo, "Bill To");
            OpenStatus status = (OpenStatus) getSelectedValue(cboStatus, "Status");
            EntryModes entryMode = EntryModes.Manual;
            double amount = getDouble(txtAmount, "Amount");
            String amountWords = getText(txaAmountWords, "Amount Words");
            if (status.equals(OpenStatus.Closed)) {
                throw new Exception("The sales order with id " + saleOrderID + " is already closed. You can't update it");
            }
            List<SaleOrderDetailDA> saleOrderDetailsDAs = tblSaleOrderDetails.getItems();
            saleOrderDetailsDAs.removeIf((p) -> p.getItem() == null);

            SaleOrderDA saleOrderDA = new SaleOrderDA(saleOrderID, orderDate, shipAddress, sellTo, billTo, amount, amountWords, status, entryMode);
            saleOrderDetailsDAs.forEach(e -> {
                e.setSaleOrder((SaleOrder) saleOrderDA.getSaleOrder());
                e.setInvoiceStatus(InvoiceStatus.Pending);
            });
            saleOrderDA.setSaleOrderDetailsDAs(saleOrderDetailsDAs);
            String buttonText = btnSave.getText();
            if (buttonText.equalsIgnoreCase(FormMode.Save.name())) {
                saleOrderDA.save();
                message("Saved Successfully");
                clear();
            } else if (buttonText.equalsIgnoreCase(FormMode.Update.name())) {
                saleOrderDA.update();
                message("Updated Successfully");
            }
            this.dbAccess = saleOrderDA;
            this.editSuccessful = true;
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void delete() {
        try {
            String saleOrderID = getText(txtSaleOrderID, "Sale Order ID");
            SaleOrderDA saleOrderDA = oSaleOrderDA.get(saleOrderID);
            if (!warningOk("Confirm Delete", "You are about to delete a record with ID: " + saleOrderID + " Are you sure you want to continue?", "Remember this action cannot be un done")) {
                return;
            }
            if (saleOrderDA.delete()) {
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
            String saleOrderID = getText(txtSaleOrderID, "Sale Order ID");

            SaleOrderDA saleOrderDA = oSaleOrderDA.get(saleOrderID);
            txtSaleOrderID.setText(saleOrderDA.getSaleOrderID());
            dtpOrderDate.setValue((LocalDate) saleOrderDA.getOrderDate());
            txaShipAddress.setText(saleOrderDA.getShipAddress());
            cboSellTo.setValue(saleOrderDA.getSellTo());
            Customer billTo = saleOrderDA.getBillTo();
            List<Customer> customers = cboBillTo.getItems();
            if (!customers.contains(billTo)) {
                cboBillTo.getItems().add(billTo);
            }
            cboBillTo.setValue(billTo);
            txtAmount.setText(formatNumber(saleOrderDA.getAmount()));
            cboStatus.setValue(saleOrderDA.getStatus());
            txaAmountWords.setText(saleOrderDA.getAmountWords());
            tblSaleOrderDetails.setItems(FXCollections.observableArrayList(saleOrderDA.getSaleOrderDetailsDAs()));
            addRow(tblSaleOrderDetails, new SaleOrderDetailDA());

        } catch (Exception e) {
            errorMessage(e);
        }

    }

    private void setNextSaleOrderID() {
        try {
            if (btnSave.getText().equalsIgnoreCase(FormMode.Save.name())) {
                txtSaleOrderID.setText(oSaleOrderDA.getNextSaleOrderID(oSaleOrderDA.getNextIdHelper()));
            }
        } catch (Exception e) {
            errorMessage(e);
        }
    }

    private void setSaleOrderDetailItem() {
        tbcSaleOrderDetailItemID.setCellFactory(EditCell.StringTableColumn());
        tbcSaleOrderDetailItemID.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null ? event.getNewValue()
                    : event.getOldValue();
            Item item = oItemDA.getItem(value);
            if (item == null) {
                Platform.runLater(() -> {
                    message("No Item with Id " + value + " found");
                    tblSaleOrderDetails.getItems().set(event.getTablePosition().getRow(), new SaleOrderDetailDA());
                    tblSaleOrderDetails.refresh();
                });
                return;
            }

            if (tblSaleOrderDetails.getItems()
                    .parallelStream()
                    .filter((p) -> p.getItemID() != null)
                    .filter(p -> p.getItemID().equals(value))
                    .collect(Collectors.toList())
                    .size() > 1) {
                Platform.runLater(() -> {
                    message("Item : " + item.getItemName() + " is already selected");
                    tblSaleOrderDetails.getItems().set(event.getTablePosition().getRow(), new SaleOrderDetailDA());
                    tblSaleOrderDetails.refresh();
                });
                return;

            }
            SaleOrderDetailDA saleOrderDetailDA = event.getTableView().getItems()
                    .get(event.getTablePosition().getRow());
            saleOrderDetailDA.setItem(item);
            setPrices(saleOrderDetailDA);
            calculateAmount();
            tblSaleOrderDetails.refresh();
            addRow(tblSaleOrderDetails, new SaleOrderDetailDA());
        });
    }

    private void setSaleOrderDetailBaseQuantity() {
        tbcSaleOrderDetailBaseQuantity.setCellFactory(EditCell.StringTableColumn());
        tbcSaleOrderDetailBaseQuantity.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null ? event.getNewValue()
                    : event.getOldValue();
            ((SaleOrderDetailDA) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow()))
                    .setBaseQuantity(getInt(value));
            tblSaleOrderDetails.refresh();
            addRow(tblSaleOrderDetails, new SaleOrderDetailDA());
            this.calculateAmount();
        });
    }

    private void setSaleOrderDetailUnitPrice() {
        tbcSaleOrderDetailUnitPrice.setCellFactory(EditCell.StringTableColumn());
        tbcSaleOrderDetailUnitPrice.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null ? event.getNewValue()
                    : event.getOldValue();
            ((SaleOrderDetailDA) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow()))
                    .setUnitPrice(defortNumberOptional(value));
            tblSaleOrderDetails.refresh();
            addRow(tblSaleOrderDetails, new SaleOrderDetailDA());
        });
        this.calculateAmount();
    }

    private void setSaleOrderDetailDiscount() {
        tbcSaleOrderDetailDiscount.setCellFactory(EditCell.StringTableColumn());
        tbcSaleOrderDetailDiscount.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null ? event.getNewValue()
                    : event.getOldValue();
            ((SaleOrderDetailDA) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow()))
                    .setDiscount(defortNumberOptional(value));
            tblSaleOrderDetails.refresh();
            addRow(tblSaleOrderDetails, new SaleOrderDetailDA());
        });
        this.calculateAmount();
    }

    @Override
    protected void clear() {
        txtSaleOrderID.clear();
        dtpOrderDate.setValue(null);
        txaShipAddress.clear();
        cboSellTo.setValue(null);
        cboBillTo.setValue(null);
        txtAmount.clear();
        txaAmountWords.clear();
        tblSaleOrderDetails.getItems().clear();
        addRow(tblSaleOrderDetails, new SaleOrderDetailDA());
        this.setNextSaleOrderID();

    }

    private void loadItem() {
        try {
            ObservableList<SaleOrderDetailDA> selectedItems = tblSaleOrderDetails.getSelectionModel().getSelectedItems();
            if (selectedItems.isEmpty() || selectedItems.size() > 1) {
                return;
            }
            ItemDA itemDA = (ItemDA) getSelectedItem(FinanceNavigate.MAIN_CLASS, new ItemDA(), "Item", "Items", 400, 450, tblSaleOrderDetails, false);

            if (itemDA == null) {
                return;
            }

            if (tblSaleOrderDetails.getItems().stream().map(SaleOrderDetailDA::getItemDA).collect(Collectors.toList()).contains(itemDA)) {
                throw new Exception("The record with id: " + itemDA.getId() + " is already selected");
            }

            final TablePosition<SaleOrderDetailDA, String> focusedCell = tblSaleOrderDetails
                    .focusModelProperty().get().focusedCellProperty().get();
            SaleOrderDetailDA saleOrderDetailDA = tblSaleOrderDetails.getItems().get(focusedCell.getRow());
            saleOrderDetailDA.setItem(itemDA.getItem());
            setPrices(saleOrderDetailDA);
            tblSaleOrderDetails.refresh();
            calculateAmount();
            addRow(tblSaleOrderDetails, new SaleOrderDetailDA());

        } catch (IOException e) {
            errorMessage(e);
        } catch (Exception e) {
            errorMessage(e);
        }
    }

    private void setPrices(SaleOrderDetailDA saleOrderDetailDA) {
        saleOrderDetailDA.setUnitPrice(saleOrderDetailDA.getItemDA().getUnitPrice(selectedBillToDA));
        saleOrderDetailDA.setDiscount(saleOrderDetailDA.getItemDA().getDiscount(selectedBillToDA));
    }

    public void calculateAmount() {
        double totalAmount = tblSaleOrderDetails.getItems().stream().mapToDouble(SaleOrderDetailDA::getAmount).sum();
        txtAmount.setText(formatNumber(totalAmount));
        txaAmountWords.setText(NumberToWord.toWords(totalAmount));

    }

    private void loadUnitMeasure() {
        try {
            ObservableList<SaleOrderDetailDA> selectedItems = tblSaleOrderDetails.getSelectionModel().getSelectedItems();
            if (selectedItems.isEmpty() || selectedItems.size() > 1) {
                return;
            }

            final TablePosition<SaleOrderDetailDA, String> focusedCell = tblSaleOrderDetails
                    .focusModelProperty().get().focusedCellProperty().get();

            if (focusedCell == null) {
                return;
            }

            SaleOrderDetailDA saleOrderDetailDA = tblSaleOrderDetails.getItems().get(focusedCell.getRow());

            ItemDA itemDA = saleOrderDetailDA.getItemDA();
            if (itemDA == null) {
                return;
            }
            List<MeasureRelationDA> measureRelationDAs = new MeasureGroupDA().get(itemDA.getMeasureGroup().getMeasureGroupID()).getAllMeasureRelationsDAs();

            MeasureRelationDA measureRelationDA = (MeasureRelationDA) getSelectedItem(FinanceNavigate.MAIN_CLASS, new MeasureRelationDA(), measureRelationDAs, "View", "Item", 400, 450, tblSaleOrderDetails, false);

            if (measureRelationDA != null) {
                saleOrderDetailDA.setUnitMeasure(measureRelationDA.getDisplayKey());
                saleOrderDetailDA.setMeasureSize(measureRelationDA.getBaseSize());

            }

            tblSaleOrderDetails.refresh();
            calculateAmount();

        } catch (IOException e) {
            errorMessage(e);
        } catch (Exception e) {
            errorMessage(e);
        }
    }

    public void setTableEditable() {
        tblSaleOrderDetails.setEditable(true);
        // allows the individual cells to be selected
        tblSaleOrderDetails.getSelectionModel().cellSelectionEnabledProperty().set(true);
        // when character or numbers pressed it will start edit in editable
        // fields
        tblSaleOrderDetails.setOnKeyPressed(event -> {
            if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
                final TablePosition<SaleOrderDetailDA, ?> focusedCell = tblSaleOrderDetails
                        .focusModelProperty().get().focusedCellProperty().get();
                tblSaleOrderDetails.edit(focusedCell.getRow(), focusedCell.getTableColumn());

            } else if (event.getCode() == KeyCode.RIGHT
                    || event.getCode() == KeyCode.TAB) {
                tblSaleOrderDetails.getSelectionModel().selectNext();
                event.consume();
            } else if (event.getCode() == KeyCode.LEFT) {
                // work around due to
                // TableView.getSelectionModel().selectPrevious() due to a bug
                // stopping it from working on
                // the first column in the last row of the table
                selectPrevious(tblSaleOrderDetails);
                event.consume();
            } else if (event.getCode() == KeyCode.DELETE) {
                tblSaleOrderDetails.getItems().removeAll(tblSaleOrderDetails.getSelectionModel().getSelectedItems());
                calculateAmount();
            }
        });
    }

}
