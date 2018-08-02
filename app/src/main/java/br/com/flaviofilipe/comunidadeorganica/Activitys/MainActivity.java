package br.com.flaviofilipe.comunidadeorganica.Activitys;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.flaviofilipe.comunidadeorganica.Adapters.PagerAdapter;
import br.com.flaviofilipe.comunidadeorganica.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings();
        setTabLayout();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void settings(){

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        requestPermission();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setTabLayout(){
        //TabLayout
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("POSTS"));
        tabLayout.addTab(tabLayout.newTab().setText("ENCONTRAR"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //ViewPage - Adapter/PagerAdapter
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    //Permissão para acessar a localização
    public void requestPermission(){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Log.i("PERMISSAO", "ENTRA NA FUNCAO");

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Permissão de localização");
            builder.setMessage("Você precisa permitir a utilização da sua localização para o funcionamento da aplicação");
            builder.setPositiveButton("Aceito",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Should we show an explanation?
                            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                                Log.i("PERMISSAO", "ESTA AQUI - IF");

                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        1);
                            }
                        }
                    });
            builder.setNegativeButton("Não aceito", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    requestPermission();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();


        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.i("grantResults", String.valueOf(grantResults[0]));
        if(grantResults[0] != 0){
            requestPermission();
        }else{
            Toast.makeText(this,"Aceito", Toast.LENGTH_SHORT);
            Log.i("Aceito", "SUCESSO!!");
            Intent i = new Intent(this, MainActivity.class);
            finish();
            startActivity(i);
        }

    }

}
