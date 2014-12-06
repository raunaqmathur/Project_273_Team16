import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import DropBox.DownloadFiles;
import Database.FilteredUserPhotoPlace;
import Database.SearchFriendsWithPlace;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import facebook4j.FacebookException;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;
import syncdata.FBDBController;

@Controller
@RequestMapping("/")
public class MvCController {

	@RequestMapping(value="/pik-a-place",method=RequestMethod.GET)
    public String index() {
    	System.out.println("Reached mainpage");
        return "Login";
    }
	
	@RequestMapping(value="/signup",method=RequestMethod.GET)
    public String indexJsp() {
    	System.out.println("Reached signup controller page");
        return "index";
    }
	
	@RequestMapping(value="/success",method=RequestMethod.GET)
    public String successJsp() {
		
    	System.out.println("Reached success controller page");
        return "Success";
    }
	
	@RequestMapping(value="/Error",method=RequestMethod.GET)
	public String Errorjsp(){
		System.out.println("Reached Error controller page");
		return "Error";
	}
	@RequestMapping(value="/maps",method=RequestMethod.GET)
    public String MapsJsp() {
		
    	System.out.println("Reached maps controller page");
        return "Maps";
    }
	
	@RequestMapping(value="/Recommendation",method=RequestMethod.GET)
    public String RecommendationJsp() {
		
    	System.out.println("Reached Recommendation controller page");
        return "Recommendation";
    }
	
	@RequestMapping(value="/syncPhotos",method=RequestMethod.POST)
	@ResponseBody
	public List<String> syncPhotos(@RequestBody String data)
    {
		
		DownloadFiles df = new DownloadFiles();
		List<String> picList = null;
		try {
			 picList = df.downloadFile(data.replace("=", ""));
			 
			 
			 
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return picList;
		
    }
	
	@RequestMapping(value="/placePhoto",method=RequestMethod.POST)
	@ResponseBody
    public List<FilteredUserPhotoPlace> placePhoto(@RequestBody String data) {
		JSONObject jsonObj = null;
		List<FilteredUserPhotoPlace> filteredUserPhotoPlace = null;
		try {
			jsonObj = new JSONObject(data);
			System.out.println(jsonObj.getString("k").toString());
			System.out.println(jsonObj.getString("B").toString());
			filteredUserPhotoPlace = getAllData(jsonObj.getDouble("k"),jsonObj.getDouble("B"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return filteredUserPhotoPlace;
		
    }
	
	public List<FilteredUserPhotoPlace> getAllData(double latitude, double longitude)
	{
		
		SearchFriendsWithPlace frdU = new SearchFriendsWithPlace();
		String parentID = "1375341852757639"; 
		List<FilteredUserPhotoPlace> filteredData = null;
		try {
			filteredData = frdU.GetFriendListNPhotos(parentID,latitude,longitude);
			
		} catch (UnknownHostException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filteredData;
	}
	
	
}