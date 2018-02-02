package com.jackchen_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TextView text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_view = (TextView) findViewById(R.id.text_view);
        Log.e("TAG" , "height1 -> "+text_view.getMeasuredHeight()) ;   //height1 -> 0


        //这种情况是不能获取view高度的 , 因为传递的null,是还没有将view添加到布局里边
        View view = View.inflate(this , R.layout.activity_main , null) ;

        //这种情况是可以获取view高度的 , 需要传递ViewGroup ,
//        View view = View.inflate(this , R.layout.activity_main , text_view) ;


        text_view.post(new Runnable() {
        //只是把Runnable保存到queue<队列>中，什么都没干，会在dispatchAttachedToWindow中执行，而这个方法会在测量完毕后调用，然后在这个方法的下边会执行executeActions()
            @Override
            public void run() {
               Log.e("TAG" , "height2 -> "+text_view.getMeasuredHeight()) ; //height2 -> 1710
            }
        }) ;
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("TAG" , "height3 -> "+text_view.getMeasuredHeight()) ; //height3 -> 0
    }
}
