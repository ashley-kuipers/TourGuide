package ashleykuipers.com.tourguide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int cityIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting up a fragment manager for a CityList fragment on the main activity and bringing it up
        FragmentManager fr = getSupportFragmentManager();
        FragmentTransaction ft = fr.beginTransaction();
        CityList citylist = new CityList();
        // there will only ever be one copy of CityList so we can use "add" method instead of "replace" like in other fragment managers
        ft.add(R.id.frame_main_top, citylist);
        ft.commit();

        // set up new fragment manager for a CityInfo fragment
        FragmentManager fr2 = getSupportFragmentManager();
        FragmentTransaction ft2 = fr2.beginTransaction();
        CityInfo cityinfo = new CityInfo();

        //packages info to send to cityInfo
        Bundle bundle = new Bundle();
        // start the fragment with index as 999 (which will make the welcome message come up in the beginning as per code in CityInfo fragment)
        bundle.putInt("cityIndex", 999);
        cityIndex = 999;
        cityinfo.setArguments(bundle);

        // actually brings up cityInfo (replaces any instance of cityInfo fragment currently up)
        ft2.replace(R.id.frame_main_bottom, cityinfo);
        ft2.commit();
    }

    // custom method that happens when a list element is clicked
    public void getCityIndex(int index){
        // gets index from CityList fragment
        cityIndex = index;

        // adding a CityInfo fragment to the main activity and sending data
        // set up another fragment manager
        FragmentManager fr2 = getSupportFragmentManager();
        FragmentTransaction ft2 = fr2.beginTransaction();
        CityInfo cityinfo = new CityInfo();

        // packages info to send to cityInfo
        Bundle bundle = new Bundle();
        bundle.putInt("cityIndex", cityIndex);
        cityinfo.setArguments(bundle);

        // actually brings up new cityInfo fragment (replaces any instance of cityInfo fragment currently up)
        ft2.replace(R.id.frame_main_bottom, cityinfo);
        ft2.commit();
    }

    // when you turn the phone, this function is called to save any data you wish to save
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // saving the index to the bundle in the method argument ^
        outState.putInt("int_value",cityIndex);
        super.onSaveInstanceState(outState);
    }

    // when phone is done turning, this function is called to restore any of that data you saved
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        // get values from saved state
        cityIndex=savedInstanceState.getInt("int_value");

        // set up another fragment manager
        FragmentManager fr2 = getSupportFragmentManager();
        FragmentTransaction ft2 = fr2.beginTransaction();
        CityInfo cityinfo = new CityInfo();

        //packages info to send to the new cityInfo fragment
        Bundle bundle = new Bundle();
        bundle.putInt("cityIndex", cityIndex);
        cityinfo.setArguments(bundle);

        // actually brings up cityInfo (replaces any instance of cityInfo fragment currently up)
        ft2.replace(R.id.frame_main_bottom, cityinfo);
        ft2.commit();

        super.onRestoreInstanceState(savedInstanceState);
    }
}

