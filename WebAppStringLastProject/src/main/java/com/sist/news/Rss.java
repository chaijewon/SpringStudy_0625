package com.sist.news;

import javax.xml.bind.annotation.XmlRootElement;

/*
 *   <rss> ===> Class
 *     <channel> ==> Class
 *       <item>  ==> Item
 *        =============================
 *         <title>aaa</title>
 *         <description></description>
 *         <link></link>
 *         <author></author>
 *         ============================변수설정 
 *       </item>
 *     </channel>
 *   </rss>
 */
@XmlRootElement
public class Rss {
   private Channel channel=new Channel();

	public Channel getChannel() {
		return channel;
	}
	
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
   
}
