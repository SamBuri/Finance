/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.controllers;

import com.saburi.common.controllers.EditController;
import com.saburi.common.dbaccess.CompanyDA;
import com.saburi.common.entities.Company;
import com.saburi.common.utils.CommonEnums.OpenStatus;
import static com.saburi.common.utils.FXUIUtils.errorMessage;
import static com.saburi.common.utils.FXUIUtils.getDate;
import static com.saburi.common.utils.FXUIUtils.getEntity;
import static com.saburi.common.utils.FXUIUtils.getSelectedValue;
import static com.saburi.common.utils.FXUIUtils.getText;
import static com.saburi.common.utils.FXUIUtils.loadDBEntities;
import static com.saburi.common.utils.FXUIUtils.message;
import static com.saburi.common.utils.FXUIUtils.selectItem;
import static com.saburi.common.utils.FXUIUtils.warningOk;
import com.saburi.common.utils.Utilities.FormMode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import com.saburi.finance.dbaccess.FinancialPeriodDA;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
;
import com.saburi.finance.utils.FinanceNavigate;
import javafx.collections.FXCollections;



public class FinancialPeriodController extends EditController {

    private final FinancialPeriodDA oFinancialPeriodDA = new FinancialPeriodDA();
    @FXML
    private TextField txtPeriodID;
    @FXML
    private TextField txtDescription;
    @FXML
    private ComboBox cboCompany;
    @FXML
    private MenuItem cmiSelectCompany;
    @FXML
    private DatePicker dtpStartDate;
    @FXML
    private DatePicker dtpEndDate;
    @FXML
    private ComboBox cboStatus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadDBEntities(new CompanyDA().getCompanys(), cboCompany);
            cboStatus.setItems(FXCollections.observableArrayList(OpenStatus.values()));
            this.primaryKeyControl = txtPeriodID;
            this.dbAccess = oFinancialPeriodDA;
            this.restrainColumnConstraint = false;
            this.minSize = 360;
            this.setNextPeriodID();
            selectItem(FinanceNavigate.MAIN_CLASS, cmiSelectCompany, new CompanyDA(), "Company", "Company", 700, 400, cboCompany, true);
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void save() {
        try {
            this.editSuccessful = false;
            String periodID = getText(txtPeriodID, "Period ID");
            String description = getText(txtDescription, "Discription");
            Company company = (Company) getEntity(cboCompany, "Company No");
            LocalDate startDate = getDate(dtpStartDate, "Start Date");
            LocalDate endDate = getDate(dtpEndDate, "End Date");
            OpenStatus status = (OpenStatus) getSelectedValue(cboStatus, "Status");

            FinancialPeriodDA financialPeriodDA = new FinancialPeriodDA(periodID, description, company, startDate, endDate, status);
            String buttonText = btnSave.getText();
            if (buttonText.equalsIgnoreCase(FormMode.Save.name())) {
                financialPeriodDA.save();
                message("Saved Successfully");
                clear();
                this.setNextPeriodID();
            } else if (buttonText.equalsIgnoreCase(FormMode.Update.name())) {
                financialPeriodDA.update();
                message("Updated Successfully");
            }
            this.dbAccess =  financialPeriodDA;
            this.editSuccessful=true;
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    @Override
    protected void delete() {
        try {
            String periodID = getText(txtPeriodID, "Period ID");
            FinancialPeriodDA financialPeriodDA = oFinancialPeriodDA.get(periodID);
            if (!warningOk("Confirm Delete", "You are about to delete a record with ID: " + periodID + " Are you sure you want to continue?", "Remember this action cannot be un done")) {
                return;
            }
            if (financialPeriodDA.delete()) {
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
            String periodID = getText(txtPeriodID, "Period ID");

            FinancialPeriodDA financialPeriodDA = oFinancialPeriodDA.get(periodID);
            txtPeriodID.setText(financialPeriodDA.getPeriodID());
            txtDescription.setText(financialPeriodDA.getDescription());
            cboCompany.setValue(financialPeriodDA.getCompanyDA());
            dtpStartDate.setValue((LocalDate) financialPeriodDA.getStartDate());
            dtpEndDate.setValue((LocalDate) financialPeriodDA.getEndDate());
            cboStatus.setValue(financialPeriodDA.getStatus());

        } catch (Exception e) {
            errorMessage(e);
        }

    }

    private void setNextPeriodID() {
        try {
            if (btnSave.getText().equalsIgnoreCase(FormMode.Save.name())) {
                txtPeriodID.setText(oFinancialPeriodDA.getNextPeriodID(oFinancialPeriodDA.getNextIdHelper()));
            }
        } catch (Exception e) {
            errorMessage(e);
        }
    }


}