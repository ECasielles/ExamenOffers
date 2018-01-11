package com.example.usuario.examenecasielles.data.db.repository;

import com.example.usuario.examenecasielles.R;
import com.example.usuario.examenecasielles.data.db.model.Offer;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Repositorio de Ofertas de la aplicación
 */
public class OfferRepository {

    public static OfferRepository repository;
    private ArrayList<Offer> offers;

    static {
        repository = new OfferRepository();
    }

    private OfferRepository() {
        init();
    }

    public static OfferRepository getInstance() {
        if(repository == null)
            repository = new OfferRepository();
        return repository;
    }

    private void init() {
        Date today = new Date();
        offers = new ArrayList<>();
        offers.add(new Offer(offers.size(), R.drawable.ic_pool_black_24dp, "Bici Paseo", "Decathlon",
                (today.getDay() + 1) + "/" + (today.getMonth() + 1) + "/" + (today.getYear() + 1900), Offer.Importance.NOT_IMPORTANT));
        offers.add(new Offer(offers.size(), R.drawable.ic_devices_black_24dp, "LG3", "Mediamarkt",
                (today.getDay() + 1) + "/" + (today.getMonth() + 1) + "/" + (today.getYear() + 1900), Offer.Importance.DEFAULT));
        offers.add(new Offer(offers.size(), R.drawable.ic_home_black_24dp, "Vajilla Murano", "Corte Inglés",
                (today.getDay() + 1) + "/" + (today.getMonth() + 1) + "/" + (today.getYear() + 1900), Offer.Importance.IMPORTANT));
        offers.add(new Offer(offers.size(), R.drawable.ic_pool_black_24dp, "Chándal Adidas", "Décimas",
                (today.getDay() + 1) + "/" + (today.getMonth() + 1) + "/" + (today.getYear() + 1900), Offer.Importance.DEFAULT));
        offers.add(new Offer(offers.size(), R.drawable.ic_devices_black_24dp, "Samsung Galaxy Note 7", "Saturn",
                (today.getDay() + 1) + "/" + (today.getMonth() + 1) + "/" + (today.getYear() + 1900), Offer.Importance.NOT_IMPORTANT));
        offers.add(new Offer(offers.size(), R.drawable.ic_home_black_24dp, "Sofá", "Sumobel",
                (today.getDay() + 1) + "/" + (today.getMonth() + 1) + "/" + (today.getYear() + 1900), Offer.Importance.NOT_IMPORTANT));
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public boolean addOffer(Offer offer) {
        boolean result = true;
        Iterator<Offer> iterator = offers.iterator();
        while (iterator.hasNext()) {
            if (offer.getName() == iterator.next().getName()) {
                result = false;
                break;
            }
        }
        if(result)
            offers.add(offer);
        return result;
    }

    public void deleteOffer(Offer offer) {
        Iterator<Offer> iterator = offers.iterator();
        while (iterator.hasNext())
            if(offer.getId() == iterator.next().getId())
                iterator.remove();
    }


    public ArrayList<Offer> getOffers(int importance) {
        ArrayList<Offer> result = new ArrayList<>();
        Iterator<Offer> iterator = offers.iterator();
        while (iterator.hasNext()) {
            Offer temp = iterator.next();
            if(temp.getImportance() == importance)
                result.add(temp);
        }
        return result;
    }
}
