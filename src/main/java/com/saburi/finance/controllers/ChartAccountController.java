/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.controllers;

import com.saburi.common.controllers.EditController;
import static com.saburi.common.utils.FXUIUtils.errorMessage;
import static com.saburi.common.utils.FXUIUtils.formatValue;
import static com.saburi.common.utils.FXUIUtils.getEntity;
import static com.saburi.common.utils.FXUIUtils.getSelectedValue;
import static com.saburi.common.utils.FXUIUtils.getText;
import static com.saburi.common.utils.FXUIUtils.loadDBEntities;
import static com.saburi.common.utils.FXUIUtils.message;
import static com.saburi.common.utils.FXUIUtils.selectItem;
import static com.saburi.common.utils.FXUIUtils.validateNumber;
import static com.saburi.common.utils.FXUIUtils.warningOk;
import com.saburi.common.utils.Utilities.FormMode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import com.saburi.finance.dbaccess.ChartAccountDA;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import com.saburi.finance.utils.FinanceEnums.AccountTypes;
import javafx.collections.FXCollections;
import javafx.scene.control.MenuItem;
import com.saburi.finance.dbaccess.AccountCategoryDA;
import com.saburi.finance.entities.AccountCategory;
import com.saburi.finance.utils.FinanceNavigate;
import com.saburi.finance.utils.FinanceEnums.AccountActions;
import com.saburi.finance.utils.FinanceEnums.AccountReports;
import javafx.scene.control.CheckBox;
import static com.saburi.common.utils.Utilities.formatNumber;
import java.util.List;

public class ChartAccountController extends EditController {

    private final ChartAccountDA oChartAccountDA = new ChartAccountDA();
    @FXML
    private ComboBox cboAccountType;
    @FXML
    private ComboBox cboAccountCategory;
    @FXML
    private MenuItem cmiSelectAccountCategory;
    @FXML
    private TextField txtAccountID;
    @FXML
    private TextField txtAccountName;
    @FXML
    private ComboBox cboAccountAction;
    @FXML
    private ComboBox cboAccountReport;
    @FXML
    private CheckBox chkContra;
    @FXML
    private CheckBox chkControlAccount;
    @FXML
    private TextField txtOpeningBalance;
    @FXML
    private TextField txtClosingBalance;
    @FXML
    private CheckBox chkReadOnly;
    @FXML
    private CheckBox chkHidden;
    private final AccountCategoryDA oAccountCategoryDA = new AccountCategoryDA();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cboAccountType.setItems(FXCollections.observableArrayList(AccountTypes.values()));
            cboAccountAction.setItems(FXCollections.observableArrayList(AccountActions.values()));
            cboAccountReport.setItems(FXCollections.observableArrayList(AccountReports.values()));
            loadDBEntities(cboAccountCategory);
            validateNumber(txtOpeningBalance);
            validateNumber(txtClosingBalance);
            formatValue(txtOpeningBalance);
            formatValue(txtClosingBalance);
            this.primaryKeyControl = txtAccountID;
            this.dbAccess = oChartAccountDA;
            this.restrainColumnConstraint = false;
            //this.prefSize = 360;
            cboAccountCategory.setOnAction(e -> this.setNextAccountID());
            cboAccountType.setOnAction(e -> accountTypeSelectected());
            chkContra.setOnAction(e -> setDefaultSelections());
            selectItem(FinanceNavigate.MAIN_CLASS, cmiSelectAccountCategory, oAccountCategoryDA, "AccountCategory", "Account Category", cboAccountCategory, true);
            enableNodes();
            chkHidden.disableProperty().bind(btnSave.textProperty().isEqualToIgnoreCase(FormMode.Save.name()));
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void save() {
        try {
            this.editSuccessful = false;
            AccountCategory accountCategory = (AccountCategory) getEntity(cboAccountCategory, "Account Category");
            String accountID = getText(txtAccountID, "Account ID");
            String accountName = getText(txtAccountName, "Account Name");
            AccountActions accountAction = (AccountActions) getSelectedValue(cboAccountAction, "Account Action");
            AccountReports accountReport = (AccountReports) getSelectedValue(cboAccountReport, "Account Report");
            boolean contra = chkContra.isSelected();
            boolean controlAccount = chkControlAccount.isSelected();
            boolean readOnly = chkReadOnly.isSelected();
            boolean hidden = chkHidden.isSelected();

            ChartAccountDA chartAccountDA = new ChartAccountDA(accountCategory, accountID, accountName, accountAction, accountReport, contra, controlAccount, readOnly, hidden);
            String buttonText = btnSave.getText();
            if (buttonText.equalsIgnoreCase(FormMode.Save.name())) {
                chartAccountDA.save();
                message("Saved Successfully");
                clear();
            } else if (buttonText.equalsIgnoreCase(FormMode.Update.name())) {
                chartAccountDA.update();
                message("Updated Successfully");
            }
            this.dbAccess = chartAccountDA;
            this.editSuccessful = true;
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void delete() {
        try {
            String accountID = getText(txtAccountID, "Account ID");
            ChartAccountDA chartAccountDA = oChartAccountDA.get(accountID);
            if (!warningOk("Confirm Delete", "You are about to delete a record with ID: " + accountID + " Are you sure you want to continue?", "Remember this action cannot be un done")) {
                return;
            }
            if (chartAccountDA.delete()) {
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
            String accountID = getText(txtAccountID, "Account ID");

            ChartAccountDA chartAccountDA = oChartAccountDA.get(accountID);
            cboAccountType.setValue(chartAccountDA.getAccountType());
            AccountCategory accountCategory = chartAccountDA.getAccountCategory();
            List<AccountCategory> accountCategories = cboAccountCategory.getItems();
            if (!accountCategories.contains(accountCategory)) {
                cboAccountCategory.getItems().add(accountCategory);
            }
            cboAccountCategory.setValue(accountCategory);
            txtAccountID.setText(chartAccountDA.getAccountID());
            txtAccountName.setText(chartAccountDA.getAccountName());
            cboAccountAction.setValue(chartAccountDA.getAccountAction());
            cboAccountReport.setValue(chartAccountDA.getAccountReport());
            chkContra.setSelected(chartAccountDA.isContra());
            chkControlAccount.setSelected(chartAccountDA.isControlAccount());
            txtOpeningBalance.setText(formatNumber(chartAccountDA.getOpeningBalance()));
            txtClosingBalance.setText(formatNumber(chartAccountDA.getClosingBalance()));
            chkReadOnly.setSelected(chartAccountDA.isReadOnly());
            chkHidden.setSelected(chartAccountDA.isHidden());

        } catch (Exception e) {
            errorMessage(e);
        }

    }

    private void setNextAccountID() {
        try {
            if (btnSave.getText().equalsIgnoreCase(FormMode.Save.name())) {
                AccountCategory accountCategory = (AccountCategory) getEntity(cboAccountCategory);

                if (accountCategory == null) {
                    return;
                }

                String accountCategoryID = accountCategory.getCategoryID();
                txtAccountID.setText(oChartAccountDA.getNextAccountID(oChartAccountDA.getNextIdHelper(accountCategory), accountCategoryID));
            }
        } catch (Exception e) {
            errorMessage(e);
        }
    }

    @Override
    protected void clear() {

        txtAccountID.clear();
        txtAccountName.clear();
        cboAccountAction.setValue(null);
        cboAccountReport.setValue(null);
        chkContra.setSelected(false);
        chkControlAccount.setSelected(false);
        txtOpeningBalance.clear();
        txtClosingBalance.clear();
        chkReadOnly.setSelected(false);
        chkHidden.setSelected(false);
        setNextAccountID();
        setDefaultSelections();
    }

    private void setDefaultSelections() {
        AccountTypes accountType = (AccountTypes) cboAccountType.getValue();
        AccountActions defaultAccountAction = null;
        AccountReports defaultAccountReport = null;
        if (chkContra.isSelected()) {
            if (accountType.equals(AccountTypes.Asset) || accountType.equals(AccountTypes.Expense)) {
                defaultAccountAction = AccountActions.Credit;
                defaultAccountReport = AccountReports.Financial_Statement;
            } else if (accountType.equals(AccountTypes.Income) || accountType.equals(AccountTypes.Equity) || accountType.equals(AccountTypes.Liability)) {
                defaultAccountAction = AccountActions.Debit;
                defaultAccountReport = AccountReports.Balance_Sheet;
            }

        } else {

            if (accountType.equals(AccountTypes.Asset) || accountType.equals(AccountTypes.Expense)) {
                defaultAccountAction = AccountActions.Debit;
                defaultAccountReport = AccountReports.Balance_Sheet;
            } else if (accountType.equals(AccountTypes.Income) || accountType.equals(AccountTypes.Equity) || accountType.equals(AccountTypes.Liability)) {
                defaultAccountAction = AccountActions.Credit;
                defaultAccountReport = AccountReports.Financial_Statement;
            }

        }
        cboAccountAction.setValue(defaultAccountAction);
        cboAccountReport.setValue(defaultAccountReport);
    }

    private void accountTypeSelectected() {
        AccountTypes accountType = (AccountTypes) cboAccountType.getValue();
        loadDBEntities(oAccountCategoryDA.getAccountCategories(accountType), cboAccountCategory);
        setDefaultSelections();

    }

    private void enableNodes() {
        cboAccountAction.disableProperty().set(true);
        cboAccountReport.disableProperty().set(true);
        chkReadOnly.disableProperty().set(true);
        chkHidden.disableProperty().set(btnSave.getText().equalsIgnoreCase(FormMode.Save.name()));

    }

}
