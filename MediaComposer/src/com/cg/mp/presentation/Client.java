package com.cg.mp.presentation;

import java.sql.Date;
import java.time.LocalDate;
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

public class Client {

	public  void clientTest(int choice3,int userId,String checkLogin) {
		
		/**************Object Creation*****************/
		ISongService songService=new SongService();
		ComposerMasterDTO composerMasterDTO=new ComposerMasterDTO();
		UserMasterDTO userMasterDTO=new UserMasterDTO();
		ArtistMasterDTO artistMasterDTO=new ArtistMasterDTO();
		ClientArtist clientTest=new ClientArtist();
		ClientSongs clientSongs=new ClientSongs();
		Scanner sc = new Scanner(System.in);
		/************************************************/
		
		/************************Variable declaration and initialization*********************/
		int choice1,choice2,composerId=0,count=0,artistId=0,wrongLogin=0;
		String composerName="",choice7="";
		String composerBorn="",composerDied="",composerCaeipiNumber="",composerMusicSocId="";
		List<SongMasterDTO>songsList=new ArrayList();
		char choice5;
		String password;
		Date composerDiedDate=null,composerBornDate=null;
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String choiceArtistName,choiceArtistBirthDate,choiceArtistType,choiceDeathStatus;
		String artistBorn,artistName,artistDeathStatus,artistDied,artistType;
		Date artistBornDate=null;
		Date artistDiedDate=null;
		String stringCheck="";
		/************************************************************************************/
		
		/**********************Regex pattern initialization*******************/
		String patternId="[0-9]{6}";
		String patternName="^[A-Za-z]{1,50}$";
		String patternDate="[1-2][0-9][0-9]{2}[-][0-1][0-9][-][0-3][0-9]";
		String patternCaeipi="[0-9]{7,10}";
		String patternMusSocId="[A-Za-z0-9]{3}";
		/**********************************************************************/
			
		/*************do while loop for main menu***************/
		
							/********************switch case for admin menu*********************/
		if(checkLogin=="admin")
		{
							switch(choice3)
							{
							
							/***************case for adding composer********************/
							case 1:
								do
								{
									System.out.println("Enter Composer name:");
									composerName=sc.next()+sc.nextLine();
									choice7="";
								//	if(Pattern.matches(patternName,composerName)==false)
									if(composerName.matches("^[A-Za-z][A-Za-z ]{1,50}$")==false)
									{
										try
										{
											throw new SongException("Please enter a valid name. Only alphabets and blanks spaces allowed");
										}catch(SongException e)
										{
											System.out.println(e.getMessage());
											choice7="again";
										}
								}
								}while(choice7=="again");
								
								do
								{
									System.out.println("Enter Composer Born date:");
									composerBorn=sc.next();
									choice7="";
									try
									{
										composerBornDate=Date.valueOf(composerBorn);
										if((composerBornDate.toLocalDate()).isAfter(LocalDate.now()))
											throw new SongException("Date entered must be before present date");
									}catch(SongException e)
									{
										System.out.println(e.getMessage());
										choice7="again";
										
									}
									catch(Exception ex)
									{
										System.out.println("Please enter date in specified format eg  yyyy-mm-dd");
										choice7="again";
									}
								
								}while(choice7=="again");
								formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
								LocalDate bornDateLocal=composerBornDate.toLocalDate();
								
								System.out.println("Is the composer dead?(y/n)");
								String choice4=sc.next();
								if(choice4.equals("Y") || choice4.equals("y"))
								{
									do
									{
										System.out.println("Enter Composer Died date:");
										composerDied=sc.next();
										choice7="";
										try
										{
											composerDiedDate=Date.valueOf(composerDied);
											if((composerDiedDate.toLocalDate()).isAfter(LocalDate.now()))
												throw new SongException("Date entered must be before present date");
											if((composerDiedDate.toLocalDate()).isBefore(bornDateLocal))
												throw new SongException("Death date must be after birth date");
										}catch(SongException e)
										{
											System.out.println(e.getMessage());
											choice7="again";
											
										}
										catch(Exception ex)
										{
											System.out.println("Please enter date in specified format eg  yyyy-mm-dd");
											choice7="again";
										}
									
									}while(choice7=="again");
									
									composerMasterDTO.setComposerDiedDate(composerDiedDate);
								}
								else
									composerMasterDTO.setComposerDiedDate(null);
								
								do
								{
									System.out.println("Enter Composer Caeipi number:");
									composerCaeipiNumber=sc.next();
									choice7="";
									if(composerCaeipiNumber.matches("[0-9]{7,10}")==false)
									{
										try
										{
											throw new SongException("Please enter a valid cae/ipi number. Only numbers allowed and length "
													+ "between 7 digits and 10 digits allowed only.");
										}catch(SongException e)
										{
											System.out.println(e.getMessage());
											choice7="again";
										}
								    }
								}while(choice7=="again");
								
								do
								{
									System.out.println("Enter Composer Music Society Id");
									composerMusicSocId=sc.next();
									choice7="";
									if(composerMusicSocId.matches("[A-Za-z0-9]{3}")==false)
									{
										try
										{
											throw new SongException("Please enter a valid 3 letters Id. Note: music society id is case sensitive.");
										}catch(SongException e)
										{
											System.out.println(e.getMessage());
											choice7="again";
										}
								    }
								}while(choice7=="again");
								
								composerMasterDTO.setComposerName(composerName);
								composerMasterDTO.setComposerBornDate(composerBornDate);
								composerMasterDTO.setComposerCaeipiNumber(composerCaeipiNumber);
								composerMasterDTO.setComposerMusicSocId(composerMusicSocId);
								composerMasterDTO.setComposerDelFlag(1);
								try {

									System.out.println("Composer succesfully added with composer Id="
											+songService.addComposer(composerMasterDTO,userId));
								} catch (SongException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getMessage());
								}
								break;
								
								
								/***************case for searching composer********************/
							case 2:
								do
								{
									System.out.println("Enter  composer Id: ");
									stringCheck=sc.next()+sc.nextLine();
									choice7="";
								//	if(Pattern.matches(patternName,composerName)==false)
									if(stringCheck.matches("^[0-9]{1,6}$")==false)
									{
										try
										{
											throw new SongException("Please enter a valid composer Id. Only numeric value allowed max upto 6 digits.");
										}catch(SongException e)
										{
											System.out.println(e.getMessage());
											choice7="again";
										}
								}
								}while(choice7=="again");
								composerId=Integer.parseInt(stringCheck);
								try {
									composerMasterDTO=songService.getComposerById(composerId);
									if(composerMasterDTO.getComposerName()==null)
									{
										try
										{
											throw new SongException("The composer you are searching for is unavailable.");
										}
										catch(SongException e) {
											System.out.println(e.getMessage());
										}
									}

									else
										System.out.println(composerMasterDTO);
								} catch (SongException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getMessage());
								}

								break;
								
								
								/***************case for editing composer details********************/
							case 3:
								do
								{
									System.out.println("Enter  composer Id: ");
									stringCheck=sc.next()+sc.nextLine();
									choice7="";
								//	if(Pattern.matches(patternName,composerName)==false)
									if(stringCheck.matches("^[0-9]{1,6}$")==false)
									{
										try
										{
											throw new SongException("Please enter a valid composer Id. Only numeric value allowed max upto 6 digits.");
										}catch(SongException e)
										{
											System.out.println(e.getMessage());
											choice7="again";
										}
								}
								}while(choice7=="again");
								composerId=Integer.parseInt(stringCheck);
								
								ComposerMasterDTO composerMasterDTOEdit=null;
								try {
									composerMasterDTOEdit = songService.getComposerById(composerId);
								} catch (SongException e2) {
									// TODO Auto-generated catch block
									System.out.println(e2.getMessage());
								}
								if(composerMasterDTOEdit.getComposerId()==0)
								{System.out.println("The composer id "+composerId+" is unavailable");break;}
								
								else
								{
									System.out.println("What do u want to edit?");
									System.out.println("*************************");
									System.out.println("1.Death Date");
									System.out.println("2.Caeipi number");
									System.out.println("3.Music Society Id");
									System.out.println("Enter choice: ");
									int choice6=sc.nextInt();
								switch(choice6)
								{
								
								
								/***************case for editing composer death date********************/
								case 1:
									
									/**********************do while loop for editing composer died date**************************/
									do
									{
										System.out.println("Enter Composer Died date in yyyy-mm-dd format:");
										composerDied=sc.next();
										choice7="";
										try
										{
											composerDiedDate=Date.valueOf(composerDied);
											if((composerDiedDate.toLocalDate()).isAfter(LocalDate.now()))
												throw new SongException("Date entered must be before present date");
											if((composerDiedDate.toLocalDate()).isBefore((composerMasterDTOEdit.getComposerBornDate()).toLocalDate()))
												throw new SongException("Death date must be after birth date");
										}catch(SongException e)
										{
											System.out.println(e.getMessage());
											choice7="again";
											
										}
										catch(Exception ex)
										{
											System.out.println("Please enter date in specified format eg  yyyy-mm-dd");
											choice7="again";
										}
									
									}while(choice7=="again");
									composerMasterDTOEdit.setComposerDiedDate(composerDiedDate);
									
									try {
										songService.editComposerDetails(composerMasterDTOEdit,choice6,userId);
										System.out.println("Composer details succesfully edited");
									} catch (SongException e) {
										// TODO Auto-generated catch block
										System.out.println(e.getMessage());
									}

									break;
								
									
									
									/***************case for editing composer cae/ipi number********************/
								case 2:
									
									/**********************do while loop for editing composer cae/ipi number**************************/
									do
									{
										System.out.println("Enter Composer Caeipi number:");
										composerCaeipiNumber=sc.next();
										choice7="";
										if(composerCaeipiNumber.matches("[0-9]{7,10}")==false)
										{
											try
											{
												throw new SongException("Please enter a valid cae/ipi number. Only numbers allowed and length "
														+ "between 7 digits and 10 digits allowed only.");
											}catch(SongException e)
											{
												System.out.println(e.getMessage());
												choice7="again";
											}
									    }
									}while(choice7=="again");

									break;
									
									
									/***************case for editing music society id********************/
								case 3:
									
									/**********************do while loop for editing composer music society id**************************/
									do
									{
										System.out.println("Enter Composer Music Society Id");
										composerMusicSocId=sc.next();
										choice7="";
										if(composerMusicSocId.matches("[A-Za-z0-9]{3}")==false)
										{
											try
											{
												throw new SongException("Please enter a valid 3 letters Id. Note: music society id is case sensitive.");
											}catch(SongException e)
											{
												System.out.println(e.getMessage());
												choice7="again";
											}
									    }
									}while(choice7=="again");
									break;
									
									/***************default case for editing composer details********************/
								default:
									System.out.println("Please enter a valid choice.");
									break;
								}
								}
								break;
								
								/****************Case for deleting composer details********************/
							case 4:
								do
								{
									System.out.println("Enter  composer Id: ");
									stringCheck=sc.next()+sc.nextLine();
									choice7="";
								//	if(Pattern.matches(patternName,composerName)==false)
									if(stringCheck.matches("^[0-9]{1,6}$")==false)
									{
										try
										{
											throw new SongException("Please enter a valid composer Id. Only numeric value allowed max upto 6 digits.");
										}catch(SongException e)
										{
											System.out.println(e.getMessage());
											choice7="again";
										}
								}
								}while(choice7=="again");
								composerId=Integer.parseInt(stringCheck);
								try {
									songService.deleteComposerDetails(composerId);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									System.out.println(e.getMessage());
								}
								break;
								
								
								/***************case for displaying all composer********************/
							case 5:
								List<ComposerMasterDTO>composerList=new ArrayList();
								try {
									composerList=songService.showAllComposerDetails();
									for(ComposerMasterDTO composerMasterDTOList:composerList)
										System.out.println(composerMasterDTOList);
								} catch (SongException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getMessage());
								}
								break;
								
								
								
								/***************case for associating songs to a composer********************/
							case 6:
								clientSongs.clientSongsTest(choice3,userId);
								break;
								
								
								/***************case for adding Artist details********************/
							case 7:
								
								do
								{
									System.out.println("Enter Artist's name:");
									artistName=sc.next()+sc.nextLine();
									 choiceArtistName="";
									if(artistName.matches("^[A-Za-z][A-Za-z ]{1,50}$")==false)
									{
										try
										{
											throw new SongException("Please enter a valid name. Only alphabets and blanks spaces allowed");
										}catch(SongException e)
										{
											System.out.println(e.getMessage());
											choiceArtistName="again";
										}
									}
								}while(choiceArtistName.equals("again"));
								do
								{
									System.out.println("ENter the Artist Type:M for Male and F for Female");
									artistType=sc.next();
									choiceArtistType="";
									if(artistType.equals("M")|| artistType.equals("F"))
										artistMasterDTO.setArtistType(artistType);
									else
									{
										try{
											throw new SongException("Enter a valid Artist Type");
										}
										catch(SongException e)
										{
											System.out.println(e.getMessage());
											choiceArtistType="again";
										}
									}
								}while(choiceArtistType=="again");
								
								
								do
								{
									System.out.println("Enter Artist Born date:");
									artistBorn=sc.next();
									choice7="";
									try
									{
										artistBornDate=Date.valueOf(artistBorn);
										if((artistBornDate.toLocalDate()).isAfter(LocalDate.now()))
											throw new SongException("Date entered must be before present date");
									}catch(SongException e)
									{
										System.out.println(e.getMessage());
										choice7="again";
										
									}
									catch(Exception ex)
									{
										System.out.println("Please enter date in specified format eg  yyyy-mm-dd");
										choice7="again";
									}
								
								}while(choice7=="again");
								formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
								bornDateLocal=artistBornDate.toLocalDate();
								
								System.out.println("Is the Artist dead?(y/n)");
								choice4=sc.next();
								if(choice4.equals("Y") || choice4.equals("y"))
								{
									do
									{
										System.out.println("Enter Artist Died date:");
										artistDied=sc.next();
										choice7="";
										try
										{
											artistDiedDate=Date.valueOf(artistDied);
											if((artistDiedDate.toLocalDate()).isAfter(LocalDate.now()))
												throw new SongException("Date entered must be before present date");
											if((artistDiedDate.toLocalDate()).isBefore(bornDateLocal))
												throw new SongException("Death date must be after birth date");
										}catch(SongException e)
										{
											System.out.println(e.getMessage());
											choice7="again";
											
										}
										catch(Exception ex)
										{
											System.out.println("Please enter date in specified format eg  yyyy-mm-dd");
											choice7="again";
										}
									
									}while(choice7=="again");
									
									artistMasterDTO.setArtistDiedDate(artistDiedDate);
								}
								else
									artistMasterDTO.setArtistDiedDate(null);
								artistMasterDTO.setArtistName(artistName);
								artistMasterDTO.setArtistBornDate(artistBornDate);
								artistMasterDTO.setCreatedBy(userId);
								artistMasterDTO.setUpdatedBy(userId);
								artistMasterDTO.setArtistDelFlag(1);
								int status=0;
								try {

									status=songService.addNewArtist(artistMasterDTO);
									System.out.println("Artist succesfully added with Artist Id="+status);
								} catch (SongException e) {
									System.out.println(e.getMessage());
								}
										
									
							break;
							
							
							/***************case for searching an artist********************/
							case 8:
								do
								{
									System.out.println("Enter Artist Id: ");
									stringCheck=sc.next()+sc.nextLine();
									choice7="";
								//	if(Pattern.matches(patternName,composerName)==false)
									if(stringCheck.matches("^[0-9]{1,6}$")==false)
									{
										try
										{
											throw new SongException("Please enter a valid artistId. Only numeric value allowed max upto 6 digits.");
										}catch(SongException e)
										{
											System.out.println(e.getMessage());
											choice7="again";
										}
								}
								}while(choice7=="again");
								artistId=Integer.parseInt(stringCheck);
								List<ArtistMasterDTO> artistMasterList=new ArrayList();
								try {
									artistMasterList = songService.searchArtist(artistId);
								} catch (SongException e1) {
									// TODO Auto-generated catch block
									System.out.println(e1.getMessage());
								}
								if(artistMasterList.isEmpty())
								{
									try
									{
										throw new SongException("The Artist you are searching for is unavailable.");
									}
									catch(SongException e)
									{
										System.out.println(e.getMessage());
									}
								}
								else
								{
									for(ArtistMasterDTO ArtistMasterDTO:artistMasterList )
									{
										System.out.println(ArtistMasterDTO);
									}
								}
								break;
								
								
					}/*************end of admin menu in composer client**** next cases are in artist client**************/
		}
					
					
					/***********************User Menu***************************/
					else if(checkLogin=="user")
					{
							
						switch(choice3)
						{
							/***************case for searching composer by name********************/
							case 1:
								System.out.print("Enter composer name:");
								composerName=sc.next()+sc.nextLine();

								try {
									songsList=songService.getSongsByName(composerName);
									if(songsList.size()==0)
									{
										try
										{
											throw new SongException("No songs related to the name "+
													composerName+" found :'( . Please try for other composers :) .");
										}
										catch(SongException e) {
											System.out.println(e.getMessage());
										}
									}

									else
									{
										for(SongMasterDTO songMasterDTOList:songsList)
											System.out.println(songMasterDTOList.displaySongsDetails());
									}
								} catch (SongException e) {
									
									System.out.println(e.getMessage());
								}

								break;
								
								
								/***************case for searching composer by music society id********************/
							case 2:
								System.out.print("Enter music society ID:");
								String composerMusSocId=sc.next();
								try {
									songsList=songService.getSongsBySocId(composerMusSocId);
									if(songsList.size()==0)
									{
										try
										{
											throw new SongException("No songs related to the society Id "+
													composerMusSocId+" found :'( . Please try for other society Id :) .");
										}
										catch(SongException e) {
											System.out.println(e.getMessage());
										}
									}

									else
									{
										for(SongMasterDTO songMasterDTOList:songsList)
											System.out.println(songMasterDTOList.displaySongsDetails());
									}
								} catch (SongException e) {
									// TODO Auto-generated catch block
									System.out.println(e.getMessage());
								}

								break;
								
								
								/***************default case for user menu********************/
							default:
								System.out.println("Please enter a valid choice.");
								break;
							}
							
					}/*****************end of user menu switch case***************************/
					
					
					
	}

}
