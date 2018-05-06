package com.plusultra.csci448.journalshare;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ndeibert on 3/16/2018.
 */

public class SettingsFragment extends Fragment {
    private ImageButton mBackground_button_1;
    private ImageButton mBackground_button_2;
    private ImageButton mBackground_button_3;
    private ImageButton mBackground_button_4;
    private CheckBox mNotificationBox;
    private CheckBox mShareBox;
    private SeekBar mVolumeSlider;
    private Spinner mFontSpinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        final JournalBook journalBook = JournalBook.get(getActivity());
        mBackground_button_1 = (ImageButton) v.findViewById(R.id.settings_bg_button1);
        mBackground_button_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                journalBook.setEntryBgId(R.drawable.default_bg);
            }
        });

        mBackground_button_2 = (ImageButton) v.findViewById(R.id.settings_bg_button2);
        mBackground_button_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                journalBook.setEntryBgId(R.drawable.paper1);
            }
        });

        mBackground_button_3 = (ImageButton) v.findViewById(R.id.settings_bg_button3);
        mBackground_button_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                journalBook.setEntryBgId(R.drawable.paper2);
            }
        });

        mBackground_button_4 = (ImageButton) v.findViewById(R.id.settings_bg_button4);
        mBackground_button_4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                journalBook.setEntryBgId(R.drawable.paper3);
            }
        });

        mNotificationBox = (CheckBox) v.findViewById(R.id.notification_box);
        mNotificationBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                 @Override
                                                 public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                                                 }
                                             }
        );

        mShareBox = (CheckBox) v.findViewById(R.id.sharing_box);
        mShareBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                       @Override
                                                       public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                                                       }
                                                   }
        );


        mVolumeSlider = (SeekBar) v.findViewById(R.id.volume_slider);
        mVolumeSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getActivity(), "Seek bar progress is :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }
        });



        mFontSpinner = (Spinner) v.findViewById(R.id.font_spinner);
        mFontSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> fonts = new ArrayList<String>();
        fonts.add("Font 1");
        fonts.add("Font 2");
        fonts.add("Font 3");
        fonts.add("Font 4");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_text, fonts) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                if (position == 0) {
                    Typeface externalFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Black.ttf");
                    ((TextView) v).setTypeface(externalFont);
                }

               else if(position == 1) {
                    Typeface externalFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Medium.ttf");
                    ((TextView) v).setTypeface(externalFont);
                }
                else if(position == 2) {
                    Typeface externalFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Thin.ttf");
                    ((TextView) v).setTypeface(externalFont);
                }
                if (position == 3) {
                    Typeface externalFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/Chantelli_Antiqua.ttf");
                    ((TextView) v).setTypeface(externalFont);
                }


                return v;
            }


            public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                View v =super.getDropDownView(position, convertView, parent);

                if (position == 0) {
                    Typeface externalFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Black.ttf");
                    ((TextView) v).setTypeface(externalFont);
                }

                else if(position == 1) {
                    Typeface externalFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Medium.ttf");
                    ((TextView) v).setTypeface(externalFont);
                }
                else if(position == 2) {
                    Typeface externalFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Thin.ttf");
                    ((TextView) v).setTypeface(externalFont);
                }
                if (position == 3) {
                    Typeface externalFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/Chantelli_Antiqua.ttf");
                    ((TextView) v).setTypeface(externalFont);
                }
                return v;
            }
        };


       // ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, fonts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFontSpinner.setAdapter(adapter);

        return v;
    }

    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();
        // add args here
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
