import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import com.cg.mp.dao.ArtistDAO;
import com.cg.mp.dao.ComposerDAO;
import com.cg.mp.dao.IArtistDAO;
import com.cg.mp.dao.IComposerDAO;
import com.cg.mp.dto.ArtistMasterDTO;
import com.cg.mp.dto.ComposerMasterDTO;
import com.cg.mp.exception.SongException;

public class SongTest {
	
	int idC=0;
	int idA=0;
	IComposerDAO composerDAO=new ComposerDAO();
	IArtistDAO artistDAO=new ArtistDAO();
	ComposerMasterDTO composerMasterDTO=new ComposerMasterDTO();
	ArtistMasterDTO artistMasterDTO=new ArtistMasterDTO();
	@Before
	public void setUp() throws Exception {
		composerMasterDTO.setComposerName("TestName");
		composerMasterDTO.setComposerBornDate(Date.valueOf("2017-02-01"));
		composerMasterDTO.setComposerCaeipiNumber("1234567");
		composerMasterDTO.setComposerMusicSocId("e34");
		idC=composerDAO.addComposer(composerMasterDTO, 100001);
		artistMasterDTO.setArtistName("Pratik");
		artistMasterDTO.setArtistBornDate(Date.valueOf("2017-02-01"));
		artistMasterDTO.setCreatedBy(100001);
		artistMasterDTO.setUpdatedBy(100001);
		artistMasterDTO.setArtistDelFlag(1);
		idA=artistDAO.addNewArtist(artistMasterDTO);
		
	}
	
	@Test
	public void loginAdminTest() {
		try {
			assertEquals("admin",composerDAO.checkLogin(100001, "zyrus"));
		} catch (SongException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void loginUserTest() {
		try {
			assertEquals("user",composerDAO.checkLogin(100006, "zyrus"));
		} catch (SongException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void insertComposerTest() {
		
		composerMasterDTO.setComposerName("TestName");
		composerMasterDTO.setComposerBornDate(Date.valueOf("2017-02-01"));
		composerMasterDTO.setComposerCaeipiNumber("1234567");
		composerMasterDTO.setComposerMusicSocId("e34");
		
		try {
			assertEquals(++idC,composerDAO.addComposer(composerMasterDTO, 100001));
		} catch (SongException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void insertArtistTest()
	{
		artistMasterDTO.setArtistName("Pratik");
		artistMasterDTO.setArtistBornDate(Date.valueOf("2017-02-01"));
		artistMasterDTO.setCreatedBy(100001);
		artistMasterDTO.setUpdatedBy(100001);
		artistMasterDTO.setArtistDelFlag(1);
		try {
			assertEquals(++idA,artistDAO.addNewArtist(artistMasterDTO));
		} catch (SongException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
