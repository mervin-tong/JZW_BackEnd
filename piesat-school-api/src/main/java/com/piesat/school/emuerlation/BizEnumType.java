package com.piesat.school.emuerlation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.smartwork.api.enums.IEnum;

import java.util.HashMap;
import java.util.Map;

public interface BizEnumType {
	/**
	 * 公开状态
	 */
	enum PublicStatus  {
		FullOpen(0, "完全公开"),
		HalfOpen(1, "申请获取"),
		NoOpen(2,"保护期内"),
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
	 * 权限申请状态
	 */
	enum UploadPermissionsStatus {
		CreatePermissions(0, "申请权限"),
		Pass(1, "权限通过"),
		Reject(2, "权限申请不通过"),
		;
		static Map<Integer, UploadPermissionsStatus> allTypes;
		private int key;
		private String name;
		UploadPermissionsStatus(Integer key, String name) {
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

		public static boolean isNotPermissions(int key){
			if(CreatePermissions.getKey() == key) return true;
			return false;
		}

		public static UploadPermissionsStatus fromKey(Integer key) {
			if (key == null) return null;
			return allTypes.get(key);
		}

		static {
			allTypes = new HashMap<>();
			UploadPermissionsStatus[] types = values();
			for (UploadPermissionsStatus type : types) {
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
	/**
	 * 公开状态
	 */
	enum RoleStatus  {
		ADMIN(1L, "EGC管理员"),
		EGCADMIN(2L, "系统管理员"),
		USER(3L,"普通用户"),
		;
		static Map<Long, RoleStatus> allTypes;
		private Long key;
		private String name;

		RoleStatus(Long key, String name) {
			this.key = key;
			this.name = name;
		}

		public Long getKey() {
			return key;
		}

		public void setKey(Long key) {
			this.key = key;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public static boolean isUSER(Long key){
			if(ADMIN.getKey() == key) return true;
			return false;
		}
		public static RoleStatus fromKey(Integer key) {
			if (key == null) return null;
			return allTypes.get(key);
		}

		static {
			allTypes = new HashMap<>();
			RoleStatus[] types = values();
			for (RoleStatus type : types) {
				allTypes.put(type.getKey(), type);
			}
		}

	}


	/**
	 * 数据是否通过评审
	 */
	enum ThroughReview  {
		NOTPASS(0, "未通过"),
		PASS(1, "通过"),
		;
		static Map<Integer, ThroughReview> allTypes;
		private Integer key;
		private String name;

		ThroughReview(Integer key, String name) {
			this.key = key;
			this.name = name;
		}

		public Integer getKey() {
			return key;
		}

		public void setKey(Integer key) {
			this.key = key;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public static boolean isUSER(Integer key){
			if(NOTPASS.getKey() == key) return true;
			return false;
		}
		public static ThroughReview fromKey(Integer key) {
			if (key == null) return null;
			return allTypes.get(key);
		}

		static {
			allTypes = new HashMap<>();
			ThroughReview[] types = values();
			for (ThroughReview type : types) {
				allTypes.put(type.getKey(), type);
			}
		}

	}

	/**
	 * 数据评审状态
	 */
	enum ReviewStatus  {
		TOREVIEW(0, "待评审"),
		FIRSTREVIEWPASS(1, "初审通过"),
		FIRSTREVIEWNOPASS(2,"初审不通过"),
		RECHECKPASS(3,"复审通过"),
		RECHECKNOPASS(4,"复审不通过"),
		RELEASE(5,"已发布")
		;
		static Map<Integer, ReviewStatus> allTypes;
		private Integer key;
		private String name;

		ReviewStatus(Integer key, String name) {
			this.key = key;
			this.name = name;
		}

		public Integer getKey() {
			return key;
		}

		public void setKey(Integer key) {
			this.key = key;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public static boolean isTOREVIEW(Integer key){
			if(TOREVIEW.getKey() == key) return true;
			return false;
		}
		public static ReviewStatus fromKey(Integer key) {
			if (key == null) return null;
			return allTypes.get(key);
		}

		static {
			allTypes = new HashMap<>();
			ReviewStatus[] types = values();
			for (ReviewStatus type : types) {
				allTypes.put(type.getKey(), type);
			}
		}

	}
	/**
	 * 数据评审状态
	 */
	enum Default {
		NULL(0L, "默认人员编号（表示没有）"),
		;

		private Long key;
		private String name;
		Default(Long key, String name) {
			this.key = key;
			this.name = name;
		}
		public Long getKey() {
			return key;
		}

		public void setKey(Long key) {
			this.key = key;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	/**
	 * 资讯类别
	 */
	enum InformationType  {
		Web(1, "YGC平台"),
		DataDynamic(2, "数据动态"),
		Important(3,"主要通知")
		;
		static Map<Integer, InformationType> allTypes;
		private Integer key;
		private String name;

		InformationType(Integer key, String name) {
			this.key = key;
			this.name = name;
		}

		public Integer getKey() {
			return key;
		}

		public void setKey(Integer key) {
			this.key = key;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public static InformationType fromKey(Integer key) {
			if (key == null) return null;
			return allTypes.get(key);
		}

		static {
			allTypes = new HashMap<>();
			InformationType[] types = values();
			for (InformationType type : types) {
				allTypes.put(type.getKey(), type);
			}
		}

	}
}
