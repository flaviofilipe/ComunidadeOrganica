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
import br.com.flaviofilipe.comunidadeorganica.FirebaseModels.PostsFirebase;

public class PostsFragment extends Fragment {

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

        return rv;
    }

    @Override
    public void onStart() {
        super.onStart();
        PostsFirebase posts = new PostsFirebase(getContext(), rv);
        posts.getPostsFirebase();

    }

}

