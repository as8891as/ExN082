package com.example.exn082;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalcActivity extends AppCompatActivity {
    private double a;
    private double b;
    private double c;
    private double delta;
    private TextView tV_firstSolution;
    private TextView tV_noSolutionOrOnlySolution;
    private TextView tV_secondSolution;
    private ImageView iV_graph;
    private Intent gi;
    private final int RESULT_OK= Activity.RESULT_OK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        gi = getIntent();
        a = gi.getDoubleExtra("a",0.0);
        b = gi.getDoubleExtra("b",0.0);
        c = gi.getDoubleExtra("c",0.0);
        delta = b*b-4*a*c;
        tV_firstSolution = findViewById(R.id.tV_firstSolution_calc);
        tV_noSolutionOrOnlySolution = findViewById(R.id.tV_noSolutionOrOnlySolution_calc);
        tV_secondSolution = findViewById(R.id.tV_secondSolution_calc);
        iV_graph = findViewById(R.id.iV_graph);
        if (delta>0)
        {
            gi.putExtra("numSol",2);
            tV_firstSolution.setText(""+((-1*b+Math.sqrt(delta))/(2*a)));
            tV_firstSolution.setVisibility(VISIBLE);
            gi.putExtra("x1",(-1*b+Math.sqrt(delta))/(2*a));
            tV_secondSolution.setText(""+((-1*b-Math.sqrt(delta))/(2*a)));
            tV_secondSolution.setVisibility(VISIBLE);
            gi.putExtra("x2",(-1*b-Math.sqrt(delta))/(2*a));
            if (a>0)
            {
                iV_graph.setImageResource(R.drawable.a_pos_2);
            }
            else
            {
                iV_graph.setImageResource(R.drawable.a_neg_2);
            }
        }
        else if (delta==0)
        {
            gi.putExtra("numSol",1);
            tV_noSolutionOrOnlySolution.setText(""+-1*b/(2*a));
            tV_noSolutionOrOnlySolution.setVisibility(VISIBLE);
            gi.putExtra("x1",(-1*b/(2*a)));
            if (a>0)
            {
                iV_graph.setImageResource(R.drawable.a_pos_1);
            }
            else
            {
                iV_graph.setImageResource(R.drawable.a_neg_1);
            }
        }
        else
        {
            gi.putExtra("numSol",0);
            tV_noSolutionOrOnlySolution.setText("No solution");
            tV_noSolutionOrOnlySolution.setVisibility(VISIBLE);
            if (a>0)
            {
                iV_graph.setImageResource(R.drawable.a_pos_0);
            }
            else
            {
                iV_graph.setImageResource(R.drawable.a_neg_0);
            }
        }
    }

    public void returnWithAns(View view) {
        tV_firstSolution.setVisibility(INVISIBLE);
        tV_noSolutionOrOnlySolution.setVisibility(INVISIBLE);
        tV_secondSolution.setVisibility(INVISIBLE);
        setResult(RESULT_OK,gi);
        finish();
    }
}