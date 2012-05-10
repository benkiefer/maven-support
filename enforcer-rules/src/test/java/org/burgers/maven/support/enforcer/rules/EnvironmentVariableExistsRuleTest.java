package org.burgers.maven.support.enforcer.rules;

import org.apache.maven.enforcer.rule.api.EnforcerRuleException;
import org.apache.maven.enforcer.rule.api.EnforcerRuleHelper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

public class EnvironmentVariableExistsRuleTest {
    EnvironmentVariableExistsRule rule;
    EnforcerRuleHelper helper;

    @Before
    public void setUp(){
        rule = new EnvironmentVariableExistsRule();
        helper = mock(EnforcerRuleHelper.class);
    }

    @Test
    public void execute() throws Exception {
        rule.setEnvironmentVariable("MAVEN_HOME");
        rule.execute(helper);
        verifyZeroInteractions(helper);
    }

    @Test
    public void execute_variable_not_found() throws Exception {
        rule.setEnvironmentVariable("DOES_NOT_EXIST");
        try{
            rule.execute(helper);
            fail("Shouldn't get here");
        } catch (EnforcerRuleException e){
            assertEquals(e.getMessage(), "Environment variable: DOES_NOT_EXIST does not exist.");
        }
        verifyZeroInteractions(helper);
    }

    @Test
    public void isCacheable() throws Exception {
        assertFalse(rule.isCacheable());
    }

    @Test
    public void isResultValid() throws Exception {
        assertFalse(rule.isResultValid(null));
    }

    @Test
    public void getCacheId() throws Exception {
        assertNull(rule.getCacheId());
    }
}
