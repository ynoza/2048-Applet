import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;


public class music extends Applet implements ActionListener

{
    AudioClip a;

    public void init ()
    {
	JButton yo = new JButton ("Play");
	yo.addActionListener (this);
	yo.setActionCommand ("p");
	add (yo);
	a = getAudioClip (getDocumentBase (), "aladdin.wav");

    }



    public void actionPerformed (ActionEvent e)
    {

	a.play ();

    }
}
