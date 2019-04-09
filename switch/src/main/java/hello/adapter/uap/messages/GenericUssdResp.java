package hello.adapter.uap.messages;

public class GenericUssdResp extends MessageBase {
    public GenericUssdResp(byte[] message) {
        this.Message = message;
        this.dencode();
    }

    @Override
    protected boolean dencode() {
        super.dencode();
        return true;
    }
}
