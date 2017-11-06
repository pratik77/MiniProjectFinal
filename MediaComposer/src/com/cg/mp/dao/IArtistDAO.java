package com.cg.mp.dao;

import java.util.List;

import com.cg.mp.dto.ArtistMasterDTO;
import com.cg.mp.dto.SongMasterDTO;
import com.cg.mp.exception.SongException;

public interface IArtistDAO {
	List<ArtistMasterDTO> searchArtist(int artistId) throws SongException;

	int getArtistId() throws SongException;

	int addNewArtist(ArtistMasterDTO artistMaster) throws SongException;

	void editArtistDetails(ArtistMasterDTO artistMasterDTOEdit, int choiceArtist,int userId) throws SongException;

	int deleteArtistDetails(int artistId) throws SongException;

	List<ArtistMasterDTO> retrieveAllArtists() throws SongException;

	List<ArtistMasterDTO> searchArtistByName(String artistName) throws SongException;

	List<ArtistMasterDTO> searchArtistByType(String artistType) throws SongException;

	List<SongMasterDTO> getSongsByName(String searchartistName) throws SongException; 


}
