package br.com.flaviofilipe.comunidadeorganica.Fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.flaviofilipe.comunidadeorganica.Adapters.PostsAdapter;
import br.com.flaviofilipe.comunidadeorganica.PostsModel;

public class PostsFragment extends Fragment {

    private PostsAdapter mAdapter;

    //Tela do fragment 1
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new PostsAdapter(new ArrayList<>(0));
        rv.setAdapter(mAdapter);


        // Configurando um dividr entre linhas, para uma melhor visualização.
        rv.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return rv;

    }

    @Override
    public void onStart() {
        super.onStart();
        PostsModel post1 = new PostsModel("Ttitulo da noticia","Descição da noticia com algumas palavras Descição da noticia com algumas palavras Descição da noticia com algumas palavras","18/07/2018"); //Cria user
        mAdapter.updateList(post1); //Insere na lista
        mAdapter.updateList(post1); //Insere na lista
        mAdapter.updateList(post1); //Insere na lista
        mAdapter.updateList(post1); //Insere na lista
        mAdapter.updateList(post1); //Insere na lista

    }

}

