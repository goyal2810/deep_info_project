package com.example.deepinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>  {

    LayoutInflater inflater;
    List<SongsList> songs;

    public MusicAdapter(Context context, List<SongsList> songs){
        this.inflater = LayoutInflater.from(context);
        this.songs = songs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_song_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.songTitle.setText(songs.get(position).getTitle());
        holder.songArtist.setText(songs.get(position).getArtist());
        Picasso.get().load(songs.get(position).getCoverImage()).into(holder.songCoverImage);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public void deleteData(int arraySize){
        songs.clear();
        notifyItemRangeRemoved(0, arraySize);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView songTitle, songArtist;
        ImageView songCoverImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            songTitle = itemView.findViewById(R.id.songTitle);
            songArtist = itemView.findViewById(R.id.songArtist);
            songCoverImage = itemView.findViewById(R.id.coverImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Still working on it",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
