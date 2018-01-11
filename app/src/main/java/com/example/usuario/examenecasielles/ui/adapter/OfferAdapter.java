package com.example.usuario.examenecasielles.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.examenecasielles.R;
import com.example.usuario.examenecasielles.data.db.model.Offer;

import java.util.ArrayList;

/**
 * Adapter de ListOfferFragment
 * @author Enrique Casielles
 * @see com.example.usuario.examenecasielles.ui.fragment.ListOfferFragment
 * @see ArrayAdapter
 * @see Offer
 */
public class OfferAdapter extends ArrayAdapter<Offer> {
    public OfferAdapter(@NonNull Context context) {
        super(context, R.layout.fragment_list_offers, new ArrayList<Offer>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View root = convertView;
        OfferHolder holder;

        if(root == null) {
            root = LayoutInflater.from(getContext()).inflate(R.layout.item_offer, parent, false);
            holder = new OfferHolder();

            holder.txvName = root.findViewById(R.id.txvName);
            holder.txvShop = root.findViewById(R.id.txvShop);
            holder.txvDate = root.findViewById(R.id.txvDate);
            holder.imvIcon = root.findViewById(R.id.imvIcon);
            root.setTag(holder);
        } else
            holder = (OfferHolder) root.getTag();

        holder.txvName.setText(getItem(position).getName());
        holder.txvShop.setText(getItem(position).getShop());
        holder.txvDate.setText(getItem(position).getDate());
        holder.imvIcon.setImageResource(getItem(position).getIdResImg());
        switch (getItem(position).getImportance()) {
            case Offer.Importance.NOT_IMPORTANT:
                root.setBackgroundColor(getContext().getResources().getColor(R.color.color_unimportant));
                break;
            case Offer.Importance.DEFAULT:
                root.setBackgroundColor(getContext().getResources().getColor(R.color.color_default));
                break;
            case Offer.Importance.IMPORTANT:
                root.setBackgroundColor(getContext().getResources().getColor(R.color.color_important));
                break;
        }
        return root;
    }

    class OfferHolder {
        TextView txvName, txvShop, txvDate;
        ImageView imvIcon;
    }
}
