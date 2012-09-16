package org.burgers.maven.support.enforcer.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

import javax.management.MBeanServerConnection;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class ActiveMqHelperTest {
    ActiveMqHelper rule;
    MBeanServerConnectionFactoryBean mockMbean;
    MBeanServerConnection mockConnection;

    @Before
    public void setUp() {
        rule = new ActiveMqHelper();
        mockMbean = mock(MBeanServerConnectionFactoryBean.class);
        mockConnection = mock(MBeanServerConnection.class);
        rule.setmBean(mockMbean);
    }

    @Test
    public void testExecute() throws IOException {
        when(mockMbean.getObject()).thenReturn(mockConnection);

        rule.connect("blah");

        verify(mockMbean).setServiceUrl("blah");
        verify(mockMbean).afterPropertiesSet() ;
        verify(mockMbean).getObject();
        verify(mockMbean).destroy();
        verifyNoMoreInteractions(mockMbean);
    }

    @Test
    public void testExecute_throwException() throws IOException {
        doThrow(new RuntimeException("Test Kaboom!!!")).when(mockMbean).setServiceUrl("blah");

        try {
            rule.connect("blah");
            fail("Shouldn't get here.");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Test Kaboom!!!");
        }

        verify(mockMbean).setServiceUrl("blah");
        verify(mockMbean).destroy();
        verifyNoMoreInteractions(mockMbean);
    }

    @Test
    public void testExecute_throwException_when_destroying_mbean() throws IOException {
        when(mockMbean.getObject()).thenReturn(mockConnection);
        doThrow(new RuntimeException("Test Kaboom!!!")).when(mockMbean).destroy();

        try {
            rule.connect("blah");
            fail("Shouldn't get here.");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Test Kaboom!!!");
        }

        verify(mockMbean).setServiceUrl("blah");
        verify(mockMbean).afterPropertiesSet();
        verify(mockMbean).getObject();
        verify(mockMbean).destroy();
        verifyNoMoreInteractions(mockMbean);
    }

    @After
    public void tearDown() {
        verifyZeroInteractions(mockConnection);
    }
}
