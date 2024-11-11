package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import io.restassured.response.Response;

public class ShawEndpoints {
	static ResourceBundle getProperty() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
		}
	
	public static Response PayloadReport(String reportType, String sheath, String segment) {
		String get_url=getProperty().getString("PayloadReport_ThirdParty");
		Response response = given()
				.auth().basic(getProperty().getString("user"), getProperty().getString("password"))
			.queryParam("ReportType", reportType)
			.queryParam("Sheath", sheath)
			.queryParam("Segment", segment)
		.when()
			.get(get_url);
		
		return response;	
	}
}
