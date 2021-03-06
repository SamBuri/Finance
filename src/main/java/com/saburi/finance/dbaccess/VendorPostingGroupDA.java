/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.dbaccess;

import com.saburi.common.dbaccess.DBAccess;
import com.saburi.common.dbaccess.IDGeneratorDA;
import com.saburi.common.dbaccess.LookupDataDA;
import com.saburi.finance.entities.VendorPostingGroup;
import javafx.beans.property.*;
import java.util.ArrayList;
import com.saburi.common.entities.AppRevisionEntity;
import java.util.List;
import com.saburi.common.utils.SearchColumn;
import com.saburi.common.utils.SearchColumn.SearchDataTypes;
import org.hibernate.envers.RevisionType;
import com.saburi.common.entities.LookupData;
import com.saburi.finance.entities.ChartAccount;

public class VendorPostingGroupDA extends DBAccess {

    private VendorPostingGroup vendorPostingGroup = new VendorPostingGroup();
    private final SimpleIntegerProperty idHelper = new SimpleIntegerProperty(this, "idHelper");
    private final SimpleStringProperty vendorPostingGroupID = new SimpleStringProperty(this, "vendorPostingGroupID");
    private final SimpleStringProperty vendorGroupDisplay = new SimpleStringProperty(this, "vendorGroupDisplay");
    private final SimpleObjectProperty vendorGroupID = new SimpleObjectProperty(this, "vendorGroupID");
    private LookupData vendorGroup;
    private final SimpleStringProperty payableAccountDisplay = new SimpleStringProperty(this, "payableAccountDisplay");
    private final SimpleObjectProperty payableAccountID = new SimpleObjectProperty(this, "payableAccountID");
    private ChartAccount payableAccount;
    private final SimpleStringProperty serviceChargeAccountDisplay = new SimpleStringProperty(this, "serviceChargeAccountDisplay");
    private final SimpleObjectProperty serviceChargeAccountID = new SimpleObjectProperty(this, "serviceChargeAccountID");
    private ChartAccount serviceChargeAccount;
    private final SimpleStringProperty paymentDiscountAccountDisplay = new SimpleStringProperty(this, "paymentDiscountAccountDisplay");
    private final SimpleObjectProperty paymentDiscountAccountID = new SimpleObjectProperty(this, "paymentDiscountAccountID");
    private ChartAccount paymentDiscountAccount;

    public VendorPostingGroupDA() {
        createSearchColumns();
    }

    public VendorPostingGroupDA(String persistenceUnit) {
        super(persistenceUnit);
    }

    public VendorPostingGroupDA(VendorPostingGroup vendorPostingGroup) {
        this.vendorPostingGroup = vendorPostingGroup;
        initialseProprties();
        createSearchColumns();
    }

    public VendorPostingGroupDA(String persistenceUnit, VendorPostingGroup vendorPostingGroup) {
        super(persistenceUnit);
        this.vendorPostingGroup = vendorPostingGroup;
        initialseProprties();
        createSearchColumns();
    }

    public VendorPostingGroupDA(String vendorPostingGroupID, LookupData vendorGroup, ChartAccount payableAccount, ChartAccount serviceChargeAccount, ChartAccount paymentDiscountAccount) {
        this.vendorPostingGroup = new VendorPostingGroup(getNextIdHelper(), vendorPostingGroupID, vendorGroup, payableAccount, serviceChargeAccount, paymentDiscountAccount);
        initialseProprties();
        createSearchColumns();
    }

    public VendorPostingGroupDA(String persistenceUnit, String vendorPostingGroupID, LookupData vendorGroup, ChartAccount payableAccount, ChartAccount serviceChargeAccount, ChartAccount paymentDiscountAccount) {
        super(persistenceUnit);
        this.vendorPostingGroup = new VendorPostingGroup(getNextIdHelper(), vendorPostingGroupID, vendorGroup, payableAccount, serviceChargeAccount, paymentDiscountAccount);
        initialseProprties();
        createSearchColumns();
    }

    public int getIdHelper() {
        return idHelper.get();
    }

    public void setIdHelper(int idHelper) {
        vendorPostingGroup.setIdHelper(idHelper);
        this.idHelper.set(idHelper);
    }

    public String getVendorPostingGroupID() {
        return vendorPostingGroupID.get();
    }

    public void setVendorPostingGroupID(String vendorPostingGroupID) {
        vendorPostingGroup.setVendorPostingGroupID(vendorPostingGroupID);
        this.vendorPostingGroupID.set(vendorPostingGroupID);
    }

    public LookupData getVendorGroup() {
        return vendorGroup;
    }

    public Object getVendorGroupID() {
        return vendorGroupID.get();
    }

    public String getVendorGroupDisplay() {
        return vendorGroupDisplay.get();
    }

    public LookupDataDA getVendorGroupDA() {
        return this.vendorGroup != null ? new LookupDataDA(this.vendorGroup) : null;
    }

    public void setVendorGroup(LookupData vendorGroup) {
        vendorPostingGroup.setVendorGroup(vendorGroup);
        this.vendorGroup = vendorGroup;
        this.vendorGroupID.set(vendorGroup.getId());
        this.vendorGroupDisplay.set(vendorGroup.getDisplayKey());
    }

    public ChartAccount getPayableAccount() {
        return payableAccount;
    }

    public Object getPayableAccountID() {
        return payableAccountID.get();
    }

    public String getPayableAccountDisplay() {
        return payableAccountDisplay.get();
    }

    public ChartAccountDA getPayableAccountDA() {
        return this.payableAccount != null ? new ChartAccountDA(this.payableAccount) : null;
    }

    public void setPayableAccount(ChartAccount payableAccount) {
        vendorPostingGroup.setPayableAccount(payableAccount);
        this.payableAccount = payableAccount;
        this.payableAccountID.set(payableAccount.getId());
        this.payableAccountDisplay.set(payableAccount.getDisplayKey());
    }

    public ChartAccount getServiceChargeAccount() {
        return serviceChargeAccount;
    }

    public Object getServiceChargeAccountID() {
        return serviceChargeAccountID.get();
    }

    public String getServiceChargeAccountDisplay() {
        return serviceChargeAccountDisplay.get();
    }

    public ChartAccountDA getServiceChargeAccountDA() {
        return this.serviceChargeAccount != null ? new ChartAccountDA(this.serviceChargeAccount) : null;
    }

    public void setServiceChargeAccount(ChartAccount serviceChargeAccount) {
        vendorPostingGroup.setServiceChargeAccount(serviceChargeAccount);
        this.serviceChargeAccount = serviceChargeAccount;
        this.serviceChargeAccountID.set(serviceChargeAccount.getId());
        this.serviceChargeAccountDisplay.set(serviceChargeAccount.getDisplayKey());
    }

    public ChartAccount getPaymentDiscountAccount() {
        return paymentDiscountAccount;
    }

    public Object getPaymentDiscountAccountID() {
        return paymentDiscountAccountID.get();
    }

    public String getPaymentDiscountAccountDisplay() {
        return paymentDiscountAccountDisplay.get();
    }

    public ChartAccountDA getPaymentDiscountAccountDA() {
        return this.paymentDiscountAccount != null ? new ChartAccountDA(this.paymentDiscountAccount) : null;
    }

    public void setPaymentDiscountAccount(ChartAccount paymentDiscountAccount) {
        vendorPostingGroup.setPaymentDiscountAccount(paymentDiscountAccount);
        this.paymentDiscountAccount = paymentDiscountAccount;
        this.paymentDiscountAccountID.set(paymentDiscountAccount.getId());
        this.paymentDiscountAccountDisplay.set(paymentDiscountAccount.getDisplayKey());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendorPostingGroupDA)) {
            return false;
        }

        VendorPostingGroupDA vendorPostingGroupDA = (VendorPostingGroupDA) o;

        if (vendorPostingGroupDA.getDBEntity() == null || this.getDBEntity() == null) {
            return false;
        }
        return this.getId().equals(vendorPostingGroupDA.getId());
    }

    @Override
    public int hashCode() {
        return vendorPostingGroup.getId().hashCode();
    }

    private void initialseProprties() {
        this.dBEntity = vendorPostingGroup;
        this.idHelper.set(vendorPostingGroup.getIdHelper());
        this.vendorPostingGroupID.set(vendorPostingGroup.getVendorPostingGroupID());
        this.vendorGroup = vendorPostingGroup.getVendorGroup();
        if (this.vendorGroup != null) {
            this.vendorGroupID.set(vendorGroup.getId());
            this.vendorGroupDisplay.set(vendorGroup.getDisplayKey());
        }
        this.payableAccount = vendorPostingGroup.getPayableAccount();
        if (this.payableAccount != null) {
            this.payableAccountID.set(payableAccount.getId());
            this.payableAccountDisplay.set(payableAccount.getDisplayKey());
        }
        this.serviceChargeAccount = vendorPostingGroup.getServiceChargeAccount();
        if (this.serviceChargeAccount != null) {
            this.serviceChargeAccountID.set(serviceChargeAccount.getId());
            this.serviceChargeAccountDisplay.set(serviceChargeAccount.getDisplayKey());
        }
        this.paymentDiscountAccount = vendorPostingGroup.getPaymentDiscountAccount();
        if (this.paymentDiscountAccount != null) {
            this.paymentDiscountAccountID.set(paymentDiscountAccount.getId());
            this.paymentDiscountAccountDisplay.set(paymentDiscountAccount.getDisplayKey());
        }
        initCommonProprties();
    }

    private void createSearchColumns() {
        this.searchColumns.add(new SearchColumn("vendorPostingGroupID", "Vendor Posting GroupID", this.vendorPostingGroupID.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("vendorGroupID", "Vendor Group ID", this.vendorGroupID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("vendorGroupDisplay", "Vendor Group", this.vendorGroupDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("payableAccountID", "Payable Account ID", this.payableAccountID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("payableAccountDisplay", "Payable Account", this.payableAccountDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("serviceChargeAccountID", "Service Charge Account ID", this.serviceChargeAccountID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("serviceChargeAccountDisplay", "Service Charge Account", this.serviceChargeAccountDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("paymentDiscountAccountID", "Payment Discount Account ID", this.paymentDiscountAccountID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("paymentDiscountAccountDisplay", "Payment Discount Account", this.paymentDiscountAccountDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.addAll(this.getDefaultSearchColumns());
    }

    @Override
    public List<SearchColumn> getSearchColumns() {
        return this.searchColumns;
    }

    @Override
    public Object getId() {
        return this.vendorPostingGroup.getId();
    }

    @Override
    public String getDisplayKey() {
        return this.vendorPostingGroup.getDisplayKey();
    }

    public static List<VendorPostingGroupDA> getVendorPostingGroupDAs(List<VendorPostingGroup> vendorPostingGroups) {
        List<VendorPostingGroupDA> list = new ArrayList<>();
        vendorPostingGroups.forEach((vendorPostingGroup) -> {
            list.add(new VendorPostingGroupDA(vendorPostingGroup));
        });
        return list;
    }

    public static List<VendorPostingGroup> getVendorPostingGroupList(List<VendorPostingGroupDA> vendorPostingGroupDAs) {
        List<VendorPostingGroup> vendorPostingGroups = new ArrayList<>();
        vendorPostingGroupDAs.forEach(a -> vendorPostingGroups.add(a.vendorPostingGroup));
        return vendorPostingGroups;
    }

    public boolean save() throws Exception {
        if (!isValid()) {
            return false;
        }
        return super.persist(this.vendorPostingGroup);

    }

    public boolean update() throws Exception {
        if (!isValid()) {
            return false;
        }
        return super.merge(this.vendorPostingGroup);

    }

    public boolean delete() {
        return super.remove(this.vendorPostingGroup);

    }

    public VendorPostingGroup getVendorPostingGroup(String vendorPostingGroupID) {
        return (VendorPostingGroup) super.find(VendorPostingGroup.class, vendorPostingGroupID);
    }

    public VendorPostingGroup getVendorPostingGroup() {
        return this.vendorPostingGroup;
    }

    public List<VendorPostingGroup> getVendorPostingGroups() {
        return super.find(VendorPostingGroup.class);
    }

    @Override
    public List<DBAccess> get() {
        List<DBAccess> list = new ArrayList<>();
        selectQuery(VendorPostingGroup.class).forEach(o -> list.add(new VendorPostingGroupDA((VendorPostingGroup) o)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public VendorPostingGroupDA get(String vendorPostingGroupID) throws Exception {
        VendorPostingGroup oVendorPostingGroup = getVendorPostingGroup(vendorPostingGroupID);
        if (oVendorPostingGroup == null) {
            throw new Exception("No Record with id: " + vendorPostingGroupID);
        }
        return new VendorPostingGroupDA(oVendorPostingGroup);
    }

    public List<VendorPostingGroupDA> get(String columName, Object value) {
        List<VendorPostingGroupDA> list = new ArrayList<>();
        super.selectQuery(VendorPostingGroup.class, columName, value).forEach(da -> list.add(new VendorPostingGroupDA((VendorPostingGroup) da)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public List<VendorPostingGroupDA> toDaList(List<VendorPostingGroup> vendorPostingGroups) {
        List<VendorPostingGroupDA> vendorPostingGroupDAs = new ArrayList<>();
        vendorPostingGroups.forEach(s -> vendorPostingGroupDAs.add(new VendorPostingGroupDA(s)));
        return vendorPostingGroupDAs;
    }

    public List<DBAccess> toDBAccessList(List<VendorPostingGroup> vendorPostingGroups) {
        List<DBAccess> dbAccesses = new ArrayList<>();
        vendorPostingGroups.forEach(s -> dbAccesses.add(new VendorPostingGroupDA(s)));
        return dbAccesses;
    }

    public int getMax(String columnName) {
        return super.getMax(VendorPostingGroup.class, columnName);
    }

    public int getMax(String columnName, String comparatorColumn, Object comparatorVaue) {
        return super.getMax(VendorPostingGroup.class, columnName, comparatorColumn, comparatorVaue);
    }

    public final int getNextIdHelper() {
        return this.getMax("idHelper") + 1;
    }

    public String getNextVendorPostingGroupID(int idHelper) {
        return new IDGeneratorDA().getToAppendString(VendorPostingGroup.class.getSimpleName(), idHelper);
    }

    public List<VendorPostingGroup> getVendorPostingGroups(String columName, Object value) {
        return super.find(VendorPostingGroup.class, columName, value);
    }

    @Override
    public List<DBAccess> getRevisions() {
        try {

            List<Object[]> list = getEntityRevisions(VendorPostingGroup.class);
            List<DBAccess> dBAccesses = new ArrayList<>();
            list.forEach(e -> {

                VendorPostingGroupDA vendorPostingGroupDA = new VendorPostingGroupDA((VendorPostingGroup) e[0]);
                vendorPostingGroupDA.revisionEntity = (AppRevisionEntity) e[1];
                vendorPostingGroupDA.oRevisionType = (RevisionType) e[2];
                vendorPostingGroupDA.initRevProprties();
                vendorPostingGroupDA.searchColumns.addAll(vendorPostingGroupDA.getRevSearchColumns());
                dBAccesses.add(vendorPostingGroupDA);

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
        List<VendorPostingGroup> vendorPostingGroups = getVendorPostingGroups("vendorGroup", this.vendorGroup);
        vendorPostingGroups.remove(this.vendorPostingGroup);
        if (!vendorPostingGroups.isEmpty()) {
            throw new Exception("There is an existing vendor posting group for vendor group: " + vendorGroup.getLookupDataName()
                    + " you can't have another one");
        }
        return true;
    }

}
