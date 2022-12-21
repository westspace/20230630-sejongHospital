package com.sh.ctrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sh.common.ApiUrl;
import com.sh.common.Maps;

@RestController
public class ManagementController {

	@RequestMapping(value = "/api/getEmrm", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getEmrm() throws IOException {

		StringBuilder urlBuilder = new StringBuilder(ApiUrl.SERVICE_URL + "/getEmrrmRltmUsefulSckbdInfoInqire");

		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + ApiUrl.API_KEY); /* Service Key */
		urlBuilder.append(
				"&" + URLEncoder.encode("STAGE1", "UTF-8") + "=" + URLEncoder.encode("서울특별시", "UTF-8")); /* 주소(시도) */
		urlBuilder.append(
				"&" + URLEncoder.encode("STAGE2", "UTF-8") + "=" + URLEncoder.encode("강남구", "UTF-8")); /* 주소(시군구) */
		urlBuilder.append(
				"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지 번호 */
		urlBuilder.append(
				"&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /* 목록 건수 */

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		// System.out.println(sb.toString());

		JSONObject json = XML.toJSONObject(sb.toString());
		String jsonStr = json.toString(4);
		System.out.println(jsonStr);

		return Maps.json("", "", json.toString());
	}

	//응급의료기관 위치정보를 경도/위도별 반경내 정보를 조회할 수 있다.
	@RequestMapping(value = "/api/getEgytLcinfoInqire", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getEgytLcinfoInqire() throws IOException {
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B552657/ErmctInfoInqireService/getEgytLcinfoInqire"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + ApiUrl.API_KEY); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("WGS84_LON", "UTF-8") + "="
				+ URLEncoder.encode("127.08515659273706", "UTF-8")); /* 병원경도 */
		urlBuilder.append("&" + URLEncoder.encode("WGS84_LAT", "UTF-8") + "="
				+ URLEncoder.encode("37.488132562487905", "UTF-8")); /* 병원위도 */
		urlBuilder.append(
				"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지 번호 */
		urlBuilder.append(
				"&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /* 목록 건수 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		//System.out.println(sb.toString());
		

		JSONObject json = XML.toJSONObject(sb.toString());
		String jsonStr = json.toString(4);
		System.out.println(jsonStr);
		
		return Maps.json("", "", json.toString());
	}

}