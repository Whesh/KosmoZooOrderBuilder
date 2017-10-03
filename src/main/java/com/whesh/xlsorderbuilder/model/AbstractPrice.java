package com.whesh.xlsorderbuilder.model;

public abstract class AbstractPrice {

    protected String sellerName;
    protected int productIdCellNumber;
    protected int productArticulCellNumber;
    protected int productNameCellNumber;
    protected int productColsCellNumber;

    public AbstractPrice(){
        setSellerName();
        setProductArticulCellNumber();
        setProductColsCellNumber();
        setProductNameCellNumber();
        setProductIdCellNumber();
    }

    public String getSellerName() {
        return sellerName;
    }

    protected abstract void setSellerName();

    public int getProductArticulCellNumber() {
        return productArticulCellNumber;
    }

    protected abstract void setProductArticulCellNumber();

    public int getProductColsCellNumber() {
        return productColsCellNumber;
    }

    protected abstract void setProductColsCellNumber();

    public int getProductNameCellNumber() {
        return productNameCellNumber;
    }

    protected abstract void setProductNameCellNumber();

    public int getProductIdCellNumber() {
        return productIdCellNumber;
    }

    protected abstract void setProductIdCellNumber();
}
