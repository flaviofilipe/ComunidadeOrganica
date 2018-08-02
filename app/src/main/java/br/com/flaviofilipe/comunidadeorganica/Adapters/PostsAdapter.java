package br.com.flaviofilipe.comunidadeorganica.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.flaviofilipe.comunidadeorganica.Holder.PostsHolder;
import br.com.flaviofilipe.comunidadeorganica.PostsModel;
import br.com.flaviofilipe.comunidadeorganica.R;

public class PostsAdapter extends RecyclerView.Adapter<PostsHolder> {
    private final List<PostsModel> mPosts;

    public PostsAdapter(ArrayList posts) {
        mPosts = posts;
    }

    @Override
    public PostsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Retorna o layout criado pelo ViewHolder
        return new PostsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_posts, parent, false));
    }

    @Override
    public void onBindViewHolder(PostsHolder holder, final int position) {
        //Recebe ViewHolder e a posição da lista. Aqui é recuperado o objeto da lista de Objetos pela posição e associado à ViewHolder.
        holder.title.setText(String.format(Locale.getDefault(), "%s",
                mPosts.get(position).getTitle()
        ));

        holder.description.setText(String.format(Locale.getDefault(), "%s",
                mPosts.get(position).getDescription()
        ));

        holder.launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),mPosts.get(position).getLink(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        //Método que deverá retornar quantos itens há na lista.
        return mPosts != null ? mPosts.size() : 0;
    }


    public void updateList(PostsModel post) {
        insertItem(post);
    }

    // Método responsável por inserir um novo usuário na lista e notificar que há novos itens.
    private void insertItem(PostsModel post) {
        mPosts.add(post);
        notifyItemInserted(getItemCount());
    }

}
