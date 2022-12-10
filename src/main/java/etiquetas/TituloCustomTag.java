package etiquetas;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TituloCustomTag extends TagSupport {

	private String text;
	private String size;
	private String color;
	
	@Override
	public int doStartTag() throws JspException {
		
		JspWriter writer = pageContext.getOut();
		
		try {
			
			writer.print("<font color='"+color+"' size='"+size+"'>");
			writer.print(text);
			writer.print("</font>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return super.doStartTag();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
	
}
