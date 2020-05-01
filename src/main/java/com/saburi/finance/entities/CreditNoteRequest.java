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
import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import com.saburi.finance.utils.FinanceEnums.RequestStatus;
import javax.persistence.Enumerated;
import java.util.List;
import javax.persistence.CascadeType;
import java.util.ArrayList;
import javax.persistence.OneToMany;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)

public class CreditNoteRequest extends DBEntity {

    @Column(updatable = false)
    private int idHelper;
    @NotNull(message = "The field: Invoice cannot be null")
    @OneToOne
    @JoinColumn(name = "invoiceID", foreignKey = @ForeignKey(name = "fkInvoiceIDCreditNoteRequest"))
    private Invoice invoice;
    @Id
    @NotNull(message = "The field: Credit Note RequestID cannot be null")
    @Size(max = 20, message = "The field: Credit Note RequestID size cannot be greater than 20")
    @Column(length = 20, updatable = false)
    private String creditNoteRequestID;
    private LocalDate requestDate;
    @Enumerated
    private RequestStatus requestStatus;
    private double amount;
    @Size(max = 200, message = "The field: AmountWords size cannot be greater than 200")
    @Column(length = 200)
    private String amountWords;
    @Size(max = 200, message = "The field: Notes size cannot be greater than 200")
    @Column(length = 200)
    private String notes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditNoteRequest")
    private List<CreditNoteRequestDetails> creditNoteRequestDetails = new ArrayList<>();

    public CreditNoteRequest() {
    }

    public CreditNoteRequest(int idHelper, Invoice invoice, String creditNoteRequestID, LocalDate requestDate, RequestStatus requestStatus, double amount, String amountWords, String notes) {
        this.idHelper = idHelper;
        this.invoice = invoice;
        this.creditNoteRequestID = creditNoteRequestID;
        this.requestDate = requestDate;
        this.requestStatus = requestStatus;
        this.amount = amount;
        this.amountWords = amountWords;
        this.notes = notes;

    }

    public int getIdHelper() {
        return idHelper;
    }

    public void setIdHelper(int idHelper) {
        this.idHelper = idHelper;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getCreditNoteRequestID() {
        return creditNoteRequestID;
    }

    public void setCreditNoteRequestID(String creditNoteRequestID) {
        this.creditNoteRequestID = creditNoteRequestID;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<CreditNoteRequestDetails> getCreditNoteRequestDetails() {
        return creditNoteRequestDetails;
    }

    public void setCreditNoteRequestDetails(List<CreditNoteRequestDetails> creditNoteRequestDetails) {
        this.creditNoteRequestDetails = creditNoteRequestDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreditNoteRequest)) {
            return false;
        }

        CreditNoteRequest creditNoteRequest = (CreditNoteRequest) o;

        return this.getId().equals(creditNoteRequest.getId());
    }

    @Override
    public int hashCode() {
        return this.creditNoteRequestID.hashCode();

    }

    @Override
    public Object getId() {
        return this.creditNoteRequestID;
    }

    @Override
    public String getDisplayKey() {
        return this.creditNoteRequestID;
    }

}