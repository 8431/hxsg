package com.hxsg.admin.vo;

import java.io.Serializable;

/**
 * Created by dlf on 2016/11/7.
 */
public class ColumnAndComment implements Serializable{
    private String column;
    private String comment;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
