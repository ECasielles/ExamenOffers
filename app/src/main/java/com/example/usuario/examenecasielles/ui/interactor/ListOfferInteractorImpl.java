package com.example.usuario.examenecasielles.ui.interactor;

import com.example.usuario.examenecasielles.data.db.model.Offer;
import com.example.usuario.examenecasielles.data.db.repository.OfferRepository;

/**
 * Interactor de la clase ListOfferPresenter
 */
public class ListOfferInteractorImpl implements ListOfferInteractor {

    private final OnOffersLoadedListener listener;

    public ListOfferInteractorImpl(OnOffersLoadedListener listener) {
        this.listener = listener;
    }

    @Override
    public void load() {
        listener.showListOffers(OfferRepository.getInstance().getOffers());
    }

    @Override
    public void load(int importance) {
        listener.showListOffers(OfferRepository.getInstance().getOffers(importance));
    }
}
