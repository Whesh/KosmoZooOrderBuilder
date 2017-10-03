package com.whesh.xlsorderbuilder.model;

public class PriceOrder extends AbstractPrice {
    @Override
    public void setSellerName() {
        sellerName = "КосмоЗОО";
    }

    @Override
    public void setProductArticulCellNumber() {
        productArticulCellNumber = 3;
    }

    @Override
    public void setProductColsCellNumber() {
        productColsCellNumber = 9;
    }

    @Override
    protected void setProductNameCellNumber() {
        productNameCellNumber = 4;
    }

    @Override
    protected void setProductIdCellNumber() {
        productIdCellNumber = 0;
    }
}
