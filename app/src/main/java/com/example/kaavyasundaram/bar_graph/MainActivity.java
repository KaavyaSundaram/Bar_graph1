package com.example.kaavyasundaram.bar_graph;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
   int cx_start,cy_start,c_width,c_height;
  int xpoint,ypoint,bar_count,bar_width,rxpoint,rypoint;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //Initialization of the Array list
        List<Integer> y_ip=new ArrayList<>();
        List<Integer> x_ip=new ArrayList<>();
        List<Integer> x_points=new ArrayList<>();
        List<Integer> y_points=new ArrayList<>();
        List<Integer> r_colors=new ArrayList<>();

        try {
            //To open the .xml file
            InputStream is = getAssets().open("data.xml");

            //Parsing the values using DOM parser
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            Element element=doc.getDocumentElement();
            element.normalize();

            //To read the values under the tag canvas

            NodeList nList = doc.getElementsByTagName("canvas");

            Node node = nList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element can = (Element) node;
                cx_start=(Integer.parseInt(getValue("cx_start",can)));
                cy_start=(Integer.parseInt(getValue("cy_start",can)));
                c_width=(Integer.parseInt(getValue("c_width",can)));
                c_height=(Integer.parseInt(getValue("c_height",can)));

            }
                //To read the values under the tag "LINE_XY"

                nList=doc.getElementsByTagName("line_xy");
                node = nList.item(0);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;

                     //To get the start point of X & Y axis inside the canvas

                     xpoint = Integer.parseInt(getValue("xpoint", element2));
                     ypoint= Integer.parseInt(getValue("ypoint", element2));

                    //To get the width of the bar
                    bar_count=Integer.parseInt(getValue("bar_count",element2));
                    bar_width=Integer.parseInt(getValue("bar_width", element2));

                }

            //To read the values under the tag "RECT"

            nList=doc.getElementsByTagName("rect");
            for(int i=0;i<nList.getLength();i++){
                Element ele=(Element) nList.item(i);
                x_points.add(Integer.parseInt(getValue("rxpoint",ele)));
                y_points.add(Integer.parseInt(getValue("rypoint",ele)));
                r_colors.add(Color.parseColor(getValue("rcolor",ele)));
            }

            //to get the x_list of inputs given under x_inputs
            nList=doc.getElementsByTagName("xinputs");
            for(int i=0;i<nList.getLength();i++){
                Element ele=(Element) nList.item(i);
                x_ip.add(Integer.parseInt(getValue("xip",ele)));

            }
            //to get the y_list of inputs given under y_inputs
            nList=doc.getElementsByTagName("yinputs");
            for(int i=0;i<nList.getLength();i++){
                Element ele=(Element) nList.item(i);
               y_ip.add(Integer.parseInt(getValue("yip",ele)));

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);

        Bview bview=new Bview(this);
        Graphdata graphdata=new Graphdata(cx_start,cy_start,c_width,c_height,xpoint,ypoint,bar_count,bar_width,x_ip,y_ip,x_points,y_points,r_colors);
        bview.setGraphdata(graphdata);

        //To resize the canvas

        RelativeLayout rel=(RelativeLayout)findViewById(R.id.rel);
        RelativeLayout.LayoutParams rl=new RelativeLayout.LayoutParams(graphdata.getC_width(),graphdata.getC_height());
        rl.setMargins(graphdata.getCx_point(),graphdata.getCy_point(),0,0);
        bview.setLayoutParams(rl);
        bview.setBackgroundColor(Color.GRAY);
        rel.addView(bview);
    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

}







