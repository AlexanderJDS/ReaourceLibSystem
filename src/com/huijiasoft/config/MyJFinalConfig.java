package com.huijiasoft.config;

import com.huijiasoft.handler.ResourceHandler;
import com.huijiasoft.model._MappingKit;
import com.huijiasoft.routes.AdminRoutes;
import com.huijiasoft.routes.FrontRoutes;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;



/**
 * @author pangPython
 * JFinal�����ļ�
 */
public class MyJFinalConfig extends JFinalConfig {

	public static void main(String[] args) {
		JFinal.start("WebRoot",8080,"/",5);
	}
	
	@Override
	public void configConstant(Constants me) {
		PropKit.use("myconfig.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setError404View("/404.html");
		me.setBaseDownloadPath("WebRoot/download/");
		me.setBaseUploadPath(PathKit.getWebRootPath()+"/upload/");
	}
	
	@Override
	public void configRoute(Routes me) {
		//����ǰ��·��
		me.add(new FrontRoutes());
		//���ú��·��
		me.add(new AdminRoutes());
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new ResourceHandler());
	}

	@Override
	public void configInterceptor(Interceptors arg0) {

	}
	
	public static C3p0Plugin createC3p0Plugin(){
		return new C3p0Plugin(PropKit.get("jdbcUrl"),PropKit.get("user"),PropKit.get("password").trim());
	}
	

	@Override
	public void configPlugin(Plugins me) {
		C3p0Plugin c3p0Plugin = createC3p0Plugin();
		me.add(c3p0Plugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		_MappingKit.mapping(arp);
	}
	


}
