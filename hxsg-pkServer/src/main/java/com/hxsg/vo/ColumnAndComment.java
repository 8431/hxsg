package com.hxsg.vo;

import java.io.Serializable;

/**
 * Created by dlf on 2016/11/7.
 */
public class ColumnAndComment implements Serializable{
    private String columnName;
    private String columnComment;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
}
