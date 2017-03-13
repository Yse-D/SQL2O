package io.laudoak.model;

/**
 * Created by laudoak on 17/1/1.
 * <p>
 * 表字段
 */
public class TableAtt extends BaseAtt {
    private Integer ordinalPosition;
    private String columnDefault;
    private String isNullable;
    private String columnKey;
    private String columnType;
    private String extra;

    public TableAtt() {
        super();
    }

    public TableAtt(String name, String type, String comment) {
        super(name, type, comment);
    }

    public Integer getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(Integer ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
}
