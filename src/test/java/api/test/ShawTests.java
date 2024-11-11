package api.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
//import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
//import org.json.simple.JSONArray;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.xml.internal.Parser;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import api.endpoints.ShawEndpoints;
import api.utilities.DataProviders;
import api.utilities.ExtentReportManager;
import io.restassured.response.Response;

import api.utilities.Resources;
public class ShawTests{

	
	
	
@Test(dataProvider = "Data", dataProviderClass = DataProviders.class)
public void PayloadReport(String reportType, String sheath, String segment, String expectedBodyPath) throws IOException, ParseException {
	
	Response response= ShawEndpoints.PayloadReport(reportType, sheath, segment);
	Assert.assertEquals(response.getStatusCode(), 200);
	
	
	FileWriter file = new FileWriter("./Responses/"+reportType+"_"+sheath+"_"+segment+".json");
	file.write(response.getBody().prettyPrint());
	file.flush();
	file.close();
	
	/*JSONParser parser = new JSONParser();
	Object obj = parser.parse(new FileReader("./output.json"));
	JSONObject jobj = (JSONObject) obj;
	jobj.replace("Summary[0][1]", "XYZ");
	
	jobj.put("jobj.Summary[0][1]", "XYZ");
	jobj.put("Summary[1][1]", "ABC");
	jobj.put("Summary[3][1]", 123);
	
	FileWriter fw;
	fw = new FileWriter("./output2.json");
	fw.write(jobj.toJSONString(jobj));
	fw.flush();
	fw.close();
	*/
	String bodyData = response.jsonPath().getString("Data");
	System.out.println("Actual body: "+ bodyData);
	
	/*JSONParser parser = new JSONParser();
	Object obj = parser.parse(new FileReader("./Expectedoutput.json"));
	JSONObject jobj = (JSONObject) obj;
	
	String ExpectedOutput = jobj.get("Data").toString();
	System.out.println("Expeted body: "+ExpectedOutput);
	//Assert.assertEquals(bodyData.equals(ExpectedOutput), true);
	
	String actualBody = response.getBody().asString();
	System.out.println("Expected Date: "+ jobj.get("Summary[0][1]").toString());
	System.out.println("Actual Date: "+response.jsonPath().getString("Summary[0][1]"));
	 JSONAssert.assertEquals(jobj.toJSONString(), actualBody,
	            new CustomComparator(JSONCompareMode.LENIENT,
	                    new Customization("Summary[0][1]", (o1, o2) -> true),
	                    new Customization("Summary[1][1]", (o1, o2) -> true),
	                    new Customization("Summary[3][1]", (o1, o2) -> true)
	            ));
	*/
	String expectedBody="";
	try {
	      File myObj = new File(expectedBodyPath);
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	        expectedBody = myReader.nextLine();
	        System.out.println("Expected Body: "+expectedBody);
	      }
	      myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	
	Assert.assertEquals(bodyData, expectedBody);
	
	
	
	
	
	
	
	
	
	
	/*
	File f = new File("C:\\Users\\rakk0001\\OneDrive - SpatialNetworX\\Desktop\\PayloadReport_ThirdParty.json");
	FileReader fr = new FileReader(f);
	JSONTokener jt = new JSONTokener(fr);
	JSONObject jo = new JSONObject(jt);
	response.body().ass
	*/
}


}


