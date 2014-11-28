package networking;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class NetworkingException extends Exception {

    protected NetworkingException(String e, Exception ex) {
        super(e, ex);
    }

    public static class SendingException extends NetworkingException {
        public SendingException(String e, Exception ex) {
            super(e, ex);
        }
    }

    public static class ReceivingException extends NetworkingException {

        protected ReceivingException(String e, Exception ex) {
            super(e,ex);
        }
    }

}

