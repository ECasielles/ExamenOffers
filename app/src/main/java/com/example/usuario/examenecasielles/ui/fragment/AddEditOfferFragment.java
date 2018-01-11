package com.example.usuario.examenecasielles.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.usuario.examenecasielles.R;
import com.example.usuario.examenecasielles.ui.contract.AddEditOfferContract;
import com.example.usuario.examenecasielles.ui.presenter.AddEditOfferPresenter;

/**
 * Fragment que permite añadir o editar una oferta
 */
public class AddEditOfferFragment extends Fragment implements AddEditOfferContract.View {
    public static final String TAG = "AddEditOfferFragment";
    private OnLoadListener callback;
    private AddEditOfferContract.Presenter presenter;
    private EditText edtName, edtShop, edtDate;
    private Spinner spnType, spnImportance;
    private FloatingActionButton floatingActionButton;
    private Toolbar toolbar;

    public static AddEditOfferFragment getInstance(Bundle args) {
        AddEditOfferFragment addEditOfferFragment = new AddEditOfferFragment();
        if(args != null)
            addEditOfferFragment.setArguments(args);
        return addEditOfferFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (AddEditOfferFragment.OnLoadListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity + " must implement OnAddNewListener interface");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = new AddEditOfferPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_edit_offers, container, false);
        edtName = view.findViewById(R.id.edtName);
        edtShop = view.findViewById(R.id.edtShop);
        edtDate = view.findViewById(R.id.edtDate);
        spnType = view.findViewById(R.id.spnIcon);
        spnImportance = view.findViewById(R.id.spnImportance);
        toolbar = view.findViewById(R.id.toolbar);
        floatingActionButton = view.findViewById(R.id.fab);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        //FloatingActionButton comprueba y añade
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addOffer(
                        edtShop.getText().toString(),
                        edtDate.getText().toString(),
                        edtName.getText().toString(),
                        spnType.getSelectedItemPosition(),
                        spnImportance.getSelectedItemPosition()
                );
            }
        });
    }

    //COMUNICACION CON EL PRESENTER
    @Override
    public void reload() {
        callback.loadOfferList();
    }

    @Override
    public void onEmptyFieldError() {
        Snackbar.make(getView(), getResources().getString(R.string.error_empty_field), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onExistsError() {
        Snackbar.make(getView(), getResources().getString(R.string.error_offer_exists), Snackbar.LENGTH_SHORT).show();
    }

    /**
     * Contrato con la Activity
     */
    public interface OnLoadListener {
        void loadOfferList();
    }
}
