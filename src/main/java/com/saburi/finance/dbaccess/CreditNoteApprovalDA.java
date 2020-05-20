/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.dbaccess;

import com.saburi.common.dbaccess.DBAccess;
import com.saburi.common.dbaccess.StaffDA;
import com.saburi.finance.entities.CreditNoteApproval;
import javafx.beans.property.*;
import java.util.ArrayList;
import com.saburi.common.entities.AppRevisionEntity;
import java.util.List;
import com.saburi.common.utils.SearchColumn;
import com.saburi.common.utils.SearchColumn.SearchDataTypes;
import org.hibernate.envers.RevisionType;
import com.saburi.finance.entities.CreditNoteRequest;
import java.time.LocalDate;
import static com.saburi.common.utils.Utilities.formatDate;
import com.saburi.common.entities.Staff;
import com.saburi.finance.entities.Invoice;
import static com.saburi.common.utils.Utilities.formatNumber;
import com.saburi.finance.utils.FinanceEnums.RequestStatus;
import com.saburi.finance.entities.CreditNoteRequestDetails;
import com.saburi.common.entities.DBEntity;
import com.saburi.common.utils.CommonEnums.Rights;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreditNoteApprovalDA extends DBAccess {

    private CreditNoteApproval creditNoteApproval = new CreditNoteApproval();
    private final SimpleStringProperty creditNoteApprovalID = new SimpleStringProperty(this, "creditNoteApprovalID");
    private final SimpleStringProperty creditNoteRequestDisplay = new SimpleStringProperty(this, "creditNoteRequestDisplay");
    private final SimpleObjectProperty creditNoteRequestID = new SimpleObjectProperty(this, "creditNoteRequestID");
    private CreditNoteRequest creditNoteRequest;
    private final SimpleObjectProperty approvalDate = new SimpleObjectProperty(this, "approvalDate");
    private final SimpleStringProperty approvalDateDisplay = new SimpleStringProperty(this, "approvalDateDisplay");
    private final SimpleStringProperty notes = new SimpleStringProperty(this, "notes");
    private final SimpleStringProperty approvedByDisplay = new SimpleStringProperty(this, "approvedByDisplay");
    private final SimpleObjectProperty approvedByID = new SimpleObjectProperty(this, "approvedByID");
    private Staff approvedBy;
    private final SimpleStringProperty invoiceDisplay = new SimpleStringProperty(this, "invoiceDisplay");
    private final SimpleObjectProperty invoiceID = new SimpleObjectProperty(this, "invoiceID");
    private Invoice invoice;
    private final SimpleObjectProperty requestDate = new SimpleObjectProperty(this, "requestDate");
    private final SimpleStringProperty requestDateDisplay = new SimpleStringProperty(this, "requestDateDisplay");
    private final SimpleStringProperty requestNotes = new SimpleStringProperty(this, "requestNotes");
    private final SimpleDoubleProperty amount = new SimpleDoubleProperty(this, "amount");
    private final SimpleStringProperty amountDisplay = new SimpleStringProperty(this, "amountDisplay");
    private final SimpleStringProperty amountWords = new SimpleStringProperty(this, "amountWords");
    private final SimpleStringProperty customerName = new SimpleStringProperty(this, "customerName");
    private final SimpleDoubleProperty invoiceAmount = new SimpleDoubleProperty(this, "invoiceAmount");
    private final SimpleStringProperty invoiceAmountDisplay = new SimpleStringProperty(this, "invoiceAmountDisplay");
    private final SimpleObjectProperty requestStatus = new SimpleObjectProperty(this, "requestStatus");
    private List<CreditNoteRequestDetails> creditNoteRequestDetails = new ArrayList<>();

    public CreditNoteApprovalDA() {
        createSearchColumns();
    }

    public CreditNoteApprovalDA(String persistenceUnit) {
        super(persistenceUnit);
    }

    public CreditNoteApprovalDA(CreditNoteApproval creditNoteApproval) {
        this.creditNoteApproval = creditNoteApproval;
        initialseProprties();
        createSearchColumns();
    }

    public CreditNoteApprovalDA(String persistenceUnit, CreditNoteApproval creditNoteApproval) {
        super(persistenceUnit);
        this.creditNoteApproval = creditNoteApproval;
        initialseProprties();
        createSearchColumns();
    }

    public CreditNoteApprovalDA(CreditNoteRequest creditNoteRequest, LocalDate approvalDate, String notes,
            RequestStatus requestStatus, Staff approvedBy) {
        this.creditNoteApproval = new CreditNoteApproval(creditNoteRequest, approvalDate, notes, requestStatus, approvedBy);
        initialseProprties();
        createSearchColumns();
    }

    public CreditNoteApprovalDA(String persistenceUnit, CreditNoteRequest creditNoteRequest, LocalDate approvalDate, String notes,
            RequestStatus requestStatus, Staff approvedBy) {
        super(persistenceUnit);
        this.creditNoteApproval = new CreditNoteApproval(creditNoteRequest, approvalDate, notes, requestStatus, approvedBy);
        initialseProprties();
        createSearchColumns();
    }

    public String getCreditNoteApprovalID() {
        return creditNoteApprovalID.get();
    }

    public void setCreditNoteApprovalID(String creditNoteApprovalID) {
        creditNoteApproval.setCreditNoteApprovalID(creditNoteApprovalID);
        this.creditNoteApprovalID.set(creditNoteApprovalID);
    }

    public CreditNoteRequest getCreditNoteRequest() {
        return creditNoteRequest;
    }

    public Object getCreditNoteRequestID() {
        return creditNoteRequestID.get();
    }

    public String getCreditNoteRequestDisplay() {
        return creditNoteRequestDisplay.get();
    }

    public CreditNoteRequestDA getCreditNoteRequestDA() {
        return this.creditNoteRequest != null ? new CreditNoteRequestDA(this.creditNoteRequest) : null;
    }

    public void setCreditNoteRequest(CreditNoteRequest creditNoteRequest) {
        creditNoteApproval.setCreditNoteRequest(creditNoteRequest);
        this.creditNoteRequest = creditNoteRequest;
        this.creditNoteRequestID.set(creditNoteRequest.getId());
        this.creditNoteRequestDisplay.set(creditNoteRequest.getDisplayKey());
    }

    public Object getApprovalDate() {
        return approvalDate.get();
    }

    public String getApprovalDateDisplay() {
        return approvalDateDisplay.get();
    }

    public void setApprovalDate(LocalDate approvalDate) {
        creditNoteApproval.setApprovalDate(approvalDate);
        this.approvalDate.set(approvalDate);
    }

    public String getNotes() {
        return notes.get();
    }

    public void setNotes(String notes) {
        creditNoteApproval.setNotes(notes);
        this.notes.set(notes);
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

    public void setApprovedBy(Staff approvedBy) {
        creditNoteApproval.setApprovedBy(approvedBy);
        this.approvedBy = approvedBy;
        this.approvedByID.set(approvedBy.getId());
        this.approvedByDisplay.set(approvedBy.getDisplayKey());
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

    public double getAmount() {
        return amount.get();
    }

    public String getAmountDisplay() {
        return amountDisplay.get();
    }

    public String getAmountWords() {
        return amountWords.get();
    }

    public String getCustomerName() {
        return customerName.get();
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

    public void setRequestStatus(RequestStatus requestStatus) {
        creditNoteApproval.setRequestStatus(requestStatus);
        this.requestStatus.set(requestStatus);
    }

    public List<CreditNoteRequestDetails> getCreditNoteRequestDetails() {
        return creditNoteRequestDetails;
    }

    public List<CreditNoteRequestDetailsDA> getCreditNoteRequestDetailsDAs() throws Exception {
        return new CreditNoteRequestDA().get(creditNoteRequest.getCreditNoteRequestID()).getCreditNoteRequestDetailsDAs();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreditNoteApprovalDA)) {
            return false;
        }

        CreditNoteApprovalDA creditNoteApprovalDA = (CreditNoteApprovalDA) o;

        if (creditNoteApprovalDA.getDBEntity() == null || this.getDBEntity() == null) {
            return false;
        }
        return this.getId().equals(creditNoteApprovalDA.getId());
    }

    @Override
    public int hashCode() {
        return creditNoteApproval.getId().hashCode();
    }

    private void initialseProprties() {
        this.dBEntity = creditNoteApproval;
        this.creditNoteApprovalID.set(creditNoteApproval.getCreditNoteApprovalID());
        this.creditNoteRequest = creditNoteApproval.getCreditNoteRequest();
        if (this.creditNoteRequest != null) {
            this.creditNoteRequestID.set(creditNoteRequest.getId());
            this.creditNoteRequestDisplay.set(creditNoteRequest.getDisplayKey());
            this.invoice = creditNoteRequest.getInvoice();
            if (this.invoice != null) {
                this.invoiceID.set(invoice.getId());
                this.invoiceDisplay.set(invoice.getDisplayKey());
            }
            this.requestDate.set(creditNoteRequest.getRequestDate());
            this.requestDateDisplay.set(formatDate(creditNoteRequest.getRequestDate()));
            this.requestNotes.set(creditNoteRequest.getNotes()
            );
            this.amount.set(creditNoteRequest.getAmount());
            this.amountDisplay.set(formatNumber(creditNoteRequest.getAmount()));
            this.amountWords.set(creditNoteRequest.getAmountWords());
            this.customerName.set(invoice.getBillTo().getCustomerName());
            this.invoiceAmount.set(invoice.getAmount());
            this.invoiceAmountDisplay.set(formatNumber(invoice.getAmount()));
        }
        this.approvalDate.set(creditNoteApproval.getApprovalDate());
        this.approvalDateDisplay.set(formatDate(creditNoteApproval.getApprovalDate()));
        this.notes.set(creditNoteApproval.getNotes());
        this.approvedBy = creditNoteApproval.getApprovedBy();
        if (this.approvedBy != null) {
            this.approvedByID.set(approvedBy.getId());
            this.approvedByDisplay.set(approvedBy.getDisplayKey());
        }

        this.requestStatus.set(creditNoteApproval.getRequestStatus());
        initCommonProprties();
    }

    private void createSearchColumns() {
        this.searchColumns.add(new SearchColumn("creditNoteApprovalID", "Credit Note Approval ID", this.creditNoteApprovalID.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("creditNoteRequestID", "Credit Note Request ID", this.creditNoteRequestID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("creditNoteRequestDisplay", "Credit Note Request", this.creditNoteRequestDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("approvalDate", "Approval Date", this.approvalDate.get(), this.approvalDateDisplay.get(), SearchDataTypes.DATE));
        this.searchColumns.add(new SearchColumn("notes", "Notes", this.notes.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("approvedByID", "Approved By ID", this.approvedByID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("approvedByDisplay", "Approved By", this.approvedByDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("invoiceID", "Invoice ID", this.invoiceID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("invoiceDisplay", "Invoice", this.invoiceDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("requestDate", "Request Date", this.requestDate.get(),this.requestDateDisplay.get(), SearchDataTypes.DATE));
        this.searchColumns.add(new SearchColumn("requestNotes", "Request Notes", this.requestNotes.get(), SearchDataTypes.STRING
        ));
        this.searchColumns.add(new SearchColumn("amount", "Amount", this.amount.get(),this.amountDisplay.get(), SearchDataTypes.NUMBER));
        this.searchColumns.add(new SearchColumn("amountWords", "AmountWords", this.amountWords.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("customerName", "Customer Name", this.customerName.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("invoiceAmount", "Invoice Amount", this.invoiceAmount.get(), this.invoiceAmountDisplay.get(), SearchDataTypes.NUMBER));
        this.searchColumns.add(new SearchColumn("requestStatus", "Request Status", this.requestStatus.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal));
        this.searchColumns.addAll(this.getDefaultSearchColumns());
    }

    @Override
    public List<SearchColumn> getSearchColumns() {
        return this.searchColumns;
    }

    @Override
    public Object getId() {
        return this.creditNoteApproval.getId();
    }

    @Override
    public String getDisplayKey() {
        return this.creditNoteApproval.getDisplayKey();
    }

    public static List<CreditNoteApprovalDA> getCreditNoteApprovalDAs(List<CreditNoteApproval> creditNoteApprovals) {
        List<CreditNoteApprovalDA> list = new ArrayList<>();
        creditNoteApprovals.forEach((creditNoteApproval) -> {
            list.add(new CreditNoteApprovalDA(creditNoteApproval));
        });
        return list;
    }

    public static List<CreditNoteApproval> getCreditNoteApprovalList(List<CreditNoteApprovalDA> creditNoteApprovalDAs) {
        List<CreditNoteApproval> creditNoteApprovals = new ArrayList<>();
        creditNoteApprovalDAs.forEach(a -> creditNoteApprovals.add(a.creditNoteApproval));
        return creditNoteApprovals;
    }

    public boolean save() throws Exception {
        if (isValid()) {
            Map<DBEntity, Rights> map = new LinkedHashMap<>();
            map.put(creditNoteApproval, Rights.Create);
            creditNoteRequest.setRequestStatus(creditNoteApproval.getRequestStatus());
            map.put(creditNoteRequest, Rights.Update);
            return this.processBatch(map) > 1;
        }
        return false;

    }

    public boolean update() throws Exception {
        if (isValid()) {
            Map<DBEntity, Rights> map = new LinkedHashMap<>();
            map.put(creditNoteApproval, Rights.Update);
            creditNoteRequest.setRequestStatus(creditNoteApproval.getRequestStatus());
            map.put(creditNoteRequest, Rights.Update);
            return this.processBatch(map) > 1;
        }
        return false;

    }

    public boolean delete() {
        return super.remove(this.creditNoteApproval);
    }

    public CreditNoteApproval getCreditNoteApproval(String creditNoteApprovalID) {
        return (CreditNoteApproval) super.find(CreditNoteApproval.class, creditNoteApprovalID);
    }

    public CreditNoteApproval getCreditNoteApproval() {
        return this.creditNoteApproval;
    }

    public List<CreditNoteApproval> getCreditNoteApprovals() {
        return super.find(CreditNoteApproval.class);
    }

    @Override
    public List<DBAccess> get() {
        List<DBAccess> list = new ArrayList<>();
        selectQuery(CreditNoteApproval.class).forEach(o -> list.add(new CreditNoteApprovalDA((CreditNoteApproval) o)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public CreditNoteApprovalDA get(String creditNoteApprovalID) throws Exception {
        CreditNoteApproval oCreditNoteApproval = getCreditNoteApproval(creditNoteApprovalID);
        if (oCreditNoteApproval == null) {
            throw new Exception("No Record with id: " + creditNoteApprovalID);
        }
        return new CreditNoteApprovalDA(oCreditNoteApproval);
    }

    public List<CreditNoteApprovalDA> get(String columName, Object value) {
        List<CreditNoteApprovalDA> list = new ArrayList<>();
        super.selectQuery(CreditNoteApproval.class, columName, value).forEach(da -> list.add(new CreditNoteApprovalDA((CreditNoteApproval) da)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public List<CreditNoteApprovalDA> toDaList(List<CreditNoteApproval> creditNoteApprovals) {
        List<CreditNoteApprovalDA> creditNoteApprovalDAs = new ArrayList<>();
        creditNoteApprovals.forEach(s -> creditNoteApprovalDAs.add(new CreditNoteApprovalDA(s)));
        return creditNoteApprovalDAs;
    }

    public List<DBAccess> toDBAccessList(List<CreditNoteApproval> creditNoteApprovals) {
        List<DBAccess> dbAccesses = new ArrayList<>();
        creditNoteApprovals.forEach(s -> dbAccesses.add(new CreditNoteApprovalDA(s)));
        return dbAccesses;
    }

    public int getMax(String columnName) {
        return super.getMax(CreditNoteApproval.class, columnName);
    }

    public int getMax(String columnName, String comparatorColumn, Object comparatorVaue) {
        return super.getMax(CreditNoteApproval.class, columnName, comparatorColumn, comparatorVaue);
    }

    public List<CreditNoteApproval> getCreditNoteApprovals(String columName, Object value) {
        return super.find(CreditNoteApproval.class, columName, value);
    }

    @Override
    public List<DBAccess> getRevisions() {
        try {

            List<Object[]> list = getEntityRevisions(CreditNoteApproval.class);
            List<DBAccess> dBAccesses = new ArrayList<>();
            list.forEach(e -> {

                CreditNoteApprovalDA creditNoteApprovalDA = new CreditNoteApprovalDA((CreditNoteApproval) e[0]);
                creditNoteApprovalDA.revisionEntity = (AppRevisionEntity) e[1];
                creditNoteApprovalDA.oRevisionType = (RevisionType) e[2];
                creditNoteApprovalDA.initRevProprties();
                creditNoteApprovalDA.searchColumns.addAll(creditNoteApprovalDA.getRevSearchColumns());
                dBAccesses.add(creditNoteApprovalDA);

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

    private boolean isValid() throws Exception {
        RequestStatus approvalRequestStatus = creditNoteApproval.getRequestStatus();
        if (!(approvalRequestStatus.equals(RequestStatus.Cancelled) || approvalRequestStatus.equals(RequestStatus.Approved))) {
            throw new Exception("Invalid status: " + approvalRequestStatus.name() + " for approval operation. The only accepted statuses are"
                    + " " + RequestStatus.Approved.name() + " and " + RequestStatus.Cancelled);
        }
        if (approvalRequestStatus.equals(RequestStatus.Approved)) {
            return new CreditNoteRequestDA().get(creditNoteRequest.getCreditNoteRequestID()).isAmountValid();
        }
        return true;
    }
    
    public List<CreditNoteApproval> getApprovedCreditNoteApprovals(){
        return this.getCreditNoteApprovals("approvalStatus", RequestStatus.Approved);
    }

}
