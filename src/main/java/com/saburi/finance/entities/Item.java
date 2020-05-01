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
import javax.validation.constraints.Size;
import java.util.List;
import javax.persistence.CascadeType;
import java.util.ArrayList;
import javax.persistence.OneToMany;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Item extends DBEntity {

    @Column(updatable = false)
    private int idHelper;
    @NotNull(message = "The field: Item Category cannot be null")
    @OneToOne
    @JoinColumn(name = "itemCategoryID", foreignKey = @ForeignKey(name = "fkItemCategoryIDItem"))
    private ItemCategory itemCategory;
    @Id
    @NotNull(message = "The field: Item ID cannot be null")
    @Size(max = 100, message = "The field: Item ID size cannot be greater than 100")
    @Column(length = 100, updatable = false)
    private String itemID;
    @Size(max = 100, message = "The field: Item Name size cannot be greater than 100")
    @NotNull(message = "The field: Item Name cannot be null")
    @Column(length = 100)
    private String itemName;
    private double unitCost;
    private double unitPrice;
    @OneToOne
    @JoinColumn(name = "itemGroupID", foreignKey = @ForeignKey(name = "fkItemGroupIDItem"))
    private LookupData itemGroup;
    @OneToOne
    @JoinColumn(name = "vATItemGroupID", foreignKey = @ForeignKey(name = "fkVATItemGroupIDItem"))
    private LookupData vATItemGroup;
    @OneToOne
    @JoinColumn(name = "inventoryGroupID", foreignKey = @ForeignKey(name = "fkInventoryGroupIDItem"))
    private LookupData inventoryGroup;
    @OneToOne
    @JoinColumn(name = "measureGroupID", foreignKey = @ForeignKey(name = "fkMeasureGroupIDItem"))
    private MeasureGroup measureGroup;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<ItemPriceGroup> itemPriceGroups = new ArrayList<>();

    public Item() {
    }

    public Item(int idHelper, ItemCategory itemCategory, String itemID, String itemName, double unitCost, double unitPrice, LookupData itemGroup, LookupData vATItemGroup, LookupData inventoryGroup, MeasureGroup measureGroup) {
        this.idHelper = idHelper;
        this.itemCategory = itemCategory;
        this.itemID = itemID;
        this.itemName = itemName;
        this.unitCost = unitCost;
        this.unitPrice = unitPrice;
        this.itemGroup = itemGroup;
        this.vATItemGroup = vATItemGroup;
        this.inventoryGroup = inventoryGroup;
        this.measureGroup = measureGroup;

    }

    public int getIdHelper() {
        return idHelper;
    }

    public void setIdHelper(int idHelper) {
        this.idHelper = idHelper;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LookupData getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(LookupData itemGroup) {
        this.itemGroup = itemGroup;
    }

    public LookupData getVATItemGroup() {
        return vATItemGroup;
    }

    public void setVATItemGroup(LookupData vATItemGroup) {
        this.vATItemGroup = vATItemGroup;
    }

    public LookupData getInventoryGroup() {
        return inventoryGroup;
    }

    public void setInventoryGroup(LookupData inventoryGroup) {
        this.inventoryGroup = inventoryGroup;
    }

    public MeasureGroup getMeasureGroup() {
        return measureGroup;
    }

    public void setMeasureGroup(MeasureGroup measureGroup) {
        this.measureGroup = measureGroup;
    }

    public List<ItemPriceGroup> getItemPriceGroups() {
        return itemPriceGroups;
    }

    public void setItemPriceGroups(List<ItemPriceGroup> itemPriceGroups) {
        this.itemPriceGroups = itemPriceGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }

        Item item = (Item) o;

        return this.getId().equals(item.getId());
    }

    @Override
    public int hashCode() {
        return this.itemID.hashCode();

    }

    @Override
    public Object getId() {
        return this.itemID;
    }

    @Override
    public String getDisplayKey() {
        return this.itemName;
    }

}
