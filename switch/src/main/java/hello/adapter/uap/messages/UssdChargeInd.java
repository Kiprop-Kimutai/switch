/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello.adapter.uap.messages;

/**
 *
 * @author Ghasem Fattahpour
 */
public class UssdChargeInd extends MessageBase {

    public UssdChargeInd() {
        this.CommandID = CommandIDs.UssdChargeInd;
    }
    
    
}
