package com.cg.mp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.mp.dto.ArtistMasterDTO;
import com.cg.mp.dto.SongMasterDTO;
import com.cg.mp.exception.SongException;
import com.cg.mp.utility.DBUtil;

public class ArtistDAO implements IArtistDAO{

	private static Logger logger=Logger.getLogger(com.cg.mp.dao.ArtistDAO.class);
	Connection connection=null;
	Statement stmt=null;
	ResultSet rset=null;
	@Override
	public List<ArtistMasterDTO> searchArtist(int artistId) throws SongException {
		List<ArtistMasterDTO> artistList=new ArrayList<ArtistMasterDTO>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		int count=0;
		String sql=new String("SELECT * FROM Artist_Master where Artist_Id="+artistId);
		try
		{
		conn=DBUtil.createConnection();
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery (sql);
		while(rset.next())
		{
			ArtistMasterDTO artistMasterDTO=new ArtistMasterDTO();
			artistMasterDTO.setArtistId(rset.getInt(1));
			artistMasterDTO.setArtistName(rset.getString(2));
			artistMasterDTO.setArtistType(rset.getString(3));
			artistMasterDTO.setArtistBornDate(rset.getDate(4));
			artistMasterDTO.setArtistDiedDate(rset.getDate(5));
			artistMasterDTO.setCreatedBy(rset.getInt(6));
			artistMasterDTO.setCreatedOn(rset.getDate(7));
			artistMasterDTO.setUpdatedBy(rset.getInt(8));
			artistMasterDTO.setUpdatedOn(rset.getDate(9));
			artistMasterDTO.setArtistDelFlag(rset.getInt(10));
			artistList.add(artistMasterDTO);
		}
		logger.info("Artist Search operation successfull.");
		}catch(SQLException se)
		{
			throw new SongException(se.getMessage()+"Please enter correct credentials.");
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
		return artistList;
	}

	@Override
	public int getArtistId() throws SongException
	{
		
		Connection conn=null;
		PreparedStatement pstStudent=null;
		int artistId;
		
		String sql=new String("SELECT Artist_Master_Seq.nextval FROM Dual");
		try
		{
		conn=DBUtil.createConnection();
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery (sql);
		rset.next();
		artistId=rset.getInt(1);
		logger.info("Artist details retrieved successfully with Id: "+rset.getInt(1));
		}catch(SQLException se)
		{
			throw new SongException("Problem in generating artistId.");
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
		return artistId;
	}

	@Override
	public int addNewArtist(ArtistMasterDTO artistMaster) throws SongException {
		int status=0;
		Connection connStudent=null;
		PreparedStatement pstStudent=null;
		String sql=new String("INSERT INTO Artist_Master VALUES(artist_master_seq.nextval,?,?,?,?,?,sysdate,?,sysdate,1)");
		String sqlArtistId=new String("SELECT artist_master_seq.currval from dual");
		int id=0;
		try
		{
		connStudent=DBUtil.createConnection();
		
		pstStudent=connStudent.prepareStatement(sql);
		pstStudent.setString(1,artistMaster.getArtistName());
		pstStudent.setString(2,artistMaster.getArtistType());
		pstStudent.setDate(3,artistMaster.getArtistBornDate());
		pstStudent.setDate(4,artistMaster.getArtistDiedDate());	
		pstStudent.setInt(5,artistMaster.getCreatedBy());
		pstStudent.setInt(6,artistMaster.getUpdatedBy());
		status=pstStudent.executeUpdate();
		stmt=connStudent.createStatement();
		rset=stmt.executeQuery(sqlArtistId);
		rset.next();
		id=rset.getInt(1);
		logger.info("Artist Added successfully with ID: "+rset.getInt(1));
		}catch(SQLException se)
		{
			throw new SongException(se.getMessage()+"Problem in adding artist details.");
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
		return id;
	}

	@Override
	public void editArtistDetails(ArtistMasterDTO artistMasterDTOEdit,
			int choiceArtist,int userId) throws SongException {
		switch(choiceArtist)
		{
		case 1:
			Connection connection=null;
			Statement stmt=null;
			Statement stmt1=null;
			Statement stmt2=null;
			LocalDate locDate=LocalDate.now();
			Date pdate=Date.valueOf(locDate);
			String date=new String("SELECT to_char(to_date('"+artistMasterDTOEdit.getArtistDiedDate()+"','yyyy-mm-dd'),'dd-mon-yyyy') FROM DUAL");
			
			try
			{
			connection=DBUtil.createConnection();
			stmt = connection.createStatement();
			stmt1 = connection.createStatement();
			stmt2 = connection.createStatement();
			ResultSet rset = stmt.executeQuery (date);
			rset.next();
			
			date=rset.getString(1);
			System.out.println(date);
			String sql=new String("UPDATE artist_master SET artist_dieddate='"+date
			+"' WHERE artist_id="+artistMasterDTOEdit.getArtistId());
			String sql2=new String("UPDATE artist_master SET updated_on=sysdate,updated_by="+userId+" where artist_id="+artistMasterDTOEdit.getArtistId());
			int check=stmt1.executeUpdate(sql);
			int check2=stmt2.executeUpdate(sql2);
			logger.info("Artist details successfully edited.");
			}catch(SQLException se)
			{
				throw new SongException(se.getMessage()+" and Problem in updation.");
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
			break;
		}
	}

	@Override
	public int deleteArtistDetails(int artistId) throws SongException {
		int status=0;
		Connection conn=null;
		PreparedStatement pst=null;
		try 
	     {  
			
			conn=DBUtil.createConnection();
	        pst = conn.prepareStatement("DELETE FROM artist_master WHERE artist_id="+artistId);
	        status=pst.executeUpdate();
	        logger.info("Artist details successfully deleted.");
	     }
	     catch(Exception se)
	     {
	         System.out.println(se.getMessage()+"No deletion done" );
	     }
		return status;
	}

	@Override
	public List<ArtistMasterDTO> retrieveAllArtists() throws SongException {
		
		List<ArtistMasterDTO> artistList=new ArrayList<ArtistMasterDTO>();
		Connection conn=null;
		PreparedStatement pst=null;
		
		String sql=new String("SELECT * FROM artist_master");
		try
		{
		conn=DBUtil.createConnection();
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery (sql);
		while(rset.next())
		{
			ArtistMasterDTO artist=new ArtistMasterDTO();
			artist.setArtistId(rset.getInt(1));
			artist.setArtistName(rset.getString(2));
			artist.setArtistType(rset.getString(3));
			artist.setArtistBornDate(rset.getDate(4));
			artist.setArtistDiedDate(rset.getDate(5));
			artist.setCreatedBy(rset.getInt(6));
			artist.setCreatedOn(rset.getDate(7));
			artist.setUpdatedBy(rset.getInt(8));
			artist.setUpdatedOn(rset.getDate(9));
			artist.setArtistDelFlag(rset.getInt(10));
			
			artistList.add(artist);
			
		}
		logger.info("Artist details successfully retrieved.");
		}catch(SQLException se)
		{
			throw new SongException("Problem in showing artist details.");
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
		
		
		return artistList;
	}

	@Override
	public List<ArtistMasterDTO> searchArtistByName(String artistName) throws SongException {
		List<ArtistMasterDTO> artistList=new ArrayList<ArtistMasterDTO>();
		Connection conn=null;
		String sql=new String("SELECT * FROM Artist_Master where Artist_Name='"+artistName+"'");
		try
		{
		conn=DBUtil.createConnection();
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(sql);
		ArtistMasterDTO artistMasterDTO=new ArtistMasterDTO();
		while(rset.next())
		{
			artistMasterDTO.setArtistId(rset.getInt(1));
			artistMasterDTO.setArtistName(rset.getString(2));
			artistMasterDTO.setArtistType(rset.getString(3));
			artistMasterDTO.setArtistBornDate(rset.getDate(4));
			artistMasterDTO.setArtistDiedDate(rset.getDate(5));
			artistMasterDTO.setCreatedBy(rset.getInt(6));
			artistMasterDTO.setCreatedOn(rset.getDate(7));
			artistMasterDTO.setUpdatedBy(rset.getInt(8));
			artistMasterDTO.setUpdatedOn(rset.getDate(9));
			artistMasterDTO.setArtistDelFlag(rset.getInt(10));
			artistList.add(artistMasterDTO);
			
		
		}
		logger.info("Artist details successfully retrieved for Artist Name."+rset.getString(2));
		}catch(SQLException se)
		{
			throw new SongException(se.getMessage()+"Please enter correct credentials.");
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
		return artistList;
}

	@Override
	public List<ArtistMasterDTO> searchArtistByType(String artistType)
			throws SongException 
			{
		List<ArtistMasterDTO> artistList=new ArrayList<ArtistMasterDTO>();
		Connection conn=null;
		String sql=new String("SELECT * FROM Artist_Master where Artist_Type='"+artistType+"'");
		try
		{
		conn=DBUtil.createConnection();
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(sql);
		ArtistMasterDTO artistMasterDTO=new ArtistMasterDTO();
		while(rset.next())
		{
			artistMasterDTO.setArtistId(rset.getInt(1));
			artistMasterDTO.setArtistName(rset.getString(2));
			artistMasterDTO.setArtistType(rset.getString(3));
			artistMasterDTO.setArtistBornDate(rset.getDate(4));
			artistMasterDTO.setArtistDiedDate(rset.getDate(5));
			artistMasterDTO.setCreatedBy(rset.getInt(6));
			artistMasterDTO.setCreatedOn(rset.getDate(7));
			artistMasterDTO.setUpdatedBy(rset.getInt(8));
			artistMasterDTO.setUpdatedOn(rset.getDate(9));
			artistMasterDTO.setArtistDelFlag(rset.getInt(10));
			artistList.add(artistMasterDTO);
			
		
		}
		logger.info("Artist details successfully retrieved for Artist Type. "+artistType);
		}catch(SQLException se)
		{
			throw new SongException(se.getMessage()+"Please enter correct credentials.");
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
		return artistList;
	}

	@Override
	public List<SongMasterDTO> getSongsByName(String searchartistName)
			throws SongException {

		Connection connection=null;
		Statement stmt=null;
		Statement stmt1=null;
		String lowerArtistName=searchartistName.toLowerCase();
		System.out.println(lowerArtistName);
		List<SongMasterDTO>songsList=new ArrayList();
		try
		{
		connection=DBUtil.createConnection();
		stmt = connection.createStatement();
		stmt1 = connection.createStatement();
		String sqlSongsList=new String("SELECT * FROM song_master WHERE song_id IN "
				+ "(SELECT song_id FROM artist_song_assoc WHERE artist_id IN"
				+ "(SELECT artist_id FROM artist_master WHERE LOWER(artist_name)='"+lowerArtistName+"'))");
		ResultSet rsetSong=stmt1.executeQuery(sqlSongsList);
		while(rsetSong.next())
		{
			SongMasterDTO songMasterDTO=new SongMasterDTO();
			songMasterDTO.setSongId(rsetSong.getInt(1));
			songMasterDTO.setSongName(rsetSong.getString(2));
			songMasterDTO.setSongDuration(rsetSong.getString(3));
			//songMasterDTO.setSongDuration(rsetSong.getTimestamp(3));
			songsList.add(songMasterDTO);
		}
		logger.info("Song Details successfully retrieved for Artist Name "+searchartistName);
		}catch(SQLException se)
		{
			throw new SongException(se.getMessage()+" and problem in retrieving composer list.");
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
		return songsList;
}
}
