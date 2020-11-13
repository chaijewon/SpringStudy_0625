package com.sist.dao;
/*
 *  MNO          NUMBER(3)      
	TITLE        VARCHAR2(300)  
	SINGER       VARCHAR2(100)  
	ALBUM        VARCHAR2(200)  
	POSTER       VARCHAR2(1000) 
	STATE        CHAR(6)        
	IDCREMENT    NUMBER(3)      
	KEY          VARCHAR2(50)
	              DispatcherServlet => HandlerMapping
	                                      | => RequestMapping("list.do")
	========> 요청 ==================> MusicController <=======> DAO
	                                  List,VO
	                                    | => Model
	                                      => List,VO, JSON(ajax,react,vue) => Front
	                                                   {} []
	                                                   VO List
	                                  ViewResolver ===> Model을 request로 변환 ==> JSP
	       list.do
 */
public class MusicVO {
    private int mno;
    private String title;
    private String singer;
    private String poster;
    private String album;
    private String state;
    private int idcrement;
    private String key;
    private int hit;
    
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getIdcrement() {
		return idcrement;
	}
	public void setIdcrement(int idcrement) {
		this.idcrement = idcrement;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	   
}





