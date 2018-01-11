package com.example.usuario.examenecasielles.ui.presenter;

import com.example.usuario.examenecasielles.ui.contract.AddEditOfferContract;
import com.example.usuario.examenecasielles.ui.interactor.AddEditOfferInteractor;
import com.example.usuario.examenecasielles.ui.interactor.AddEditOfferInteractorImpl;

/**
 * Created by usuario on 11/01/18.
 */

public class AddEditOfferPresenter implements AddEditOfferContract.Presenter, AddEditOfferInteractor.OnOfferAddEditListener {
    private AddEditOfferInteractor interactor;
    private AddEditOfferContract.View view;

    public AddEditOfferPresenter(AddEditOfferContract.View view) {
        this.view = view;
        this.interactor = new AddEditOfferInteractorImpl(this);
    }

    @Override
    public void reloadOffers() {
        view.reload();
    }

    @Override
    public void emptyFieldError() {
        view.onEmptyFieldError();
    }

    @Override
    public void offerExistsError() {
        view.onExistsError();
    }

    @Override
    public void addOffer(String name, String shop, String date, int type, int importance) {
        interactor.addNewOffer(name, shop, date, type, importance);
    }
}
