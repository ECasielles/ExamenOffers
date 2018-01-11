package com.example.usuario.examenecasielles.ui.contract;

import com.example.usuario.examenecasielles.data.db.model.Offer;

import java.util.ArrayList;

/**
 * Contrato entre las clases ListOfferFragment y ListOfferPresenter
 */
public interface ListOfferContract {
    interface View {
        void showOffers(ArrayList<Offer> offers);
    }
    interface Presenter {
        void loadOffers();
        void loadOffers(int importance);
    }
}
