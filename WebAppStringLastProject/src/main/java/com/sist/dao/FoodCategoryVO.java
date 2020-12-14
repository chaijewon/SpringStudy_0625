package com.sist.dao;
/*
 *   NO      NOT NULL NUMBER         
	TITLE   NOT NULL VARCHAR2(500)  
	POSTER  NOT NULL VARCHAR2(1000) 
	SUBJECT NOT NULL VARCHAR2(500)  
	LINK             VARCHAR2(100)  

 */
// 데이터를 모아서 전송할 목적 (DTO,Bean)
// 코틀린 : data class FoodCategoryVO(var no:Int,
// var title:String,var poster:String,var subject:String)
/*
 *    JavaScript : React , Vue
 *    => this.state={
 *          no:0,
 *          title:'',
 *          poster:'',
 *          subject:''
 *       }
 *       
 *       ====================================
 *       => Spring + Kotlin => Food
 *       => NodeJS + Kotlin + Redux => Recipe
 *       ====================================
 *       Spark(실시간 분석),챗봇 
 *       ====================================
 *       수료 : 클라우드 (주소=>다운로드) 
 *             : 자바 , 오라클 , JSP ,스프링 , 코틀린 , React,Redux
 *             =============================================
 */
public class FoodCategoryVO {
	private int no;
	private String title;
	private String poster;
	private String subject;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
