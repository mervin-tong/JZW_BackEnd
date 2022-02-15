package com.piesat.school.emuerlation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.smartwork.api.enums.IEnum;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public interface BizEnumType {
	/**
	 * 公开状态
	 */
	enum PublicStatus  {
		FullOpen(1, "完全公开"),
		HalfOpen(2, "申请获取"),
		NoOpen(3,"保护期内"),
		;
		static Map<Integer, PublicStatus> allTypes;
		private int key;
		private String name;

		PublicStatus(Integer key, String name) {
			this.key = key;
			this.name = name;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public static boolean isOpen(int key){
			if(FullOpen.getKey() == key) return true;
			return false;
		}
		public static PublicStatus fromKey(Integer key) {
			if (key == null) return null;
			return allTypes.get(key);
		}

		static {
			allTypes = new HashMap<>();
			PublicStatus[] types = values();
			for (PublicStatus type : types) {
				allTypes.put(type.getKey(), type);
			}
		}

	}

	/**
	 * 通用状态
	 */
	enum CommonStatus {
		Invalid(0, "无效的"),
		Valid(1, "有效的"),
		;
		static Map<Integer, CommonStatus> allTypes;
		private int key;
		private String name;

		CommonStatus(Integer key, String name) {
			this.key = key;
			this.name = name;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public static boolean isValid(int key){
			if(Valid.getKey() == key) return true;
			return false;
		}

		public static CommonStatus fromKey(Integer key) {
			if (key == null) return null;
			return allTypes.get(key);
		}

		static {
			allTypes = new HashMap<>();
			CommonStatus[] types = values();
			for (CommonStatus type : types) {
				allTypes.put(type.getKey(), type);
			}
		}
	}

	/**
	 * 通用发布状态
	 */
	//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	enum CommonPublishStatus implements IEnum<Integer> {
		BanPublished(-1, "已下架"),
		Draft(0, "草稿状态"),
		Published(1, "已上架"),
		;
		static Map<Integer, CommonPublishStatus> allTypes;
		private Integer id;
		private String name;

		CommonPublishStatus(Integer id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
		public static CommonPublishStatus fromKey(Integer id) {
			if (id == null) return null;
			return allTypes.get(id);
		}

		public static boolean isPublished(Integer id){
			CommonPublishStatus status = fromKey(id);
			if(Published.equals(status)) return true;
			return false;
		}

		/*public static SubjectPublishStatus fromKeyHasDefault(Integer id) {
			SubjectPublishStatus mode = fromKey(id);
			if(mode == null){
				mode = ManualHandlingContentData;
			}
			return mode;
		}*/

		static {
			allTypes = new HashMap<>();
			CommonPublishStatus[] types = values();
			for (CommonPublishStatus type : types) {
				allTypes.put(type.getId(), type);
			}
		}

		@Override
		public Integer getId() {
			return id;
		}

		@Override
		public String toString() {
			return id + ":" + name;
		}
	}


	/**
	 * 通知推送状态
	 */
	enum NotificationPushStatus implements IEnum<Integer>{
		WaitingPush(1, "未推送"),
		Pushing(2, "正在推送"),
		Pushed(3,"已推送"),
		;
		static Map<Integer, NotificationPushStatus> allTypes;

		private Integer id;
		private String name;

		NotificationPushStatus(Integer id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		@Override
		public Integer getId() {
			return id;
		}

		@Override
		public String toString() {
			return id + ":" + name;
		}

		@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
		public static NotificationPushStatus fromKey(Integer key) {
			if (key == null) return null;
			return allTypes.get(key);
		}

		public static boolean isWaitingPush(Integer id){
			NotificationPushStatus status = fromKey(id);
			if(WaitingPush.equals(status)) return true;
			return false;
		}

		public static boolean isPushed(Integer id){
			NotificationPushStatus status = fromKey(id);
			if(Pushed.equals(status)) return true;
			return false;
		}

		/*public static InterestPointType fromKeyNonDefault(Integer key) {
			InterestPointType type = fromKey(key);
			if(type != null){
				return type;
			}
			return ConvenienceStore;
		}*/

		static {
			allTypes = new HashMap<>();
			NotificationPushStatus[] types = values();
			for (NotificationPushStatus type : types) {
				allTypes.put(type.getId(), type);
			}
		}
	}

	/**
	 * 消息发送状态
	 */
	enum MessageSendStatus implements IEnum<Integer>{
		WaitingSend(1, "未发送"),
		Sending(2, "正在发送"),
		Sended(3,"已发送"),
		;
		static Map<Integer, MessageSendStatus> allTypes;

		private Integer id;
		private String name;

		MessageSendStatus(Integer id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		@Override
		public Integer getId() {
			return id;
		}

		@Override
		public String toString() {
			return id + ":" + name;
		}

		@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
		public static MessageSendStatus fromKey(Integer key) {
			if (key == null) return null;
			return allTypes.get(key);
		}

		public static boolean isWaitingSend(Integer id){
			MessageSendStatus status = fromKey(id);
			if(WaitingSend.equals(status)) return true;
			return false;
		}

		public static boolean isSended(Integer id){
			MessageSendStatus status = fromKey(id);
			if(Sended.equals(status)) return true;
			return false;
		}
		/*public static InterestPointType fromKeyNonDefault(Integer key) {
			InterestPointType type = fromKey(key);
			if(type != null){
				return type;
			}
			return ConvenienceStore;
		}*/

		static {
			allTypes = new HashMap<>();
			MessageSendStatus[] types = values();
			for (MessageSendStatus type : types) {
				allTypes.put(type.getId(), type);
			}
		}
	}
}
