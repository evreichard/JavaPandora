/**
 * Evan Reichard
 * https://github.com/evreichard
 * evan@evanreichard.com
 * 2013 - 2014
 **/

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class PandoraGUI{
	
	MainPandora pandoraBackEnd = new MainPandora();
	Scanner in = new Scanner(System.in);
	
	public PandoraGUI(){
		
		// Prompt Password
		String tempPassArr[] = promptPassword();
		
		// Login with password
		pandoraBackEnd.pandoraLogin(tempPassArr[0], tempPassArr[1]);
		
		QueueManager queueMan = new QueueManager(pandoraBackEnd);
		queueMan.saveAsMP3(true);
		
		queueMan.playStation(AskStation());
		
		String tempIn;
		
		while(true){
			tempIn = in.next();
			
			if(tempIn.equals("p")){
				queueMan.pause();
			}else if(tempIn.equals("r")){
				queueMan.resume();
			}else if(tempIn.equals("n")){
				queueMan.nextSong();
			}else if(tempIn.equals("s")){
				// Stop everything here
				queueMan.stop();
				queueMan.playStation(AskStation());
			}else if(tempIn.equals("h")){
				System.out.println("p - pause\nr - resume\nn - next\ns - stations");
			}
		}		
	}
	
	public String AskStation(){
		// Get Station List
		ArrayList<ArrayList<String>> tempStationList = pandoraBackEnd.getStationList();
		
		// Print and choose station
		for(ArrayList<String> tempAL : tempStationList){
			System.out.println("(" + (tempStationList.indexOf(tempAL) + 1) + ") " + tempAL.get(0) + ", " + tempAL.get(1));
		}
		
		System.out.print("\nPlease enter a station: ");
		int selection = in.nextInt() - 1;
		String stationId = tempStationList.get(selection).get(1);
		
		return stationId;
	}
	
	// [TEMPORARY] This prompts for the password, implement GUI in this.
	public String[] promptPassword(){
		Scanner in = new Scanner(System.in);
		System.out.print("Email: ");
		String username = in.nextLine();
		Console console = System.console();
		char passwordArray[] = console.readPassword("Password: ");
		String tempStrArr[] = {username, new String(passwordArray)};
		
		return tempStrArr;
	}
	
	public static void main(String args[]){
		new PandoraGUI();
	}
}