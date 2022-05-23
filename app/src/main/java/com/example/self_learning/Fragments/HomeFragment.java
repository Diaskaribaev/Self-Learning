package com.example.self_learning.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.self_learning.Authentication.LoginActivity;
import com.example.self_learning.Content.Android;
import com.example.self_learning.Content.Cplus;
import com.example.self_learning.Content.Csharp;
import com.example.self_learning.Content.Html;
import com.example.self_learning.Content.Java;
import com.example.self_learning.Content.Php;
import com.example.self_learning.Content.Python;
import com.example.self_learning.Content.React;
import com.example.self_learning.MainActivity;
import com.example.self_learning.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    ImageSlider slider;
    ImageView python;
    ImageView java;
    ImageView cplusplus;
    ImageView csharp;
    ImageView html;
    ImageView reactjs;
    ImageView js;
    ImageView android;
    ImageView php;








    public HomeFragment() {

    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        slider = view.findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://w.wallha.com/ws/12/6SEVGMlI.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.java, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.js, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.cplus, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.csharp, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.php, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.html, ScaleTypes.FIT));
        slider.setImageList(slideModels,ScaleTypes.FIT);


        python = view.findViewById(R.id.python);
        java = view.findViewById(R.id.java);
        cplusplus = view.findViewById(R.id.cplusplus);
        csharp = view.findViewById(R.id.csharp);
        html= view.findViewById(R.id.html);
        reactjs = view.findViewById(R.id.react);
        android = view.findViewById(R.id.android);
        php = view.findViewById(R.id.php);




        python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartPython();


            }
        });

        html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartHtml();
            }
        });

        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartJava();
            }
        });

        cplusplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartCplus();
            }
        });

        csharp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartCsharp();
            }
        });

        reactjs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartReactJs();
            }
        });

        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartAndroid();
            }
        });


        php.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartPhp();
            }
        });
































        return view;
    }


    private void StartPython(){
        Intent intent = new Intent(getActivity(),Python.class );
        startActivity(intent);
    }

    private void StartHtml(){
        Intent intent = new Intent(getActivity(), Html.class );
        startActivity(intent);
    }

    private void StartJava(){
        Intent intent = new Intent(getActivity(), Java.class );
        startActivity(intent);
    }
    private void StartCplus(){
        Intent intent = new Intent(getActivity(), Cplus.class );
        startActivity(intent);
    }

    private void StartCsharp(){
        Intent intent = new Intent(getActivity(), Csharp.class );
        startActivity(intent);
    }

    private void StartPhp(){
        Intent intent = new Intent(getActivity(), Php.class );
        startActivity(intent);
    }

    private void StartAndroid(){
        Intent intent = new Intent(getActivity(), Android.class );
        startActivity(intent);
    }

    private void StartReactJs(){
        Intent intent = new Intent(getActivity(), React.class );
        startActivity(intent);
    }


}