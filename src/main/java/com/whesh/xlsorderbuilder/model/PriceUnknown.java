package com.whesh.xlsorderbuilder.model;

public class PriceUnknown extends AbstractPrice {

    public PriceUnknown(String sellerName, int productIdCellNumber, int productColsCellNumber){
        setSellerName(sellerName);
        setArticulCellNumber(productIdCellNumber);
        setProductColsCellNumber(productColsCellNumber);
    }

    protected void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @Override
    protected void setSellerName() {
        this.sellerName = "Неизвестная компания";
    }

    @Override
    protected void setProductArticulCellNumber() {
        productArticulCellNumber = 0;
    }

    protected void setArticulCellNumber(int productIdCellNumber){
        this.productArticulCellNumber = productIdCellNumber;
    }

    @Override
    protected void setProductColsCellNumber() {
        productColsCellNumber = 0;
    }

    protected void setProductColsCellNumber(int productColsCellNumber){
        this.productColsCellNumber = productColsCellNumber;
    }

    @Override
    protected void setProductNameCellNumber() {
        productNameCellNumber = 0;
    }

    @Override
    protected void setProductIdCellNumber() {
        productIdCellNumber = 0;
    }

    protected void setProductNameCellNumber(int productNameCellNumber) {
        this.productNameCellNumber = productNameCellNumber;
    }

}
