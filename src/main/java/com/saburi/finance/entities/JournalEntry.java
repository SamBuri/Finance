/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.entities;

import com.saburi.common.entities.DBEntity;
import com.saburi.common.utils.CommonEnums;
import com.saburi.common.utils.CommonEnums.EntryModes;
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
import com.saburi.finance.utils.FinanceEnums.DocumentTypes;
import javax.persistence.Enumerated;
import com.saburi.finance.utils.FinanceEnums.PostStatus;
import com.saburi.finance.utils.FinanceEnums.JournalTypes;
import java.util.List;
import javax.persistence.CascadeType;
import java.util.ArrayList;
import javax.persistence.OneToMany;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class JournalEntry extends DBEntity {

    @Column(updatable = false)
    private int idHelper;
    @NotNull(message = "The field: Financial Period cannot be null")
    @OneToOne
    @JoinColumn(name = "financialPeriodID", foreignKey = @ForeignKey(name = "fkFinancialPeriodIDJournalEntry"))
    private FinancialPeriod financialPeriod;
    @Id
    @NotNull(message = "The field: Journal ID cannot be null")
    @Size(max = 100, message = "The field: Journal ID size cannot be greater than 100")
    @Column(length = 100, updatable = false)
    private String journalID;
    @NotNull(message = "The field: Entry Date cannot be null")
    @Column(name = "entryDate", nullable = false)
    private LocalDate entryDate;
    @Size(max = 200, message = "The field: Narration size cannot be greater than 200")
    @Column(length = 200)
    private String narration;
    @Enumerated
    private DocumentTypes documentType;
    @Size(max = 100, message = "The field: Document No size cannot be greater than 100")
    @Column(length = 100)
    private String documentNo;
    @Size(max = 100, message = "The field: Reference No size cannot be greater than 100")
    @Column(length = 100)
    private String referenceNo;
    @OneToOne
    @JoinColumn(name = "currencyID", foreignKey = @ForeignKey(name = "fkCurrencyIDJournalEntry"))
    private Currency currency;
    private double baseAmount;
    private double exchangeRate;
    private double amount;
    @Size(max = 200, message = "The field: Amount Words size cannot be greater than 200")
    @Column(length = 200)
    private String amountWords;
    @Enumerated
    private PostStatus postStatus;
    @Enumerated
    private JournalTypes journalType;
    @Enumerated
    private EntryModes entryMode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "journalEntry")
    private List<JournalEntryDetail> journalEntryDetails = new ArrayList<>();

    public JournalEntry() {
    }

    public JournalEntry(int idHelper, FinancialPeriod financialPeriod, String journalID, LocalDate entryDate, String narration, DocumentTypes documentType, String documentNo, String referenceNo, Currency currency, double baseAmount, double exchangeRate, double amount, String amountWords, PostStatus postStatus, JournalTypes journalType, EntryModes entryMode) {
        this.idHelper = idHelper;
        this.financialPeriod = financialPeriod;
        this.journalID = journalID;
        this.entryDate = entryDate;
        this.narration = narration;
        this.documentType = documentType;
        this.documentNo = documentNo;
        this.referenceNo = referenceNo;
        this.currency = currency;
        this.baseAmount = baseAmount;
        this.exchangeRate = exchangeRate;
        this.amount = amount;
        this.amountWords = amountWords;
        this.postStatus = postStatus;
        this.journalType = journalType;
        this.entryMode = entryMode;

    }

    public int getIdHelper() {
        return idHelper;
    }

    public void setIdHelper(int idHelper) {
        this.idHelper = idHelper;
    }

    public FinancialPeriod getFinancialPeriod() {
        return financialPeriod;
    }

    public void setFinancialPeriod(FinancialPeriod financialPeriod) {
        this.financialPeriod = financialPeriod;
    }

    public String getJournalID() {
        return journalID;
    }

    public void setJournalID(String journalID) {
        this.journalID = journalID;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public DocumentTypes getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentTypes documentType) {
        this.documentType = documentType;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(double baseAmount) {
        this.baseAmount = baseAmount;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
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

    public PostStatus getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(PostStatus postStatus) {
        this.postStatus = postStatus;
    }

    public JournalTypes getJournalType() {
        return journalType;
    }

    public void setJournalType(JournalTypes journalType) {
        this.journalType = journalType;
    }

    public EntryModes getEntryMode() {
        return entryMode;
    }

    public void setEntryMode(EntryModes entryMode) {
        this.entryMode = entryMode;
    }

    public List<JournalEntryDetail> getJournalEntryDetails() {
        return journalEntryDetails;
    }

    public void setJournalEntryDetails(List<JournalEntryDetail> journalEntryDetails) {
        this.journalEntryDetails = journalEntryDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JournalEntry)) {
            return false;
        }

        JournalEntry journalEntry = (JournalEntry) o;

        return this.getId().equals(journalEntry.getId());
    }

    @Override
    public int hashCode() {
        return this.journalID.hashCode();

    }

    @Override
    public Object getId() {
        return this.journalID;
    }

    @Override
    public String getDisplayKey() {
        return this.journalID;
    }

}
