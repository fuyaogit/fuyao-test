/**  
 * @Project: linktrust-management
 * @Title: AnnunciateUtil.java
 * @Package: cn.com.linktrust.assess.util
 * @Description: TODO
 * @author: GUO WEI 
 * @since: 2017年3月31日 上午11:01:53
 * @Copyright: 2017. All rights reserved.
 * @Version: v1.0   
 */
package com.linktrust.fuyao.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**  
 * @ClassName AnnunciateUtil
 * @Description: TODO  
 * @author: GUO WEI  
 * @since: 2017年3月31日 上午11:01:53 
 */
public class AnnunciateUtil extends Thread {
	private String type;
	private String title;
	private String content;
	private String pushUserId;
	private String targetType;
	private List<String> targetIds;
	/**  
	 * AnnunciateUtil.  
	 *    
	 */
	public AnnunciateUtil(String type,String title,String content,String pushUserId,String targetType,List<String> targetIds) {
		this.type = type;
		this.title = title;
		this.content = content;
		this.pushUserId = pushUserId;
		this.targetType = targetType;
		this.targetIds = targetIds;
	}
	
	@Override
	public void run() {
		for(int i = 0 ; i < this.targetIds.size() ; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("type", type);// 消息类型
			map.put("title", title);// 标题
			map.put("content", content);// 消息内容
			map.put("pushUserId", pushUserId);// 发布推送人<消息推送者用户ID>
			map.put("targetIds", this.targetIds.get(i));// 推送目标<接送人>targetId班级，学校或者用户ID用“,”分隔
			map.put("targetType", targetType);// 学生:0, 家长：1, 老师:2, 学生和家长:3, 学生、家长、老师：4,
										// 家长和老师：5
										// 0为教师 1为家长 2为学生
			map.put("target", "school");// 消息接收者类型，班级，学校，用户
			try {
				HttpClient.get(Constant._WEB_ADDR + "/linktrust-framework/getui/push", map);
				Thread.sleep(500);  
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPushUserId() {
		return pushUserId;
	}
	public void setPushUserId(String pushUserId) {
		this.pushUserId = pushUserId;
	}
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public List<String> getTargetIds() {
		return targetIds;
	}
	public void setTargetIds(List<String> targetIds) {
		this.targetIds = targetIds;
	}
}
