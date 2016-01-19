import java.io.*;
import menu.*;



public class Console {

	public static void main(String[] args) throws IOException {
		
		
		DBKeuzeMenu dBKeuzeMenu = new DBKeuzeMenu();
		dBKeuzeMenu.toonMenu();
		//DBConnectivityManagement inloggen = new DBConnectivityManagement();
		//inloggen.toonMenu();
		
	} 
	

		// inlogscherm wordt getoond met invoerveld voor databaseurl, user, password,
		// waarna de user op het hoofdmenu aankomt
				
		// hoofdmenu : 
		// 1 crud handelingen
		// 2 klasse selectie
		// 3 uitloggen
		// 4 stoppen
				
		// indien user optie 1 heeft gekozen in hoofdmenu, volgt het :
		//
		// vervolgmenu "crud handeling" :
		// 1 create
		// 2 read
		// 3 update
		// 4 delete
		// 5 terug naar het hoofdmenu
		
		// indien user optie 2 heeft gekozen in hoofdmenu, volgt het :
		//
		// vervolgmenu "klasse selectie" :
		// 1 klant
		// 2 adres
		// 3 bestelling
		// 4 artikel
		// 5 terug naar het hoofdmenu
		
		// indien user optie 3 heeft gekozen in hoofdmenu, volgt weer het inlogscherm :
		//
		// vervolgmenu "klasse selectie" :
		// 1 klant
		// 2 adres
		// 3 bestelling
		// 4 artikel
		// 5 terug naar het hoofdmenu
		
		// indien user optie 5 heeft gekozen in hoofdmenu, wordt het programmaatje beeindigd 
	
		

		
}


