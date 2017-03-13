package io.laudoak.model;

/**
 * Created by laudoak on 16/12/18.
 * <p>
 * Java实体类和数据库表的基类
 */
public class BaseModel {
    private String name;//Java实体类/数据库表名称
    private String comment;//Java实体类/数据库表注释

    public BaseModel(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
