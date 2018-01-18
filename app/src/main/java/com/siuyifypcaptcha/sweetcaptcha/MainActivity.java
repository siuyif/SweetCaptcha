package com.siuyifypcaptcha.sweetcaptcha;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private ImageButton porridge, babybottle, pacifier, rattle, stock, cup;
    private TextView rndword;
    private String randomText;
    private ImageView cryingbaby;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // randomise textview
        String [] word = {"porridge", "milk bottle", "pacifier", "rattle"};
        Random rnd = new Random();
        rndword = (TextView) findViewById(R.id.textRand);
        randomText = word[rnd.nextInt(word.length)];
        rndword.setText("Drag the " + randomText + " to the crying baby.");

        //views to drag
        porridge = (ImageButton) findViewById(R.id.imageButton);
        babybottle = (ImageButton) findViewById(R.id.imageButton1);
        pacifier = (ImageButton) findViewById(R.id.PimageButton2);
        rattle = (ImageButton) findViewById(R.id.imageButton3);
        stock = (ImageButton) findViewById(R.id.imageButton4);
        cup = (ImageButton) findViewById(R.id.imageButton5);

        //views to drop onto
        cryingbaby = (ImageView) findViewById(R.id.PimageView);

        //set touch listeners
        porridge.setOnTouchListener(new ChoiceTouchListener());
        babybottle.setOnTouchListener(new ChoiceTouchListener());
        pacifier.setOnTouchListener(new ChoiceTouchListener());
        rattle.setOnTouchListener(new ChoiceTouchListener());
        stock.setOnTouchListener(new ChoiceTouchListener());
        cup.setOnTouchListener(new ChoiceTouchListener());

        //set drag listeners (target)
        cryingbaby.setOnDragListener(new ChoiceDragListener());

    }

    //To handle touch events for the draggable views
    private final class ChoiceTouchListener implements OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                //To pass data.
                ClipData data = ClipData.newPlainText("", "");
                DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    //To handle and check dragged view was dropped off at the set target.
    private class ChoiceDragListener implements OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:

                    //handle the dragged view being dropped over a drop view
                    View view = (View) event.getLocalState();

                    //view dragged item is being dropped on
                    ImageView dropTarget = (ImageView) v;

                    //view being dragged and dropped
                    ImageButton dropped = (ImageButton) view;


                   //checking the randomText as well as the dragged view, to make sure user dragged the right image to the set target
                    if (randomText.equals("pacifier") && (dropped.getId() == pacifier.getId())) {
                            //stop displaying the view where it was before it was dragged
                            view.setVisibility(View.INVISIBLE);

                            // if conditions met, brought to another new activity
                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                            startActivity(intent);
                    }
                    else if (randomText.equals("porridge") && (dropped.getId() == porridge.getId())) {
                        view.setVisibility(View.INVISIBLE);

                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);
                    }
                    else if (randomText.equals("milk bottle") && (dropped.getId() == babybottle.getId())){
                        view.setVisibility(View.INVISIBLE);

                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);
                    }
                    else if (randomText.equals("rattle") && (dropped.getId() == rattle.getId())){
                        view.setVisibility(View.INVISIBLE);

                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);
                    }
                    else{
                        //if didn't match any of the conditions above, user has to restart all over again
                        Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                        startActivity(intent);
                        break;
                    }

                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }
            return true;
        }
    }
}




