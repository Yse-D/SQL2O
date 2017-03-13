package io.laudoak.model;

/**
 * Created by laudoak on 16/12/18.
 * <p>
 * Java实体类域/数据库表字段属性基类
 */
public class BaseAtt {
    private String name;//名称
    private String type;//类型
    private String comment;//注释

    public BaseAtt() {
    }

    public BaseAtt(String name, String type, String comment) {
        this.name = name;
        this.type = type;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
