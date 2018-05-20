package com.linktrust.fuyao.shiro;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.linktrust.fuyao.bean.Privilege;
import com.linktrust.fuyao.service.LoginService;

public class ClientShiroFilterFactoryBean extends ShiroFilterFactoryBean implements InitializingBean {
	
	 // 加载默认的权限配置
    private static String filterChainDefinitions;
    //	@Autowired
    private Logger log = Logger.getLogger(ClientShiroFilterFactoryBean.class);
    
    @Autowired
    private LoginService loginService;

    @Override
    public void setFilterChainDefinitions(String definitions) {
        Ini ini = new Ini();
        ClientShiroFilterFactoryBean.filterChainDefinitions = definitions;
        // 加载默认的权限配置
        ini.load(filterChainDefinitions);
        Ini.Section section = ini.get(Ini.DEFAULT_SECTION_NAME);
        try {
            super.setFilterChainDefinitionMap(section);
        } catch (Exception e) {
            log.error("shiro:", e);
        }
    }

    /**
     * 刷新资源缓存
     */
    public void update() {

        AbstractShiroFilter shiroFilter = null;

        try {
            shiroFilter = (AbstractShiroFilter) super.getObject();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        // 获取过滤管理器
        PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                .getFilterChainResolver();
        DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

        // 清空初始权限配置
        manager.getFilterChains().clear();
        super.getFilterChainDefinitionMap().clear();

        // 重新构建生成
        this.reload();
        Map<String, String> chains = super.getFilterChainDefinitionMap();

        for (Map.Entry<String, String> entry : chains.entrySet()) {
            String url = entry.getKey();
            String chainDefinition = entry.getValue().trim().replace(" ", "");
            manager.createChain(url, chainDefinition);
        }

        log.debug("update shiro permission success...");


    }

    private synchronized void reload() {
        try {
            Ini ini = new Ini();
            // 加载默认的权限配置
            ini.load(ClientShiroFilterFactoryBean.filterChainDefinitions);
            Ini.Section section = ini.get(Ini.DEFAULT_SECTION_NAME);
            List<Privilege> privileges = loginService.selectAllPrivilege();
            if (privileges != null && privileges.size() > 0) {
                section.clear();
                // 初始化权限配置
                for (Privilege pri : privileges) {
                    if (pri.getType() == 0) {
                        if (pri.getPriUrl().startsWith("/")) {
                            section.put(pri.getPriUrl(), pri.getPriName());
                        } else {
                            section.put("/" + pri.getPriUrl(), pri.getPriName());

                        }
                        continue;
                    }
                    log.info("update:" + "/" + pri.getPriUrl() + "=" + "perms["
                            + pri.getPriName() + "]");
                    section.put("/" + pri.getPriUrl(), "perms[" + pri.getPriName()
                            + "]");
                }
                ini.load(ClientShiroFilterFactoryBean.filterChainDefinitions);
                Ini.Section oldSection = ini.get(Ini.DEFAULT_SECTION_NAME);
                section.putAll(oldSection);
            }

            super.setFilterChainDefinitionMap(section);
        } catch (Exception e) {
            log.error("shiro:", e);
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        final Thread t = new Thread(new Runnable() {

            // 本线程是用于检查系统权限是否变更,后期可以改为主动通知
            @Override
            public void run() {
                int index = 0;
                boolean isInit = true;
                log.info("刷新系统权限，获取最新系统权限");
                try {
                    while (true) {
                        if (loginService != null) {
                            if (isInit) {
                                update();
                                log.info("刷新系统权限成功");
                                isInit = false;
                            } else {
                                Thread.sleep(1000 * 60 * 10);// 每10分钟刷新一次系统权限
                                update();
                                log.info("刷新系统权限成功");
                            }

                        } else {
                            index++;
                            log.warn("刷新系统权限失败,第[" + index + "]次重试");
                            Thread.sleep(1000);
                        }

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
