package com.company.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    Logger logger = LogManager.getLogger(Command.class);
    String execute(HttpServletRequest request);
}
