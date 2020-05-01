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
import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.OneToOne;
import com.saburi.finance.utils.FinanceEnums.DocumentTypes;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import com.saburi.finance.utils.FinanceEnums.AccountActions;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class CustomerLedger extends DBEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 100, updatable = false)
    private int ledgerID;
    @OneToOne
    @JoinColumn(name = "journalEntryDetailID", foreignKey = @ForeignKey(name = "fkJournalEntryDetailIDCustomerLedger"))
    private JournalEntryDetail journalEntryDetail;
    @Size(max = 20, message = "The field: Customer ID size cannot be greater than 20")
    @Column(length = 20)
    private String customerID;
    @Size(max = 100, message = "The field: Customer Name size cannot be greater than 100")
    @Column(length = 100)
    private String customerName;
    private LocalDate postingDate;
    @Enumerated
    private DocumentTypes documentType;
    @Size(max = 100, message = "The field: Description size cannot be greater than 100")
    @Column(length = 100)
    private String description;
    @Size(max = 100, message = "The field: Document No size cannot be greater than 100")
    @Column(length = 100)
    private String documentNo;
    @Size(max = 100, message = "The field: Reference No size cannot be greater than 100")
    @Column(length = 100)
    private String referenceNo;
    private double amount;
    private double debit;
    private double credit;
    private double balance;

    public CustomerLedger() {
    }

    public CustomerLedger(JournalEntryDetail journalEntryDetail, String customerID, String customerName, LocalDate postingDate, DocumentTypes documentType, String description, String documentNo, String referenceNo, double amount, double balance) {
        this.journalEntryDetail = journalEntryDetail;
        this.customerID = customerID;
        this.customerName = customerName;
        this.postingDate = postingDate;
        this.documentType = documentType;
        this.description = description;
        this.documentNo = documentNo;
        this.referenceNo = referenceNo;
        this.amount = amount;
        AccountActions accountAction = journalEntryDetail.getAccountAction();
        this.debit = accountAction.equals(AccountActions.Debit)?amount:0;
        this.credit = accountAction.equals(AccountActions.Credit)?amount:0;
        this.balance = balance;

    }

    public int getLedgerID() {
        return ledgerID;
    }

    public void setLedgerID(int ledgerID) {
        this.ledgerID = ledgerID;
    }

    public JournalEntryDetail getJournalEntryDetail() {
        return journalEntryDetail;
    }

    public void setJournalEntryDetail(JournalEntryDetail journalEntryDetail) {
        this.journalEntryDetail = journalEntryDetail;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(LocalDate postingDate) {
        this.postingDate = postingDate;
    }

    public DocumentTypes getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentTypes documentType) {
        this.documentType = documentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
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
        if (!(o instanceof CustomerLedger)) {
            return false;
        }

        CustomerLedger customerLedger = (CustomerLedger) o;

        return this.getId().equals(customerLedger.getId());
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
        return this.customerName;
    }

}
