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
import com.saburi.finance.dbaccess.VendorPostingGroupDA;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import com.saburi.finance.utils.FinanceObjectNames;
import com.saburi.finance.dbaccess.ChartAccountDA;
import com.saburi.finance.entities.ChartAccount;
import com.saburi.finance.utils.FinanceEnums.AccountTypes;
import com.saburi.finance.utils.FinanceNavigate;

public class VendorPostingGroupController extends EditController {

    private final VendorPostingGroupDA oVendorPostingGroupDA = new VendorPostingGroupDA();
    @FXML
    private TextField txtVendorPostingGroupID;
    @FXML
    private ComboBox cboVendorGroup;
    @FXML
    private MenuItem cmiSelectVendorGroup;
    @FXML
    private ComboBox cboPayableAccount;
    @FXML
    private MenuItem cmiSelectPayableAccount;
    @FXML
    private ComboBox cboServiceChargeAccount;
    @FXML
    private MenuItem cmiSelectServiceChargeAccount;
    @FXML
    private ComboBox cboPaymentDiscountAccount;
    @FXML
    private MenuItem cmiSelectPaymentDiscountAccount;
    private final ChartAccountDA oChartAccountDA = new ChartAccountDA();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadLookupData(cboVendorGroup, FinanceObjectNames.VENDORGROUP);
            loadDBEntities(oChartAccountDA.getChartAccounts(AccountTypes.Liability), cboPayableAccount);
            loadDBEntities(oChartAccountDA.getChartAccounts(AccountTypes.Liability), cboServiceChargeAccount);
            loadDBEntities(oChartAccountDA.getChartAccounts(AccountTypes.Income, false), cboPaymentDiscountAccount);

            this.primaryKeyControl = txtVendorPostingGroupID;
            this.dbAccess = oVendorPostingGroupDA;
            this.restrainColumnConstraint = false;
            this.prefSize = 360;
            this.setNextVendorPostingGroupID();
            selectLookupData(FinanceNavigate.MAIN_CLASS, cmiSelectVendorGroup, FinanceObjectNames.VENDORGROUP, "LookupData", "Vendor Group", 700, 400, cboVendorGroup, false);
            selectItem(FinanceNavigate.MAIN_CLASS, cmiSelectPayableAccount, oChartAccountDA, oChartAccountDA.getChartAccountDAs(AccountTypes.Liability), "ChartAccount", "Payable Account", 700, 400, cboPayableAccount, true);
            selectItem(FinanceNavigate.MAIN_CLASS, cmiSelectServiceChargeAccount, oChartAccountDA, oChartAccountDA.getChartAccountDAs(AccountTypes.Liability), "ChartAccount", "Service Charge Account", 700, 400, cboServiceChargeAccount, true);
            selectItem(FinanceNavigate.MAIN_CLASS, cmiSelectPaymentDiscountAccount, oChartAccountDA, oChartAccountDA.getChartAccountDAs(AccountTypes.Income, false), "ChartAccount", "Payment Discount Account", 700, 400, cboPaymentDiscountAccount, true);
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void save() {
        try {
            this.editSuccessful = true;
            String vendorPostingGroupID = getText(txtVendorPostingGroupID, "Vendor Posting GroupID");
            LookupData vendorGroup = (LookupData) getEntity(cboVendorGroup, "Vendor Group");
            ChartAccount payableAccount = (ChartAccount) getEntity(cboPayableAccount, "Payable Account");
            ChartAccount serviceChargeAccount = (ChartAccount) getEntity(cboServiceChargeAccount, "Service Charge Account");
            ChartAccount paymentDiscountAccount = (ChartAccount) getEntity(cboPaymentDiscountAccount, "Payment Discount Account");

            VendorPostingGroupDA vendorPostingGroupDA = new VendorPostingGroupDA(vendorPostingGroupID, vendorGroup, payableAccount, serviceChargeAccount, paymentDiscountAccount);
            String buttonText = btnSave.getText();
            if (buttonText.equalsIgnoreCase(FormMode.Save.name())) {
                vendorPostingGroupDA.save();
                message("Saved Successfully");
                clear();
                this.setNextVendorPostingGroupID();
            } else if (buttonText.equalsIgnoreCase(FormMode.Update.name())) {
                vendorPostingGroupDA.update();
                message("Updated Successfully");
            }
            this.dbAccess = vendorPostingGroupDA;
            this.editSuccessful = true;
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void delete() {
        try {
            String vendorPostingGroupID = getText(txtVendorPostingGroupID, "Vendor Posting GroupID");
            VendorPostingGroupDA vendorPostingGroupDA = oVendorPostingGroupDA.get(vendorPostingGroupID);
            if (!warningOk("Confirm Delete", "You are about to delete a record with ID: " + vendorPostingGroupID + " Are you sure you want to continue?", "Remember this action cannot be un done")) {
                return;
            }
            if (vendorPostingGroupDA.delete()) {
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
            String vendorPostingGroupID = getText(txtVendorPostingGroupID, "Vendor Posting GroupID");

            VendorPostingGroupDA vendorPostingGroupDA = oVendorPostingGroupDA.get(vendorPostingGroupID);
            txtVendorPostingGroupID.setText(vendorPostingGroupDA.getVendorPostingGroupID());
            cboVendorGroup.setValue(vendorPostingGroupDA.getVendorGroup());
            cboPayableAccount.setValue(vendorPostingGroupDA.getPayableAccount());
            cboServiceChargeAccount.setValue(vendorPostingGroupDA.getServiceChargeAccount());
            cboPaymentDiscountAccount.setValue(vendorPostingGroupDA.getPaymentDiscountAccount());

        } catch (Exception e) {
            errorMessage(e);
        }

    }

    private void setNextVendorPostingGroupID() {
        try {
            if (btnSave.getText().equalsIgnoreCase(FormMode.Save.name())) {
                txtVendorPostingGroupID.setText(oVendorPostingGroupDA.getNextVendorPostingGroupID(oVendorPostingGroupDA.getNextIdHelper()));
            }
        } catch (Exception e) {
            errorMessage(e);
        }
    }

}
