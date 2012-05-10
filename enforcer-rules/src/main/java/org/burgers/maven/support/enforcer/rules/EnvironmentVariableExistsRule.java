package org.burgers.maven.support.enforcer.rules;

import org.apache.maven.enforcer.rule.api.EnforcerRule;
import org.apache.maven.enforcer.rule.api.EnforcerRuleException;
import org.apache.maven.enforcer.rule.api.EnforcerRuleHelper;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;

public class EnvironmentVariableExistsRule implements EnforcerRule {
    private String environmentVariable;

    public void execute(EnforcerRuleHelper enforcerRuleHelper) throws EnforcerRuleException {
        if (!System.getenv().keySet().contains(environmentVariable)){
           throw new EnforcerRuleException("Environment variable: " + environmentVariable + " does not exist.");
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

    public void setEnvironmentVariable(String environmentVariable) {
        this.environmentVariable = environmentVariable;
    }
}
