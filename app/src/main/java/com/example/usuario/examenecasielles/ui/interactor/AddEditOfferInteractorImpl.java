package com.example.usuario.examenecasielles.ui.interactor;

import com.example.usuario.examenecasielles.R;
import com.example.usuario.examenecasielles.data.db.model.Offer;
import com.example.usuario.examenecasielles.data.db.repository.OfferRepository;

/**
 * Created by usuario on 11/01/18.
 */

public class AddEditOfferInteractorImpl implements AddEditOfferInteractor {

    private final OnOfferAddEditListener listener;

    public AddEditOfferInteractorImpl(AddEditOfferInteractor.OnOfferAddEditListener listener) {
        this.listener = listener;
    }

    @Override
    public void addNewOffer(String name, String shop, String date, int type, int importance) {
        if(name.isEmpty() || shop.isEmpty() || date.isEmpty())
            listener.emptyFieldError();
        else {
            int typeRes = 0;
            switch (type) {
                case 0:
                    typeRes = R.drawable.ic_home_black_24dp;
                    break;
                case 1:
                    typeRes = R.drawable.ic_devices_black_24dp;
                    break;
                case 2:
                    typeRes = R.drawable.ic_pool_black_24dp;
                    break;
            }
            if(OfferRepository.getInstance().addOffer(
                    new Offer(-1, typeRes, name, shop, date, importance)))
                listener.reloadOffers();
            else
                listener.offerExistsError();
        }
    }
}
