package com.example.user.calculator.ui.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;

import com.example.user.calculator.R;
import com.example.user.calculator.utils.calculator.Calculator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.expressionTextView)
    AppCompatTextView mExpressionTextView;

    @BindView(R.id.divButton)
    AppCompatButton mDivButton;

    @BindView(R.id.multiplicationButton)
    AppCompatButton mMultiplicationButton;

    @BindView(R.id.subButton)
    AppCompatButton mSubButton;

    @BindView(R.id.plusButton)
    AppCompatButton mPlusButton;

    @BindView(R.id.equalsButton)
    AppCompatButton mEqualsButton;

    @BindView(R.id.openBracketButton)
    AppCompatButton mOpenBracketButton;

    @BindView(R.id.closeBracketButton)
    AppCompatButton mCloseBracketButton;

    @BindView(R.id.clearButton)
    AppCompatButton mClearButton;

    @BindView(R.id.backspaceButton)
    AppCompatImageButton mBackspaceButton;

    @BindView(R.id.pointButton)
    AppCompatButton mPointButton;

    @BindView(R.id.zeroButton)
    AppCompatButton mZeroButton;

    @BindView(R.id.oneButton)
    AppCompatButton mOneButton;

    @BindView(R.id.twoButton)
    AppCompatButton mTwoButton;

    @BindView(R.id.threeButton)
    AppCompatButton mThreeButton;

    @BindView(R.id.fourButton)
    AppCompatButton mFourButton;

    @BindView(R.id.fiveButton)
    AppCompatButton mFiveButton;

    @BindView(R.id.sixButton)
    AppCompatButton mSixButton;

    @BindView(R.id.sevenButton)
    AppCompatButton mSevenButton;

    @BindView(R.id.eightButton)
    AppCompatButton mEightButton;

    @BindView(R.id.nineButton)
    AppCompatButton mNineButton;

    //
    private Calculator calculator = new Calculator();

    // Key for save current expression between device rotate.
    private static final String EXP_KEY = "expression";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind view.
        ButterKnife.bind(this);

        // Set expression after device rotate if need.
        if (savedInstanceState != null) {
            mExpressionTextView.setText(savedInstanceState.getString(EXP_KEY));
        }

        // Set click listener on the buttons with number.
        mZeroButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString() + getString(R.string.zero)));
        mOneButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString() + getString(R.string.one)));
        mTwoButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString() + getString(R.string.two)));
        mThreeButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString() + getString(R.string.three)));
        mFourButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString() + getString(R.string.four)));
        mFiveButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString() + getString(R.string.five)));
        mSixButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString() + getString(R.string.six)));
        mSevenButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString() + getString(R.string.seven)));
        mEightButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString() + getString(R.string.eight)));
        mNineButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString() + getString(R.string.nine)));

        // Set click listener on the button with point.
        mPointButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString() +
                        getString(R.string.point)));

        // Set click listener on the button with open and close brackets.
        mOpenBracketButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString() +
                        getString(R.string.option_open_bracket)));
        mCloseBracketButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString() +
                        getString(R.string.option_close_bracket)));

        // Set click listener on the button with math operations.
        mDivButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString() + getString(R.string.div)));
        mMultiplicationButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString() + getString(R.string.multiplication)));
        mSubButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString() + getString(R.string.sub)));
        mPlusButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString() + getString(R.string.plus)));

        // Calculate expression and set result.
        mEqualsButton.setOnClickListener((v) ->
            mExpressionTextView.setText(checkResult(calculator.calculate(mExpressionTextView.getText().toString()))));

        //
        mBackspaceButton.setOnClickListener((v) ->
                mExpressionTextView.setText(mExpressionTextView.getText().toString()
                        .substring(0, mExpressionTextView.getText().toString().length() - 1)));
        mClearButton.setOnClickListener((v) -> mExpressionTextView.setText(""));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save current expression.
        outState.putString(EXP_KEY, mExpressionTextView.getText().toString());
    }

    // If expression is illegal then notify user about this.
    private String checkResult(String res) {
        if (res.isEmpty()) {
            Snackbar.make(this.findViewById(android.R.id.content),
                    getString(R.string.message_expression_error), Snackbar.LENGTH_SHORT).show();
            return res;
        } else {
            return res;
        }
    }
}
