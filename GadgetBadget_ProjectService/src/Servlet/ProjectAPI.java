package Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Project;

/**
 * Servlet implementation class ProjectAPI
 */
@WebServlet("/ProjectAPI")
public class ProjectAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Project projObj = new Project();

    /**
     * Default constructor. 
     */
    public ProjectAPI() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String topic = request.getParameter("projectName");
		String type = request.getParameter("projectType");
		String price = request.getParameter("projFund");
		String researcherID = "1002";
		
		String output = "";
		
		if(type.equalsIgnoreCase("finished"))
				 output = projObj.insertFinishedProject(researcherID, topic, "Not Paid", price);
		else if(type.equalsIgnoreCase("unfinished"))
			 output = projObj.insertUnfinishedProject(researcherID, topic, "Not Paid", price);
		
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Got here");
		Map paras = getParasMap(request); 
		String id = paras.get("hidProjectIDSave").toString();
		String topic = paras.get("projectName").toString();
		String price = paras.get("projFund").toString();
		String researcherID = "1002";
		System.out.println(id+topic+price+researcherID);
		
		 String output = projObj.updateFinishedProject(id, topic, price, researcherID); 
		response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		System.out.println(paras.get("projectID").toString());
		 String output = projObj.deleteProject(paras.get("projectID").toString()); 
		response.getWriter().write(output); 
	}
	
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
		try
		 { 
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			 String queryString = scanner.hasNext() ? 
			 scanner.useDelimiter("\\A").next() : ""; 
			 scanner.close(); 
			 String[] params = queryString.split("&"); 
		 for (String param : params) 
		 { 
				String[] p = param.split("=");
				 map.put(p[0], p[1]); 
		 } 
		 } 
		catch (Exception e) 
		 { 
		 } 
	return map; 
	}

}
