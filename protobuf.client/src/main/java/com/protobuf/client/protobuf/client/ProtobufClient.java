package com.protobuf.client.protobuf.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.googlecode.protobuf.format.JsonFormat;
import com.student.protobuf.StudentTraining.Course;

public class ProtobufClient {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		 InputStream responseStream = executeHttpRequest("http://localhost:8080/courses/1");
	        String jsonOutput = convertProtobufMessageStreamToJsonString(responseStream);
	        System.out.println(jsonOutput);
	        //assertResponse(jsonOutput);
	        
	}
	
	private static InputStream executeHttpRequest(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(request);
        return httpResponse.getEntity().getContent();
    }

    private static String convertProtobufMessageStreamToJsonString(InputStream protobufStream) throws IOException {
        JsonFormat jsonFormat = new JsonFormat();
        Course course = Course.parseFrom(protobufStream);
        BufferedReader br = new BufferedReader(new InputStreamReader(protobufStream));

    		String output;
    		System.out.println("Output from Server .... \n");
    		while ((output = br.readLine()) != null) {
    			System.out.println(output);
    		}
    		
        return jsonFormat.printToString(course);
    }

}
