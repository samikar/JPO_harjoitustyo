package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the tag database table.
 * 
 */
@Entity @Table(name = "tag")
@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TAG_TAGID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAG_TAGID_GENERATOR")
	@Column(name="tag_id")
	private int tagId;

	private String name;

	public Tag() {
	}

	public int getTagId() {
		return this.tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}