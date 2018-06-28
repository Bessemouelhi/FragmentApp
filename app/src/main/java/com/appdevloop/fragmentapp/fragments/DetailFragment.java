package com.appdevloop.fragmentapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appdevloop.fragmentapp.R;

import icepick.Icepick;
import icepick.State;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // 1 - Declare a buttonTag tracking
    @State int buttonTag;
    // 2 - Create static variable to identify key in Bundle
    private static final String KEY_BUTTONTAG = "DetailFragment.KEY_BUTTONTAG";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // 1 - Declare TextView
    private TextView textView;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        // 2 - Get textView from layout (don't forget to create ID in fragment_detail.xml)
        this.textView = (TextView) view.findViewById(R.id.fragment_detail_text_view);
        return(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Restore last buttonTag if possible
        // Restore all @State annotation variables in Bundle
        Icepick.restoreInstanceState(this, savedInstanceState);
        this.updateTextView(this.buttonTag);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //  Save buttonTag in Bundle when fragment is destroyed
        //  Save all @State annotation variables in Bundle
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    // 3 - Update TextView depending on TAG's button
    public void updateTextView(int tag){
        Log.i("DetailFragment", "updateTextView: " + tag);
        // 3 - Save tag in ButtonTag variable
        this.buttonTag = tag;
        switch (tag){
            case 10:
                this.textView.setText("You're a very good programmer !");
                break;
            case 20:
                this.textView.setText("I do believe that Jon Snow is going to die in next season...");
                break;
            case 30:
                this.textView.setText("Maybe Game of Thrones next season will get back in 2040 ?");
                break;
            default:
                break;
        }
    }

}
