package com.epam.finalproject;

import com.epam.finalproject.controller.command.CommandExecutor;
import org.junit.Test;
import org.junit.Assert;

public class CommandExecutorTests {

    @Test
    public void assertCommandExecutorNotNull() {
        CommandExecutor commandExecutor = new CommandExecutor();
        Assert.assertNotNull(commandExecutor);
    }

}
