package com.flexion.funflowers;


import com.flexionmobile.codingchallenge.integration.Purchase;

public class FlexionPurchase implements Purchase {

    private final String id;
    private boolean consumed;
    private final String itemId;

    public FlexionPurchase() {
        this.id = null;
        this.consumed = false;
        this.itemId = null;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean getConsumed() {
        return consumed;
    }

    @Override
    public String getItemId() {
        return itemId;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }
}
