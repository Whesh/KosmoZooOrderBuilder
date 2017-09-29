package com.whesh.xlsorderbuilder.model;

public class PriceUnknown extends AbstractPrice {

    public PriceUnknown(String sellerName, int productIdCellNumber, int productColsCellNumber){
        setSellerName(sellerName);
        setProductIdCellNumber(productIdCellNumber);
        setProductColsCellNumber(productColsCellNumber);
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @Override
    public void setSellerName() {
        this.sellerName = "Неизвестная компания";
    }

    @Override
    public void setProductIdCellNumber() {
        productIdCellNumber = 0;
    }

    public void setProductIdCellNumber(int productIdCellNumber){
        this.productIdCellNumber = productIdCellNumber;
    }

    @Override
    public void setProductColsCellNumber() {
        productColsCellNumber = 0;
    }

    public void setProductColsCellNumber(int productColsCellNumber){
        this.productColsCellNumber = productColsCellNumber;
    }
}
