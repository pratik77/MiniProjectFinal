package com.cg.mp.dto;

import java.sql.Date;

public class ComposerMasterDTO 
{
	private int composerId;
	private String composerName;
	private Date composerBornDate;
	private Date composerDiedDate;
	private String composerCaeipiNumber;
	private String composerMusicSocId;
	private int createdBy;
	private Date createdOn;
	private int updatedBy;
	private Date updatedOn;
	private int composerDelFlag;
	public void disp()
	{
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ComposerId","Composer Name","Born Date","Died Date","Caeipi No.","MusicSoc ID","createdBy","Created On","UpdatedBy","Updated On","Del Flag");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	@Override
	public String toString() {
		return String.format("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s", composerId, composerName, composerBornDate,composerDiedDate,composerCaeipiNumber,composerMusicSocId,createdBy,createdOn,updatedBy,updatedOn,composerDelFlag);
	}
	public int getComposerId() {
		return composerId;
	}
	public void setComposerId(int composerId) {
		this.composerId = composerId;
	}
	public String getComposerName() {
		return composerName;
	}
	public void setComposerName(String composerName) {
		this.composerName = composerName;
	}
	public Date getComposerBornDate() {
		return composerBornDate;
	}
	public void setComposerBornDate(Date composerBornDate) {
		this.composerBornDate = composerBornDate;
	}
	public Date getComposerDiedDate() {
		return composerDiedDate;
	}
	public void setComposerDiedDate(Date composerDiedDate) {
		this.composerDiedDate = composerDiedDate;
	}
	public String getComposerCaeipiNumber() {
		return composerCaeipiNumber;
	}
	public void setComposerCaeipiNumber(String composerCaeipiNumber) {
		this.composerCaeipiNumber = composerCaeipiNumber;
	}
	public String getComposerMusicSocId() {
		return composerMusicSocId;
	}
	public void setComposerMusicSocId(String composerMusicSocId) {
		this.composerMusicSocId = composerMusicSocId;
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
	public int getComposerDelFlag() {
		return composerDelFlag;
	}
	public void setComposerDelFlag(int composerDelFlag) {
		this.composerDelFlag = composerDelFlag;
	}
	
}
