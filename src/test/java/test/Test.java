package test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test {

    public static void main(String[] args) {
	try {
	  System.out.println(URLEncoder.encode("name=中华人民共和国&value=tom&key=smart_wasp","UTF-8"));
	  System.out.println(URLEncoder.encode("This*string*has*asterisks","UTF-8"));
	  System.out.println(URLEncoder.encode("This%string%has%percent%signs", "UTF-8"));
	  System.out.println(URLEncoder.encode("This+string+has+pluses","UTF-8"));
	  System.out.println(URLEncoder.encode("This/string/has/slashes","UTF-8"));
	  System.out.println(URLEncoder.encode("This/string/has/quote/marks", "UTF-8"));
	  System.out.println(URLEncoder.encode("This:string:has:colons","UTF-8"));
	  System.out.println(URLEncoder.encode("This~string~has~tildes","UTF-8"));
	  System.out.println(URLEncoder.encode("This(string)has(parentheses)", "UTF-8"));
	  System.out.println(URLEncoder.encode("This.string.has.periods","UTF-8"));
	  System.out.println(URLEncoder.encode("This=string=has=equals=signs", "UTF-8"));
	  System.out.println(URLEncoder.encode("This&string&has&ersands","UTF-8"));
	  System.out.println(URLEncoder.encode("Thiséstringéhasé non-ASCII characters","UTF-8"));
	  // System.out.println(URLEncoder.encode("this中华人民共和国","UTF-8"));
	  System.out.println(URLDecoder.decode("name%3D%E4%B8%AD%E5%8D%8E%E4%BA%BA%E6%B0%91%E5%85%B1%E5%92%8C%E5%9B%BD%26value%3Dtom%26key%3Dsmart_wasp", "utf-8"));

	  } catch (UnsupportedEncodingException ex) {
	      throw new RuntimeException("Broken VM does not support UTF-8");
	  }
    }
}
