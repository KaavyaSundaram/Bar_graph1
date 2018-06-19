package com.example.kaavyasundaram.bar_graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Bview extends View {

    private Paint paint;
    Graphdata data;
    List<Integer> x_copy, y_copy;

    public Bview(Context context) {
        super(context);
        paint = new Paint();
        paint.setTextSize(40);
        paint.setColor(Color.BLACK);

    }

    //To get the values under the object and store it into the another object

    public void setGraphdata(Graphdata graphdata) {
        data = graphdata;
        postInvalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        if (data != null) {

            canvas.drawColor(Color.TRANSPARENT);
            canvas.drawLine(data.getXpoint(), data.getYpoint(), data.getXpoint(), data.getC_height() - data.getYpoint(), paint);
            canvas.drawLine(data.getXpoint(), data.getC_height() - data.getYpoint(), data.getC_width() - data.getXpoint(), data.getC_height() - data.getXpoint(), paint);
            //Function call
            xyinteger(data.getX_point(),data.getY_point(),canvas);







     /*    for (int j = 0; j < data.getY_point().size(); j++) {
             int x = data.getX_point().get(j);
             int y =data.getY_point().get(j);
             int xc = (int) hrec.get(x);
             int yc = (int) vrec.get(y);
             paint.setColor(data.getR_colors().get(j));
             canvas.drawRect(xc,yc,data.getBar_width()+xc,ystp,paint);
         }*/
        }
        else{
            return;
        }
    }

    private void xyinteger(List<Integer> x_point, List<Integer> y_point, Canvas canvas) {
        //To copy the values of X_points into x_copy and sort the x_copy

        x_copy = new ArrayList<>(data.getX_point());
        Collections.sort(x_copy);

        //After sorting finding the minimum and maximum values of X
        int min_x = x_copy.get(0);
        int max_x = x_copy.get(x_copy.size() - 1);

        //To copy the values of Y_points into x_copy and sort the y_copy
        y_copy = new ArrayList<>(data.getY_point());
        Collections.sort(y_copy);

        //After sorting finding the minimum and maximum values of X
        int min_y = y_copy.get(0);
        int max_y = y_copy.get(y_copy.size() - 1);


        //To get the scaling of X values

        int xtemp;
        int xsplit = max_x - min_x;
        int xinc = xsplit / data.getX_point().size();
        xtemp = min_x;
        ArrayList xscale = new ArrayList();
        for (; xtemp <= max_x; xtemp = xtemp + xinc) {
            xscale.add(xtemp);
        }
        xscale.add(xtemp);
//            for (; ; ) {
//                if (xtemp <= max_x) {
//                    xscale.add(xtemp);
//                    xtemp = xtemp + xinc;
//                } else {
//                    xscale.add(xtemp);
//                    break;
//                }
//            }

        //To get the scaling of y values
        int ytemp;
        int ysplit = max_y - min_y;// (6-1=5)
        int yinc = ysplit / data.getY_point().size();//5/5=1
        ytemp = min_y;
        ArrayList yscale = new ArrayList();
        for(;ytemp<=max_y;ytemp=ytemp+yinc){
            yscale.add(ytemp);
        }
        yscale.add(ytemp);

//            for (; ; ) {
//                if (ytemp <= max_y) {
//                    yscale.add(ytemp);
//                    ytemp = ytemp + yinc;
//                } else {
//                    yscale.add(ytemp);
//                    break;
//                }
//            }



        //Horizontal line division and plotting points in the horizontal line and drawing bars corresponding to the Y-inputs & Text scaling dynamically
        paint.setTextSize(40);
        int xpt = data.getC_width() - data.getXpoint();
        int xhigh = xpt - data.getXpoint();
        int xdiv = (int) xhigh / xscale.size();
        int xx = data.getXpoint();

        HashMap<Integer, Integer> hrec = new HashMap<Integer, Integer>();

        for (int i = 0; i < xscale.size(); i++) {
            paint.setColor(Color.BLACK);
            paint.setTextSize(40);
            xx = xdiv + xx;
            canvas.drawCircle(xx, xpt, 5, paint);
            while(paint.measureText(String.valueOf(xscale.get(i)))>xdiv){
                paint.setTextSize(paint.getTextSize()-1);
            }
            canvas.drawText(Integer.toString((Integer) xscale.get(i)), xx-(paint.measureText(String.valueOf(xscale.get(i)))/2), data.getC_height()-20 , paint);
            hrec.put((Integer) xscale.get(i), xx);
        }
        paint.setTextSize(40);

        //Vertical line division and plotting points in the vertical line & Text scaling dynamically
        int ystp = data.getC_height() - data.getYpoint();
        int yhigh = ystp - data.getYpoint();
        int div = yhigh / yscale.size();
        int yy = ystp;

        HashMap<Integer, Integer> vrec = new HashMap<Integer, Integer>();

        for (int i = 0; i < yscale.size(); i++) {
            paint.setColor(Color.BLACK);
            paint.setTextSize(40);
            yy = yy - div;
            canvas.drawCircle(data.getXpoint(), yy, 5, paint);
            //canvas.drawLine(data.getXpoint(), yy, data.getXpoint()+10,yy, paint);
            canvas.drawText(String.valueOf(yscale.get(i)), 0, yy, paint);
            //canvas.drawText(Integer.toString((Integer) yscale.get(i)), 20,yy-(paint.measureText(String.valueOf(yscale.get(i)))/2) , paint);
            vrec.put((Integer) yscale.get(i), yy);
        }
        paint.setTextSize(40);


        //To plot the given points
        for (int i=0;i<data.getX_point().size();i++) {
            int xs=data.getBar_width()/2;

            if (hrec.containsKey(data.getX_point().get(i)) && vrec.containsKey(data.getY_point().get(i))) {
                //canvas.drawCircle(hrec.get(data.getX_point().get(i)),vrec.get(data.getY_point().get(i)),5,paint);
                paint.setColor(data.getR_colors().get(i));
                canvas.drawRect(hrec.get(data.getX_point().get(i))-xs,vrec.get(data.getY_point().get(i)),hrec.get(data.getX_point().get(i))+xs,ystp,paint);

            } else {
                int x=xinc-1,x_plot=0;
                while(!hrec.containsKey(data.getX_point().get(i)-x)){ x--;
                }

                if(hrec.containsKey(data.getX_point().get(i)-x)){
                    x_plot=hrec.get(data.getX_point().get(i)-x)+(xdiv/xinc)*x;
                }
                int y=yinc-1,y_plot=0;
                while(!vrec.containsKey(data.getY_point().get(i)-y)){ y--; }
                if(vrec.containsKey(data.getY_point().get(i)-y)){
                    y_plot=vrec.get(data.getY_point().get(i)-y)-(div/yinc)*y;
                }
                //canvas.drawCircle(x_plot,y_plot,5,paint);
                paint.setColor(data.getR_colors().get(i));
                canvas.drawRect(x_plot-xs,y_plot,x_plot+xs,ystp,paint);

            }
        }
    }

}













  /*
      for(int i=0;i<data.getX_point().size();i++) {
            // i=Color.parseColor(i);

             //canvas.drawCircle(data.getX_point().get(i),data.getHeight(),5,paint);
           //  canvas.drawText(Integer.toString(data.getX_point().get(i)), 50, 1050, paint);

             paint.setColor(colors[i]);
             canvas.drawRect(data.getX_point().get(i), data.getY_point().get(i), data.getXyheight().get(i), data.getX_point().get(i)+data.getBar_width(),paint);


         }*/
        /* for(int i=0;i<data.getX_point().size();i++) {
             paint.setColor(Color.BLACK);

             canvas.drawCircle(data.getX_point().get(i), data.getHeight(), 5, paint);//xpoints
            // canvas.drawText(Integer.toString(data.getX_point().get(i)), data.getX_point().get(i), 1050, paint);//xpoints

         }*/


// canvas.drawLine(200, 200, 200,900, paint);//canvas.drawLine(200, 900, 900,900, paint);
        /*int x=100,y=1000;
        for(int i=0;i<10;i++)
        {
            // canvas.drawLine(x,y,x,y, paint);
            canvas.drawCircle(x, y, 5, paint);
            canvas.drawText(Integer.toString(x), x, 1050, paint);
            x=x+100;
        }
        int xx=100,yy=1000;
        for(int i=0;i<10;i++)
        {
            // canvas.drawLine(x,y,x,y, paint);
            canvas.drawCircle(xx,yy, 5, paint);
            canvas.drawText(Integer.toString(yy), 50, yy, paint);
            yy=yy-100;
        }

        int []xaxis={350,500,450,330,620,800,745,430};
        int []colors= {
                0xFFFF0000, 0xFFFF00FF, 0xFF0000FF, 0xFF00FFFF, 0xFF00FF00,
                0xFFFFFF00, 0xFFFF0000,0xFFFF0000
        };
        int rx=200,ry=1000;
        for(int i=0;i<8;i++)
        {
            // canvas.drawLine(x,y,x,y, paint);
            canvas.drawRect(rx,xaxis[i],rx+30, ry, paint);
            paint.setColor(colors[i]);
            rx=rx+100;

        }*/