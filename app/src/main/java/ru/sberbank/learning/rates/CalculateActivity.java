package ru.sberbank.learning.rates;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import ru.sberbank.learning.rates.storage.CurrenciesStorage;

/**
 * Created by DevCom on 30.04.2017.
 */

public class CalculateActivity extends Activity {

    private CurrenciesStorage mStorage;
    private EditText mInputText;
    private TextView mResult;
    private TextView mCharCode;
    private View m1View, m2View, m3View, m4View, m5View;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        mInputText = (EditText) findViewById(R.id.inputEditText);
        mCharCode = (TextView) findViewById(R.id.charCodeTextView);
        mResult = (TextView) findViewById(R.id.resultTextView);
        m1View = findViewById(R.id.first_animated_view);
        m2View = findViewById(R.id.second_animated_view);
        m3View = findViewById(R.id.third_animated_view);
        m4View = findViewById(R.id.fourth_animated_view);
        m5View = findViewById(R.id.fifth_animated_view);

        intent = getIntent();
        mCharCode.setText(intent.getStringExtra("charCode"));

        animView(m1View,8000);
        animView(m2View,2000);
        animView(m3View,9000);
        animView(m4View,5000);
        animView(m5View,7000);

        mInputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculate();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void calculate() {

        try {
            intent = getIntent();
            double currentValue = Double.parseDouble(mInputText.getText().toString());
            double value = intent.getDoubleExtra("value", 0);
            double nominal = intent.getDoubleExtra("nominal", 0);
            mResult.setText("= " + String.format("%.2f",currentValue * value / nominal)+" RUB");

        }catch (NumberFormatException e) {
            mResult.setText("=");
        }
    }

    private void animView(final View view, int lvl) {
        ValueAnimator mAnimator = ValueAnimator.ofInt(0, lvl);
        mAnimator.setRepeatCount(0);
        mAnimator.setDuration(1500);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                view.getBackground().setLevel(value);
            }
        });
        mAnimator.start();
    }

}
