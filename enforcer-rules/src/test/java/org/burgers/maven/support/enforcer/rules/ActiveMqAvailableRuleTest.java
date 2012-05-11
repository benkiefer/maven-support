package org.burgers.maven.support.enforcer.rules;

import org.apache.maven.enforcer.rule.api.EnforcerRuleException;
import org.junit.Before;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.Test;

import static org.mockito.Mockito.verify;
import org.junit.After;

import java.io.IOException;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.doThrow;
import static junit.framework.Assert.fail;

public class ActiveMqAvailableRuleTest  {
    ActiveMqAvailableRule rule;
    ActiveMqHelper mockHelper;

    @Before
    public void setUp(){
        rule = new ActiveMqAvailableRule();
        mockHelper = mock(ActiveMqHelper.class);
        rule.setHelper(mockHelper);
    }

    @Test
    public void execute() throws EnforcerRuleException, IOException {
        rule.setActiveMqUrl("blah");
        rule.execute(null);
        verify(mockHelper).connect("blah");
    }

    @Test
    public void execute_with_exception() throws IOException {
        rule.setActiveMqUrl("blah");
        doThrow(new RuntimeException("Test Kaboom!!!")).when(mockHelper).connect("blah");
        try{
            rule.execute(null);
            fail("Shouldn't get here.");
        } catch (Exception e){
            assertEquals(e.getMessage(), "Test Kaboom!!!");
        }
        verify(mockHelper).connect("blah");
    }

    @After
    public void tearDown(){
        verifyNoMoreInteractions(mockHelper);
    }

}
