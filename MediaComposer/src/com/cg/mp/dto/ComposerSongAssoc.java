package com.cg.mp.dto;

import java.sql.Date;

public class ComposerSongAssoc {
	
	private int composerId;
	private int songId;
	private int createdBy;
	private Date createdOn;
	private int updatedBy;
	private Date updatedOn;
	public int getComposerId() {
		return composerId;
	}
	public void setComposerId(int composerId) {
		this.composerId = composerId;
	}
	public int getSongId() {
		return songId;
	}
	public void setSongId(int songId) {
		this.songId = songId;
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

}
