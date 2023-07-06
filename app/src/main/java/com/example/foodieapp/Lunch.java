package com.example.foodieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class Lunch extends AppCompatActivity {

    TextView tvResult1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        tvResult1=findViewById(R.id.tvResult1);

        String[] array={"Alu gosht", "Palak Gosht", "Chicken Karahi", "Mutton Karahi", "Masaly wale Tendy", "Alu Qeema",
                        "Karele Gosht", "Alu Matar", "Masaly dar Arvi", "Alu Bengan", "Shalgam gosht", "Dal Chawal", "Kaley Channy",
                        "Safaid Channy", "Mash Channa ki daal", "Alu Shimla Mirch", "Ghia Gosht", "Mutton Qourma", "Chicken Qourma",
                        "Alu Maithi", "Dal channa and chicken", "Alu Karele", "Qeemy wale Karele", "Chinese Rice", "Chicken Biryani",
                        "Mutton Biryani", "Chicken Palao","Matar palao", "Mutton Palao", "Boneless Handi", "Daal Mash", "Alu Bengan",
                        "Kari Pakora", "Anda Kofta", "Arvi Gosht", "Kabuli Palao", "Nehari", "Beef Biryani", "Saag", "Chicken Haleem",
                        ""};
        Random random = new Random();

        // Generate a random index within the array bounds
        int randomIndex = random.nextInt(array.length);

        // Retrieve the random element from the array
        String randomElement = array[randomIndex];

        tvResult1.setText(randomElement);
    }
}