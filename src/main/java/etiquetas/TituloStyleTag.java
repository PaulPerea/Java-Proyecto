package etiquetas;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TituloStyleTag extends TagSupport {
	
	@Override
	public int doStartTag() throws JspException {
	
		JspWriter writer = pageContext.getOut();
		
		try {
			
			writer.print("Hola a todos");
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.doStartTag();
	}
	
}
