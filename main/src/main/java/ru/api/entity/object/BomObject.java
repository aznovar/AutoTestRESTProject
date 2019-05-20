package main.java.ru.api.entity.object;

import main.java.ru.api.entity.BaseEntityInterface;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BomObject implements BaseEntityInterface<BomObject> {

    private String name;
    private String table;
    private String databaseName;
    private MetaType metaType;
    private String mainDict;
    private Boolean hidden;
    private String status;
    private String idAttr;
    private String parentAttr;
    private String isGroupAttr;
    private String ownerAttr;
    private String ownershipId;
    private String deleteMarkAttr;
    private String lastChangeAttr;
    private String lastUserAttr;
    private String versionAttr;
    private String scoringAttr;
    private List<String> interceptors;
    private List<String> labels;
    private Map<String, String> sourceQueries;
    private List<String> uniqueAttr;
    private List<String> captionAttrsAttr;
    private List<String> systemAttrs;
    private List<String> systemFields;
    private List<String> loadDataAttrs;
    private List<String> mergeExternalAttrs;
    private Boolean autoDeleteMarkAttr;
    private Boolean autoIsGroupAttr;
    private Boolean autoParentAttr;
    private Boolean autoLastChangeAttr;
    private Boolean autoLastUserAttr;
    private Boolean autoScoringAttr;
    private Boolean autoOwnershipId;
    private Boolean autoVersion;
    private Boolean autoId;
    private String caption;
    private List<Attribute> attrs;
    private List<Field> fields;
    private List<String> refObjectAttrs;
    private List<String> tableObjectAttrs;

    private BomObject(BomObjectBuilder builder) {
        name = builder.name;
        caption = builder.caption;
        table = builder.table;
        databaseName = builder.databaseName;
        metaType = builder.metaType;
        mainDict = builder.mainDict;
        hidden = builder.hidden;
        status = builder.status;
        idAttr = builder.idAttr;
        parentAttr = builder.parentAttr;
        isGroupAttr = builder.isGroupAttr;
        ownerAttr = builder.ownerAttr;
        ownershipId = builder.ownershipId;
        deleteMarkAttr = builder.deleteMarkAttr;
        lastChangeAttr = builder.lastChangeAttr;
        lastUserAttr = builder.lastUserAttr;
        versionAttr = builder.versionAttr;
        scoringAttr = builder.scoringAttr;
        attrs = builder.attrs;
        fields = builder.fields;
        interceptors = builder.interceptors;
        labels = builder.labels;
        sourceQueries = builder.sourceQueries;
        uniqueAttr = builder.uniqueAttr;
        captionAttrsAttr = builder.captionAttrsAttr;
        systemAttrs = builder.systemAttrs;
        systemFields = builder.systemFields;
        refObjectAttrs = builder.refObjectAttrs;
        loadDataAttrs = builder.loadDataAttrs;
        tableObjectAttrs = builder.tableObjectAttrs;
        mergeExternalAttrs = builder.mergeExternalAttrs;
        autoDeleteMarkAttr = builder.autoDeleteMarkAttr;
        autoIsGroupAttr = builder.autoIsGroupAttr;
        autoParentAttr = builder.autoParentAttr;
        autoLastChangeAttr = builder.autoLastChangeAttr;
        autoLastUserAttr = builder.autoLastUserAttr;
        autoOwnershipId = builder.autoOwnershipId;
        autoScoringAttr = builder.autoScoringAttr;
        autoVersion = builder.autoVersion;
        autoId = builder.autoId;
    }

    private BomObject() {
    }

    public static BomObjectBuilder builder() {
        return new BomObjectBuilder();
    }

    public String getName() {
        return name;
    }

    public String getCaption() {
        return caption;
    }

    public String getTable() {
        return table;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public MetaType getMetaType() {
        return metaType;
    }

    public String getMainDict() {
        return mainDict;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public String getStatus() {
        return status;
    }

    public String getIdAttr() {
        return idAttr;
    }

    public String getParentAttr() {
        return parentAttr;
    }

    public String getIsGroupAttr() {
        return isGroupAttr;
    }

    public String getOwnerAttr() {
        return ownerAttr;
    }

    public String getOwnershipId() {
        return ownershipId;
    }

    public String getDeleteMarkAttr() {
        return deleteMarkAttr;
    }

    public String getLastChangeAttr() {
        return lastChangeAttr;
    }

    public String getLastUserAttr() {
        return lastUserAttr;
    }

    public String getVersionAttr() {
        return versionAttr;
    }

    public String getScoringAttr() {
        return scoringAttr;
    }

    public List<Attribute> getAttrs() {
        return attrs;
    }

    public List<Field> getFields() {
        return fields;
    }

    public List<String> getInterceptors() {
        return interceptors;
    }

    public List<String> getLabels() {
        return labels;
    }

    public Map<String, String> getSourceQueries() {
        return sourceQueries;
    }

    public List<String> getUniqueAttr() {
        return uniqueAttr;
    }

    public List<String> getCaptionAttrsAttr() {
        return captionAttrsAttr;
    }

    public List<String> getSystemAttrs() {
        return systemAttrs;
    }

    public List<String> getSystemFields() {
        return systemFields;
    }

    public List<String> getRefObjectAttrs() {
        return refObjectAttrs;
    }

    public List<String> getLoadDataAttrs() {
        return loadDataAttrs;
    }

    public List<String> getTableObjectAttrs() {
        return tableObjectAttrs;
    }

    public List<String> getMergeExternalAttrs() {
        return mergeExternalAttrs;
    }

    public Boolean getAutoDeleteMarkAttr() {
        return autoDeleteMarkAttr;
    }

    public Boolean getAutoIsGroupAttr() {
        return autoIsGroupAttr;
    }

    public Boolean getAutoParentAttr() {
        return autoParentAttr;
    }

    public Boolean getAutoLastChangeAttr() {
        return autoLastChangeAttr;
    }

    public Boolean getAutoLastUserAttr() {
        return autoLastUserAttr;
    }

    public Boolean getAutoScoringAttr() {
        return autoScoringAttr;
    }

    public Boolean getAutoOwnershipId() {
        return autoOwnershipId;
    }

    public Boolean getAutoVersion() {
        return autoVersion;
    }

    public Boolean getAutoId() {
        return autoId;
    }

    @Override
    public String singleEntityUrl() {
        return "/some/address/to_single_entity_url";
    }

    @Override
    public String multipleEntitiesUrl() {
        return "/some/address/to_multiple_entity_url";
    }

    @Override
    public String entityByIdUrl() {
        return "/some/address/to_entity_by_name" + name;
    }

    @Override
    public void update(BomObject fromObj) {
        caption = fromObj.caption;
        attrs = fromObj.attrs;
        fields = fromObj.fields;
        refObjectAttrs = fromObj.refObjectAttrs;
        tableObjectAttrs = fromObj.tableObjectAttrs;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + nvl(name) + '\'' +
                ", caption='" + nvl(caption) + '\'' +
                ", table='" + nvl(table) + '\'' +
                ", metaType='" + metaType + '\'' +
                ", databaseName='" + databaseName + '\'' +
                ", attrs=" + attrs +
                ", fields=" + fields +
                ", autoScoringAttr=" + boolNvl(autoScoringAttr) +
                ", idAttr=" + nvl(idAttr) +
                ", deleteMarkAttr=" + nvl(deleteMarkAttr) +
                ", ownershipId=" + nvl(ownershipId) +
                ", lastChangeAttr=" + nvl(lastChangeAttr) +
                ", lastUserAttr=" + nvl(lastUserAttr) +
                ", scoringAttr=" + nvl(scoringAttr) +
                ", labels=" + listNvl(labels) +
                ", refObjectAttrs=" + listNvl(refObjectAttrs) +
                ", tableObjectAttrs=" + listNvl(tableObjectAttrs) +
                ", status=" + nvl(status) +
                ", autoId=" + autoId +
                '}';
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class BomObjectBuilder implements BuilderInterface<BomObject> {

        private String name;
        private String caption;
        private String table;
        private String databaseName;
        private MetaType metaType;
        private String mainDict;
        private Boolean hidden;
        private String status;
        private String idAttr;
        private String parentAttr;
        private String isGroupAttr;
        private String ownerAttr;
        private String ownershipId;
        private String deleteMarkAttr;
        private String lastChangeAttr;
        private String lastUserAttr;
        private String versionAttr;
        private String scoringAttr;
        private List<Attribute> attrs;
        private List<Field> fields;
        private List<String> interceptors;
        private List<String> labels;
        private Map<String, String> sourceQueries;
        private List<String> uniqueAttr;
        private List<String> captionAttrsAttr;
        private List<String> systemAttrs;
        private List<String> systemFields;
        private List<String> refObjectAttrs;
        private List<String> loadDataAttrs;
        private List<String> tableObjectAttrs;
        private List<String> mergeExternalAttrs;
        private Boolean autoDeleteMarkAttr;
        private Boolean autoIsGroupAttr;
        private Boolean autoParentAttr;
        private Boolean autoLastChangeAttr;
        private Boolean autoLastUserAttr;
        private Boolean autoScoringAttr;
        private Boolean autoOwnershipId;
        private Boolean autoVersion;
        private Boolean autoId;

        public BomObjectBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BomObjectBuilder caption(String caption) {
            this.caption = caption;
            return this;
        }

        public BomObjectBuilder table(String table) {
            this.table = table;
            return this;
        }

        public BomObjectBuilder databaseName(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        public BomObjectBuilder metaType(MetaType metaType) {
            this.metaType = metaType;
            return this;
        }

        public BomObjectBuilder mainDict(String mainDict) {
            this.mainDict = mainDict;
            return this;
        }

        public BomObjectBuilder hidden(Boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public BomObjectBuilder status(String status) {
            this.status = status;
            return this;
        }

        public BomObjectBuilder idAttr(String idAttr) {
            this.idAttr = idAttr;
            return this;
        }

        public BomObjectBuilder parentAttr(String parentAttr) {
            this.parentAttr = parentAttr;
            return this;
        }

        public BomObjectBuilder isGroupAttr(String isGroupAttr) {
            this.isGroupAttr = isGroupAttr;
            return this;
        }

        public BomObjectBuilder ownerAttr(String ownerAttr) {
            this.ownerAttr = ownerAttr;
            return this;
        }

        public BomObjectBuilder ownershipId(String ownershipId) {
            this.ownershipId = ownershipId;
            return this;
        }

        public BomObjectBuilder deleteMarkAttr(String deleteMarkAttr) {
            this.deleteMarkAttr = deleteMarkAttr;
            return this;
        }

        public BomObjectBuilder lastChangeAttr(String lastChangeAttr) {
            this.lastChangeAttr = lastChangeAttr;
            return this;
        }

        public BomObjectBuilder lastUserAttr(String lastUserAttr) {
            this.lastUserAttr = lastUserAttr;
            return this;
        }

        public BomObjectBuilder versionAttr(String versionAttr) {
            this.versionAttr = versionAttr;
            return this;
        }

        public BomObjectBuilder scoringAttr(String scoringAttr) {
            this.scoringAttr = scoringAttr;
            return this;
        }

        public BomObjectBuilder attrs(List<Attribute> attrs) {
            this.attrs = attrs;
            return this;
        }

        public BomObjectBuilder fields(List<Field> fields) {
            this.fields = fields;
            return this;
        }

        public BomObjectBuilder interceptors(List<String> interceptors) {
            this.interceptors = interceptors;
            return this;
        }

        public BomObjectBuilder labels(List<String> labels) {
            this.labels = labels;
            return this;
        }

        public BomObjectBuilder sourceQueries(Map<String, String> sourceQueries) {
            this.sourceQueries = sourceQueries;
            return this;
        }

        public BomObjectBuilder uniqueAttr(List<String> uniqueAttr) {
            this.uniqueAttr = uniqueAttr;
            return this;
        }

        public BomObjectBuilder captionAttrsAttr(List<String> captionAttrsAttr) {
            this.captionAttrsAttr = captionAttrsAttr;
            return this;
        }

        public BomObjectBuilder systemAttrs(List<String> systemAttrs) {
            this.systemAttrs = systemAttrs;
            return this;
        }

        public BomObjectBuilder systemFields(List<String> systemFields) {
            this.systemFields = systemFields;
            return this;
        }

        public BomObjectBuilder refObjectAttrs(List<String> refObjectAttrs) {
            this.refObjectAttrs = refObjectAttrs;
            return this;
        }

        public BomObjectBuilder loadDataAttrs(List<String> loadDataAttrs) {
            this.loadDataAttrs = loadDataAttrs;
            return this;
        }

        public BomObjectBuilder tableObjectAttrs(List<String> tableObjectAttrs) {
            this.tableObjectAttrs = tableObjectAttrs;
            return this;
        }

        public BomObjectBuilder mergeExternalAttrs(List<String> mergeExternalAttrs) {
            this.mergeExternalAttrs = mergeExternalAttrs;
            return this;
        }

        public BomObjectBuilder autoDeleteMarkAttr(Boolean autoDeleteMarkAttr) {
            this.autoDeleteMarkAttr = autoDeleteMarkAttr;
            return this;
        }

        public BomObjectBuilder autoIsGroupAttr(Boolean autoIsGroupAttr) {
            this.autoIsGroupAttr = autoIsGroupAttr;
            return this;
        }

        public BomObjectBuilder autoParentAttr(Boolean autoParentAttr) {
            this.autoParentAttr = autoParentAttr;
            return this;
        }

        public BomObjectBuilder autoLastChangeAttr(Boolean autoLastChangeAttr) {
            this.autoLastChangeAttr = autoLastChangeAttr;
            return this;
        }

        public BomObjectBuilder autoLastUserAttr(Boolean autoLastUserAttr) {
            this.autoLastUserAttr = autoLastUserAttr;
            return this;
        }

        public BomObjectBuilder autoScoringAttr(Boolean autoScoringAttr) {
            this.autoScoringAttr = autoScoringAttr;
            return this;
        }

        public BomObjectBuilder autoOwnershipId(Boolean autoOwnershipId) {
            this.autoOwnershipId = autoOwnershipId;
            return this;
        }

        public BomObjectBuilder autoVersion(Boolean autoVersion) {
            this.autoVersion = autoVersion;
            return this;
        }

        public BomObjectBuilder autoId(Boolean autoId) {
            this.autoId = autoId;
            return this;
        }

        @Override
        public BomObjectBuilder randomize() {
            name = new Param.ParamBuilder()
                    .prefix("some_prefix")
                    .maxLen(7)
                    .generate()
                    .build()
                    .getName();
            caption = name.toLowerCase();
            table = name.toUpperCase();
            metaType = MetaType.list;
            databaseName = DBName.ORACLE.getName();
            autoId = true;
            fields = Collections.emptyList();
            attrs = Collections.emptyList();
            return this;
        }

        @Override
        public BomObject build() {
            return new BomObject(this);
        }
    }
}
