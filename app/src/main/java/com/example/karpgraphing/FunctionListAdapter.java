package com.example.karpgraphing;

import static android.graphics.Color.parseColor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FunctionListAdapter extends RecyclerView.Adapter<FunctionListAdapter.ViewHolder> {

    ArrayList<FunctionDetails> list;
    public FunctionListAdapter (ArrayList<FunctionDetails> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.function_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FunctionDetails pos = list.get(i);

        viewHolder.funcName.setText("y=" + pos.getFun());

        switch(list.get(i).getColor()){
            case 0:
                viewHolder.icon.setImageResource(R.drawable.clr0);
                break;
            case 1:
                viewHolder.icon.setImageResource(R.drawable.clr1);
                break;
            case 2:
                viewHolder.icon.setImageResource(R.drawable.clr2);
                break;
            case 3:
                viewHolder.icon.setImageResource(R.drawable.clr3);
                break;
            case 4:
                viewHolder.icon.setImageResource(R.drawable.clr4);
                break;
            default:
                viewHolder.icon.setImageResource(R.drawable.clr0);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView funcName;
        public final ImageView icon;

        public ViewHolder(View view) {
            super(view);
            funcName = view.findViewById(R.id.funcText);
            icon = view.findViewById(R.id.icon);
        }
    }


}
