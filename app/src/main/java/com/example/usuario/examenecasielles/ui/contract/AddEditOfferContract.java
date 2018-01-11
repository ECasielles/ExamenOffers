package com.example.usuario.examenecasielles.ui.contract;

/**
 * Created by usuario on 11/01/18.
 */

public interface AddEditOfferContract {
    interface View {
        void reload();
        void onEmptyFieldError();

        void onExistsError();
    }
    interface Presenter {
        void addOffer(String name, String shop, String date, int type, int importance);
    }
}
