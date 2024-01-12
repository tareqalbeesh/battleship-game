package mainclass;
/////the bad version
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App extends JFrame implements ActionListener, MouseListener, MouseMotionListener,Serializable {

	
	private JButton player_hits,computer_hits,winner,scoreBoard;
	private JLabel game_id1,game_id2,start_time1,start_time2,end_time1,end_time2;
	private JLabel c_label, p_label;
	private JButton submit;
	private JTextField file_name;
	private JButton hp_grid[][];
    private App cp_history_gui, my_history_gui,player_hits_gui,computer_hits_gui;
    private JTextField tf1, tf2;
    private JButton play, grid[][], put_ships_grid[][], my_grid[][], cp_grid[][], Exit, Options, Ok, BomB , pass;
    private JLabel label1, label2, label3 , mylabel;
    private JComboBox<String> record_files;
    private JComboBox  g1, g2 ;
    private JCheckBox Random, Smart;
    private ButtonGroup g;
    private JButton battleShip, Destroyer, Cruiser, Carrier, Submarine,clear;
    private boolean CPStratey = true;
    private App A;
    private JButton load;
    Timer_ t;
    private BattleshipGame G;
    private Ship S;
    private JButton restart;
    private int i, j, x, y;
    private JButton cp_history, my_history;
    private JButton golden_button;
    private JButton custom;
    private JTextField namee, numm;
private int num;
    private int ship_width, ship_length, ship_num;
    int k;
    private ComputerPlayer cp;

    private JButton history;
    private JButton save;
    private JButton player_grid[][], player_draft[][];

    private BattleshipPlayer curr_player;
    BattleshipMoveResult player_move_r;
    BattleshipMoveResult computer_move_r;

    ///Default Constrecter
    public App(String Title)
    {
        this.setTitle(Title);
        this.setSize(736, 552);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        i = 10;
        j = 10;
        ship_num = 5;
    }

    ///Constrecter to set the size of grid
    public App(String Title, int i, int j)
    {
        this.setTitle(Title);
        this.setSize(736, 552);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.i = i;
        this.j = j;

    }

    public void edit_hits_gui (ArrayList <BattleshipMoveResult> my_hits)
    {
    	
    	  this.setSize(800, 700);
          this.setLocation(0, 0);
          
          BattleshipMoveResult m;
          String s = "";
          this.setLayout(new FlowLayout());
          
          int score = 0;
          if(my_hits==null)
          {
        	  System.out.println("my _hits == null ");
          }
          System.out.println(my_hits.size());
          for (int i = 0; i < my_hits.size(); i++) {
        	
              m = my_hits.get(i);
           
              s = "the " + (i + 1) + "hit is:[" + m.get_x() + " " + m.get_y() + " " + "] at (" + m.get_time().HOUR + ":" + m.get_time().MINUTE + ":" + m.get_time().SECOND +") case is: ";
                 if(m.get_hit_case()>=0)
                 {
                	 s+="HIT";
                 }else
                 {
                	 s+="MISS";
                 }
             
              JLabel l = new JLabel();
              l.setFont(l.getFont().deriveFont(30.0f));
              l.setText(s);
              add(l);

          }
    	
    }
    
    public void edit_for_me(BattleshipMoveResult my_move)
    { 
    	if(my_move.get_hit_case()>=0)
    		player_draft[my_move.get_x()][my_move.get_y()].setBackground(Color.red);
    	else if(my_move.get_hit_case()!=-10)
    		player_draft[my_move.get_x()][my_move.get_y()].setBackground(Color.blue);
    	
    }
    public void edit_for_cp(BattleshipMoveResult cp_move)
    { 
    	if(cp_move.get_hit_case()>=0)
    		player_grid[cp_move.get_x()][cp_move.get_y()].setBackground(Color.red);
    	else if(cp_move.get_hit_case()!=-10)
    		player_grid[cp_move.get_x()][cp_move.get_y()].setBackground(Color.blue);
    	
    }
    
    public void player_history(ArrayList<BattleshipMoveResult> my_hits, int num) {
        this.setSize(400, 700);
        this.setLocation(0, 0);;

        BattleshipMoveResult m;
        String s = "";
        this.setLayout(new FlowLayout());
        int score = 0;
        for (int i = 0; i < my_hits.size(); i++) {

            m = my_hits.get(i);
            if (m.get_hit_case() >= 0) {
                score += num - i;
            }
            s = "the " + (i + 1) + "hit is:[" + m.get_x() + " " + m.get_y() + " " + m.get_hit_case() + "](" + score + ")\n";

            JLabel l = new JLabel();
            l.setFont(l.getFont().deriveFont(30.0f));
            l.setText(s);
            add(l);

        }

    }

    public void END(boolean winner, ArrayList<BattleshipMoveResult> cp_hits, ArrayList<BattleshipMoveResult> my_hits, int num) {

        this.getContentPane().removeAll();

        cp_history_gui = new App("computer history");
        my_history_gui = new App("Player History");
        cp_history_gui.player_history(cp_hits, num);
        my_history_gui.player_history(my_hits, num);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        if (winner) {
            setContentPane(new JLabel(new ImageIcon("Images\\Player wins.jpg")));
        } else {
            setContentPane(new JLabel(new ImageIcon("Images\\computer wins.jpg")));
        }

        this.setLayout(null);
        cp_history = new JButton("Computer History");
        my_history = new JButton("player History");
        cp_history.addMouseListener(this);
        my_history.addMouseListener(this);
        cp_history.setBounds(500, 450, 100, 60);
        my_history.setBounds(200, 450, 100, 60);
        add(my_history);
        add(cp_history);
        restart = new JButton("Play Again");
        Exit = new JButton("Exit");
        restart.addActionListener(this);
        Exit.addActionListener(this);
        restart.setBounds(350, 350, 100, 60);
        Exit.setBounds(350, 450, 100, 60);
        this.add(restart);
        this.add(Exit);
        this.setVisible(true);
	 //Exit.setBounds(, arg1, arg2, arg3);;

    }

    public void start() {
        this.setSize(1680, 1050);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        setContentPane(new JLabel(new ImageIcon("Images\\battleship.jpg")));

        tf1 = new JTextField("Enter Your Name");
        tf1.setBounds(600, 100, 400, 35);
        add(tf1).setBackground(Color.WHITE);

        play = new JButton("  Play  ");
        play.addActionListener(this);
        play.setBounds(600, 200, 400, 60);
        add(play).setBackground(Color.WHITE);

        Options = new JButton("Options");
        Options.addActionListener(this);
        Options.setBounds(600, 300, 400, 60);
        add(Options).setBackground(Color.WHITE);

        load = new JButton("  Load  ");
        load.addActionListener(this);
        load.setBounds(600, 400, 400, 60);
        add(load).setBackground(Color.WHITE);

        Exit = new JButton("Exit");
        Exit.addActionListener(this);
        Exit.setBounds(600, 700, 400, 60);
        add(Exit).setBackground(Color.WHITE);

        history = new JButton("History");
        history.addActionListener(this);
        history.setBounds(600, 600, 400, 60);
        add(history).setBackground(Color.WHITE);
        
        scoreBoard = new JButton("score Board");
        scoreBoard.addActionListener(this);
        scoreBoard.setBounds(600, 500, 400, 60);
        add(scoreBoard).setBackground(Color.WHITE);
        
        this.setVisible(true);

    }

    public void Implementation(BattleshipGame G, int round, BattleshipMoveResult player_result, BattleshipMoveResult computer_result) {
        if (round == -2) {
this.setLocation(0, 0);
            JLabel xx = new JLabel(new ImageIcon("Images\\Start War.jpg"));
            xx.setSize(1680,1050);setContentPane(xx);
            mylabel = new JLabel ("0");
            mylabel.setBounds(750,50,200,150);            add (mylabel);
            t = new Timer_ (this);
            t.start();
            
           
           
            this.G = G;
            this.curr_player = (BattleshipPlayer) G.players.get(1);
            cp = (ComputerPlayer) G.players.get(0);
            i = curr_player.player_data.get_grid_length();
            j = curr_player.player_data.get_grid_width();

            ship_num = curr_player.player_data.get_num_of_ships();
            player_grid = new JButton[i][j];
            player_draft = new JButton[i][j];//for computer grid
            this.setSize(1680, 1050);

            this.setLayout(null);
            JLabel c_label, p_label;
            c_label = new JLabel("Computer Grid");
            p_label = new JLabel("PLayer Grid");

            save =new JButton("Save");
            save.setBounds(730, 700, 150, 30);
            save.addActionListener(this);
            add(save);
            
            JPanel g1_panel, g2_panel;
            g1_panel = new JPanel();
            g2_panel = new JPanel();
            g1_panel.setLayout(new GridLayout(i, j));
            g2_panel.setLayout(new GridLayout(i, j));

            pass = new JButton("Pass");
            pass.setBounds(1110, 655, 80, 30);
            pass.setBackground(Color.white);
            pass.addActionListener(this);
            add (pass);
            
            golden_button = new JButton("Golden button");
            golden_button.setBounds(730, 650, 150, 30);
            golden_button.addMouseListener(this);
            this.add(golden_button);
            for (int l = 0; l < i; l++) {
                for (int w = 0; w < j; w++) {
                    player_grid[l][w] = new JButton();
                    ///////adsda
                    //////asdasd
                    if (curr_player.player_data.get_grid_case(l, w) == -1) {
                        player_grid[l][w].setBackground(Color.white);
                    } else if (curr_player.player_data.get_grid_case(l, w) == ship_num) {
                        player_grid[l][w].setBackground(Color.BLACK);
                    } else {
                        player_grid[l][w].setBackground(Color.GREEN);
                    }
                    /////asdasd
                    //////asdasd
                    g1_panel.add(player_grid[l][w]);

                }
            }

            for (int l = 0; l < i; l++) {
                for (int w = 0; w < j; w++) {
                    player_draft[l][w] = new JButton();
                    player_draft[l][w].addActionListener(this);
                    player_draft[l][w].setBackground(Color.white);
                    g2_panel.add(player_draft[l][w]);

                }
            }

            p_label.setBounds(420, 50, 200, 100);
            c_label.setBounds(1100, 50, 200, 100);
            g1_panel.setBounds(200, 150, 500, 500);
            g2_panel.setBounds(900, 150, 500, 500);
            add(c_label);
            add(p_label);
            add(g1_panel);
            add(g2_panel);

            this.setVisible(true);
            int q=0 ; 
            while(q<curr_player.B.size())
            {
            	
                curr_player.B.get(q).Gui=this;
               curr_player.B.get(q).start(); 
               cp.B.get(q).Gui=this;
               cp.B.get(q).start(); 
               q++;
            }
           ///// G.RunGame(1, this, null);
            
        } 
        else if (round == 0)
        {
            for (int l = 0; l < i; l++) {
                for (int w = 0; w < j; w++) {
                    if (player_result != null &&player_result.get_x() == l && player_result.get_y() == w ) {
                        if (player_result.get_hit_case() == -1) {
                            player_draft[l][w].setBackground(Color.BLUE);
                        } else {
                            player_draft[l][w].setBackground(Color.red);
                        }

                    }

                }
            }
            for (int l = 0; l < i; l++) {
                for (int w = 0; w < j; w++) {
                    if (computer_result != null &&computer_result.get_x() == l && computer_result.get_y() == w) {
                        if ( computer_result.get_hit_case() == -1  ) {
                            player_grid[l][w].setBackground(Color.BLUE);
                        } else if (computer_result != null) {
                            player_grid[l][w].setBackground(Color.red);
                        }

                    }

                }
            }

            G.RunGame(1, this, null);

        }

    }

    public void addships(BattleshipGame G, BattleshipPlayer p, int k) {

    	num=0;
        this.setLocation(0, 0);

        curr_player = p;
        this.G = G;
        cp = (ComputerPlayer) G.players.get(0);
        if (k == 0) {
            this.ship_num = p.player_data.num_of_ships;
            JLabel xx = new JLabel(new ImageIcon("Images\\put ships.jpg"));
            xx.setSize(1680,1050);
            setContentPane(xx);
            this.i = p.player_data.get_grid_length();
            this.j = p.player_data.get_grid_width();

            this.setLayout(null);
            this.setResizable(false);
            this.setSize(1680,1050);
            battleShip = new JButton("BattleShip");
            battleShip.addActionListener(this);
            battleShip.setBackground(Color.WHITE);
            battleShip.setBounds(300, 200, 150, 50);
            add(battleShip);

            Destroyer = new JButton("Destroyer");
            Destroyer.addActionListener(this);
            Destroyer.setBackground(Color.WHITE);
            Destroyer.setBounds(300, 260, 150, 50);
            add(Destroyer);

            Cruiser = new JButton("Cruiser");
            Cruiser.addActionListener(this);
            Cruiser.setBackground(Color.WHITE);
            Cruiser.setBounds(300, 320, 150, 50);
            add(Cruiser);

            Carrier = new JButton("Carrier");
            Carrier.addActionListener(this);
            Carrier.setBackground(Color.WHITE);
            Carrier.setBounds(300,380, 150, 50);
            add(Carrier);

            Submarine = new JButton("Submarine");
            Submarine.addActionListener(this);
            Submarine.setBackground(Color.WHITE);
            Submarine.setBounds(300, 440, 150, 50);
            add(Submarine);

            namee = new JTextField("Enter the name");
            namee.setBounds(300, 500, 150, 50);
            add(namee);

            numm = new JTextField("Enter the length");
            numm.setBounds(300, 560, 150, 50);
            add(numm);

            custom = new JButton("Custom");
            custom.addActionListener(this);
            custom.setBackground(Color.WHITE);
            custom.setBounds(300, 620, 150, 50);
            add(custom);

            BomB = new JButton("BomB");
            BomB.addActionListener(this);
            BomB.setBackground(Color.WHITE);
            BomB.setBounds(300, 680, 150, 50);
            add(BomB);
            
            JPanel pq = new JPanel();

            put_ships_grid = new JButton[i][j];

            for (int l = 0; l < i; l++) {
                for (int w = 0; w < j; w++) {
                    put_ships_grid[l][w] = new JButton();
                    put_ships_grid[l][w].addMouseListener(this);
                    put_ships_grid[l][w].setBackground(Color.white);
                    pq.add(put_ships_grid[l][w]);

                }
            }
            //pq.addMouseListener(this);
            pq.setLayout(new GridLayout(i, j));
            //edit size 
            pq.setBounds(600, 170, 600, 600);
            this.add(pq);
            this.setVisible(true);
        }

        //wait();
    }

    public void actionPerformed(ActionEvent e) {

        Object o = new Object();
        o = e.getSource();

        ///When the player click start to the game
        if (o == Exit) {
            System.exit(0);

        }
        if (o==clear){
            try {
                    FileOutputStream fos = new FileOutputStream("RecordFiles\\ScoreBoard");
                    ObjectOutputStream obj = new ObjectOutputStream(fos);
                    obj.writeObject(new ScoreBoard());
                    obj.close();
                    fos.close();
                    this.setVisible(false);
                } catch (IOException ex) {
                    System.out.println("nooooo");
                }
        }
        else if (o == scoreBoard){
            new App("Score Board").MakeScore();
        }
        else if (o == history) {
            //this.setVisible(false);
            new App("Options").editHistory(this);

        }else if (o == submit) {
                
        
        	FilesName fn;
        	GameRecord gr ;
        	try {
        	 FileInputStream fis = new FileInputStream("RecordFiles\\FilesNames");
      		ObjectInputStream ois = new ObjectInputStream(fis);
        	fn = (FilesName) ois.readObject();
        	int i=0;
        	ArrayList <String> hh =fn.names;
        	while(hh.get(i)!=null)
        	{
        		String one=hh.get(i),tow=record_files.getSelectedItem().toString();
        		if(one.compareTo(tow)==0)
        		{
            		

        			File f = new File("RecordFiles\\" + tow );
        			if(f.exists())
        				System.out.println("Exist");
        			 FileInputStream fis2 = new FileInputStream(f);
              		ObjectInputStream ois2 = new ObjectInputStream(fis2);
              		
              		 gr = (GameRecord) ois2.readObject();

              	
                 
              		this.EditHistory(gr);
              		              		
              		break;
        		}
        		i++;
        	}
        	}catch(Exception ex)
        	{
        		System.out.print("hhhhhhh");
        	}
        	

            }
        	else if(o==load)
        {
        	BattleshipGame p = null;
             try {
            	 FileInputStream fis = new FileInputStream("Files\\file1");
         		ObjectInputStream ois = new ObjectInputStream(fis);
         		 p = (BattleshipGame)ois.readObject();///
         		ois.close();
         		fis.close(); 
                        p.load();
                        

           	 System.out.println("loaded");
          
         	
             }catch (Exception ex)
             {
            	 System.out.println("cant load");
             }
             
        	
        }
        else if(o == save)
        {
        	//String File_name=JOptionPane.showInputDialog("Enter the file name:");
        	
		try {
                        FileOutputStream file = new FileOutputStream ("Files\\file1");
		    	ObjectOutputStream obj = new ObjectOutputStream(file);
                        obj.writeObject(this.G);
                        obj.close();
                        file.close();
                        System.out.println("Save");
                        
		} catch (Exception ex) {
			System.out.println("Don't Save");
		}
			
        }
        else if (o == pass)
        {
            this.t.label.setText("Counter :"+0);
            
            G.RunGame(0, this, null);
        }
        else if (o == BomB) {
            ship_length = 1;
            ship_width = 1;

        } else if (o == restart) {
            this.start();
        } else if (o == custom) {
            ship_length = Integer.parseInt(numm.getText());
            ship_width = 1;
            if (Integer.parseInt(numm.getText()) > max(i, j)) {

                JOptionPane.showConfirmDialog(null, "the size of your custom ship is greater than the size of the board");
            } else {
                curr_player.player_data.shipsArray[k] = new CustomShip(namee.getText(), Integer.parseInt(numm.getText()));
                cp.player_data.shipsArray[k] = new CustomShip(namee.getText(), Integer.parseInt(numm.getText()));
            }
        } else if (o == battleShip) {
            ship_length = 4;
            ship_width = 1;
            curr_player.player_data.shipsArray[k] = new Battleship();
            cp.player_data.shipsArray[k] = new Battleship();

        } else if (o == Destroyer) {
            ship_length = 2;
            ship_width = 1;
            curr_player.player_data.shipsArray[k] = new Destroyer();
            cp.player_data.shipsArray[k] = new Destroyer();
        } else if (o == Cruiser) {
            ship_length = 3;
            ship_width = 1;
            curr_player.player_data.shipsArray[k] = new Cruiser();
            cp.player_data.shipsArray[k] = new Cruiser();
        } else if (o == Submarine) {
            ship_length = 3;
            ship_width = 1;
            curr_player.player_data.shipsArray[k] = new Submarine();
            cp.player_data.shipsArray[k] = new Submarine();
        } else if (o == Carrier) {
            ship_length = 5;
            ship_width = 1;
            curr_player.player_data.shipsArray[k] = new Carrier();
            cp.player_data.shipsArray[k] = new Carrier();
        } else if (o == Ok) {
            int Glength = Integer.parseInt(g1.getSelectedItem().toString());
            int Gwidth = Integer.parseInt(g2.getSelectedItem().toString());
            int NumShips = Integer.parseInt(tf2.getText());

            this.A.edit(Glength, Gwidth, NumShips, Random.isSelected());
            this.dispose();
        } else if (o == Options) {
            //this.setVisible(false);
            new App("Options").editOptions(this);

        } else if (o == play) {
            if ("Enter Your Name".equals(tf1.getText())) {
                JOptionPane.showMessageDialog(this, "Your Name Please ", "!!!", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println(i + " " + j + " " + ship_num + " " + CPStratey);
                this.setVisible(false);
                BattleshipGame G = new BattleshipGame();
                if (CPStratey == true) {

                    System.out.println("Random");
                    RandomComputerPlayer Cp = new RandomComputerPlayer("ComputerPlayer", i, j);
                    Cp.player_data.set_num_of_ships(ship_num);
                    Cp.player_data.shipsArray = new Ship[ship_num];
                    Cp.SubscribeTo(G);

                } else {
                    System.out.println("Smart");
                    SmartComputerPlayer Cp = new SmartComputerPlayer("ComputerPlayer", i, j);
                    Cp.player_data.set_num_of_ships(ship_num);
                    Cp.player_data.shipsArray = new Ship[ship_num];
                    Cp.SubscribeTo(G);

                }
                HumanPlayer HP = new HumanPlayer(tf1.getText(), i, j);
                HP.player_data.set_num_of_ships(ship_num);
                HP.SubscribeTo(G);
                
                //////
                
                ScoreBoard s = null ;
                
                try {
                    FileInputStream fos = new FileInputStream("RecordFiles\\ScoreBoard");
                    ObjectInputStream obj = new ObjectInputStream(fos);
                    try {
                        s = (ScoreBoard) obj.readObject();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    obj.close();
                    fos.close();

                } catch (IOException ex) {
                    System.out.println ("null");
                }
                if (s.objects.size() == 0) {
                    player_score s1 = new player_score(tf1.getText());
                    s1.inc_startedGame();
                    s.objects.add(s1);
                } else {

                    int cnt = 0, z = 0;
                    while (s.objects.size() != cnt) {
                        if (s.objects.get(cnt).getName().equals(tf1.getText())) {
                            s.objects.get(cnt).inc_startedGame();
                            z = 1;
                            break;
                        }
                        cnt++;
                    }
                    if (z != 1) {

                        player_score s1 = new player_score(tf1.getText());
                        s1.inc_startedGame();
                        s.objects.add(s1);
                    }
                }
                s.sort();

                try {
                    FileOutputStream fos = new FileOutputStream("RecordFiles\\ScoreBoard");
                    ObjectOutputStream obj = new ObjectOutputStream(fos);
                    obj.writeObject(s);
                    obj.close();
                    fos.close();
                } catch (IOException ex) {
                    System.out.println("nooooo");
                }
                
                //////
                G.start();
            }
        } else {
            for (int l = 0; l < i; l++) {
                for (int w = 0; w < j; w++) {

                    if (o == player_draft[l][w]) {
                        if (player_draft[l][w].getBackground() == Color.blue || player_draft[l][w].getBackground() == Color.red) {
                        } else {
                            BattleshipMove player_move = new BattleshipMove(l, w);
                            G.RunGame(0, this, player_move);
                        }
                    }

                }
            }
        }

    }

    private int max(int i2, int j2) {
        if (i2 < j2) {
            return j2;
        }

        return i2;
    }

    public void mouseClicked(MouseEvent e) {
        Object o = new Object();
        o = e.getSource();

        if (SwingUtilities.isRightMouseButton(e)) {
            for (int l = 0; l < i; l++) {
                for (int w = 0; w < j; w++) {
                    if (o == put_ships_grid[l][w]) {
                        int ll = l + ship_length;
                        int ww = w + ship_width;
                        if (ll > i) {
                            ll = i;
                        }
                        if (ww > j) {
                            ww = j;
                        }

                        System.out.print("YOU CLICKED");
                        for (int ii = l; ii < ll; ii++) {
                            for (int jj = w; jj < ww; jj++) {
                                if (put_ships_grid[ii][jj].getBackground() == Color.green) {
                                    put_ships_grid[ii][jj].setBackground(Color.white);

                                } else if (put_ships_grid[ii][jj].getBackground() == Color.red) {
                                    put_ships_grid[ii][jj].setBackground(Color.blue);

                                } else if (put_ships_grid[ii][jj].getBackground() == Color.yellow) {
                                    put_ships_grid[ii][jj].setBackground(Color.white);

                                }
                            }
                        }
                    }
                }
            }

            int temp = ship_width;
            ship_width = ship_length;
            ship_length = temp;

            for (int l = 0; l < i; l++) {
                for (int w = 0; w < j; w++) {
                    if (o == put_ships_grid[l][w]) {
                        int ll = l + ship_length;
                        int ww = w + ship_width;
                        if (ll > i) {
                            ll = i;
                        }
                        if (ww > j) {
                            ww = j;
                        }

                        if (l + ship_length > i) {
                            for (int ii = l; ii < i; ii++) {
                                if (put_ships_grid[ii][w].getBackground() == Color.blue) {
                                    put_ships_grid[ii][w].setBackground(Color.red);
                                } else if (put_ships_grid[ii][w].getBackground() == Color.white) {
                                    put_ships_grid[ii][w].setBackground(Color.yellow);
                                }

                            }
                        } else if (w + ship_width > j) {
                            for (int jj = w; jj < j; jj++) {
                                if (put_ships_grid[l][jj].getBackground() == Color.blue) {
                                    put_ships_grid[l][jj].setBackground(Color.red);
                                } else if (put_ships_grid[l][jj].getBackground() == Color.white) {
                                    put_ships_grid[l][jj].setBackground(Color.yellow);
                                }

                            }
                        } else {

                            for (int ii = l; ii < ll; ii++) {
                                for (int jj = w; jj < ww; jj++) {
                                    if (put_ships_grid[ii][jj].getBackground() == Color.white) {
                                        put_ships_grid[ii][jj].setBackground(Color.green);

                                    } else if (put_ships_grid[ii][jj].getBackground() == Color.blue) {
                                        put_ships_grid[ii][jj].setBackground(Color.red);

                                    }

                                }
                            }
                        }
                    }
                }
            }
        } else {
            for (int l = 0; l < i; l++) {
                for (int w = 0; w < j; w++) {

                    if (o == put_ships_grid[l][w]) {

                        boolean yes = false;
                        int ll = l + ship_length;
                        int ww = w + ship_width;
                        if (ll > i) {
                            ll = i;
                        }
                        if (ww > j) {
                            ww = j;
                        }

                        for (int ii = l; ii < ll; ii++) {
                            for (int jj = w; jj < ww; jj++) {
                                if (put_ships_grid[ii][jj].getBackground() == Color.red || put_ships_grid[ii][jj].getBackground() == Color.yellow ||put_ships_grid[ii][jj].getBackground() == Color.black) {
                                    yes = true;
                                }
                            }
                        }

                        if (!yes) {
                            for (int ii = l; ii < l + ship_length; ii++) {
                                for (int jj = w; jj < w + ship_width; jj++) {
                                    if (ship_length == 1 && ship_width == 1) {
                                    	
                                        put_ships_grid[ii][jj].setBackground(Color.BLACK);
                                        curr_player.B.add(new BomB(curr_player, ii, jj, this,num));
                                        cp.add_BomB(num);
                                        curr_player.player_data.edit(ii, jj, curr_player.player_data.get_num_of_ships());
                                        num++;
                                    } else {
                                        put_ships_grid[ii][jj].setBackground(Color.BLUE);
                                        curr_player.player_data.edit(ii, jj, k);
                                    }

                                }

                            }

                            if (ship_length != 1 || ship_width != 1) {
                                k++;
                                System.out.println(k + ' ' + ship_num);

                                if (k == this.ship_num) {
                                    this.setVisible(false);
                                    //App1 pp = new App1("Game Implementation");
                                    // pp.Game_Implementation( G, (HumanPlayer)curr_player );
                                    G.BSJ.set(ship_num);
                                    cp.putShips();
                                    G.RunGame(-2, this, null);
                                    //G.RunGame(1,pp);
                                    return;
                                }
                            }
                            ship_length = ship_width = 0;
                        }

                    }
                }
            }
        }
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
        Object o = new Object();
        o = e.getSource();

        if (o == golden_button) {

            for (int l = 0; l < i; l++) {
                for (int w = 0; w < j; w++) {
                    //System.out.println(cp.player_data.get_draft_case(l, w));
                    if (cp.player_data.get_grid_case(l, w) >= 0) {
                    	
                        if (player_draft[l][w].getBackground() != Color.red) {
                            if(cp.player_data.get_grid_case(l, w)==cp.player_data.get_num_of_ships())
                            	player_draft[l][w].setBackground(Color.black);
                            	else
                            	player_draft[l][w].setBackground(Color.green);
                            
                        }
                    }

                }

            }
        } else if (o == cp_history) {
            cp_history_gui.setVisible(true);
        } else if (o == my_history) {
            my_history_gui.setVisible(true);
        } else if (o == player_hits) {
        	player_hits_gui.setVisible(true);
        } else if (o == computer_hits) {
        	computer_hits_gui.setVisible(true);
        } else {
            for (int l = 0; l < i; l++) {
                for (int w = 0; w < j; w++) {
                    if (o == put_ships_grid[l][w]) {
                        if (l + ship_length > i) {
                            for (int ii = l; ii < i; ii++) {
                                if (put_ships_grid[ii][w].getBackground() == Color.blue) {
                                    put_ships_grid[ii][w].setBackground(Color.red);
                                } else if (put_ships_grid[ii][w].getBackground() == Color.white) {
                                    put_ships_grid[ii][w].setBackground(Color.yellow);
                                }

                            }
                        } else if (w + ship_width > j) {
                            for (int jj = w; jj < j; jj++) {
                                if (put_ships_grid[l][jj].getBackground() == Color.blue) {
                                    put_ships_grid[l][jj].setBackground(Color.red);
                                } else if (put_ships_grid[l][jj].getBackground() == Color.white) {
                                    put_ships_grid[l][jj].setBackground(Color.yellow);
                                }

                            }
                        } else {
                            for (int ii = l; ii < l + ship_length; ii++) {
                                for (int jj = w; jj < w + ship_width; jj++) {
                                    if (put_ships_grid[ii][jj].getBackground() == Color.blue) {
                                        put_ships_grid[ii][jj].setBackground(Color.red);
                                    } else if (put_ships_grid[ii][jj].getBackground() == Color.white) {
                                        put_ships_grid[ii][jj].setBackground(Color.green);
                                    } else if (put_ships_grid[ii][jj].getBackground() == Color.yellow) {
                                        put_ships_grid[ii][jj].setBackground(Color.white);
                                    }

                                }
                            }
                        }
                    }
                }
            }

        }
    }

    public void mouseExited(MouseEvent e) {
        Object o = new Object();
        o = e.getSource();
        if (o == golden_button) {
            for (int l = 0; l < i; l++) {
                for (int w = 0; w < j; w++) {
             	   if (player_draft[l][w].getBackground() == Color.black) {
                       player_draft[l][w].setBackground(Color.white);
                   }
                    if (cp.player_data.get_grid_case(l, w) >= 0) {
                        if (player_draft[l][w].getBackground() == Color.green) {
                            player_draft[l][w].setBackground(Color.white);
                        }
                        
                    }

                }

            }
        } else if (o == cp_history) {
            cp_history_gui.setVisible(false);
        } else if (o == my_history) {
            my_history_gui.setVisible(false);
        }else if (o == player_hits) {
        	player_hits_gui.setVisible(false);
        } else if (o == computer_hits) {
        	computer_hits_gui.setVisible(false);
        } else {
            for (int l = 0; l < i; l++) {
                for (int w = 0; w < j; w++) {
                    if (o == put_ships_grid[l][w]) {
                        int ll = l + ship_length, ww = w + ship_width;
                        if (l + ship_length > i) {
                            ll = i;
                        }
                        if (w + ship_width > j) {
                            ww = j;
                        }

                        for (int ii = l; ii < ll; ii++) {
                            for (int jj = w; jj < ww; jj++) {
                                if (put_ships_grid[ii][jj].getBackground() == Color.red) {
                                    put_ships_grid[ii][jj].setBackground(Color.blue);
                                } else if (put_ships_grid[ii][jj].getBackground() == Color.green) {
                                    put_ships_grid[ii][jj].setBackground(Color.white);
                                } else if (put_ships_grid[ii][jj].getBackground() == Color.yellow) {
                                    put_ships_grid[ii][jj].setBackground(Color.white);
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void editOptions(App A) {
        this.A = A;

        this.setSize(300, 361);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        //setContentPane(new JLabel(new ImageIcon("C:\\Users\\axa\\Desktop\\battleship.jpg")));

        label1 = new JLabel("Grid Size :");
        label1.setBounds(15, 30, 80, 30);
        label1.setBackground(Color.WHITE);
        add(label1);

        label2 = new JLabel("Number Of Ships :");
        label2.setBounds(15, 70, 120, 30);
        label2.setBackground(Color.WHITE);
        add(label2);

        label3 = new JLabel("Strategy of Cp Player :");
        label3.setBounds(15, 110, 130, 30);
        label3.setBackground(Color.WHITE);
        add(label3);
        g1 = new JComboBox();
        g1.addItem("5");
        g1.addItem("10");
        g1.addItem("15");
        g1.setBackground(Color.WHITE);

        g2 = new JComboBox();
        g2.addItem("5");
        g2.addItem("10");
        g2.addItem("15");
        g2.setBackground(Color.WHITE);

        g1.setBounds(110, 40, 50, 20);
        add(g1);
        g2.setBounds(170, 40, 50, 20);
        add(g2);

        tf2 = new JTextField("5");
        tf2.setBounds(150, 75, 45, 20);
        tf2.setBackground(Color.WHITE);
        add(tf2);

        Random = new JCheckBox("Random");
        Random.setBounds(140, 110, 80, 30);
        Smart = new JCheckBox("Smart");
        Smart.setBounds(220, 110, 80, 30);

        g = new ButtonGroup();
        g.add(Random);
        g.add(Smart);
        Random.setBackground(Color.WHITE);
        Random.setSelected(true);
        add(Random);
        Smart.setBackground(Color.WHITE);
        add(Smart);

        Ok = new JButton("Ok");
        Ok.addActionListener(this);
        Ok.setBounds(100, 250, 100, 30);
        add(Ok);
        this.setVisible(true);
    }

    public void EditHistory(GameRecord game_record)
    {
    	
    	if(game_record.get_winner()) {
    		c_label.setText("Computer Player (loser)");
        	p_label.setText(game_record.get_name()+" (winner)");
    	}else
    	{
    		c_label.setText("Computer Player (winner)");
        	p_label.setText(game_record.get_name() + " (loser)");
    	}
    	hp_grid = new JButton[game_record.get_cp_grid().get_length()][game_record.get_cp_grid().get_width()];
        cp_grid = new JButton[game_record.get_cp_grid().get_length()][game_record.get_cp_grid().get_width()];
    	  JPanel g1_panel, g2_panel;
          g1_panel = new JPanel();
          g2_panel = new JPanel();
          g1_panel.setLayout(new GridLayout(game_record.get_cp_grid().get_length(),game_record.get_cp_grid().get_width() ));
          g2_panel.setLayout(new GridLayout(game_record.get_cp_grid().get_length(),game_record.get_cp_grid().get_width() ));
           
       
      
      
    	
    	
    	for(int l=0;l<game_record.get_cp_grid().get_length();l++)
    	{
    		for(int w=0;w<game_record.get_cp_grid().get_width();w++)
    		{
    			cp_grid[l][w] = new JButton();
    			if(game_record.get_cp_grid().get_case(l, w)==-1 || game_record.get_cp_grid().get_case(l, w)==-1)
    				cp_grid[l][w].setBackground(Color.blue);
    			else if(game_record.get_cp_grid().get_case(l,w)==game_record.get_bomb_num())
    			{
    				cp_grid[l][w].setBackground(Color.black);
    			}
    			else if(game_record.get_cp_grid().get_case(l,w)>=0 || game_record.get_cp_grid().get_case(l,w)==-2){
    				cp_grid[l][w].setBackground(Color.green);
    			}else {
    				cp_grid[l][w].setBackground(Color.blue);
    			}
    			 g2_panel.add(cp_grid[l][w]);
    		}
    	}
    	for(int l=0;l<game_record.get_hp_grid().get_length();l++)
    	{
    		for(int w=0;w<game_record.get_hp_grid().get_width();w++)
    		{
    			hp_grid[l][w] = new JButton();
    			if(game_record.get_hp_grid().get_case(l, w)==-1 || game_record.get_hp_grid().get_case(l, w)==-1)
    			hp_grid[l][w].setBackground(Color.blue);
    			else if(game_record.get_hp_grid().get_case(l,w)==game_record.get_bomb_num())
    			{
    				hp_grid[l][w].setBackground(Color.black);
    			}
    			else if(game_record.get_hp_grid().get_case(l,w)>=0 || game_record.get_hp_grid().get_case(l,w)==-2){
    				hp_grid[l][w].setBackground(Color.green);
    			}else {
    				hp_grid[l][w].setBackground(Color.blue);
    			}
    			 g1_panel.add(hp_grid[l][w]);
    		}
    	}
        p_label.setBounds(320, 50, 200, 100);
        c_label.setBounds(1000, 50, 200, 100);
        g1_panel.setBounds(100, 150, 500, 500);
        g2_panel.setBounds(800, 150, 500, 500);
       
        add(g1_panel);
        add(g2_panel);
        
        
       game_id2.setText(Integer.toString(game_record.get_id()-1));
       int s,m,h;
       s=game_record.get_start_time().getTime().getSeconds();
       m=game_record.get_start_time().getTime().getMinutes();
       h=game_record.get_start_time().getTime().getHours();
       start_time2.setText("" + h + ":" + m + ":" + s );
       s=game_record.get_end_time().getTime().getSeconds();
       m=game_record.get_end_time().getTime().getMinutes();
       h=game_record.get_end_time().getTime().getHours();
       end_time2.setText("" + h + ":" + m + ":" + s );
       
       
       player_hits_gui = new App("");
       computer_hits_gui = new App("");
       
       
       System.out.println(11111);
   	player_hits_gui.edit_hits_gui(game_record.get_hp_record());
 	System.out.println(22222);
   	computer_hits_gui.edit_hits_gui(game_record.get_cp_record());
 	this.player_hits.setText(game_record.get_name());
 	this.computer_hits.setText("computer");
this.setVisible(true);
    	
    }
    
    public void editHistory(App A) {
    	
       
    	
    	
    	player_hits = new JButton("player1 hits");
    	computer_hits = new JButton("player2 hits");
    	player_hits_gui = new App("player");
    	computer_hits_gui = new App("computer");
    	
     
        setSize(1400,800);
       this.setLocationRelativeTo(null);
       
       

       record_files = new JComboBox();
     
       
       FilesName fn;
       ArrayList <String> my_array;
       try {
       FileInputStream fis = new FileInputStream("RecordFiles\\FilesNames");
 		ObjectInputStream ois = new ObjectInputStream(fis);
   	fn = (FilesName) ois.readObject();
   	 my_array =fn.names;
   	 int i=1;
     while(my_array.get(i)!=null)
	    {
    	 
	    	record_files.addItem(my_array.get(i));
	        i++;
	    }
      ois.close();
      fis.close();
       }
       catch(Exception e)
       {
    	   System.out.println("hhhhh22");
       }
   	    
   	   
   	  
       game_id1 = new JLabel("Game ID");
       game_id2 = new JLabel("the id");
       start_time1 =new JLabel("Start Time");
       start_time2 =new JLabel("00:00:00");
       end_time1 =new JLabel("End Time");
       end_time2 =new JLabel("00:00:00");
       
       game_id1.setBounds(660,200,200,50);
       add(game_id1);
       game_id2.setBounds(670,240,200,50);
       add(game_id2);
       
       
       start_time1.setBounds(660,280,200,50);
       add(start_time1);
       start_time2.setBounds(670,320,200,50);
       add(start_time2);
       
       end_time1.setBounds(660,360,200,50);
       add(end_time1);
       end_time2.setBounds(670,390,200,50);
       add(end_time2);
       
       
       record_files.setBounds(550,50,200,50);
       record_files.addActionListener(this);
      add( record_files);
      
      player_hits.setBounds(640,430,100,50);
      player_hits.addMouseListener(this);
      add(player_hits);
      
      computer_hits.setBounds(640,490,100,50);
      computer_hits.addMouseListener(this);
      add(computer_hits);
      
        submit = new JButton("submit");
        submit.setBounds(750,50,100,50);
        submit.addActionListener(this);
        add(submit);
        
        
        
        
        
        this.setLayout(null);
       
        c_label = new JLabel("player1 grid");
        p_label = new JLabel("player2 grid");
           
        add(c_label);
        add(p_label);
        
      
        this.setVisible(true);
       

            
         

        //Ok = new JButton("Ok");
       // Ok.addActionListener(this);
       // Ok.setBounds(100, 250, 100, 30);
        //add(Ok);
        //this.setVisible(true);
    }


    public void edit(int GridLength, int GridWidth, int NumOfShips, boolean CPStratey) {
        i = GridLength;
        j = GridWidth;
        ship_num = NumOfShips;
        this.CPStratey = CPStratey;
        //System.out.print(ship_num);
        this.setVisible(true);
    }

    void Upgate(BattleshipPlayer p) {

        i = p.player_data.get_grid_length();
        j = p.player_data.get_grid_width();
        for (int i = 0; i < this.i; i++) {
            for (int j = 0; j < this.j; j++) {
                if (curr_player.player_data.get_grid_case(i, j) == -1) {
                    player_grid[i][j].setBackground(Color.WHITE);
                } else if (curr_player.player_data.get_grid_case(i, j) == -2) {
                    player_grid[i][j].setBackground(Color.red);
                } else if (curr_player.player_data.get_grid_case(i, j) == -3) {
                    player_grid[i][j].setBackground(Color.BLUE);
                } else if (curr_player.player_data.get_grid_case(i, j) == ship_num) {
                    player_grid[i][j].setBackground(Color.BLACK);
                } else {
                    player_grid[i][j].setBackground(Color.GREEN);
                }
            }
        }

    }

    void Make(JLabel label) {
        this.mylabel.setText(label.getText());
        repaint();
    }

    public void edit_bomb(BattleshipMoveResult move_bomb,boolean curr_hitter)
    {
     

        
        int xxx=move_bomb.get_x();
        int yyy=move_bomb.get_x();
        if(curr_hitter==true){///computer hit
         for (int l=xxx-1 ; l<=xxx+1 ; l++)
                for (int w=yyy-1; w<=yyy+1 ;w++)
                {
                    if (l>=0 && l<curr_player.player_data.get_grid_length() && w>=0 && w<curr_player.player_data.get_grid_width()){
                        if (curr_player.player_data.get_grid_case(l, w)!=-1 && curr_player.player_data.get_grid_case(l, w)!=curr_player.player_data.get_num_of_ships())
                        {
                            player_grid[l][w].setBackground(Color.red);
                        }else if(curr_player.player_data.get_grid_case(l, w)!=-1 )
                        {
                            player_grid[l][w].setBackground(Color.blue);
                        }
                    }
                }
        }else
        {///human hit
             for (int l=xxx-1 ; l<=xxx+1 ; l++)
                for (int w=yyy-1; w<=yyy+1 ;w++)
                {
                    if (l>=0 && l<cp.player_data.get_grid_length() && w>=0 && w<cp.player_data.get_grid_width()){
                        if (cp.player_data.get_grid_case(l,w)!=-1 && cp.player_data.get_grid_case(l, w)!=cp.player_data.get_num_of_ships())
                        {
                            player_draft[l][w].setBackground(Color.red);
                        }else if(cp.player_data.get_grid_case(l, w)!=-1 )
                        {
                            player_draft[l][w].setBackground(Color.blue);
                        }
                    }
                }
        }
    }

    public void MakeScore() {
        ScoreBoard s = null; 
        try {
            FileInputStream fos = new FileInputStream ("RecordFiles\\ScoreBoard");
            ObjectInputStream obj = new ObjectInputStream(fos);
                    try {
                        s = (ScoreBoard) obj.readObject();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    obj.close();
                    fos.close();
        }
        catch (IOException e){
            System.out.println ("no");
        }
        if (s.objects.size() == 0) 
            JOptionPane.showMessageDialog(this, "Have not had players yet!");
        else {
            
        this.setTitle("Score Board");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(s.objects.size()+2,1));
        this.setLocationRelativeTo(null);
        JPanel f[] = new JPanel [s.objects.size()+2];
        JLabel q[][] = new JLabel [s.objects.size()+1][5];
        f[0] = new JPanel ();
        for (int i=0 ;i<5 ;i++){
            q[0][i] = new JLabel ();
            q[0][i].setSize(100,30);
        }
        for (int i=1 ; i<=s.objects.size() ;i++){
            
            f[i] = new JPanel ();
            
            for (int j=0 ; j<5 ; j++){
                q[i][j]=new JLabel();
                q[i][j].setSize(100,30);
                if (j==0) q[i][j].setText("Name: "+s.objects.get(i-1).name+"      ");
                if (j==1) q[i][j].setText("Started Game : "+s.objects.get(i-1).getStartedGame()+"     ");
                if (j==2) q[i][j].setText("Won : "+s.objects.get(i-1).getWon()+"      ");
                if (j==3) q[i][j].setText("Loose : "+s.objects.get(i-1).getloose()+"      ");
                if (j==4) q[i][j].setText("Score : "+s.objects.get(i-1).score());
                f[i].add(q[i][j]);
            }
            
        }
        clear = new JButton ();
        clear.setText("clear");
        clear.addActionListener(this);
        clear.setBackground(Color.white);
        f[s.objects.size()+1]= new JPanel();
        f[s.objects.size()+1].add(clear);
        for (int i=0 ;i<=s.objects.size()+1;i++)
            add(f[i]);
        
        
        this.setVisible(true);
        }
        
    }
}
