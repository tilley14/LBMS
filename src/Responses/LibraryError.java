package Responses;

import LBMSCommands.*;

public class LibraryError implements Response{

    private Object obj;

    private LBMSCommand command;

    private String action;


    public LibraryError(LBMSCommand cmd,Object object,String act) {
        this.command = cmd;
        this.obj = object;
        this.action = act;
    }

    public getCommand() {
        return this.command;
    }

    public toString() {
        return "ERROR: Unsuccessful " + this.action + " of " + this.obj.toString();
    }
}