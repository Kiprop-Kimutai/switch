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
public class UssdUnBindResp extends MessageBase {

    public UssdUnBindResp() {
        this.CommandID = CommandIDs.UssdUnBindResp;
    }

    @Override
    protected boolean dencode(byte [] message) {
        return super.dencode(message); //To change body of generated methods, choose Tools | Templates.
    }
    

}
