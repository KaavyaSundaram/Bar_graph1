package com.example.kaavyasundaram.bar_graph;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Graphdata implements Serializable {
    //canvas parameters
    int cx_point;
    int cy_point;
    int c_width;
    int c_height;
    //vertical line x and y inputs
    int xpoint;
    int ypoint;


    //bar width & bar count
    int bar_count;
    int bar_width;

    //X and Y inputs to write near the axis
    List<Integer> xip;
    List<Integer> yip;

    //X and Y inputs to plot the values
    List<Integer> x_point;
    List<Integer> y_point;
    List<Integer> r_colors;




    public Graphdata(int cx_point, int cy_point, int c_width, int c_height, int xpoint, int ypoint, int bar_count, int bar_width, List<Integer> xi, List<Integer> yi, List<Integer> x, List<Integer> y, List<Integer> r_colors) {
        this.cx_point = cx_point;
        this.cy_point = cy_point;
        this.c_width = c_width;
        this.c_height = c_height;

        this.xpoint = xpoint;
        this.ypoint = ypoint;






        this.bar_count = bar_count;
        this.bar_width = bar_width;

        this.xip = xi;
        this.yip = yi;

        this.x_point = x;
        this.y_point = y;
        this.r_colors = r_colors;
    }

   //Getter and setter for the canvas

    public int getCx_point() {
        return cx_point;
    }

    public void setCx_point(int cx_point) {
        this.cx_point = cx_point;
    }

    public int getCy_point() {
        return cy_point;
    }

    public void setCy_point(int cy_point) {
        this.cy_point = cy_point;
    }

    public int getC_width() {
        return c_width;
    }

    public void setC_width(int c_width) {
        this.c_width = c_width;
    }

    public int getC_height() {
        return c_height;
    }

    public void setC_height(int c_height) {
        this.c_height = c_height;
    }


    //X and Y axis inputs for Start X and Y point inside the canvas

    public int getXpoint() {
        return xpoint;
    }

    public void setXpoint(int xpoint) {
        this.xpoint = xpoint;
    }

    public int getYpoint() {
        return ypoint;
    }

    public void setYpoint(int ypoint) {
        this.ypoint = ypoint;
    }


    //Bar width getter and setter
    public int getBar_count() {
        return bar_count;
    }

    public void setBar_count(int bar_count) {
        this.bar_count = bar_count;
    }

    public int getBar_width() {
        return bar_width;
    }

    public void setBar_width(int bar_width) {
        this.bar_width = bar_width;
    }

    //List of X and Y inputs to write near the line

    public List<Integer> getXip() {
        return xip;
    }

    public void setXip(List<Integer> xip) {
        this.xip = xip;
    }
    public List<Integer> getYip() {
        return yip;
    }

    public void setYip(List<Integer> yip) {
        this.yip = yip;
    }



    //List begins here for plotting the X and Y coordinates and the color of the bar

    public List<Integer> getX_point() {
        return x_point;
    }

    public void setX_point(List<Integer> x_point) {
        this.x_point = x_point;
    }

    public List<Integer> getY_point() {
        return y_point;
    }

    public void setY_point(List<Integer> y_point) {
        this.y_point = y_point;
    }

    public List<Integer> getR_colors() {
        return r_colors;
    }

    public void setR_colors(List<Integer> r_colors) {
        this.r_colors = r_colors;
    }


}
