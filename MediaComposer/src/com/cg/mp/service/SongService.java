package com.cg.mp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.cg.mp.dao.ArtistDAO;
import com.cg.mp.dao.ComposerDAO;
import com.cg.mp.dao.IArtistDAO;
import com.cg.mp.dao.IComposerDAO;
import com.cg.mp.dao.ISongDAO;
import com.cg.mp.dao.SongDAO;
import com.cg.mp.dto.ArtistMasterDTO;
import com.cg.mp.dto.ArtistSongAssoc;
import com.cg.mp.dto.ComposerMasterDTO;
import com.cg.mp.dto.ComposerSongAssoc;
import com.cg.mp.dto.SongMasterDTO;
import com.cg.mp.dto.UserMasterDTO;
import com.cg.mp.exception.SongException;

public class SongService implements ISongService {
	IComposerDAO composerDAO=null	;
	IArtistDAO artistDAO=null;
	ISongDAO songDAO = null;
	public SongService() {
		// TODO Auto-generated constructor stub
		composerDAO=new ComposerDAO();
		artistDAO=new ArtistDAO();
		songDAO = new SongDAO();
		
	}

	@Override
	public String checkLogin(int userId, String password) throws SongException {
		// TODO Auto-generated method stub
		return composerDAO.checkLogin(userId,password);
	}

	@Override
	public int addComposer(ComposerMasterDTO composerMasterDTO, int userId) throws SongException {
		// TODO Auto-generated method stub
		return composerDAO.addComposer(composerMasterDTO,userId);
	}

	@Override
	public ComposerMasterDTO getComposerById(int composerId) throws SongException {
		// TODO Auto-generated method stub
		return composerDAO.getComposerById(composerId);
	}

	@Override
	public void editComposerDetails(ComposerMasterDTO composerMasterDTOEdit,int choice6,int userId) throws SongException {
		// TODO Auto-generated method stub
		composerDAO.editComposerDetails(composerMasterDTOEdit,choice6,userId);
	}

	@Override
	public void deleteComposerDetails(int composerId) throws SongException {
		// TODO Auto-generated method stub
		composerDAO.deleteComposerDetails(composerId);
	}

	@Override
	public List<ComposerMasterDTO> showAllComposerDetails()
			throws SongException {
		// TODO Auto-generated method stub
		return composerDAO.showAllComposerDetails();
	}

	@Override
	public int addUser(UserMasterDTO userMasterDTO, int userId)
			throws SongException {
		// TODO Auto-generated method stub
		return composerDAO.addUser(userMasterDTO, userId);
	}

	@Override
	public List<SongMasterDTO> getSongsByName(String composerName)
			throws SongException {
		// TODO Auto-generated method stub
		return composerDAO.getSongsByName(composerName);
	}

	@Override
	public List<SongMasterDTO> getSongsBySocId(String composerMusSocId)
			throws SongException {
		// TODO Auto-generated method stub
		return composerDAO.getSongsBySocId(composerMusSocId);
	}

	@Override
	public List<ArtistMasterDTO> searchArtist(int artistId)
			throws SongException {
		List<ArtistMasterDTO> artistList=artistDAO.searchArtist(artistId);
		return artistList;
	}
	@Override
	public int getArtistId() throws SongException {
		int artistId=artistDAO.getArtistId();
		return artistId;
	}
	@Override
	public int addNewArtist(ArtistMasterDTO artistMaster) throws SongException {
		int status=0;
		status=artistDAO.addNewArtist(artistMaster);
		return status;
	}
	@Override
	public void editArtistDetails(ArtistMasterDTO artistMasterDTOEdit,
			int choiceArtist,int userId) throws SongException {
		artistDAO.editArtistDetails(artistMasterDTOEdit,choiceArtist,userId);
		
	}
	@Override
	public int deleteArtistDetails(int artistId) throws SongException {
		int status=0;
		status=artistDAO.deleteArtistDetails(artistId);
		return status;
		
	}
	@Override
	public List<ArtistMasterDTO> retrieveAllArtists() throws SongException {
		List<ArtistMasterDTO> artistList=artistDAO. retrieveAllArtists();
		return artistList;
	}
	@Override
	public List<ArtistMasterDTO> searchArtistByName(String artistName)
			throws SongException {
		List<ArtistMasterDTO> artistList=new ArrayList();
		artistList=artistDAO.searchArtistByName(artistName);
				return artistList;
		
	}
	@Override
	public List<ArtistMasterDTO> searchArtistByType(String artistType)
			throws SongException {
		
		List<ArtistMasterDTO> artistList=new ArrayList();
		artistList=artistDAO.searchArtistByType(artistType);
				return artistList;
	}
	@Override
	public List<SongMasterDTO> getSongsByArtistName(String searchartistName)
			throws SongException {
		
		return artistDAO.getSongsByName(searchartistName);
	}
	
	@Override
	public int addSong(SongMasterDTO songMasterDTO, int userId)
			throws SongException {
		// TODO Auto-generated method stub
		return songDAO.addSong(songMasterDTO, userId);
	}

	@Override
	public String compSongAssoc(ComposerSongAssoc composerSongAssoc, int userId)
			throws SongException {
		if(songDAO.checkComposerId(composerSongAssoc.getComposerId())==0){
			return "Composer Id DNE";
		}
		if(songDAO.checkSongId(composerSongAssoc.getSongId())==0){
			return" This Song Id is not present";
		}
		return songDAO.compSongAssoc(composerSongAssoc, userId);
		
	}

	@Override
	public String artistSongAssoc(ArtistSongAssoc artistSongAssoc, int userId)
			throws SongException {
				
		if(songDAO.checkArtistId(artistSongAssoc.getArtistId())==0){
			return "Artist Id DNE";
		}
		if(songDAO.checkSongId(artistSongAssoc.getSongId())==0){
			return" This Song Id is not present";
		}
		return songDAO.artistSongAssoc(artistSongAssoc, userId);
		
	}
	
	@Override
	public boolean checkSongDuration(String songDuration) 
	{
		
		System.out.println(songDuration);
		String pattern1="[0-9]{0,}[:][0-5]{1}[0-9]{1}[:][0-5]{1}[0-9]{1}";

		if(Pattern.matches(pattern1, songDuration))
		{
			return true;
		}
		else
		{                                    
			System.out.println("Song duration is not in proper format");
			return false;
		}
	}

	@Override
	public List<SongMasterDTO> showAllSongDetails() throws SongException {
		return songDAO.showAllSongDetails();
	}
	
}
