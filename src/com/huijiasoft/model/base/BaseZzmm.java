package com.huijiasoft.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseZzmm<M extends BaseZzmm<M>> extends Model<M> implements IBean {

	public void setZzmmId(java.lang.Integer zzmmId) {
		set("zzmm_id", zzmmId);
	}

	public java.lang.Integer getZzmmId() {
		return get("zzmm_id");
	}

	public void setZzmmname(java.lang.String zzmmname) {
		set("zzmmname", zzmmname);
	}

	public java.lang.String getZzmmname() {
		return get("zzmmname");
	}

}
