/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.dbaccess;

import com.saburi.common.dbaccess.DBAccess;
import com.saburi.finance.entities.Refund;
import javafx.beans.property.*;
import java.util.ArrayList;
import com.saburi.common.entities.AppRevisionEntity;
import java.util.List;
import com.saburi.common.utils.SearchColumn;
import com.saburi.common.utils.SearchColumn.SearchDataTypes;
import org.hibernate.envers.RevisionType;
import com.saburi.finance.entities.CreditNote;
import java.time.LocalDate;
import static com.saburi.common.utils.Utilities.formatDate;
import com.saburi.finance.utils.FinanceEnums.PayModes;
import com.saburi.finance.entities.BankAccount;
import com.saburi.finance.entities.Currency;
import com.saburi.finance.entities.Customer;
import com.saburi.common.entities.DBEntity;
import com.saburi.finance.entities.FinancialPeriod;
import com.saburi.finance.entities.Invoice;
import com.saburi.finance.entities.JournalEntry;
import com.saburi.finance.entities.JournalEntryDetail;
import com.saburi.finance.entities.Receipt;
import com.saburi.finance.entities.ReceiptInvoice;
import static com.saburi.common.utils.Utilities.formatNumber;
import com.saburi.finance.entities.RefundReceiptInvoice;
import com.saburi.finance.utils.FinanceEnums.AccountActions;
import com.saburi.finance.utils.FinanceEnums.AccountGroups;
import com.saburi.finance.utils.FinanceEnums.AccountTypes;
import com.saburi.finance.utils.FinanceEnums.DocumentTypes;
import com.saburi.common.utils.CommonEnums.EntryModes;
import com.saburi.finance.utils.FinanceEnums.JournalTypes;
import com.saburi.finance.utils.FinanceEnums.PostStatus;
import com.saburi.common.utils.CommonEnums.Rights;
import java.util.LinkedHashMap;
import java.util.Map;

public class RefundDA extends DBAccess {

    private Refund refund = new Refund();
    private final SimpleStringProperty creditNoteDisplay = new SimpleStringProperty(this, "creditNoteDisplay");
    private final SimpleObjectProperty creditNoteID = new SimpleObjectProperty(this, "creditNoteID");
    private CreditNote creditNote;
    private final SimpleObjectProperty refundDate = new SimpleObjectProperty(this, "refundDate");
    private final SimpleStringProperty refundDateDisplay = new SimpleStringProperty(this, "refundDateDisplay");
    private final SimpleObjectProperty payMode = new SimpleObjectProperty(this, "payMode");
    private final SimpleStringProperty bankAccountDisplay = new SimpleStringProperty(this, "bankAccountDisplay");
    private final SimpleObjectProperty bankAccountID = new SimpleObjectProperty(this, "bankAccountID");
    private BankAccount bankAccount;
    private final SimpleDoubleProperty amount = new SimpleDoubleProperty(this, "amount");
    private final SimpleStringProperty amountDisplay = new SimpleStringProperty(this, "amountDisplay");
    private final SimpleStringProperty amountWords = new SimpleStringProperty(this, "amountWords");
    private final SimpleStringProperty customerID = new SimpleStringProperty(this, "customerID");
    private final SimpleStringProperty customerName = new SimpleStringProperty(this, "customerName");
    private Invoice invoice;
    private final SimpleStringProperty invoiceID = new SimpleStringProperty(this, "invoiceID");
    private final SimpleStringProperty invoiceDate = new SimpleStringProperty(this, "invoiceDate");
    private final SimpleStringProperty creditNoteDate = new SimpleStringProperty(this, "creditNoteDate");
    private List<RefundReceiptInvoice> refundReceiptInvoices = new ArrayList<>();

    public RefundDA() {
        createSearchColumns();
    }

    public RefundDA(String persistenceUnit) {
        super(persistenceUnit);
    }

    public RefundDA(Refund refund) {
        this.refund = refund;
        initialseProprties();
        createSearchColumns();
    }

    public RefundDA(String persistenceUnit, Refund refund) {
        super(persistenceUnit);
        this.refund = refund;
        initialseProprties();
        createSearchColumns();
    }

    public RefundDA(CreditNote creditNote, LocalDate refundDate, PayModes payMode, BankAccount bankAccount, double amount, String amountWords) {
        this.refund = new Refund(creditNote, refundDate, payMode, bankAccount, amount, amountWords);
        initialseProprties();
        createSearchColumns();
    }

    public RefundDA(String persistenceUnit, CreditNote creditNote, LocalDate refundDate, PayModes payMode, BankAccount bankAccount, double amount, String amountWords) {
        super(persistenceUnit);
        this.refund = new Refund(creditNote, refundDate, payMode, bankAccount, amount, amountWords);
        initialseProprties();
        createSearchColumns();
    }

    public CreditNote getCreditNote() {
        return creditNote;
    }

    public Object getCreditNoteID() {
        return creditNoteID.get();
    }

    public String getCreditNoteDisplay() {
        return creditNoteDisplay.get();
    }

    public CreditNoteDA getCreditNoteDA() {
        return this.creditNote != null ? new CreditNoteDA(this.creditNote) : null;
    }

    public void setCreditNote(CreditNote creditNote) {
        refund.setCreditNote(creditNote);
        this.creditNote = creditNote;
        this.creditNoteID.set(creditNote.getId());
        this.creditNoteDisplay.set(creditNote.getDisplayKey());
    }

    public Object getRefundDate() {
        return refundDate.get();
    }

    public String getRefundDateDisplay() {
        return refundDateDisplay.get();
    }

    public void setRefundDate(LocalDate refundDate) {
        refund.setRefundDate(refundDate);
        this.refundDate.set(refundDate);
    }

    public Object getPayMode() {
        return payMode.get();
    }

    public void setPayMode(PayModes payMode) {
        refund.setPayMode(payMode);
        this.payMode.set(payMode);
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public Object getBankAccountID() {
        return bankAccountID.get();
    }

    public String getBankAccountDisplay() {
        return bankAccountDisplay.get();
    }

    public BankAccountDA getBankAccountDA() {
        return this.bankAccount != null ? new BankAccountDA(this.bankAccount) : null;
    }

    public void setBankAccount(BankAccount bankAccount) {
        refund.setBankAccount(bankAccount);
        this.bankAccount = bankAccount;
        this.bankAccountID.set(bankAccount.getId());
        this.bankAccountDisplay.set(bankAccount.getDisplayKey());
    }

    public double getAmount() {
        return amount.get();
    }

    public String getAmountDisplay() {
        return amountDisplay.get();
    }

    public void setAmount(double amount) {
        refund.setAmount(amount);
        this.amount.set(amount);
        this.amountDisplay.set(formatNumber(amount));
    }

    public String getAmountWords() {
        return amountWords.get();
    }

    public void setAmountWords(String amountWords) {
        refund.setAmountWords(amountWords);
        this.amountWords.set(amountWords);
    }

    public String getCustomerID() {
        return customerID.get();
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public String getInvoiceID() {
        return invoiceID.get();
    }

    public String getInvoiceDate() {
        return invoiceDate.get();
    }

    public String getCreditNoteDate() {
        return creditNoteDate.get();
    }

    public List<RefundReceiptInvoice> getRefundReceiptInvoices() {
        return refundReceiptInvoices;
    }

    public List<RefundReceiptInvoiceDA> getRefundReceiptInvoiceDAs() {
        return RefundReceiptInvoiceDA.getRefundReceiptInvoiceDAs(refund.getRefundReceiptInvoices());
    }

    public void setRefundReceiptInvoices(List<RefundReceiptInvoice> refundReceiptInvoices) {
        refund.setRefundReceiptInvoices(refundReceiptInvoices);
        this.refundReceiptInvoices = refundReceiptInvoices;
    }

    public void setRefundReceiptInvoiceDAs(List<RefundReceiptInvoiceDA> refundReceiptInvoiceDAs) {
        this.refund.setRefundReceiptInvoices(RefundReceiptInvoiceDA.getRefundReceiptInvoiceList(refundReceiptInvoiceDAs));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RefundDA)) {
            return false;
        }

        RefundDA refundDA = (RefundDA) o;

        if (refundDA.getDBEntity() == null || this.getDBEntity() == null) {
            return false;
        }
        return this.getId().equals(refundDA.getId());
    }

    @Override
    public int hashCode() {
        return refund.getId().hashCode();
    }

    private void initialseProprties() {
        this.dBEntity = refund;
        this.creditNote = refund.getCreditNote();
        if (this.creditNote != null) {
            this.creditNoteID.set(creditNote.getId());
            this.creditNoteDisplay.set(creditNote.getDisplayKey());
            this.creditNoteDate.set(formatDate(creditNote.getCreditNoteDate()));
            this.invoice = creditNote.getCreditNoteApproval().getCreditNoteRequest().getInvoice();
            if (invoice != null) {
                this.customerID.set(invoice.getBillTo().getCustomerID());
                this.customerName.set(invoice.getBillTo().getCustomerName());
                this.invoiceID.set(invoice.getInvoiceID());
                this.invoiceDate.set(formatDate(invoice.getInvoiceDate()));

            }
        }
        this.refundDate.set(refund.getRefundDate());
        this.refundDateDisplay.set(formatDate(refund.getRefundDate()));
        this.payMode.set(refund.getPayMode());
        this.bankAccount = refund.getBankAccount();
        if (this.bankAccount != null) {
            this.bankAccountID.set(bankAccount.getId());
            this.bankAccountDisplay.set(bankAccount.getDisplayKey());
        }
        this.amount.set(refund.getAmount());
        this.amountDisplay.set(formatNumber(refund.getAmount()));
        this.amountWords.set(refund.getAmountWords());

        initCommonProprties();
    }

    private void createSearchColumns() {
        this.searchColumns.add(new SearchColumn("creditNoteID", "Credit Note ID", this.creditNoteID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("creditNoteDisplay", "Credit Note", this.creditNoteDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("refundDate", "Refund Date", this.refundDate.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("payMode", "Pay Mode", this.payMode.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal));
        this.searchColumns.add(new SearchColumn("bankAccountID", "Bank Account ID", this.bankAccountID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("bankAccountDisplay", "Bank Account", this.bankAccountDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("amount", "Amount", this.amount.get(), SearchDataTypes.NUMBER));
        this.searchColumns.add(new SearchColumn("amountWords", "Amount Words", this.amountWords.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("customerID", "Customer ID", this.customerID.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("customerName", "Customer Name", this.customerName.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("invoice", "Invoice", this.invoiceID.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("invoiceDate", "Invoice Date", this.invoiceDate.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("creditNoteDate", "Credit Note Date", this.creditNoteDate.get(), SearchDataTypes.STRING));
        this.searchColumns.addAll(this.getDefaultSearchColumns());
    }

    @Override
    public List<SearchColumn> getSearchColumns() {
        return this.searchColumns;
    }

    @Override
    public Object getId() {
        return this.refund.getId();
    }

    @Override
    public String getDisplayKey() {
        return this.refund.getDisplayKey();
    }

    public static List<RefundDA> getRefundDAs(List<Refund> refunds) {
        List<RefundDA> list = new ArrayList<>();
        refunds.forEach((refund) -> {
            list.add(new RefundDA(refund));
        });
        return list;
    }

    public static List<Refund> getRefundList(List<RefundDA> refundDAs) {
        List<Refund> refunds = new ArrayList<>();
        refundDAs.forEach(a -> refunds.add(a.refund));
        return refunds;
    }

    public boolean save() throws Exception {
        return this.saveRefunds();

    }

    public boolean update() throws Exception {
        throw new Exception("Refund can't be updated");

    }

    public boolean delete() {
        return false;

    }

    public Refund getRefund(String refundID) {
        return (Refund) super.findJoin(Refund.class, refundID, "refundReceipts");
    }

    public Refund getRefund() {
        return this.refund;
    }

    public List<Refund> getRefunds() {
        return super.find(Refund.class);
    }

    @Override
    public List<DBAccess> get() {
        List<DBAccess> list = new ArrayList<>();
        selectQuery(Refund.class).forEach(o -> list.add(new RefundDA((Refund) o)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public RefundDA get(String refundID) throws Exception {
        Refund oRefund = getRefund(refundID);
        if (oRefund == null) {
            throw new Exception("No Record with id: " + refundID);
        }
        return new RefundDA(oRefund);
    }

    public List<RefundDA> get(String columName, Object value) {
        List<RefundDA> list = new ArrayList<>();
        super.selectQuery(Refund.class, columName, value).forEach(da -> list.add(new RefundDA((Refund) da)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public List<RefundDA> toDaList(List<Refund> refunds) {
        List<RefundDA> refundDAs = new ArrayList<>();
        refunds.forEach(s -> refundDAs.add(new RefundDA(s)));
        return refundDAs;
    }

    public List<DBAccess> toDBAccessList(List<Refund> refunds) {
        List<DBAccess> dbAccesses = new ArrayList<>();
        refunds.forEach(s -> dbAccesses.add(new RefundDA(s)));
        return dbAccesses;
    }

    public int getMax(String columnName) {
        return super.getMax(Refund.class, columnName);
    }

    public int getMax(String columnName, String comparatorColumn, Object comparatorVaue) {
        return super.getMax(Refund.class, columnName, comparatorColumn, comparatorVaue);
    }

    public List<Refund> getRefunds(String columName, Object value) {
        return super.find(Refund.class, columName, value);
    }

    @Override
    public List<DBAccess> getRevisions() {
        try {

            List<Object[]> list = getEntityRevisions(Refund.class);
            List<DBAccess> dBAccesses = new ArrayList<>();
            list.forEach(e -> {

                RefundDA refundDA = new RefundDA((Refund) e[0]);
                refundDA.revisionEntity = (AppRevisionEntity) e[1];
                refundDA.oRevisionType = (RevisionType) e[2];
                refundDA.initRevProprties();
                refundDA.searchColumns.addAll(refundDA.getRevSearchColumns());
                dBAccesses.add(refundDA);

            });

            return dBAccesses;
        } catch (Exception e) {
            throw e;
        } finally {
            if (entityManager == null) {
                entityManager.close();
            }
        }

    }

    public boolean isValid() throws Exception {
        double refundReceiptInvoiceAmount = refund.getRefundReceiptInvoices().stream().mapToDouble(RefundReceiptInvoice::getAmount).sum();
        double toRefundAmount = creditNote.getToRefundAmount();
        double currentBankBalance = new BankAccountDA().getBankAccount(this.bankAccount.getBankAccountID()).getBalance();
        if (refundReceiptInvoiceAmount > toRefundAmount) {
            throw new Exception("The Refund Receipt Amount: " + formatNumber(refundReceiptInvoiceAmount) + " cannot be greater than To Refund "
                    + "Amount: " + formatNumber(toRefundAmount));
        }

        if (refundReceiptInvoiceAmount < getAmount()) {
            throw new Exception("The Refund Receipt Amount: " + formatNumber(refundReceiptInvoiceAmount) + " cannot be less than "
                    + "To Refund Amount: " + formatNumber(toRefundAmount));
        }
        if (currentBankBalance < toRefundAmount) {
            throw new Exception("The Bank Balance for Bank Account: " + bankAccount.getDisplayKey() + " cannot be less"
                    + " than To Refund Amount: " + formatNumber(toRefundAmount) + " You need to top up with:"
                    + " " + formatNumber(toRefundAmount - currentBankBalance) + " to continue or use a different account");
        }
        return true;

    }

    public JournalEntry makeJournalEntries() throws Exception {
        JournalEntryDA journalEntryDA = new JournalEntryDA();
        FinancialPeriod financialPeriod = FinancialPeriodDA.getCurrentFinancialPeriodDA().getFinancialPeriod();
        Currency currency = new CurrencyDA().getDefaultCurrency();
        int invoiceIdHelper = journalEntryDA.getNextIdHelper(financialPeriod);
        String journalID = journalEntryDA.getNextJournalID(invoiceIdHelper, financialPeriod.getPeriodID());

        Customer billToCustomer = refund.getCreditNote().getCreditNoteApproval().getCreditNoteRequest().getInvoice().getBillTo();
        String narration = "Refund on Credit Note ID: " + refund.getCreditNote().getCreditNoteID() + " for Customer: "
                + billToCustomer.getDisplayKey();
        String referenceNo = "";

        BankAccount bkAccount = refund.getBankAccount();
        List<JournalEntryDetail> journalEntryDetails = new ArrayList<>();

        JournalEntry journalEntry = new JournalEntry(invoiceIdHelper, financialPeriod, journalID,
                refund.getRefundDate(), narration, DocumentTypes.Refund, refund.getRefundID(), referenceNo, currency,
                refund.getAmount(), currency.getBuying(), refund.getAmount(),
                refund.getAmountWords(), PostStatus.Pending, JournalTypes.Bank, EntryModes.System);

        journalEntryDetails.add(new JournalEntryDetail(AccountGroups.Customer, journalEntry, billToCustomer.getCustomerID(),
                billToCustomer.getDisplayKey(), AccountTypes.Asset, AccountActions.Debit, 1, 1, refund.getAmount(),
                narration, null));

        journalEntryDetails.add(new JournalEntryDetail(AccountGroups.Bank, journalEntry, bkAccount.getBankAccountID(),
                bkAccount.getDisplayKey(), AccountTypes.Asset, AccountActions.Credit, 1, 1, refund.getAmount(),
                narration, null));

        journalEntry.setJournalEntryDetails(journalEntryDetails);
        return journalEntry;
    }

    private boolean saveRefunds() throws Exception {
        if (!isValid()) {
            return false;
        }
        creditNote.setToRefundAmount(0);
        List<RefundReceiptInvoice> lRefundReceiptInvoices = refund.getRefundReceiptInvoices();
        List<ReceiptInvoice> receiptInvoices = new ArrayList<>();
        List<Receipt> receipts = new ArrayList<>();
        for (RefundReceiptInvoice invR : lRefundReceiptInvoices) {
            ReceiptInvoice receiptInvoice = new ReceiptInvoiceDA().getReceiptInvoice(invR.getReceiptInvoice().getReceiptInvoiceID());
            Receipt receipt = new ReceiptDA().getReceipt(receiptInvoice.getReceipt().getReceiptID());
            receiptInvoice.setAmountRefunded(receiptInvoice.getAmountRefunded() + invR.getAmount());
            receipt.setAmountRefunded(receipt.getAmountRefunded() + invR.getAmount());

            if (invR.getAmount() > receiptInvoice.getAmount()) {
                throw new Exception("The Amount to Refund: " + receiptInvoice.getAmount() + " on Invoice ID:  " + this.invoice.getInvoiceID() + " and Receipt ID: " + receipt.getReceiptID() + "can't be greater than the amount paid: " + receiptInvoice.getAmount());
            }
            receiptInvoices.add(receiptInvoice);

            receipts.add(receipt);

        }
        Invoice invoiceToEdit =  new InvoiceDA().getInvoice(this.invoice.getInvoiceID());
        invoiceToEdit.setAmountPaid(invoiceToEdit.getAmountRefunded()+getAmount());
        
        List<DBEntity> toSaveEntities = new ArrayList<>();
        List<DBEntity> toUpdateEntities = new ArrayList<>();
        toSaveEntities.add(this.refund);
        toSaveEntities.add(makeJournalEntries());
        toUpdateEntities.add(this.creditNote);
        toUpdateEntities.add(invoiceToEdit);
        toUpdateEntities.addAll(receipts);
        toUpdateEntities.addAll(receiptInvoices);

        Map<List<? extends DBEntity>, Rights> map = new LinkedHashMap<>();
        map.put(toSaveEntities, Rights.Create);
        map.put(toUpdateEntities, Rights.Update);

        return super.processBatchList(map) > 0;
    }
}