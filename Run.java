import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
public class Run{
    public static void main(String[] args)
    {
        GridMaker bars = new GridMaker(30,30);
        bars.run();
        //System.out.println(bars.bombCheck(20, 3));
        /*
        for(int i  = 0; i < 30; i++)
            for(int j = 0; j < 30; j++)
                System.out.println("i: "+ i + " j: "+ j + " " + bars.bombCheck(i, j));
                */
        //System.out.print(bars.toString());
    }
}
