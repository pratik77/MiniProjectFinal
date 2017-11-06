package com.cg.mp.dao;

import com.cg.mp.dto.ArtistSongAssoc;
import com.cg.mp.dto.ComposerSongAssoc;
import com.cg.mp.dto.SongMasterDTO;
import com.cg.mp.exception.SongException;

public interface ISongDAO
{
	int addSong(SongMasterDTO songMasterDTO, int userId) throws SongException;

	int checkComposerId(int composerId) throws SongException;

	int checkArtistId(int artistId) throws SongException;

	int checkSongId(int songId) throws SongException;

	String compSongAssoc(ComposerSongAssoc composerSongAssoc, int userId) throws SongException;

	String artistSongAssoc(ArtistSongAssoc artistSongAssoc, int userId) throws SongException;

}
