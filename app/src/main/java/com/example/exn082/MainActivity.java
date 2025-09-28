package com.example.exn082;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Random rnd;
    private EditText eT_number_a_input;
    private double a_value;
    private EditText eT_number_b_input;
    private EditText eT_number_c_input;
    private TextView tV_firstSolution;
    private TextView tV_noSolutionOrOnlySolution;
    private TextView tV_secondSolution;
    private Intent si;
    private int numSolutions;
    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rnd = new Random();
        eT_number_a_input = findViewById(R.id.eT_number_a_input);
        eT_number_b_input = findViewById(R.id.eT_number_b_input);
        eT_number_c_input = findViewById(R.id.eT_number_c_input);
        tV_firstSolution = findViewById(R.id.tV_firstSolution);
        tV_noSolutionOrOnlySolution = findViewById(R.id.tV_noSolutionOrOnlySolution);
        tV_secondSolution = findViewById(R.id.tV_secondSolution);
        si = new Intent(this,CalcActivity.class);
    }

    public void putRandomValue(View view) {
        tV_firstSolution.setVisibility(INVISIBLE);
        tV_noSolutionOrOnlySolution.setVisibility(INVISIBLE);
        tV_secondSolution.setVisibility(INVISIBLE);
        a_value = rnd.nextInt(20)-10+(rnd.nextInt(11))/10.0;
        if (a_value == 0.0)
        {
            a_value = 0.1;
        }
        eT_number_a_input.setText(""+a_value);
        eT_number_b_input.setText(""+(rnd.nextInt(20)-10+(rnd.nextInt(11))/10.0));
        eT_number_c_input.setText(""+(rnd.nextInt(20)-10+(rnd.nextInt(11))/10.0));
    }

    public void goToCalcActivity(View view) {
        if (eT_number_a_input.getText().toString().matches("-?\\d+(\\.\\d+)?|\\.\\d+") && eT_number_b_input.getText().toString().matches("-?\\d+(\\.\\d+)?|\\.\\d+") && eT_number_c_input.getText().toString().matches("-?\\d+(\\.\\d+)?|\\.\\d+"))
        {
            tV_firstSolution.setVisibility(INVISIBLE);
            tV_noSolutionOrOnlySolution.setVisibility(INVISIBLE);
            tV_secondSolution.setVisibility(INVISIBLE);
            if (Double.parseDouble(eT_number_a_input.getText().toString())==0)
            {
                eT_number_a_input.setText("a!=0!");
            }
            else
            {
                si.putExtra("a", Double.parseDouble(eT_number_a_input.getText().toString()));
                si.putExtra("b", Double.parseDouble(eT_number_b_input.getText().toString()));
                si.putExtra("c", Double.parseDouble(eT_number_c_input.getText().toString()));
                startActivityForResult(si, REQUEST_CODE);
            }
        }
    }
    @Override
    protected void onActivityResult(int source, int result, @Nullable Intent data_back)
    {
        super.onActivityResult(source,result,data_back);
        if (source==REQUEST_CODE)
        {
            if (result== Activity.RESULT_OK)
            {
                if (data_back!=null)
                {
                    numSolutions =data_back.getIntExtra("numSol",2);
                    if (numSolutions==2)
                    {
                        tV_firstSolution.setText(""+(data_back.getDoubleExtra("x1",0.0)));
                        tV_firstSolution.setVisibility(VISIBLE);
                        tV_secondSolution.setText(""+(data_back.getDoubleExtra("x2",0.0)));
                        tV_secondSolution.setVisibility(VISIBLE);
                    }
                    else if (numSolutions==1)
                    {
                        tV_noSolutionOrOnlySolution.setText(""+(data_back.getDoubleExtra("x1",0.0)));
                        tV_noSolutionOrOnlySolution.setVisibility(VISIBLE);
                    }
                    else
                    {
                        tV_noSolutionOrOnlySolution.setText("No solution");
                        tV_noSolutionOrOnlySolution.setVisibility(VISIBLE);
                    }
                }
            }
        }
    }
}