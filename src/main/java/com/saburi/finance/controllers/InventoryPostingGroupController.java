/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.controllers;

import com.saburi.common.controllers.EditController;
import com.saburi.common.entities.LookupData;
import static com.saburi.common.utils.FXUIUtils.errorMessage;
import static com.saburi.common.utils.FXUIUtils.getEntity;
import static com.saburi.common.utils.FXUIUtils.getText;
import static com.saburi.common.utils.FXUIUtils.loadDBEntities;
import static com.saburi.common.utils.FXUIUtils.loadLookupData;
import static com.saburi.common.utils.FXUIUtils.message;
import static com.saburi.common.utils.FXUIUtils.selectItem;
import static com.saburi.common.utils.FXUIUtils.selectLookupData;
import static com.saburi.common.utils.FXUIUtils.warningOk;
import com.saburi.common.utils.Utilities.FormMode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import com.saburi.finance.dbaccess.InventoryPostingGroupDA;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import com.saburi.finance.utils.FinanceObjectNames;
import com.saburi.finance.dbaccess.ChartAccountDA;
import com.saburi.finance.entities.ChartAccount;
import com.saburi.finance.utils.FinanceNavigate;

public class InventoryPostingGroupController extends EditController {

    private final InventoryPostingGroupDA oInventoryPostingGroupDA = new InventoryPostingGroupDA();
    @FXML
    private TextField txtInventoryPostingGroupID;
    @FXML
    private ComboBox cboLocation;
    @FXML
    private MenuItem cmiSelectLocation;
    @FXML
    private ComboBox cboInventoryGroup;
    @FXML
    private MenuItem cmiSelectInventoryGroup;
    @FXML
    private ComboBox cboAccount;
    @FXML
    private MenuItem cmiSelectAccount;
    private final ChartAccountDA oChartAccountDA = new ChartAccountDA();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadLookupData(cboLocation, FinanceObjectNames.LOCATION);
            loadLookupData(cboInventoryGroup, FinanceObjectNames.INVENTORYGROUP);
            loadDBEntities(oChartAccountDA.getChartAccounts(), cboAccount);

            this.primaryKeyControl = txtInventoryPostingGroupID;
            this.dbAccess = oInventoryPostingGroupDA;
            this.restrainColumnConstraint = false;
            this.prefSize = 360;
            this.setNextInventoryPostingGroupID();
            selectLookupData(FinanceNavigate.MAIN_CLASS,cmiSelectLocation, FinanceObjectNames.LOCATION, "view", "Location", 700, 400, cboLocation, false);
            selectLookupData(FinanceNavigate.MAIN_CLASS, cmiSelectInventoryGroup, FinanceObjectNames.INVENTORYGROUP, "LookupData", "Inventory Group", 700, 400, cboInventoryGroup, false);
            selectItem(FinanceNavigate.MAIN_CLASS,cmiSelectAccount, oChartAccountDA, "ChartAccount", "Account", 700, 400, cboAccount, true);
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void save() {
        try {
            this.editSuccessful = false;
            String inventoryPostingGroupID = getText(txtInventoryPostingGroupID, "Inventory Posting Group ID");
            LookupData location = (LookupData) getEntity(cboLocation, "Location");
            LookupData inventoryGroup = (LookupData) getEntity(cboInventoryGroup, "Inventory Group");
            ChartAccount account = (ChartAccount) getEntity(cboAccount, "Account");

            InventoryPostingGroupDA inventoryPostingGroupDA = new InventoryPostingGroupDA(inventoryPostingGroupID, location, inventoryGroup, account);
            String buttonText = btnSave.getText();
            if (buttonText.equalsIgnoreCase(FormMode.Save.name())) {
                inventoryPostingGroupDA.save();
                message("Saved Successfully");
                clear();
                this.setNextInventoryPostingGroupID();
            } else if (buttonText.equalsIgnoreCase(FormMode.Update.name())) {
                inventoryPostingGroupDA.update();
                message("Updated Successfully");
            }
            this.dbAccess = inventoryPostingGroupDA;
            this.editSuccessful = true;
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void delete() {
        try {
            String inventoryPostingGroupID = getText(txtInventoryPostingGroupID, "Inventory Posting Group ID");
            InventoryPostingGroupDA inventoryPostingGroupDA = oInventoryPostingGroupDA.get(inventoryPostingGroupID);
            if (!warningOk("Confirm Delete", "You are about to delete a record with ID: " + inventoryPostingGroupID + " Are you sure you want to continue?", "Remember this action cannot be un done")) {
                return;
            }
            if (inventoryPostingGroupDA.delete()) {
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
            String inventoryPostingGroupID = getText(txtInventoryPostingGroupID, "Inventory Posting Group ID");

            InventoryPostingGroupDA inventoryPostingGroupDA = oInventoryPostingGroupDA.get(inventoryPostingGroupID);
            txtInventoryPostingGroupID.setText(inventoryPostingGroupDA.getInventoryPostingGroupID());
            cboLocation.setValue(inventoryPostingGroupDA.getLocation());
            cboInventoryGroup.setValue(inventoryPostingGroupDA.getInventoryGroup());
            cboAccount.setValue(inventoryPostingGroupDA.getAccount());

        } catch (Exception e) {
            errorMessage(e);
        }

    }

    private void setNextInventoryPostingGroupID() {
        try {
            if (btnSave.getText().equalsIgnoreCase(FormMode.Save.name())) {
                txtInventoryPostingGroupID.setText(oInventoryPostingGroupDA.getNextInventoryPostingGroupID(oInventoryPostingGroupDA.getNextIdHelper()));
            }
        } catch (Exception e) {
            errorMessage(e);
        }
    }

   

}
