package messages;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class ParsingException extends Exception {

    protected ParsingException(String s, Exception e) {
        super(s,e);
    }


    public static class JSONParsingException extends ParsingException {

        protected JSONParsingException(String s, Exception e) {
            super(s, e);
        }
    }

    public static class JSONUnParsingException extends ParsingException {

        protected JSONUnParsingException(String s, Exception e) {
            super(s, e);
        }
    }
}
