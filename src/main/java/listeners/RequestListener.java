package listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class RequestListener
 *
 */
@WebListener
public class RequestListener implements ServletRequestListener {

    /**
     * Default constructor. 
     */
    public RequestListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent arg0)  { 
         System.out.println("requestDestroyed");
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent arg0)  { 
    	System.out.println("requestInitialized");
    	System.out.println("Lisener request - correo: " + arg0.getServletRequest().getParameter("txtCorreo"));
    	System.out.println("Lisener request - Protocolo: " + arg0.getServletRequest().getProtocol());
    	System.out.println("Lisener request - Port: " + arg0.getServletRequest().getServerPort());
    	System.out.println("Lisener request - Name: " + arg0.getServletRequest().getServerName());
    }
	
}
