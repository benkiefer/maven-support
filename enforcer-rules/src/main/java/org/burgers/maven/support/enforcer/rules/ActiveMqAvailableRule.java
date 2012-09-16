package org.burgers.maven.support.enforcer.rules;

import org.apache.maven.enforcer.rule.api.EnforcerRule;
import org.apache.maven.enforcer.rule.api.EnforcerRuleException;
import org.apache.maven.enforcer.rule.api.EnforcerRuleHelper;

public class ActiveMqAvailableRule implements EnforcerRule {
    private String activeMqUrl = "service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi";
    private ActiveMqHelper helper = new ActiveMqHelper();

    public void execute(EnforcerRuleHelper enforcerRuleHelper) throws EnforcerRuleException {
        try{
            helper.connect(activeMqUrl);
        } catch (Exception e){
            throw new EnforcerRuleException(e.getMessage());
        }

    }

    public boolean isCacheable() {
        return false;
    }

    public boolean isResultValid(EnforcerRule enforcerRule) {
        return false;
    }

    public String getCacheId() {
        return null;
    }

    public void setHelper(ActiveMqHelper helper) {
        this.helper = helper;
    }

    public void setActiveMqUrl(String activeMqUrl) {
        this.activeMqUrl = activeMqUrl;
    }
}
