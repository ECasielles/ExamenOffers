package com.example.usuario.examenecasielles.ui.interactor;

/**
 * Created by usuario on 11/01/18.
 */

public interface AddEditOfferInteractor {

    void addNewOffer(String name, String shop, String date, int type, int importance);

    interface OnOfferAddEditListener {
        void reloadOffers();
        void emptyFieldError();
        void offerExistsError();
    }
}
