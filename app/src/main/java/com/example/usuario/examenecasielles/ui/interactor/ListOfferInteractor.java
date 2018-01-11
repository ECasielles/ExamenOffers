package com.example.usuario.examenecasielles.ui.interactor;

import com.example.usuario.examenecasielles.data.db.model.Offer;

import java.util.ArrayList;

/**
 * Contrato entre ListOfferInteractorImpl y ListOfferPresenter
 */
public interface ListOfferInteractor {

    void load();
    void load(int importance);

    interface OnOffersLoadedListener {
        void showListOffers(ArrayList<Offer> offers);
    }
}
