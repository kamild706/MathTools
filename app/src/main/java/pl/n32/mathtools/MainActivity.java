package pl.n32.mathtools;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import pl.n32.mathtools.Fragments.*;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setUILanguage();

        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadDefaultFragment(navigationView);
    }

    private void setUILanguage()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String languageToLoad = prefs.getString("lang", null);

        if (languageToLoad != null) {
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);

            Configuration config = new Configuration();
            config.setLocale(locale);

            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }
    }

    private void loadDefaultFragment(NavigationView navigationView)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new DayOfWeek());
        transaction.commit();

        navigationView.getMenu().getItem(0).setChecked(true);
        setTitle(navigationView.getMenu().getItem(0).getTitle());
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent e)
    {
        switch (keycode) {
            case KeyEvent.KEYCODE_MENU:
                if (drawer.isDrawerOpen(GravityCompat.START))
                    drawer.closeDrawer(GravityCompat.START);
                else
                    drawer.openDrawer(GravityCompat.START);
                break;

            case KeyEvent.KEYCODE_BACK:
                if (drawer.isDrawerOpen(GravityCompat.START))
                    drawer.closeDrawer(GravityCompat.START);
                else
                    super.onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        Fragment newFragment = null;

        switch (id) {
            case R.id.nav_calendar:
                newFragment = new DayOfWeek();
                break;
            case R.id.nav_combination:
                newFragment = new Combination();
                break;
            case R.id.nav_permutation:
                newFragment = new Permutation();
                break;
            case R.id.nav_risingFactorial:
                newFragment = new RisingFactorial();
                break;
            case R.id.nav_divisors:
                newFragment = new Divisors();
                break;
            case R.id.nav_factors:
                newFragment = new Factors();
                break;
            case R.id.nav_multiples:
                newFragment = new Multiples();
                break;
            case R.id.nav_gcd:
                newFragment = new GreatestCommonDivisor();
                break;
            case R.id.nav_lcm:
                newFragment = new LeastCommonMultiple();
                break;
            case R.id.nav_sequence:
                newFragment = new ArithmeticSeries();
                break;
            case R.id.nav_primeNumbers:
                newFragment = new PrimeNumbers();
                break;
            case R.id.nav_modularEquation:
                newFragment = new Equation();
                break;
            case R.id.nav_settings:
                startActivity(
                        new Intent(this, SettingsActivity.class)
                );
            default:
                break;
        }

        if (newFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.commit();

            setTitle(item.getTitle());
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
