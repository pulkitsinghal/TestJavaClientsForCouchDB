import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import com.fermyon.router.pojos.shoppinpal.FoursquareVenue;

public class TestClientsWithCouchDB {

	@org.junit.Test
	public void testCloudantWithEktorp() throws IOException {
		Properties props = new Properties();
        URL url = ClassLoader.getSystemResource("couchdb.properties");
        props.load(url.openStream());

        // create the client
		org.ektorp.http.HttpClient httpClient = cloudantClient(props);

		// setup the db
		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
		CouchDbConnector db = new StdCouchDbConnector(props.getProperty("couchdb.name"), dbInstance);

		// ===test1===
		FoursquareVenue venue = db.get(FoursquareVenue.class, props.getProperty("cloudant.testId"));
		System.out.println(venue + "\n" + venue.posUrl);
	}

	@org.junit.Test
	public void testIrisCouchWithEktorp() throws IOException {
		Properties props = new Properties();
        URL url = ClassLoader.getSystemResource("couchdb.properties");
        props.load(url.openStream());

        // create the client
		org.ektorp.http.HttpClient httpClient = iriscouchClient(props);

		// setup the db
		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
		CouchDbConnector db = new StdCouchDbConnector(props.getProperty("couchdb.name"), dbInstance);

		// ===test1===
		System.out.println(
				"GET https://" + props.getProperty("iriscouch.host") +
				":" + props.getProperty("iriscouch.port") +
				"/" + props.getProperty("couchdb.name") +
				"/" + props.getProperty("iriscouch.testId"));
		FoursquareVenue venue = db.get(FoursquareVenue.class, props.getProperty("iriscouch.testId"));
		System.out.println(venue + "\n" + venue.posUrl);
	}

	private static org.ektorp.http.HttpClient cloudantClient(Properties props) {
		System.out.println(
				"cloudant.host " + props.getProperty("cloudant.host") + "\n" +
				"cloudant.port " + props.getProperty("cloudant.port") + "\n" +
				"ektorp.enableSSL " + props.getProperty("ektorp.enableSSL") + "\n" +
				"ektorp.relaxedSSLSettings " + props.getProperty("ektorp.relaxedSSLSettings") + "\n" +
				"cloudant.username " + props.getProperty("cloudant.username") + "\n" +
				"cloudant.password " + props.getProperty("cloudant.password"));
		return new StdHttpClient.Builder()
		.host(props.getProperty("cloudant.host"))
		.port(Integer.parseInt(props.getProperty("cloudant.port")))
		.enableSSL(Boolean.parseBoolean(props.getProperty("ektorp.enableSSL")))
		.relaxedSSLSettings(Boolean.parseBoolean(props.getProperty("ektorp.relaxedSSLSettings")))
		.username(props.getProperty("cloudant.username"))
		.password(props.getProperty("cloudant.password"))
		.build();
	}

	private static org.ektorp.http.HttpClient iriscouchClient(Properties props) {
		System.out.println(
				"iriscouch.host " + props.getProperty("iriscouch.host") + "\n" +
				"iriscouch.port " + props.getProperty("iriscouch.port") + "\n" +
				"ektorp.enableSSL " + props.getProperty("ektorp.enableSSL") + "\n" +
				"ektorp.relaxedSSLSettings " + props.getProperty("ektorp.relaxedSSLSettings") + "\n" +
				"iriscouch.username " + props.getProperty("iriscouch.username") + "\n" +
				"iriscouch.password " + props.getProperty("iriscouch.password"));
        return new StdHttpClient.Builder()
		.host(props.getProperty("iriscouch.host"))
		.port(Integer.parseInt(props.getProperty("iriscouch.port")))
		.enableSSL(Boolean.parseBoolean(props.getProperty("ektorp.enableSSL")))
		.relaxedSSLSettings(Boolean.parseBoolean(props.getProperty("ektorp.relaxedSSLSettings")))
		.username(props.getProperty("iriscouch.username"))
		.password(props.getProperty("iriscouch.password"))
		.build();
	}
}
