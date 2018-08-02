package br.com.flaviofilipe.comunidadeorganica.Holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import br.com.flaviofilipe.comunidadeorganica.R;

public class PostsHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView description;
    public TextView data;
    public ImageButton launchButton;

    public PostsHolder(View itemView) {
        //Pega itens do layout
        super(itemView);
        title = itemView.findViewById(R.id.post_title);
        description = itemView.findViewById(R.id.post_description);
        data = itemView.findViewById(R.id.post_date);
        launchButton = itemView.findViewById(R.id.post_launch);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setData(String data) {
        this.data.setText(data);
    }
}
