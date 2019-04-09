package com.example.schedule;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

private int Res;
private List<User> userList;
public ContactAdapter(ArrayList<User>userList, int res){
    this.userList = userList;
    this.Res = res;

}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(Res, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    User user = userList.get(i);
    viewHolder.TxtName.setText(user.getName());
    viewHolder.TxtLastName.setText(user.getLastName());
    viewHolder.TxtCellphne.setText(user.getCellphone());
    viewHolder.TxtPhone.setText(user.getPhone());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView TxtName, TxtLastName, TxtCellphne, TxtPhone;
        public View view;
        public ViewHolder(View view){
            super(view);
            this.view=view;
            this.TxtName=(TextView)view.findViewById(R.id.TxtNameD);
            this.TxtLastName=(TextView)view.findViewById(R.id.TxtLastNameD);
            this.TxtCellphne=(TextView)view.findViewById(R.id.TxtCellphoneD);
            this.TxtPhone=(TextView)view.findViewById(R.id.TxtPhoneD);

        }
    }

}
