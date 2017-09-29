package com.whesh.xlsorderbuilder.model;

public class PriceUnknown extends AbstractPrice {

    public PriceUnknown(String sellerName, int productIdCellNumber, int productColsCellNumber){
        setSellerName(sellerName);
        setProductIdCellNumber(productIdCellNumber);
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
    protected void setProductIdCellNumber() {
        productIdCellNumber = 0;
    }

    protected void setProductIdCellNumber(int productIdCellNumber){
        this.productIdCellNumber = productIdCellNumber;
    }

    @Override
    protected void setProductColsCellNumber() {
        productColsCellNumber = 0;
    }

    protected void setProductColsCellNumber(int productColsCellNumber){
        this.productColsCellNumber = productColsCellNumber;
    }
}
