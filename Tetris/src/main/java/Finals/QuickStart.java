/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package Finals;

import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class QuickStart {

	public static void main(String[] args) throws Exception {
		String urlPath = "http://pajitnov.inseng.net";
		
		 HashMap<String, Integer> postMap = new HashMap<String, Integer>();
         postMap.put("seats", 1);
         postMap.put("turns", 10);
         postMap.put("initial_garbage", 0);
         
         Header[] startGame = httpPost(urlPath, postMap);
         String gameId = null;
         for(Header h : startGame){
        	 System.out.println(h.getName() + ": " + h.getValue());
        	 if(h.getName() == "Location"){
        		 gameId = h.getValue();
        		 System.out.println("gameId: " + gameId);
        	 }
         }
         
         urlPath += gameId + "/players";
         
         HashMap<String, String> playerMap = new HashMap<String, String>();
         Header[] addPlayer = httpPost(urlPath, playerMap);
         for(Header h : addPlayer){
        	 System.out.println(h.getName() + ": " + h.getValue());
         }
	}
	
    public static Header[] httpPost(String urlPath, HashMap postMap) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(urlPath);
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST call CloseableHttpResponse#close() from a finally clause.
            // Please note that if response content is not fully consumed the underlying
            // connection cannot be safely re-used and will be shut down and discarded
            // by the connection manager.
            try {
                System.out.println(response1.getStatusLine());
                HttpEntity entity1 = response1.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity1);
            } finally {
                response1.close();
            }

            HttpPost httpPost = new HttpPost(urlPath);
           
            Gson gson = new Gson();
            String pm = gson.toJson(postMap);
            
            httpPost.setEntity(createStringEntity(pm));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);

            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity2);
                Header[] headers = response2.getAllHeaders();
                return headers;
                /*for(Header h : headers){
                	System.out.println(h.getName() + "\n" + h.getValue());
                }*/
            } finally {
                response2.close();
            }
        } finally {
            httpclient.close();
        }
    }
    
    private static HttpEntity createStringEntity(String params) {
        StringEntity se = null;
            se = new StringEntity(params.toString(), "UTF-8");
            se.setContentType("application/json; charset=UTF-8");
        return se;
    }

}