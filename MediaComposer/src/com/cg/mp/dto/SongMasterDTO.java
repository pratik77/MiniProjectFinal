package com.cg.mp.dto;

import java.sql.Date;

public class SongMasterDTO 
{

	private int songId;
	private String songName;
	private String songDuration;
	private int createdBy;
	private Date createdOn;
	private int updatedBy;
	private Date updatedOn;
	private int songDelFlag;


	public SongMasterDTO() {
		super();
	}
	public SongMasterDTO(int songId, String songName, String songDuration,
			int createdBy, Date createdOn, int updatedBy, Date updatedOn,
			int songDelFlag) {
		super();
		this.songId = songId;
		this.songName = songName;
		this.songDuration = songDuration;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.songDelFlag = songDelFlag;
	}
	public int getSongId() {
		return songId;
	}
	public void setSongId(int songId) {
		this.songId = songId;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getSongDuration() {
		return songDuration;
	}
	public void setSongDuration(String songDuration) {
		this.songDuration = songDuration;
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
	public int getSongDelFlag() {
		return songDelFlag;
	}
	public void setSongDelFlag(int songDelFlag) {
		this.songDelFlag = songDelFlag;
	}
	public String displaySongsDetails() {
		// TODO Auto-generated method stub
		return String.format("%-30s%-30s%-30s\n", songId, songName, songDuration);
	}

	public void disp()
	{
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","Song ID","Song Name","Song Duration","Created By","Created On","Updated By","Updated On");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	@Override
	public String toString() {

		return String.format("%-30s%-30s%-30s%-30s%-30s%-30s%-30s", songId, songName, songDuration,createdBy,createdOn,updatedBy,updatedOn);

	}

}
