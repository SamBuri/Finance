/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.dbaccess;

import com.saburi.common.dbaccess.DBAccess;
import com.saburi.common.dbaccess.IDGeneratorDA;
import com.saburi.finance.entities.Receipt;
import javafx.beans.property.*;
import java.util.ArrayList;
import com.saburi.common.entities.AppRevisionEntity;
import com.saburi.finance.entities.BankAccount;
import java.util.List;
import com.saburi.common.utils.SearchColumn;
import com.saburi.common.utils.SearchColumn.SearchDataTypes;
import org.hibernate.envers.RevisionType;
import java.time.LocalDate;
import static com.saburi.common.utils.Utilities.formatDate;
import com.saburi.finance.entities.Customer;
import com.saburi.finance.utils.FinanceEnums.PayModes;
import static com.saburi.common.utils.Utilities.formatNumber;
import com.saburi.finance.entities.Currency;
import com.saburi.common.entities.DBEntity;
import com.saburi.finance.entities.FinancialPeriod;
import com.saburi.finance.entities.Invoice;
import com.saburi.finance.entities.JournalEntry;
import com.saburi.finance.entities.JournalEntryDetail;
import com.saburi.finance.entities.ReceiptInvoice;
import java.util.Map;
import java.util.stream.Collectors;
import com.saburi.finance.utils.FinanceEnums.AccountActions;
import com.saburi.finance.utils.FinanceEnums.AccountGroups;
import com.saburi.finance.utils.FinanceEnums.AccountTypes;
import com.saburi.finance.utils.FinanceEnums.DocumentTypes;
import com.saburi.common.utils.CommonEnums.EntryModes;
import com.saburi.common.utils.CommonEnums.Rights;
import com.saburi.finance.utils.FinanceEnums.JournalTypes;
import com.saburi.finance.utils.FinanceEnums.PostStatus;
import com.saburi.finance.utils.CurrentFinacialPeriod;
import java.util.LinkedHashMap;

public class ReceiptDA extends DBAccess {

    private Receipt receipt = new Receipt();
    private final SimpleIntegerProperty idHelper = new SimpleIntegerProperty(this, "idHelper");
    private final SimpleStringProperty receiptID = new SimpleStringProperty(this, "receiptID");
    private final SimpleObjectProperty receiptDate = new SimpleObjectProperty(this, "receiptDate");
    private final SimpleStringProperty receiptDateDisplay = new SimpleStringProperty(this, "receiptDateDisplay");
    private final SimpleStringProperty customerDisplay = new SimpleStringProperty(this, "customerDisplay");
    private final SimpleObjectProperty customerID = new SimpleObjectProperty(this, "customerID");
    private Customer customer;
    private final SimpleObjectProperty payMode = new SimpleObjectProperty(this, "payMode");
    private final SimpleStringProperty bankAcccountDisplay = new SimpleStringProperty(this, "bankAcccountDisplay");
    private final SimpleObjectProperty bankAcccountID = new SimpleObjectProperty(this, "bankAcccountID");
    private BankAccount bankAcccount;
    private final SimpleDoubleProperty totalBill = new SimpleDoubleProperty(this, "totalBill");
    private final SimpleStringProperty totalBillDisplay = new SimpleStringProperty(this, "totalBillDisplay");
    private final SimpleDoubleProperty amountTered = new SimpleDoubleProperty(this, "amountTered");
    private final SimpleStringProperty amountTeredDisplay = new SimpleStringProperty(this, "amountTeredDisplay");
    private final SimpleStringProperty currencyDisplay = new SimpleStringProperty(this, "currencyDisplay");
    private final SimpleObjectProperty currencyID = new SimpleObjectProperty(this, "currencyID");
    private Currency currency;
    private final SimpleDoubleProperty exchangeRate = new SimpleDoubleProperty(this, "exchangeRate");
    private final SimpleStringProperty exchangeRateDisplay = new SimpleStringProperty(this, "exchangeRateDisplay");
    private final SimpleDoubleProperty changeGiven = new SimpleDoubleProperty(this, "changeGiven");
    private final SimpleStringProperty changeGivenDisplay = new SimpleStringProperty(this, "changeGivenDisplay");
    private final SimpleDoubleProperty amountPaid = new SimpleDoubleProperty(this, "amountPaid");
    private final SimpleStringProperty amountPaidDisplay = new SimpleStringProperty(this, "amountPaidDisplay");
    private final SimpleStringProperty amountWords = new SimpleStringProperty(this, "amountWords");
    private final SimpleDoubleProperty amountRefunded = new SimpleDoubleProperty(this, "amountRefunded");
    private final SimpleStringProperty amountRefundedDisplay = new SimpleStringProperty(this, "amountRefundedDisplay");
    private final SimpleDoubleProperty netPaid = new SimpleDoubleProperty(this, "netPaid");
    private final SimpleStringProperty netPaidDisplay = new SimpleStringProperty(this, "netPaidDisplay");

    private List<ReceiptInvoice> receiptInvoices = new ArrayList<>();

    public ReceiptDA() {
        createSearchColumns();
    }

    public ReceiptDA(String persistenceUnit) {
        super(persistenceUnit);
    }

    public ReceiptDA(Receipt receipt) {
        this.receipt = receipt;
        initialseProprties();
        createSearchColumns();
    }

    public ReceiptDA(String persistenceUnit, Receipt receipt) {
        super(persistenceUnit);
        this.receipt = receipt;
        initialseProprties();
        createSearchColumns();
    }

    public ReceiptDA(String receiptID, LocalDate receiptDate, Customer customer, PayModes payMode, BankAccount bankAcccount, double totalBill, double amountTered, Currency currency, double exchangeRate, double changeGiven, double amountPaid, String amountWords) {
        this.receipt = new Receipt(getNextIdHelper(), receiptID, receiptDate, customer, payMode, bankAcccount, totalBill, amountTered, currency, exchangeRate, changeGiven, amountPaid, amountWords);
        initialseProprties();
        createSearchColumns();
    }

    public ReceiptDA(String persistenceUnit, String receiptID, LocalDate receiptDate, Customer customer, PayModes payMode, BankAccount bankAcccount, double totalBill, double amountTered, Currency currency, double exchangeRate, double changeGiven, double amountPaid, String amountWords) {
        super(persistenceUnit);
        this.receipt = new Receipt(getNextIdHelper(), receiptID, receiptDate, customer, payMode, bankAcccount, totalBill, amountTered, currency, exchangeRate, changeGiven, amountPaid, amountWords);
        initialseProprties();
        createSearchColumns();
    }

    public int getIdHelper() {
        return idHelper.get();
    }

    public void setIdHelper(int idHelper) {
        receipt.setIdHelper(idHelper);
        this.idHelper.set(idHelper);
    }

    public String getReceiptID() {
        return receiptID.get();
    }

    public void setReceiptID(String receiptID) {
        receipt.setReceiptID(receiptID);
        this.receiptID.set(receiptID);
    }

    public Object getReceiptDate() {
        return receiptDate.get();
    }

    public String getReceiptDateDisplay() {
        return receiptDateDisplay.get();
    }

    public void setReceiptDate(LocalDate receiptDate) {
        receipt.setReceiptDate(receiptDate);
        this.receiptDate.set(receiptDate);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Object getCustomerID() {
        return customerID.get();
    }

    public String getCustomerDisplay() {
        return customerDisplay.get();
    }

    public CustomerDA getCustomerDA() {
        return this.customer != null ? new CustomerDA(this.customer) : null;
    }

    public void setCustomer(Customer customer) {
        receipt.setCustomer(customer);
        this.customer = customer;
        this.customerID.set(customer.getId());
        this.customerDisplay.set(customer.getDisplayKey());
    }

    public Object getPayMode() {
        return payMode.get();
    }

    public void setPayMode(PayModes payMode) {
        receipt.setPayMode(payMode);
        this.payMode.set(payMode);
    }

    public BankAccount getBankAcccount() {
        return bankAcccount;
    }

    public Object getBankAcccountID() {
        return bankAcccountID.get();
    }

    public String getBankAcccountDisplay() {
        return bankAcccountDisplay.get();
    }

    public BankAccountDA getBankAcccountDA() {
        return this.bankAcccount != null ? new BankAccountDA(this.bankAcccount) : null;
    }

    public void setBankAcccount(BankAccount bankAcccount) {
        receipt.setBankAcccount(bankAcccount);
        this.bankAcccount = bankAcccount;
        this.bankAcccountID.set(bankAcccount.getId());
        this.bankAcccountDisplay.set(bankAcccount.getDisplayKey());
    }

    public double getTotalBill() {
        return totalBill.get();
    }

    public String getTotalBillDisplay() {
        return totalBillDisplay.get();
    }

    public void setTotalBill(double totalBill) {
        receipt.setTotalBill(totalBill);
        this.totalBill.set(totalBill);
        this.totalBillDisplay.set(formatNumber(totalBill));
    }

    public double getAmountTered() {
        return amountTered.get();
    }

    public String getAmountTeredDisplay() {
        return amountTeredDisplay.get();
    }

    public void setAmountTered(double amountTered) {
        receipt.setAmountTered(amountTered);
        this.amountTered.set(amountTered);
        this.amountTeredDisplay.set(formatNumber(amountTered));
    }

    public Currency getCurrency() {
        return currency;
    }

    public Object getCurrencyID() {
        return currencyID.get();
    }

    public String getCurrencyDisplay() {
        return currencyDisplay.get();
    }

    public CurrencyDA getCurrencyDA() {
        return this.currency != null ? new CurrencyDA(this.currency) : null;
    }

    public void setCurrency(Currency currency) {
        receipt.setCurrency(currency);
        this.currency = currency;
        this.currencyID.set(currency.getId());
        this.currencyDisplay.set(currency.getDisplayKey());
    }

    public double getExchangeRate() {
        return exchangeRate.get();
    }

    public String getExchangeRateDisplay() {
        return exchangeRateDisplay.get();
    }

    public void setExchangeRate(double exchangeRate) {
        receipt.setExchangeRate(exchangeRate);
        this.exchangeRate.set(exchangeRate);
        this.exchangeRateDisplay.set(formatNumber(exchangeRate));
    }

    public double getChangeGiven() {
        return changeGiven.get();
    }

    public String getChangeGivenDisplay() {
        return changeGivenDisplay.get();
    }

    public void setChangeGiven(double changeGiven) {
        receipt.setChangeGiven(changeGiven);
        this.changeGiven.set(changeGiven);
        this.changeGivenDisplay.set(formatNumber(changeGiven));
    }

    public double getAmountPaid() {
        return amountPaid.get();
    }

    public String getAmountPaidDisplay() {
        return amountPaidDisplay.get();
    }

    public void setAmountPaid(double amountPaid) {
        receipt.setAmountPaid(amountPaid);
        this.amountPaid.set(amountPaid);
        this.amountPaidDisplay.set(formatNumber(amountPaid));
    }

    public String getAmountWords() {
        return amountWords.get();
    }

    public void setAmountWords(String amountWords) {
        receipt.setAmountWords(amountWords);
        this.amountWords.set(amountWords);
    }

    public List<ReceiptInvoice> getReceiptInvoices() {
        return receiptInvoices;
    }

    public List<ReceiptInvoiceDA> getReceiptInvoicesDAs() {
        return ReceiptInvoiceDA.getReceiptInvoiceDAs(receipt.getReceiptInvoices());
    }

    public void setReceiptInvoices(List<ReceiptInvoice> receiptInvoices) {
        receipt.setReceiptInvoices(receiptInvoices);
        this.receiptInvoices = receiptInvoices;
    }

    public void setReceiptInvoicesDAs(List<ReceiptInvoiceDA> receiptInvoiceDAs) {
        this.receipt.setReceiptInvoices(ReceiptInvoiceDA.getReceiptInvoiceList(receiptInvoiceDAs));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReceiptDA)) {
            return false;
        }

        ReceiptDA receiptDA = (ReceiptDA) o;

        if (receiptDA.getDBEntity() == null || this.getDBEntity() == null) {
            return false;
        }
        return this.getId().equals(receiptDA.getId());
    }

    @Override
    public int hashCode() {
        return receipt.getId().hashCode();
    }

    private void initialseProprties() {
        this.dBEntity = receipt;
        this.idHelper.set(receipt.getIdHelper());
        this.receiptID.set(receipt.getReceiptID());
        this.receiptDate.set(receipt.getReceiptDate());
        this.receiptDateDisplay.set(formatDate(receipt.getReceiptDate()));
        this.customer = receipt.getCustomer();
        if (this.customer != null) {
            this.customerID.set(customer.getId());
            this.customerDisplay.set(customer.getDisplayKey());
        }
        this.payMode.set(receipt.getPayMode());
        this.bankAcccount = receipt.getBankAcccount();
        if (this.bankAcccount != null) {
            this.bankAcccountID.set(bankAcccount.getId());
            this.bankAcccountDisplay.set(bankAcccount.getDisplayKey());
        }
        this.totalBill.set(receipt.getTotalBill());
        this.totalBillDisplay.set(formatNumber(receipt.getTotalBill()));
        this.amountTered.set(receipt.getAmountTered());
        this.amountTeredDisplay.set(formatNumber(receipt.getAmountTered()));
        this.currency = receipt.getCurrency();
        if (this.currency != null) {
            this.currencyID.set(currency.getId());
            this.currencyDisplay.set(currency.getDisplayKey());
        }
        this.exchangeRate.set(receipt.getExchangeRate());
        this.exchangeRateDisplay.set(formatNumber(receipt.getExchangeRate()));
        this.changeGiven.set(receipt.getChangeGiven());
        this.changeGivenDisplay.set(formatNumber(receipt.getChangeGiven()));
        this.amountPaid.set(receipt.getAmountPaid());
        this.amountPaidDisplay.set(formatNumber(receipt.getAmountPaid()));
        this.amountWords.set(receipt.getAmountWords());
        this.amountWords.set(receipt.getAmountWords());
        this.amountRefunded.set(receipt.getAmountRefunded());
        this.amountRefundedDisplay.set(formatNumber(receipt.getAmountRefunded()));
        this.netPaid.set(receipt.getAmountPaid()-receipt.getAmountRefunded());
        this.netPaidDisplay.set(formatNumber(netPaid.get()));

        initCommonProprties();
    }

    
    
    private void createSearchColumns(){
 this.searchColumns.add(new SearchColumn("receiptID", "Receipt ID", this.receiptID.get(), SearchDataTypes.STRING));
this.searchColumns.add(new SearchColumn("receiptDate", "Receipt Date", this.receiptDate.get(), receiptDateDisplay.get(), SearchDataTypes.DATE));
this.searchColumns.add(new SearchColumn("customerID", "Customer ID", this.customerID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal,false));
this.searchColumns.add(new SearchColumn("customerDisplay", "Customer", this.customerDisplay.get(), SearchDataTypes.STRING));
this.searchColumns.add(new SearchColumn("payMode", "Pay Mode", this.payMode.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal));
this.searchColumns.add(new SearchColumn("bankAcccountID", "Account ID", this.bankAcccountID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal,false));
this.searchColumns.add(new SearchColumn("bankAcccountDisplay", "Account", this.bankAcccountDisplay.get(), SearchDataTypes.STRING));
this.searchColumns.add(new SearchColumn("totalBill", "Total Bill", this.totalBill.get(), totalBillDisplay.get(), SearchDataTypes.MONEY));
this.searchColumns.add(new SearchColumn("amountTered", "Amount Tendered", this.amountTered.get(), amountTeredDisplay.get(), SearchDataTypes.MONEY));
this.searchColumns.add(new SearchColumn("currencyID", "Currency ID", this.currencyID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal,false));
this.searchColumns.add(new SearchColumn("currencyDisplay", "Currency", this.currencyDisplay.get(), SearchDataTypes.STRING));
this.searchColumns.add(new SearchColumn("exchangeRate", "Exchange Rate", this.exchangeRate.get(), exchangeRateDisplay.get(), SearchDataTypes.MONEY));
this.searchColumns.add(new SearchColumn("changeGiven", "Change Given", this.changeGiven.get(), changeGivenDisplay.get(), SearchDataTypes.MONEY));
this.searchColumns.add(new SearchColumn("amountPaid", "Amount Paid", this.amountPaid.get(), amountPaidDisplay.get(), SearchDataTypes.MONEY));
this.searchColumns.add(new SearchColumn("amountWords", "Amount Words", this.amountWords.get(), SearchDataTypes.STRING));
this.searchColumns.add(new SearchColumn("amountRefunded", "Amount Refunded", this.amountRefunded.get(), amountRefundedDisplay.get(), SearchDataTypes.MONEY));
this.searchColumns.addAll(this.getDefaultSearchColumns());
}


    @Override
    public List<SearchColumn> getSearchColumns() {
        return this.searchColumns;
    }

    @Override
    public Object getId() {
        return this.receipt.getId();
    }

    @Override
    public String getDisplayKey() {
        return this.receipt.getDisplayKey();
    }

    public static List<ReceiptDA> getReceiptDAs(List<Receipt> receipts) {
        List<ReceiptDA> list = new ArrayList<>();
        receipts.forEach((receipt) -> {
            list.add(new ReceiptDA(receipt));
        });
        return list;
    }

    public static List<Receipt> getReceiptList(List<ReceiptDA> receiptDAs) {
        List<Receipt> receipts = new ArrayList<>();
        receiptDAs.forEach(a -> receipts.add(a.receipt));
        return receipts;
    }

    public boolean save() throws Exception {
        return this.saveReceipt() > 0;

    }

    public boolean update() throws Exception {
        return super.merge(this.receipt);

    }

    public boolean delete() {
        return super.remove(this.receipt);

    }

    public Receipt getReceipt(String receiptID) {
        return (Receipt) super.findJoin(Receipt.class, receiptID, "receiptInvoices");
    }

    public Receipt getReceipt() {
        return this.receipt;
    }

    public List<Receipt> getReceipts() {
        return super.find(Receipt.class);
    }

    @Override
    public List<DBAccess> get() {
        List<DBAccess> list = new ArrayList<>();
        selectQuery(Receipt.class).forEach(o -> list.add(new ReceiptDA((Receipt) o)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public ReceiptDA get(String receiptID) throws Exception {
        Receipt oReceipt = getReceipt(receiptID);
        if (oReceipt == null) {
            throw new Exception("No Record with id: " + receiptID);
        }
        return new ReceiptDA(oReceipt);
    }

    public List<ReceiptDA> get(String columName, Object value) {
        List<ReceiptDA> list = new ArrayList<>();
        super.selectQuery(Receipt.class, columName, value).forEach(da -> list.add(new ReceiptDA((Receipt) da)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public List<ReceiptDA> toDaList(List<Receipt> receipts) {
        List<ReceiptDA> receiptDAs = new ArrayList<>();
        receipts.forEach(s -> receiptDAs.add(new ReceiptDA(s)));
        return receiptDAs;
    }

    public List<DBAccess> toDBAccessList(List<Receipt> receipts) {
        List<DBAccess> dbAccesses = new ArrayList<>();
        receipts.forEach(s -> dbAccesses.add(new ReceiptDA(s)));
        return dbAccesses;
    }

    public int getMax(String columnName) {
        return super.getMax(Receipt.class, columnName);
    }

    public int getMax(String columnName, String comparatorColumn, Object comparatorVaue) {
        return super.getMax(Receipt.class, columnName, comparatorColumn, comparatorVaue);
    }

    public final int getNextIdHelper() {
        return this.getMax("idHelper") + 1;
    }

    public String getNextReceiptID(int idHelper) {
        return new IDGeneratorDA().getToAppendString(Receipt.class.getSimpleName(), idHelper);
    }

    public List<Receipt> getReceipts(String columName, Object value) {
        return super.find(Receipt.class, columName, value);
    }

    @Override
    public List<DBAccess> getRevisions() {
        try {

            List<Object[]> list = getEntityRevisions(Receipt.class);
            List<DBAccess> dBAccesses = new ArrayList<>();
            list.forEach(e -> {

                ReceiptDA receiptDA = new ReceiptDA((Receipt) e[0]);
                receiptDA.revisionEntity = (AppRevisionEntity) e[1];
                receiptDA.oRevisionType = (RevisionType) e[2];
                receiptDA.initRevProprties();
                receiptDA.searchColumns.addAll(receiptDA.getRevSearchColumns());
                dBAccesses.add(receiptDA);

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

    public int saveReceipt() throws Exception {
        List<DBEntity> lReceipts = new ArrayList<>();
        lReceipts.add(this.receipt);
        lReceipts.add(makeJournalEntries());
        List<Invoice> invoices = this.receipt.getReceiptInvoices().stream().map(ReceiptInvoice::getInvoice).collect(Collectors.toList());
        Map<List<? extends DBEntity>, Rights> map = new LinkedHashMap<>();
        map.put(lReceipts, Rights.Create);
        map.put(invoices, Rights.Update);
        return super.processBatchList(map);

    }

    public JournalEntry makeJournalEntries() throws Exception {
        JournalEntryDA journalEntryDA = new JournalEntryDA();
        FinancialPeriod financialPeriod = FinancialPeriodDA.getCurrentFinancialPeriodDA().getFinancialPeriod();
        int invoiceIdHelper = journalEntryDA.getNextIdHelper(financialPeriod);
        String journalID = journalEntryDA.getNextJournalID(invoiceIdHelper, financialPeriod.getPeriodID());
        String narration = "Payment on receipt ID: " + receipt.getReceiptID() + " from: " + receipt.getCustomer().getDisplayKey();
        String referenceNo = "";
        Customer billToCustomer = receipt.getCustomer();
        BankAccount bkAccount = receipt.getBankAcccount();
        List<JournalEntryDetail> journalEntryDetails = new ArrayList<>();

        JournalEntry journalEntry = new JournalEntry(invoiceIdHelper, financialPeriod, journalID,
                receipt.getReceiptDate(), narration, DocumentTypes.Receipt, receipt.getReceiptID(), referenceNo, receipt.getCurrency(),
                receipt.getAmountTered(), receipt.getExchangeRate(), receipt.getAmountPaid(),
                receipt.getAmountWords(), PostStatus.Pending, JournalTypes.Bank, EntryModes.System);

        journalEntryDetails.add(new JournalEntryDetail(AccountGroups.Customer, journalEntry, billToCustomer.getCustomerID(),
                billToCustomer.getDisplayKey(), AccountTypes.Asset, AccountActions.Credit, 1, 1, receipt.getAmountPaid(),
                narration, null));

        journalEntryDetails.add(new JournalEntryDetail(AccountGroups.Bank, journalEntry, bkAccount.getBankAccountID(),
                bkAccount.getDisplayKey(), AccountTypes.Asset, AccountActions.Debit, 1, 1, receipt.getAmountPaid(),
                narration, null));

        journalEntry.setJournalEntryDetails(journalEntryDetails);
        return journalEntry;
    }

}
