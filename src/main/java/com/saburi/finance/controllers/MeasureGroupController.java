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
import static com.saburi.common.utils.FXUIUtils.addRowOnce;
import static com.saburi.common.utils.FXUIUtils.errorMessage;
import static com.saburi.common.utils.FXUIUtils.getEntity;
import static com.saburi.common.utils.FXUIUtils.getSelectedLookupData;
import static com.saburi.common.utils.FXUIUtils.getText;
import static com.saburi.common.utils.FXUIUtils.loadLookupData;
import static com.saburi.common.utils.FXUIUtils.message;
import static com.saburi.common.utils.FXUIUtils.selectLookupData;
import static com.saburi.common.utils.FXUIUtils.setTableEditable;
import static com.saburi.common.utils.FXUIUtils.warningOk;
import com.saburi.common.utils.Utilities.FormMode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import com.saburi.finance.dbaccess.MeasureGroupDA;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import com.saburi.finance.utils.FinanceObjectNames;
import javafx.scene.control.TableView;
import java.util.List;
import com.saburi.finance.dbaccess.MeasureRelationDA;
import javafx.collections.FXCollections;
import com.saburi.finance.entities.MeasureGroup;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.scene.control.cell.ComboBoxTableCell;
import com.saburi.finance.utils.FinanceEnums;
import com.saburi.finance.utils.FinanceEnums.UnitMeasureUsages;

public class MeasureGroupController extends EditController {

    private final MeasureGroupDA oMeasureGroupDA = new MeasureGroupDA();
    @FXML
    private TextField txtMeasureGroupID;
    @FXML
    private TextField txtMeasureGroupName;
    @FXML
    private ComboBox cboBaseUnitMeasure;
    @FXML
    private MenuItem cmiSelectBaseUnitMeasure;
    @FXML
    private TableView<MeasureRelationDA> tblMeasureRelations;
    @FXML
    private MenuItem cmiSelectUnitMeasure;
    LookupDataDA oLookupDataDA = new LookupDataDA();
    @FXML
    private TableColumn<MeasureRelationDA, String> tbcMeasureRelationUnitMeasureID;
    @FXML
    private TableColumn<MeasureRelationDA, Integer> tbcMeasureRelationBaseSize;
    @FXML
    private TableColumn<MeasureRelationDA, FinanceEnums.UnitMeasureUsages> tbcMeasureRelationDefaultUsage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadLookupData(cboBaseUnitMeasure, FinanceObjectNames.UNITMEASURE);

            tblMeasureRelations.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            setTableEditable(tblMeasureRelations);
            addRow(tblMeasureRelations, new MeasureRelationDA());
            cmiSelectUnitMeasure.setOnAction(e -> loadMeasureRelations());
            this.primaryKeyControl = txtMeasureGroupID;
            this.dbAccess = oMeasureGroupDA;
            this.restrainColumnConstraint = false;
//            this.prefSize = 360;
            this.setNextMeasureGroupID();
            setMeasureRelationUnitMeasure();
            setMeasureRelationBaseSize();
            setMeasureRelationDefaultUsage();
            selectLookupData(cmiSelectBaseUnitMeasure, FinanceObjectNames.UNITMEASURE,  "Unit Measure", 700, 400, cboBaseUnitMeasure, false);
        } catch (IOException e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void save() {
        try {
            this.editSuccessful = false;
            String measureGroupID = getText(txtMeasureGroupID, "Measure Group ID");
            String measureGroupName = getText(txtMeasureGroupName, "Measure Group Name");
            LookupData baseUnitMeasure = (LookupData) getEntity(cboBaseUnitMeasure, "Base Unit Measure");
            List<MeasureRelationDA> measureRelationsDAs = tblMeasureRelations.getItems();
            measureRelationsDAs.removeIf((p) -> p.getUnitMeasure() == null);

            MeasureGroupDA measureGroupDA = new MeasureGroupDA(measureGroupID, measureGroupName, baseUnitMeasure);
            measureRelationsDAs.forEach(e -> {
                e.setMeasureGroup((MeasureGroup) measureGroupDA.getMeasureGroup());
            });
            measureGroupDA.setMeasureRelationsDAs(measureRelationsDAs);
            String buttonText = btnSave.getText();
            if (buttonText.equalsIgnoreCase(FormMode.Save.name())) {
                measureGroupDA.save();
                message("Saved Successfully");
                clear();
            } else if (buttonText.equalsIgnoreCase(FormMode.Update.name())) {
                measureGroupDA.update();
                message("Updated Successfully");
            }
            this.dbAccess = measureGroupDA;
            this.editSuccessful = true;
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void delete() {
        try {
            String measureGroupID = getText(txtMeasureGroupID, "Measure Group ID");
            MeasureGroupDA measureGroupDA = oMeasureGroupDA.get(measureGroupID);
            if (!warningOk("Confirm Delete", "You are about to delete a record with ID: " + measureGroupID + " Are you sure you want to continue?", "Remember this action cannot be un done")) {
                return;
            }
            if (measureGroupDA.delete()) {
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
            String measureGroupID = getText(txtMeasureGroupID, "Measure Group ID");

            MeasureGroupDA measureGroupDA = oMeasureGroupDA.get(measureGroupID);
            txtMeasureGroupID.setText(measureGroupDA.getMeasureGroupID());
            txtMeasureGroupName.setText(measureGroupDA.getMeasureGroupName());
            cboBaseUnitMeasure.setValue(measureGroupDA.getBaseUnitMeasure());
            tblMeasureRelations.setItems(FXCollections.observableArrayList(measureGroupDA.getMeasureRelationsDAs()));
            addRow(tblMeasureRelations, new MeasureRelationDA());

        } catch (Exception e) {
            errorMessage(e);
        }

    }

    private void setNextMeasureGroupID() {
        try {
            if (btnSave.getText().equalsIgnoreCase(FormMode.Save.name())) {
                txtMeasureGroupID.setText(oMeasureGroupDA.getNextMeasureGroupID(oMeasureGroupDA.getNextIdHelper()));
            }
        } catch (Exception e) {
            errorMessage(e);
        }
    }

    private void setMeasureRelationUnitMeasure() {
        tbcMeasureRelationUnitMeasureID.setCellFactory(EditCell.StringTableColumn());
        tbcMeasureRelationUnitMeasureID.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null ? event.getNewValue()
                    : event.getOldValue();
            LookupData unitMeasure = oLookupDataDA.getLookupData(value);
            if (unitMeasure == null) {
                Platform.runLater(() -> message("No LookupData with Id " + value + " found"));
                return;
            }
            ((MeasureRelationDA) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow()))
                    .setUnitMeasure(unitMeasure);
            tblMeasureRelations.refresh();
            addRowOnce(tblMeasureRelations, new MeasureRelationDA());
        });
    }

    private void setMeasureRelationBaseSize() {
        tbcMeasureRelationBaseSize.setCellFactory(EditCell.IntegerTableColumn());
        tbcMeasureRelationBaseSize.setOnEditCommit(event -> {
            try {
                final int value = event.getNewValue() != null ? event.getNewValue()
                        : event.getOldValue();
                ((MeasureRelationDA) event.getTableView().getItems()
                        .get(event.getTablePosition().getRow()))
                        .setBaseSize(value);
                tblMeasureRelations.refresh();
                addRowOnce(tblMeasureRelations, new MeasureRelationDA());
            } catch (Exception e) {
                Platform.runLater(() -> errorMessage(e));
            }
        });
    }

    private void setMeasureRelationDefaultUsage() {
        tbcMeasureRelationDefaultUsage.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(UnitMeasureUsages.values())));
        tbcMeasureRelationDefaultUsage.setOnEditCommit(event -> {
            final UnitMeasureUsages value = event.getNewValue() != null ? event.getNewValue()
                    : event.getOldValue();
            ((MeasureRelationDA) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow()))
                    .setDefaultUsage(value);
            tblMeasureRelations.refresh();
            addRowOnce(tblMeasureRelations, new MeasureRelationDA());
        });
    }

    private void loadMeasureRelations() {
        try {
            ObservableList<MeasureRelationDA> selectedItems = tblMeasureRelations.getSelectionModel().getSelectedItems();
            if (selectedItems.isEmpty() || selectedItems.size() > 1) {
                return;
            }
            LookupDataDA unitMeasureDA = (LookupDataDA) getSelectedLookupData(FinanceObjectNames.UNITMEASURE, "Unit Measure", 400, 450, tblMeasureRelations, false);

            if (unitMeasureDA == null) {
                return;
            }

            if (tblMeasureRelations.getItems().stream().map(MeasureRelationDA::getUnitMeasureDA)
                    .collect(Collectors.toList()).contains(unitMeasureDA)) {
                throw new Exception("The record with id: " + unitMeasureDA.getId() + " is already selected");
            }

            final TablePosition<MeasureRelationDA, String> focusedCell = tblMeasureRelations
                    .focusModelProperty().get().focusedCellProperty().get();
            tblMeasureRelations.getItems().get(focusedCell.getRow()).setUnitMeasure(unitMeasureDA.getLookupData());
            tblMeasureRelations.edit(focusedCell.getRow(), focusedCell.getTableColumn());

        } catch (IOException e) {
            errorMessage(e);
        } catch (Exception e) {
            errorMessage(e);
        }
    }

    @Override
    protected void clear() {
        super.clear();
        addRow(tblMeasureRelations, new MeasureRelationDA());
        this.setNextMeasureGroupID();

    }

}
