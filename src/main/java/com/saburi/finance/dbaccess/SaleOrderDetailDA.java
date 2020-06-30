/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.dbaccess;

import com.saburi.common.dbaccess.DBAccess;
import com.saburi.finance.entities.SaleOrderDetail;
import javafx.beans.property.*;
import java.util.ArrayList;
import com.saburi.common.entities.AppRevisionEntity;
import com.saburi.finance.entities.Customer;
import java.util.List;
import com.saburi.common.utils.SearchColumn;
import com.saburi.common.utils.SearchColumn.SearchDataTypes;
import static com.saburi.common.utils.Utilities.formatInteger;
import org.hibernate.envers.RevisionType;
import com.saburi.finance.entities.SaleOrder;
import com.saburi.finance.entities.Item;
import com.saburi.finance.entities.MeasureRelation;
import java.util.Optional;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import com.saburi.finance.utils.FinanceEnums;
import static com.saburi.common.utils.Utilities.formatNumber;
import com.saburi.finance.utils.FinanceEnums.InvoiceStatus;

public class SaleOrderDetailDA extends DBAccess {
    
    private final SimpleIntegerProperty saleOrderDetailID = new SimpleIntegerProperty(this, "saleOrderDetailID");
    private final SimpleStringProperty saleOrderDetailIDDisplay = new SimpleStringProperty(this, "saleOrderDetailIDDisplay");
    private SaleOrderDetail saleOrderDetail = new SaleOrderDetail();
    private final SimpleStringProperty saleOrderDisplay = new SimpleStringProperty(this, "saleOrderDisplay");
    private final SimpleObjectProperty saleOrderID = new SimpleObjectProperty(this, "saleOrderID");
    private SaleOrder saleOrder;
    private final SimpleStringProperty itemDisplay = new SimpleStringProperty(this, "itemDisplay");
    private final SimpleObjectProperty itemID = new SimpleObjectProperty(this, "itemID");
    private Item item;
    private final SimpleIntegerProperty baseQuantity = new SimpleIntegerProperty(this, "baseQuantity");
    private final SimpleStringProperty baseQuantityDisplay = new SimpleStringProperty(this, "baseQuantityDisplay");
    private final SimpleStringProperty unitMeasure = new SimpleStringProperty(this, "unitMeasure");
    private final SimpleIntegerProperty measureSize = new SimpleIntegerProperty(this, "measureSize");
    private final SimpleStringProperty measureSizeDisplay = new SimpleStringProperty(this, "measureSizeDisplay");
    private final SimpleIntegerProperty quantity = new SimpleIntegerProperty(this, "quantity");
    private final SimpleStringProperty quantityDisplay = new SimpleStringProperty(this, "quantityDisplay");
    private final SimpleDoubleProperty unitPrice = new SimpleDoubleProperty(this, "unitPrice");
    private final SimpleStringProperty unitPriceDisplay = new SimpleStringProperty(this, "unitPriceDisplay");
    private final SimpleDoubleProperty discount = new SimpleDoubleProperty(this, "discount");
    private final SimpleStringProperty discountDisplay = new SimpleStringProperty(this, "discountDisplay");
    private final SimpleDoubleProperty amount = new SimpleDoubleProperty(this, "amount");
    private final SimpleStringProperty amountDisplay = new SimpleStringProperty(this, "amountDisplay");
    private final SimpleObjectProperty invoiceStatus = new SimpleObjectProperty(this, "invoiceStatus");
    private final SimpleStringProperty invoiceID = new SimpleStringProperty(this, "invoiceID");
    
    public SaleOrderDetailDA() {
        createSearchColumns();
    }
    
    public SaleOrderDetailDA(String persistenceUnit) {
        super(persistenceUnit);
    }
    
    public SaleOrderDetailDA(SaleOrderDetail saleOrderDetail) {
        this.saleOrderDetail = saleOrderDetail;
        initialseProprties();
        createSearchColumns();
    }
    
    public SaleOrderDetailDA(String persistenceUnit, SaleOrderDetail saleOrderDetail) {
        super(persistenceUnit);
        this.saleOrderDetail = saleOrderDetail;
        initialseProprties();
        createSearchColumns();
    }
    
    public SaleOrderDetailDA(SaleOrder saleOrder, Item item, int baseQuantity, String unitMeasure, int measureSize, int quantity, double unitPrice, double discount, double amount, InvoiceStatus invoiceStatus, String invoiceID) {
        this.saleOrderDetail = new SaleOrderDetail(saleOrder, item, baseQuantity, unitMeasure, measureSize, quantity, unitPrice, discount, amount, invoiceStatus, invoiceID);
        initialseProprties();
        createSearchColumns();
    }
    
    public SaleOrderDetailDA(String persistenceUnit, SaleOrder saleOrder, Item item, int baseQuantity, String unitMeasure, int measureSize, int quantity, double unitPrice, double discount, double amount, InvoiceStatus invoiceStatus, String invoiceID) {
        super(persistenceUnit);
        this.saleOrderDetail = new SaleOrderDetail(saleOrder, item, baseQuantity, unitMeasure, measureSize, quantity, unitPrice, discount, amount, invoiceStatus, invoiceID);
        initialseProprties();
        createSearchColumns();
    }
    
    public SaleOrder getSaleOrder() {
        return saleOrder;
    }
    
    public Object getSaleOrderID() {
        return saleOrderID.get();
    }
    
    public String getSaleOrderDisplay() {
        return saleOrderDisplay.get();
    }
    
    public SaleOrderDA getSaleOrderDA() {
        return this.saleOrder != null ? new SaleOrderDA(this.saleOrder) : null;
    }
    
    public void setSaleOrder(SaleOrder saleOrder) {
        saleOrderDetail.setSaleOrder(saleOrder);
        this.saleOrder = saleOrder;
        this.saleOrderID.set(saleOrder.getId());
        this.saleOrderDisplay.set(saleOrder.getDisplayKey());
    }
    
    public Item getItem() {
        return this.item;
    }
    
    public Object getItemID() {
        return itemID.get();
    }
    
    public String getItemDisplay() {
        return itemDisplay.get();
    }
    
    public ItemDA getItemDA() {
        return this.item != null ? new ItemDA(this.item) : null;
    }
    
    public void setItem(Item item) {
        saleOrderDetail.setItem(item);
        this.item = item;
        this.itemID.set(item.getId());
        this.itemDisplay.set(item.getDisplayKey());
        List<MeasureRelation> measureRelations = new MeasureGroupDA().getMeasureGroup(item.getMeasureGroup().getMeasureGroupID()).getMeasureRelations();
        
        Optional<MeasureRelation> invoiceMeasureRelation = measureRelations.stream()
                .filter((p) -> p.getDefaultUsage() != null)
                .filter((p) -> p.getDefaultUsage().equals(FinanceEnums.UnitMeasureUsages.Invoicing))
                .findFirst();
        if (invoiceMeasureRelation.isEmpty()) {
            this.setUnitMeasure(this.item.getMeasureGroup().getBaseUnitMeasure().getLookupDataName());
            this.setMeasureSize(1);
        } else {
            MeasureRelation invMeasureRelation = invoiceMeasureRelation.get();
            this.setUnitMeasure(invMeasureRelation.getUnitMeasure().getLookupDataName());
            this.setMeasureSize(invMeasureRelation.getBaseSize());
        }
        this.setBaseQuantity(1);
        
    }
    
    public int getSaleOrderDetailID() {
        return saleOrderDetailID.get();
    }
    
    public String getSaleOrderDetailIDDisplay() {
        return saleOrderDetailIDDisplay.get();
    }
    
    public int getBaseQuantity() {
        return baseQuantity.get();
    }
    
    public String getBaseQuantityDisplay() {
        return baseQuantityDisplay.get();
    }
    
    public void setBaseQuantity(int baseQuantity) {
        saleOrderDetail.setBaseQuantity(baseQuantity);
        this.baseQuantity.set(baseQuantity);
        this.setQuantity();
        this.baseQuantityDisplay.set(formatInteger(baseQuantity));
    }
    
    public String getUnitMeasure() {
        return unitMeasure.get();
    }
    
    public void setUnitMeasure(String unitMeasure) {
        saleOrderDetail.setUnitMeasure(unitMeasure);
        this.unitMeasure.set(unitMeasure);
    }
    
    public int getMeasureSize() {
        return measureSize.get();
    }
    
    public String getMeasureSizeDisplay() {
        return measureSizeDisplay.get();
    }
    
    public void setMeasureSize(int measureSize) {
        saleOrderDetail.setMeasureSize(measureSize);
        this.measureSize.set(measureSize);
        this.setQuantity();
        this.measureSizeDisplay.set(formatInteger(measureSize));
    }
    
    public int getQuantity() {
        return quantity.get();
    }
    
    public String getQuantityDisplay() {
        return quantityDisplay.get();
    }
    
    private void setQuantity() {
        int calQuantity = this.baseQuantity.get() * this.measureSize.get();
        saleOrderDetail.setQuantity(calQuantity);
        this.quantity.set(calQuantity);
        this.quantityDisplay.set(formatInteger(calQuantity));
        this.setAmount();
    }
    
    public double getUnitPrice() {
        return unitPrice.get();
    }
    
    public String getUnitPriceDisplay() {
        return unitPriceDisplay.get();
    }
    
    public void setUnitPrice(double unitPrice) {
        this.saleOrderDetail.setUnitPrice(unitPrice);
        this.unitPrice.set(unitPrice);
        this.unitPriceDisplay.set(formatNumber(unitPrice));
        this.setAmount();
    }
    
    public double getDiscount() {
        return discount.get();
    }
    
    public String getDiscountDisplay() {
        return discountDisplay.get();
    }
    
    public void setDiscount(double discount) {
        saleOrderDetail.setDiscount(discount);
        this.discount.set(discount);
        this.discountDisplay.set(formatNumber(discount));
        this.setAmount();
    }
    
    public double getAmount() {
        return amount.get();
    }
    
    public String getAmountDisplay() {
        return amountDisplay.get();
    }
    
    private void setAmount() {
        double amountCalc = (this.quantity.get() * this.unitPrice.get()) - this.discount.get();
        this.amount.set(amountCalc);
        this.amountDisplay.set(formatNumber(amountCalc));
        saleOrderDetail.setAmount(amountCalc);
        
    }
    
    public Object getInvoiceStatus() {
        return invoiceStatus.get();
    }
    
    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        saleOrderDetail.setInvoiceStatus(invoiceStatus);
        this.invoiceStatus.set(invoiceStatus);
    }
    
    public String getInvoiceID() {
        return invoiceID.get();
    }
    
    public void setInvoiceID(String invoiceID) {
        saleOrderDetail.setInvoiceID(invoiceID);
        this.invoiceID.set(invoiceID);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SaleOrderDetailDA)) {
            return false;
        }
        
        SaleOrderDetailDA saleOrderDetailDA = (SaleOrderDetailDA) o;
        
        if (saleOrderDetailDA.getDBEntity() == null || this.getDBEntity() == null) {
            return false;
        }
        return this.getId().equals(saleOrderDetailDA.getId());
    }
    
    @Override
    public int hashCode() {
        return saleOrderDetail.getId().hashCode();
    }
    
    private void initialseProprties() {
        this.dBEntity = saleOrderDetail;
        this.saleOrder = saleOrderDetail.getSaleOrder();
        this.saleOrderDetailID.set(saleOrderDetail.getSaleOrderDetailID());
        this.saleOrderDetailIDDisplay.set(formatInteger(saleOrderDetail.getSaleOrderDetailID()));
        if (this.saleOrder != null) {
            this.saleOrderID.set(saleOrder.getId());
            this.saleOrderDisplay.set(saleOrder.getDisplayKey());
        }
        this.item = saleOrderDetail.getItem();
        if (this.item != null) {
            this.itemID.set(item.getId());
            this.itemDisplay.set(item.getDisplayKey());
        }
        this.baseQuantity.set(saleOrderDetail.getBaseQuantity());
        this.baseQuantityDisplay.set(formatInteger(saleOrderDetail.getBaseQuantity()));
        this.unitMeasure.set(saleOrderDetail.getUnitMeasure());
        this.measureSize.set(saleOrderDetail.getMeasureSize());
        this.measureSizeDisplay.set(formatInteger(saleOrderDetail.getMeasureSize()));
        this.quantity.set(saleOrderDetail.getQuantity());
        this.quantityDisplay.set(formatInteger(saleOrderDetail.getQuantity()));
        this.unitPrice.set(saleOrderDetail.getUnitPrice());
        this.unitPriceDisplay.set(formatNumber(saleOrderDetail.getUnitPrice()));
        this.discount.set(saleOrderDetail.getDiscount());
        this.discountDisplay.set(formatNumber(saleOrderDetail.getDiscount()));
        this.amount.set(saleOrderDetail.getAmount());
        this.amountDisplay.set(formatNumber(saleOrderDetail.getAmount()));
        this.invoiceStatus.set(saleOrderDetail.getInvoiceStatus());
        this.invoiceID.set(saleOrderDetail.getInvoiceID());
        initCommonProprties();
    }
    
    private void createSearchColumns() {
        this.searchColumns.add(new SearchColumn("saleOrderDetailID", "Sale Order Detail ID", this.saleOrderDetailID.get(), saleOrderDetailIDDisplay.get(), SearchDataTypes.INTEGER));
        this.searchColumns.add(new SearchColumn("saleOrderID", "SaleOrder ID", this.saleOrderID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("saleOrderDisplay", "SaleOrder", this.saleOrderDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("itemID", "Item ID", this.itemID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("itemDisplay", "Item", this.itemDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("baseQuantity", "Base Quantity", this.baseQuantity.get(), baseQuantityDisplay.get(), SearchDataTypes.INTEGER));
        this.searchColumns.add(new SearchColumn("unitMeasure", "Unit Measure", this.unitMeasure.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("measureSize", "Measure Size", this.measureSize.get(), measureSizeDisplay.get(), SearchDataTypes.INTEGER));
        this.searchColumns.add(new SearchColumn("quantity", "Quantity", this.quantity.get(), quantityDisplay.get(), SearchDataTypes.INTEGER));
        this.searchColumns.add(new SearchColumn("unitPrice", "Unit Price", this.unitPrice.get(), unitPriceDisplay.get(), SearchDataTypes.MONEY));
        this.searchColumns.add(new SearchColumn("discount", "Discount", this.discount.get(), discountDisplay.get(), SearchDataTypes.MONEY));
        this.searchColumns.add(new SearchColumn("amount", "Amount", this.amount.get(), amountDisplay.get(), SearchDataTypes.MONEY));
        this.searchColumns.add(new SearchColumn("invoiceStatus", "Invoice Status", this.invoiceStatus.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal));
        this.searchColumns.add(new SearchColumn("invoiceID", "Invoice ID", this.invoiceID.get(), SearchDataTypes.STRING));
        this.searchColumns.addAll(this.getDefaultSearchColumns());
    }
    
    @Override
    public List<SearchColumn> getSearchColumns() {
        return this.searchColumns;
    }
    
    @Override
    public Object getId() {
        return this.saleOrderDetail.getId();
    }
    
    @Override
    public String getDisplayKey() {
        return this.saleOrderDetail.getDisplayKey();
    }
    
    public static List<SaleOrderDetailDA> getSaleOrderDetailDAs(List<SaleOrderDetail> saleOrderDetails) {
        List<SaleOrderDetailDA> list = new ArrayList<>();
        saleOrderDetails.forEach((saleOrderDetail) -> {
            list.add(new SaleOrderDetailDA(saleOrderDetail));
        });
        return list;
    }
    
    public static List<SaleOrderDetail> getSaleOrderDetailList(List<SaleOrderDetailDA> saleOrderDetailDAs) {
        List<SaleOrderDetail> saleOrderDetails = new ArrayList<>();
        saleOrderDetailDAs.forEach(a -> saleOrderDetails.add(a.saleOrderDetail));
        return saleOrderDetails;
    }
    
    public boolean save() throws Exception {
        if (!isValid()) {
            return false;
        }
        return super.persist(this.saleOrderDetail);
        
    }
    
    public boolean update() throws Exception {
        if (!isValid()) {
            return false;
        }
        return super.merge(this.saleOrderDetail);
        
    }
    
    public boolean delete() {
        return super.remove(this.saleOrderDetail);
        
    }
    
    public SaleOrderDetail getSaleOrderDetail(int saleOrderDetailID) {
        return (SaleOrderDetail) super.find(SaleOrderDetail.class, saleOrderDetailID);
    }
    
    public SaleOrderDetail getSaleOrderDetail() {
        return this.saleOrderDetail;
    }
    
    public List<SaleOrderDetail> getSaleOrderDetails() {
        return super.find(SaleOrderDetail.class);
    }
    
    @Override
    public List<DBAccess> get() {
        List<DBAccess> list = new ArrayList<>();
        selectQuery(SaleOrderDetail.class).forEach(o -> list.add(new SaleOrderDetailDA((SaleOrderDetail) o)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }
    
    public SaleOrderDetailDA get(int saleOrderDetailID) throws Exception {
        SaleOrderDetail oSaleOrderDetail = getSaleOrderDetail(saleOrderDetailID);
        if (oSaleOrderDetail == null) {
            throw new Exception("No Record with id: " + saleOrderDetailID);
        }
        return new SaleOrderDetailDA(oSaleOrderDetail);
    }
    
    public List<SaleOrderDetailDA> get(String columName, Object value) {
        List<SaleOrderDetailDA> list = new ArrayList<>();
        super.selectQuery(SaleOrderDetail.class, columName, value).forEach(da -> list.add(new SaleOrderDetailDA((SaleOrderDetail) da)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }
    
    public List<SaleOrderDetailDA> toDaList(List<SaleOrderDetail> saleOrderDetails) {
        List<SaleOrderDetailDA> saleOrderDetailDAs = new ArrayList<>();
        saleOrderDetails.forEach(s -> saleOrderDetailDAs.add(new SaleOrderDetailDA(s)));
        return saleOrderDetailDAs;
    }
    
    public List<DBAccess> toDBAccessList(List<SaleOrderDetail> saleOrderDetails) {
        List<DBAccess> dbAccesses = new ArrayList<>();
        saleOrderDetails.forEach(s -> dbAccesses.add(new SaleOrderDetailDA(s)));
        return dbAccesses;
    }
    
    public int getMax(String columnName) {
        return super.getMax(SaleOrderDetail.class, columnName);
    }
    
    public int getMax(String columnName, String comparatorColumn, Object comparatorVaue) {
        return super.getMax(SaleOrderDetail.class, columnName, comparatorColumn, comparatorVaue);
    }
    
    public List<SaleOrderDetail> getSaleOrderDetails(String columName, Object value) {
        return super.find(SaleOrderDetail.class, columName, value);
    }
    
    public boolean isValid() throws Exception {
        List<SearchColumn> lSearchColumns = new ArrayList<>();
        lSearchColumns.add(new SearchColumn("saleOrder", saleOrderDetail.getSaleOrder(), SearchColumn.SearchType.Equal));
        lSearchColumns.add(new SearchColumn("item", saleOrderDetail.getItem(), SearchColumn.SearchType.Equal));
        List<SaleOrderDetail> lSaleOrderDetail = super.find(SaleOrderDetail.class, lSearchColumns);
        lSaleOrderDetail.removeIf((p) -> p.getId().equals(saleOrderDetail.getId()));
        if (lSaleOrderDetail.size() > 0) {
            throw new Exception("The record with SaleOrder: " + saleOrderDetail.getSaleOrder().getDisplayKey() + " and Item: " + saleOrderDetail.getItem().getDisplayKey() + "already exists");
        }
        return true;
    }
    
    @Override
    public List<DBAccess> getRevisions() {
        try {
            
            List<Object[]> list = getEntityRevisions(SaleOrderDetail.class);
            List<DBAccess> dBAccesses = new ArrayList<>();
            list.forEach(e -> {
                
                SaleOrderDetailDA saleOrderDetailDA = new SaleOrderDetailDA((SaleOrderDetail) e[0]);
                saleOrderDetailDA.revisionEntity = (AppRevisionEntity) e[1];
                saleOrderDetailDA.oRevisionType = (RevisionType) e[2];
                saleOrderDetailDA.initRevProprties();
                saleOrderDetailDA.searchColumns.addAll(saleOrderDetailDA.getRevSearchColumns());
                dBAccesses.add(saleOrderDetailDA);
                
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
    
    public List<SaleOrderDetail> getPendingSaleOrderDetails(Customer customer) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<SaleOrderDetail> criteriaQuery = criteriaBuilder.createQuery(SaleOrderDetail.class);
            Root<SaleOrderDetail> root = criteriaQuery.from(SaleOrderDetail.class);
            Join<SaleOrderDetail, SaleOrder> bJoin = root.join("saleOrder");
            
            criteriaQuery.where(criteriaBuilder.equal(root.get("invoiceStatus"), InvoiceStatus.Pending),
                    criteriaBuilder.equal(bJoin.get("billTo"), customer));
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get("recordDateTime")));
            TypedQuery<SaleOrderDetail> typedQuery = entityManager.createQuery(criteriaQuery);
            return typedQuery.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.close();
        }
        
    }
    
    public List<SaleOrderDetail> getSaleOrderDetails(String column, Object value, String column1, Object value1) {
        return super.find(SaleOrderDetail.class, column, value, column1, value1);
    }
    
    public List<SaleOrderDetail> getSaleOrderDetails(SaleOrder saleOrder, Item item) {
        return getSaleOrderDetails("saleOrder", saleOrder, "item", item);
    }
    
    public SaleOrderDetail getSaleOrderDetail(SaleOrder saleOrder, Item item) {
        List<SaleOrderDetail> saleOrderDetails = getSaleOrderDetails(saleOrder, item);
        return saleOrderDetails.isEmpty() ? null : saleOrderDetails.get(0);
    }
    
    public SaleOrderDetail getSaleOrderDetail(String saleOrderID, Item item) {
        List<SaleOrderDetail> saleOrderDetails = getSaleOrderDetails(new SaleOrderDA().getSaleOrder(saleOrderID), item);
        return saleOrderDetails.isEmpty() ? null : saleOrderDetails.get(0);
    }
    
}
