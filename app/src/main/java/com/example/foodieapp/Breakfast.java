package com.example.foodieapp;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.os.Bundle;
import android.widget.TextView;

public class Breakfast extends AppCompatActivity {

    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
        tvResult=findViewById(R.id.tvResult);

        String[] array = { "Aloo Paratha -- آلو کا پراٹھا", "Chicken Paratha -- چکن پراٹھا", "Omelet and Parata -- پراٹھا آملیٹ",
                            "Chai and Paratha -- چاۓ اور پراٹھا", "Cheese Paratha -- چیز پراٹھا", "Gobhi Paratha -- گوبھی پراٹھا",
                            "Beef Nehari -- بیف نہاری", "Naan Channy -- نان چنے", "Halwa poori -- حلوہ پوری",
                            "Lassi and Bun -- لسی اور بن", "Onion Omelet -- پیاز والا آملیٹ", "Bread and omelet -- بریڈ اور آملیٹ",
                            "Mooli wala Paratha -- مولی والا پراٹھا", "Shami Sandwich and chai -- شامی سینڈوچ اور چاۓ",
                            "Meetha Paratha -- میٹھا پراٹھا", "Bakir Khani and chai -- باقر خانی اور چاۓ", "Chai Rusk -- چاۓ رسک",
                            "Saag Paratha -- ساگ پراٹھا", "Besani Naan -- بیسن والا نان", "Achar Paratha -- اچار پراٹھا",
                            "Sooji Halwa and Paratha -- سوجی کا حلوہ اور پراٹھا"};

        // Create an instance of the Random class
        Random random = new Random();

        // Generate a random index within the array bounds
        int randomIndex = random.nextInt(array.length);

        // Retrieve the random element from the array
        String randomElement = array[randomIndex];

        tvResult.setText(randomElement);
    }

}