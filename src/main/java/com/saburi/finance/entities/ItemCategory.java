/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.entities;

import com.saburi.common.entities.DBEntity;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import javax.validation.constraints.NotNull;
import com.saburi.finance.utils.FinanceEnums.ItemCategoryGroups;
import javax.persistence.Enumerated;
import javax.persistence.Column;
import javax.validation.constraints.Size;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class ItemCategory extends DBEntity {

    @Column(updatable = false)
    private int idHelper;
    @NotNull(message = "The field: Item Group cannot be null")
    @Enumerated
    private ItemCategoryGroups itemCategoryGroup;
    @Id
    @NotNull(message = "The field: Item Category ID cannot be null")
    @Size(max = 20, message = "The field: Item Category ID size cannot be greater than 20")
    @Column(length = 20, updatable = false)
    private String itemCategoryID;
    @Size(max = 100, message = "The field: Item Category Name size cannot be greater than 100")
    @NotNull(message = "The field: Item Category Name cannot be null")
    @Column(length = 100, unique = true)
    private String itemCategoryName;
    private boolean hidden;

    public ItemCategory() {
    }

    public ItemCategory(int idHelper, ItemCategoryGroups itemCategoryGroup, String itemCategoryID, String itemCategoryName, boolean hidden) {
        this.idHelper = idHelper;
        this.itemCategoryGroup = itemCategoryGroup;
        this.itemCategoryID = itemCategoryID;
        this.itemCategoryName = itemCategoryName;
        this.hidden = hidden;

    }

    public int getIdHelper() {
        return idHelper;
    }

    public void setIdHelper(int idHelper) {
        this.idHelper = idHelper;
    }

    public ItemCategoryGroups getItemCategoryGroup() {
        return itemCategoryGroup;
    }

    public void setItemCategoryGroup(ItemCategoryGroups itemCategoryGroup) {
        this.itemCategoryGroup = itemCategoryGroup;
    }

   

    public String getItemCategoryID() {
        return itemCategoryID;
    }

    public void setItemCategoryID(String itemCategoryID) {
        this.itemCategoryID = itemCategoryID;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemCategory)) {
            return false;
        }

        ItemCategory itemCategory = (ItemCategory) o;

        return this.getId().equals(itemCategory.getId());
    }

    @Override
    public int hashCode() {
        return this.itemCategoryID.hashCode();

    }

    @Override
    public Object getId() {
        return this.itemCategoryID;
    }

    @Override
    public String getDisplayKey() {
        return this.itemCategoryName;
    }

}