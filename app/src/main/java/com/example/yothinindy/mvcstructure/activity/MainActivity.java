package com.example.yothinindy.mvcstructure.activity;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yothinindy.mvcstructure.R;
import com.example.yothinindy.mvcstructure.fragment.MainFragment;
import com.example.yothinindy.mvcstructure.fragment.SecondFragment;
import com.example.yothinindy.mvcstructure.util.ScreenUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int ScreenWidth = ScreenUtil.getInstance().getScreenWidth();
        int ScreenHeight = ScreenUtil.getInstance().getScreenHeight();


        Toast.makeText(MainActivity.this, ScreenWidth+"  "+ScreenHeight, Toast.LENGTH_LONG).show();

        if (Build.VERSION.SDK_INT >= 21){

        }else{

        }

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer,
                            MainFragment.newInstance(123),
                            "MainFragment")
                    .commit();

            SecondFragment secondFragment = SecondFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer,
                            secondFragment,
                            "SecondFragment")
                    .detach(secondFragment)
                    .commit();


        }

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (savedInstanceState == null){
            MainFragment fragment = (MainFragment)
                    getSupportFragmentManager().findFragmentByTag("MainFragment");
            fragment.setHelloText("Woo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\nWoo Hooooooooo\n");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case R.id.action_first_tab:{
               MainFragment mainFragment = (MainFragment)
                       getSupportFragmentManager().findFragmentByTag("MainFragment");
               SecondFragment secondFragment =(SecondFragment)
                       getSupportFragmentManager().findFragmentByTag("SecondFragment");
               getSupportFragmentManager().beginTransaction()
                       .attach(mainFragment)
                       .detach(secondFragment)
                       .commit();
               return true;

           }
           case R.id.action_second_tab:{
               MainFragment mainFragment = (MainFragment)
                       getSupportFragmentManager().findFragmentByTag("MainFragment");
               SecondFragment secondFragment =(SecondFragment)
                       getSupportFragmentManager().findFragmentByTag("SecondFragment");
               getSupportFragmentManager().beginTransaction()
                       .attach(secondFragment)
                       .detach(mainFragment)
                       .commit();
               return true;

           }
           case R.id.action_second_fragment: {
               Fragment fragment = getSupportFragmentManager()
                       .findFragmentById(R.id.contentContainer);

               if (fragment instanceof SecondFragment == false) {
                   getSupportFragmentManager().beginTransaction()
                           .setCustomAnimations(
                                   R.anim.from_right, R.anim.to_left,
                                   R.anim.from_left, R.anim.to_right
                           )
                           .replace(R.id.contentContainer, SecondFragment.newInstance())
                           .addToBackStack(null)
                           .commit();
               }
               Toast.makeText(MainActivity.this,
                       "Second Fragment",
                       Toast.LENGTH_LONG
               )
                       .show();
               return true;
           }
           default:
               return super.onOptionsItemSelected(item);
       }
    }
}
