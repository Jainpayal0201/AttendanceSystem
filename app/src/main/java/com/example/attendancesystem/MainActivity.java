package com.example.attendancesystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

public class MainActivity extends AppCompatActivity {
    DBHandler dbHandler;
    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing type.
    public static final String TYPE_KEY = "type_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";

    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    String userEmail, userPassword,userType;
    NavHostFragment navHostFragment;
    NavInflater inflater;
    NavGraph graph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);  // Hostfragment
        inflater = navHostFragment.getNavController().getNavInflater();
        graph = inflater.inflate(R.navigation.fragmentnavigation);
        dbHandler=new DBHandler(MainActivity.this);
        // getting the data which is stored in shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        userType = sharedpreferences.getString(TYPE_KEY,null);
        userEmail = sharedpreferences.getString(EMAIL_KEY, null);
        userPassword = sharedpreferences.getString(PASSWORD_KEY, null);

        if (!TextUtils.isEmpty(userEmail)  && !TextUtils.isEmpty(userPassword) && !TextUtils.isEmpty(userType)) {

            if (userType.equals("admin")) {
                graph.setStartDestination(R.id.adminDashboard);
            } else if (userType.equals("faculty")) {
                graph.setStartDestination(R.id.facultyDashboard);
            }
        }else{
            graph.setStartDestination(R.id.loginPage);
        }
        navHostFragment.getNavController().setGraph(graph);

    }

    @Override
    public void onBackPressed() {
        Fragment navHostFragment = getSupportFragmentManager().getPrimaryNavigationFragment();
        Fragment fragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
        if (fragment instanceof AdminDashboard) {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        else if (fragment instanceof LoginPage) {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        super.onBackPressed();
    }

}
