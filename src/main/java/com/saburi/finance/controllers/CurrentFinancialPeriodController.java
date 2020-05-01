package com.saburi.finance.controllers;

import static com.saburi.common.utils.FXUIUtils.close;
import static com.saburi.common.utils.FXUIUtils.errorMessage;
import static com.saburi.common.utils.FXUIUtils.getTextValue;
import static com.saburi.common.utils.FXUIUtils.loadCombo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import com.saburi.finance.dbaccess.FinancialPeriodDA;
import javafx.scene.Cursor;

public class CurrentFinancialPeriodController implements Initializable {

    @FXML
    private Button btnSave, btnSearch, btnDelete, btnClose;
    @FXML
    private ComboBox cboFinancialPeriod;
    @FXML
    private CheckBox chkRemeberSelection;

    public void setButtonText(String text) {
        btnSave.setText(text);
        btnSearch.setVisible(true);
        btnDelete.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadCombo(new FinancialPeriodDA().keyValuePairs(), cboFinancialPeriod);

            btnSave.setOnAction(e -> this.save());
            btnClose.setOnAction(e -> close(btnClose));
        } catch (Exception e) {
            errorMessage(e);
        } finally {
        }
    }

    private void save() {
        try {
            this.btnSave.getScene().setCursor(Cursor.WAIT);

            String financialPeriod = getTextValue(cboFinancialPeriod, "Financial Period");
            boolean remeberSelection = chkRemeberSelection.isSelected();
            FinancialPeriodDA financialPeriodDA = new FinancialPeriodDA().get(financialPeriod);
//            CurrentFinacialPeriod.setCurrentFinancialPeriodDA(financialPeriodDA);
            this.btnClose.fire();

        } catch (Exception e) {
            errorMessage(e);
        } finally {
            this.btnSave.getScene().setCursor(Cursor.DEFAULT);
        }
    }

    private void clear() {
        cboFinancialPeriod.setValue(null);
        chkRemeberSelection.setSelected(false);

    }

}
