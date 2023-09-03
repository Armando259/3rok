package com.example.a12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.content.Intent;

import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.a12.R;

import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> {

    private Context context;
    private List<ImageItem> imageList;

    public ImageListAdapter(Context context, List<ImageItem> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        ImageItem currentItem = imageList.get(position);

        // Postavljanje slike iz drawable resursa
        holder.imageView.setImageResource(currentItem.getImageResource());
        holder.titleTextView.setText(currentItem.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                switch (holder.getAdapterPosition()) {
                    case 0:
                        intent = new Intent(view.getContext(), biftek.class); // Promijenite aktivnost
                        break;
                    case 1:
                        intent = new Intent(view.getContext(), biftek.class); // Promijenite aktivnost
                        break;
                    case 2:
                        intent = new Intent(view.getContext(), biftek.class); // Promijenite aktivnost
                        break;
                    default:
                        // U slučaju da se ne pronađe odgovarajuća slika, neka se ništa ne dogodi
                        return;
                }
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView titleTextView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
        }
    }
}
