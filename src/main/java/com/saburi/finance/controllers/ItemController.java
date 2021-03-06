/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.controllers;

import com.saburi.common.controllers.EditController;
import com.saburi.common.dbaccess.LookupDataDA;
import com.saburi.common.entities.LookupData;
import com.saburi.common.utils.EditCell;
import static com.saburi.common.utils.FXUIUtils.addRow;
import static com.saburi.common.utils.FXUIUtils.errorMessage;
import static com.saburi.common.utils.FXUIUtils.formatValue;
import static com.saburi.common.utils.FXUIUtils.getDouble;
import static com.saburi.common.utils.FXUIUtils.getEntity;
import static com.saburi.common.utils.FXUIUtils.getSelectedItem;
import static com.saburi.common.utils.FXUIUtils.getSelectedLookupData;
import static com.saburi.common.utils.FXUIUtils.getText;
import static com.saburi.common.utils.FXUIUtils.loadDBEntities;
import static com.saburi.common.utils.FXUIUtils.loadLookupData;
import static com.saburi.common.utils.FXUIUtils.message;
import static com.saburi.common.utils.FXUIUtils.selectItem;
import static com.saburi.common.utils.FXUIUtils.selectLookupData;
import static com.saburi.common.utils.FXUIUtils.setTableEditable;
import static com.saburi.common.utils.FXUIUtils.validateNumber;
import static com.saburi.common.utils.FXUIUtils.warningOk;
import com.saburi.common.utils.Utilities.FormMode;
import static com.saburi.common.utils.Utilities.defortNumberOptional;
import static com.saburi.common.utils.Utilities.formatNumber;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import com.saburi.finance.dbaccess.ItemDA;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import com.saburi.finance.dbaccess.ItemCategoryDA;
import com.saburi.finance.entities.ItemCategory;
import com.saburi.finance.dbaccess.MeasureGroupDA;
import com.saburi.finance.entities.MeasureGroup;
import javafx.scene.control.TableView;
import java.util.List;
import com.saburi.finance.dbaccess.ItemPriceGroupDA;
import com.saburi.finance.dbaccess.ItemTemplateDA;
import javafx.collections.FXCollections;
import com.saburi.finance.entities.Item;
import com.saburi.finance.utils.FinanceEnums;
import com.saburi.finance.utils.FinanceEnums.ItemCategoryGroups;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.collections.ObservableList;
import com.saburi.finance.utils.FinanceNavigate;
import java.io.IOException;
import java.util.stream.Collectors;
import javafx.application.Platform;
import com.saburi.finance.utils.FinanceObjectNames;

public class ItemController extends EditController {

    private final ItemDA oItemDA = new ItemDA();
    @FXML
    private ComboBox cboItemCategory;
    @FXML
    private MenuItem cmiSelectItemCategory;
    @FXML
    private TextField txtItemID;
    @FXML
    private TextField txtItemName;
    @FXML
    private TextField txtItemCategoryGroup;
    @FXML
    private TextField txtUnitCost;
    @FXML
    private TextField txtUnitPrice;
    @FXML
    private ComboBox cboItemGroup;
    @FXML
    private MenuItem cmiSelectItemGroup;
    @FXML
    private ComboBox cboVATItemGroup;
    @FXML
    private MenuItem cmiSelectVATItemGroup;
    @FXML
    private ComboBox cboInventoryGroup;
    @FXML
    private MenuItem cmiSelectInventoryGroup;
    @FXML
    private ComboBox cboMeasureGroup;
    @FXML
    private MenuItem cmiSelectMeasureGroup;
    @FXML
    private TableView<ItemPriceGroupDA> tblItemPriceGroups;
    @FXML
    private MenuItem cmiSelectItemPriceGroups, cmiSelectTemplate;
    private final ItemCategoryDA oItemCategoryDA = new ItemCategoryDA();
    private final MeasureGroupDA oMeasureGroupDA = new MeasureGroupDA();
    LookupDataDA oLookupDataDA = new LookupDataDA();
    @FXML
    private TableColumn<ItemPriceGroupDA, String> tbcItemPriceGroupPriceGroupID;
    @FXML
    private TableColumn<ItemPriceGroupDA, String> tbcItemPriceGroupUnitPrice;
    @FXML
    private TableColumn<ItemPriceGroupDA, String> tbcItemPriceGroupDiscount;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadLookupData(cboItemGroup, FinanceObjectNames.ITEMGROUP);
            loadLookupData(cboVATItemGroup, FinanceObjectNames.VATITEMGROUP);
            loadLookupData(cboInventoryGroup, FinanceObjectNames.INVENTORYGROUP);
            loadDBEntities(oItemCategoryDA.getItemCategorys(), cboItemCategory);
            loadDBEntities(oMeasureGroupDA.getMeasureGroups(), cboMeasureGroup);

            validateNumber(txtUnitCost);
            validateNumber(txtUnitPrice);
            formatValue(txtUnitCost);
            formatValue(txtUnitPrice);
            tblItemPriceGroups.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            setTableEditable(tblItemPriceGroups);
            addRow(tblItemPriceGroups, new ItemPriceGroupDA());
            cmiSelectItemPriceGroups.setOnAction(e -> loadPriceGroups());
            this.primaryKeyControl = txtItemID;
            this.dbAccess = oItemDA;
            this.restrainColumnConstraint = false;
//            this.prefSize = 360;
            cboItemCategory.setOnAction(e -> this.setNextItemID());
            setItemPriceGroupPriceGroup();
            setItemPriceGroupUnitPrice();
            setItemPriceGroupDiscount();
            selectItem(FinanceNavigate.MAIN_CLASS, cmiSelectItemCategory, oItemCategoryDA, "ItemCategory", "Item Category", 700, 400, cboItemCategory, true);
            selectLookupData(FinanceNavigate.MAIN_CLASS, cmiSelectItemGroup, FinanceObjectNames.ITEMGROUP, "LookupData", "Item Group", 700, 400, cboItemGroup, false);
            selectLookupData(FinanceNavigate.MAIN_CLASS, cmiSelectVATItemGroup, FinanceObjectNames.VATITEMGROUP, "LookupData", "VAT Item Group", 700, 400, cboVATItemGroup, false);
            selectLookupData(FinanceNavigate.MAIN_CLASS, cmiSelectInventoryGroup, FinanceObjectNames.INVENTORYGROUP, "LookupData", "Inventory Group", 700, 400, cboInventoryGroup, false);
            selectItem(FinanceNavigate.MAIN_CLASS, cmiSelectMeasureGroup, oMeasureGroupDA, "MeasureGroup", "Measure Group", 700, 400, cboMeasureGroup, true);

            cboItemCategory.setOnAction(e -> {
                ItemCategory itemCategory = (ItemCategory) getEntity(cboItemCategory);
                txtItemCategoryGroup.setText(itemCategory == null ? "" : itemCategory.getItemCategoryGroup().name());
                this.setNextItemID();
            });
            cmiSelectTemplate.setOnAction(e -> selectItemTemplate());
        } catch (IOException e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void save() {
        try {
            this.editSuccessful = false;
            ItemCategory itemCategory = (ItemCategory) getEntity(cboItemCategory, "Item Category");
            String itemID = getText(txtItemID, "Item ID");
            String itemName = getText(txtItemName, "Item Name");
            double unitCost = getDouble(txtUnitCost, "Unit Cost");
            double unitPrice = getDouble(txtUnitPrice, "Unit Price");
            LookupData itemGroup = (LookupData) getEntity(cboItemGroup, "Item Group");
            LookupData vATItemGroup = (LookupData) getEntity(cboVATItemGroup);
            LookupData inventoryGroup = (LookupData) getEntity(cboInventoryGroup, "Inventory Group",
                    itemCategory.getItemCategoryGroup().equals(ItemCategoryGroups.Inventory));
            MeasureGroup measureGroup = (MeasureGroup) getEntity(cboMeasureGroup);
            List<ItemPriceGroupDA> itemPriceGroupsDAs = tblItemPriceGroups.getItems();
            itemPriceGroupsDAs.removeIf((p) -> p.getPriceGroup() == null);

            ItemDA itemDA = new ItemDA(itemCategory, itemID, itemName, unitCost, unitPrice, itemGroup, vATItemGroup, inventoryGroup, measureGroup);
            itemPriceGroupsDAs.forEach(e -> {
                e.setItem((Item) itemDA.getItem());
            });
            itemDA.setItemPriceGroupsDAs(itemPriceGroupsDAs);
            String buttonText = btnSave.getText();
            if (buttonText.equalsIgnoreCase(FormMode.Save.name())) {
                itemDA.save();
                message("Saved Successfully");
                clear();
            } else if (buttonText.equalsIgnoreCase(FormMode.Update.name())) {
                itemDA.update();
                message("Updated Successfully");
            }
            this.dbAccess = itemDA;
            this.editSuccessful = true;
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void delete() {
        try {
            String itemID = getText(txtItemID, "Item ID");
            ItemDA itemDA = oItemDA.get(itemID);
            if (!warningOk("Confirm Delete", "You are about to delete a record with ID: " + itemID + " Are you sure you want to continue?", "Remember this action cannot be un done")) {
                return;
            }
            if (itemDA.delete()) {
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
            String itemID = getText(txtItemID, "Item ID");

            ItemDA itemDA = oItemDA.get(itemID);
            cboItemCategory.setValue(itemDA.getItemCategory());
            txtItemID.setText(itemDA.getItemID());
            txtItemName.setText(itemDA.getItemName());
            txtItemCategoryGroup.setText(itemDA.getItemCategoryGroup().toString());
            txtUnitCost.setText(formatNumber(itemDA.getUnitCost()));
            txtUnitPrice.setText(formatNumber(itemDA.getUnitPrice()));
            cboItemGroup.setValue(itemDA.getItemGroup());
            cboVATItemGroup.setValue(itemDA.getVATItemGroup());
            cboInventoryGroup.setValue(itemDA.getInventoryGroup());
            cboMeasureGroup.setValue(itemDA.getMeasureGroup());
            tblItemPriceGroups.setItems(FXCollections.observableArrayList(itemDA.getItemPriceGroupsDAs()));
            addRow(tblItemPriceGroups, new ItemPriceGroupDA());

        } catch (Exception e) {
            errorMessage(e);
        }

    }

    private void setNextItemID() {
        try {
            if (btnSave.getText().equalsIgnoreCase(FormMode.Save.name())) {

                ItemCategory itemCategory = (ItemCategory) getEntity(cboItemCategory);
                String itemCategoryID = itemCategory == null ? "" : itemCategory.getItemCategoryID();
                txtItemID.setText(oItemDA.getNextItemID(oItemDA.getNextIdHelper(itemCategory), itemCategoryID));
            }
        } catch (Exception e) {
            errorMessage(e);
        }
    }

    private void setItemPriceGroupPriceGroup() {
        tbcItemPriceGroupPriceGroupID.setCellFactory(EditCell.StringTableColumn());
        tbcItemPriceGroupPriceGroupID.setOnEditCommit(event -> {
            try {
                final String value = event.getNewValue() != null ? event.getNewValue()
                        : event.getOldValue();
                LookupData priceGroup = oLookupDataDA.getLookupData(value);
                if (priceGroup == null) {
                    Platform.runLater(() -> message("No LookupData with Id " + value + " found"));
                    return;
                }
                ((ItemPriceGroupDA) event.getTableView().getItems()
                        .get(event.getTablePosition().getRow()))
                        .setPriceGroup(priceGroup);
                tblItemPriceGroups.refresh();
                addRow(tblItemPriceGroups, new ItemPriceGroupDA());
            } catch (Exception e) {
                Platform.runLater(() -> errorMessage(e));
            }
        });
    }

    private void setItemPriceGroupUnitPrice() {
        tbcItemPriceGroupUnitPrice.setCellFactory(EditCell.StringTableColumn());
        tbcItemPriceGroupUnitPrice.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null ? event.getNewValue()
                    : event.getOldValue();
            ((ItemPriceGroupDA) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow()))
                    .setUnitPrice(defortNumberOptional(value));
            tblItemPriceGroups.refresh();
            addRow(tblItemPriceGroups, new ItemPriceGroupDA());
        });
    }

    private void setItemPriceGroupDiscount() {
        tbcItemPriceGroupDiscount.setCellFactory(EditCell.StringTableColumn());
        tbcItemPriceGroupDiscount.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null ? event.getNewValue()
                    : event.getOldValue();
            ((ItemPriceGroupDA) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow()))
                    .setDiscount(defortNumberOptional(value));
            tblItemPriceGroups.refresh();
            addRow(tblItemPriceGroups, new ItemPriceGroupDA());
        });
    }

    private void loadPriceGroups() {
        try {
            ObservableList<ItemPriceGroupDA> selectedItems = tblItemPriceGroups.getSelectionModel().getSelectedItems();
            if (selectedItems.isEmpty() || selectedItems.size() > 1) {
                return;
            }
            LookupDataDA priceGroupDA = (LookupDataDA) getSelectedLookupData(FinanceObjectNames.PRICEGROUP, "Price Groups", 400, 450, tblItemPriceGroups, false);

            if (priceGroupDA == null) {
                return;
            }

            if (tblItemPriceGroups.getItems().stream().map(ItemPriceGroupDA::getPriceGroupDA)
                    .collect(Collectors.toList()).contains(priceGroupDA)) {
                throw new Exception("The record with id: " + priceGroupDA.getId() + " is already selected");
            }

            final TablePosition<ItemPriceGroupDA, String> focusedCell = tblItemPriceGroups
                    .focusModelProperty().get().focusedCellProperty().get();
            tblItemPriceGroups.getItems().get(focusedCell.getRow()).setPriceGroup((LookupData) priceGroupDA.getDBEntity());
            tblItemPriceGroups.edit(focusedCell.getRow(), focusedCell.getTableColumn());

        } catch (IOException e) {
            errorMessage(e);
        } catch (Exception e) {
            errorMessage(e);
        }
    }

    private void selectItemTemplate() {
        ItemTemplateDA itemTemplateDA;
        try {
            itemTemplateDA = (ItemTemplateDA) getSelectedItem(FinanceNavigate.MAIN_CLASS, new ItemTemplateDA(), new ItemTemplateDA().get(), "ItemTemplate", title, viewWidth, viewHight, btnSave, false);
            this.loadItem(itemTemplateDA);
        } catch (IOException ex) {
            errorMessage(ex);
        }

    }

    public void loadItem(ItemTemplateDA itemTemplateDA) {

        if (itemTemplateDA == null) {
            return;
        }
        ItemCategory itemCategory = itemTemplateDA.getItemCategory();
        cboItemCategory.setValue(itemTemplateDA.getItemCategory());
        cboItemGroup.setValue(itemTemplateDA.getItemGroup());
        cboVATItemGroup.setValue(itemTemplateDA.getVATItemGroup());
        cboInventoryGroup.setValue(itemTemplateDA.getInventoryGroup());
        cboMeasureGroup.setValue(itemTemplateDA.getMeasureGroup());
        txtItemCategoryGroup.setText(itemCategory == null ? "" : itemCategory.getItemCategoryGroup().name());
        this.setNextItemID();

    }

}
