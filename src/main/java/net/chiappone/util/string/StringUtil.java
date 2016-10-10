package net.chiappone.util.string;

/**
 * @author Kurtis Chiappone
 * @date 10/9/2016
 */
public class StringUtil {

	/**
	 * Capitalizes the first letter of the given String.
	 *
	 * @param s
	 * @return
	 */
	public static String capitalizeFirstLetter( String s ) {

		char[] chars = s.toCharArray();
		chars[ 0 ] = Character.toUpperCase( chars[ 0 ] );
		return new String( chars );

	}

	/**
	 * Returns the Nth index of the char in a String. This method will return -1
	 * if the Nth occurence if the specified char is not found in the String.
	 *
	 * @param charToFind
	 * @param n
	 * @param s
	 * @return
	 */
	public static int findNthIndexOf( char charToFind, int n, String s ) {

		// User provided an invalid N

		if ( n <= 0 ) {

			return -1;

		}

		// Delegate to String#indexOf

		if ( n == 1 ) {

			return s.indexOf( charToFind );

		}

		// Traverse the chars manually

		int index = 0;
		int timesFound = 0;

		for ( char ch : s.toCharArray() ) {

			// Found the char

			if ( ch == charToFind ) {

				// If found N times, break

				if ( ( ++timesFound ) == n )
					break;

			}

			index++;

		}

		// Return -1 if we didn't find the char N amount of times

		if ( timesFound != n ) {

			return -1;

		}

		// Otherwise return the index which we found the char at the Nth
		// occurrence

		return index;

	}

	/**
	 * Returns a Java sanitized field or method name. Removes illegal characters
	 * and converts to camel-case if words are delimited by an underscore,
	 * hyphen or period.
	 *
	 * @param s
	 * @return
	 */
	public static String getJavaSanitizedName( String s ) {

		s = s.toLowerCase();
		char[] chars = s.toCharArray();

		for ( int i = 0; i < chars.length; i++ ) {

			char current = chars[ i ];

			// If the String is delimited by some token...

			if ( current == '_' || current == '-' || current == '.' ) {

				// Capitalize the next char

				char next = chars[ i + 1 ];

				if ( next != '\0' ) {

					chars[ i + 1 ] = Character.toUpperCase( next );

				}

			}

		}

		// Return to string and get rid of punctuation

		s = new String( chars );
		s = s.replaceAll( "\\p{Punct}", "" );
		return s.trim();

	}

	/**
	 * Removes commas from the given String.
	 *
	 * @param str
	 * @return
	 */
	public static String removeCommas( String str ) {

		return str.replaceAll( ",", "" );

	}

	/**
	 * Removes quotes from the given String.
	 *
	 * @param s
	 * @return
	 */
	public static String removeQuotes( String s ) {

		s = s.replaceAll( "[\"'`]", "" );
		return s;

	}

	/**
	 * Returns the reverse of the given String.
	 *
	 * @param s
	 * @return
	 */
	public static String reverse( String s ) {

		return new StringBuilder( s ).reverse().toString();

	}

	/**
	 * Removes multiples of inner white space from the given String and performs
	 * the standard "outer" trim.
	 *
	 * @param s
	 * @return
	 */
	public static String trimAll( String s ) {

		s = s.replaceAll( "\\s+", "" );
		return s.trim();

	}

	/**
	 * his method wraps a String based on a default number of max chars per
	 * line (60). This method is a helper which just calls
	 * {@link #wrap(String, int)}
	 *
	 * @param line
	 * @return
	 */
	public static String wrap( String line ) {

		return wrap( line, 60, false );

	}

	/**
	 * @param line
	 * @param maxCharsPerLine
	 * @return
	 */
	public static String wrap( String line, int maxCharsPerLine ) {

		return wrap( line, maxCharsPerLine, false );

	}

	/**
	 * This method takes a String with presumably no line breaks and inserts a
	 * break in the first white space after the max number of chars specified.
	 * This is useful for wrapping, especially in JOptionPanes where wrapping is
	 * not a supported behavior.
	 *
	 * @param line
	 * @param maxCharsPerLine
	 * @param htmlSafe
	 * @return
	 */
	public static String wrap( String line, int maxCharsPerLine, boolean htmlSafe ) {

		char[] characters = line.toCharArray();
		int count = 0;

		for ( int i = 0; i < characters.length; i++ ) {

			count++;

			if ( count > maxCharsPerLine ) {

				if ( characters[ i ] == ' ' ) {

					characters[ i ] = '\n';
					count = 0;

				}

			}

		}

		String toRet = new String( characters );

		if ( htmlSafe ) {

			toRet = toRet.replaceAll( "\n", "<br/>" );

		}

		return toRet;

	}

	/**
	 * @param line
	 * @return
	 */
	public static String wrapHtmlSafe( String line ) {

		return wrap( line, 60, true );

	}

	/**
	 * @param line
	 * @param maxCharsPerLine
	 * @return
	 */
	public static String wrapHtmlSafe( String line, int maxCharsPerLine ) {

		return wrap( line, maxCharsPerLine, true );

	}

}
