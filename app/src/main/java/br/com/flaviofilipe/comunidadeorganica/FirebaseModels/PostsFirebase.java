package br.com.flaviofilipe.comunidadeorganica.FirebaseModels;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import br.com.flaviofilipe.comunidadeorganica.Holder.PostsHolder;
import br.com.flaviofilipe.comunidadeorganica.Posts;
import br.com.flaviofilipe.comunidadeorganica.R;

public class PostsFirebase {


    FirebaseDatabase database;
    DatabaseReference ref;
    Context context;
    RecyclerView rv;

    public PostsFirebase(Context contex, RecyclerView rv) {
        this.context = contex;
        this.rv = rv;

        database = FirebaseDatabase.getInstance();
    }



    public void getPostsFirebase() {
        ref = database.getReference().child("POSTS");
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("POSTS");
        FirebaseRecyclerOptions<Posts> options =
                new FirebaseRecyclerOptions.Builder<Posts>()
                        .setQuery(query, Posts.class)
                        .build();
        FirebaseRecyclerAdapter<Posts, PostsHolder> adapter = new FirebaseRecyclerAdapter<Posts, PostsHolder>(options) {

            @Override
            public PostsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                //Captura o layout da lista
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_posts, parent, false);
                return new PostsHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull PostsHolder holder, int position, @NonNull final Posts postsModel) {

                //Insere os valores na nosta
                holder.setTitle(postsModel.getTitle());
                holder.setDescription(postsModel.getDescription());
                holder.setData(postsModel.getDate());
                holder.launchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,postsModel.getLink(),Toast.LENGTH_SHORT).show();
                        browseTo(postsModel.getLink());
                    }
                });

            }

        };

        //Exibindo o resultado do firebase no recycler view
        rv.setAdapter(adapter);
        adapter.startListening();

    }

    //Abre link exterior
    private void browseTo(String url){

        if (!url.startsWith("http://") && !url.startsWith("https://")){
            url = "http://" + url;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
    }



}
