package com.excilys.computerDatabase.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class BeforTest implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

	private static boolean started = false;
	//private static final String PROP_FILE_NAME = "test-db.sql";
	private static final String PROP_FILE_NAME = "/scriptTest.sql";

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		  if (!started) {
	            started = true;
	            
	            System.out.println("beforeall");
	    		CdbConnectionTest cdbConnection = CdbConnectionTest.getInstance();
	    		try (Connection con = cdbConnection.getConnection()) {
	    			ScriptRunner sr = new ScriptRunner(con);
	    			
	    			
	    			Reader reader = new BufferedReader(
	    					new InputStreamReader( getClass().getResourceAsStream(PROP_FILE_NAME) ) );
	    			
	    			
	    			sr.runScript(reader);
	    		}
	            
	            
	            
	            // Your "before all tests" startup logic goes here
	            // The following line registers a callback hook when the root test context is shut down
	            context.getRoot().getStore(GLOBAL).put("test dao computer data base", this);
	        }		
	}

	@Override
	public void close() throws Throwable {
		// TODO Auto-generated method stub
		
	}

}
