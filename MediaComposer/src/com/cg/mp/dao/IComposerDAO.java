package com.cg.mp.dao;

import java.util.List;

import com.cg.mp.dto.ComposerMasterDTO;
import com.cg.mp.dto.SongMasterDTO;
import com.cg.mp.dto.UserMasterDTO;
import com.cg.mp.exception.SongException;

public interface IComposerDAO {

	String checkLogin(int userId, String password)throws SongException;

	int addComposer(ComposerMasterDTO composerMasterDTO, int userId) throws SongException;

	ComposerMasterDTO getComposerById(int composerId) throws SongException;

	void editComposerDetails(ComposerMasterDTO composerMasterDTOEdit, int choice6, int userId)throws SongException;

	void deleteComposerDetails(int composerId)throws SongException;

	List<ComposerMasterDTO> showAllComposerDetails()throws SongException;

	int addUser(UserMasterDTO userMasterDTO, int userId)throws SongException;

	List<SongMasterDTO> getSongsByName(String composerName)throws SongException;

	List<SongMasterDTO> getSongsBySocId(String composerMusSocId) throws SongException;

}
