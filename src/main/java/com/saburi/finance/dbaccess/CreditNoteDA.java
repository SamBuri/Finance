/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.dbaccess;

import com.saburi.common.dbaccess.DBAccess;
import com.saburi.common.dbaccess.OptionsDA;
import com.saburi.common.dbaccess.StaffDA;
import com.saburi.finance.entities.CreditNote;
import javafx.beans.property.*;
import java.util.ArrayList;
import com.saburi.common.entities.AppRevisionEntity;
import com.saburi.finance.entities.ChartAccount;
import java.util.List;
import com.saburi.common.utils.SearchColumn;
import com.saburi.common.utils.SearchColumn.SearchDataTypes;
import org.hibernate.envers.RevisionType;
import com.saburi.finance.entities.CreditNoteApproval;
import com.saburi.finance.entities.CreditNoteRequest;
import java.time.LocalDate;
import static com.saburi.common.utils.Utilities.formatDate;
import com.saburi.finance.utils.FinanceEnums.ApprovalModes;
import com.saburi.common.entities.Staff;
import com.saburi.finance.entities.Invoice;
import com.saburi.finance.utils.FinanceEnums.RequestStatus;
import com.saburi.finance.entities.CreditNoteRequestDetails;
import com.saburi.finance.entities.Currency;
import com.saburi.finance.entities.Customer;
import com.saburi.common.entities.DBEntity;
import com.saburi.common.utils.CommonEnums.Rights;
import com.saburi.finance.entities.FinancialPeriod;
import com.saburi.finance.entities.GeneralPostingGroup;
import com.saburi.finance.entities.InvoiceDetails;
import com.saburi.finance.entities.JournalEntry;
import com.saburi.finance.entities.JournalEntryDetail;
import com.saburi.common.entities.LookupData;
import com.saburi.common.utils.CommonEnums.EntryModes;
import com.saburi.finance.utils.AccountAmount;
import com.saburi.finance.utils.FinanceEnums;
import com.saburi.finance.utils.FinanceEnums.AccountActions;
import com.saburi.finance.utils.FinanceEnums.AccountGroups;
import com.saburi.finance.utils.FinanceEnums.DocumentTypes;
import com.saburi.finance.utils.FinanceEnums.JournalTypes;
import com.saburi.finance.utils.FinanceEnums.PostStatus;
import static com.saburi.common.utils.Utilities.formatNumber;
import static com.saburi.common.utils.Utilities.toWords;
import com.saburi.finance.utils.FinanceOptionKeys;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

public class CreditNoteDA extends DBAccess {

    private CreditNote creditNote = new CreditNote();
    private final SimpleStringProperty creditNoteApprovalDisplay = new SimpleStringProperty(this, "creditNoteApprovalDisplay");
    private final SimpleStringProperty creditNoteApprovalID = new SimpleStringProperty(this, "creditNoteApprovalID");
    private CreditNoteApproval creditNoteApproval;
    private final SimpleObjectProperty creditNoteDate = new SimpleObjectProperty(this, "creditNoteDate");
    private final SimpleStringProperty creditNoteDateDisplay = new SimpleStringProperty(this, "creditNoteDateDisplay");
    private final SimpleObjectProperty approvalMode = new SimpleObjectProperty(this, "approvalMode");
    private final SimpleDoubleProperty amount = new SimpleDoubleProperty(this, "amount");
    private final SimpleStringProperty amountDisplay = new SimpleStringProperty(this, "amountDisplay");
    private final SimpleStringProperty amountWords = new SimpleStringProperty(this, "amountWords");
    private final SimpleStringProperty customerName = new SimpleStringProperty(this, "customerName");
    private final SimpleObjectProperty approvalDate = new SimpleObjectProperty(this, "approvalDate");
    private final SimpleStringProperty approvalDateDisplay = new SimpleStringProperty(this, "approvalDateDisplay");
    private final SimpleStringProperty approvalNotes = new SimpleStringProperty(this, "approvalNotes");
    private final SimpleStringProperty approvedByDisplay = new SimpleStringProperty(this, "approvedByDisplay");
    private final SimpleObjectProperty approvedByID = new SimpleObjectProperty(this, "approvedByID");
    private Staff approvedBy;
    private final SimpleStringProperty invoiceDisplay = new SimpleStringProperty(this, "invoiceDisplay");
    private final SimpleObjectProperty invoiceID = new SimpleObjectProperty(this, "invoiceID");
    private Invoice invoice;
    private final SimpleObjectProperty requestDate = new SimpleObjectProperty(this, "requestDate");
    private final SimpleStringProperty requestDateDisplay = new SimpleStringProperty(this, "requestDateDisplay");
    private final SimpleStringProperty requestNotes = new SimpleStringProperty(this, "requestNotes");
    private final SimpleDoubleProperty invoiceAmount = new SimpleDoubleProperty(this, "invoiceAmount");
    private final SimpleStringProperty invoiceAmountDisplay = new SimpleStringProperty(this, "invoiceAmountDisplay");
    private final SimpleObjectProperty requestStatus = new SimpleObjectProperty(this, "requestStatus");
    private List<CreditNoteRequestDetails> creditNoteRequestDetails = new ArrayList<>();

    public CreditNoteDA() {
        createSearchColumns();
    }

    public CreditNoteDA(String persistenceUnit) {
        super(persistenceUnit);
    }

    public CreditNoteDA(CreditNote creditNote) {
        this.creditNote = creditNote;
        initialseProprties();
        createSearchColumns();
    }

    public CreditNoteDA(String persistenceUnit, CreditNote creditNote) {
        super(persistenceUnit);
        this.creditNote = creditNote;
        initialseProprties();
        createSearchColumns();
    }

    public CreditNoteDA(CreditNoteApproval creditNoteApproval, LocalDate creditNoteDate, ApprovalModes approvalMode, double amount, String amountWords) {
        this.creditNote = new CreditNote(creditNoteApproval, approvalMode, creditNoteDate, amount, amountWords);
        initialseProprties();
        createSearchColumns();
    }

    public CreditNoteDA(String persistenceUnit, CreditNoteApproval creditNoteApproval, LocalDate creditNoteDate, ApprovalModes approvalMode, double amount, String amountWords) {
        super(persistenceUnit);
        this.creditNote = new CreditNote(creditNoteApproval, approvalMode, creditNoteDate, amount, amountWords);
        initialseProprties();
        createSearchColumns();
    }

    public CreditNoteApproval getCreditNoteApproval() {
        return creditNoteApproval;
    }

    public String getCreditNoteApprovalID() {
        return creditNoteApprovalID.get();
    }

    public String getCreditNoteApprovalDisplay() {
        return creditNoteApprovalDisplay.get();
    }

    public CreditNoteApprovalDA getCreditNoteApprovalDA() {
        return this.creditNoteApproval != null ? new CreditNoteApprovalDA(this.creditNoteApproval) : null;
    }

    public void setCreditNoteApproval(CreditNoteApproval creditNoteApproval) {
        creditNote.setCreditNoteApproval(creditNoteApproval);
        this.creditNoteApproval = creditNoteApproval;
        this.creditNoteApprovalID.set(creditNoteApproval.getCreditNoteApprovalID());
        this.creditNoteApprovalDisplay.set(creditNoteApproval.getDisplayKey());
    }

    public Object getCreditNoteDate() {
        return creditNoteDate.get();
    }

    public String getCreditNoteDateDisplay() {
        return creditNoteDateDisplay.get();
    }

    public void setCreditNoteDate(LocalDate creditNoteDate) {
        creditNote.setCreditNoteDate(creditNoteDate);
        this.creditNoteDate.set(creditNoteDate);
    }

    public Object getApprovalMode() {
        return approvalMode.get();
    }

    public double getAmount() {
        return amount.get();
    }

    public String getAmountDisplay() {
        return amountDisplay.get();
    }

    public void setAmount(double amount) {
        creditNote.setAmount(amount);
        this.amount.set(amount);
        this.amountDisplay.set(formatNumber(amount));
    }

    public String getAmountWords() {
        return amountWords.get();
    }

    public void setAmountWords(String amountWords) {
        creditNote.setAmountWords(amountWords);
        this.amountWords.set(amountWords);
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public Object getApprovalDate() {
        return approvalDate.get();
    }

    public String getApprovalDateDisplay() {
        return approvalDateDisplay.get();
    }

    public String getApprovalNotes() {
        return approvalNotes.get();
    }

    public Staff getApprovedBy() {
        return approvedBy;
    }

    public Object getApprovedByID() {
        return approvedByID.get();
    }

    public String getApprovedByDisplay() {
        return approvedByDisplay.get();
    }

    public StaffDA getApprovedByDA() {
        return this.approvedBy != null ? new StaffDA(this.approvedBy) : null;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Object getInvoiceID() {
        return invoiceID.get();
    }

    public String getInvoiceDisplay() {
        return invoiceDisplay.get();
    }

    public InvoiceDA getInvoiceDA() {
        return this.invoice != null ? new InvoiceDA(this.invoice) : null;
    }

    public Object getRequestDate() {
        return requestDate.get();
    }

    public String getRequestDateDisplay() {
        return requestDateDisplay.get();
    }

    public String getRequestNotes() {
        return requestNotes.get();
    }

    public double getInvoiceAmount() {
        return invoiceAmount.get();
    }

    public String getInvoiceAmountDisplay() {
        return invoiceAmountDisplay.get();
    }

    public Object getRequestStatus() {
        return requestStatus.get();
    }

    public List<CreditNoteRequestDetails> getCreditNoteRequestDetails() {
        return creditNoteRequestDetails;
    }

    public List<CreditNoteRequestDetailsDA> getCreditNoteRequestDetailsDAs() throws Exception {
        return new CreditNoteApprovalDA(creditNoteApproval).getCreditNoteRequestDetailsDAs();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreditNoteDA)) {
            return false;
        }

        CreditNoteDA creditNoteDA = (CreditNoteDA) o;

        if (creditNoteDA.getDBEntity() == null || this.getDBEntity() == null) {
            return false;
        }
        return this.getId().equals(creditNoteDA.getId());
    }

    @Override
    public int hashCode() {
        return creditNote.getId().hashCode();
    }

    private void initialseProprties() {
        this.dBEntity = creditNote;
        this.creditNoteApproval = creditNote.getCreditNoteApproval();
        if (this.creditNoteApproval != null) {
            this.creditNoteApprovalID.set(creditNoteApproval.getCreditNoteApprovalID());
            this.creditNoteApprovalDisplay.set(creditNoteApproval.getDisplayKey());
            this.invoice = creditNote.getCreditNoteApproval().getCreditNoteRequest().getInvoice();
            if (this.invoice != null) {
                this.invoiceID.set(invoice.getId());
                this.invoiceDisplay.set(invoice.getDisplayKey());
                this.customerName.set(invoice.getBillTo().getCustomerName());

            }
            this.approvalDate.set(creditNote.getCreditNoteApproval().getApprovalDate());
            this.approvalDateDisplay.set(formatDate(creditNote.getCreditNoteApproval().getApprovalDate()));
            this.approvalNotes.set(creditNoteApproval.getNotes());
            this.approvedBy = creditNoteApproval.getApprovedBy();
            CreditNoteRequest creditNoteRequest = creditNoteApproval.getCreditNoteRequest();
            this.requestDate.set(creditNoteRequest.getRequestDate());
            this.requestDateDisplay.set(formatDate(creditNoteRequest.getRequestDate()));
            this.requestNotes.set(creditNoteRequest.getNotes());
            this.invoiceAmount.set(invoice.getAmount());
            this.invoiceAmountDisplay.set(formatNumber(invoiceAmount.get()));
            this.requestStatus.set(creditNoteRequest.getRequestStatus());
        }
        this.creditNoteDate.set(creditNote.getCreditNoteDate());
        this.creditNoteDateDisplay.set(formatDate(creditNote.getCreditNoteDate()));
        this.approvalMode.set(creditNote.getApprovalMode());
        this.amount.set(creditNote.getAmount());
        this.amountDisplay.set(formatNumber(creditNote.getAmount()));
        this.amountWords.set(creditNote.getAmountWords());

        if (this.approvedBy != null) {
            this.approvedByID.set(approvedBy.getId());
            this.approvedByDisplay.set(approvedBy.getDisplayKey());
        }

        initCommonProprties();
    }

    private void createSearchColumns() {
        this.searchColumns.add(new SearchColumn("creditNoteApprovalID", "Credit Note Approval ID", this.creditNoteApprovalID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("creditNoteApprovalDisplay", "Credit Note Approval", this.creditNoteApprovalDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("creditNoteDate", "Credit Note Date", this.creditNoteDate.get(),this.creditNoteDateDisplay.get(), SearchDataTypes.DATE));
        this.searchColumns.add(new SearchColumn("approvalMode", "Approval Mode", this.approvalMode.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal));
        this.searchColumns.add(new SearchColumn("amount", "Amount", this.amount.get(),this.amountDisplay.get(), SearchDataTypes.NUMBER));
        this.searchColumns.add(new SearchColumn("amountWords", "AmountWords", this.amountWords.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("customerName", "Customer Name", this.customerName.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("approvalDate", "Approval Date", this.approvalDate.get(), this.approvalDateDisplay.get(), SearchDataTypes.DATE));
        this.searchColumns.add(new SearchColumn("approvalNotes", "Approval Notes", this.approvalNotes.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("approvedByID", "Approved By ID", this.approvedByID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("approvedByDisplay", "Approved By", this.approvedByDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("invoiceID", "Invoice ID", this.invoiceID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("invoiceDisplay", "Invoice", this.invoiceDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("requestDate", "Request Date", this.requestDate.get(), this.requestDateDisplay.get(),SearchDataTypes.DATE));
        this.searchColumns.add(new SearchColumn("requestNotes", "Request Notes", this.requestNotes.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("invoiceAmount", "Invoice Amount", this.invoiceAmount.get(),this.invoiceAmountDisplay.get(), SearchDataTypes.NUMBER));
        this.searchColumns.add(new SearchColumn("requestStatus", "Request Status", this.requestStatus.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal));
        this.searchColumns.addAll(this.getDefaultSearchColumns());
    }

    @Override
    public List<SearchColumn> getSearchColumns() {
        return this.searchColumns;
    }

    @Override
    public Object getId() {
        return this.creditNote.getId();
    }

    @Override
    public String getDisplayKey() {
        return this.creditNote.getDisplayKey();
    }

    public static List<CreditNoteDA> getCreditNoteDAs(List<CreditNote> creditNotes) {
        List<CreditNoteDA> list = new ArrayList<>();
        creditNotes.forEach((creditNote) -> {
            list.add(new CreditNoteDA(creditNote));
        });
        return list;
    }

    public static List<CreditNote> getCreditNoteList(List<CreditNoteDA> creditNoteDAs) {
        List<CreditNote> creditNotes = new ArrayList<>();
        creditNoteDAs.forEach(a -> creditNotes.add(a.creditNote));
        return creditNotes;
    }

    public boolean save() throws Exception {
        return this.saveCreditNote();

    }

    public boolean update() throws Exception {
        throw new Exception("A Credit Note cannot be updated");

    }

    public boolean delete() {
        return super.remove(this.creditNote);

    }

    public CreditNote getCreditNote(String creditNoteApproval) {
        return (CreditNote) super.find(CreditNote.class, creditNoteApproval);
    }

    public CreditNote getCreditNote() {
        return this.creditNote;
    }

    public List<CreditNote> getCreditNotes() {
        return super.find(CreditNote.class);
    }

    @Override
    public List<DBAccess> get() {
        List<DBAccess> list = new ArrayList<>();
        selectQuery(CreditNote.class).forEach(o -> list.add(new CreditNoteDA((CreditNote) o)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public CreditNoteDA get(String creditNoteApproval) throws Exception {
        CreditNote oCreditNote = getCreditNote(creditNoteApproval);
        if (oCreditNote == null) {
            throw new Exception("No Record with id: " + creditNoteApproval);
        }
        return new CreditNoteDA(oCreditNote);
    }

    public List<CreditNoteDA> get(String columName, Object value) {
        List<CreditNoteDA> list = new ArrayList<>();
        super.selectQuery(CreditNote.class, columName, value).forEach(da -> list.add(new CreditNoteDA((CreditNote) da)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public List<CreditNoteDA> toDaList(List<CreditNote> creditNotes) {
        List<CreditNoteDA> creditNoteDAs = new ArrayList<>();
        creditNotes.forEach(s -> creditNoteDAs.add(new CreditNoteDA(s)));
        return creditNoteDAs;
    }

    public List<DBAccess> toDBAccessList(List<CreditNote> creditNotes) {
        List<DBAccess> dbAccesses = new ArrayList<>();
        creditNotes.forEach(s -> dbAccesses.add(new CreditNoteDA(s)));
        return dbAccesses;
    }

    public int getMax(String columnName) {
        return super.getMax(CreditNote.class, columnName);
    }

    public int getMax(String columnName, String comparatorColumn, Object comparatorVaue) {
        return super.getMax(CreditNote.class, columnName, comparatorColumn, comparatorVaue);
    }

    public List<CreditNote> getCreditNotes(String columName, Object value) {
        return super.find(CreditNote.class, columName, value);
    }

    @Override
    public List<DBAccess> getRevisions() {
        try {

            List<Object[]> list = getEntityRevisions(CreditNote.class);
            List<DBAccess> dBAccesses = new ArrayList<>();
            list.forEach(e -> {

                CreditNoteDA creditNoteDA = new CreditNoteDA((CreditNote) e[0]);
                creditNoteDA.revisionEntity = (AppRevisionEntity) e[1];
                creditNoteDA.oRevisionType = (RevisionType) e[2];
                creditNoteDA.initRevProprties();
                creditNoteDA.searchColumns.addAll(creditNoteDA.getRevSearchColumns());
                dBAccesses.add(creditNoteDA);

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

    public List<CreditNote> getCreditNotes(Invoice invoice) {

        try {
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<CreditNote> criteriaQuery = criteriaBuilder.createQuery(CreditNote.class);
            Root<CreditNote> root = criteriaQuery.from(CreditNote.class);
            Join<CreditNote, CreditNoteApproval> crA = root.join("creditNoteApproval");
            Join<CreditNoteApproval, CreditNoteRequest> crR = crA.join("creditNoteRequest");
            criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(crR.get("invoice"), invoice),
                    criteriaBuilder.greaterThan(root.get("toRefundAmount"), 0)));

            return entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.close();
        }

    }

    private boolean isValid() throws Exception {
        RequestStatus approvalRequestStatus = creditNoteApproval.getRequestStatus();
        if (!approvalRequestStatus.equals(RequestStatus.Approved)) {
            throw new Exception("This Credit Note request with id: " + getCreditNoteApprovalID() + " is " + approvalRequestStatus.name() + "\n"
                    + "and cannot be processed");
        }
        return new CreditNoteRequestDA().get(creditNoteApproval.getCreditNoteRequest().getCreditNoteRequestID()).isAmountValid();
    }

    public JournalEntry makeJournalEntries() throws Exception {
        JournalEntryDA journalEntryDA = new JournalEntryDA();
        FinancialPeriod financialPeriod = FinancialPeriodDA.getCurrentFinancialPeriodDA().getFinancialPeriod();
        int invoiceIdHelper = journalEntryDA.getNextIdHelper(financialPeriod);
        Currency currency = new CurrencyDA().getDefaultCurrency();
        String journalID = journalEntryDA.getNextJournalID(invoiceIdHelper, financialPeriod.getPeriodID());
        String narration = "Credit Note on Request ID: " + creditNote.getCreditNoteID() + " for customer: " + invoice.getBillTo().getDisplayKey();
        String referenceNo = "";
        Customer billToCustomer = invoice.getBillTo();
        LookupData businessGroup = billToCustomer.getBusinessGroup();

        List<JournalEntryDetail> journalEntryDetails = new ArrayList<>();
        List<AccountAmount> discAccountAmounts = new ArrayList<>();
        List<AccountAmount> saleReturnAccountAmounts = new ArrayList<>();

        double totalAmout = 0;
        for (CreditNoteRequestDetailsDA e : new CreditNoteApprovalDA(creditNote.getCreditNoteApproval()).getCreditNoteRequestDetailsDAs()) {
            double discount = e.getInvoiceDetails().getDiscount();
            double lineAmount = e.getUnitPrice() * e.getQuantity();
            GeneralPostingGroup generalPostingGroup = new GeneralPostingGroupDA().getGeneralPostingGroup(businessGroup, e.getInvoiceDetails().getItem().getItemGroup());
            saleReturnAccountAmounts.add(new AccountAmount(generalPostingGroup.getSalesReturnAccount(), lineAmount));
            totalAmout += lineAmount;
            if (discount > 0) {
                ChartAccount chartAccount = new GeneralPostingGroupDA().getSaleDiscountAccount(businessGroup, e.getInvoiceDetails().getItem().getItemGroup());
                discAccountAmounts.add(new AccountAmount(chartAccount, discount));
            }
        }

        JournalEntry journalEntry = new JournalEntry(invoiceIdHelper, financialPeriod, journalID,
                creditNote.getCreditNoteDate(), narration, DocumentTypes.Credit_Memo, creditNote.getCreditNoteID(), referenceNo, currency,
                totalAmout, currency.getBuying(), totalAmout, toWords(totalAmout), PostStatus.Pending, JournalTypes.Sales, EntryModes.System);

        journalEntryDetails.add(new JournalEntryDetail(AccountGroups.Customer, journalEntry, billToCustomer.getCustomerID(),
                billToCustomer.getDisplayKey(), FinanceEnums.AccountTypes.Asset, AccountActions.Credit, 1, 1, creditNote.getAmount(),
                narration, null));
        Map<ChartAccount, Double> groupedSalesReturnAccounts = saleReturnAccountAmounts.stream()
                .collect(Collectors.groupingBy(AccountAmount::getChartAccount, Collectors.summingDouble(AccountAmount::getAmount)));

        Map<ChartAccount, Double> groupedDiscAccounts = discAccountAmounts.stream()
                .collect(Collectors.groupingBy(AccountAmount::getChartAccount, Collectors.summingDouble(AccountAmount::getAmount)));

        groupedSalesReturnAccounts.entrySet().forEach((entry) -> {
            journalEntryDetails.add(new JournalEntryDetail(AccountGroups.COA, journalEntry, entry.getKey().getAccountID(),
                    entry.getKey().getAccountName(), entry.getKey().getAccountCategory().getAccountType(), entry.getKey().getAccountAction(), 1, 1, entry.getValue(),
                    narration, null));
        });

        groupedDiscAccounts.entrySet().forEach((entry) -> {
            journalEntryDetails.add(new JournalEntryDetail(AccountGroups.COA, journalEntry, entry.getKey().getAccountID(),
                    entry.getKey().getAccountName(), entry.getKey().getAccountCategory().getAccountType(), AccountActions.Credit, 1, 1, entry.getValue(),
                    narration, null));
        });

        journalEntry.setJournalEntryDetails(journalEntryDetails);
        return journalEntry;
    }

    private boolean saveCreditNote() throws Exception {
        try {
            if (!isValid()) {
                return false;
            }
            Invoice toSaveInvoice = new InvoiceDA().getInvoice(this.invoice.getInvoiceID());
            double invoiceBalance = toSaveInvoice.getAmount();
            List<DBEntity> toSaveEntities = new ArrayList<>();
            List<DBEntity> toUpdateEntities = new ArrayList<>();
            List<InvoiceDetails> invoiceDetailses = new ArrayList<>();
            for (CreditNoteRequestDetailsDA crd : this.getCreditNoteRequestDetailsDAs()) {
                crd.validate();
                InvoiceDetails invoiceDetails = new InvoiceDetailsDA().getInvoiceDetails(crd.getInvoiceDetails().getInvoiceDetailID());
                double amountBalance = invoiceDetails.getAmount() - crd.getAmount();
                int quantityBalance = invoiceDetails.getQuantity() - crd.getQuantity();
                invoiceDetails.setAmount(amountBalance);
                invoiceDetails.setQuantity(quantityBalance);
                invoiceDetailses.add(invoiceDetails);
            }
            double toReduceAmount = this.getCreditNoteRequestDetailsDAs()
                    .parallelStream()
                    .mapToDouble((p) -> p.getAmount())
                    .sum();

            if (invoiceBalance < toReduceAmount) {
                throw new Exception("The invoice balance: " + formatNumber(invoiceBalance) + " can't be less than Credit Note Amount " + formatNumber(toReduceAmount));
            }
            double balance = invoiceBalance - toReduceAmount;
            this.invoice.setAmount(balance);
            this.invoice.setInvoiceDetails(invoiceDetailses);
            this.creditNoteApproval.setRequestStatus(RequestStatus.Concluded);
            CreditNoteRequest creditNoteRequest = this.getCreditNoteApproval().getCreditNoteRequest();
            creditNoteRequest.setRequestStatus(RequestStatus.Concluded);

            double amountPaid = toSaveInvoice.getAmountPaid();

            if (amountPaid > 0) {
                double pendingToRefundAmount = getCreditNotes(this.invoice).stream()
                        .mapToDouble(CreditNote::getToRefundAmount)
                        .sum();
                double amoutRefunded = toSaveInvoice.getAmountRefunded();
                double netPaid = amountPaid - (amoutRefunded + pendingToRefundAmount);
                double toRefundAmount = 0;
                if (netPaid == balance) {
                    toRefundAmount = toReduceAmount;
                } else if (netPaid > balance) {
                    toRefundAmount = netPaid - balance;
                }
                this.creditNote.setToRefundAmount(toRefundAmount);

            }
            JournalEntry journalEntry = this.makeJournalEntries();
            toUpdateEntities.add(this.invoice);
            toUpdateEntities.add(this.creditNoteApproval);
            toUpdateEntities.add(creditNoteRequest);
            toSaveEntities.add(this.creditNote);
            toSaveEntities.add(journalEntry);
            Map<List<? extends DBEntity>, Rights> map = new LinkedHashMap<>();
            map.put(toSaveEntities, Rights.Create);
            map.put(toUpdateEntities, Rights.Update);
            if (OptionsDA.getBooleanOptionValue(FinanceOptionKeys.ENABLE_AUTO_GENERAL_LEDGER_POSTING)) {
                map.putAll(new JournalEntryDA(journalEntry).getPostMap());
            }
            processBatchList(map);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CreditNote> getToRefundCreditNotes() {
        return super.findGreater(CreditNote.class, "toRefundAmount", 0);
    }

    public List<CreditNoteDA> getToRefundCreditNoteDAs() {
        return toDaList(getToRefundCreditNotes());
    }

}
