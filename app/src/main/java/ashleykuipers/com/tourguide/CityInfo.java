package ashleykuipers.com.tourguide;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CityInfo extends Fragment {
    TextView output;
    String[] info;

    public CityInfo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // getting city info from array in strings.xml
        // (doing this in this method because resources can only be accessed in OnCreate)
        Resources res = getResources();
        info = res.getStringArray(R.array.info);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // need to formally assign this to variable so we can use it to search for elements on the fragment
        View view = inflater.inflate(R.layout.fragment_city_info, container, false);

        // get index from bundle sent by main activity by the getCityIndex() method
        Bundle bundle = getArguments();
        int cityIndex = bundle.getInt("cityIndex");

        // set output text to city info
        // if index is 999, welcome message is displayed, if not, output is sent to the indicated city's info
        output = view.findViewById(R.id.textView_cityInfo);
        if(cityIndex != 999){
            output.setText(info[cityIndex]);
        } else {
            output.setText("Welcome! Please select a city above to learn more about it.");
        }

        return view;
    }
}