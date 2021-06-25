package com.example.soundsbasic.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soundsbasic.Models.Settings;
import com.example.soundsbasic.R;

import java.util.List;

public class SettingsRAdapter extends RecyclerView.Adapter<SettingsRAdapter.ViewHolder> {
    private final Context context;
    List<Settings>settingsList;
   /* private RecyclerViewOnClickListener listener;*/

    public SettingsRAdapter(Context context) {

        this.context = context;
    }

    public SettingsRAdapter(Context context, List<Settings> settingsList /*,RecyclerViewOnClickListener listener*/) {
        this.context = context;
        this.settingsList = settingsList;
     /*   this.listener = listener;*/
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

    /*public interface RecyclerViewOnClickListener
    {
        void OnClick(View v,int position);
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        RelativeLayout parentlayout;
        TextView txtvsettingsitem,txtvsettingsitem2;
        ImageView imgvlogo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtvsettingsitem = itemView.findViewById(R.id.itemsettingstxtv);
            txtvsettingsitem2 = itemView.findViewById(R.id.itemsettingstxtv2);
            imgvlogo = itemView.findViewById(R.id.imgvsettingitem);
            parentlayout = itemView.findViewById(R.id.relativesettings);
            itemView.setOnClickListener(this);
        }

        public void setData(int resource, String itemname1,String itemname2) {
            imgvlogo.setImageResource(resource);
            txtvsettingsitem.setText(itemname1);
            txtvsettingsitem2.setText(itemname2);
        }

        @Override
        public void onClick(View v) {
            /*final Intent intent;
            switch (getAdapterPostion()){
                case 0:
                    intent =  new Intent(context, faqactivi.class);
                    break;
                case 1:
                    intent =  new Intent(context, SecondActivity.class);
                    break;
                default:
                    intent =  new Intent(context, DefaultActivity.class);
                    break;
            }
            context.startActivity(intent);*/
        }
    }
}
