package com.beginners.hangdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class GameActivity extends ActionBarActivity {

    String mWord;

    int mFailCounter = 0;
    int mPoints=0;
    int mGuessedLetters = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRandomWord();
    }

    public void introduceLetter(View v){
        EditText myEditText= (EditText) findViewById (R.id.editTextLetter);

        String letter= myEditText.getText().toString();
        myEditText.setText("");
        Log.d("MYLOG", "The Letter is "+letter);

        if(letter.length()==1){
            checkLetter(letter);
        } else {
            Toast.makeText(this,"Please Introduce Letter", Toast.LENGTH_SHORT).show();
        }
    }

    public void setRandomWord(){
        String words="";
    }

    public void checkLetter(String introducedLetter){

        char charIntroduced = introducedLetter.charAt(0);
        boolean letterGuessed = false;

        for (int i=0; i< mWord.length(); i++){

            char charFromTheWord= mWord.charAt(i);

            if (mWord.charAt(i)==introducedLetter.charAt(0)){

                letterGuessed=true;
                showLettersAtIndex(i,charIntroduced);
                mGuessedLetters++;
            }
        }

        if (letterGuessed==false){
            letterFailed(Character.toString(charIntroduced));
        }

        if (mGuessedLetters==mWord.length()){
            mPoints++;
            clearScreen();
            setRandomWord();
            //start the game
        }

    }

    public void clearScreen(){
        TextView textViewFailed= (TextView) findViewById(R.id.tvFailedLetters);
        textViewFailed.setText("");
        mGuessedLetters=0;
        mFailCounter=0;

        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);

        for (int i =0; i<layoutLetters.getChildCount(); i++){
            TextView currentTV = (TextView) layoutLetters.getChildAt(i);
            currentTV.setText("_");
        }
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangdroid_0);
    }

    public void letterFailed(String letterFailed){
        mFailCounter++;
        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        TextView textViewFailed= (TextView) findViewById(R.id.tvFailedLetters);
        textViewFailed.append(letterFailed);


        switch (mFailCounter){
            case 1:
                imageView.setImageResource(R.drawable.hangdroid_1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.hangdroid_2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.hangdroid_3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.hangdroid_4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.hangdroid_5);
                break;
            case 6:

                Intent gameOverIntent= new Intent(this,GameOverActivity.class);

                gameOverIntent.putExtra("POINTS_IDENTIFIER",mPoints);
                startActivity(gameOverIntent);
                break;
            default:
                imageView.setImageResource(R.drawable.hangdroid_5);
                break;
        }



    }

    /**
     * Displaying a letter guessed by the user
     * @param position of the letter
     * @param letterGuessed
     */
    public void showLettersAtIndex(int position, char letterGuessed){

        LinearLayout layoutLetter = (LinearLayout) findViewById(R.id.layoutLetters);

        TextView textView = (TextView) layoutLetter.getChildAt(position);
        textView.setText(Character.toString(letterGuessed));

    }


}
