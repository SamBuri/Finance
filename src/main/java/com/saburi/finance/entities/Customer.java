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
import javax.persistence.Column;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.OneToOne;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Customer extends DBEntity {

    @Column(updatable = false)
    private int idHelper;
    @Id
    @NotNull(message = "The field: Customer ID cannot be null")
    @Size(max = 20, message = "The field: Customer ID size cannot be greater than 20")
    @Column(length = 20, updatable = false)
    private String customerID;
    @Size(max = 100, message = "The field: Customer Name size cannot be greater than 100")
    @NotNull(message = "The field: Customer Name cannot be null")
    @Column(length = 100)
    private String customerName;
    @OneToOne
    @JoinColumn(name = "customerCategoryID", foreignKey = @ForeignKey(name = "fkCustomerCategoryIDCustomer"))
    private LookupData customerCategory;
    @Size(max = 100, message = "The field: PhoneNo size cannot be greater than 100")
    @Column(length = 100)
    private String phoneNo;
    @Size(max = 100, message = "The field: Email size cannot be greater than 100")
    @Column(length = 100)
    private String email;
    @Size(max = 200, message = "The field: Address size cannot be greater than 200")
    @Column(length = 200)
    private String address;
    @OneToOne
    @JoinColumn(name = "priceGroupID", foreignKey = @ForeignKey(name = "fkPriceGroupIDCustomer"))
    private LookupData priceGroup;
    @OneToOne
    @JoinColumn(name = "billToCustomerID", foreignKey = @ForeignKey(name = "fkBillToCustomerIDCustomer"))
    private Customer billToCustomer;
    @OneToOne
    @JoinColumn(name = "customerPostingGroupID", foreignKey = @ForeignKey(name = "fkCustomerPostingGroupIDCustomer"))
    private CustomerPostingGroup customerPostingGroup;
    @OneToOne
    @JoinColumn(name = "businessGroupID", foreignKey = @ForeignKey(name = "fkBusinessGroupIDCustomer"))
    private LookupData businessGroup;
    @OneToOne
    @JoinColumn(name = "vATBusinessGroupID", foreignKey = @ForeignKey(name = "fkVATBusinessGroupIDCustomer"))
    private LookupData vATBusinessGroup;
    @Size(max = 20, message = "The field: Reference No size cannot be greater than 20")
    @Column(length = 20)
    private String referenceNo;
    private double balance;

    public Customer() {
    }
    
    public Customer(int idHelper, String customerID, String customerName, LookupData customerCategory, String phoneNo, String email, String address, LookupData priceGroup, Customer billToCustomer, CustomerPostingGroup customerPostingGroup, LookupData businessGroup, LookupData vATBusinessGroup, String referenceNo) {
        this.idHelper = idHelper;
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerCategory = customerCategory;
        this.phoneNo = phoneNo;
        this.email = email;
        this.address = address;
        this.priceGroup = priceGroup;
        this.billToCustomer = billToCustomer;
        this.customerPostingGroup = customerPostingGroup;
        this.businessGroup = businessGroup;
        this.vATBusinessGroup = vATBusinessGroup;
        this.referenceNo = referenceNo;

    }

    public Customer(int idHelper, String customerID, String customerName, LookupData customerCategory, String phoneNo, String email, String address, LookupData priceGroup, Customer billToCustomer, CustomerPostingGroup customerPostingGroup, LookupData businessGroup, LookupData vATBusinessGroup, String referenceNo, double balance) {
        this.idHelper = idHelper;
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerCategory = customerCategory;
        this.phoneNo = phoneNo;
        this.email = email;
        this.address = address;
        this.priceGroup = priceGroup;
        this.billToCustomer = billToCustomer;
        this.customerPostingGroup = customerPostingGroup;
        this.businessGroup = businessGroup;
        this.vATBusinessGroup = vATBusinessGroup;
        this.referenceNo = referenceNo;
        this.balance = balance;

    }
    
    

    public int getIdHelper() {
        return idHelper;
    }

    public void setIdHelper(int idHelper) {
        this.idHelper = idHelper;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LookupData getCustomerCategory() {
        return customerCategory;
    }

    public void setCustomerCategory(LookupData customerCategory) {
        this.customerCategory = customerCategory;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LookupData getPriceGroup() {
        return priceGroup;
    }

    public void setPriceGroup(LookupData priceGroup) {
        this.priceGroup = priceGroup;
    }

    public Customer getBillToCustomer() {
        return billToCustomer;
    }

    public void setBillToCustomer(Customer billToCustomer) {
        this.billToCustomer = billToCustomer;
    }

    public CustomerPostingGroup getCustomerPostingGroup() {
        return customerPostingGroup;
    }

    public void setCustomerPostingGroup(CustomerPostingGroup customerPostingGroup) {
        this.customerPostingGroup = customerPostingGroup;
    }

    public LookupData getBusinessGroup() {
        return businessGroup;
    }

    public void setBusinessGroup(LookupData businessGroup) {
        this.businessGroup = businessGroup;
    }

    public LookupData getVATBusinessGroup() {
        return vATBusinessGroup;
    }

    public void setVATBusinessGroup(LookupData vATBusinessGroup) {
        this.vATBusinessGroup = vATBusinessGroup;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }

        Customer customer = (Customer) o;

        return this.getId().equals(customer.getId());
    }

    @Override
    public int hashCode() {
        return this.customerID.hashCode();

    }

    @Override
    public Object getId() {
        return this.customerID;
    }

    @Override
    public String getDisplayKey() {
        return this.customerName;
    }

}
