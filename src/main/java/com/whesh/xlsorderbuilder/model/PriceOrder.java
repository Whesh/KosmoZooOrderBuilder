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
    public void setProductCollsCellNumber() {
        productCollsCellNumber = 9;
    }
}
