import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
class SnakeNLadder extends JFrame implements ActionListener
{
	JLabel jl[], w1, l1,l2;
	int p1=0,p2=0;
	int c=0;
	JButton dice,player1,player2;
	JPanel jp1,jp2;	
	static Random rand = new Random();
	int prev1 = 0, prev2 = 0;
	static int flag=0;	
	int arr[] = {-1,90,91,92,93,94,95,96,97,98,99,
			89,88,87,86,85,84,83,82,81,80,
			70,71,72,73,74,75,76,77,78,79,
			69,68,67,66,65,64,63,62,61,60,
			50,51,52,53,54,55,56,57,58,59,
			49,48,47,46,45,44,43,42,41,40,
			30,31,32,33,34,35,36,37,38,39,
			29,28,27,26,25,24,23,22,21,20,
			10,11,12,13,14,15,16,17,18,19,
			9,8,7,6,5,4,3,2,1,0};

	int game[] = {100,99,98,97,96,95,94,93,92,91,
			81,82,83,84,85,86,87,88,89,90,
			80,79,78,77,76,75,74,73,72,71,
			61,62,63,64,65,66,67,68,69,70,
			60,59,58,57,56,55,54,53,52,51,
			41,42,43,44,45,46,47,48,49,50,
			40,39,38,37,36,35,34,33,32,31,
			21,22,23,24,25,26,27,28,29,30,
			20,19,18,17,16,15,14,13,12,11,
			1, 2, 3, 4, 5, 6, 7, 8, 9,10};	

	SnakeNLadder()
	{
		super("Snake and Ladder");		
		jp1 = new JPanel();
		jp1.setLayout(new GridLayout(10,10));
		jp2 = new JPanel();
		jp2.setLayout(null);
		jl = new JLabel[100];
		dice = new JButton(new ImageIcon(getClass().getResource("dice\\3.jpg")));
		dice.setBackground(Color.BLACK);
		dice.addActionListener(this);
		dice.setBounds(1100,300,80,80);
		jp2.add(dice);
		
l1=new JLabel("Player 1: Green");
l2=new JLabel("Player 2: Blue");
		
jp2.add(l1);
jp2.add(l2);
l1.setBounds(1100,350,400,100);
l2.setBounds(1100,380,400,100);

		for(int i=0;i<100;i++)
		{
			String str = Integer.toString(game[i]);
			jl[i] = new JLabel();
			jl[i].setIcon(new ImageIcon(str+".PNG"));
			jp1.add(jl[i]);		
		}
		w1 = new JLabel("WON !");
		
		player1 = new JButton("PLAYER 1");
		player2 = new JButton("PLAYER 2");
		player1.setBounds(950,100,200,50);
	
		player2.setBounds(1250,100,200,50);
		jp2.add(player1);
		jp2.add(player2);	

		jp1.setBounds(0,0,800,800);
		jp2.setBounds(1000,0,500,1000);
		jp2.setBackground(Color.GRAY);
		add(jp1);
		add(jp2);
		setSize(1500,835);
		setVisible(true);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		dice.setEnabled(true);
		//player1.setEnabled(false);
		player2.setEnabled(false);
	}
	@Override
	public void actionPerformed(ActionEvent v)
	{                  
		 if(v.getSource() == dice)
		{
			int n = rand.nextInt(6) +1;
			System.out.println(n);
			dice.setIcon(new ImageIcon(getClass().getResource("dice\\"+Integer.toString(n)+".jpg")));
			play(n);
						
		}
		
		
	}
	
	public void play(int l)
	{
		int k=l;
		int a[] = new int[100];
		for(int i=0;i<100;i++)
		{
			a[i] = 101;
		}
		// ladders
		a[93] = 86;
		a[98] = 69;
		a[80] = 62;
		a[77] = 13;
		a[60] = 41;
 		a[49] = 36;
		a[32] = 10;
		a[29] = 9;
		
		//snakes
		a[1] = 22;
		a[5] = 25;
 		a[7] = 27;
		a[16] = 73;
   		a[31] = 81;
		a[33] = 40;
		a[46] = 66;
		a[83] = 96;

		
		if(c%2==0)
		{
			
			if(p1+k==100)
			{
				jp2.add(w1);
				jl[0].setIcon(new ImageIcon("green.png"));
				System.out.println("Player 1 wins");
				w1.setBounds(950,152,200,100);
				
				if(flag==1)
				{
					jl[prev1].setIcon(new ImageIcon("blue.png"));
				}
				else
				{
					jl[prev1].setIcon(new ImageIcon(Integer.toString(game[prev1])+".PNG"));
				}
				dice.setEnabled(false);
				

			}
			else if((p1+k) > 100)
			{
				int d = rand.nextInt(6) +1;
				c++;
				play(d);
			}		
			else
			{
				p1=p1+k ;
				int p11 = p1;
				if(a[arr[p1]] != 101)
				{
					p11 = a[arr[p1]];
					p1 =  game[p11];
				}
				else
				{
					p11 = game[p11];
				}

				jl[arr[p1]].setIcon(new ImageIcon("green.png"));
				
				
		//both at same position
				if(flag==1)
				{
					jl[prev1].setIcon(new ImageIcon("blue.png"));

				}
				else
				{
					jl[prev1].setIcon(new ImageIcon(Integer.toString(game[prev1])+".PNG"));
				}
				if(p1==p2)
				{
					flag=1;
					jl[arr[p1]].setIcon(new ImageIcon("bg.png"));
				}
				else
				{
					flag=0;
				}
				prev1 = arr[p1];
				System.out.println("Player 1 at : "+ p1);
				c++;
				player1.setEnabled(false);
				player2.setEnabled(true);
			}
		}
		else
		{
			if(p2+k==100)
			{
				jl[0].setIcon(new ImageIcon("blue.png"));
				System.out.println("Player 2 wins");
				w1.setBounds(1250,152,200,100);
				jp2.add(w1);
				
				dice.setEnabled(false);
				

				if(flag==1)
				{
					jl[prev2].setIcon(new ImageIcon("green.png"));
				}
				else
				{
					jl[prev2].setIcon(new ImageIcon(Integer.toString(game[prev2])+".PNG"));
				}
			}
			else if((p2+k) > 100)
			{
				int d = rand.nextInt(6) +1;
				c++;
				play(d);
			}	
			else
			{
				p2=p2+k ;
				int p22 = p2;
				if(a[arr[p2]] != 101)
				{
					p22 = a[arr[p2]];
					p2 =  game[p22];
				}
				else
				{
					p2 = p2;
				}
					jl[arr[p2]].setIcon(new ImageIcon("blue.png"));	
				
			//both at same position
				if(flag==1)
				{
					jl[prev2].setIcon(new ImageIcon("green.png"));

				}
				else
				{
					jl[prev2].setIcon(new ImageIcon(Integer.toString(game[prev2])+".PNG"));

				}
				if(p1==p2)
				{
					flag=1;
					jl[arr[p2]].setIcon(new ImageIcon("bg.png"));	
				}
				else
				{
					flag=0;
				}
				prev2 = arr[p2];			
				System.out.println("Player 2 at : "+ p2);
				c++;
				player1.setEnabled(true);
				player2.setEnabled(false);
			}
		}	
		
	}
	public static void main(String...s)
	{
		
		new SnakeNLadder();
	}
}