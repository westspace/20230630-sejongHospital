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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sh.common.ApiUrl;
import com.sh.common.Maps;

@RestController
public class ManagerController {

	// 세종 충남 대전
	@RequestMapping(value = "/api/getEmrm", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getEmrm(@RequestParam String hAddr) throws IOException {

		
		System.out.println("hAddr : " + hAddr);
		StringBuilder urlBuilder = new StringBuilder(ApiUrl.SERVICE_URL + "/getEmrrmRltmUsefulSckbdInfoInqire");

		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + ApiUrl.API_KEY); /* Service Key */
		urlBuilder.append(
				"&" + URLEncoder.encode("STAGE1", "UTF-8") + "=" + URLEncoder.encode(hAddr, "UTF-8")); /* 주소(시도) */
		urlBuilder.append(
				"&" + URLEncoder.encode("STAGE2", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /* 주소(시군구) */
		urlBuilder.append(
				"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지 번호 */
		urlBuilder.append(
				"&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /* 목록 건수 */

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
	
	// 응급의료기관의 상세정보
	@RequestMapping(value = "/api/getEgytBassInfoInqire", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getEgytBassInfoInqire(@RequestParam String hpid) throws IOException {

		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInfoInqireService/getEgytBassInfoInqire"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + ApiUrl.API_KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("HPID","UTF-8") + "=" + URLEncoder.encode(hpid, "UTF-8")); /*기관ID*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*목록 건수*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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


	//응급의료기관 정보를 시도/시군구/진료요일/기관별/진료과목별로 조회할 수 있다.
	@RequestMapping(value = "/api/getEgytListInfoInqire", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getEgytListInfoInqire() throws IOException {

		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInfoInqireService/getEgytListInfoInqire"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") +  "=" + ApiUrl.API_KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("Q0","UTF-8") + "=" + URLEncoder.encode("서울특별시", "UTF-8")); /*주소(시도)*/
        urlBuilder.append("&" + URLEncoder.encode("Q1","UTF-8") + "=" + URLEncoder.encode("강남구", "UTF-8")); /*주소(시군구)*/
        urlBuilder.append("&" + URLEncoder.encode("QT","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*월~일요일(1~7), 공휴일(8)*/
        urlBuilder.append("&" + URLEncoder.encode("QZ","UTF-8") + "=" + URLEncoder.encode("A", "UTF-8")); /*CODE_MST의'H000' 참조(A~H, J~O, Q)*/
        urlBuilder.append("&" + URLEncoder.encode("QD","UTF-8") + "=" + URLEncoder.encode("D000", "UTF-8")); /*CODE_MST의'D000' 참조(D000~D029)*/
        urlBuilder.append("&" + URLEncoder.encode("QN","UTF-8") + "=" + URLEncoder.encode("(사)삼성생명공익재단 삼성서울병원", "UTF-8")); /*기관명*/
        urlBuilder.append("&" + URLEncoder.encode("ORD","UTF-8") + "=" + URLEncoder.encode("NAME", "UTF-8")); /*순서*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*목록 건수*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
        System.out.println(sb.toString());
		return Maps.json("", "");
	}
	
	//응급의료기관 메시지.
	@RequestMapping(value = "/api/getEmrrmSrsillDissMsgInqire", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getEmrrmSrsillDissMsgInqire() throws IOException {

		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInfoInqireService/getEmrrmSrsillDissMsgInqire"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") +  "=" + ApiUrl.API_KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("HPID","UTF-8") + "=" + URLEncoder.encode("A1800441", "UTF-8")); /*기관ID*/
        urlBuilder.append("&" + URLEncoder.encode("QN","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*기관명*/
        urlBuilder.append("&" + URLEncoder.encode("Q0","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*주소(시도)*/
        urlBuilder.append("&" + URLEncoder.encode("Q1","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*주소(시군구)*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
        
        JSONObject json = XML.toJSONObject(sb.toString());
		String jsonStr = json.toString(4);
		System.out.println(jsonStr);

		return Maps.json("", "");
	}
}