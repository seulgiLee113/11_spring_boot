package com.ohgiraffers.crud.menu.model.dto;

public class CategoryDTO {

    private int code;
    private String name;
    private int refCategoryName;

    public CategoryDTO() {
    }

    public CategoryDTO(int code, String name, int refCategoryName) {
        this.code = code;
        this.name = name;
        this.refCategoryName = refCategoryName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRefCategoryName() {
        return refCategoryName;
    }

    public void setRefCategoryName(int refCategoryName) {
        this.refCategoryName = refCategoryName;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", refCategoryName=" + refCategoryName +
                '}';
    }
}
