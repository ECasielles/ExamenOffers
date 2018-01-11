package com.example.usuario.examenecasielles.ui.presenter;

import com.example.usuario.examenecasielles.data.db.model.Offer;
import com.example.usuario.examenecasielles.ui.contract.ListOfferContract;
import com.example.usuario.examenecasielles.ui.interactor.ListOfferInteractor;
import com.example.usuario.examenecasielles.ui.interactor.ListOfferInteractorImpl;

import java.util.ArrayList;

/**
 * Presenter de la clase ListOfferFragment
 */
public class ListOfferPresenter implements ListOfferContract.Presenter, ListOfferInteractor.OnOffersLoadedListener {

    private ListOfferInteractor interactor;
    private ListOfferContract.View view;

    public ListOfferPresenter(ListOfferContract.View view) {
        this.view = view;
        this.interactor = new ListOfferInteractorImpl(this);
    }

    @Override
    public void loadOffers() {
        interactor.load();
    }


    @Override
    public void loadOffers(int importance) {
        interactor.load(importance);
    }

    @Override
    public void showListOffers(ArrayList<Offer> offers) {
        view.showOffers(offers);
    }
}
