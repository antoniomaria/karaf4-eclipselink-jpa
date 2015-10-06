package net.sf.companymanager.discovery;

import org.apache.shiro.web.servlet.IniShiroFilter;

public class KarafShiroFilter extends IniShiroFilter {

    @Override
    public String getConfigPath() {
        final String karafEtc = System.getProperty("karaf.etc");
        return "file:" + karafEtc + "/shiro.ini";
    }
}
