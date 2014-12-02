package Database;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;

import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

public class SearchFriendsWithPlace {

	List<String> dataFriend = new ArrayList<String>();
	//List<String,String> dataUserPlace = new ArrayList<String,String>();
	
	String cityName = "San Jose";
	
	List<String> dataFriendFiltered = new ArrayList<String>();
	List<String> dataPlaceFiltered = new ArrayList<String>();
	List<String> dataPhotoFiltered = new ArrayList<String>();
	///HashMap<String, String> dataFriendPhoto = new HashMap();
	List<FilteredUserPhotoPlace> filteredData  = new ArrayList<FilteredUserPhotoPlace>();
	
	
	//List<List<List<String>>> dataPhotoFiltered = new ArrayList();
	
	public void GetFriendListNPhotos(String parentId) throws UnknownHostException
	{
			char[] password = "1234".toCharArray();
			
			MongoCredential credential = MongoCredential.createMongoCRCredential("sjsuTeam16", "fbdata",password);
			MongoClient mongoClient = new MongoClient(new ServerAddress("ds047720.mongolab.com",47720), Arrays.asList(credential));
			DB db = mongoClient.getDB( "fbdata" );
			DBCollection dbcUser = db.getCollection("User");
			
			
			
			
			
			
			BasicDBObject query = null;
			query = new BasicDBObject("id", parentId);
		
			DBCursor cursor = dbcUser.find(query);
			//boolean flag = false;
			
			try {
			   if(cursor.hasNext()) {
				   //System.out.println("hi0");
				   GetFriendList(parentId,db);
				   GetFriendPlaceList(parentId,db);
				   GetFriendPhoto(parentId,db);
			   }
			} finally {
		   cursor.close();
		   
		}
			System.out.println("final data");
			for(int i=0;i<filteredData.size();i++)
			{
				
				System.out.println(filteredData.get(i).parentId + "-- " + filteredData.get(i).photoId + "--" + filteredData.get(i).placeId);
			}
			
	
	}
	
	public void GetFriendList(String parentId, DB db)
	{
		
		DBCollection dbcUser = db.getCollection("UserFriends");
		
		
		
		
		//System.out.println("hi1");
		
		BasicDBObject query = null;
		query = new BasicDBObject("parentId", parentId);
	
		DBCursor cursor = dbcUser.find(query);
		DBObject dbotemp = null;
		try {
			   while(cursor.hasNext()) {
				   
				   dbotemp = cursor.next();   
				   
				  
				   dataFriend.add(dbotemp.get("id").toString());
			   }
			} finally {
		   cursor.close();
		   
		}
	}
	
	public void GetFriendPlaceList(String parentId, DB db)
	{
		
		DBCollection dbcUser = db.getCollection("UserPlaces");
		
		
		
		
		
		
		BasicDBObject query = null;
		
		for(int i=0; i < dataFriend.size(); i++)
		{
			//System.out.println(dataFriend.get(i));
			//System.out.println("displaying " + dataFriend.get(i));
				query = new BasicDBObject("parentId", dataFriend.get(i));
			
				DBCursor cursor = dbcUser.find(query);
				DBObject dbotemp = null;
				try {
					   while(cursor.hasNext()) {
						   
						   //System.out.println("hi2");
						   BasicDBObject object = (BasicDBObject) cursor.next();
						      BasicDBObject place =  (BasicDBObject) object.get("place");
						      BasicDBObject location = (BasicDBObject) place.get("location");
						      
						      String city = "";
						      if(location.containsField("city"))
						    	  		city= location.get("city").toString();
						      else
						    	  city = place.get("name").toString();
						      
						      //System.out.println("it is: " + city + " " + object.get("id").toString());
						      if(city.indexOf(cityName) >= 0)
						      {
						    	  //System.out.println("he: " + place.get("id").toString() + " " + object.get("id").toString());
						    	  //dataUserPlace.put(dataFriend.get(i),object.get("id").toString());
						    	  dataFriendFiltered.add(dataFriend.get(i));
						    	  dataPlaceFiltered.add(place.get("id").toString());
						      }
						   
					   }
					} finally {
				   cursor.close();
				   
				}
		}
	}
	
	public void GetFriendPhoto(String parentId, DB db)
	{
		
		DBCollection dbcUser = db.getCollection("UserPhotosPlaces");
		
		
		
		
		
		
		BasicDBObject query = null;
		//int counter = 0;
		for(int i=0; i < dataPlaceFiltered.size(); i++)
		{
			//System.out.println(dataFriend.get(i));
			//System.out.println("displaying for " + dataFriendFiltered.get(i) + "  " + dataPlaceFiltered.get(i));
				query = new BasicDBObject("placeId", dataPlaceFiltered.get(i));
				query.append("parentId", dataFriendFiltered.get(i));
			
				DBCursor cursor = dbcUser.find(query);
				DBObject dbotemp = null;
				FilteredUserPhotoPlace fTemp = null;
				try {
					   while(cursor.hasNext()) {
						   dbotemp = cursor.next();
						   //System.out.println("pic is " + dbotemp.get("photoId").toString());
						   //dataPhotoFiltered.add(dataFriendFiltered.get(i));
						   //dataFriendPhoto.put(dataFriendFiltered.get(i), dbotemp.get("photoId").toString());
						   fTemp = new FilteredUserPhotoPlace();
						   fTemp.placeId = dataPlaceFiltered.get(i);
						   fTemp.photoId = dbotemp.get("photoId").toString();
						   fTemp.parentId = dataFriendFiltered.get(i);
						   filteredData.add(fTemp);
						   /*counter++;
						   System.out.println(counter + " pic is " + dbotemp.get("photoId").toString());*/
					   }
					} finally {
				   cursor.close();
				   
				}
		}
	}
	
	
	
}
