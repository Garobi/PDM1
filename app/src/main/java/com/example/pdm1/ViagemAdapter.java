package com.example.pdm1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ViagemAdapter extends ArrayAdapter<Viagem> {

    public ViagemAdapter(Context context, List<Viagem> viagens){
        super(context, 0, viagens);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Viagem viagem = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.viagem_cell, parent, false);
        }

        TextView title = convertView.findViewById(R.id.cellTitle);

        title.setText(viagem.getTitle());

        return convertView;
    }
}
