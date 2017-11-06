package com.cg.mp.service;

import java.util.List;

import com.cg.mp.dto.ArtistMasterDTO;
import com.cg.mp.dto.ArtistSongAssoc;
import com.cg.mp.dto.ComposerMasterDTO;
import com.cg.mp.dto.ComposerSongAssoc;
import com.cg.mp.dto.SongMasterDTO;
import com.cg.mp.dto.UserMasterDTO;
import com.cg.mp.exception.SongException;

public interface ISongService {
	
	/************For Composer******************/
	String checkLogin(int userId, String password)throws SongException;

	int addComposer(ComposerMasterDTO composerMasterDTO, int userId)throws SongException;

	ComposerMasterDTO getComposerById(int composerId) throws SongException;

	void editComposerDetails(ComposerMasterDTO composerMasterDTOEdit, int choice6, int userId)throws SongException;

	void deleteComposerDetails(int composerId)throws SongException;

	List<ComposerMasterDTO> showAllComposerDetails()throws SongException;

	int addUser(UserMasterDTO userMasterDTO, int userId)throws SongException;

	List<SongMasterDTO> getSongsByName(String composerName)throws SongException;

	List<SongMasterDTO> getSongsBySocId(String composerMusSocId)throws SongException;
	

	/************For Artist******************/
	
	List<ArtistMasterDTO> searchArtist(int artistId) throws SongException;

	int getArtistId() throws SongException;

	int addNewArtist(ArtistMasterDTO artistMaster)  throws SongException;

	void editArtistDetails(ArtistMasterDTO artistMasterDTOEdit, int choiceArtist,int userId)  throws SongException;

	int deleteArtistDetails(int artistId) throws SongException;

	List<ArtistMasterDTO> retrieveAllArtists() throws SongException;

	List<ArtistMasterDTO> searchArtistByName(String artistName) throws SongException;

	List<ArtistMasterDTO> searchArtistByType(String artistType)  throws SongException;

	List<SongMasterDTO> getSongsByArtistName(String searchartistName) throws SongException; 
	
	
	/*****************For Songs**********************/
	int addSong(SongMasterDTO songMasterDTO, int userId) throws SongException;

	String compSongAssoc(ComposerSongAssoc composerSongAssoc, int userId) throws SongException;

	String artistSongAssoc(ArtistSongAssoc artistSongAssoc, int userId) throws SongException;
	
	public boolean checkSongDuration(String songDuration);
	
	List<SongMasterDTO> showAllSongDetails() throws SongException;
	}
