package com.qux.randnum;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText minEditText;
    EditText maxEditText;
    TextView textView;
    Button generateButton;
    String min_text;
    String max_text;
    Integer min_num;
    Integer max_num;
    Integer base_num;
    Integer num_num;
    Integer result_num;
    Integer count = 0;
    String[] colors = {"#ff0000", "#00ff00", "#0000ff"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minEditText = findViewById(R.id.min_num);
        maxEditText = findViewById(R.id.max_num);
        textView = findViewById(R.id.textView);
        generateButton = findViewById(R.id.generate_button);


        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                min_text = minEditText.getText().toString();
                max_text = maxEditText.getText().toString();
                if (min_text.length() > 10 || max_text.length() > 9){
                    Toast.makeText(MainActivity.this, "支持位数不超过9位", Toast.LENGTH_SHORT).show();
                }else{
                    if(min_text == null || min_text.length() == 0){
                        min_num = 0;
                    }else{
                        min_num = Integer.parseInt(min_text);
                    }
                    if(max_text == null || max_text.length() == 0){
                        max_num = 10;
                    }else {
                        max_num = Integer.parseInt(max_text);
                    }
                    // 输入的人有可能不听话，最大比最小小，用这样的方式解决
                    if(max_num > min_num){
                        base_num = min_num;
                        num_num = max_num - min_num + 1;
                    }else{
                        base_num = max_num;
                        num_num = min_num - max_num + 1;
                    }

                    Random r = new Random();
                    result_num = r.nextInt(num_num) + base_num;
                    // 根据不同的位数设置不同的尺寸
                    if(result_num < 1000 && result_num > -100){
                        textView.setTextSize(200);
                    }else if(result_num < 10000 && result_num > -1000){
                        textView.setTextSize(100);
                    }else if(result_num < 10000000 && result_num > -1000000){
                        textView.setTextSize(80);
                    }else{
                        textView.setTextSize(60);
                    }
                    textView.setText(String.valueOf(result_num));
                    // 变色
                    textView.setTextColor(Color.parseColor(colors[count]));
                    count = (count + 1) % 3;
                }

            }
        });

    }
}
