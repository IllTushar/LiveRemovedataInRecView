package com.example.interfaceinandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.myViewHolder>
{
    List<BankDetailsResponseModel.Datum>list;
    Context context;
    private  deleteApiCall deleteApiCall;
    private SharedPreference sharedPreference;

    public AdapterClass(List<BankDetailsResponseModel.Datum> list, Context context, deleteApiCall deleteApiCall)
    {
        this.list = list;
        this.context = context;
        this.deleteApiCall = deleteApiCall;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_row_xml,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
      holder.name.setText(list.get(position).getName());
      sharedPreference.saveData("id",list.get(position).getId().toString());
      holder.delete.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              deleteApiCall.deleteApi();
          }
      });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
     ImageView delete;
     TextView name;
    public myViewHolder(@NonNull View itemView) {
        super(itemView);
        name =  itemView.findViewById(R.id.name);
        delete = itemView.findViewById(R.id.delete);
        sharedPreference = new SharedPreference(context);
    }
}



  interface deleteApiCall{
        void deleteApi();
  }
}
