/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.dbaccess;

import com.saburi.common.dbaccess.DBAccess;
import com.saburi.common.dbaccess.IDGeneratorDA;
import com.saburi.finance.entities.BankAccount;
import javafx.beans.property.*;
import java.util.ArrayList;
import com.saburi.common.entities.AppRevisionEntity;
import java.util.List;
import com.saburi.common.utils.SearchColumn;
import com.saburi.common.utils.SearchColumn.SearchDataTypes;
import org.hibernate.envers.RevisionType;
import com.saburi.finance.utils.FinanceEnums.BankAccountTypes;
import com.saburi.finance.entities.Currency;
import com.saburi.finance.entities.ChartAccount;
import static com.saburi.common.utils.Utilities.formatNumber;
import com.saburi.finance.utils.FinanceEnums;
import com.saburi.finance.utils.FinanceEnums.PayModes;

public class BankAccountDA extends DBAccess {

    private BankAccount bankAccount = new BankAccount();
    private final SimpleIntegerProperty idHelper = new SimpleIntegerProperty(this, "idHelper");
    private final SimpleStringProperty bankAccountID = new SimpleStringProperty(this, "bankAccountID");
    private final SimpleStringProperty bankAccountName = new SimpleStringProperty(this, "bankAccountName");
    private final SimpleObjectProperty bankAccountType = new SimpleObjectProperty(this, "bankAccountType");
    private final SimpleStringProperty currencyDisplay = new SimpleStringProperty(this, "currencyDisplay");
    private final SimpleObjectProperty currencyID = new SimpleObjectProperty(this, "currencyID");
    private Currency currency;
    private final SimpleStringProperty accountNo = new SimpleStringProperty(this, "accountNo");
    private final SimpleStringProperty bankName = new SimpleStringProperty(this, "bankName");
    private final SimpleStringProperty branchName = new SimpleStringProperty(this, "branchName");
    private final SimpleStringProperty postingAccountDisplay = new SimpleStringProperty(this, "postingAccountDisplay");
    private final SimpleObjectProperty postingAccountID = new SimpleObjectProperty(this, "postingAccountID");
    private ChartAccount postingAccount;
    private final SimpleStringProperty phoneNo = new SimpleStringProperty(this, "phoneNo");
    private final SimpleStringProperty email = new SimpleStringProperty(this, "email");
    private final SimpleStringProperty address = new SimpleStringProperty(this, "address");
    private final SimpleBooleanProperty isDefault = new SimpleBooleanProperty(this, "isDefault");
    private final SimpleDoubleProperty balance = new SimpleDoubleProperty(this, "balance");
    private final SimpleStringProperty balanceDisplay = new SimpleStringProperty(this, "balanceDisplay");

    public BankAccountDA() {
        createSearchColumns();
    }

    public BankAccountDA(String persistenceUnit) {
        super(persistenceUnit);
    }

    public BankAccountDA(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        initialseProprties();
        createSearchColumns();
    }

    public BankAccountDA(String persistenceUnit, BankAccount bankAccount) {
        super(persistenceUnit);
        this.bankAccount = bankAccount;
        initialseProprties();
        createSearchColumns();
    }

    public BankAccountDA(String bankAccountID, String bankAccountName, BankAccountTypes bankAccountType, Currency currency, String accountNo, String bankName, String branchName, ChartAccount postingAccount, String phoneNo, String email, String address, boolean isDefault) {
        this.bankAccount = new BankAccount(getNextIdHelper(), bankAccountID, bankAccountName, bankAccountType, currency, accountNo, bankName, branchName, postingAccount, phoneNo, email, address, isDefault);
        initialseProprties();
        createSearchColumns();
    }

    public BankAccountDA(String persistenceUnit, String bankAccountID, String bankAccountName, BankAccountTypes bankAccountType, Currency currency, String accountNo, String bankName, String branchName, ChartAccount postingAccount, String phoneNo, String email, String address, boolean isDefault) {
        super(persistenceUnit);
        this.bankAccount = new BankAccount(getNextIdHelper(), bankAccountID, bankAccountName, bankAccountType, currency, accountNo, bankName, branchName, postingAccount, phoneNo, email, address, isDefault);
        initialseProprties();
        createSearchColumns();
    }

    public int getIdHelper() {
        return idHelper.get();
    }

    public void setIdHelper(int idHelper) {
        bankAccount.setIdHelper(idHelper);
        this.idHelper.set(idHelper);
    }

    public String getBankAccountID() {
        return bankAccountID.get();
    }

    public void setBankAccountID(String bankAccountID) {
        bankAccount.setBankAccountID(bankAccountID);
        this.bankAccountID.set(bankAccountID);
    }

    public String getBankAccountName() {
        return bankAccountName.get();
    }

    public void setBankAccountName(String bankAccountName) {
        bankAccount.setBankAccountName(bankAccountName);
        this.bankAccountName.set(bankAccountName);
    }

    public Object getBankAccountType() {
        return bankAccountType.get();
    }

    public void setBankAccountType(BankAccountTypes bankAccountType) {
        bankAccount.setBankAccountType(bankAccountType);
        this.bankAccountType.set(bankAccountType);
    }

    public Currency getCurrency() {
        return currency;
    }

    public Object getCurrencyID() {
        return currencyID.get();
    }

    public String getCurrencyDisplay() {
        return currencyDisplay.get();
    }

    public CurrencyDA getCurrencyDA() {
        return this.currency != null ? new CurrencyDA(this.currency) : null;
    }

    public void setCurrency(Currency currency) {
        bankAccount.setCurrency(currency);
        this.currency = currency;
        this.currencyID.set(currency.getId());
        this.currencyDisplay.set(currency.getDisplayKey());
    }

    public String getAccountNo() {
        return accountNo.get();
    }

    public void setAccountNo(String accountNo) {
        bankAccount.setAccountNo(accountNo);
        this.accountNo.set(accountNo);
    }

    public String getBankName() {
        return bankName.get();
    }

    public void setBankName(String bankName) {
        bankAccount.setBankName(bankName);
        this.bankName.set(bankName);
    }

    public String getBranchName() {
        return branchName.get();
    }

    public void setBranchName(String branchName) {
        bankAccount.setBranchName(branchName);
        this.branchName.set(branchName);
    }

    public ChartAccount getPostingAccount() {
        return postingAccount;
    }

    public Object getPostingAccountID() {
        return postingAccountID.get();
    }

    public String getPostingAccountDisplay() {
        return postingAccountDisplay.get();
    }

    public ChartAccountDA getPostingAccountDA() {
        return this.postingAccount != null ? new ChartAccountDA(this.postingAccount) : null;
    }

    public void setPostingAccount(ChartAccount postingAccount) {
        bankAccount.setPostingAccount(postingAccount);
        this.postingAccount = postingAccount;
        this.postingAccountID.set(postingAccount.getId());
        this.postingAccountDisplay.set(postingAccount.getDisplayKey());
    }

    public String getPhoneNo() {
        return phoneNo.get();
    }

    public void setPhoneNo(String phoneNo) {
        bankAccount.setPhoneNo(phoneNo);
        this.phoneNo.set(phoneNo);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        bankAccount.setEmail(email);
        this.email.set(email);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        bankAccount.setAddress(address);
        this.address.set(address);
    }

    public boolean isisDefault() {
        return isDefault.get();
    }

    public void setisDefault(boolean isDefault) {
        bankAccount.setisDefault(isDefault);
        this.isDefault.set(isDefault);
    }

    public double getBalance() {
        return balance.get();
    }

    public String getBalanceDisplay() {
        return balanceDisplay.get();
    }

    public void setBalance(double balance) {
        bankAccount.setBalance(balance);
        this.balance.set(balance);
        this.balanceDisplay.set(formatNumber(balance));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankAccountDA)) {
            return false;
        }

        BankAccountDA bankAccountDA = (BankAccountDA) o;

        if (bankAccountDA.getDBEntity() == null || this.getDBEntity() == null) {
            return false;
        }
        return this.getId().equals(bankAccountDA.getId());
    }

    @Override
    public int hashCode() {
        return bankAccount.getId().hashCode();
    }

    private void initialseProprties() {
        this.dBEntity = bankAccount;
        this.idHelper.set(bankAccount.getIdHelper());
        this.bankAccountID.set(bankAccount.getBankAccountID());
        this.bankAccountName.set(bankAccount.getBankAccountName());
        this.bankAccountType.set(bankAccount.getBankAccountType());
        this.currency = bankAccount.getCurrency();
        if (this.currency != null) {
            this.currencyID.set(currency.getId());
            this.currencyDisplay.set(currency.getDisplayKey());
        }
        this.accountNo.set(bankAccount.getAccountNo());
        this.bankName.set(bankAccount.getBankName());
        this.branchName.set(bankAccount.getBranchName());
        this.postingAccount = bankAccount.getPostingAccount();
        if (this.postingAccount != null) {
            this.postingAccountID.set(postingAccount.getId());
            this.postingAccountDisplay.set(postingAccount.getDisplayKey());
        }
        this.phoneNo.set(bankAccount.getPhoneNo());
        this.email.set(bankAccount.getEmail());
        this.address.set(bankAccount.getAddress());
        this.isDefault.set(bankAccount.isisDefault());
        this.balance.set(bankAccount.getBalance());
        this.balanceDisplay.set(formatNumber(bankAccount.getBalance()));
        initCommonProprties();
    }

    private void createSearchColumns() {
        this.searchColumns.add(new SearchColumn("bankAccountID", "Bank Account ID", this.bankAccountID.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("bankAccountName", "Bank Account Name", this.bankAccountName.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("bankAccountType", "Bank Account Type", this.bankAccountType.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal));
        this.searchColumns.add(new SearchColumn("currencyID", "Currency ID", this.currencyID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("currencyDisplay", "Currency", this.currencyDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("accountNo", "Account No", this.accountNo.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("bankName", "Bank Name", this.bankName.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("branchName", "Branch Name", this.branchName.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("postingAccountID", "Posting Account ID", this.postingAccountID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("postingAccountDisplay", "Posting Account", this.postingAccountDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("phoneNo", "Phone No", this.phoneNo.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("email", "Email", this.email.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("address", "Address", this.address.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("isDefault", "Is Default", this.isDefault.get(), SearchDataTypes.BOOLEAN));
        this.searchColumns.add(new SearchColumn("balance", "Balance", this.balance.get(), balanceDisplay.get(), SearchDataTypes.NUMBER));
        this.searchColumns.addAll(this.getDefaultSearchColumns());
    }

    @Override
    public List<SearchColumn> getSearchColumns() {
        return this.searchColumns;
    }

    @Override
    public Object getId() {
        return this.bankAccount.getId();
    }

    @Override
    public String getDisplayKey() {
        return this.bankAccount.getDisplayKey();
    }

    public static List<BankAccountDA> getBankAccountDAs(List<BankAccount> bankAccounts) {
        List<BankAccountDA> list = new ArrayList<>();
        bankAccounts.forEach((bankAccount) -> {
            list.add(new BankAccountDA(bankAccount));
        });
        return list;
    }

    public static List<BankAccount> getBankAccountList(List<BankAccountDA> bankAccountDAs) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccountDAs.forEach(a -> bankAccounts.add(a.bankAccount));
        return bankAccounts;
    }

    public boolean save() throws Exception {
        if (!isValid()) {
            return false;
        }
        return super.persist(this.bankAccount);

    }

    public boolean update() throws Exception {
        if (!isValid()) {
            return false;
        }
        return super.merge(this.bankAccount);

    }

    public boolean delete() {
        return super.remove(this.bankAccount);

    }

    public BankAccount getBankAccount(String bankAccountID) {
        return (BankAccount) super.find(BankAccount.class, bankAccountID);
    }

    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public List<BankAccount> getBankAccounts() {
        return super.find(BankAccount.class);
    }

    @Override
    public List<DBAccess> get() {
        List<DBAccess> list = new ArrayList<>();
        selectQuery(BankAccount.class).forEach(o -> list.add(new BankAccountDA((BankAccount) o)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public BankAccountDA get(String bankAccountID) throws Exception {
        BankAccount oBankAccount = getBankAccount(bankAccountID);
        if (oBankAccount == null) {
            throw new Exception("No Record with id: " + bankAccountID);
        }
        return new BankAccountDA(oBankAccount);
    }

    public List<BankAccountDA> get(String columName, Object value) {
        List<BankAccountDA> list = new ArrayList<>();
        super.selectQuery(BankAccount.class, columName, value).forEach(da -> list.add(new BankAccountDA((BankAccount) da)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public List<BankAccountDA> toDaList(List<BankAccount> bankAccounts) {
        List<BankAccountDA> bankAccountDAs = new ArrayList<>();
        bankAccounts.forEach(s -> bankAccountDAs.add(new BankAccountDA(s)));
        return bankAccountDAs;
    }

    public List<DBAccess> toDBAccessList(List<BankAccount> bankAccounts) {
        List<DBAccess> dbAccesses = new ArrayList<>();
        bankAccounts.forEach(s -> dbAccesses.add(new BankAccountDA(s)));
        return dbAccesses;
    }

    public int getMax(String columnName) {
        return super.getMax(BankAccount.class, columnName);
    }

    public int getMax(String columnName, String comparatorColumn, Object comparatorVaue) {
        return super.getMax(BankAccount.class, columnName, comparatorColumn, comparatorVaue);
    }

    public final int getNextIdHelper() {
        return this.getMax("idHelper") + 1;
    }

    public String getNextBankAccountID(int idHelper) {
        return new IDGeneratorDA().getToAppendString(BankAccount.class.getSimpleName(), idHelper);
    }

    public List<BankAccount> getBankAccounts(String columName, Object value) {
        return super.find(BankAccount.class, columName, value);
    }

    @Override
    public List<DBAccess> getRevisions() {
        try {

            List<Object[]> list = getEntityRevisions(BankAccount.class);
            List<DBAccess> dBAccesses = new ArrayList<>();
            list.forEach(e -> {

                BankAccountDA bankAccountDA = new BankAccountDA((BankAccount) e[0]);
                bankAccountDA.revisionEntity = (AppRevisionEntity) e[1];
                bankAccountDA.oRevisionType = (RevisionType) e[2];
                bankAccountDA.initRevProprties();
                bankAccountDA.searchColumns.addAll(bankAccountDA.getRevSearchColumns());
                dBAccesses.add(bankAccountDA);

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

    private boolean isValid() throws Exception {
        List<BankAccount> bankAccounts = super.find(BankAccount.class, "bankAccountType", this.bankAccount.getBankAccountType(), "isDefault", true);
        bankAccounts.remove(bankAccount);
        if (bankAccounts.size() > 0) {
            throw new Exception("There is already an exisiting default Bank Account for account type: " + bankAccount.getBankAccountType().name() + ""
                    + "\n You can't have more than one");
        }
        return true;
    }

    public List<BankAccount> getBankAccounts(BankAccountTypes bankAccountType) {
        return this.getBankAccounts("bankAccountType", bankAccountType);
    }

    public List<BankAccountDA> getBankAccountDAs(BankAccountTypes bankAccountType) {
        return toDaList(getBankAccounts(bankAccountType));
    }

    public static BankAccountTypes getBankAccountTypes(PayModes payMode) {
        if (payMode.equals(PayModes.Bank)) {
            return BankAccountTypes.Bank;
        }
        return BankAccountTypes.Cash;
    }

    public List<BankAccount> getBankAccounts(PayModes payModes) {
        return this.getBankAccounts(getBankAccountTypes(payModes));
    }

    public List<BankAccountDA> getBankAccountDAs(PayModes payModes) {
        return toDaList(getBankAccounts(getBankAccountTypes(payModes)));
    }

}
