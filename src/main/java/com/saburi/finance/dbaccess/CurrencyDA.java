/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.dbaccess;

import com.saburi.common.dbaccess.DBAccess;
import com.saburi.common.dbaccess.IDGeneratorDA;
import com.saburi.finance.entities.Currency;
import javafx.beans.property.*;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import com.saburi.common.utils.SearchColumn;
import com.saburi.common.utils.SearchColumn.SearchDataTypes;
import static com.saburi.common.utils.Utilities.formatNumber;

public class CurrencyDA extends DBAccess {

    private Currency currency;
    private SimpleIntegerProperty idHelper = new SimpleIntegerProperty(this, "idHelper");
    private SimpleStringProperty currencyID = new SimpleStringProperty(this, "currencyID");
    private SimpleStringProperty currencyName = new SimpleStringProperty(this, "currencyName");
    private SimpleDoubleProperty buying = new SimpleDoubleProperty(this, "buying");
    private SimpleStringProperty buyingDisplay = new SimpleStringProperty(this, "buyingDisplay");
    private SimpleDoubleProperty selling = new SimpleDoubleProperty(this, "selling");
    private SimpleStringProperty sellingDisplay = new SimpleStringProperty(this, "sellingDisplay");
    private SimpleBooleanProperty isDefault = new SimpleBooleanProperty(this, "isDefault");

    public CurrencyDA() {
        createSearchColumns();
    }

    public CurrencyDA(String persistenceUnit) {
        super(persistenceUnit);
    }

    public CurrencyDA(Currency currency) {
        this.currency = currency;
        initialseProprties();
        createSearchColumns();
    }

    public CurrencyDA(String persistenceUnit, Currency currency) {
        super(persistenceUnit);
        this.currency = currency;
        initialseProprties();
        createSearchColumns();
    }

    public CurrencyDA(String currencyID, String currencyName, double buying, double selling, boolean isDefault) {
        this.currency = new Currency(getNextIdHelper(), currencyID, currencyName, buying, selling, isDefault);
        initialseProprties();
        createSearchColumns();
    }

    public CurrencyDA(String persistenceUnit, String currencyID, String currencyName, double buying, double selling, boolean isDefault) {
        super(persistenceUnit);
        this.currency = new Currency(getNextIdHelper(), currencyID, currencyName, buying, selling, isDefault);
        initialseProprties();
        createSearchColumns();
    }

    public int getIdHelper() {
        return idHelper.get();
    }

    public void setIdHelper(int idHelper) {
        currency.setIdHelper(idHelper);
        this.idHelper.set(idHelper);
    }

    public String getCurrencyID() {
        return currencyID.get();
    }

    public void setCurrencyID(String currencyID) {
        currency.setCurrencyID(currencyID);
        this.currencyID.set(currencyID);
    }

    public String getCurrencyName() {
        return currencyName.get();
    }

    public void setCurrencyName(String currencyName) {
        currency.setCurrencyName(currencyName);
        this.currencyName.set(currencyName);
    }

    public double getBuying() {
        return buying.get();
    }

    public String getBuyingDisplay() {
        return buyingDisplay.get();
    }

    public void setBuying(double buying) {
        currency.setBuying(buying);
        this.buying.set(buying);
    }

    public double getSelling() {
        return selling.get();
    }

    public String getSellingDisplay() {
        return sellingDisplay.get();
    }

    public void setSelling(double selling) {
        currency.setSelling(selling);
        this.selling.set(selling);
    }

    public boolean isIsDefault() {
        return isDefault.get();
    }

    public void setIsDefault(boolean isDefault) {
        currency.setIsDefault(isDefault);
        this.isDefault.set(isDefault);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CurrencyDA)) {
            return false;
        }

        CurrencyDA currencyDA = (CurrencyDA) o;

        if (currencyDA.getDBEntity() == null || this.getDBEntity() == null) {
            return false;
        }
        return this.getId().equals(currencyDA.getId());
    }

    @Override
    public int hashCode() {
        return currency.getId().hashCode();
    }

    private void initialseProprties() {
        this.dBEntity = currency;
        this.idHelper = new SimpleIntegerProperty(currency.getIdHelper());
        this.currencyID = new SimpleStringProperty(currency.getCurrencyID());
        this.currencyName = new SimpleStringProperty(currency.getCurrencyName());
        this.buying = new SimpleDoubleProperty(currency.getBuying());
        this.buyingDisplay = new SimpleStringProperty(formatNumber(currency.getBuying()));
        this.selling = new SimpleDoubleProperty(currency.getSelling());
        this.sellingDisplay = new SimpleStringProperty(formatNumber(currency.getSelling()));
        this.isDefault = new SimpleBooleanProperty(currency.isIsDefault());
        initCommonProprties();
    }

    private void createSearchColumns() {
        this.searchColumns.add(new SearchColumn("currencyID", "Currency ID", this.currencyID.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("currencyName", "Currency Name", this.currencyName.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("buying", "Buying", this.buying.get(), SearchDataTypes.NUMBER));
        this.searchColumns.add(new SearchColumn("selling", "Selling", this.selling.get(), SearchDataTypes.NUMBER));
        this.searchColumns.add(new SearchColumn("isDefault", "Is Default", this.isDefault.get(), SearchDataTypes.BOOLEAN));
        this.searchColumns.addAll(this.getDefaultSearchColumns());
    }

    @Override
    public List<SearchColumn> getSearchColumns() {
        return this.searchColumns;
    }

    @Override
    public Object getId() {
        return this.currency.getId();
    }

    @Override
    public String getDisplayKey() {
        return this.currency.getDisplayKey();
    }

    public static List<CurrencyDA> getCurrencyDAs(List<Currency> currencys) {
        List<CurrencyDA> list = new ArrayList<>();
        currencys.forEach((currency) -> {
            list.add(new CurrencyDA(currency));
        });
        return list;
    }

    public static List<Currency> getCurrencyList(List<CurrencyDA> currencyDAs) {
        List<Currency> currencys = new ArrayList<>();
        currencyDAs.forEach(a -> currencys.add(a.currency));
        return currencys;
    }

    public boolean save() throws Exception {
        if (isValidate()) {
            return super.persist(this.currency);
        }
        return false;
    }

    public boolean update() throws Exception {
        if (isValidate()) {
            return super.merge(this.currency);
        }
        return false;
    }

    public boolean delete() {
        return super.remove(this.currency);

    }

    public Currency getCurrency(String currencyID) {
        return (Currency) super.find(Currency.class, currencyID);
    }

    public List<Currency> getCurrencys() {
        return super.find(Currency.class);
    }

    @Override
    public List<DBAccess> get() {
        List<DBAccess> list = new ArrayList<>();
        List<Currency> datas = super.find(Currency.class);
        datas.forEach((data) -> {
            list.add(new CurrencyDA(data));
        });
        return list;
    }

    public CurrencyDA get(String currencyID) throws Exception {
        Currency oCurrency = getCurrency(currencyID);
        if (oCurrency == null) {
            throw new Exception("No Record with id: " + currencyID);
        }
        return new CurrencyDA(oCurrency);
    }

    public List<CurrencyDA> get(String columName, Object value) {
        List<Currency> data = super.find(Currency.class, columName, value);
        List<CurrencyDA> list = new ArrayList<>();
        data.forEach(da -> list.add(new CurrencyDA(da)));
        return list;
    }

    public List<Pair<String, Object>> keyValuePairs() {
        List<Pair<String, Object>> pairs = new ArrayList<>();
        this.get().forEach((t) -> pairs.add(t.keyValuePair()));
        return pairs;
    }

    public List<Pair<String, Object>> keyValuePairs(String columName, Object value) {
        List<Pair<String, Object>> pairs = new ArrayList<>();
        this.get(columName, value).forEach((t) -> pairs.add(t.keyValuePair()));
        return pairs;
    }

    public List<CurrencyDA> toDaList(List<Currency> currencys) {
        List<CurrencyDA> currencyDAs = new ArrayList<>();
        currencys.forEach(s -> currencyDAs.add(new CurrencyDA(s)));
        return currencyDAs;
    }

    public List<DBAccess> toDBAccessList(List<Currency> currencys) {
        List<DBAccess> dbAccesses = new ArrayList<>();
        currencys.forEach(s -> dbAccesses.add(new CurrencyDA(s)));
        return dbAccesses;
    }

    public int getMax(String columnName) {
        return super.getMax(Currency.class, columnName);
    }

    public int getMax(String columnName, String comparatorColumn, Object comparatorVaue) {
        return super.getMax(Currency.class, columnName, comparatorColumn, comparatorVaue);
    }

    public final int getNextIdHelper() {
        return this.getMax("idHelper") + 1;
    }

    public String getNextCurrencyID(int idHelper) {
        return new IDGeneratorDA().getToAppendString(Currency.class.getSimpleName(), idHelper);
    }

    public List<Currency> getCurrencys(String columName, Object value) {
        return super.find(Currency.class, columName, value);
    }

    private boolean isValidate() throws Exception {
        if (this.isIsDefault()) {
            if (!getCurrencys("isDefault", true).isEmpty()) {
                throw new Exception("There is already a record set as default for currency. You can't have more than one");

            }
            if (getBuying() != 1 || getSelling() != 1) {
                throw new Exception("The buying and selling must be set to 1 for a default currency");
            }
        }
        return true;
    }

    public Currency getDefaultCurrency() {
        List<Currency> currencies = this.getCurrencys("isDefault", true);
        return currencies.isEmpty() ? null : currencies.get(0);
    }

    public CurrencyDA getDefaultCurrencyDA() {
        List<Currency> currencies = this.getCurrencys("isDefault", true);
        return currencies.isEmpty() ? null : new CurrencyDA(currencies.get(0));
    }

}