package com.tejaswininimbalkar.hellohomeoassesment.LocalDB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tejaswininimbalkar.hellohomeoassesment.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MemberViewHolder> {

    private Context context;
    private List<CrewMembersEntity> membersEntities;

    public MembersAdapter(Context context, List<CrewMembersEntity> membersEntities) {
        this.context = context;
        this.membersEntities = membersEntities;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crew_member_card, parent, false);
        MemberViewHolder holder = new MemberViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MembersAdapter.MemberViewHolder holder, int position) {
        holder.name.setText(membersEntities.get(position).name);
        holder.agency.setText(membersEntities.get(position).agency);
        holder.status.setText(membersEntities.get(position).status);
        holder.wikipedia.setText(membersEntities.get(position).wikipedia);
        //holder.name.setText(membersEntities.get(position).name);
    }

    @Override
    public int getItemCount() {
        return this.membersEntities.size();
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
