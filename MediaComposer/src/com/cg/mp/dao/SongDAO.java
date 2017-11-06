package com.cg.mp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.mp.dto.ArtistMasterDTO;
import com.cg.mp.dto.ArtistSongAssoc;
import com.cg.mp.dto.ComposerMasterDTO;
import com.cg.mp.dto.ComposerSongAssoc;
import com.cg.mp.dto.SongMasterDTO;
import com.cg.mp.exception.SongException;
import com.cg.mp.utility.DBUtil;

public class SongDAO implements ISongDAO {
	private static Logger logger=Logger.getLogger(com.cg.mp.dao.SongDAO.class);
	@Override
	public int addSong(SongMasterDTO songMasterDTO, int userId)
			throws SongException {
		Connection connection=null;
		PreparedStatement pst=null;	
		Statement stmt=null;
		String sql=new String("INSERT INTO song_master VALUES (song_master_seq.nextval,?,?,?,SYSDATE,?,SYSDATE,1)");
		String sql1=new String("SELECT song_master_seq.currval FROM DUAL");
		int status=0;
		try
		{
			connection=DBUtil.createConnection();
			pst = connection.prepareStatement (sql);
			pst.setString(1,songMasterDTO.getSongName());
			pst.setString(2,songMasterDTO.getSongDuration());
			pst.setInt(3,userId);
			pst.setInt(4,userId);
			pst.executeUpdate();
			stmt = connection.createStatement();
			ResultSet rset = stmt.executeQuery (sql1);
			if(rset.next())
			{
				status=rset.getInt(1);
			}
			logger.info("Song Added Successfully with Song Id"+status);

		}catch(SQLException se)
		{
			throw new SongException(se.getMessage()+"Problem in adding song details.",se);
		}finally
		{
			try
			{
				DBUtil.closeConnection();
			}catch(SQLException se)
			{
				throw new SongException("Problems in closing connection.",se);
			}
		}
		return status;

	}

	@Override
	public int checkComposerId(int composerId) throws SongException {
		Connection connection=null;
		Statement stmt=null;		
		String sql=new String("SELECT composer_id FROM composer_master where composer_id="+composerId);
		ComposerMasterDTO composerMasterDTO=new ComposerMasterDTO();

		try
		{
			connection=DBUtil.createConnection();
			stmt = connection.createStatement();
			ResultSet rset = stmt.executeQuery (sql);
			int cnt=0;
			while(rset.next())
			{
				composerMasterDTO.setComposerId(composerId);
				cnt++;
			}
			logger.info("Composer Id exists.");
			return cnt;

		}catch(SQLException se)
		{
			throw new SongException(se.getMessage()+"Composer id DNE");
		}finally
		{
			try
			{
				DBUtil.closeConnection();
			}catch(SQLException se)
			{
				throw new SongException("Problems in closing connection.",se);
			}
		}

	}

	@Override
	public int checkArtistId(int artistId) throws SongException {
		Connection connection=null;
		Statement stmt=null;		
		String sql=new String("SELECT artist_id FROM artist_master where artist_id="+artistId);
		ArtistMasterDTO artistMasterDTO=new ArtistMasterDTO();

		try
		{
			connection=DBUtil.createConnection();
			stmt = connection.createStatement();
			ResultSet rset = stmt.executeQuery (sql);
			int cnt=0;
			while(rset.next())
			{
				artistMasterDTO.setArtistId(artistId);
				cnt++;
			}
			logger.info("Artist Id exists.");
			return cnt;
		}catch(SQLException se)
		{
			throw new SongException(se.getMessage()+"Artist id DNE");
		}finally
		{
			try
			{
				DBUtil.closeConnection();
			}catch(SQLException se)
			{
				throw new SongException("Problems in closing connection.",se);
			}
		}
	}

	@Override
	public int checkSongId(int songId) throws SongException {
		Connection connection=null;
		Statement stmt=null;

		String sql=new String("SELECT song_id FROM song_master where song_id="+songId);
		SongMasterDTO songMasterDTO = new SongMasterDTO();

		try
		{
			connection=DBUtil.createConnection();
			stmt = connection.createStatement();
			ResultSet rset = stmt.executeQuery (sql);
			int cnt=0;
			while(rset.next())
			{
				songMasterDTO.setSongId(songId);
				cnt++;
			}
			logger.info("Song Id exists.");
			return cnt;
		}catch(SQLException se)
		{
			throw new SongException(se.getMessage()+"Song id DNE");
		}finally
		{
			try
			{
				DBUtil.closeConnection();
			}catch(SQLException se)
			{
				throw new SongException("Problems in closing connection.",se);
			}
		}

	}

	@Override
	public String compSongAssoc(ComposerSongAssoc composerSongAssoc, int userId)
			throws SongException {

		Connection connection=null;
		PreparedStatement pst=null;		
		String sql=new String("INSERT INTO composer_song_assoc VALUES (?,?,?,SYSDATE,?,SYSDATE)");

		try
		{
			connection=DBUtil.createConnection();
			pst = connection.prepareStatement (sql);
			pst.setInt(1,composerSongAssoc.getComposerId());
			pst.setInt(2,composerSongAssoc.getSongId());
			pst.setInt(3,userId);
			pst.setInt(4,userId);

			int status=0;
			status=pst.executeUpdate();
			logger.info("Composer with Id "+composerSongAssoc.getComposerId() +"asscociated to Song with ID "+composerSongAssoc.getSongId());
		}catch(SQLException se)
		{
			throw new SongException(se.getMessage()+"Problem in associating composer song details.",se);
		}finally
		{
			try
			{
				DBUtil.closeConnection();
			}catch(SQLException se)
			{
				throw new SongException("Problems in closing connection.",se);
			}
		}
		return "Composer and Song associated successfully";


	}

	@Override
	public String artistSongAssoc(ArtistSongAssoc artistSongAssoc, int userId)
			throws SongException {

		Connection connection=null;
		PreparedStatement pst=null;		
		String sql=new String("INSERT INTO artist_song_assoc VALUES (?,?,?,SYSDATE,?,SYSDATE)");

		try
		{
			connection=DBUtil.createConnection();
			pst = connection.prepareStatement (sql);
			pst.setInt(1,artistSongAssoc.getArtistId());
			pst.setInt(2,artistSongAssoc.getSongId());
			pst.setInt(3,userId);
			pst.setInt(4,userId);

			int status=0;
			status=pst.executeUpdate();
			logger.info("Artist with Id "+artistSongAssoc.getArtistId() +"asscociated to Song with ID "+artistSongAssoc.getSongId());
		}catch(SQLException se)
		{
			throw new SongException(se.getMessage()+"Problem in associating artist song details.",se);
		}finally
		{
			try
			{
				DBUtil.closeConnection();
			}catch(SQLException se)
			{
				throw new SongException("Problems in closing connection.",se);
			}
		}
		return "Artist and Song associated successfully";

	}

	@Override
	public List<SongMasterDTO> showAllSongDetails() throws SongException 
	{
		Connection connection=null;
		Statement stmt=null;
		List<SongMasterDTO> songList=new ArrayList();
		try
		{
			connection=DBUtil.createConnection();
			stmt = connection.createStatement();
			String sqlRetrieve=new String("SELECT * FROM song_master");
			ResultSet rsetList=stmt.executeQuery(sqlRetrieve);
			while(rsetList.next())
			{
				SongMasterDTO songMasterDTO=new SongMasterDTO();
				songMasterDTO.setSongName(rsetList.getString(2));
				songMasterDTO.setSongDuration(rsetList.getString(3));
				songMasterDTO.setCreatedBy(rsetList.getInt(4));
				songMasterDTO.setCreatedOn(rsetList.getDate(5));
				songMasterDTO.setUpdatedBy(rsetList.getInt(6));
				songMasterDTO.setUpdatedOn(rsetList.getDate(7));
				songList.add(songMasterDTO);
			}
			logger.info("Song details retrieved Successfully.");
		}catch(SQLException se)
		{
			throw new SongException(se.getMessage()+" and problem in retrieving Song list.");
		}finally
		{
			try
			{
				DBUtil.closeConnection();
			}catch(SQLException se)
			{
				throw new SongException("Problems in closing connection.",se);
			}
		}
		return songList;
	}
}
