/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.dbaccess;

import com.saburi.common.dbaccess.DBAccess;
import com.saburi.common.dbaccess.IDGeneratorDA;
import com.saburi.common.dbaccess.LookupDataDA;
import com.saburi.finance.entities.GeneralPostingGroup;
import javafx.beans.property.*;
import java.util.ArrayList;
import com.saburi.common.entities.AppRevisionEntity;
import java.util.List;
import com.saburi.common.utils.SearchColumn;
import com.saburi.common.utils.SearchColumn.SearchDataTypes;
import org.hibernate.envers.RevisionType;
import com.saburi.common.entities.LookupData;
import com.saburi.finance.entities.ChartAccount;

public class GeneralPostingGroupDA extends DBAccess {

    private GeneralPostingGroup generalPostingGroup = new GeneralPostingGroup();
    private final SimpleIntegerProperty idHelper = new SimpleIntegerProperty(this, "idHelper");
    private final SimpleStringProperty generalPostingID = new SimpleStringProperty(this, "generalPostingID");
    private final SimpleStringProperty businessGroupDisplay = new SimpleStringProperty(this, "businessGroupDisplay");
    private final SimpleObjectProperty businessGroupID = new SimpleObjectProperty(this, "businessGroupID");
    private LookupData businessGroup;
    private final SimpleStringProperty itemGroupDisplay = new SimpleStringProperty(this, "itemGroupDisplay");
    private final SimpleObjectProperty itemGroupID = new SimpleObjectProperty(this, "itemGroupID");
    private LookupData itemGroup;
    private final SimpleStringProperty salesAccountDisplay = new SimpleStringProperty(this, "salesAccountDisplay");
    private final SimpleObjectProperty salesAccountID = new SimpleObjectProperty(this, "salesAccountID");
    private ChartAccount salesAccount;
    private final SimpleStringProperty salesDiscountAccountDisplay = new SimpleStringProperty(this, "salesDiscountAccountDisplay");
    private final SimpleObjectProperty salesDiscountAccountID = new SimpleObjectProperty(this, "salesDiscountAccountID");
    private ChartAccount salesDiscountAccount;
    private final SimpleStringProperty salesReturnAccountDisplay = new SimpleStringProperty(this, "salesReturnAccountDisplay");
    private final SimpleObjectProperty salesReturnAccountID = new SimpleObjectProperty(this, "salesReturnAccountID");
    private ChartAccount salesReturnAccount;
    private final SimpleStringProperty purchasesAccountDisplay = new SimpleStringProperty(this, "purchasesAccountDisplay");
    private final SimpleObjectProperty purchasesAccountID = new SimpleObjectProperty(this, "purchasesAccountID");
    private ChartAccount purchasesAccount;
    private final SimpleStringProperty directCostAppliedAccountDisplay = new SimpleStringProperty(this, "directCostAppliedAccountDisplay");
    private final SimpleObjectProperty directCostAppliedAccountID = new SimpleObjectProperty(this, "directCostAppliedAccountID");
    private ChartAccount directCostAppliedAccount;
    private final SimpleStringProperty purchaseDiscountAccountDisplay = new SimpleStringProperty(this, "purchaseDiscountAccountDisplay");
    private final SimpleObjectProperty purchaseDiscountAccountID = new SimpleObjectProperty(this, "purchaseDiscountAccountID");
    private ChartAccount purchaseDiscountAccount;
    private final SimpleStringProperty purchaseReturnAccountDisplay = new SimpleStringProperty(this, "purchaseReturnAccountDisplay");
    private final SimpleObjectProperty purchaseReturnAccountID = new SimpleObjectProperty(this, "purchaseReturnAccountID");
    private ChartAccount purchaseReturnAccount;

    public GeneralPostingGroupDA() {
        createSearchColumns();
    }

    public GeneralPostingGroupDA(String persistenceUnit) {
        super(persistenceUnit);
    }

    public GeneralPostingGroupDA(GeneralPostingGroup generalPostingGroup) {
        this.generalPostingGroup = generalPostingGroup;
        initialseProprties();
        createSearchColumns();
    }

    public GeneralPostingGroupDA(String persistenceUnit, GeneralPostingGroup generalPostingGroup) {
        super(persistenceUnit);
        this.generalPostingGroup = generalPostingGroup;
        initialseProprties();
        createSearchColumns();
    }

    public GeneralPostingGroupDA(String generalPostingID, LookupData businessGroup, LookupData itemGroup, ChartAccount salesAccount, ChartAccount salesDiscountAccount, ChartAccount salesReturnAccount, ChartAccount purchasesAccount, ChartAccount directCostAppliedAccount, ChartAccount purchaseDiscountAccount, ChartAccount purchaseReturnAccount) {
        this.generalPostingGroup = new GeneralPostingGroup(getNextIdHelper(), generalPostingID, businessGroup, itemGroup, salesAccount, salesDiscountAccount, salesReturnAccount, purchasesAccount, directCostAppliedAccount, purchaseDiscountAccount, purchaseReturnAccount);
        initialseProprties();
        createSearchColumns();
    }

    public GeneralPostingGroupDA(String persistenceUnit, String generalPostingID, LookupData businessGroup, LookupData itemGroup, ChartAccount salesAccount, ChartAccount salesDiscountAccount, ChartAccount salesReturnAccount, ChartAccount purchasesAccount, ChartAccount directCostAppliedAccount, ChartAccount purchaseDiscountAccount, ChartAccount purchaseReturnAccount) {
        super(persistenceUnit);
        this.generalPostingGroup = new GeneralPostingGroup(getNextIdHelper(), generalPostingID, businessGroup, itemGroup, salesAccount, salesDiscountAccount, salesReturnAccount, purchasesAccount, directCostAppliedAccount, purchaseDiscountAccount, purchaseReturnAccount);
        initialseProprties();
        createSearchColumns();
    }

    public int getIdHelper() {
        return idHelper.get();
    }

    public void setIdHelper(int idHelper) {
        generalPostingGroup.setIdHelper(idHelper);
        this.idHelper.set(idHelper);
    }

    public String getGeneralPostingID() {
        return generalPostingID.get();
    }

    public void setGeneralPostingID(String generalPostingID) {
        generalPostingGroup.setGeneralPostingID(generalPostingID);
        this.generalPostingID.set(generalPostingID);
    }

    public LookupData getBusinessGroup() {
        return businessGroup;
    }

    public Object getBusinessGroupID() {
        return businessGroupID.get();
    }

    public String getBusinessGroupDisplay() {
        return businessGroupDisplay.get();
    }

    public LookupDataDA getBusinessGroupDA() {
        return this.businessGroup != null ? new LookupDataDA(this.businessGroup) : null;
    }

    public void setBusinessGroup(LookupData businessGroup) {
        generalPostingGroup.setBusinessGroup(businessGroup);
        this.businessGroup = businessGroup;
        this.businessGroupID.set(businessGroup.getId());
        this.businessGroupDisplay.set(businessGroup.getDisplayKey());
    }

    public LookupData getItemGroup() {
        return itemGroup;
    }

    public Object getItemGroupID() {
        return itemGroupID.get();
    }

    public String getItemGroupDisplay() {
        return itemGroupDisplay.get();
    }

    public LookupDataDA getItemGroupDA() {
        return this.itemGroup != null ? new LookupDataDA(this.itemGroup) : null;
    }

    public void setItemGroup(LookupData itemGroup) {
        generalPostingGroup.setItemGroup(itemGroup);
        this.itemGroup = itemGroup;
        this.itemGroupID.set(itemGroup.getId());
        this.itemGroupDisplay.set(itemGroup.getDisplayKey());
    }

    public ChartAccount getSalesAccount() {
        return salesAccount;
    }

    public Object getSalesAccountID() {
        return salesAccountID.get();
    }

    public String getSalesAccountDisplay() {
        return salesAccountDisplay.get();
    }

    public ChartAccountDA getSalesAccountDA() {
        return this.salesAccount != null ? new ChartAccountDA(this.salesAccount) : null;
    }

    public void setSalesAccount(ChartAccount salesAccount) {
        generalPostingGroup.setSalesAccount(salesAccount);
        this.salesAccount = salesAccount;
        this.salesAccountID.set(salesAccount.getId());
        this.salesAccountDisplay.set(salesAccount.getDisplayKey());
    }

    public ChartAccount getSalesDiscountAccount() {
        return salesDiscountAccount;
    }

    public Object getSalesDiscountAccountID() {
        return salesDiscountAccountID.get();
    }

    public String getSalesDiscountAccountDisplay() {
        return salesDiscountAccountDisplay.get();
    }

    public ChartAccountDA getSalesDiscountAccountDA() {
        return this.salesDiscountAccount != null ? new ChartAccountDA(this.salesDiscountAccount) : null;
    }

    public void setSalesDiscountAccount(ChartAccount salesDiscountAccount) {
        generalPostingGroup.setSalesDiscountAccount(salesDiscountAccount);
        this.salesDiscountAccount = salesDiscountAccount;
        this.salesDiscountAccountID.set(salesDiscountAccount.getId());
        this.salesDiscountAccountDisplay.set(salesDiscountAccount.getDisplayKey());
    }

    public ChartAccount getSalesReturnAccount() {
        return salesReturnAccount;
    }

    public Object getSalesReturnAccountID() {
        return salesReturnAccountID.get();
    }

    public String getSalesReturnAccountDisplay() {
        return salesReturnAccountDisplay.get();
    }

    public ChartAccountDA getSalesReturnAccountDA() {
        return this.salesReturnAccount != null ? new ChartAccountDA(this.salesReturnAccount) : null;
    }

    public void setSalesReturnAccount(ChartAccount salesReturnAccount) {
        generalPostingGroup.setSalesReturnAccount(salesReturnAccount);
        this.salesReturnAccount = salesReturnAccount;
        this.salesReturnAccountID.set(salesReturnAccount.getId());
        this.salesReturnAccountDisplay.set(salesReturnAccount.getDisplayKey());
    }

    public ChartAccount getPurchasesAccount() {
        return purchasesAccount;
    }

    public Object getPurchasesAccountID() {
        return purchasesAccountID.get();
    }

    public String getPurchasesAccountDisplay() {
        return purchasesAccountDisplay.get();
    }

    public ChartAccountDA getPurchasesAccountDA() {
        return this.purchasesAccount != null ? new ChartAccountDA(this.purchasesAccount) : null;
    }

    public void setPurchasesAccount(ChartAccount purchasesAccount) {
        generalPostingGroup.setPurchasesAccount(purchasesAccount);
        this.purchasesAccount = purchasesAccount;
        this.purchasesAccountID.set(purchasesAccount.getId());
        this.purchasesAccountDisplay.set(purchasesAccount.getDisplayKey());
    }

    public ChartAccount getDirectCostAppliedAccount() {
        return directCostAppliedAccount;
    }

    public Object getDirectCostAppliedAccountID() {
        return directCostAppliedAccountID.get();
    }

    public String getDirectCostAppliedAccountDisplay() {
        return directCostAppliedAccountDisplay.get();
    }

    public ChartAccountDA getDirectCostAppliedAccountDA() {
        return this.directCostAppliedAccount != null ? new ChartAccountDA(this.directCostAppliedAccount) : null;
    }

    public void setDirectCostAppliedAccount(ChartAccount directCostAppliedAccount) {
        generalPostingGroup.setDirectCostAppliedAccount(directCostAppliedAccount);
        this.directCostAppliedAccount = directCostAppliedAccount;
        this.directCostAppliedAccountID.set(directCostAppliedAccount.getId());
        this.directCostAppliedAccountDisplay.set(directCostAppliedAccount.getDisplayKey());
    }

    public ChartAccount getPurchaseDiscountAccount() {
        return purchaseDiscountAccount;
    }

    public Object getPurchaseDiscountAccountID() {
        return purchaseDiscountAccountID.get();
    }

    public String getPurchaseDiscountAccountDisplay() {
        return purchaseDiscountAccountDisplay.get();
    }

    public ChartAccountDA getPurchaseDiscountAccountDA() {
        return this.purchaseDiscountAccount != null ? new ChartAccountDA(this.purchaseDiscountAccount) : null;
    }

    public void setPurchaseDiscountAccount(ChartAccount purchaseDiscountAccount) {
        generalPostingGroup.setPurchaseDiscountAccount(purchaseDiscountAccount);
        this.purchaseDiscountAccount = purchaseDiscountAccount;
        this.purchaseDiscountAccountID.set(purchaseDiscountAccount.getId());
        this.purchaseDiscountAccountDisplay.set(purchaseDiscountAccount.getDisplayKey());
    }

    public ChartAccount getPurchaseReturnAccount() {
        return purchaseReturnAccount;
    }

    public Object getPurchaseReturnAccountID() {
        return purchaseReturnAccountID.get();
    }

    public String getPurchaseReturnAccountDisplay() {
        return purchaseReturnAccountDisplay.get();
    }

    public ChartAccountDA getPurchaseReturnAccountDA() {
        return this.purchaseReturnAccount != null ? new ChartAccountDA(this.purchaseReturnAccount) : null;
    }

    public void setPurchaseReturnAccount(ChartAccount purchaseReturnAccount) {
        generalPostingGroup.setPurchaseReturnAccount(purchaseReturnAccount);
        this.purchaseReturnAccount = purchaseReturnAccount;
        this.purchaseReturnAccountID.set(purchaseReturnAccount.getId());
        this.purchaseReturnAccountDisplay.set(purchaseReturnAccount.getDisplayKey());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeneralPostingGroupDA)) {
            return false;
        }

        GeneralPostingGroupDA generalPostingGroupDA = (GeneralPostingGroupDA) o;

        if (generalPostingGroupDA.getDBEntity() == null || this.getDBEntity() == null) {
            return false;
        }
        return this.getId().equals(generalPostingGroupDA.getId());
    }

    @Override
    public int hashCode() {
        return generalPostingGroup.getId().hashCode();
    }

    private void initialseProprties() {
        this.dBEntity = generalPostingGroup;
        this.idHelper.set(generalPostingGroup.getIdHelper());
        this.generalPostingID.set(generalPostingGroup.getGeneralPostingID());
        this.businessGroup = generalPostingGroup.getBusinessGroup();
        if (this.businessGroup != null) {
            this.businessGroupID.set(businessGroup.getId());
            this.businessGroupDisplay.set(businessGroup.getDisplayKey());
        }
        this.itemGroup = generalPostingGroup.getItemGroup();
        if (this.itemGroup != null) {
            this.itemGroupID.set(itemGroup.getId());
            this.itemGroupDisplay.set(itemGroup.getDisplayKey());
        }
        this.salesAccount = generalPostingGroup.getSalesAccount();
        if (this.salesAccount != null) {
            this.salesAccountID.set(salesAccount.getId());
            this.salesAccountDisplay.set(salesAccount.getDisplayKey());
        }
        this.salesDiscountAccount = generalPostingGroup.getSalesDiscountAccount();
        if (this.salesDiscountAccount != null) {
            this.salesDiscountAccountID.set(salesDiscountAccount.getId());
            this.salesDiscountAccountDisplay.set(salesDiscountAccount.getDisplayKey());
        }
        this.salesReturnAccount = generalPostingGroup.getSalesReturnAccount();
        if (this.salesReturnAccount != null) {
            this.salesReturnAccountID.set(salesReturnAccount.getId());
            this.salesReturnAccountDisplay.set(salesReturnAccount.getDisplayKey());
        }
        this.purchasesAccount = generalPostingGroup.getPurchasesAccount();
        if (this.purchasesAccount != null) {
            this.purchasesAccountID.set(purchasesAccount.getId());
            this.purchasesAccountDisplay.set(purchasesAccount.getDisplayKey());
        }
        this.directCostAppliedAccount = generalPostingGroup.getDirectCostAppliedAccount();
        if (this.directCostAppliedAccount != null) {
            this.directCostAppliedAccountID.set(directCostAppliedAccount.getId());
            this.directCostAppliedAccountDisplay.set(directCostAppliedAccount.getDisplayKey());
        }
        this.purchaseDiscountAccount = generalPostingGroup.getPurchaseDiscountAccount();
        if (this.purchaseDiscountAccount != null) {
            this.purchaseDiscountAccountID.set(purchaseDiscountAccount.getId());
            this.purchaseDiscountAccountDisplay.set(purchaseDiscountAccount.getDisplayKey());
        }
        this.purchaseReturnAccount = generalPostingGroup.getPurchaseReturnAccount();
        if (this.purchaseReturnAccount != null) {
            this.purchaseReturnAccountID.set(purchaseReturnAccount.getId());
            this.purchaseReturnAccountDisplay.set(purchaseReturnAccount.getDisplayKey());
        }
        initCommonProprties();
    }

    private void createSearchColumns() {
        this.searchColumns.add(new SearchColumn("generalPostingID", "General Posting ID", this.generalPostingID.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("businessGroupID", "Business Group ID", this.businessGroupID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("businessGroupDisplay", "Business Group", this.businessGroupDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("itemGroupID", "Item Group ID", this.itemGroupID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("itemGroupDisplay", "Item Group", this.itemGroupDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("salesAccountID", "Sales Account ID", this.salesAccountID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("salesAccountDisplay", "Sales Account", this.salesAccountDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("salesDiscountAccountID", "Sales Discout Account ID", this.salesDiscountAccountID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("salesDiscountAccountDisplay", "Sales Discout Account", this.salesDiscountAccountDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("salesReturnAccountID", "Sales Return Account ID", this.salesReturnAccountID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("salesReturnAccountDisplay", "Sales Return Account", this.salesReturnAccountDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("purchasesAccountID", "Purchases Account ID", this.purchasesAccountID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("purchasesAccountDisplay", "Purchases Account", this.purchasesAccountDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("directCostAppliedAccountID", "Direct Cost Applied Account ID", this.directCostAppliedAccountID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("directCostAppliedAccountDisplay", "Direct Cost Applied Account", this.directCostAppliedAccountDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("purchaseDiscountAccountID", "Purchase Discount Account ID", this.purchaseDiscountAccountID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("purchaseDiscountAccountDisplay", "Purchase Discount Account", this.purchaseDiscountAccountDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("purchaseReturnAccountID", "Purchase Return Account ID", this.purchaseReturnAccountID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("purchaseReturnAccountDisplay", "Purchase Return Account", this.purchaseReturnAccountDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.addAll(this.getDefaultSearchColumns());
    }

    @Override
    public List<SearchColumn> getSearchColumns() {
        return this.searchColumns;
    }

    @Override
    public Object getId() {
        return this.generalPostingGroup.getId();
    }

    @Override
    public String getDisplayKey() {
        return this.generalPostingGroup.getDisplayKey();
    }

    public static List<GeneralPostingGroupDA> getGeneralPostingGroupDAs(List<GeneralPostingGroup> generalPostingGroups) {
        List<GeneralPostingGroupDA> list = new ArrayList<>();
        generalPostingGroups.forEach((generalPostingGroup) -> {
            list.add(new GeneralPostingGroupDA(generalPostingGroup));
        });
        return list;
    }

    public static List<GeneralPostingGroup> getGeneralPostingGroupList(List<GeneralPostingGroupDA> generalPostingGroupDAs) {
        List<GeneralPostingGroup> generalPostingGroups = new ArrayList<>();
        generalPostingGroupDAs.forEach(a -> generalPostingGroups.add(a.generalPostingGroup));
        return generalPostingGroups;
    }

    public boolean save() throws Exception {
        if (!isValid()) {
            return false;
        }
        return super.persist(this.generalPostingGroup);

    }

    public boolean update() throws Exception {
        if (!isValid()) {
            return false;
        }
        return super.merge(this.generalPostingGroup);

    }

    public boolean delete() {
        return super.remove(this.generalPostingGroup);

    }

    public GeneralPostingGroup getGeneralPostingGroup(String generalPostingID) {
        return (GeneralPostingGroup) super.find(GeneralPostingGroup.class, generalPostingID);
    }

    public GeneralPostingGroup getGeneralPostingGroup() {
        return this.generalPostingGroup;
    }

    public List<GeneralPostingGroup> getGeneralPostingGroups() {
        return super.find(GeneralPostingGroup.class);
    }

    @Override
    public List<DBAccess> get() {
        List<DBAccess> list = new ArrayList<>();
        selectQuery(GeneralPostingGroup.class).forEach(o -> list.add(new GeneralPostingGroupDA((GeneralPostingGroup) o)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public GeneralPostingGroupDA get(String generalPostingID) throws Exception {
        GeneralPostingGroup oGeneralPostingGroup = getGeneralPostingGroup(generalPostingID);
        if (oGeneralPostingGroup == null) {
            throw new Exception("No Record with id: " + generalPostingID);
        }
        return new GeneralPostingGroupDA(oGeneralPostingGroup);
    }

    public List<GeneralPostingGroupDA> get(String columName, Object value) {
        List<GeneralPostingGroupDA> list = new ArrayList<>();
        super.selectQuery(GeneralPostingGroup.class, columName, value).forEach(da -> list.add(new GeneralPostingGroupDA((GeneralPostingGroup) da)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public List<GeneralPostingGroupDA> toDaList(List<GeneralPostingGroup> generalPostingGroups) {
        List<GeneralPostingGroupDA> generalPostingGroupDAs = new ArrayList<>();
        generalPostingGroups.forEach(s -> generalPostingGroupDAs.add(new GeneralPostingGroupDA(s)));
        return generalPostingGroupDAs;
    }

    public List<DBAccess> toDBAccessList(List<GeneralPostingGroup> generalPostingGroups) {
        List<DBAccess> dbAccesses = new ArrayList<>();
        generalPostingGroups.forEach(s -> dbAccesses.add(new GeneralPostingGroupDA(s)));
        return dbAccesses;
    }

    public int getMax(String columnName) {
        return super.getMax(GeneralPostingGroup.class, columnName);
    }

    public int getMax(String columnName, String comparatorColumn, Object comparatorVaue) {
        return super.getMax(GeneralPostingGroup.class, columnName, comparatorColumn, comparatorVaue);
    }

    public final int getNextIdHelper() {
        return this.getMax("idHelper") + 1;
    }

    public String getNextGeneralPostingID(int idHelper) {
        return new IDGeneratorDA().getToAppendString(GeneralPostingGroup.class.getSimpleName(), idHelper);
    }

    public List<GeneralPostingGroup> getGeneralPostingGroups(String columName, Object value) {
        return super.find(GeneralPostingGroup.class, columName, value);
    }

    @Override
    public List<DBAccess> getRevisions() {
        try {

            List<Object[]> list = getEntityRevisions(GeneralPostingGroup.class);
            List<DBAccess> dBAccesses = new ArrayList<>();
            list.forEach(e -> {

                GeneralPostingGroupDA generalPostingGroupDA = new GeneralPostingGroupDA((GeneralPostingGroup) e[0]);
                generalPostingGroupDA.revisionEntity = (AppRevisionEntity) e[1];
                generalPostingGroupDA.oRevisionType = (RevisionType) e[2];
                generalPostingGroupDA.initRevProprties();
                generalPostingGroupDA.searchColumns.addAll(generalPostingGroupDA.getRevSearchColumns());
                dBAccesses.add(generalPostingGroupDA);

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

    public List<GeneralPostingGroup> getGeneralPostingGroups(LookupData businessGroup, LookupData itemGroup) {
        return super.find(GeneralPostingGroup.class, "businessGroup", businessGroup, "itemGroup", itemGroup);
    }

    public GeneralPostingGroup getGeneralPostingGroup(LookupData businessGroup, LookupData itemGroup) throws Exception {
        List<GeneralPostingGroup> generalPostingGroups = getGeneralPostingGroups(businessGroup, itemGroup);
        GeneralPostingGroup genPostingGroup = generalPostingGroups.isEmpty() ? null : generalPostingGroups.get(0);

        if (genPostingGroup == null) {
            throw new Exception("No posting group found for the"
                    + " business group: " + businessGroup.getLookupDataName() + " and item group: " + itemGroup.getLookupDataName());
        }

        if (genPostingGroup.getSalesAccount() == null) {
            throw new Exception("No Sales set for the"
                    + " business group " + businessGroup.getLookupDataName() + " and item group " + itemGroup.getLookupDataName());
        }

        if (genPostingGroup.getSalesDiscountAccount() == null) {
            throw new Exception("No Sales Discount set for the"
                    + " business group " + businessGroup.getLookupDataName() + " and item group " + itemGroup.getLookupDataName());
        }
        return genPostingGroup;
    }

    public ChartAccount getSaleDiscountAccount(LookupData businessGroup, LookupData itemGroup) throws Exception {
        GeneralPostingGroup genPostingGroup = getGeneralPostingGroup(businessGroup, itemGroup);
        if (genPostingGroup == null) {
            throw new Exception("No posting group found for the"
                    + " business group: " + businessGroup.getLookupDataName() + " and item group: " + itemGroup.getLookupDataName());
        }
        ChartAccount saleDiscAcc = genPostingGroup.getSalesDiscountAccount();
        if (saleDiscAcc == null) {
            throw new Exception("No Sales Discount set for the"
                    + " business group " + businessGroup.getLookupDataName() + " and item group " + itemGroup.getLookupDataName());
        }
        return saleDiscAcc;
    }

    public boolean isValid() throws Exception {

        List<GeneralPostingGroup> generalPostingGroups = getGeneralPostingGroups(this.businessGroup, this.itemGroup);
        generalPostingGroups.remove(this.generalPostingGroup);

        if (generalPostingGroups.size() > 0) {
            throw new Exception("There is already an exisiting posting group for the"
                    + " business group: " + businessGroup.getLookupDataName() + " and item group: " + itemGroup.getLookupDataName());
        }
        return true;
    }

}
