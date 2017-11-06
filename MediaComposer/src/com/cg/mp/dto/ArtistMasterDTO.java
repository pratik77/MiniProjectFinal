package com.cg.mp.dto;

import java.sql.Date;

public class ArtistMasterDTO {
	private int artistId;
	private String artistName;
	private String artistType;
	private Date artistBornDate;
	private Date artistDiedDate;
	private int createdBy;
	private Date createdOn;
	private int updatedBy;
	private Date updatedOn;
	private int artistDelFlag;
	public void disp()
	{
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","Artist Id","Artist Name","Artist Type","Born Date","Died Date","createdBy","Created On","UpdatedBy","Updated On","Del Flag");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	@Override
	public String toString() {
		return String.format("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s", artistId, artistName, artistType,artistBornDate,artistDiedDate,createdBy,createdOn,updatedBy,updatedOn,artistDelFlag);
	}
	public int getArtistId() {
		return artistId;
	}
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getArtistType() {
		return artistType;
	}
	public void setArtistType(String artistType) {
		this.artistType = artistType;
	}
	public Date getArtistBornDate() {
		return artistBornDate;
	}
	public void setArtistBornDate(Date artistBornDate) {
		this.artistBornDate = artistBornDate;
	}
	public Date getArtistDiedDate() {
		return artistDiedDate;
	}
	public void setArtistDiedDate(Date artistDiedDate) {
		this.artistDiedDate = artistDiedDate;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public int getArtistDelFlag() {
		return artistDelFlag;
	}
	public void setArtistDelFlag(int artistDelFlag) {
		this.artistDelFlag = artistDelFlag;
	}
	
	public ArtistMasterDTO() {
		super();
	}
	public ArtistMasterDTO(int artistId, String artistName, String artistType,
			Date artistBornDate, Date artistDiedDate, int createdBy,
			Date createdOn, int updatedBy, Date updatedOn, int artistDelFlag) {
		super();
		this.artistId = artistId;
		this.artistName = artistName;
		this.artistType = artistType;
		this.artistBornDate = artistBornDate;
		this.artistDiedDate = artistDiedDate;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.artistDelFlag = artistDelFlag;
	}
	/*@Override
	public String toString() {
		return "Artist Details of "+artistId+" [ artistName="
				+ artistName + ", artistType=" + artistType
				+ ", artistBornDate=" + artistBornDate + ", artistDiedDate="
				+ artistDiedDate + ", createdBy=" + createdBy + ", createdOn="
				+ createdOn + ", updatedBy=" + updatedBy + ", updatedOn="
				+ updatedOn +"]";
	}*/

}
