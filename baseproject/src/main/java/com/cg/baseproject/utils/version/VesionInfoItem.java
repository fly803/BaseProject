package com.cg.baseproject.utils.version;

/*
 *  apk升级
 升级url及服务器处理
 升级url类似为： http://192.100.110.252:8007/reserver/apkupdate.do?vod=1.0
 其中1.0为apk填写的当前的版本号；
 服务器收到如上url后，返回
 [{"update":"false", "policy":"onDaily","apkpath":"http://192.168.1.5/apkupdate/","apkname":"xxxx.apk","verName":"2.0","verCode":2}]
 字段说明
 update:升级控制开关, 如果为false,则忽略升级
 policy: onDaily表示每天只升级一次，onStarted表示程序每次进入都进行升级检查
 apkpath: 表示升级软件包在服务器的目录配置
 apkname: 表示升级软件的名字
 verName: 该字段表示服务器上的软件升级包的版本号名称
 verCode: 该字段表示服务器上的软件升级包的版本号（点播端软件内部使用，判断版本号）
 软件包下载
 只需要将软件包放置在服务器的指定文件即可，由使用http方式下载，无需服务器配置下载，服务器只需要完成json配置的响应；
 */
public class VesionInfoItem {
	private String update;
	private String policy;
	private String apkpath;
	private String apkname;
	private String verName;
	private String verCode;

	public String getApkname() {
		return apkname;
	}

	public void setApkname(String apkname) {
		this.apkname = apkname;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public String getApkpath() {
		return apkpath;
	}

	public void setApkpath(String apkpath) {
		this.apkpath = apkpath;
	}

	public String getVerName() {
		return verName;
	}

	public void setVerName(String verName) {
		this.verName = verName;
	}

	public String getVerCode() {
		return verCode;
	}

	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}

	@Override
	public String toString() {
		return "VesionInfoItem [update=" + update + ", policy=" + policy + ", apkpath="
				+ apkpath + ", apkname=" + apkname + ", verName=" + verName + ", verCode=" + verCode
				+ "]";
	}

}
