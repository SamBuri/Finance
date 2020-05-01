/*
 Sam Buriima
generated by Saburi Tools
 */
package com.saburi.finance.dbaccess;

import com.saburi.common.dbaccess.DBAccess;
import com.saburi.common.dbaccess.LookupDataDA;
import com.saburi.finance.entities.MeasureRelation;
import javafx.beans.property.*;
import java.util.ArrayList;
import com.saburi.common.entities.AppRevisionEntity;
import java.util.List;
import com.saburi.common.utils.SearchColumn;
import com.saburi.common.utils.SearchColumn.SearchDataTypes;
import org.hibernate.envers.RevisionType;
import com.saburi.finance.entities.MeasureGroup;
import com.saburi.common.entities.LookupData;
import com.saburi.finance.utils.FinanceEnums.UnitMeasureUsages;

public class MeasureRelationDA extends DBAccess {

    private MeasureRelation measureRelation = new MeasureRelation();
    private final SimpleStringProperty measureRelationID = new SimpleStringProperty(this, "measureRelationID");
    private final SimpleStringProperty measureGroupDisplay = new SimpleStringProperty(this, "measureGroupDisplay");
    private final SimpleObjectProperty measureGroupID = new SimpleObjectProperty(this, "measureGroupID");
    private MeasureGroup measureGroup;
    private final SimpleStringProperty unitMeasureDisplay = new SimpleStringProperty(this, "unitMeasureDisplay");
    private final SimpleObjectProperty unitMeasureID = new SimpleObjectProperty(this, "unitMeasureID");
    private LookupData unitMeasure;
    private final SimpleIntegerProperty baseSize = new SimpleIntegerProperty(this, "baseSize");
    private final SimpleObjectProperty defaultUsage = new SimpleObjectProperty(this, "defaultUsage");

    public MeasureRelationDA() {
        createSearchColumns();
    }

    public MeasureRelationDA(String persistenceUnit) {
        super(persistenceUnit);
    }

    public MeasureRelationDA(MeasureRelation measureRelation) {
        this.measureRelation = measureRelation;
        initialseProprties();
        createSearchColumns();
    }

    public MeasureRelationDA(String persistenceUnit, MeasureRelation measureRelation) {
        super(persistenceUnit);
        this.measureRelation = measureRelation;
        initialseProprties();
        createSearchColumns();
    }

    public MeasureRelationDA(MeasureGroup measureGroup, LookupData unitMeasure, int baseSize, UnitMeasureUsages defaultUsage) {
        this.measureRelation = new MeasureRelation(measureGroup, unitMeasure, baseSize, defaultUsage);
        initialseProprties();
        createSearchColumns();
    }

    public MeasureRelationDA(String persistenceUnit, MeasureGroup measureGroup, LookupData unitMeasure, int baseSize, UnitMeasureUsages defaultUsage) {
        super(persistenceUnit);
        this.measureRelation = new MeasureRelation(measureGroup, unitMeasure, baseSize, defaultUsage);
        initialseProprties();
        createSearchColumns();
    }

    public String getMeasureRelationID() {
        return measureRelationID.get();
    }

    public MeasureGroup getMeasureGroup() {
        return measureGroup;
    }

    public Object getMeasureGroupID() {
        return measureGroupID.get();
    }

    public String getMeasureGroupDisplay() {
        return measureGroupDisplay.get();
    }

    public MeasureGroupDA getMeasureGroupDA() {
        return this.measureGroup != null ? new MeasureGroupDA(this.measureGroup) : null;
    }

    public void setMeasureGroup(MeasureGroup measureGroup) {
        measureRelation.setMeasureGroup(measureGroup);
        this.measureGroup = measureGroup;
        this.measureGroupID.set(measureGroup.getId());
        this.measureGroupDisplay.set(measureGroup.getDisplayKey());
    }

    public LookupData getUnitMeasure() {
        return unitMeasure;
    }

    public Object getUnitMeasureID() {
        return unitMeasureID.get();
    }

    public String getUnitMeasureDisplay() {
        return unitMeasureDisplay.get();
    }

    public LookupDataDA getUnitMeasureDA() {
        return this.unitMeasure != null ? new LookupDataDA(this.unitMeasure) : null;
    }

    public void setUnitMeasure(LookupData unitMeasure) {
        measureRelation.setUnitMeasure(unitMeasure);
        this.unitMeasure = unitMeasure;
        this.unitMeasureID.set(unitMeasure.getId());
        this.unitMeasureDisplay.set(unitMeasure.getDisplayKey());
    }

    public int getBaseSize() {
        return baseSize.get();
    }

    public void setBaseSize(int baseSize) {
        measureRelation.setBaseSize(baseSize);
        this.baseSize.set(baseSize);

    }

    public Object getDefaultUsage() {
        return defaultUsage.get();
    }

    public void setDefaultUsage(UnitMeasureUsages defaultUsage) {
        measureRelation.setDefaultUsage(defaultUsage);
        this.defaultUsage.set(defaultUsage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MeasureRelationDA)) {
            return false;
        }

        MeasureRelationDA measureRelationDA = (MeasureRelationDA) o;

        if (measureRelationDA.getDBEntity() == null || this.getDBEntity() == null) {
            return false;
        }
        return this.getId().equals(measureRelationDA.getId());
    }

    @Override
    public int hashCode() {
        return measureRelation.getId().hashCode();
    }

    private void initialseProprties() {
        this.dBEntity = measureRelation;
        this.measureRelationID.set(measureRelation.getMeasureRelationID());
        this.measureGroup = measureRelation.getMeasureGroup();
        if (this.measureGroup != null) {
            this.measureGroupID.set(measureGroup.getId());
            this.measureGroupDisplay.set(measureGroup.getDisplayKey());
        }
        this.unitMeasure = measureRelation.getUnitMeasure();
        if (this.unitMeasure != null) {
            this.unitMeasureID.set(unitMeasure.getId());
            this.unitMeasureDisplay.set(unitMeasure.getDisplayKey());
        }
        this.baseSize.set(measureRelation.getBaseSize());
        this.defaultUsage.set(measureRelation.getDefaultUsage());
        initCommonProprties();
    }

    private void createSearchColumns() {
        this.searchColumns.add(new SearchColumn("measureRelationID", "Measure Relation ID", this.measureRelationID.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("measureGroupID", "Measure Group ID", this.measureGroupID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("measureGroupDisplay", "Measure Group", this.measureGroupDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("unitMeasureID", "Unit Measure ID", this.unitMeasureID.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal, false));
        this.searchColumns.add(new SearchColumn("unitMeasureDisplay", "Unit Measure", this.unitMeasureDisplay.get(), SearchDataTypes.STRING));
        this.searchColumns.add(new SearchColumn("baseSize", "Base Size", this.baseSize.get(), SearchDataTypes.NUMBER));
        this.searchColumns.add(new SearchColumn("defaultUsage", "Default Usage", this.defaultUsage.get(), SearchDataTypes.STRING, SearchColumn.SearchType.Equal));
        this.searchColumns.addAll(this.getDefaultSearchColumns());
    }

    @Override
    public List<SearchColumn> getSearchColumns() {
        return this.searchColumns;
    }

    @Override
    public Object getId() {
        return this.measureRelation.getId();
    }

    @Override
    public String getDisplayKey() {
        return this.measureRelation.getDisplayKey();
    }

    public static List<MeasureRelationDA> getMeasureRelationDAs(List<MeasureRelation> measureRelations) {
        List<MeasureRelationDA> list = new ArrayList<>();
        measureRelations.forEach((measureRelation) -> {
            list.add(new MeasureRelationDA(measureRelation));
        });
        return list;
    }

    public static List<MeasureRelation> getMeasureRelationList(List<MeasureRelationDA> measureRelationDAs) {
        List<MeasureRelation> measureRelations = new ArrayList<>();
        measureRelationDAs.forEach(a -> measureRelations.add(a.measureRelation));
        return measureRelations;
    }

    public boolean save() throws Exception {
        return super.persist(this.measureRelation);

    }

    public boolean update() throws Exception {
        return super.merge(this.measureRelation);

    }

    public boolean delete() {
        return super.remove(this.measureRelation);

    }

    public MeasureRelation getMeasureRelation(String measureRelationID) {
        return (MeasureRelation) super.find(MeasureRelation.class, measureRelationID);
    }

    public MeasureRelation getMeasureRelation() {
        return this.measureRelation;
    }

    public List<MeasureRelation> getMeasureRelations() {
        return super.find(MeasureRelation.class);
    }

    @Override
    public List<DBAccess> get() {
        List<DBAccess> list = new ArrayList<>();
        selectQuery(MeasureRelation.class).forEach(o -> list.add(new MeasureRelationDA((MeasureRelation) o)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public MeasureRelationDA get(String measureRelationID) throws Exception {
        MeasureRelation oMeasureRelation = getMeasureRelation(measureRelationID);
        if (oMeasureRelation == null) {
            throw new Exception("No Record with id: " + measureRelationID);
        }
        return new MeasureRelationDA(oMeasureRelation);
    }

    public List<MeasureRelationDA> get(String columName, Object value) {
        List<MeasureRelationDA> list = new ArrayList<>();
        super.selectQuery(MeasureRelation.class, columName, value).forEach(da -> list.add(new MeasureRelationDA((MeasureRelation) da)));
        if (entityManager != null) {
            entityManager.close();
        }
        return list;
    }

    public List<MeasureRelationDA> toDaList(List<MeasureRelation> measureRelations) {
        List<MeasureRelationDA> measureRelationDAs = new ArrayList<>();
        measureRelations.forEach(s -> measureRelationDAs.add(new MeasureRelationDA(s)));
        return measureRelationDAs;
    }

    public List<DBAccess> toDBAccessList(List<MeasureRelation> measureRelations) {
        List<DBAccess> dbAccesses = new ArrayList<>();
        measureRelations.forEach(s -> dbAccesses.add(new MeasureRelationDA(s)));
        return dbAccesses;
    }

    public int getMax(String columnName) {
        return super.getMax(MeasureRelation.class, columnName);
    }

    public int getMax(String columnName, String comparatorColumn, Object comparatorVaue) {
        return super.getMax(MeasureRelation.class, columnName, comparatorColumn, comparatorVaue);
    }

    public List<MeasureRelation> getMeasureRelations(String columName, Object value) {
        return super.find(MeasureRelation.class, columName, value);
    }

    @Override
    public List<DBAccess> getRevisions() {
        try {

            List<Object[]> list = getEntityRevisions(MeasureRelation.class);
            List<DBAccess> dBAccesses = new ArrayList<>();
            list.forEach(e -> {

                MeasureRelationDA measureRelationDA = new MeasureRelationDA((MeasureRelation) e[0]);
                measureRelationDA.revisionEntity = (AppRevisionEntity) e[1];
                measureRelationDA.oRevisionType = (RevisionType) e[2];
                measureRelationDA.initRevProprties();
                measureRelationDA.searchColumns.addAll(measureRelationDA.getRevSearchColumns());
                dBAccesses.add(measureRelationDA);

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

}
