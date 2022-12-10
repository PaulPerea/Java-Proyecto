package listeners;

import java.util.Calendar;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SesionListener
 *
 */
@WebListener
public class SesionListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public SesionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
    	System.out.println("sessionCreated");
    	System.out.println("Sesssion Listener created - " + arg0.getSession().getId());
    	System.out.println("Sesssion Listener created - " + arg0.getSession().getCreationTime());
    	
    	long time = arg0.getSession().getCreationTime();
    	
    	Calendar calendar = Calendar.getInstance();
    	
    	calendar.setTimeInMillis(time);
    	
    	int year = calendar.get(Calendar.YEAR);
    	int month = calendar.get(Calendar.MONTH);
    	int day = calendar.get(Calendar.DAY_OF_MONTH);
    	int hora = calendar.get(Calendar.HOUR_OF_DAY);
    	int min = calendar.get(Calendar.MINUTE);
    	int sec = calendar.get(Calendar.SECOND);
    	
    	System.out.println("Sesssion Listener created - " + calendar.getTimeZone().getDisplayName());
    	System.out.println("Sesssion Listener year - " + year);
    	System.out.println("Sesssion Listener month - " + month);
    	System.out.println("Sesssion Listener day - " + day);
    	System.out.println("Sesssion Listener hora - " + hora);
    	System.out.println("Sesssion Listener min - " + min);
    	System.out.println("Sesssion Listener sec - " + sec);
    	
    	
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	System.out.println("sessionDestroyed");
    }
	
}
