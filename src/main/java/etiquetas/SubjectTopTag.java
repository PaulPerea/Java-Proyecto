//package etiquetas;
//
//import java.util.List;
//
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.JspWriter;
//import javax.servlet.jsp.tagext.TagSupport;
//
//import dao.DAOFactory;
//import entidades.Subject;
//import interfaces.SubjectInterface;
//
//public class SubjectTopTag extends TagSupport {
//
//	private String numeroCursos;
//	
//	@Override
//	public int doStartTag() throws JspException {
//		
//		JspWriter writer = pageContext.getOut();
//		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
//		SubjectInterface subjectInterface = daoFactory.getSubject();
//		
//		try {
//			
//			List<Subject> subjectsList = subjectInterface.listSubjectTop(numeroCursos);
//			for(Subject item: subjectsList) {
//				writer.println(item.getName());
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return super.doStartTag();
//	}
//
//	public String getNumeroCursos() {
//		return numeroCursos;
//	}
//
//	public void setNumeroCursos(String numeroCursos) {
//		this.numeroCursos = numeroCursos;
//	}
//	
//	
//	
//}
