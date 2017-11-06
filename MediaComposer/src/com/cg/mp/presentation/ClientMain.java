package com.cg.mp.presentation;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.mp.dto.ArtistMasterDTO;
import com.cg.mp.dto.ComposerMasterDTO;
import com.cg.mp.dto.SongMasterDTO;
import com.cg.mp.dto.UserMasterDTO;
import com.cg.mp.exception.SongException;
import com.cg.mp.service.ISongService;
import com.cg.mp.service.SongService;

public class ClientMain {

	public static void main(String[] args) {
		/**************Object Creation*****************/
		ISongService songService=new SongService();
		ComposerMasterDTO composerMasterDTO=new ComposerMasterDTO();
		UserMasterDTO userMasterDTO=new UserMasterDTO();
		ArtistMasterDTO artistMasterDTO=new ArtistMasterDTO();
		ClientArtist clientTest=new ClientArtist();
		ClientSongs clientSongs=new ClientSongs();
		Client client=new Client();
		Scanner sc = new Scanner(System.in);
		/************************************************/
		
		/************************Variable declaration and initialization*********************/
		int choice1,choice2,choice3,userId,composerId=0,count=0,artistId=0,wrongLogin=0;
		String composerName="",choice7="";
		String composerBorn="",composerDied="",composerCaeipiNumber="",composerMusicSocId="";
		List<SongMasterDTO>songsList=new ArrayList();
		char choice5='n';
		String password;
		String checkLogin="invalid";
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String logoutChoiceNew="";
		String stringUserId="";
		/************************************************************************************/
		
		/**********************Regex pattern initialization*******************/
		String patternId="[0-9]{6}";
		String patternName="^[A-Za-z]{1,50}$";
		String patternDate="[1-2][0-9][0-9]{2}[-][0-1][0-9][-][0-3][0-9]";
		String patternCaeipi="[0-9]{7,10}";
		String patternMusSocId="[A-Za-z0-9]{3}";
		/**********************************************************************/
			
		/*************do while loop for main menu***************/
		do
		{
			System.out.println("Enter choice");
			System.out.println("*************");
			System.out.println("1.Login");
			System.out.println("2.Sign Up");
			System.out.println("3.Exit :(");
			choice1=sc.nextInt();
			
			/*switch case main menu*/
			switch(choice1)
			{
			case 1:
				count=3;
				/******************do while loop for taking credentials max upto 3 times****************/
				do
				{
					System.out.println("Enter Credentials");
					do
					{
						System.out.println("Enter  userId( must be a numeric value max 6 digit): ");
						stringUserId=sc.next()+sc.nextLine();
						choice7="";
					//	if(Pattern.matches(patternName,composerName)==false)
						if(stringUserId.matches("^[0-9]{1,6}$")==false)
						{
							try
							{
								throw new SongException("Please enter a valid userId. Only numeric value allowed max upto 6 digits.");
							}catch(SongException e)
							{
								System.out.println(e.getMessage());
								choice7="again";
							}
					}
					}while(choice7=="again");
					userId=Integer.parseInt(stringUserId);
					
					do
					{
						System.out.println("Password: ");
						password=sc.next()+sc.nextLine();
						choice7="";
					//	if(Pattern.matches(patternName,composerName)==false)
						if(password.matches("^[A-Za-z0-9_.]{1,25}$")==false)
						{
							try
							{
								throw new SongException("Please enter a valid password. Only alphanumeric characters underscore and dots allowed.");
							}catch(SongException e)
							{
								System.out.println(e.getMessage());
								choice7="again";
							}
					}
					}while(choice7=="again");
					
					checkLogin="invalid";
					try {
						checkLogin=songService.checkLogin(userId,password);
					} catch (SongException e) {
						e.getMessage();
					}
					if(checkLogin=="admin")
					{
						System.out.println("Hello Admin.");
						System.out.println("****************");
						
						/******************do while loop for admin menu**********************/
						do
						{
							System.out.println("Admin Menu:");
							System.out.println();
							System.out.println("1.  Add a Composer.");
							System.out.println("2.  Search for a Composer");
							System.out.println("3.  Edit an existing Composer details.");
							System.out.println("4.  Delete a Composer.");
							System.out.println("5.  Show all Composer.");
							System.out.println("6.  Associate song/songs to a Composer.");
							System.out.println("7.  Add an Artist."); 
							System.out.println("8.  Search for an Artist");
							System.out.println("9.  Edit an existing Artist details.");
							System.out.println("10. Delete an Artist.");
							System.out.println("11. Show all Artists.");
							System.out.println("12. Associate song/songs to an Artist.");
							System.out.println("13. Add Songs.");
							System.out.println("14. Show all songs.");
							System.out.println("15. Logout");
							System.out.println("Enter choice:");
							choice3=sc.nextInt();
							
							/********************switch case for admin menu*********************/
							switch(choice3)
							{
							
							/***************case for adding composer********************/
							case 1:
								client.clientTest(choice3,userId,checkLogin);
								break;
								
								
								/***************case for searching composer********************/
							case 2:
								client.clientTest(choice3,userId,checkLogin);
								break;
								
								
								/***************case for editing composer details********************/
							case 3:
								client.clientTest(choice3,userId,checkLogin);
								break;
								
								
								/****************Case for deleting composer details********************/
							case 4:
								client.clientTest(choice3,userId,checkLogin);
								break;
								
								
								/***************case for displaying all composer********************/
							case 5:
								client.clientTest(choice3,userId,checkLogin);
								break;
								
								
								/***************case for songs to a composer********************/
							case 6:
								clientSongs.clientSongsTest(choice3,userId);
								break;
								
								
								/***************case for adding Artist details********************/
							case 7:
								client.clientTest(choice3,userId,checkLogin);
								break;
							
							
							/***************case for searching an artist********************/
							case 8:
								client.clientTest(choice3,userId,checkLogin);
								break;
								
								
								
								/***************case for editing artist details********************/
							case 9:
								try {
									clientTest.clientArtistTest(choice3,userId);
								} catch (SongException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getMessage());
								}
								break;
								
								
								/***************case for deleting an artist********************/
							case 10:
								try {
									clientTest.clientArtistTest(choice3, userId);
								} catch (SongException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getMessage());
								}
								break;
								
								
								/***************case for showing all artist********************/
							case 11:
								try {
									clientTest.clientArtistTest(choice3, userId);
								} catch (SongException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getMessage());
								}
								break;
								
								
								
								/***************case for associating songs to an artist********************/
							case 12:
								clientSongs.clientSongsTest(choice3, userId);
								break;
								
								
								
								/***************case for adding songs********************/
							case 13:
								clientSongs.clientSongsTest(choice3, userId);
								
								break;
								
								/***************case for  Logout********************/
							case 15:
								logoutChoiceNew="a";
								break;	
					
								/***************default case for admin menu********************/
							default:
								System.out.println("Please enter a valid choice.");
								break;
								
								

							}
							if(logoutChoiceNew.equals("a"))
								logoutChoiceNew="";
							else
							{
							System.out.println("Want to continue (y/n)");
							choice5=sc.next().charAt(0);
							count=0;
							}
							
							/***************end of do while loop for admin menu********************/
						}while(choice5 =='y' || choice5=='Y');
						System.out.println("Thank you for your time. Hope to see you soon again. Cheers :)");
					}/*************end of admin menu******************/
					
					
					
					/***********************User Menu***************************/
					else if(checkLogin=="user")
					{
						System.out.println("Hello User");
						System.out.println("************");
						
						/***************do while loop for user menu********************/
						do
						{
							System.out.println("User Menu:");
							System.out.println();
							System.out.println("1.  Search songs by Composer name.");
							System.out.println("2.  Search songs by Composer Music Society ID");
							System.out.println("3.  Search songs by Artist name.");
							System.out.println("4.  Search artist by artist type.");
							System.out.println("Enter your choice");
							choice3=sc.nextInt();
							switch(choice3)
							{
							
							/***************case for searching composer by name********************/
							case 1:
								client.clientTest(choice3,userId,checkLogin);
								break;
								
								
								/***************case for searching composer by music society id********************/
							case 2:
								client.clientTest(choice3,userId,checkLogin);
								break;
								
								
								/***************case for searching artist by gender********************/
							case 3:
								try {
									clientTest.clientArtistTest(15,userId);
								} catch (SongException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getMessage());
								}
								break;
								
								
								/***************case for searching composer by music society id********************/
							case 4:
								try {
									clientTest.clientArtistTest(16,userId);
								} catch (SongException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getMessage());
								}
								break;
								
							case 5 :
								logoutChoiceNew="a";
								break;
								/***************default case for user menu********************/
							default:
								System.out.println("Please enter a valid choice.");
								break;
							}
							if(logoutChoiceNew.equals("a"))
								logoutChoiceNew="";
							else
							{
							System.out.println("Want to continue (y/n)");
							choice5=sc.next().charAt(0);
							count=0;
							}
							
							/***************end of do while loop for user menu********************/
						}while(choice5 =='y' || choice5=='Y');
					}/*****************end of user menu switch case***************************/
					
					
					
					else
					{
						--count;
						if(count!=0)
						{
							System.err.println("Invalid userid password combination. Login denied."+
									(count)+" attempts left");
							System.out.println("Want to log out (y/n)");
							String logoutChoice=sc.next();
							if(logoutChoice.equals("y")||logoutChoice.equals("Y"))
								wrongLogin=0;
							else
								wrongLogin=1;
						
						}
						else
							System.out.println("Sorry you have exhausted all your attempts. Please try after "
									+ "sometime.");
						
						

					}}
				
				/***************end of do while loop for Login Menu********************/
				while(count>0 && wrongLogin==1);

				break;

				/***************Case for sign up**********************/
			case 2:
				count=3;
				
				/***************do while loop for Sign up********************/
				do
				{
					System.out.println("Enter  your Admin Credentials to add users");
					System.out.print("UserId (no space in userId): ");
					userId=sc.nextInt();
					System.out.println("Password: ");
					password=sc.next();
					checkLogin="invalid";
					try {
						checkLogin=songService.checkLogin(userId,password);
					} catch (SongException e) {
						// TODO Auto-generated catch block
						e.getMessage();
					}
					if(checkLogin=="admin")
					{
						System.out.println("UserId and Password successfully validated");
						System.out.println("Enter password for the user:");
						password=sc.next();
						userMasterDTO.setUserPassword(password);
						try {

							System.out.println("User succesfully added with user Id="
									+songService.addUser(userMasterDTO,userId));
						} catch (SongException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
						}
					}
					else
					{
						System.err.println("Invalid userid password combination. Login denied."+
								(--count)+" attempts left");
						
						System.out.println("Want to log out (y/n)");
						String logoutChoice=sc.next();
						if(logoutChoice.equals("y")||logoutChoice.equals("Y"))
							wrongLogin=0;
						else
							wrongLogin=1;
						if(count==0)
							System.out.println("Sorry you have exhausted all your attempts. Please try after "
									+ "sometime.");
						
						
					}}
				
				/***************end of do while loop for sign up********************/
				while(count>0&&wrongLogin==1);
				break;

				/****************Case for system exit*******************/
			case 3:
				System.out.println("Have a great time. Hope to see you soon.");
				System.exit(0);
				break;
				/***************default case for main menu********************/
			default:
				System.out.println("Please enter a valid choice.");
				break;
				


			}
		}while(true);
		

	}

}
