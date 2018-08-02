package br.com.flaviofilipe.comunidadeorganica.Fragments;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import br.com.flaviofilipe.comunidadeorganica.Adapters.PostsAdapter;
import br.com.flaviofilipe.comunidadeorganica.Holder.PostsHolder;
import br.com.flaviofilipe.comunidadeorganica.PostsModel;
import br.com.flaviofilipe.comunidadeorganica.R;

public class PostsFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference ref;
    RecyclerView rv;

    //Tela do fragment 1
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        // Configurando um dividr entre linhas, para uma melhor visualização.
        rv.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));


        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        database = FirebaseDatabase.getInstance();

        return rv;

    }

    @Override
    public void onStart() {
        super.onStart();
        getPostsFirebase();

    }


    public void getPostsFirebase() {
        ref = database.getReference().child("POSTS");
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("POSTS");
        FirebaseRecyclerOptions<PostsModel> options =
                new FirebaseRecyclerOptions.Builder<PostsModel>()
                        .setQuery(query, PostsModel.class)
                        .build();
        FirebaseRecyclerAdapter<PostsModel, PostsHolder> adapter = new FirebaseRecyclerAdapter<PostsModel, PostsHolder>(options) {

            @Override
            public PostsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                //Captura o layout da lista
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_posts, parent, false);
                return new PostsHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull PostsHolder holder, int position, @NonNull final PostsModel postsModel) {

                //Insere os valores na nosta
                holder.setTitle(postsModel.getTitle());
                holder.setDescription(postsModel.getDescription());
                holder.setData(postsModel.getDate());
                holder.launchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),postsModel.getLink(),Toast.LENGTH_SHORT).show();
                    }
                });

            }

        };

        //Exibindo o resultado do firebase no recycler view
        rv.setAdapter(adapter);
        adapter.startListening();

    }

}

