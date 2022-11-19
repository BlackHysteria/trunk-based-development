package com.bakumcev.demo.service;

import java.io.File;

public interface BashService {

    Process runCommand(String command);

    Process runFromFile(String command, String[] envp, File dir);

}
