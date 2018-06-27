package in.syncro.grabit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import in.syncro.grabit.R;
import in.syncro.grabit.db.local.ObjPojo;
import in.syncro.grabit.listeners.ActivateClickListener;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<ObjPojo> list;
    Context context;
    ActivateClickListener listener;
    boolean isVertical;


    public RecyclerAdapter(List<ObjPojo> list,
                           Context context,
                           ActivateClickListener listener, boolean isVertical) {
        this.list = list;
        this.context = context;
        this.listener = listener;
        this.isVertical = isVertical;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (isVertical) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item_large, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, final int position) {
        final ObjPojo pojo = list.get(position);
        Glide.with(context)
                .load(pojo.getImage())
                .into(h.logo);

        h.title.setText(pojo.getTitle());
        h.amount.setText(pojo.getAmount());

        h.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onActivateClicked(pojo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        TextView title, amount;
        LinearLayout main;

        public ViewHolder(View v) {
            super(v);
            main = v.findViewById(R.id.main);
            logo = v.findViewById(R.id.logo);
            title = v.findViewById(R.id.title);
            amount = v.findViewById(R.id.amount);
        }
    }
}
