package com.cg.mp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.mp.dto.ComposerMasterDTO;
import com.cg.mp.dto.SongMasterDTO;
import com.cg.mp.dto.UserMasterDTO;
import com.cg.mp.exception.SongException;
import com.cg.mp.utility.DBUtil;

public class ComposerDAO implements IComposerDAO {
	private static Logger logger=Logger.getLogger(com.cg.mp.dao.ComposerDAO.class);

	Connection connection=null;
	Statement stmt=null;
	Statement stmt1=null;
	public ComposerDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String checkLogin(int userId, String password) throws SongException {
		// TODO Auto-generated method stub
		Connection connection=null;
		Statement pstStudent=null;
		String message="invalid";		
		String sql=new String("SELECT user_flag FROM User_Master where user_id="+userId+" AND user_password='"+password+"'");

		try
		{
			connection=DBUtil.createConnection();
			Statement stmt = connection.createStatement ();
			ResultSet rset = stmt.executeQuery (sql);
			rset.next();
			int checkFlag=rset.getInt(1);
			logger.info("Login successfull. "+rset.getInt(1));
			if(checkFlag==1)
				message="admin";
			else if(checkFlag==2)
				message="user";
			else message="invalid";
		}catch(SQLException se)
		{
			throw new SongException("Problem in checking login.");
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



		return message;
	}

	@Override
	public int addComposer(ComposerMasterDTO composerMasterDTO, int userId) throws SongException {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement pst=null;		
		String sql=new String("INSERT INTO composer_master VALUES (composer_master_seq.nextval,?,?,?,?,?,?,SYSDATE,?,SYSDATE,?)");
		String sqlComposerId=new String("SELECT composer_master_seq.currval from dual");
		int id=0;
		try
		{
			connection=DBUtil.createConnection();
			pst = connection.prepareStatement (sql);
			pst.setString(1,composerMasterDTO.getComposerName());
			pst.setDate(2,composerMasterDTO.getComposerBornDate());
			pst.setDate(3,composerMasterDTO.getComposerDiedDate());
			pst.setString(4,composerMasterDTO.getComposerCaeipiNumber());
			pst.setString(5,composerMasterDTO.getComposerMusicSocId());
			pst.setInt(6,userId);
			pst.setInt(7,userId);
			pst.setInt(8,composerMasterDTO.getComposerDelFlag());
			pst.executeUpdate();
			stmt=connection.createStatement ();
			ResultSet rs=stmt.executeQuery(sqlComposerId);
			rs.next();
			id=rs.getInt(1);
			logger.info("Composer added with Id: "+rs.getInt(1));
		}catch(SQLException se)
		{
			throw new SongException(se.getMessage()+" and Problem in inserting composer details.",se);
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
	public ComposerMasterDTO getComposerById(int composerId) throws SongException {
		// TODO Auto-generated method stub
		Connection connection=null;
		Statement stmt=null;		
		String sql=new String("SELECT * FROM composer_master where composer_id="+composerId);
		ComposerMasterDTO composerMasterDTO=new ComposerMasterDTO();

		try
		{
			connection=DBUtil.createConnection();
			stmt = connection.createStatement();
			ResultSet rset = stmt.executeQuery (sql);
			while(rset.next())
			{

				composerMasterDTO.setComposerId(composerId);
				composerMasterDTO.setComposerName(rset.getString(2));
				composerMasterDTO.setComposerBornDate(rset.getDate(3));
				composerMasterDTO.setComposerDiedDate(rset.getDate(4));
				composerMasterDTO.setComposerCaeipiNumber(rset.getString(5));
				composerMasterDTO.setComposerMusicSocId(rset.getString(6));
				composerMasterDTO.setCreatedBy(rset.getInt(7));
				composerMasterDTO.setCreatedOn(rset.getDate(8));
				composerMasterDTO.setUpdatedBy(rset.getInt(9));
				composerMasterDTO.setUpdatedOn(rset.getDate(10));
				composerMasterDTO.setComposerDelFlag(rset.getInt(11));
				logger.info("Composer List extracted Successfully for composer with ID: "+rset.getInt(1));
			}
		}catch(SQLException se)
		{
			throw new SongException(se.getMessage()+"Problem in checking login.");
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


		return composerMasterDTO;

	}

	@Override
	public void editComposerDetails(ComposerMasterDTO composerMasterDTOEdit,int choice6,int userId) throws SongException {
		// TODO Auto-generated method stub
		switch(choice6)
		{
		case 1:

			String date=new String("SELECT to_char(to_date('"+composerMasterDTOEdit.getComposerDiedDate()+"','yyyy-mm-dd'),'dd-mon-yyyy') FROM DUAL");

			try
			{
				connection=DBUtil.createConnection();
				stmt = connection.createStatement();
				stmt1 = connection.createStatement();
				ResultSet rset = stmt.executeQuery (date);
				rset.next();

				date=rset.getString(1);
				String sql=new String("UPDATE composer_master SET composer_dieddate='"+date
						+"',updated_on=sysdate,updated_by="+userId +" WHERE composer_id="+composerMasterDTOEdit.getComposerId());
				int check=stmt1.executeUpdate(sql);
				logger.info("Composer Died Date updated successfully.");
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

		case 2:
			try
			{
				connection=DBUtil.createConnection();
				stmt = connection.createStatement();
				stmt1 = connection.createStatement();
				String sql=new String("UPDATE composer_master SET composer_caeipinumber='"+composerMasterDTOEdit.getComposerCaeipiNumber()
				+"',updated_on=sysdate,updated_by="+userId +" WHERE composer_id="+composerMasterDTOEdit.getComposerId());
				int check=stmt1.executeUpdate(sql);
				logger.info("Composer CAEIPI Number updated successfully.");
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

		case 3:
			try
			{
				connection=DBUtil.createConnection();
				stmt = connection.createStatement();
				stmt1 = connection.createStatement();
				String sql=new String("UPDATE composer_master SET composer_caeipinumber='"+composerMasterDTOEdit.getComposerMusicSocId()
				+"',updated_on=sysdate,updated_by="+userId +" WHERE composer_id="+composerMasterDTOEdit.getComposerId());
				int check=stmt1.executeUpdate(sql);
				logger.info("Composer details updated Successfully.");
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


			/***************default case for main menu********************/
		default:
			System.out.println("Please enter a valid choice.");
			break;
		}

	}

	@Override
	public void deleteComposerDetails(int composerId) throws SongException {
		// TODO Auto-generated method stub
		try
		{
			connection=DBUtil.createConnection();
			stmt = connection.createStatement();
			stmt1 = connection.createStatement();
			String sql=new String("DELETE FROM composer_master WHERE composer_id="+composerId);
			int check=stmt1.executeUpdate(sql);
			if(check!=0)
			{
				System.out.println("Composer Details successfully deleted");
				logger.info("Composer details successfully deleted for composer ID: "+composerId);
			}
			else
				System.out.println("Composer details does not exist");
			logger.info("Composer details do not exist.");
		}
		catch(SQLException se)
		{
			throw new SongException(se.getMessage()+" and Problem in deletion.");
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
	public List<ComposerMasterDTO> showAllComposerDetails()
			throws SongException {
		// TODO Auto-generated method stub
		List<ComposerMasterDTO>composerList=new ArrayList();
		try
		{
			connection=DBUtil.createConnection();
			stmt = connection.createStatement();
			stmt1 = connection.createStatement();
			String sqlRetrieve=new String("SELECT * FROM composer_master");
			ResultSet rsetList=stmt1.executeQuery(sqlRetrieve);
			while(rsetList.next())
			{
				ComposerMasterDTO composerMasterDTO=new ComposerMasterDTO();
				composerMasterDTO.setComposerId(rsetList.getInt(1));
				composerMasterDTO.setComposerName(rsetList.getString(2));
				composerMasterDTO.setComposerBornDate(rsetList.getDate(3));
				composerMasterDTO.setComposerDiedDate(rsetList.getDate(4));
				composerMasterDTO.setComposerCaeipiNumber(rsetList.getString(5));
				composerMasterDTO.setComposerMusicSocId(rsetList.getString(6));
				composerMasterDTO.setCreatedBy(rsetList.getInt(7));
				composerMasterDTO.setCreatedOn(rsetList.getDate(8));
				composerMasterDTO.setUpdatedBy(rsetList.getInt(9));
				composerMasterDTO.setUpdatedOn(rsetList.getDate(10));
				composerMasterDTO.setComposerDelFlag(rsetList.getInt(11));
				composerList.add(composerMasterDTO);
			}
			logger.info("Composer details retrieved Successfully.");
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
		return composerList;

	}

	@Override
	public int addUser(UserMasterDTO userMasterDTO, int userId)
			throws SongException {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement pst=null;		
		String sql=new String("INSERT INTO user_master VALUES (user_master_seq.nextval,?,?,SYSDATE,?,SYSDATE,2)");
		String sqlComposerId=new String("SELECT user_master_seq.currval from dual");
		int id=0;
		try
		{
			connection=DBUtil.createConnection();
			pst = connection.prepareStatement (sql);
			pst.setString(1,userMasterDTO.getUserPassword());
			pst.setInt(2,userId);
			pst.setInt(3,userId);
			pst.executeUpdate();
			stmt=connection.createStatement ();
			ResultSet rs=stmt.executeQuery(sqlComposerId);
			rs.next();
			id=rs.getInt(1);
			logger.info("User added with Id: "+rs.getInt(1));
		}catch(SQLException se)
		{
			throw new SongException(se.getMessage()+" and Problem in inserting composer details.",se);
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
	public List<SongMasterDTO> getSongsByName(String composerName)
			throws SongException {
		// TODO Auto-generated method stub
		String lowerComposerName=composerName.toLowerCase();
		System.out.println(lowerComposerName);
		List<SongMasterDTO>songsList=new ArrayList();
		try
		{
			connection=DBUtil.createConnection();
			stmt = connection.createStatement();
			stmt1 = connection.createStatement();
			String sqlSongsList=new String("SELECT * FROM song_master WHERE song_id IN "
					+ "(SELECT song_id FROM composer_song_assoc WHERE composer_id IN"
					+ "(SELECT composer_id FROM composer_master WHERE LOWER(composer_name)='"+lowerComposerName+"'))");
			ResultSet rsetSong=stmt1.executeQuery(sqlSongsList);
			while(rsetSong.next())
			{
				SongMasterDTO songMasterDTO=new SongMasterDTO();
				songMasterDTO.setSongId(rsetSong.getInt(1));
				songMasterDTO.setSongName(rsetSong.getString(2));
				songMasterDTO.setSongDuration(rsetSong.getString(3));
				songsList.add(songMasterDTO);
			}
			logger.info("Song details retrieved successfully for Composer "+composerName);
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

	@Override
	public List<SongMasterDTO> getSongsBySocId(String composerMusSocId)
			throws SongException {
		// TODO Auto-generated method stub
		String lowerMusicSocId=composerMusSocId.toLowerCase();
		System.out.println(lowerMusicSocId);
		List<SongMasterDTO>songsList=new ArrayList();
		try
		{
			connection=DBUtil.createConnection();
			stmt = connection.createStatement();
			stmt1 = connection.createStatement();
			String sqlSongsList=new String("SELECT * FROM song_master WHERE song_id IN "
					+ "(SELECT song_id FROM composer_song_assoc WHERE composer_id IN"
					+ "(SELECT composer_id FROM composer_master WHERE composer_musicsocietyid='"+composerMusSocId+"'))");
			ResultSet rsetSong=stmt1.executeQuery(sqlSongsList);
			while(rsetSong.next())
			{
				SongMasterDTO songMasterDTO=new SongMasterDTO();
				songMasterDTO.setSongId(rsetSong.getInt(1));
				songMasterDTO.setSongName(rsetSong.getString(2));
				//songMasterDTO.setSongDuration(rsetSong.getTimestamp(3));
				songsList.add(songMasterDTO);
			}
			logger.info("Song details retrieved successfully for Composer Music Society ID: "+composerMusSocId);
		}catch(SQLException se)
		{
			throw new SongException(se.getMessage()+" and problem in retrieving songs list.");
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
