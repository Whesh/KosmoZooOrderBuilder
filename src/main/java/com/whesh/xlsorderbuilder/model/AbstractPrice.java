package com.whesh.xlsorderbuilder.model;

public abstract class AbstractPrice {

    protected String sellerName;
    protected int productIdCellNumber;
    protected int productCollsCellNumber;

    public AbstractPrice(){
        setSellerName();
        setProductIdCellNumber();
        setProductCollsCellNumber();
    }

    public String getSellerName() {
        return sellerName;
    }

    public abstract void setSellerName();

    public int getProductIdCellNumber() {
        return productIdCellNumber;
    }

    public abstract void setProductIdCellNumber();

    public int getProductCollsCellNumber() {
        return productCollsCellNumber;
    }

    public abstract void setProductCollsCellNumber();
}
