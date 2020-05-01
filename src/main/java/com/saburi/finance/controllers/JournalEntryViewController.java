package com.saburi.finance.controllers;

import com.saburi.common.controllers.AbstractViewController;
import com.saburi.common.dbaccess.DBAccess;
import static com.saburi.common.utils.FXUIUtils.errorMessage;
import com.saburi.finance.dbaccess.JournalEntryDA;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.MenuItem;
import com.saburi.finance.utils.FinanceEnums;

public class JournalEntryViewController extends AbstractViewController {

    @FXML
    private TableView<DBAccess> tblJournalEntry;

    @FXML
    private MenuItem cmiPost;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.tableView = tblJournalEntry;
            initSearchEvents();

            cmiPost.setOnAction(e -> {
                try {
                    JournalEntryDA selectedItemEntry = (JournalEntryDA) tblJournalEntry.getSelectionModel().getSelectedItem();
                    selectedItemEntry.post();
                    this.list = new JournalEntryDA().get();
                    this.loadTable();
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
    protected void viewContextMenuShowing() {
        super.viewContextMenuShowing();
        JournalEntryDA journalEntryDA = (JournalEntryDA) tblJournalEntry.getSelectionModel().getSelectedItem();
        if (journalEntryDA == null) {
            cmiPost.visibleProperty().set(false);
        } else {
            cmiPost.visibleProperty().set(journalEntryDA.getPostStatus().equals(FinanceEnums.PostStatus.Pending));
        }
    }

}
