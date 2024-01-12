package mainclass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class GameRecord implements Serializable{

	private boolean winner;
	private int bomb_num;
	private int ID;
	private String player_name;
	private Grid hp_grid,cp_grid;
	ArrayList <BattleshipMoveResult> hp_record,cp_record;
	private Calendar start,end;
	public void set_hp_record(ArrayList <BattleshipMoveResult> hp_record) 
	{ 
		this.hp_record =hp_record;
	}
	public void set_winner(boolean winner)
	{
		this.winner = winner;
	}
	public boolean get_winner()
	{
		return winner;
	}
	public void set_cp_record(ArrayList <BattleshipMoveResult> cp_record) 
	{ 
		this.cp_record = cp_record;
	}
	GameRecord(int ID,String name,Grid hp_grid,Grid cp_grid,int bomb_num)
	{
		this.bomb_num = bomb_num;
		start =start.getInstance();
		this.ID = ID;
		this.player_name =name;
		this.hp_grid = hp_grid;
		this.cp_grid = cp_grid;
		

	}
	public int get_bomb_num()
	{
		return bomb_num;
	}
	public void set_end()
	{
		end= end.getInstance();
	}
	public int get_id()
	{
		return ID;
	}
	public String get_name()
	{
		return player_name;	
	}
	public Grid get_cp_grid()
	{
		return cp_grid;
	}
	public Grid get_hp_grid()
	{
		return hp_grid;
	}
	public Calendar get_start_time()
	{
		return start;
	}
	public Calendar get_end_time()
	{
		return end;
	}
	public ArrayList<BattleshipMoveResult> get_hp_record()
	{
		return hp_record;
	}
	public ArrayList<BattleshipMoveResult> get_cp_record()
	{
		return cp_record;
	}
	
	
	
	
	
}
