/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.entities;

import com.saburi.common.entities.DBEntity;
import com.saburi.common.entities.LookupData;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import javax.persistence.Column;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import com.saburi.finance.utils.FinanceEnums.AccountGroups;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.OneToOne;
import com.saburi.finance.utils.FinanceEnums.AccountTypes;
import com.saburi.finance.utils.FinanceEnums.AccountActions;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class JournalEntryDetail extends DBEntity {

    @Id
    @NotNull(message = "The field: Journal Entry Detail ID cannot be null")
    @Size(max = 100, message = "The field: Journal Entry Detail ID size cannot be greater than 100")
    @Column(length = 100, updatable = false)
    private String journalEntryDetailID;
    @NotNull(message = "The field: Account Group cannot be null")
    @Enumerated
    private AccountGroups accountGroup;
    @NotNull(message = "The field: Journal Entry cannot be null")
    @OneToOne
    @JoinColumn(name = "journalEntryID", foreignKey = @ForeignKey(name = "fkJournalEntryIDJournalEntryDetail"))
    private JournalEntry journalEntry;
    @Size(max = 100, message = "The field: Account ID size cannot be greater than 100")
    @NotNull(message = "The field: Account ID cannot be null")
    @Column(length = 100)
    private String accountID;
    @Size(max = 100, message = "The field: Account Name size cannot be greater than 100")
    @NotNull(message = "The field: Account Name cannot be null")
    @Column(length = 100)
    private String accountName;
    @Enumerated
    private AccountTypes accountType;
    @Enumerated
    private AccountActions accountAction;
    private int quantity;
    private double unitPrice;
    private double amount;
    @Size(max = 100, message = "The field: Notes size cannot be greater than 100")
    @Column(length = 100)
    private String notes;
    @OneToOne
    @JoinColumn(name = "locationID", foreignKey = @ForeignKey(name = "fkLocationIDJournalEntryDetail"))
    private LookupData location;

    public JournalEntryDetail() {
    }

    public JournalEntryDetail(AccountGroups accountGroup, JournalEntry journalEntry, String accountID, String accountName, AccountTypes accountType, AccountActions accountAction, int quantity, double unitPrice, double amount, String notes, LookupData location) {
        this.accountGroup = accountGroup;
        this.journalEntry = journalEntry;
        this.accountID = accountID;
        this.accountName = accountName;
        this.accountType = accountType;
        this.accountAction = accountAction;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.notes = notes;
        this.location = location;
        setJournalEntryDetailID();

    }

    public String getJournalEntryDetailID() {
        return journalEntryDetailID;
    }

    private void setJournalEntryDetailID() {
        String journalID = journalEntry == null ? "" : journalEntry.getJournalID();
        String accountGroupPart = accountGroup == null ? "" : String.valueOf(this.accountGroup.ordinal());
        this.journalEntryDetailID = journalID.concat(accountGroupPart).concat((accountID == null ? "" : accountID));
    }

    public AccountGroups getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(AccountGroups accountGroup) {
        this.accountGroup = accountGroup;
        setJournalEntryDetailID();
    }

    public JournalEntry getJournalEntry() {
        return journalEntry;
    }

    public void setJournalEntry(JournalEntry journalEntry) {
        this.journalEntry = journalEntry;
        setJournalEntryDetailID();
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
        setJournalEntryDetailID();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public AccountTypes getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypes accountType) {
        this.accountType = accountType;
    }

    public AccountActions getAccountAction() {
        return accountAction;
    }

    public void setAccountAction(AccountActions accountAction) {
        this.accountAction = accountAction;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LookupData getLocation() {
        return location;
    }

    public void setLocation(LookupData location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JournalEntryDetail)) {
            return false;
        }

        JournalEntryDetail journalEntryDetail = (JournalEntryDetail) o;

        return this.getId().equals(journalEntryDetail.getId());
    }

    @Override
    public int hashCode() {
        return this.journalEntryDetailID.hashCode();

    }

    @Override
    public Object getId() {
        return this.journalEntryDetailID;
    }

    @Override
    public String getDisplayKey() {
        return this.accountName;
    }

}