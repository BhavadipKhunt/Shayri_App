package com.example.shayri_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Random;

public class shayri_open extends AppCompatActivity implements View.OnClickListener
{

        TextView textView;
        String shayri;
        String[] shayriarr;
        int k;
        Button Zoom,swipeleft,swipright,edit,change;
        GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shayri_open);
        Zoom=findViewById(R.id.Zoom_In_button);
        textView=findViewById(R.id.shayri_show_txt);
        swipeleft=findViewById(R.id.swiplefr_botton);
        change=findViewById(R.id.color_change_icon);
        swipright=findViewById(R.id.swipright_button);
        k=getIntent().getIntExtra("i1",0);
        shayri=getIntent().getStringExtra("shayri");
        System.out.println(""+k);
        shayriarr=getIntent().getStringArrayExtra("s");
        textView.setText(""+shayri);
        edit=findViewById(R.id.Edit_button);
        edit.setOnClickListener(this);
        change.setOnClickListener(this);
        swipright.setOnClickListener(this);
        swipeleft.setOnClickListener(this);
        Zoom.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
                if(view.getId()==Zoom.getId())
                {
                    BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this);
                    bottomSheetDialog.setContentView(R.layout.activity_shayri_grid_view);
                    gridView=bottomSheetDialog.findViewById(R.id.grid_gradient);
                    BackgroundAdapter backgroundAdapter=new BackgroundAdapter(shayri_open.this,config.gradients);

                    gridView.setAdapter(backgroundAdapter);
                    bottomSheetDialog.show();
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            textView.setBackgroundResource(config.gradients[i]);
                            bottomSheetDialog.cancel();
                        }
                    });
                }
                if(view.getId()==change.getId())
                {

                }
                    if (view.getId() == swipeleft.getId()) {
                        if(k>0) {
                            k--;

                            textView.setText(shayriarr[k]);
                        }
                    }



                    if(view.getId()==swipright.getId())
                    {
                        if(k<shayriarr.length-1) {
                            k++;
                            textView.setText(shayriarr[k]);
                        }
                    }
                if(view.getId()==edit.getId())
                {
                    Intent intent2=new Intent(shayri_open.this,Edit_shayri_activity.class);
                    intent2.putExtra("shayri",shayri);
                    startActivity(intent2);
                }
    }
}