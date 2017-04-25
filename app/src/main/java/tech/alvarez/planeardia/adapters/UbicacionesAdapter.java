package tech.alvarez.planeardia.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import tech.alvarez.planeardia.R;
import tech.alvarez.planeardia.model.Lugar;

public class UbicacionesAdapter extends ArrayAdapter<Lugar> {

    public UbicacionesAdapter(Context context, List<Lugar> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_lugar, parent, false);
        }

        TextView nombreTextView = (TextView) convertView.findViewById(R.id.nombreTextView);
        TextView descripcionTextView = (TextView) convertView.findViewById(R.id.descripcionTextView);
        TextView telefonoTextView = (TextView) convertView.findViewById(R.id.telefonoTextView);
        TextView websiteTextView = (TextView) convertView.findViewById(R.id.websiteTextView);

        Lugar u = getItem(position);

        nombreTextView.setText(u.getNombre());
        descripcionTextView.setText(u.getDireccion());
        telefonoTextView.setText(u.getTelefono());
        websiteTextView.setText(u.getWebsite());

        return convertView;
    }
}
