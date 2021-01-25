package co.id.dicoding.consumerapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import co.id.dicoding.consumerapp.CustomOnItemClickListener;
import co.id.dicoding.consumerapp.FormActivity;
import co.id.dicoding.consumerapp.R;
import co.id.dicoding.consumerapp.entity.NoteItem;

import static co.id.dicoding.consumerapp.db.DatabaseContract.NoteColumns.CONTENT_URI;

public class ConsumerAdapter extends RecyclerView.Adapter<ConsumerAdapter.NoteViewHolder> {

    private final ArrayList<NoteItem> listNotes = new ArrayList<>();
    private final Activity activity;

    public ConsumerAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<NoteItem> getListNotes() {
        return listNotes;
    }

    public void setListNotes(ArrayList<NoteItem> listNotes) {
        this.listNotes.clear();
        this.listNotes.addAll(listNotes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_consumer_note, viewGroup, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int i) {
        holder.tvTitle.setText(getListNotes().get(i).getTitle());
        holder.tvDate.setText(getListNotes().get(i).getDate());
        holder.tvDescription.setText(getListNotes().get(i).getDescription());
        holder.itemView.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, FormActivity.class);
                Uri uri = Uri.parse(CONTENT_URI + "/" + getListNotes().get(position).getId());
                intent.setData(uri);
                activity.startActivity(intent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle, tvDescription, tvDate;
        View rootView;
        public NoteViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
        }
    }
}
