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
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import com.saburi.finance.utils.FinanceEnums.PayModes;
import javax.persistence.Enumerated;
import java.util.List;
import javax.persistence.CascadeType;
import java.util.ArrayList;
import javax.persistence.OneToMany;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Refund extends DBEntity {

    @Id
    @NotNull(message = "The field: Refund ID cannot be null")
    @Size(max = 20, message = "The field: Refund ID size cannot be greater than 20")
    @Column(length = 20, updatable = false)
    private String refundID;
    @OneToOne
    @JoinColumn(name = "creditNoteID", foreignKey = @ForeignKey(name = "fkCreditNoteIDRefund"))
    private CreditNote creditNote;
    private LocalDate refundDate;
    @Enumerated
    private PayModes payMode;
    @OneToOne
    @JoinColumn(name = "bankAccountID", foreignKey = @ForeignKey(name = "fkBankAccountIDRefund"))
    private BankAccount bankAccount;
    private double amount;
    @Size(max = 100, message = "The field: Amount Words size cannot be greater than 100")
    @Column(length = 100)
    private String amountWords;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refund")
    private List<RefundReceiptInvoice> refundReceiptInvoices = new ArrayList<>();

    public Refund() {
    }

    public Refund(CreditNote creditNote, LocalDate refundDate, PayModes payMode, BankAccount bankAccount, double amount, String amountWords) {
        this.refundID = creditNote.getCreditNoteID();
        this.creditNote = creditNote;
        this.refundDate = refundDate;
        this.payMode = payMode;
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.amountWords = amountWords;

    }

    public String getRefundID() {
        return refundID;
    }

    public CreditNote getCreditNote() {
        return creditNote;
    }

    public void setCreditNote(CreditNote creditNote) {
        this.creditNote = creditNote;
        this.refundID = creditNote.getCreditNoteID();
    }

    public LocalDate getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(LocalDate refundDate) {
        this.refundDate = refundDate;
    }

    public PayModes getPayMode() {
        return payMode;
    }

    public void setPayMode(PayModes payMode) {
        this.payMode = payMode;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAmountWords() {
        return amountWords;
    }

    public void setAmountWords(String amountWords) {
        this.amountWords = amountWords;
    }

    public List<RefundReceiptInvoice> getRefundReceiptInvoices() {
        return refundReceiptInvoices;
    }

    public void setRefundReceiptInvoices(List<RefundReceiptInvoice> refundReceiptInvoices) {
        this.refundReceiptInvoices = refundReceiptInvoices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Refund)) {
            return false;
        }

        Refund refund = (Refund) o;

        return this.getId().equals(refund.getId());
    }

    @Override
    public int hashCode() {
        return this.refundID.hashCode();

    }

    @Override
    public Object getId() {
        return this.refundID;
    }

    @Override
    public String getDisplayKey() {
        return this.refundID;
    }

}