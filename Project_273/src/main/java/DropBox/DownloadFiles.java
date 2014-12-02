package DropBox;

import Database.OpenDatabase;
import Database.UserPhotosPlaces;

import com.dropbox.core.*;

import facebook4j.ResponseList;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;
import facebook4j.internal.org.json.JSONArray;

import java.io.*;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

public class DownloadFiles {

	
	public void downloadFile(String parentId) throws IOException
	{
		final String APP_KEY = "2uguk7d5cb3n4xm";
        final String APP_SECRET = "wxib4jn5nym6m27";

        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0",Locale.getDefault().toString());
        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
    
        String accessToken = "qMr5yR5h5qsAAAAAAAAABqjrFo-mJAnvweWEoimhnf3Hzq9ucCTMlcVMcdZqwoMK";
        		
        DbxClient client = new DbxClient(config, accessToken);
        //DbxEntry.Folder folder= client.createFolder("/"+parentId);
        System.out.println("INtoppppppp downloading ");
        DbxEntry.WithChildren listing = null;
		try {
			listing = client.getMetadataWithChildren("/");
		} catch (DbxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        boolean present = false;
        
        if(!(new File("./img/downloadedImages/" + parentId)).exists())
			(new File("./img/downloadedImages/" + parentId)).mkdirs();
		
        for (DbxEntry child : listing.children) {
            if(child.name == parentId)
            {
            	present = true;
            	break;
            }
        }
            listing = null;
    		try {
    			listing = client.getMetadataWithChildren("/" + parentId);
    		} catch (DbxException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		
    		if(listing != null  && !listing.children.isEmpty())
    		{
		    		for (DbxEntry childFiles : listing.children) {
		    			
		    			
		                try {
		                	
		                    DbxEntry.File downloadedFile = client.getFile("/" + parentId + "/" + childFiles.name, null,
		                    		new FileOutputStream("./img/downloadedImages/" + parentId + "/" + childFiles.name));
		                    //File inputFile = new File((File)downloadedFile);
			    	        
		                    System.out.println("downloading " + childFiles.name);
		                   
		                } catch (DbxException e) {
		    				// TODO Auto-generated catch block
		                	System.out.println(e.getMessage());
		    			} catch (IOException e) {
		    				// TODO Auto-generated catch block
		    				System.out.println(e.getMessage());
		    			} 
		                
		    		
		    		}
    		}
    		
            
            
            
        }
		
	
	
}
