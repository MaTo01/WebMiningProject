package com.apiguardian;

import com.apiguardian.bean.Response;
import com.apiguardian.bean.ResponseWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GuardianContentAPI {

  static {
// Only one time
    Unirest.setObjectMapper(new ObjectMapper() {
      private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
              = new com.fasterxml.jackson.databind.ObjectMapper();

      public <T> T readValue(String value, Class<T> valueType) {
        try {
//          System.out.println(value+" - "+valueType.getName());
          return jacksonObjectMapper.readValue(value, valueType);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }

      public String writeValue(Object value) {
        try {
          return jacksonObjectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
          throw new RuntimeException(e);
        }
      }
    });
  }

  private final static String TARGET_URL = "http://content.guardianapis.com/search";
  private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  private final String apiKey;
  private String section;
  private String tag;
  private Date toDate;
  private Date fromDate;
  private int pageSize = 0;
  private int page = 1;

  public GuardianContentAPI(final String apiKey) {
    this.apiKey = apiKey;
  }

  public void setSection(String section) {
    this.section = section;
  }

  public void setFromDate(Date date) {
    this.fromDate = date;
  }

  public void setToDate(Date date) {
    this.toDate = date;
  }

  public void setPageSize(int size) {
    this.pageSize = size;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public Response getContent() throws UnirestException {
    return getContent(null);
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public Response getContent(String query) throws UnirestException {
    HttpClient httpClient = HttpClients.custom()
            .disableCookieManagement()
            .build();
    Unirest.setHttpClient(httpClient);

    HttpRequest request = Unirest.get(TARGET_URL)
            .queryString("api-key", apiKey)
            .header("accept", "application/json");
    if (query != null && !query.isEmpty()) {
      request.queryString("q", query);
    }

    if (section != null && !section.isEmpty()) {
      request.queryString("section", section);
    }

    if (tag != null && !tag.isEmpty()) {
      request.queryString("tag", tag);
    }

    if (fromDate != null){
      request.queryString("from-date", dateFormat.format(fromDate));
    }
    if (toDate != null){
      request.queryString("to-date", dateFormat.format(toDate));
    }

    if (pageSize > 0) {
      request.queryString("page-size", pageSize);
    }

    if(page > 1) {
      request.queryString("page", page);
    }

    request.queryString("show-fields", "all");

    HttpResponse<ResponseWrapper> response = request.asObject(ResponseWrapper.class);
    return response.getBody().getResponse();

  }
}
