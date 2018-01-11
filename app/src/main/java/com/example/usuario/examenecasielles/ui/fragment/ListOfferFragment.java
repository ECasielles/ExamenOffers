package com.example.usuario.examenecasielles.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.usuario.examenecasielles.R;
import com.example.usuario.examenecasielles.data.db.model.Offer;
import com.example.usuario.examenecasielles.ui.adapter.OfferAdapter;
import com.example.usuario.examenecasielles.ui.contract.ListOfferContract;
import com.example.usuario.examenecasielles.ui.presenter.ListOfferPresenter;

import java.util.ArrayList;

/**
 * Fragment que contiene la lista de ofertas
 */
public class ListOfferFragment extends ListFragment implements ListOfferContract.View {
    public static final String TAG = "ListOfferFragment";
    OnAddNewListener callback;
    OfferAdapter adapter;
    ListOfferContract.Presenter presenter;
    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;

    //CONSTRUCTOR ESTATICO
    public static ListOfferFragment getInstance() {
        return new ListOfferFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (OnAddNewListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity + " must implement OnAddNewListener interface");
        }
    }

    //CARGA LA VISTA
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        adapter = new OfferAdapter(getActivity());
        presenter = new ListOfferPresenter(this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Infla la vista
        View view = inflater.inflate(R.layout.fragment_list_offers, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        floatingActionButton = view.findViewById(R.id.fab);

        //Presenter carga los datos en adapter
        presenter.loadOffers();
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        //Adapter
        getListView().setAdapter(adapter);
        //Menu contextual
        registerForContextMenu(getListView());

        //Añadir oferta
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.addNew(null);
            }
        });

        /*getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Offer offer = adapterView.getItemAtPosition(i)
                return false;
            }
        });*/
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_offer_filter, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter_unimportant:
                presenter.loadOffers(Offer.Importance.NOT_IMPORTANT);
                break;
            case R.id.action_filter_normal:
                presenter.loadOffers(Offer.Importance.DEFAULT);
                break;
            case R.id.action_filter_important:
                presenter.loadOffers(Offer.Importance.IMPORTANT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Offer offer = (Offer) getListView().getItemAtPosition(info.position);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Eliminar oferta")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Toast.makeText(getContext(), offer.getName(), Toast.LENGTH_SHORT).show();
                        //presenter.deleteOffer(offer);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create()
                .show();
        return super.onContextItemSelected(item);
    }
    //COMUNICACION CON SU PRESENTER
    /**
     * Carga los datos del repositorio en el adapter
     * @param offers
     */
    @Override
    public void showOffers(ArrayList<Offer> offers) {
        adapter.clear();
        adapter.addAll(offers);
    }

    /**
     * Contrato con la Activity
     */
    public interface OnAddNewListener {
        void addNew(Bundle bundle);
    }


}
