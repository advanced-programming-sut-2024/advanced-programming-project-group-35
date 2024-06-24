package com.example.controller;

import com.example.model.App;
import com.example.view.Menu;

import java.util.Scanner;

public abstract class AppController {
    public abstract void run();
    public abstract void runCommand(String input);
}
