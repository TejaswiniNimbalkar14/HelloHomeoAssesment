package com.tejaswininimbalkar.hellohomeoassesment.ServerData;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tejaswininimbalkar.hellohomeoassesment.LocalDB.AppDatabase;
import com.tejaswininimbalkar.hellohomeoassesment.LocalDB.CrewMembersEntity;
import com.tejaswininimbalkar.hellohomeoassesment.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CrewMembersAdapter extends RecyclerView.Adapter<CrewMembersAdapter.MemberViewHolder> {

    private List<CrewMembers> membersList;
    private Context context;

    public CrewMembersAdapter(List<CrewMembers> membersList, Context context) {
        this.membersList = membersList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crew_member_card, parent, false);
        MemberViewHolder holder = new MemberViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull CrewMembersAdapter.MemberViewHolder holder, int position) {
        Glide.with(holder.image.getContext()).load(membersList.get(position).getImage()).into(holder.image);
        holder.name.setText(membersList.get(position).getName());
        holder.agency.setText(membersList.get(position).getAgency());
        holder.wikipedia.setText(membersList.get(position).getWikipedia());
        holder.status.setText(membersList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return this.membersList.size();
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, agency, wikipedia, status;

        public MemberViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.crew_member_image);
            name = itemView.findViewById(R.id.crew_member_name);
            agency = itemView.findViewById(R.id.crew_member_agency);
            wikipedia = itemView.findViewById(R.id.crew_member_wikipedia);
            status = itemView.findViewById(R.id.crew_member_status);
        }
    }
}
