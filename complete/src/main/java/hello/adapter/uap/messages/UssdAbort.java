package hello.adapter.uap.messages;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Ghasem Fattahpour
 */
public class UssdAbort extends MessageBase {

    public UssdAbort() {
        this.CommandID = CommandIDs.UssdAbort;
    }

}
