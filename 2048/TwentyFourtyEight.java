import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.applet.Applet;
import java.net.*;
import java.applet.AudioClip;

//Yash Oza
//2048 Game
//May 20,2016

/*Bibliography:
Cirulli, Gabriele/ Romain Cousin. Adapted Tiles. Digital image. 2048. World Wide Web, Mobile Phone, Nintendo 3DS, n.d. Web. 5 June 2016.
Up/Down/Left/Right arrows: Fg-a.com. (2016). Free Arrow Gifs - Animated Arrows - Clipart. [online] Available at: http://www.fg-a.com/arrows.htm [Accessed 11 Jun. 2016].
Microsoft Incorporated. RPG 2048. Digital Image. n.d. Web. 5 June 2016, from https://www.microsoft.com/en-us/store/apps/2048-rpg-exclusive/9nblggh2wdds
2048 o Free on the Mac App Store. Mac App Store. N.p., n.d. Web. 06 June 2016.
National Geographic. Digital image. National Geography. National Geographic Partners, LLC., n.d. Web. 6 June 2016.
Classic Tiles:Cirulli, Gabriele. Classic Tiles. Digital image. 2048. World Wide Web, Mobile Phone, Nintendo 3DS, n.d. Web. 5 June 2016.
Nintendo Tiles/Marvel Tiles: 2048. 2048. N.p., n.d. Web. 06 June 2016, from http://allthe2048.com/
Romanch/Gif tiles: Romain Cousin. Animated Tiles. Digital image. 2048. World Wide Web, Mobile Phone, Nintendo 3DS, n.d. Web. 5 June 2016.
*/

public class TwentyFourtyEight extends Applet implements ActionListener

{
    int row = 4; //lenght on grid
    int col = 4; //width of grid
    int total = row * col; //total amount of buttons in the 1-D array
    JLabel[] a = new JLabel [total]; //array with all the tile pieces
    int pic[] [] = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}}; //2-D array that stores position of pieces/tiles
    int p = 1; //initialisation varible for printing 2 tiles instead of 1 on the first turn
    char y = 'n'; //variable that changes in accord to chosen theme to display various different tiles
    String w = ".jpg"; //initialisation of variable that hanges with coenciding pictures as some are jpg and others are gif
    int c = 0; //initialization of global varible as it is used in mutiple locations
    JButton nin, cool, marv; //theme JButtons (for setEnable purposes)
    int kol = 0; //variable for themes
    int lo = 0; //variable for themes
    int lok = 0; //variable for themes
    int ko = 0; //variable so that game doesn't check for winner more than once

    Panel p_card; //to hold all of the cards
    Panel card1, card2, card3, card4, card5;
    CardLayout cdLayout = new CardLayout ();

    AudioClip h; //audioclips initialization
    AudioClip f;
    AudioClip x;

    public void init ()
    {
	p_card = new Panel ();
	p_card.setLayout (cdLayout);
	open (); //opening method
	grid (); //method with the grid
	h = getAudioClip (getDocumentBase (), "weeknd.wav"); //music contained in each audioclip
	f = getAudioClip (getDocumentBase (), "pac.wav");
	x = getAudioClip (getDocumentBase (), "arrow.wav");
	set (); //settings method
	instruct (); //instruction method
	themes (); //theme method
	redraw (); //redraw method whih updates the grid
	resize (290, 360); //screen size
	setLayout (new BorderLayout ());
	add ("Center", p_card);
    }


    public void open ()
    {
	card1 = new Panel ();
	card1.setBackground (new Color (87, 64, 124));

	JLabel title = new JLabel ("2048"); //title
	title.setFont (new Font ("Arial", Font.BOLD, 34));
	title.setForeground (Color.white);

	JLabel pic = new JLabel (createImageIcon ("OP.gif")); //cool gif opening picture

	JButton play = new JButton ("New Game"); //play button leads to grid screen
	play.addActionListener (this);
	play.setActionCommand ("p");
	play.setForeground (Color.white);
	play.setBackground (new Color (61, 41, 99));
	play.setBorderPainted (false);

	JButton settings = new JButton ("Settings"); //settings button leads to settings screen
	settings.addActionListener (this);
	settings.setActionCommand ("s");
	settings.setForeground (Color.white);
	settings.setBackground (new Color (61, 41, 99));
	settings.setBorderPainted (false);

	JButton ins = new JButton ("Instructions"); //ins button leads to introdcution screen
	ins.addActionListener (this);
	ins.setActionCommand ("i");
	ins.setForeground (Color.white);
	ins.setBackground (new Color (61, 41, 99));
	ins.setBorderPainted (false);

	JButton theme = new JButton ("Themes"); //theme button leas to the theme screen
	theme.addActionListener (this);
	theme.setActionCommand ("z");
	theme.setForeground (Color.white);
	theme.setBackground (new Color (61, 41, 99));
	theme.setBorderPainted (false);

	Panel p = new Panel (new GridLayout (2, 2)); //adds all the buttons in a good lokking panel
	p.add (play);
	p.add (settings);
	p.add (ins);
	p.add (theme);

	card1.add (title); //add everything
	card1.add (pic);
	card1.add (p);
	p_card.add ("1", card1);

    }


    public void grid ()
    {
	card2 = new Panel ();
	card2.setBackground (new Color (87, 64, 124));

	Panel p = new Panel (new GridLayout (row, col)); //panel for grid
	resize (500, 400);
	a = new JLabel [total];

	for (int i = 0 ; i < total ; i++) //loop to print grid
	{
	    a [i] = new JLabel ("");
	    a [i].setBackground (Color.white);
	    a [i].setPreferredSize (new Dimension (60, 63));
	    p.add (a [i]);
	}
	card2.add (p);

	JButton up = new JButton (createImageIcon ("up.gif")); //move tiles up
	up.addActionListener (this);
	up.setActionCommand ("u");
	up.setForeground (Color.white);
	up.setBackground (new Color (61, 41, 99));
	up.setBorderPainted (false);

	JButton down = new JButton (createImageIcon ("down.gif")); //move tiles down
	down.addActionListener (this);
	down.setActionCommand ("d");
	down.setForeground (Color.white);
	down.setBackground (new Color (61, 41, 99));
	down.setBorderPainted (false);

	JButton left = new JButton (createImageIcon ("left.gif")); //move tiles left
	left.addActionListener (this);
	left.setActionCommand ("l");
	left.setForeground (Color.white);
	left.setBackground (new Color (61, 41, 99));
	left.setBorderPainted (false);

	JButton right = new JButton (createImageIcon ("right.gif")); //move tiles right
	right.addActionListener (this);
	right.setActionCommand ("r");
	right.setForeground (Color.white);
	right.setBackground (new Color (61, 41, 99));
	right.setBorderPainted (false);

	Panel p1 = new Panel (new GridLayout (2, 1));
	p1.add (up);
	p1.add (down);

	JButton reset = new JButton ("New Game?"); //goes back to main screen and resets the grid
	reset.addActionListener (this);
	reset.setActionCommand ("-1");
	reset.setForeground (Color.white);
	reset.setBackground (new Color (61, 41, 99));
	reset.setBorderPainted (false);

	JButton ins = new JButton ("     Instructions     "); //simply goes back to the opening screen
	ins.addActionListener (this);
	ins.setActionCommand ("i");
	ins.setForeground (Color.white);
	ins.setBackground (new Color (61, 41, 99));
	ins.setBorderPainted (false);

	card2.add (reset); //add all the buttons
	card2.add (left);
	card2.add (p1);
	card2.add (right);
	card2.add (ins);
	card2.add (reset);
	p_card.add ("2", card2);
    }


    public void set ()
    {
	card3 = new Panel ();
	card3.setBackground (new Color (87, 64, 124));

	JLabel wel = new JLabel ("Welcome to the Settings screen"); //title
	wel.setForeground (Color.white);
	wel.setFont (new Font ("Arial", Font.BOLD, 14));

	JLabel pic = new JLabel (createImageIcon ("opening.jpg")); //opening image

	JButton original = new JButton ("Change to original settings?"); //swithes theme to the original one
	original.addActionListener (this);
	original.setActionCommand ("0");
	original.setForeground (Color.white);
	original.setBackground (new Color (61, 41, 99));
	original.setBorderPainted (false);

	JButton reset = new JButton ("Home"); //move back to the opening screen
	reset.addActionListener (this);
	reset.setActionCommand ("-1");
	reset.setForeground (Color.white);
	reset.setBackground (new Color (61, 41, 99));
	reset.setBorderPainted (false);

	card3.add (wel); //add buttons to screen
	card3.add (pic);
	card3.add (original);
	card3.add (reset);
	p_card.add ("3", card3);
    }


    public void instruct ()
    {
	card4 = new Panel ();
	card4.setBackground (new Color (87, 64, 124));

	JLabel inst = new JLabel ("Use the arrow buttons to move the tiles."); //instrutions
	inst.setFont (new Font ("Arial", Font.BOLD, 14));
	inst.setForeground (Color.white);

	JLabel inst2 = new JLabel ("When two tiles with the same number"); //instuctions continued
	inst2.setFont (new Font ("Arial", Font.BOLD, 14));
	inst2.setForeground (Color.white);

	JLabel inst3 = new JLabel ("touch, they merge into one!"); //instutions continued even more
	inst3.setFont (new Font ("Arial", Font.BOLD, 14));
	inst3.setForeground (Color.white);

	JLabel inst4 = new JLabel ("To restart the game, click");
	inst4.setFont (new Font ("Arial", Font.BOLD, 14));
	inst4.setForeground (Color.white);

	JLabel inst5 = new JLabel ("the 'Rage Quit' button");
	inst5.setFont (new Font ("Arial", Font.BOLD, 14));
	inst5.setForeground (Color.white);

	JLabel pic = new JLabel (createImageIcon ("ins.jpg")); //add intro pic

	JButton reset = new JButton ("Home"); //move back to game screen
	reset.addActionListener (this);
	reset.setActionCommand ("-1");
	reset.setForeground (Color.white);
	reset.setBackground (new Color (61, 41, 99));
	reset.setBorderPainted (false);

	JButton play = new JButton ("Load Game");
	play.addActionListener (this);
	play.setActionCommand ("q");
	play.setForeground (Color.white);
	play.setBackground (new Color (61, 41, 99));
	play.setBorderPainted (false);
	Panel p = new Panel (new GridLayout (1, 2));
	p.add (reset);
	p.add (play);

	card4.add (inst); //add buttons to screen
	card4.add (inst2);
	card4.add (inst3);
	card4.add (pic);
	card4.add (inst4);
	card4.add (inst5);
	card4.add (p);
	p_card.add ("4", card4);
    }


    public void themes ()
    {
	card5 = new Panel ();
	card5.setBackground (new Color (87, 64, 124));

	JLabel title = new JLabel ("Choose whichever theme"); //themes title
	title.setFont (new Font ("Arial", Font.BOLD, 14));
	title.setForeground (Color.white);

	JLabel title2 = new JLabel ("you want from the following:"); //options
	title2.setFont (new Font ("Arial", Font.BOLD, 14));
	title2.setForeground (Color.white);

	JLabel pic = new JLabel (createImageIcon ("themes.jpg")); //cool picture

	JLabel info = new JLabel ("Pass through certain checkpoints");
	info.setFont (new Font ("Arial", Font.BOLD, 12));
	info.setForeground (Color.white);

	JLabel info2 = new JLabel ("And Unlock these rad themes!");
	info2.setFont (new Font ("Arial", Font.BOLD, 12));
	info2.setForeground (Color.white);

	nin = new JButton ("Nintendo theme"); //change to special nintendo tiles
	nin.addActionListener (this);
	nin.setActionCommand ("1");
	nin.setForeground (Color.white);
	nin.setBackground (new Color (61, 41, 99));
	nin.setBorderPainted (false);
	nin.setEnabled (false);

	cool = new JButton ("Romanch theme"); //change to special romanch titles
	cool.addActionListener (this);
	cool.setActionCommand ("2");
	cool.setForeground (Color.white);
	cool.setBackground (new Color (61, 41, 99));
	cool.setBorderPainted (false);
	cool.setEnabled (false);

	marv = new JButton ("Marvel theme"); //change to special marvel tiles
	marv.addActionListener (this);
	marv.setActionCommand ("3");
	marv.setForeground (Color.white);
	marv.setBackground (new Color (61, 41, 99));
	marv.setBorderPainted (false);
	marv.setEnabled (false);

	JButton reset = new JButton ("Home"); //game screen
	reset.addActionListener (this);
	reset.setActionCommand ("-1");
	reset.setForeground (Color.white);
	reset.setBackground (new Color (61, 41, 99));
	reset.setBorderPainted (false);

	card5.add (title); //add buttons to grid
	card5.add (title2);
	card5.add (pic);
	card5.add (info);
	card5.add (info2);
	card5.add (nin);
	card5.add (cool);
	card5.add (marv);
	card5.add (reset);
	p_card.add ("5", card5);
    }


    public void redraw ()  //with for loop print out tiles in the grid after changes are made by the player each turn
    {
	int move = 0;
	for (int i = 0 ; i < 4 ; i++)
	{
	    for (int j = 0 ; j < 4 ; j++)
	    {
		a [move].setIcon (createImageIcon ("" + y + "" + pic [i] [j] + "" + w));
		move++;
	    }
	}
    }


    public boolean gameover ()  //checks if the user has lost
    {
	int a = 0;
	int b = 0;
	c = 0;

	for (int i = 0 ; i < 4 ; i++)
	{
	    for (int j = 0 ; j < 3 ; j++)
	    {
		if (pic [i] [j] == pic [i] [j + 1])
		    a = 1;
	    }
	}

	for (int i = 0 ; i < 3 ; i++)
	{
	    for (int j = 0 ; j < 4 ; j++)
	    {
		if (pic [i] [j] == pic [i + 1] [j])
		    b = 1;
	    }
	}

	for (int i = 0 ; i < 4 ; i++)
	{
	    for (int j = 0 ; j < 4 ; j++)
	    {
		if (pic [i] [j] == 1)
		    c = 1;
	    }
	}

	if ((a == 1) || (b == 1) || (c == 1)) //all 3 conditions have to be satisfied for the user to lose (input starts at 0, so change has happenned to make it 1)
	    return false;
	else
	    return true;
    }


    public void left ()  //left option code
    {
	for (int i = 0 ; i < 4 ; i++) //combines the tiles
	{
	    for (int j = 3 ; j > 0 ; j--)
	    {
		if ((pic [i] [j] == pic [i] [j - 1]) && (pic [i] [j] != 1))
		{
		    pic [i] [j - 1] = 2 * pic [i] [j - 1];
		    pic [i] [j] = 1;
		}

		if (pic [i] [j - 1] == 1)
		{
		    pic [i] [j - 1] = pic [i] [j];
		    pic [i] [j] = 1;
		}
	    }
	}

	for (int i = 0 ; i < 4 ; i++) //moves pieces all nicely to the left
	{
	    for (int j = 1 ; j < 4 ; j++)
	    {
		if (pic [i] [j - 1] == 1)
		{
		    pic [i] [j - 1] = pic [i] [j];
		    pic [i] [j] = 1;
		}
	    }
	}
    }


    public void right ()  //right option
    {
	for (int i = 0 ; i < 4 ; i++)
	{
	    for (int j = 0 ; j < 3 ; j++) //combines all tiles nicely to the right
	    {
		if ((pic [i] [j] == pic [i] [j + 1]) && (pic [i] [j] != 1))
		{
		    pic [i] [j + 1] = 2 * pic [i] [j + 1];
		    pic [i] [j] = 1;
		}

		if (pic [i] [j + 1] == 1)
		{
		    pic [i] [j + 1] = pic [i] [j];
		    pic [i] [j] = 1;
		}
	    }
	}

	for (int i = 0 ; i < 4 ; i++) //moves all tiles to the right
	{
	    for (int j = 2 ; j >= 0 ; j--)
	    {
		if (pic [i] [j + 1] == 1)
		{
		    pic [i] [j + 1] = pic [i] [j];
		    pic [i] [j] = 1;
		}
	    }
	}
    }


    public void up ()  //up option
    {
	for (int i = 3 ; i > 0 ; i--)
	{
	    for (int j = 0 ; j < 4 ; j++) //combine3s all similar tiles up
	    {
		if ((pic [i] [j] == pic [i - 1] [j]) && (pic [i] [j] != 1))
		{
		    pic [i - 1] [j] = 2 * pic [i - 1] [j];
		    pic [i] [j] = 1;
		}

		if (pic [i - 1] [j] == 1)
		{
		    pic [i - 1] [j] = pic [i] [j];
		    pic [i] [j] = 1;
		}
	    }
	}

	for (int i = 0 ; i < 3 ; i++) //moves all tiles up
	{
	    for (int j = 0 ; j < 4 ; j++)
	    {
		if (pic [i] [j] == 1)
		{
		    pic [i] [j] = pic [i + 1] [j];
		    pic [i + 1] [j] = 1;
		}
	    }
	}
    }


    public void down ()  //down option/code
    {
	for (int i = 0 ; i < 3 ; i++)
	{
	    for (int j = 0 ; j < 4 ; j++) //combines all tiles down
	    {
		if ((pic [i] [j] == pic [i + 1] [j]) && (pic [i] [j] != 1))
		{
		    pic [i + 1] [j] = 2 * pic [i + 1] [j];
		    pic [i] [j] = 1;
		}

		if (pic [i + 1] [j] == 1)
		{
		    pic [i + 1] [j] = pic [i] [j];
		    pic [i] [j] = 1;
		}
	    }
	}
	for (int i = 2 ; i > -1 ; i--) //moves tiles down
	{
	    for (int j = 0 ; j < 4 ; j++)
	    {
		if (pic [i + 1] [j] == 1)
		{
		    pic [i + 1] [j] = pic [i] [j];
		    pic [i] [j] = 1;
		}
	    }
	}
    }


    public void piecedraw ()  //occurs to make a new tile show up
    {
	if (c == 1)
	{
	    int n = (int) (Math.random () * 4);
	    int n1 = (int) (Math.random () * 4);

	    while (pic [n] [n1] != 1)
	    {
		n = (int) (Math.random () * 4);
		n1 = (int) (Math.random () * 4);
	    }

	    if (pic [n] [n1] == 1)
	    {
		a [n * n1 + 1].setIcon (createImageIcon ("" + y + "2" + w));
		pic [n] [n1] = 2;

		if (p == 1) //only happens in the beggining or at the reset where 2 tiles are printed to the grid instead of just 1
		{
		    while (pic [n] [n1] != 1)
		    {
			n = (int) (Math.random () * 4);
			n1 = (int) (Math.random () * 4);
		    }
		    a [n * n1 + 1].setIcon (createImageIcon ("" + y + "2" + w));
		    pic [n] [n1] = 2;
		    p = 2;
		}
	    }
	    redraw (); //update the grid
	}
    }


    public void blank ()  //reset the grid to be black
    {
	for (int i = 0 ; i < 4 ; i++)
	{
	    for (int j = 0 ; j < 4 ; j++)
	    {
		pic [i] [j] = 1;
	    }
	}
	redraw (); //print out the resetted values
    }


    public void checkpoints ()  //has special checkpoints and awards for the game and checks for winner
    {
	for (int i = 0 ; i < 4 ; i++)
	{
	    for (int j = 0 ; j < 4 ; j++)
	    {
		if (pic [i] [j] == 256 && lok == 0) //nintendo checkpoint unlock at tile 256
		{
		    nin.setEnabled (true);
		    JOptionPane.showMessageDialog (null, "You have unlocked the special Nintendo theme. Congrats!", "GG", JOptionPane.ERROR_MESSAGE); //shows the user thier accomplishment
		    lok = 1;
		}

		else if (pic [i] [j] == 512 && lo == 0) //marvel checkpoint unlock at tile 256
		{
		    marv.setEnabled (true);
		    JOptionPane.showMessageDialog (null, "You have unlocked the special Marvel theme. Congrats!", "GG", JOptionPane.ERROR_MESSAGE); //shows the user thier accomplishment
		    lo = 1;
		}

		else if (pic [i] [j] == 1024 && kol == 0) //Romanch checkpoint unlock at tile 256
		{
		    cool.setEnabled (true);
		    JOptionPane.showMessageDialog (null, "You have unlocked the special Romanch theme. Congrats!", "GG", JOptionPane.ERROR_MESSAGE); //shows the user thier accomplishment
		    kol = 1;
		}
		else if (pic [i] [j] == 2048 && ko == 0)
		{
		    JOptionPane.showMessageDialog (null, "You have won the game! Congrats!", "GG", JOptionPane.ERROR_MESSAGE); //shows the user they have won
		    ko = 1;
		    cdLayout.show (p_card, "1");
		    blank ();
		    p = 1;
		}

	    }
	}
	loser (); //checks for loser
    }


    public void loser ()  //with gameover checks for loser
    {
	if (gameover () == true)
	{
	    JOptionPane.showMessageDialog (null, "Game Over! You Lose!", "Loser!", JOptionPane.ERROR_MESSAGE); //shows the user that they have lost
	    cdLayout.show (p_card, "1");
	    blank ();
	    p = 1;

	}

	else
	    piecedraw (); //if not puts a new tiles on the grid
    }


    public void rage ()  //when the user wants to reset the game without winning or losing
    {
	cdLayout.show (p_card, "1");
	h.stop ();
	f.play ();
    }


    public void play ()  //move to play screen
    {
	cdLayout.show (p_card, "2");
	ko = 0;
	f.stop ();
	h.loop ();
    }


    public void marvel ()  //marvel themed tile code changes
    {
	y = 'm';
	w = ".jpg";
	x.play ();
    }


    public void romanch ()  //romanch themed tile code changes
    {
	y = 'r';
	w = ".gif";
	x.play ();
    }


    public void nintendo ()  //nintendo themed tile code changes
    {
	y = 'd';
	w = ".jpg";
	x.play ();
    }


    public void classic ()  //original/classi themed tile changed
    {
	y = 'n';
	w = ".jpg";
	x.play ();
    }


    public void actionPerformed (ActionEvent e)
    {
	if (e.getActionCommand ().equals ("p")) //play game
	    play ();
	else if (e.getActionCommand ().equals ("s")) //settings
	    cdLayout.show (p_card, "3");
	else if (e.getActionCommand ().equals ("i")) //instruction
	    cdLayout.show (p_card, "4");
	else if (e.getActionCommand ().equals ("z")) //themes
	    cdLayout.show (p_card, "5");
	else if (e.getActionCommand ().equals ("0")) //change back to oringal settings
	    classic ();
	else if (e.getActionCommand ().equals ("1")) //nintendo theme tile changes
	    nintendo ();
	else if (e.getActionCommand ().equals ("2")) //romanch theme tile changes
	    romanch ();
	else if (e.getActionCommand ().equals ("3")) //marvel theme tile changes
	    marvel ();
	else if (e.getActionCommand ().equals ("-1")) //user resets
	{
	    if (e.getActionCommand ().equals ("-1"))
		blank (); //if user does not temporary save, all tiles become blank
	    rage ();
	}
	else if (e.getActionCommand ().equals ("l")) //left move
	    left ();
	else if (e.getActionCommand ().equals ("r")) //right move
	    right ();
	else if (e.getActionCommand ().equals ("u")) //up move
	    up ();
	else if (e.getActionCommand ().equals ("d")) //down move
	    down ();
	if (e.getActionCommand ().equals ("q")) //left move
	    cdLayout.show (p_card, "2");
	else
	    checkpoints (); //unlocks special themes
    }


    protected static ImageIcon createImageIcon (String path)  //pictures
    {
	java.net.URL imgURL = TwentyFourtyEight.class.getResource (path);
	if (imgURL != null)
	{
	    return new ImageIcon (imgURL);
	}


	else
	{
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    }
}
