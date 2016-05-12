package com.deanout.mcnumbers;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends Activity {
    private EditText input;
    private EditText total;
    private EditText input40Piece;

    private EditText input20Piece;

    private EditText input10Piece;

    private EditText input6Piece;

    private EditText input4Piece;

    private EditText inputCalories;

    private EditText inputHappiness;



    public double monies = 0.2;
    public double[] pieces = new double[5];
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.inputMonies);
        total = (EditText) findViewById(R.id.inputTotal);
        input40Piece = (EditText) findViewById(R.id.input40Piece);
        input20Piece = (EditText) findViewById(R.id.input20Piece);
        input10Piece = (EditText) findViewById(R.id.input10Piece);
        input6Piece = (EditText) findViewById(R.id.input6Piece);
        input4Piece = (EditText) findViewById(R.id.input4Piece);
        inputCalories = (EditText) findViewById(R.id.inputCalories);
        inputHappiness = (EditText) findViewById(R.id.inputHappiness);


        total.setFocusable(false);
        input40Piece.setFocusable(false);
        input20Piece.setFocusable(false);
        input10Piece.setFocusable(false);
        input6Piece.setFocusable(false);
        input40Piece.setFocusable(false);
        inputCalories.setFocusable(false);
        inputHappiness.setFocusable(false);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count){
                try {
                    monies = Integer.parseInt(input.getText().toString());
                } catch (Exception e) {
                    monies = 0;
                }
                pieces = calculateDelicious(monies);
                total.setText(String.valueOf(calculateTotalNuggets(pieces)));
                input40Piece.setText((String.valueOf(pieces[4])));
                input20Piece.setText((String.valueOf(pieces[3])));
                input10Piece.setText((String.valueOf(pieces[2])));
                input6Piece.setText((String.valueOf(pieces[1])));
                input4Piece.setText((String.valueOf(pieces[0])));
                inputCalories.setText((String.valueOf(calculateTotalNuggets(pieces) * 190)));

                if (Integer.parseInt(total.getText().toString()) > 0) {
                    inputHappiness.setText("Yes.");
                } else {
                    inputHappiness.setText("Buy soME NUGGETS!");
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public double[] calculateDelicious(double input) {
        double[] returnValues = new double[6];
        try {
            returnValues[4] = Math.floor(input / 8.99);
            returnValues[3] = Math.floor((input % 8.99) / 5.00);
            returnValues[2] = Math.floor(((input % 8.99) % 5.00) / 6.49);
            returnValues[1] = Math.floor((((input % 8.99) % 5.00) % 6.49) / 3.99);
            returnValues[0] = Math.floor(((((input % 8.99) % 5.00) % 6.49) % 3.99) / 1.99);

            for (int i = 0; i <= 4; i++) {
                returnValues[5] += returnValues[i];
            }
        } catch (Exception e) {
            for (int i = 0; i <= 5; i++) {
                returnValues[i] = 0;
            }
        }

        return returnValues;
    }
    public int calculateTotalNuggets(double[] input) {
        int runningTotal = (int)(input[4] * 40 + input[3] * 20 + input[2] * 10 + input[1] * 6 + input[0] * 4);
        return runningTotal;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.deanout.mcnumbers/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.deanout.mcnumbers/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}