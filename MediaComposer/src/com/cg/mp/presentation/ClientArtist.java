package com.cg.mp.presentation;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.mp.dto.ArtistMasterDTO;
import com.cg.mp.dto.SongMasterDTO;
import com.cg.mp.exception.SongException;
import com.cg.mp.service.ISongService;
import com.cg.mp.service.SongService;

public class ClientArtist {
	public void clientArtistTest(int userChoice,int userId) throws SongException{
		Scanner sc=new Scanner(System.in);;
		ArtistMasterDTO artistMaster;
		ArtistMasterDTO artistMasterDTO = new ArtistMasterDTO();
		int artistId=0;
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ISongService songService=new SongService();
		String artistName="";
		Date artistBornDate=null;
		Date artistDiedDate=null;
		String choiceArtistName,choiceArtistBirthDate,choiceArtistType,choiceDeathStatus,stringCheck="";
		String artistBorn,artistDeathStatus,artistDied,artistType,choice7;
		switch(userChoice)
		{
		case 8:
			//Artist details for a specific artistID
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
			List<ArtistMasterDTO> artistMasterList=songService.searchArtist(artistId);
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
				for(ArtistMasterDTO ArtistMasterDTO:songService.searchArtist(artistId))
				{
					System.out.println(ArtistMasterDTO);
				}
			}
			break;
			
		case 7:
			//Add a new Artist
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
			LocalDate bornDateLocal=artistBornDate.toLocalDate();
			
			System.out.println("Is the composer dead?(y/n)");
			String choice4=sc.next();
			if(choice4.equals("Y") || choice4.equals("y"))
			{
				do
				{
					System.out.println("Enter Composer Died date:");
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
			break;
			
		
		case 9:
			//Edit an existing artist details
			do
			{
				System.out.println("Enter Artist Id you want to edit: ");
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
			
			List<ArtistMasterDTO> artistMasterDTOList=songService.searchArtist(artistId);
			if(artistMasterDTOList.isEmpty())
			{
				System.out.println("No artist found for the artist id:"+artistId);
				break;
			}
			else
			{
				System.out.println("What do u want to edit?");
				System.out.println("*********");
				System.out.println("1.Death Date");
				System.out.println("Enter choice: ");
				int choiceArtist=sc.nextInt();
				ArtistMasterDTO artistMasterDTOEdit=new ArtistMasterDTO();
				switch(choiceArtist)
				{
				case 1:
					do
					{
						System.out.println("Enter Artist Died date in yyyy-mm-dd format:");
						artistDied=sc.next();
						choice7="";
						try
						{
							artistDiedDate=Date.valueOf(artistDied);
							if((artistDiedDate.toLocalDate()).isAfter(LocalDate.now()))
								throw new SongException("Date entered must be before present date");
							if((artistDiedDate.toLocalDate()).isBefore((artistMasterDTOList.get(0).getArtistBornDate()).toLocalDate()))
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
					artistMasterDTOList.get(0).setArtistDiedDate(artistDiedDate);
					
					try {
						songService.editArtistDetails(artistMasterDTOList.get(0),choiceArtist,userId);
						System.out.println("Artist details succesfully edited");
					} catch (SongException e) {
						System.out.println(e.getMessage());
					}
					
					break;
				}
				break;
			}
		case 10:
			//Delete an artist details for a specific artist id
		{
			System.out.println("Enter the artist's id you want to delete");
			artistId=sc.nextInt();
			int status=songService.deleteArtistDetails(artistId);
			if(status==0)
			{
				System.out.println("No deletion has been performed");
				break;
			}
			else
				System.out.println("Deletion done for artistId:"+artistId);
			break;
		}
		case 11:
			//List all the artist
		{
			System.out.println("The list of artists:");
			List<ArtistMasterDTO> artistList=new ArrayList();
			try
			{
				ArtistMasterDTO artist = new ArtistMasterDTO();
				artist.disp();
				artistList=songService.retrieveAllArtists();
			}
			catch(SongException se)
			{
				System.out.println(se.getMessage()+"No artist Found");
			}
		for(ArtistMasterDTO artists: artistList)
		{
			System.out.println(artists.toString());
		}
		}
		break;
		
		case 15:
			//If the artist is present or not
			System.out.print("Enter Artist's name:");
			 String searchArtistName=sc.next()+sc.nextLine();
			
			try {
				List<SongMasterDTO> songsList=songService.getSongsByArtistName(searchArtistName);
				if(songsList.size()==0)
				{
					try
					{
						throw new SongException("No songs related to the name "+
								searchArtistName+".");
					}
					catch(SongException e) {
						System.out.println(e.getMessage());
					}
				}
					
				else
				{
					System.out.println("---------------------------------------------------------------------------------");
					System.out.printf("%-30s%-30s%-30s\n","Song ID","Song Name","Song Duration");
					System.out.println("---------------------------------------------------------------------------------");
					for(SongMasterDTO songMasterDTOList:songsList)
						System.out.println(songMasterDTOList.displaySongsDetails());
				}
			} catch (SongException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 16:
			//Search the artist type based on Artist Type
		{
			System.out.println("Enter the type of artist you want to search:");
			System.out.println("Press M for Male or Press F for Female");
			
			artistType=sc.next();
			List<ArtistMasterDTO> statusForType=songService.searchArtistByType(artistType);
			if(statusForType.isEmpty())
			{
				System.out.println("Sorry!No artist has been found with this artistType"+artistType+" !!");
				break;
			}
			else{
				ArtistMasterDTO artist = new ArtistMasterDTO();
				artist.disp();
				for(ArtistMasterDTO ArtistMasterDTO:songService.searchArtistByType(artistType))
				{
					System.out.println(ArtistMasterDTO);
				}
			}
			break;
		}
		}
		}

}
