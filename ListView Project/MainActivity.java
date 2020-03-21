package com.example.listproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.palette.graphics.Palette;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class  MainActivity extends AppCompatActivity {

    public static final int IMAGE_GALLERY_REQUEST = 20;
    public static final int WHITE_SCREEN = 16777215;
    Info info = new Info();
    ListView listView;
    ArrayList<SuperHero> arrayList;
    TextView descriptionShort1;
    TextView descriptionShort2;
    ConstraintLayout layout;
    ImageButton addButton;
    ImageView previewImg;
    TextView landMoreDesc;
    Drawable tempImage;
    Typeface spiderFont;
    Typeface batmanFont;
    Typeface capFont;
    Typeface fontone;
    Typeface fonttwo;
    int selectedItem;
    int screenColor;
    int textColor;
    public static final String KEY_LIST = "a";
    public static final String KEY_SCREEN_COLOR = "b";
    public static final String KEY_TEXT_COLOR = "c";
    public static final String KEY_SHORT_DESCRIPTION = "d";
    public static final String KEY_SELECTED_ITEM = "e";
    public static final String KEY_SHORT_DESCRIPTION2 = "f";
    boolean dialogShowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.id_listview);
        batmanFont = ResourcesCompat.getFont(this, R.font.batmanfont);
        spiderFont = ResourcesCompat.getFont(this, R.font.homecomingfont);
        capFont = ResourcesCompat.getFont(this, R.font.capfont);
        fontone = ResourcesCompat.getFont(this, R.font.regfontone);
        fonttwo = ResourcesCompat.getFont(this, R.font.regfonttwo);
        descriptionShort1 = findViewById(R.id.id_description1);
        descriptionShort1.setText("Hello There!");
        descriptionShort2 = findViewById(R.id.id_description2);
        descriptionShort2.setText("What's Up!");
        layout = findViewById(R.id.id_layout);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            landMoreDesc = findViewById(R.id.id_land_moreDesc);
            landMoreDesc.setMovementMethod(new ScrollingMovementMethod());
        }
        addButton = findViewById(R.id.id_add);
        addButton.setImageResource(R.drawable.plus);
        addButton.setBackgroundDrawable(null);
        tempImage = getDrawable(R.drawable.captain);
        arrayList = new ArrayList<>();
        arrayList.add(new SuperHero("Superman", getDrawable(R.drawable.superman), "DC", info.SupermanDesc, "Krypton"));
        arrayList.add(new SuperHero("Batman", getDrawable(R.drawable.batman), "DC", info.BatmanDesc, "Gotham City", batmanFont));
        arrayList.add(new SuperHero("Spiderman", getDrawable(R.drawable.spiderman), "Marvel", info.SpidermanDesc, "Queens, NYC", spiderFont));
        arrayList.add(new SuperHero("Robin", getDrawable(R.drawable.robin), "DC", info.RobinDesc, "Gotham City", batmanFont));
        arrayList.add(new SuperHero("Wonder Woman", getDrawable(R.drawable.wonderwoman), "DC", info.WWDesc, "Themyscira", fonttwo));
        arrayList.add(new SuperHero("Wolverine", getDrawable(R.drawable.wolverine), "Marvel", info.WolverineDesc, "Alberta, Canada"));
        arrayList.add(new SuperHero("Captain America", getDrawable(R.drawable.captain), "Marvel", info.CapDesc, "Brooklyn, NYC", capFont));
        screenColor = WHITE_SCREEN;
        textColor = android.graphics.Color.rgb(0, 0, 0);

        if(savedInstanceState!=null) {
            arrayList = (ArrayList<SuperHero>) savedInstanceState.getSerializable(KEY_LIST);
            screenColor = savedInstanceState.getInt(KEY_SCREEN_COLOR);
            textColor = savedInstanceState.getInt(KEY_TEXT_COLOR);
            String tempShort = savedInstanceState.getString(KEY_SHORT_DESCRIPTION);
            String tempShort2 = savedInstanceState.getString(KEY_SHORT_DESCRIPTION2);

            layout.setBackgroundColor(screenColor);
            descriptionShort1.setText(tempShort);
            descriptionShort1.setTextColor(textColor);

            descriptionShort2.setText(tempShort2);
            descriptionShort2.setTextColor(textColor);

            descriptionShort1.setTypeface(fontone);
            descriptionShort2.setTypeface(fontone);

            selectedItem = savedInstanceState.getInt(KEY_SELECTED_ITEM);
            if(landMoreDesc != null) {
                landMoreDesc.setText(arrayList.get(selectedItem).getMoreDesc());
                landMoreDesc.setTextColor(textColor);
            }
        }

        final CustomAdapter customAdapter = new CustomAdapter(this, R.layout.adapter_custom, arrayList);
        listView.setAdapter(customAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShowing = true;
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_add, null);
                mView.setBackgroundColor(screenColor);

                TextView title = mView.findViewById(R.id.id_dialog_title);
                TextView nameText = mView.findViewById(R.id.id_dialog_textName);
                TextView descShortText = mView.findViewById(R.id.id_dialog_textDescShort);
                TextView imageText = mView.findViewById(R.id.id_dialog_image);
                TextView moreDescText = mView.findViewById(R.id.id_dialog_moreDescText);
                title.setTextColor(textColor);
                nameText.setTextColor(textColor);
                descShortText.setTextColor(textColor);
                imageText.setTextColor(textColor);
                moreDescText.setTextColor(textColor);

                previewImg = mView.findViewById(R.id.id_dialog_preview);
                previewImg.setImageResource(R.drawable.captain);

                final EditText mName = mView.findViewById(R.id.id_dialog_name);
                mName.setTextColor(textColor);
                final EditText mDesc = mView.findViewById(R.id.id_dialog_descShort);
                mDesc.setTextColor(textColor);
                final EditText mMoreDesc = mView.findViewById(R.id.id_dialog_moreText);
                mMoreDesc.setTextColor(textColor);

                Button mAdd = mView.findViewById(R.id.id_dialog_addButton);
                Button mAddImage = mView.findViewById(R.id.id_dialog_addImage);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                mAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name;
                        String descShort;
                        String descLong;
                        if(!(mName.getText().toString().isEmpty()) || !(mDesc.getText().toString().isEmpty()) || !(mDesc.getText().toString().isEmpty())){
                            name = mName.getText().toString();
                            descShort = mDesc.getText().toString();
                            descLong = mMoreDesc.getText().toString();
                            arrayList.add(new SuperHero(name, tempImage, descShort, descLong, "fesf"));
                            customAdapter.notifyDataSetChanged();
                            tempImage = getDrawable(R.drawable.captain);

                            Toast.makeText(MainActivity.this,
                                    "Successfully Added",
                                    Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            dialogShowing = false;
                        }
                        else{
                            Toast.makeText(MainActivity.this,
                                    "Please Fill out the Fields",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                mAddImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

                        File pictureDirectory =
                                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                        String pictureDirectoryPath = pictureDirectory.getPath();

                        Uri data = Uri.parse(pictureDirectoryPath);

                        photoPickerIntent.setDataAndType(data, "image/*");

                        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);

                    }
                });


            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = position;
                descriptionShort1.setText("UNIVERSE: " + arrayList.get(position).getDesc().toUpperCase());
                descriptionShort2.setText("ORIGIN: " + arrayList.get(position).getDescription2().toUpperCase());
                if(landMoreDesc != null) {
                    landMoreDesc.setText(arrayList.get(position).getMoreDesc());
                }

                Bitmap bitmap = ((BitmapDrawable)(arrayList.get(position).getImage())).getBitmap();
                Palette p = Palette.from(bitmap).generate();
                Palette.Swatch currentSwatch = p.getDominantSwatch();
                screenColor = p.getDominantColor(1);
                layout.setBackgroundColor(screenColor);
                int color = currentSwatch.getTitleTextColor();
                textColor = color;

                descriptionShort1.setTextColor(currentSwatch.getTitleTextColor());
                descriptionShort2.setTextColor(currentSwatch.getTitleTextColor());
                descriptionShort1.setTypeface(fontone);
                descriptionShort2.setTypeface(fontone);
                if(landMoreDesc != null) {
                    landMoreDesc.setTextColor(currentSwatch.getTitleTextColor());
                }

                customAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_GALLERY_REQUEST){
                Uri imageUri = data.getData();
                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap image = BitmapFactory.decodeStream(inputStream);
                    Bitmap scaled = Bitmap.createScaledBitmap(image, 316, 316, true); //196 by 196 at home comp 316 by 316 at school

                    Drawable d = new BitmapDrawable(getResources(), scaled);

                    tempImage = d;

                    previewImg.setImageDrawable(tempImage);
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to Open Image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(!dialogShowing) {
            outState.putSerializable(KEY_LIST, arrayList);
        }
        outState.putInt(KEY_SCREEN_COLOR, screenColor);
        outState.putInt(KEY_TEXT_COLOR, textColor);
        outState.putString(KEY_SHORT_DESCRIPTION, descriptionShort1.getText().toString());
        outState.putString(KEY_SHORT_DESCRIPTION2, descriptionShort2.getText().toString());
        outState.putInt(KEY_SELECTED_ITEM, selectedItem);
    }

    public class CustomAdapter extends ArrayAdapter<SuperHero>{

        List<SuperHero> list;
        Context context;
        int xmlresource;

        public CustomAdapter(@NonNull Context context, int resource, @NonNull List<SuperHero> objects) {
            super(context, resource, objects);
            this.context = context;
            xmlresource = resource;
            list = objects;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterView = layoutInflater.inflate(xmlresource, null);
            TextView textView = adapterView.findViewById(R.id.id_adapter_textView);
            textView.setTextColor(textColor);
            ImageView imageView = adapterView.findViewById(R.id.id_adapter_imageView);
            ImageButton trashButton = adapterView.findViewById(R.id.id_trash);
            trashButton.setBackgroundDrawable(null);
            trashButton.setImageResource(R.drawable.newdelete);

            if(arrayList.get(position).getFont() != null)
                textView.setTypeface(arrayList.get(position).getFont());

            trashButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyDataSetChanged();
                    descriptionShort1.setText("UNIVERSE: ");
                    descriptionShort2.setText("ORIGIN: ");
                    layout.setBackgroundColor(WHITE_SCREEN);
                    textColor = android.graphics.Color.rgb(0, 0, 0);
                    descriptionShort1.setTextColor(textColor);
                    descriptionShort2.setTextColor(textColor);
                    if(landMoreDesc != null){
                        landMoreDesc.setText("");
                        landMoreDesc.setTextColor(textColor);
                    }
                    screenColor = WHITE_SCREEN;
                }
            });

            textView.setText("Name: " + list.get(position).getString());
            imageView.setImageDrawable(list.get(position).getImage());

            return adapterView;
        }
    }

    public class SuperHero implements Serializable{
        String name;
        Drawable avatar;
        String description;
        String moreDesc;
        String description2;
        Typeface font = null;

        public SuperHero(String name, Drawable drawable, String description, String moreDesc, String description2, Typeface font){
            this.name = name;
            this.avatar = drawable;
            this.description = description;
            this.moreDesc = moreDesc;
            this.description2 = description2;
            this.font = font;
        }

        public SuperHero(String name, Drawable drawable, String description, String moreDesc, String description2){
            this.name = name;
            this.avatar = drawable;
            this.description = description;
            this.moreDesc = moreDesc;
            this.description2 = description2;

            font = ((int)(Math.random()*2)+1) == 1 ? fontone : fonttwo;
        }

        public String getString(){
            return this.name;
        }
        public Drawable getImage(){ return this.avatar; }
        public String getDesc(){
            return this.description;
        }
        public String getMoreDesc(){
            return this.moreDesc;
        }
        public String getDescription2(){
            return this.description2;
        }

        public Typeface getFont(){
            return this.font;
        }

    }


}
