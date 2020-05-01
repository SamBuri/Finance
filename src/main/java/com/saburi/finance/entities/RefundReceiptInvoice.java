/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.entities;

import com.saburi.common.entities.DBEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class RefundReceiptInvoice extends DBEntity {

    @Id
    @NotNull(message = "The field: RefundReceiptInvoiceID cannot be null")
    @Column(updatable = false)
    @GeneratedValue
    private int refundReceiptInvoiceID;
    @NotNull(message = "The field: Refund cannot be null")
    @OneToOne
    @JoinColumn(name = "refundID", foreignKey = @ForeignKey(name = "fkRefundIDRefundReceiptInvoice"))
    private Refund refund;
    @NotNull(message = "The field: Receipt Invoice cannot be null")
    @OneToOne
    @JoinColumn(name = "receiptInvoiceID", foreignKey = @ForeignKey(name = "fkReceiptInvoiceIDRefundReceiptInvoice"))
    private ReceiptInvoice receiptInvoice;
    private double amount;

    public RefundReceiptInvoice() {
    }

    public RefundReceiptInvoice(Refund refund, ReceiptInvoice receiptInvoice, double amount) {
        this.refund = refund;
        this.receiptInvoice = receiptInvoice;
        this.amount = amount;

    }

    public int getRefundReceiptInvoiceID() {
        return refundReceiptInvoiceID;
    }

    public void setRefundReceiptInvoiceID(int refundReceiptInvoiceID) {
        this.refundReceiptInvoiceID = refundReceiptInvoiceID;
    }

    public Refund getRefund() {
        return refund;
    }

    public void setRefund(Refund refund) {
        this.refund = refund;
    }

    public ReceiptInvoice getReceiptInvoice() {
        return receiptInvoice;
    }

    public void setReceiptInvoice(ReceiptInvoice receiptInvoice) {
        this.receiptInvoice = receiptInvoice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.refund);
        hash = 97 * hash + Objects.hashCode(this.receiptInvoice);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RefundReceiptInvoice other = (RefundReceiptInvoice) obj;
        if (!Objects.equals(this.refund, other.refund)) {
            return false;
        }
        if (!Objects.equals(this.receiptInvoice, other.receiptInvoice)) {
            return false;
        }
        return true;
    }

    

    @Override
    public Object getId() {
        return this.refundReceiptInvoiceID;
    }

    @Override
    public String getDisplayKey() {
        return this.refund.getDisplayKey() + " " + this.receiptInvoice.getDisplayKey();
    }

}
