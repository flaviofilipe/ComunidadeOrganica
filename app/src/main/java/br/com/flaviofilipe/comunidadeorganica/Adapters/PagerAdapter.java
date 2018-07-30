package br.com.flaviofilipe.comunidadeorganica.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import br.com.flaviofilipe.comunidadeorganica.Fragments.PostsFragment;
import br.com.flaviofilipe.comunidadeorganica.Fragments.MapFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private int mNumofTabs;
    public PagerAdapter(FragmentManager fm, int mNumofTabs) {
        super(fm);
        this.mNumofTabs = mNumofTabs;
    }

    //Captura o tem selecionado no menu
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                PostsFragment tab1 = new PostsFragment();
                Log.i("Frag 1", "Frag 1 ok");
                return tab1;
            case 1:
                MapFragment tab2 = new MapFragment();
                Log.i("Frag 2", "Frag 2 ok");
                return tab2;
            default:
                Log.i("Default", "Return Default");
                return null;
        }
    }

    @Override
    public int getCount() {
        //Retorna a posiçoã do item
        return mNumofTabs;
    }
}