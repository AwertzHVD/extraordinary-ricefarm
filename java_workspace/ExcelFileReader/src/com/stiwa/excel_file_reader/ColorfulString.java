package com.stiwa.excel_file_reader;
import java.awt.*;
import java.awt.event.*;
  
public class ColorfulString extends Panel {
    private static final Color[] colorArray = {
        Color.black,
        Color.red,
        Color.blue,
        Color.yellow,
        Color.green };
    private int colorIndex;
    private String s;
  
    public ColorfulString() {
        colorIndex = 0;
        s = "Java is the best!";
    }
  
    public void paint( Graphics g ) {
        char c = ' ';
        colorIndex = 0;
        Point loc = new Point( 10, 10 );
        for( int i = 0; i < s.length(); i++ ) {
            c = s.charAt( i );
            g.setColor( colorArray[ colorIndex ] );
            int width = g.getFontMetrics().charWidth( c );
            g.drawString( "" + c, loc.x, loc.y );
            loc.x += width;
            colorIndex++;
            if( colorIndex >= colorArray.length ) {
                colorIndex = 0;
            }
        }
    }
  
    public static void main( String[] args ) {
        ColorfulString cs = new ColorfulString();
        if( args.length > 0 && args[ 0 ] != null ) {
            cs.s = args[ 0 ];
        }
        Frame f = new Frame();
        f.add( cs, BorderLayout.CENTER );
        f.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent e ) {
                System.exit( 0 );
            }
        } );
        f.setSize( 400, 400 );
        f.setVisible( true );
    }
}