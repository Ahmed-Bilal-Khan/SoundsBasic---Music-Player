package com.example.soundsbasic.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soundsbasic.Models.Settings;
import com.example.soundsbasic.R;

import java.util.List;

public class SettingsRAdapter extends RecyclerView.Adapter<SettingsRAdapter.ViewHolder> {
    Context context;
    List<Settings>settingsList;

    public SettingsRAdapter() {

    }

    public SettingsRAdapter(Context context, List<Settings> settingsList) {
        this.context = context;
        this.settingsList = settingsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int resource = settingsList.get(position).getImgv1();
        String itemname = settingsList.get(position).getItemname1();
        String itemname1 = settingsList.get(position).getItemname2();
        holder.setData(resource,itemname,itemname1);
    }

    @Override
    public int getItemCount() {
        return settingsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtvsettingsitem,txtvsettingsitem2;
        ImageView imgvlogo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtvsettingsitem = itemView.findViewById(R.id.itemsettingstxtv);
            txtvsettingsitem2 = itemView.findViewById(R.id.itemsettingstxtv2);
            imgvlogo = itemView.findViewById(R.id.imgvsettingitem);
        }

        public void setData(int resource, String itemname1,String itemname2) {
            imgvlogo.setImageResource(resource);
            txtvsettingsitem.setText(itemname1);
            txtvsettingsitem2.setText(itemname2);
        }
    }
}
