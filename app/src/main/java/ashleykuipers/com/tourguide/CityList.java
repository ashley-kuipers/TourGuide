package ashleykuipers.com.tourguide;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class CityList extends Fragment {
    ListView listview;
    String[] cities;

    public CityList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // getting city names from array in strings.xml
        // (doing this in this method because resources can only be accessed in OnCreate)
        Resources res = getResources();
        cities = res.getStringArray(R.array.cities);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // need to formally assign this to variable so we can use it to search for elements on the fragment
        View view = inflater.inflate(R.layout.fragment_city_list, container, false);

        // Creating listview adapter (connects the listview to the string array)
        listview = view.findViewById(R.id.listView_cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, cities);
        listview.setAdapter(adapter);

        // makes it so that when a list item is clicked, the index of that item will be sent to the main activity
        // (this is done by invoking a custom method we made in the main activity)
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                int cityIndex = position;

                // get an instance of the main activity and run a custom method with the index as an argument
                // (this actually passes the index to the main activity, no bundles needed)
                MainActivity mainAct = (MainActivity) getActivity();
                mainAct.getCityIndex(cityIndex);
            }
        });

        // inflates (or brings up on screen) fragment
        return view;
    }
}