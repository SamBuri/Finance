/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.entities;

import com.saburi.common.entities.DBEntity;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.validation.constraints.Size;
import com.saburi.finance.utils.FinanceEnums.AccountTypes;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import com.saburi.finance.utils.FinanceEnums.AccountActions;
import javax.persistence.EnumType;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class GeneralLedger extends DBEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private int ledgerID;
    @NotNull(message = "The field: Journal Entry Detail cannot be null")
    @OneToOne
    @JoinColumn(name = "journalEntryDetailID", foreignKey = @ForeignKey(name = "fkJournalEntryDetailIDGeneralLedger"))
    private JournalEntryDetail journalEntryDetail;
    @NotNull(message = "The field: Posting Date cannot be null")
    @Column(name = "postingDate", nullable = false)
    private LocalDate postingDate;
    @Size(max = 20, message = "The field: Account ID size cannot be greater than 20")
    @Column(length = 20)
    private String accountID;
    @Size(max = 100, message = "The field: Account Name size cannot be greater than 100")
    @Column(length = 100)
    private String accountName;
    @Enumerated(EnumType.STRING)
    private AccountTypes accountType;
    @Size(max = 100, message = "The field: Description size cannot be greater than 100")
    @Column(length = 100)
    private String description;
    private double amount;
    private double debit;
    private double credit;
    private double balance;

    public GeneralLedger() {
    }

    public GeneralLedger(JournalEntryDetail journalEntryDetail, LocalDate postingDate, String accountID, String accountName, AccountTypes accountType, String description, double amount, double balance) {
        this.journalEntryDetail = journalEntryDetail;
        this.postingDate = postingDate;
        this.accountID = accountID;
        this.accountName = accountName;
        this.accountType = accountType;
        this.description = description;
        this.amount = amount;
        AccountActions accountAction = journalEntryDetail.getAccountAction();
        this.debit = accountAction.equals(AccountActions.Debit)?amount:0;
        this.credit = accountAction.equals(AccountActions.Credit)?amount:0;
        this.balance = balance;

    }

    public int getLedgerID() {
        return ledgerID;
    }

 
    public JournalEntryDetail getJournalEntryDetail() {
        return journalEntryDetail;
    }

    public void setJournalEntryDetail(JournalEntryDetail journalEntryDetail) {
        this.journalEntryDetail = journalEntryDetail;
    }

    public LocalDate getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(LocalDate postingDate) {
        this.postingDate = postingDate;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeneralLedger)) {
            return false;
        }

        GeneralLedger generalLedger = (GeneralLedger) o;

        return this.getId().equals(generalLedger.getId());
    }

    @Override
    public int hashCode() {
        return this.ledgerID;

    }

    @Override
    public Object getId() {
        return this.ledgerID;
    }

    @Override
    public String getDisplayKey() {
        return this.accountName;
    }

}
