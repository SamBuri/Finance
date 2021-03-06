/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.dbaccess;

import com.saburi.finance.entities.SaleOrder;
import javafx.beans.property.*;
import java.util.ArrayList;
import com.saburi.common.entities.AppRevisionEntity;
import java.util.List;
import com.saburi.common.utils.SearchColumn;
import com.saburi.common.utils.SearchColumn.SearchDataTypes;
import org.hibernate.envers.RevisionType;
import com.saburi.common.dbaccess.DBAccess;
import com.saburi.common.dbaccess.IDGeneratorDA;
import static com.saburi.common.utils.Utilities.formatInteger;
import java.time.LocalDate;
import static com.saburi.common.utils.Utilities.formatDate;
import com.saburi.finance.entities.Customer;
import static com.saburi.common.utils.Utilities.formatNumber;
import com.saburi.common.utils.CommonEnums.OpenStatus;
import com.saburi.common.utils.CommonEnums.EntryModes;
import com.saburi.finance.entities.SaleOrderDetail;

public class SaleOrderDA extends DBAccess {

    private SaleOrder saleOrder = new SaleOrder();
    private final SimpleIntegerProperty idHelper = new SimpleIntegerProperty(this, "idHelper");
    private final SimpleStringProperty idHelperDisplay = new SimpleStringProperty(this, "idHelperDisplay");
    private final SimpleStringProperty saleOrderID = new SimpleStringProperty(this, "saleOrderID");
    private final SimpleObjectProperty orderDate = new SimpleObjectProperty(this, "orderDate");
    private final SimpleStringProperty orderDateDisplay = new SimpleStringProperty(this, "orderDateDisplay");
    private final SimpleStringProperty shipAddress = new SimpleStringProperty(this, "shipAddress");
    private final SimpleStringProperty sellToDisplay = new SimpleStringProperty(this, "sellToDisplay");
    private final SimpleObjectProperty sellToID = new SimpleObjectProperty(this, "sellToID");
    private Customer sellTo;
    private final SimpleStringProperty billToDisplay = new SimpleStringProperty(this, "billToDisplay");
    private final SimpleObjectProperty billToID = new SimpleObjectProperty(this, "billToID");
    private Customer billTo;
    private final SimpleDoubleProperty amount = new SimpleDoubleProperty(this, "amount");
    private final SimpleStringProperty amountDisplay = new SimpleStringProperty(this, "amountDisplay");
    private final SimpleStringProperty amountWords = new SimpleStringProperty(this, "amountWords");
    private final SimpleObjectProperty status = new SimpleObjectProperty(this, "status");
    private final SimpleObjectProperty entryMode = new SimpleObjectProperty(this, "entryMode");
    private List<SaleOrderDetail> saleOrderDetails = new ArrayList<>();

    public SaleOrderDA() {
        createSearchColumns();
    }

    public SaleOrderDA(String persistenceUnit) {
        super(persistenceUnit);
    }

    public SaleOrderDA(SaleOrder saleOrder) {
        this.saleOrder = saleOrder;
        initialseProprties();
        createSearchColumns();
    }

    public SaleOrderDA(String persistenceUnit, SaleOrder saleOrder) {
        super(persistenceUnit);
        this.saleOrder = saleOrder;
        initialseProprties();
        createSearchColumns();
    }

    public SaleOrderDA(String saleOrderID, LocalDate orderDate, String shipAddress, Customer sellTo, Customer billTo, double amount, String amountWords, OpenStatus status, EntryModes entryMode) {
        this.saleOrder = new SaleOrder(getNextIdHelper(), saleOrderID, orderDate, shipAddress, sellTo, billTo, amount, amountWords, status, entryMode);
        initialseProprties();
        createSearchColumns();
    }

    public SaleOrderDA(String persistenceUnit, String saleOrderID, LocalDate orderDate, String shipAddress, Customer sellTo, Customer billTo, double amount, String amountWords, OpenStatus status, EntryModes entryMode) {
        super(persistenceUnit);
        this.saleOrder = new SaleOrder(getNextIdHelper(), saleOrderID, orderDate, shipAddress, sellTo, billTo, amount, amountWords, status, entryMode);
        initialseProprties();
        createSearchColumns();
    }

    public int getIdHelper() {
        return idHelper.get();
    }

    public String getIdHelperDisplay() {
        return idHelperDisplay.get();
    }

    public void setIdHelper(int idHelper) {
        saleOrder.setIdHelper(idHelper);
        this.idHelper.set(idHelper);
    }

    public String getSaleOrderID() {
        return saleOrderID.get();
    }

    public void setSaleOrderID(String saleOrderID) {
        saleOrder.setSaleOrderID(saleOrderID);
        this.saleOrderID.set(saleOrderID);
    }

    public Object getOrderDate() {
        return orderDate.get();
    }

    public String getOrderDateDisplay() {
        return orderDateDisplay.get();
    }

    public void setOrderDate(LocalDate orderDate) {
        saleOrder.setOrderDate(orderDate);
        this.orderDate.set(orderDate);
    }

    public String getShipAddress() {
        return shipAddress.get();
    }

    public void setShipAddress(String shipAddress) {
        saleOrder.setShipAddress(shipAddress);
        this.shipAddress.set(shipAddress);
    }

    public Customer getSellTo() {
        return sellTo;
    }

    public Object getSellToID() {
        return sellToID.get();
    }

    public String getSellToDisplay() {
        return sellToDisplay.get();
    }

    public CustomerDA getSellToDA() {
        return this.sellTo != null ? new CustomerDA(this.sellTo) : null;
    }

    public void setSellTo(Customer sellTo) {
        saleOrder.setSellTo(sellTo);
        this.sellTo = sellTo;
        this.sellToID.set(sellTo.getId());
        this.sellToDisplay.set(sellTo.getDisplayKey());
    }

    public Customer getBillTo() {
        return billTo;
    }

    public Object getBillToID() {
        return billToID.get();
    }

    public String getBillToDisplay() {
        return billToDisplay.get();
    }

    public CustomerDA getBillToDA() {
        return this.billTo != null ? new CustomerDA(this.billTo) : null;
    }

    public void setBillTo(Customer billTo) {
        saleOrder.setBillTo(billTo);
        this.billTo = billTo;
        this.billToID.set(billTo.getId());
        this.billToDisplay.set(billTo.getDisplayKey());
    }

    public double getAmount() {
        return amount.get();
    }

    public String getAmountDisplay() {
        return amountDisplay.get();
    }

    public void setAmount(double amount) {
        saleOrder.setAmount(amount);
        this.amount.set(amount);
        this.amountDisplay.set(formatNumber(amount));
    }

    public String getAmountWords() {
        return amountWords.get();
    }

    public void setAmountWords(String amountWords) {
        saleOrder.setAmountWords(amountWords);
        this.amountWords.set(amountWords);
    }

    public Object getStatus() {
        return status.get();
    }

    public void setStatus(OpenStatus status) {
        saleOrder.setStatus(status);
        this.status.set(status);
    }

    public Object getEntryMode() {
        return entryMode.get();
    }

    public void setEntryMode(EntryModes entryMode) {
        saleOrder.setEntryMode(entryMode);
        this.entryMode.set(entryMode);
    }

    public List<SaleOrderDetail> getSaleOrderDetails() {
        return saleOrderDetails;
    }

    public List<SaleOrderDetailDA> getSaleOrderDetailsDAs() {
        return SaleOrderDetailDA.getSaleOrderDetailDAs(saleOrder.getSaleOrderDetails());
    }

    public void setSaleOrderDetails(List<SaleOrderDetail> saleOrderDetails) {
        saleOrder.setSaleOrderDetails(saleOrderDetails);
        this.saleOrderDetails = saleOrderDetails;
    }

    public void setSaleOrderDetailsDAs(List<SaleOrderDetailDA> saleOrderDetailDAs) {
        this.saleOrder.setSaleOrderDetails(SaleOrderDetailDA.getSaleOrderDetailList(saleOrderDetailDAs));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SaleOrderDA)) {
            return false;
        }

        SaleOrderDA saleOrderDA = (SaleOrderDA) o;

        if (saleOrderDA.getDBEntity() == null || this.getDBEntity() == null) {
            return false;
        }
        return this.getId().equals(saleOrderDA.getId());
    }

    @Override
    public int hashCode() {
        return saleOrder.getId().hashCode();
    }

    private void initialseProprties() {
        this.dBEntity = saleOrder;
        this.idHelper.set(saleOrder.getIdHelper());
        this.idHelperDisplay.set(formatInteger(saleOrder.getIdHelper()));
        this.saleOrderID.set(saleOrder.getSaleOrderID());
        this.orderDate.set(saleOrder.getOrderDate());
        this.orderDateDisplay.set(formatDate(saleOrder.getOrderDate()));
        this.shipAddress.set(saleOrder.getShipAddress());
        this.sellTo = saleOrder.getSellTo();
        if (this.sellTo != null) {
            this.sellToID.set(sellTo.getId());
            this.sellToDisplay.set(sellTo.getDisplayKey());
        }
        this.billTo = saleOrder.getBillTo();
        if (this.billTo != null) {
            this.billToID.set(billTo.getId());
            this.billToDisplay.set(billTo.getDisplayKey());
        }
        this.amount.set(saleOrder.getAmount());
        this.amountDisplay.set(formatNumber(saleOrder.getAmount()));
        this.amountWords.set(saleOrder.getAmountWords());
        this.status.set(saleOrder.getStatus());
        this.entryMode.set(saleOrder.getEntryMode());
        initCommonProprties();
    }

    private void createSearchColumns() {
        this.searchColumns.add(new SearchColumn("saleOrderID", "Sale Order ID", this.saleOrderID.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("orderDate", "Order Date", this.orderDate.get(), orderDateDisplay.get(), SearchDataTypes.DATE));
        this.searchColumns.add(new SearchColumn("shipAddress", "Ship Address", this.shipAddress.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("sellToID", "Sell To ID", this.sellToID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("sellToDisplay", "Sell To", this.sellToDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("billToID", "Bill To ID", this.billToID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("billToDisplay", "Bill To", this.billToDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("amount", "Amount", this.amount.get(), amountDisplay.get(), SearchDataTypes.MONEY));
        this.searchColumns.add(new SearchColumn("amountWords", "Amount Words", this.amountWords.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("status", "Status", this.status.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal));
        this.searchColumns.add(new SearchColumn("entryMode", "Entry Mode", this.entryMode.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal));
        this.searchColumns.addAll(this.getDefaultSearchColumns());
    }

    @Override
    public List<SearchColumn> getSearchColumns() {
        return this.searchColumns;
    }

    @Override
    public Object getId() {
        return this.saleOrder.getId();
    }

    @Override
    public String getDisplayKey() {
        return this.saleOrder.getDisplayKey();
    }

    public static List<SaleOrderDA> getSaleOrderDAs(List<SaleOrder> saleOrders) {
        List<SaleOrderDA> list = new ArrayList<>();
        saleOrders.forEach((saleOrder) -> {
            list.add(new SaleOrderDA(saleOrder));
        });
        return list;
    }

    public static List<SaleOrder> getSaleOrderList(List<SaleOrderDA> saleOrderDAs) {
        List<SaleOrder> saleOrders = new ArrayList<>();
        saleOrderDAs.forEach(a -> saleOrders.add(a.saleOrder));
        return saleOrders;
    }

    public boolean save() throws Exception {
        if (!isValid()) {
            return false;
        }
        return super.persist(this.saleOrder);

    }

    public boolean update() throws Exception {
        if (!isValid()) {
            return false;
        }
        return super.merge(this.saleOrder);

    }

    public boolean delete() {
        return super.remove(this.saleOrder);

    }

    public SaleOrder getSaleOrder(String saleOrderID) {
        return (SaleOrder) super.findJoin(SaleOrder.class, saleOrderID, "saleOrderDetails");
    }

    public SaleOrder getSaleOrder() {
        return this.saleOrder;
    }

    public List<SaleOrder> getSaleOrders() {
        return super.find(SaleOrder.class);
    }

    @Override
    public List<DBAccess> get() {
        List<DBAccess> list = new ArrayList<>();
        selectQuery(SaleOrder.class).forEach(o -> list.add(new SaleOrderDA((SaleOrder) o)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public SaleOrderDA get(String saleOrderID) throws Exception {
        SaleOrder oSaleOrder = getSaleOrder(saleOrderID);
        if (oSaleOrder == null) {
            throw new Exception("No Record with id: " + saleOrderID);
        }
        return new SaleOrderDA(oSaleOrder);
    }

    public List<SaleOrderDA> get(String columName, Object value) {
        List<SaleOrderDA> list = new ArrayList<>();
        super.selectQuery(SaleOrder.class, columName, value).forEach(da -> list.add(new SaleOrderDA((SaleOrder) da)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public List<SaleOrderDA> toDaList(List<SaleOrder> saleOrders) {
        List<SaleOrderDA> saleOrderDAs = new ArrayList<>();
        saleOrders.forEach(s -> saleOrderDAs.add(new SaleOrderDA(s)));
        return saleOrderDAs;
    }

    public List<DBAccess> toDBAccessList(List<SaleOrder> saleOrders) {
        List<DBAccess> dbAccesses = new ArrayList<>();
        saleOrders.forEach(s -> dbAccesses.add(new SaleOrderDA(s)));
        return dbAccesses;
    }

    public int getMax(String columnName) {
        return super.getMax(SaleOrder.class, columnName);
    }

    public int getMax(String columnName, String comparatorColumn, Object comparatorVaue) {
        return super.getMax(SaleOrder.class, columnName, comparatorColumn, comparatorVaue);
    }

    public final int getNextIdHelper() {
        return this.getMax("idHelper") + 1;
    }

    public String getNextSaleOrderID(int idHelper) {
        return new IDGeneratorDA().getToAppendString(SaleOrder.class.getSimpleName(), idHelper);
    }

    public List<SaleOrder> getSaleOrders(String columName, Object value) {
        return super.find(SaleOrder.class, columName, value);
    }

    @Override
    public List<DBAccess> getRevisions() {
        try {

            List<Object[]> list = getEntityRevisions(SaleOrder.class);
            List<DBAccess> dBAccesses = new ArrayList<>();
            list.forEach(e -> {

                SaleOrderDA saleOrderDA = new SaleOrderDA((SaleOrder) e[0]);
                saleOrderDA.revisionEntity = (AppRevisionEntity) e[1];
                saleOrderDA.oRevisionType = (RevisionType) e[2];
                saleOrderDA.initRevProprties();
                saleOrderDA.searchColumns.addAll(saleOrderDA.getRevSearchColumns());
                dBAccesses.add(saleOrderDA);

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
        if (saleOrder.getStatus().equals(OpenStatus.Closed)) {
            throw new Exception("The sales order with id " + saleOrderID.get() + " is already closed. You can't update it");
        }
        return true;
    }

}
