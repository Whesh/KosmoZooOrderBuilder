package com.whesh.xlsorderbuilder.model;

public abstract class AbstractPrice {

    protected String sellerName;
    protected int productIdCellNumber;
    protected int productColsCellNumber;

    public AbstractPrice(){
        setSellerName();
        setProductIdCellNumber();
        setProductColsCellNumber();
    }

    public String getSellerName() {
        return sellerName;
    }

    public abstract void setSellerName();

    public int getProductIdCellNumber() {
        return productIdCellNumber;
    }

    public abstract void setProductIdCellNumber();

    public int getProductColsCellNumber() {
        return productColsCellNumber;
    }

    public abstract void setProductColsCellNumber();
}
