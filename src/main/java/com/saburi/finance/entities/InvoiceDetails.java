/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.entities;

import com.saburi.common.entities.DBEntity;
import com.saburi.common.entities.LookupData;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class InvoiceDetails extends DBEntity {

    @NotNull(message = "The field: Invoice cannot be null")
    @OneToOne
    @JoinColumn(name = "invoiceID", foreignKey = @ForeignKey(name = "fkInvoiceIDInvoiceDetails"))
    private Invoice invoice;
    @NotNull(message = "The field: Item cannot be null")
    @OneToOne
    @JoinColumn(name = "itemID", foreignKey = @ForeignKey(name = "fkItemIDInvoiceDetails"))
    private Item item;
    @Id
    @NotNull(message = "The field: InvoiceDetailID cannot be null")
    @Size(max = 20, message = "The field: InvoiceDetailID size cannot be greater than 20")
    @Column(length = 20, updatable = false)
    private String invoiceDetailID;
    @OneToOne
    @JoinColumn(name = "saleOrderDetailsID", foreignKey = @ForeignKey(name = "fkSaleOrderDetailsIDInvoiceDetails"))
    private SaleOrderDetail saleOrderDetails;
    @OneToOne
    @JoinColumn(name = "sellToID", foreignKey = @ForeignKey(name = "fkSellToIDInvoiceDetails"))
    private Customer sellTo;
    private int baseQuantity;
    @Size(max = 100, message = "The field: Unit Measure size cannot be greater than 100")
    @Column(length = 100)
    private String unitMeasure;
    private int measureSize;
    @Min(value = 0, message = "Quantity can't be less than 0")
    private int quantity;
    private double unitPrice;
    private double discount;
    @Min(value = 0, message = "Amount can't be less than 0")
    private double amount;
    @Min(value = 1, message = "Original Quantity can't be less than 1")
    private int originalQuantity;
    @Min(value = 1, message = "Original Amount can't be less than 1")
    private double originalAmount;
    @OneToOne
    @JoinColumn(name = "locationID", foreignKey = @ForeignKey(name = "fkLocationIDInvoiceDetails"))
    private LookupData location;

    public InvoiceDetails() {
    }

    public InvoiceDetails(Invoice invoice, Item item, SaleOrderDetail saleOrderDetails, Customer sellTo, int baseQuantity, String unitMeasure, int measureSize, int quantity, double unitPrice, double discount, double amount, LookupData location) {
        this.invoice = invoice;
        this.item = item;
        this.saleOrderDetails = saleOrderDetails;
        this.sellTo = sellTo;
        this.baseQuantity = baseQuantity;
        this.unitMeasure = unitMeasure;
        this.measureSize = measureSize;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.amount = amount;
        this.originalQuantity = quantity;
        this.originalAmount = amount;
        this.location = location;

    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
        setInvoiceDetailID();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        setInvoiceDetailID();
    }

    public String getInvoiceDetailID() {
        return invoiceDetailID;
    }

    private void setInvoiceDetailID() {
        String invoicePart = invoice == null ? "" : this.invoice.getInvoiceID() == null ? "" : invoice.getInvoiceID();
        String itemPart = item == null ? "" : this.item.getItemID() == null ? "" : item.getItemID();
        String saleOrderDetailPart = saleOrderDetails == null ? "" : this.saleOrderDetails.getSaleOrder() == null ? "" : saleOrderDetails.getSaleOrder().getSaleOrderID();
        this.invoiceDetailID = invoicePart.concat(itemPart).concat(saleOrderDetailPart);
    }

    public SaleOrderDetail getSaleOrderDetails() {
        return saleOrderDetails;
    }

    public void setSaleOrderDetails(SaleOrderDetail saleOrderDetails) {
        this.saleOrderDetails = saleOrderDetails;
        setInvoiceDetailID();
    }

    public Customer getSellTo() {
        return sellTo;
    }

    public void setSellTo(Customer sellTo) {
        this.sellTo = sellTo;
    }

    public int getBaseQuantity() {
        return baseQuantity;
    }

    public void setBaseQuantity(int baseQuantity) {
        this.baseQuantity = baseQuantity;
    }

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
    }

    public int getMeasureSize() {
        return measureSize;
    }

    public void setMeasureSize(int measureSize) {
        this.measureSize = measureSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setOriginalQuantity(int originalQuantity) {
        this.originalQuantity = originalQuantity;
    }

    public void setOriginalAmount(double originalAmount) {
        this.originalAmount = originalAmount;
    }
    

    public double getOriginalAmount() {
        return originalAmount;
    }

    public int getOriginalQuantity() {
        return originalQuantity;
    }
    
    

    public LookupData getLocation() {
        return location;
    }

    public void setLocation(LookupData location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvoiceDetails)) {
            return false;
        }

        InvoiceDetails invoiceDetails = (InvoiceDetails) o;

        return this.getId().equals(invoiceDetails.getId());
    }

    @Override
    public int hashCode() {
        return this.invoiceDetailID.hashCode();

    }

    @Override
    public Object getId() {
        return this.invoiceDetailID;
    }

    @Override
    public String getDisplayKey() {
        return this.invoice.getDisplayKey() + " " + this.item.getDisplayKey();
    }

}