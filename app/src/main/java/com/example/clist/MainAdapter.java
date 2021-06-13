package com.example.clist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.myViewHolder> {
    Button btn;

    public Context context;

    public MainAdapter(ArrayList<ContactModel> arrayList, Context context) {
       this.arrayList=arrayList;
        this.context = context;
    }

    Activity activity;
    ArrayList<ContactModel> arrayList;
    public MainAdapter(Activity activity,ArrayList<ContactModel>arrayList){
      this.activity=activity;
    this.arrayList=arrayList;
    notifyDataSetChanged();
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.myViewHolder holder, int position) {

        ContactModel model =arrayList.get(position);

        holder.tvName.setText(model.getName());
        holder.tvNumber.setText(model.getNumber());
        holder.tvbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactModel temp = arrayList.get(position);
                Intent j = new Intent(Intent.ACTION_CALL);
                String s = "tel:" + temp.getNumber();

                j.setData(Uri.parse(s));

                j.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(j);
            }
        });

    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvNumber;
Button tvbtn;

        public myViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.contact_name);
            tvNumber = itemView.findViewById(R.id.contact_number);
            tvbtn= itemView.findViewById(R.id.btn);
        }
    }

}