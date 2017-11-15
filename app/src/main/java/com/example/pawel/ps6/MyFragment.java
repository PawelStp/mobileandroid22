package com.example.pawel.ps6;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Locale;

/**
 * Created by Ibra18plus on 2017-11-12.
 */

public class MyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        Button plButton = (Button) view.findViewById(R.id.buttonLangPl);
        plButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                changeLanguage("pl");
            }
        });

        Button enButton = (Button) view.findViewById(R.id.buttonLangEng);
        enButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                changeLanguage(("en"));
            }
        });


        return view;
    }

    public void changeLanguage(String language) {
        String languageToLoad = language;
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getActivity().getBaseContext().getResources().updateConfiguration(config,
                getActivity().getBaseContext().getResources().getDisplayMetrics());
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }


}
