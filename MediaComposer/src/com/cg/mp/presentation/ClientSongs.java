package com.cg.mp.presentation;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.mp.dto.ArtistSongAssoc;
import com.cg.mp.dto.ComposerMasterDTO;
import com.cg.mp.dto.ComposerSongAssoc;
import com.cg.mp.dto.SongMasterDTO;
import com.cg.mp.exception.SongException;
import com.cg.mp.service.ISongService;
import com.cg.mp.service.SongService;

public class ClientSongs {

	public void clientSongsTest(int choice1,int userId) {
		// TODO Auto-generated method stub
		ISongService songService=new SongService();
		ComposerMasterDTO composerMasterDTO=new ComposerMasterDTO();
		SongMasterDTO songMasterDTO = new SongMasterDTO();
		Scanner sc = new Scanner(System.in);
		int choice2,choice3,composerId=0;
		char choice5;
		String password,stringCheck="",choice7="";
		DateTimeFormatter formatter=null;
		switch(choice1)
		{
		
					case 6:
						ComposerSongAssoc composerSongAssoc = new ComposerSongAssoc();
						System.out.println("********************************");
						System.out.println("Associate Song to Composer");
						System.out.println("********************************");
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
						composerSongAssoc.setComposerId(composerId);


						do
						{
							System.out.println("Enter  song Id: ");
							stringCheck=sc.next()+sc.nextLine();
							choice7="";
						//	if(Pattern.matches(patternName,composerName)==false)
							if(stringCheck.matches("^[0-9]{1,6}$")==false)
							{
								try
								{
									throw new SongException("Please enter a valid song Id. Only numeric value allowed max upto 6 digits.");
								}catch(SongException e)
								{
									System.out.println(e.getMessage());
									choice7="again";
								}
						}
						}while(choice7=="again");
						int sId=Integer.parseInt(stringCheck);
						composerSongAssoc.setSongId(sId);

						try {
							System.out.println(songService.compSongAssoc(composerSongAssoc, userId));
						} catch (SongException e) {

							System.out.println(e.getMessage());
						}
						break;

					case 12:
						ArtistSongAssoc artistSongAssoc = new ArtistSongAssoc();
						System.out.println("********************************");
						System.out.println("Associate Song to Artist");
						System.out.println("********************************");
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
						int aId=Integer.parseInt(stringCheck);
						artistSongAssoc.setArtistId(aId);


						do
						{
							System.out.println("Enter  song Id: ");
							stringCheck=sc.next()+sc.nextLine();
							choice7="";
						//	if(Pattern.matches(patternName,composerName)==false)
							if(stringCheck.matches("^[0-9]{1,6}$")==false)
							{
								try
								{
									throw new SongException("Please enter a valid song Id. Only numeric value allowed max upto 6 digits.");
								}catch(SongException e)
								{
									System.out.println(e.getMessage());
									choice7="again";
								}
						}
						}while(choice7=="again");
						int songId=Integer.parseInt(stringCheck);
						artistSongAssoc.setSongId(songId);

						try {
							System.out.println(songService.artistSongAssoc(artistSongAssoc, userId));
						} catch (SongException e) {

							System.out.println(e.getMessage());
						}
						
						break;
						
					case 13:
						System.out.println("********************************");
						System.out.println("Adding New Song");
						System.out.println("********************************");
						System.out.println("Enter Song Name");
						String songName=sc.next()+sc.nextLine();
						songMasterDTO.setSongName(songName);
						String songDuration;
						int cnt=0;
						do{
							System.out.println("Enter song duration");
							songDuration=sc.next();
							songMasterDTO.setSongDuration(songDuration);
							if(songService.checkSongDuration(songDuration)){
								break;
							}else{

								cnt++;
							}
						}while(cnt<3);
						try
						{
							if(cnt==3)
								throw new SongException();
						}
						catch(SongException e)
						{
							System.out.println("Entered Song Duration is not valid");
							System.exit(0);
						}

						try {

							int status=songService.addSong(songMasterDTO, userId);
							System.out.println("song added successfully with Song Id: "+status);
						} catch (SongException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
						}
						break;

					case 14:
						List<SongMasterDTO> songList=new ArrayList();
						SongMasterDTO song = new SongMasterDTO();
						try {
							song.disp();
							songList=songService.showAllSongDetails();
							for(SongMasterDTO songMasterDTOList:songList)
								System.out.println(songMasterDTOList);
						} catch (SongException e) {
							System.out.println(e.getMessage());
						}
						break;
					}


					
	}

	}
