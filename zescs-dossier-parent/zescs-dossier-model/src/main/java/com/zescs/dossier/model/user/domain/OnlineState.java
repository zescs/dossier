package com.zescs.dossier.model.user.domain;

/**
 * 
 * @ClassName: OnlineState
 * @Description: TODO(用户在线状态)
 * @author sjth:john
 * @date 2014年6月26日 上午9:54:08
 * 
 */
public enum OnlineState {
	ONLINE(1, "在线"), OFFLINE(2, "离线"), BUSY(3, "忙碌");

	private Integer index;

	private String displayName;

	public Integer getIndex() {
		return index;
	}

	public String getDisplayName() {
		return displayName;
	}

	private OnlineState() {
	}

	private OnlineState(Integer index, String displayName) {
		this.index = index;
		this.displayName = displayName;
	}

	public String getOnlineStateText() {
		switch (this) {
		case ONLINE:
			return "在线";
		case OFFLINE:
			return "离线";
		default:
			return "忙碌";
		}
	}

}
