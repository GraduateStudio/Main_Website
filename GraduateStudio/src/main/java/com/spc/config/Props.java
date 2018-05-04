package com.spc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "Props")
public class Props {
    List<Map<String, String>> ssh = new ArrayList<>();
    Map<String, String> sshmap = new HashMap<>();

    public Map<String, String> getSshmap() {
        return sshmap;
    }

    public void setSshmap(Map<String, String> sshmap) {
        this.sshmap = sshmap;
    }

    public List<Map<String, String>> getSsh() {
        return ssh;
    }

    public void setSsh(List<Map<String, String>> ssh) {
        this.ssh = ssh;
    }

}
