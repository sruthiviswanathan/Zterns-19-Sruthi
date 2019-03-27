package com.zilker.CustomTags;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.Calendar;

public class CustomTags extends SimpleTagSupport {

	String message;
	
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public void doTag() throws JspException, IOException {
		StringWriter stringWriter = new StringWriter();
		JspWriter out = getJspContext().getOut();
		out.print(Calendar.getInstance().getTime());
		out.print(message);
		getJspBody().invoke(stringWriter);
		getJspContext().getOut().println(stringWriter.toString());
	}
}
