package com.whesh.xlsorderbuilder.model;

public class PriceOrder extends AbstractPrice {
    @Override
    public void setSellerName() {
        sellerName = "КосмоЗОО";
    }

    @Override
    public void setProductIdCellNumber() {
        productIdCellNumber = 3;
    }

    @Override
    public void setProductColsCellNumber() {
        productColsCellNumber = 9;
    }
}
