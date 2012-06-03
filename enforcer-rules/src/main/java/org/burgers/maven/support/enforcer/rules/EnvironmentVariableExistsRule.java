package org.burgers.maven.support.enforcer.rules;

import org.apache.maven.enforcer.rule.api.EnforcerRule;
import org.apache.maven.enforcer.rule.api.EnforcerRuleException;
import org.apache.maven.enforcer.rule.api.EnforcerRuleHelper;

public class EnvironmentVariableExistsRule implements EnforcerRule {
    private String environmentVariable;
    private String[] environmentVariables;

    public void execute(EnforcerRuleHelper enforcerRuleHelper) throws EnforcerRuleException {
        verifyState();
        if (environmentVariables != null && environmentVariables.length > 0){
            for (String variable : environmentVariables) {
                checkForEnvironmentVariable(variable);
            }
        }
        if (environmentVariable != null){
            checkForEnvironmentVariable(environmentVariable);
        }
    }

    private void verifyState() throws EnforcerRuleException {
        if (environmentVariable == null && environmentVariables == null){
            throw new EnforcerRuleException("Must have either environmentVariable or environmentVariables attributes defined.");
        }
    }

    private void checkForEnvironmentVariable(String variable) throws EnforcerRuleException {
        if (!System.getenv().keySet().contains(variable)){
           throw new EnforcerRuleException("Environment variable: " + variable + " does not exist.");
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

    public void setEnvironmentVariables(String[] environmentVariables) {
        this.environmentVariables = environmentVariables;
    }
}
