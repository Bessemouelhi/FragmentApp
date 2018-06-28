package com.appdevloop.fragmentapp.fragments;

import android.support.v4.app.Fragment;
import android.widget.TextView;
import com.appdevloop.fragmentapp.R;
import butterknife.BindView;
import icepick.State;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends BaseFragment {

    @BindView(R.id.fragment_detail_text_view) TextView textView;
    @State int buttonTag;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected BaseFragment newInstance() { return new DetailFragment(); }

    @Override
    protected int getFragmentLayout() { return R.layout.fragment_detail; }

    @Override
    protected void configureDesign() { }

    @Override
    protected void updateDesign() {
        this.updateTextView(this.buttonTag);
    }

    // --------------
    // UPDATE UI
    // --------------

    //Update TextView depending on TAG's button
    public void updateTextView(int tag){

        //Save tag in ButtonTag variable
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
