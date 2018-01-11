package com.example.usuario.examenecasielles;

import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.usuario.examenecasielles.ui.fragment.AddEditOfferFragment;
import com.example.usuario.examenecasielles.ui.fragment.ListOfferFragment;
import com.example.usuario.examenecasielles.ui.presenter.AddEditOfferPresenter;
import com.example.usuario.examenecasielles.ui.presenter.ListOfferPresenter;

/**
 * Activity principal de la aplicación
 */
public class MainActivity extends AppCompatActivity implements ListOfferFragment.OnAddNewListener,
        AddEditOfferFragment.OnLoadListener {

    private ListOfferFragment listOfferFragment;
    private AddEditOfferFragment addEditOfferFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfferFragment = (ListOfferFragment) getSupportFragmentManager().findFragmentByTag(ListOfferFragment.TAG);
        if(listOfferFragment == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            listOfferFragment = ListOfferFragment.getInstance();
            fragmentTransaction.add(android.R.id.content, listOfferFragment, ListOfferFragment.TAG);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void addNew(Bundle bundle) {
        addEditOfferFragment = (AddEditOfferFragment) getSupportFragmentManager().findFragmentByTag(AddEditOfferFragment.TAG);
        if(addEditOfferFragment == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            addEditOfferFragment = AddEditOfferFragment.getInstance(bundle);
            fragmentTransaction.addToBackStack(AddEditOfferFragment.TAG);
            fragmentTransaction.replace(android.R.id.content, addEditOfferFragment, AddEditOfferFragment.TAG);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void loadOfferList() {
        getSupportFragmentManager().popBackStack();
        Toast.makeText(this, "Oferta añadida", Toast.LENGTH_SHORT).show();
    }
}
