/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.dbaccess;

import com.saburi.common.dbaccess.DBAccess;
import com.saburi.finance.entities.ReceiptInvoice;
import javafx.beans.property.*;
import java.util.ArrayList;
import com.saburi.common.entities.AppRevisionEntity;
import java.util.List;
import javafx.util.Pair;
import com.saburi.common.utils.SearchColumn;
import com.saburi.common.utils.SearchColumn.SearchDataTypes;
import org.hibernate.envers.RevisionType;
import com.saburi.finance.entities.Receipt;
import com.saburi.finance.entities.Invoice;
import static com.saburi.common.utils.Utilities.formatNumber;

public class ReceiptInvoiceDA extends DBAccess {

    private ReceiptInvoice receiptInvoice = new ReceiptInvoice();
    private final SimpleStringProperty receiptDisplay = new SimpleStringProperty(this, "receiptDisplay");
    private final SimpleObjectProperty receiptID = new SimpleObjectProperty(this, "receiptID");
    private Receipt receipt;
    private final SimpleStringProperty invoiceDisplay = new SimpleStringProperty(this, "invoiceDisplay");
    private final SimpleObjectProperty invoiceID = new SimpleObjectProperty(this, "invoiceID");
    private Invoice invoice;
    private final SimpleStringProperty receiptInvoiceID = new SimpleStringProperty(this, "receiptInvoiceID");
    private final SimpleObjectProperty invoiceType = new SimpleObjectProperty(this, "invoiceType");
    private final SimpleDoubleProperty invoiceAmount = new SimpleDoubleProperty(this, "invoiceAmount");
    private final SimpleStringProperty invoiceAmountDisplay = new SimpleStringProperty(this, "invoiceAmountDisplay");
    private final SimpleDoubleProperty amount = new SimpleDoubleProperty(this, "amount");
    private final SimpleStringProperty amountDisplay = new SimpleStringProperty(this, "amountDisplay");
      private final SimpleDoubleProperty amountRefunded = new SimpleDoubleProperty(this, "amountRefunded");
    private final SimpleStringProperty amountRefundedDisplay = new SimpleStringProperty(this, "amountRefundedDisplay");
    private final SimpleDoubleProperty netPaid = new SimpleDoubleProperty(this, "netPaid");
    private final SimpleStringProperty netPaidDisplay = new SimpleStringProperty(this, "netPaidDisplay");
   

    public ReceiptInvoiceDA() {
        createSearchColumns();
    }

    public ReceiptInvoiceDA(String persistenceUnit) {
        super(persistenceUnit);
    }

    public ReceiptInvoiceDA(ReceiptInvoice receiptInvoice) {
        this.receiptInvoice = receiptInvoice;
        initialseProprties();
        createSearchColumns();
    }

    public ReceiptInvoiceDA(String persistenceUnit, ReceiptInvoice receiptInvoice) {
        super(persistenceUnit);
        this.receiptInvoice = receiptInvoice;
        initialseProprties();
        createSearchColumns();
    }

    public ReceiptInvoiceDA(ReceiptDA receiptDA, InvoiceDA invoiceDA, String receiptInvoiceID, double amount) {
        this.receiptInvoice = new ReceiptInvoice(receiptDA != null ? (Receipt) receiptDA.getDBEntity() : null, invoiceDA != null ? (Invoice) invoiceDA.getDBEntity() : null, receiptInvoiceID, amount);
        initialseProprties();
        createSearchColumns();
    }

    public ReceiptInvoiceDA(String persistenceUnit, ReceiptDA receiptDA, InvoiceDA invoiceDA, String receiptInvoiceID, double amount) {
        super(persistenceUnit);
        this.receiptInvoice = new ReceiptInvoice(receiptDA != null ? (Receipt) receiptDA.getDBEntity() : null, invoiceDA != null ? (Invoice) invoiceDA.getDBEntity() : null, receiptInvoiceID, amount);
        initialseProprties();
        createSearchColumns();
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public Object getReceiptID() {
        return receiptID.get();
    }

    public String getReceiptDisplay() {
        return receiptDisplay.get();
    }

    public ReceiptDA getReceiptDA() {
        if (this.receipt == null) {
            return new ReceiptDA();
        } else {
            return new ReceiptDA(this.receipt);
        }
    }

    public Pair<String, Object> getReceiptPair() {
        if (this.getReceiptDA() == null) {
            return new Pair<>("", "");
        } else {
            return this.getReceiptDA().keyValuePair();
        }
    }

    public void setReceipt(Receipt receipt) {
        receiptInvoice.setReceipt(receipt);
        this.receipt = receipt;
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
        if (this.invoice == null) {
            return new InvoiceDA();
        } else {
            return new InvoiceDA(this.invoice);
        }
    }

    public Pair<String, Object> getInvoicePair() {
        if (this.getInvoiceDA() == null) {
            return new Pair<>("", "");
        } else {
            return this.getInvoiceDA().keyValuePair();
        }
    }

    public void setInvoice(Invoice invoice) {
        receiptInvoice.setInvoice(invoice);
        this.invoice = invoice;
        this.invoiceID.set(invoice.getInvoiceID());
        this.invoiceType.set(invoice.getInvoiceType());
        this.invoiceDisplay.set(invoice.getDisplayKey());
        this.setInvoiceAmount(invoice.getAmount()+invoice.getAmountRefunded()-invoice.getAmountPaid());
    }

    public String getReceiptInvoiceID() {
        return receiptInvoiceID.get();
    }

    public Object getInvoiceType() {
        return invoiceType.get();
    }

    public double getInvoiceAmount() {
        return invoiceAmount.get();
    }

    public String getInvoiceAmountDisplay() {
        return invoiceAmountDisplay.get();
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount.set(invoiceAmount);
        this.invoiceAmountDisplay.set(formatNumber(invoiceAmount));
    }

    public double getAmount() {
        return amount.get();
    }

    public String getAmountDisplay() {
        return amountDisplay.get();
    }

    public void setAmount(double amount) {
        receiptInvoice.setAmount(amount);
        this.amount.set(amount);
        this.amountDisplay.set(formatNumber(amount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReceiptInvoiceDA)) {
            return false;
        }

        ReceiptInvoiceDA receiptInvoiceDA = (ReceiptInvoiceDA) o;

        if (receiptInvoiceDA.getDBEntity() == null || this.getDBEntity() == null) {
            return false;
        }
        return this.getId().equals(receiptInvoiceDA.getId());
    }

    @Override
    public int hashCode() {
        return receiptInvoice.getId().hashCode();
    }

    private void initialseProprties() {
        this.dBEntity = receiptInvoice;
        this.receipt = receiptInvoice.getReceipt();
        if (this.receipt != null) {
            this.receiptID.set(receipt.getId());
            this.receiptDisplay.set(receipt.getDisplayKey());
        }
        this.invoice = receiptInvoice.getInvoice();
        if (this.invoice != null) {
            this.invoiceID.set(invoice.getId());
            this.invoiceDisplay.set(invoice.getDisplayKey());
            this.invoiceAmount.set(invoice.getAmount()-(invoice.getAmountPaid()-invoice.getAmountRefunded()));
            this.invoiceAmountDisplay.set(formatNumber(invoiceAmount.get()));
            this.invoiceType.set(invoice.getInvoiceType());

        }
        this.receiptInvoiceID.set(receiptInvoice.getReceiptInvoiceID());
        this.amount.set(receiptInvoice.getAmount());
        this.amountDisplay.set(formatNumber(amount.get()));
        this.amountRefunded.set(receiptInvoice.getAmountRefunded());
        this.amountRefundedDisplay.set(formatNumber(amountRefunded.get()));
        this.netPaid.set(amount.get()-amountRefunded.get());
        this.netPaidDisplay.set(formatNumber(netPaid.get()));
        initCommonProprties();
    }

    private void createSearchColumns() {
        this.searchColumns.add(new SearchColumn("receiptID", "Receipt ID", this.receiptID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("receiptDisplay", "Receipt", this.receiptDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("invoiceID", "Invoice ID", this.invoiceID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("invoiceDisplay", "Invoice", this.invoiceDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("receiptInvoiceID", "ReceiptInvoiceID", this.receiptInvoiceID.get(), SearchDataTypes.STRING, false));
        this.searchColumns.add(new SearchColumn("invoiceType", "Invoice Type", this.invoiceType.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("amount", "Amount", this.amount.get(),amountDisplay.get(), SearchDataTypes.NUMBER));
        this.searchColumns.add(new SearchColumn("amountRefunded", "Amount Refunded", this.amountRefunded.get(), amountRefundedDisplay.get(), SearchDataTypes.NUMBER));
        this.searchColumns.add(new SearchColumn("netPaid", "Net Paid", this.netPaid.get(),netPaidDisplay.get(), SearchDataTypes.NUMBER));
        this.searchColumns.addAll(this.getDefaultSearchColumns());
    }

    @Override
    public List<SearchColumn> getSearchColumns() {
        return this.searchColumns;
    }

    @Override
    public Object getId() {
        return this.receiptInvoice.getId();
    }

    @Override
    public String getDisplayKey() {
        return this.receiptInvoice.getDisplayKey();
    }

    public static List<ReceiptInvoiceDA> getReceiptInvoiceDAs(List<ReceiptInvoice> receiptInvoices) {
        List<ReceiptInvoiceDA> list = new ArrayList<>();
        receiptInvoices.forEach((receiptInvoice) -> {
            list.add(new ReceiptInvoiceDA(receiptInvoice));
        });
        return list;
    }

    public static List<ReceiptInvoice> getReceiptInvoiceList(List<ReceiptInvoiceDA> receiptInvoiceDAs) {
        List<ReceiptInvoice> receiptInvoices = new ArrayList<>();
        receiptInvoiceDAs.forEach(a -> receiptInvoices.add(a.receiptInvoice));
        return receiptInvoices;
    }

    public boolean save() throws Exception {
        return super.persist(this.receiptInvoice);

    }

    public boolean update() throws Exception {
        return super.merge(this.receiptInvoice);

    }

    public boolean delete() {
        return super.remove(this.receiptInvoice);

    }

    public ReceiptInvoice getReceiptInvoice(String receiptInvoiceID) {
        return (ReceiptInvoice) super.find(ReceiptInvoice.class, receiptInvoiceID);
    }

    public ReceiptInvoice getReceiptInvoice() {
        return this.receiptInvoice;
    }

    public List<ReceiptInvoice> getReceiptInvoices() {
        return super.find(ReceiptInvoice.class);
    }

    @Override
    public List<DBAccess> get() {
        List<DBAccess> list = new ArrayList<>();
        selectQuery(ReceiptInvoice.class).forEach(o -> list.add(new ReceiptInvoiceDA((ReceiptInvoice) o)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public ReceiptInvoiceDA get(String receiptInvoiceID) throws Exception {
        ReceiptInvoice oReceiptInvoice = getReceiptInvoice(receiptInvoiceID);
        if (oReceiptInvoice == null) {
            throw new Exception("No Record with id: " + receiptInvoiceID);
        }
        return new ReceiptInvoiceDA(oReceiptInvoice);
    }

    public List<ReceiptInvoiceDA> get(String columName, Object value) {
        List<ReceiptInvoiceDA> list = new ArrayList<>();
        super.selectQuery(ReceiptInvoice.class, columName, value).forEach(da -> list.add(new ReceiptInvoiceDA((ReceiptInvoice) da)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public List<ReceiptInvoiceDA> toDaList(List<ReceiptInvoice> receiptInvoices) {
        List<ReceiptInvoiceDA> receiptInvoiceDAs = new ArrayList<>();
        receiptInvoices.forEach(s -> receiptInvoiceDAs.add(new ReceiptInvoiceDA(s)));
        return receiptInvoiceDAs;
    }

    public List<DBAccess> toDBAccessList(List<ReceiptInvoice> receiptInvoices) {
        List<DBAccess> dbAccesses = new ArrayList<>();
        receiptInvoices.forEach(s -> dbAccesses.add(new ReceiptInvoiceDA(s)));
        return dbAccesses;
    }

    public int getMax(String columnName) {
        return super.getMax(ReceiptInvoice.class, columnName);
    }

    public int getMax(String columnName, String comparatorColumn, Object comparatorVaue) {
        return super.getMax(ReceiptInvoice.class, columnName, comparatorColumn, comparatorVaue);
    }

    public List<ReceiptInvoice> getReceiptInvoices(String columName, Object value) {
        return super.find(ReceiptInvoice.class, columName, value);
    }

    @Override
    public List<DBAccess> getRevisions() {
        try {

            List<Object[]> list = getEntityRevisions(ReceiptInvoice.class);
            List<DBAccess> dBAccesses = new ArrayList<>();
            list.forEach(e -> {

                ReceiptInvoiceDA receiptInvoiceDA = new ReceiptInvoiceDA((ReceiptInvoice) e[0]);
                receiptInvoiceDA.revisionEntity = (AppRevisionEntity) e[1];
                receiptInvoiceDA.oRevisionType = (RevisionType) e[2];
                receiptInvoiceDA.initRevProprties();
                receiptInvoiceDA.searchColumns.addAll(receiptInvoiceDA.getRevSearchColumns());
                dBAccesses.add(receiptInvoiceDA);

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

    public List<ReceiptInvoice> getReceiptInvoices(Invoice invoice) {
        return getReceiptInvoices("invoice", invoice);
    }
    
    public double getAmountPaid(Invoice invoice){
      return this.getSum(ReceiptInvoice.class,"amount", "invoice", invoice);
    }

}