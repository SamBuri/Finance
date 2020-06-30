/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.saburi.common.entities.DBEntity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import com.saburi.common.entities.LookupData;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"invoiceID", "itemID", "invoiceSourceID"}, name = "uqInvoiceItemInvoiceSourceID")
)
public class InvoiceDetails extends DBEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int invoiceDetailID;
    @NotNull(message = "The field: Invoice cannot be null")
    @OneToOne
    @JoinColumn(name = "invoiceID", foreignKey = @ForeignKey(name = "fkInvoiceIDInvoiceDetails"))
    private Invoice invoice;
    @NotNull(message = "The field: Item cannot be null")
    @OneToOne
    @JoinColumn(name = "itemID", foreignKey = @ForeignKey(name = "fkItemIDInvoiceDetails"))
    private Item item;
    @Size(max = 20, message = "The field: Invoice Source ID size cannot be greater than 20")
    @Column(length = 20)
    private String invoiceSourceID;
    @Column(name = "baseQuantity")
    private int baseQuantity;
    @Size(max = 100, message = "The field: Unit Measure size cannot be greater than 100")
    @Column(length = 100)
    private String unitMeasure;
    @Column(name = "measureSize")
    private int measureSize;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "unitPrice")
    private double unitPrice;
    @Column(name = "discount")
    private double discount;
    @Column(name = "amount")
    private double amount;
    @Column(name = "originalQuantity")
    private int originalQuantity;
    @Column(name = "originalAmount")
    private double originalAmount;
    @OneToOne
    @JoinColumn(name = "locationID", foreignKey = @ForeignKey(name = "fkLocationIDInvoiceDetails"))
    private LookupData location;

    public InvoiceDetails() {
    }

    public InvoiceDetails(int invoiceDetailID, Invoice invoice, Item item, String invoiceSourceID, int baseQuantity, String unitMeasure, int measureSize, int quantity, double unitPrice, double discount, double amount, int originalQuantity, double originalAmount, LookupData location) {
        this.invoiceDetailID = invoiceDetailID;
        this.invoice = invoice;
        this.item = item;
        this.invoiceSourceID = invoiceSourceID;
        this.baseQuantity = baseQuantity;
        this.unitMeasure = unitMeasure;
        this.measureSize = measureSize;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.amount = amount;
        this.originalQuantity = originalQuantity;
        this.originalAmount = originalAmount;
        this.location = location;

    }

    public int getInvoiceDetailID() {
        return invoiceDetailID;
    }

    public void setInvoiceDetailID(int invoiceDetailID) {
        this.invoiceDetailID = invoiceDetailID;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getInvoiceSourceID() {
        return invoiceSourceID;
    }

    public void setInvoiceSourceID(String invoiceSourceID) {
        this.invoiceSourceID = invoiceSourceID;
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

    public int getOriginalQuantity() {
        return originalQuantity;
    }

    public void setOriginalQuantity(int originalQuantity) {
        this.originalQuantity = originalQuantity;
    }

    public double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(double originalAmount) {
        this.originalAmount = originalAmount;
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
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        final InvoiceDetails invoiceDetails = (InvoiceDetails) o;
        if (!Objects.equals(this.invoice, invoiceDetails.invoice)) {
            return false;
        }
        if (!Objects.equals(this.item, invoiceDetails.item)) {
            return false;
        }
        return Objects.equals(this.invoiceSourceID, invoiceDetails.invoiceSourceID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.invoice) + Objects.hashCode(this.item) + Objects.hashCode(this.invoiceSourceID);
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
