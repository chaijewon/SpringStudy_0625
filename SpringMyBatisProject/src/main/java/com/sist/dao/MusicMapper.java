package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import java.util.*;
public interface MusicMapper {
   /*
    *   private int mno;
	    private String title;
	    private String singer;
	    private String poster;
	    private String album;
	    private String state;
	    private int idcrement;
	    private String key;
    */
   @Select("SELECT mno,title,singer,poster,album,state,idcrement,key,num "
		  +"FROM (SELECT mno,title,singer,poster,album,state,idcrement,key,rownum as num "
		  +"FROM (SELECT mno,title,singer,poster,album,state,idcrement,key "
		  +"FROM genie_music ORDER BY mno ASC ))"
		  +"WHERE num BETWEEN #{start} AND #{end}")   
   public List<MusicVO> musicListData(Map map);
   @Select("SELECT * FROM genie_music "
		  +"WHERE mno=#{mno}")
   public MusicVO musicDetailData(int mno);
}




