package com.tatianaworsham;

public class Telephone {
    //This containstrings that are formatted and unformatted.
    public final static int FORMATTED_LENGTH = 13;
    public final static int UNFORMATTED_LENGTH = 10;
    /**
     The isFormatted method determines whether a string is properly formatted as a telephone number.
     @param str The string to test.
     @return true if the string is properly formatted, false otherwise.
    */
    public static boolean isFormatted(String str)
    {
        boolean valid;  // Flag to indicate valid format.
        if (str.length() == FORMATTED_LENGTH &&
         str.charAt(0) =='(' && str.charAt(4)==')' &&
         str.charAt(8) == '-')
            valid = true;
        else
            valid = false;
        
        return valid;
        
    }
    /**
     The unformat method formats a string as a telephone number.
     @param str The string to unformat.
     @return A reference to the unformatted string.
    */ 
    public static String unformated(String str)
    {
        if(isFormatted(str))
        {
            StringBuilder strb = new StringBuilder(str);
            //First, remove the left parenthesis.
            strb.deleteCharAt(0);
            //Next, remove the right parenthesis.
            strb.deleteCharAt(3);
            //Next, remove the hyphen.
            strb.deleteCharAt(6);
            //Return the unformatted string.
            return strb.toString();
        }
        return str;
    }
    /**
     * The format method formats a string as a telephone number.
     * @param str The string to format.
     * @return A reference to the formatted string.
     */
    public static String format(String str)
    {
        StringBuilder strb = new StringBuilder(str);
        if(str.length() == UNFORMATTED_LENGTH)
        {
            //First, insert the left parenthesis.
            strb.insert(0, '(');
            //Next, insert the right parenthesis.
            strb.insert(4, ')');
            //Next, insert the hyphen.
            strb.insert(8, '-');
        }
        //Return the formatted string.
        return strb.toString();
    }
}
